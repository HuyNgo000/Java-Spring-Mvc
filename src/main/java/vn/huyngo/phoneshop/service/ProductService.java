package vn.huyngo.phoneshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.huyngo.phoneshop.domain.CHITIETDONHANG;
import vn.huyngo.phoneshop.domain.CHITIETGIOHANG;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.GIOHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.repository.*;

@Service
public class ProductService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService,
            DaoAuthenticationProvider authProvider, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository, UserRepository userRepository,
            ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public SANPHAM handleSaveProduct(SANPHAM sanpham) {
        return this.productRepository.save(sanpham);
    }

    public Page<SANPHAM> fetchProducts(Pageable page) {
        return this.productRepository.findAll(page);
    }

    public List<SANPHAM> fetchPrs() {
        return this.productRepository.findAll();
    }

    public Optional<SANPHAM> getDetailProduct(Long id) {
        return this.productRepository.findByMaSanPham(id);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public void addPrToCart(String email, Long productId, HttpSession session) {
        NGUOIDUNG nguoidung = this.userService.getUserByEmail(email);
        if (nguoidung != null) {
            GIOHANG giohang = this.cartRepository.findByNguoiDung(nguoidung);
            if (giohang == null) {
                GIOHANG newCart = new GIOHANG();
                newCart.setNguoiDung(nguoidung);
                newCart.setTong(0);
                giohang = this.cartRepository.save(newCart);
            }

            Optional<SANPHAM> p = this.productRepository.findByMaSanPham(productId);
            if (p.isPresent()) {
                SANPHAM sanpham = p.get();

                CHITIETGIOHANG od = this.cartDetailRepository.findByGioHangAndSanPham(giohang, sanpham);
                if (od == null) {
                    CHITIETGIOHANG cd = new CHITIETGIOHANG();
                    cd.setGioHang(giohang);
                    cd.setSanPham(sanpham);
                    cd.setGia(sanpham.getGia());
                    cd.setSoLuong((long) 1);
                    this.cartDetailRepository.save(cd);

                    int s = giohang.getTong() + 1;
                    giohang.setTong(s);
                    this.cartRepository.save(giohang);
                    session.setAttribute("sum", s);
                } else {
                    od.setSoLuong(od.getSoLuong() + 1);
                }

            }
        }
    }

    public GIOHANG getCarByUser(NGUOIDUNG nguoidung) {
        return this.cartRepository.findByNguoiDung(nguoidung);
    }

    public CHITIETGIOHANG getCartDetailById(Long id) {
        return this.cartDetailRepository.findByMaChiTietGioHANG(id);
    }

    public void deleteCartDetail(Long id) {
        this.cartDetailRepository.deleteById(id);
    }

    public void removeCartDetail(Long id, HttpSession session) {
        Optional<CHITIETGIOHANG> cartDetailOptional = this.cartDetailRepository.findById(id);
        if (cartDetailOptional.isPresent()) {
            CHITIETGIOHANG cartDetail = cartDetailOptional.get();
            GIOHANG cart = cartDetail.getGioHang();
            this.cartDetailRepository.deleteById(id);
            if (cart.getTong() > 1) {
                int s = cart.getTong() - 1;
                cart.setTong(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(cart);
            } else {
                this.cartRepository.deleteById(cart.getMaGioHang());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void updateCartBeforeCheckout(List<CHITIETGIOHANG> cartDetails) {
        for (CHITIETGIOHANG cartDetail : cartDetails) {
            Optional<CHITIETGIOHANG> opCartDetail = this.cartDetailRepository
                    .findById(cartDetail.getMaChiTietGioHANG());
            if (opCartDetail.isPresent()) {
                CHITIETGIOHANG currentCart = opCartDetail.get();
                currentCart.setSoLuong(cartDetail.getSoLuong());
                this.cartDetailRepository.save(currentCart);
            }
        }
    }

    public void placeOrder(NGUOIDUNG user, HttpSession session, String receiverName, String receiverAdress,
            String receiverPhone) {
        GIOHANG cart = this.cartRepository.findByNguoiDung(user);
        if (cart != null) {
            List<CHITIETGIOHANG> cartDetails = cart.getChiTietGioHang();
            if (cartDetails != null) {
                DONHANG order = new DONHANG();
                order.setNguoiDung(user);
                order.setDiaChiNguoiNhan(receiverAdress);
                order.setSdtNguoiNhan(receiverPhone);
                order.setTenNguoiNhan(receiverName);

                double sum = 0;
                for (CHITIETGIOHANG cd : cartDetails) {
                    sum += cd.getGia();
                }
                order.setTongGia(sum);
                order = this.orderRepository.save(order);

                for (CHITIETGIOHANG cartDetail : cartDetails) {
                    CHITIETDONHANG orderDetail = new CHITIETDONHANG();
                    orderDetail.setDonHang(order);
                    orderDetail.setSanPham(cartDetail.getSanPham());
                    orderDetail.setGia(cartDetail.getGia());
                    orderDetail.setSoluong(cartDetail.getSoLuong());
                    order.setTrangThai("Pending");

                    this.orderDetailRepository.save(orderDetail);

                }
                for (CHITIETGIOHANG cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getMaChiTietGioHANG());
                }

                this.cartRepository.deleteById(cart.getMaGioHang());

                session.setAttribute("sum", 0);

            }
        }

    }

    public Long countUser() {
        return this.userRepository.count();
    }

    public Long countProduct() {
        return this.productRepository.count();
    }

    public Long countOrder() {
        return this.orderRepository.count();
    }

    public Long countReview() {
        return this.reviewRepository.count();
    }

    public void handleAddProductCart(String email, long prodctId, HttpSession session, Long quantity) {
        NGUOIDUNG user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check xem đã có Cart hay chưa
            GIOHANG cart = this.cartRepository.findByNguoiDung(user);

            if (cart == null) {
                // tạo mới Cart
                GIOHANG otheCart = new GIOHANG();

                otheCart.setNguoiDung(user);
                otheCart.setTong(0);

                cart = this.cartRepository.save(otheCart);
            }
            // tìm product
            Optional<SANPHAM> productOptional = this.productRepository.findById(prodctId);
            if (productOptional.isPresent()) {
                SANPHAM otherProduct = productOptional.get();

                // Check sp đã tưnnfg có trong giỏ hàng chưa
                CHITIETGIOHANG oldDetail = this.cartDetailRepository.findByGioHangAndSanPham(cart, otherProduct);

                if (oldDetail == null) {
                    CHITIETGIOHANG cartDetail = new CHITIETGIOHANG();

                    cartDetail.setGioHang(cart);
                    cartDetail.setSanPham(otherProduct);
                    cartDetail.setGia(otherProduct.getGia());
                    cartDetail.setSoLuong(quantity);

                    // save cart detail
                    this.cartDetailRepository.save(cartDetail);

                    // update Cart
                    int sum = cart.getTong() + 1;
                    cart.setTong(sum);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", sum);
                } else {
                    oldDetail.setSoLuong(oldDetail.getSoLuong() + quantity);

                    this.cartDetailRepository.save(oldDetail);
                }

            }

        }
    }

}
