package vn.huyngo.phoneshop.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.huyngo.phoneshop.domain.CHITIETDONHANG;
import vn.huyngo.phoneshop.domain.CHITIETGIOHANG;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.GIOHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.domain.SANPHAM_;
import vn.huyngo.phoneshop.domain.dto.ProductCriteriaDTO;
import vn.huyngo.phoneshop.repository.*;
import vn.huyngo.phoneshop.service.Spectification.ProductSpecs;

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

    public List<SANPHAM> getProductsByFactory(String factory) {
        return productRepository.findByNoiSanXuat(factory);
    }

    public Page<SANPHAM> fetchProducts(Pageable page) {
        return this.productRepository.findAll(page);
    }

    public Page<SANPHAM> searchProducts(Pageable page, String search) {
        return this.productRepository.findAll(ProductSpecs.nameLike(search), page);
    }

    public List<SANPHAM> fetchPrs() {
        return this.productRepository.findAll();
    }

    public Page<SANPHAM> fetchProductsWithSpec(Pageable page, ProductCriteriaDTO productCriteriaDTO) {

        if (productCriteriaDTO.getRom() == null
                && productCriteriaDTO.getFactory() == null
                && productCriteriaDTO.getPrice() == null
                && productCriteriaDTO.getScreen() == null) {
            return this.productRepository.findAll(page);
        }

        Specification<SANPHAM> combinedSpec = Specification.where(null);

        if (productCriteriaDTO.getRom() != null && productCriteriaDTO.getRom().isPresent()) {
            Specification<SANPHAM> currentSpecs = ProductSpecs.matchListRom(productCriteriaDTO.getRom().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }
        if (productCriteriaDTO.getScreen() != null && productCriteriaDTO.getScreen().isPresent()) {
            Specification<SANPHAM> currentSpecs = ProductSpecs.matchListScreen(productCriteriaDTO.getScreen().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }
        if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getFactory().isPresent()) {
            Specification<SANPHAM> currentSpecs = ProductSpecs.matchListFactory(productCriteriaDTO.getFactory().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }

        if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()) {
            Specification<SANPHAM> currentSpecs = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }
        return this.productRepository.findAll(combinedSpec, page);
    }

    // case 6
    public Specification<SANPHAM> buildPriceSpecification(List<String> price) {
        Specification<SANPHAM> combinedSpec = Specification.where(null); // disconjunction
        for (String p : price) {
            double min = 0;
            double max = 0;

            // Set the appropriate min and max based on the price range string
            switch (p) {
                case "duoi-10-trieu":
                    min = 1;
                    max = 10000000;
                    break;
                case "10-20-trieu":
                    min = 10000000;
                    max = 20000000;
                    break;
                case "20-30-trieu":
                    min = 20000000;
                    max = 30000000;
                    break;
                case "tren-30-trieu":
                    min = 30000000;
                    max = 200000000;
                    break;
            }

            if (min != 0 && max != 0) {
                Specification<SANPHAM> rangeSpec = ProductSpecs.matchMultiplePrice(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }

        return combinedSpec;
    }

    public Optional<SANPHAM> getDetailProduct(Long id) {
        return this.productRepository.findByMaSanPham(id);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    // public void addPrToCart(String email, long productId, HttpSession session,
    // long quantity) {
    // NGUOIDUNG nguoidung = this.userService.getUserByEmail(email);
    // if (nguoidung != null) {
    // GIOHANG giohang = this.cartRepository.findByNguoiDung(nguoidung);
    // if (giohang == null) {
    // GIOHANG newCart = new GIOHANG();
    // newCart.setNguoiDung(nguoidung);
    // newCart.setTong(0);
    // giohang = this.cartRepository.save(newCart);
    // }

    // Optional<SANPHAM> p = this.productRepository.findByMaSanPham(productId);
    // if (p.isPresent()) {
    // SANPHAM sanpham = p.get();

    // CHITIETGIOHANG od =
    // this.cartDetailRepository.findByGioHangAndSanPham(giohang, sanpham);
    // if (od == null) {
    // CHITIETGIOHANG cd = new CHITIETGIOHANG();
    // cd.setGioHang(giohang);
    // cd.setSanPham(sanpham);
    // cd.setGia(sanpham.getGia());
    // cd.setSoLuong(quantity);
    // this.cartDetailRepository.save(cd);

    // int s = giohang.getTong() + 1;
    // giohang.setTong(s);
    // this.cartRepository.save(giohang);
    // session.setAttribute("sum", s);
    // } else {
    // od.setSoLuong(od.getSoLuong() + quantity);
    // this.cartDetailRepository.save(od);
    // }

    // }
    // }
    // }

    public String addPrToCart(String email, long productId, HttpSession session, long quantity) {
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

                // ✅ Kiểm tra số lượng tồn kho
                if (sanpham.getSoLuong() < quantity) {
                    return "not-enough-stock";
                }

                CHITIETGIOHANG od = this.cartDetailRepository.findByGioHangAndSanPham(giohang, sanpham);
                if (od == null) {
                    CHITIETGIOHANG cd = new CHITIETGIOHANG();
                    cd.setGioHang(giohang);
                    cd.setSanPham(sanpham);
                    cd.setGia(sanpham.getGia());
                    cd.setSoLuong(quantity);
                    this.cartDetailRepository.save(cd);
                    giohang.setTong(giohang.getTong() + 1);
                    this.cartRepository.save(giohang);
                    session.setAttribute("sum", giohang.getTong());
                } else {
                    // Kiểm tra tồn kho với số lượng mới cộng thêm
                    if (sanpham.getSoLuong() < od.getSoLuong() + quantity) {
                        return "not-enough-stock";
                    }
                    od.setSoLuong(od.getSoLuong() + quantity);
                    this.cartDetailRepository.save(od);
                }

                return String.valueOf(giohang.getTong());
            }
        }

        return "error";
    }

    public GIOHANG getCarByUser(NGUOIDUNG nguoidung) {
        return this.cartRepository.findByNguoiDung(nguoidung);
    }

    public Optional<CHITIETGIOHANG> getCartDetailById(Long id) {
        return this.cartDetailRepository.findByMaChiTietGioHang(id);
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
                    .findByMaChiTietGioHang(cartDetail.getMaChiTietGioHang());
            if (opCartDetail.isPresent()) {
                CHITIETGIOHANG currentCart = opCartDetail.get();
                currentCart.setSoLuong(cartDetail.getSoLuong());
                this.cartDetailRepository.save(currentCart);
            }
        }
    }

    // public void placeOrder(NGUOIDUNG user, HttpSession session, String
    // receiverName, String receiverAdress,
    // String receiverPhone, String paymentMethod, String uuid, String totalPrice) {
    // GIOHANG cart = this.cartRepository.findByNguoiDung(user);
    // if (cart != null) {
    // List<CHITIETGIOHANG> cartDetails = cart.getChiTietGioHang();
    // if (cartDetails != null) {
    // DONHANG order = new DONHANG();
    // order.setNguoiDung(user);
    // order.setDiaChiNguoiNhan(receiverAdress);
    // order.setSdtNguoiNhan(receiverPhone);
    // order.setTenNguoiNhan(receiverName);
    // order.setTrangThaiThanhToan("PAYMENT_UNPAID");
    // order.setHinhThucThanhToan(paymentMethod);
    // order.setMaThanhToan(paymentMethod.equals("COD") ? "UNKNOWN" : uuid);

    // double sum = 0;
    // for (CHITIETGIOHANG cd : cartDetails) {
    // sum += cd.getGia() * cd.getSoLuong();
    // }
    // order.setTongGia(sum);
    // order = this.orderRepository.save(order);

    // for (CHITIETGIOHANG cartDetail : cartDetails) {
    // CHITIETDONHANG orderDetail = new CHITIETDONHANG();
    // orderDetail.setDonHang(order);
    // orderDetail.setSanPham(cartDetail.getSanPham());
    // orderDetail.setGia(cartDetail.getGia());
    // orderDetail.setSoluong(cartDetail.getSoLuong());
    // order.setTrangThai("Pending");

    // this.orderDetailRepository.save(orderDetail);

    // SANPHAM p = cartDetail.getSanPham();
    // p.setSoLuong(p.getSoLuong() - cartDetail.getSoLuong());

    // }
    // for (CHITIETGIOHANG cd : cartDetails) {
    // this.cartDetailRepository.deleteById(cd.getMaChiTietGioHang());
    // }

    // this.cartRepository.deleteById(cart.getMaGioHang());

    // session.setAttribute("sum", 0);

    // }
    // }

    // }

    public void placeOrder(NGUOIDUNG user, HttpSession session, String receiverName, String receiverAdress,
            String receiverPhone, String paymentMethod, String uuid, String totalPrice) {
        GIOHANG cart = this.cartRepository.findByNguoiDung(user);
        if (cart != null) {
            List<CHITIETGIOHANG> cartDetails = cart.getChiTietGioHang();
            if (cartDetails != null && !cartDetails.isEmpty()) {

                // Bước 1: Kiểm tra tồn kho
                for (CHITIETGIOHANG cd : cartDetails) {
                    SANPHAM p = cd.getSanPham();
                    if (p.getSoLuong() < cd.getSoLuong()) {
                        throw new RuntimeException("Sản phẩm " + p.getTen()
                                + " không đủ hàng trong kho. Hãy kiểm tra lại số lượng trước khi thanh toán");
                    }
                }

                // Bước 2: Tạo đơn hàng như cũ
                DONHANG order = new DONHANG();
                order.setNguoiDung(user);
                order.setDiaChiNguoiNhan(receiverAdress);
                order.setSdtNguoiNhan(receiverPhone);
                order.setTenNguoiNhan(receiverName);
                order.setTrangThaiThanhToan("PAYMENT_UNPAID");
                order.setHinhThucThanhToan(paymentMethod);
                order.setMaThanhToan(paymentMethod.equals("COD") ? "UNKNOWN" : uuid);
                order.setNgayDatHang(LocalDate.now());

                double sum = 0;
                for (CHITIETGIOHANG cd : cartDetails) {
                    sum += cd.getGia() * cd.getSoLuong();
                }
                order.setTongGia(sum);
                order.setTrangThai("Pending");
                order = this.orderRepository.save(order);

                for (CHITIETGIOHANG cartDetail : cartDetails) {
                    CHITIETDONHANG orderDetail = new CHITIETDONHANG();
                    orderDetail.setDonHang(order);
                    orderDetail.setSanPham(cartDetail.getSanPham());
                    orderDetail.setGia(cartDetail.getGia());
                    orderDetail.setSoluong(cartDetail.getSoLuong());

                    this.orderDetailRepository.save(orderDetail);

                    SANPHAM p = cartDetail.getSanPham();
                    p.setSoLuong(p.getSoLuong() - cartDetail.getSoLuong());
                    this.productRepository.save(p); // nhớ lưu lại
                }

                // Xóa giỏ hàng sau khi tạo đơn
                for (CHITIETGIOHANG cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getMaChiTietGioHang());
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

    // public void handleAddProductCart(String email, long prodctId, HttpSession
    // session, long quantity) {
    // NGUOIDUNG user = this.userService.getUserByEmail(email);
    // if (user != null) {
    // // check xem đã có Cart hay chưa
    // GIOHANG cart = this.cartRepository.findByNguoiDung(user);

    // if (cart == null) {
    // // tạo mới Cart
    // GIOHANG otheCart = new GIOHANG();

    // otheCart.setNguoiDung(user);
    // otheCart.setTong(0);

    // cart = this.cartRepository.save(otheCart);
    // }
    // // tìm product
    // Optional<SANPHAM> productOptional =
    // this.productRepository.findById(prodctId);
    // if (productOptional.isPresent()) {
    // SANPHAM otherProduct = productOptional.get();

    // // Check sp đã tưnnfg có trong giỏ hàng chưa
    // CHITIETGIOHANG oldDetail =
    // this.cartDetailRepository.findByGioHangAndSanPham(cart, otherProduct);

    // if (oldDetail == null) {
    // CHITIETGIOHANG cartDetail = new CHITIETGIOHANG();

    // cartDetail.setGioHang(cart);
    // cartDetail.setSanPham(otherProduct);
    // cartDetail.setGia(otherProduct.getGia());
    // cartDetail.setSoLuong(quantity);

    // // save cart detail
    // this.cartDetailRepository.save(cartDetail);

    // // update Cart
    // int sum = cart.getTong() + 1;
    // cart.setTong(sum);
    // this.cartRepository.save(cart);
    // session.setAttribute("sum", sum);
    // } else {
    // oldDetail.setSoLuong(oldDetail.getSoLuong() + quantity);

    // this.cartDetailRepository.save(oldDetail);
    // }

    // }

    // }
    // }

    public int handleAddProductCart(String email, long productId, HttpSession session, long quantity) {
        NGUOIDUNG nguoidung = this.userService.getUserByEmail(email);
        if (nguoidung != null) {
            GIOHANG giohang = this.cartRepository.findByNguoiDung(nguoidung);
            if (giohang == null) {
                giohang = new GIOHANG();
                giohang.setNguoiDung(nguoidung);
                giohang.setTong(0);
                giohang = this.cartRepository.save(giohang);
            }

            Optional<SANPHAM> p = this.productRepository.findByMaSanPham(productId);
            if (p.isPresent()) {
                SANPHAM sanpham = p.get();

                CHITIETGIOHANG od = this.cartDetailRepository.findByGioHangAndSanPham(giohang, sanpham);

                long totalQuantity = quantity;
                if (od != null) {
                    totalQuantity += od.getSoLuong();
                }

                // Kiểm tra kho
                if (sanpham.getSoLuong() < totalQuantity) {
                    return -1; // Không đủ hàng
                }

                if (od == null) {
                    CHITIETGIOHANG cd = new CHITIETGIOHANG();
                    cd.setGioHang(giohang);
                    cd.setSanPham(sanpham);
                    cd.setGia(sanpham.getGia());
                    cd.setSoLuong(quantity);
                    this.cartDetailRepository.save(cd);
                    giohang.setTong(giohang.getTong() + 1);
                    this.cartRepository.save(giohang);
                    session.setAttribute("sum", giohang.getTong());
                } else {
                    od.setSoLuong(od.getSoLuong() + quantity);
                    this.cartDetailRepository.save(od);
                }

                return giohang.getTong(); // Trả về số lượng mới
            }
        }

        return -2; // Lỗi chung
    }

    public void updatePaymentStatus(String paymentRef, String paymentStatus) {
        Optional<DONHANG> orderOptional = this.orderRepository.findByMaThanhToan(paymentRef);
        if (orderOptional.isPresent()) {
            // update
            DONHANG order = orderOptional.get();
            order.setTrangThaiThanhToan(paymentStatus);
            this.orderRepository.save(order);
        }
    }

}
