package vn.huyngo.phoneshop.controller.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.huyngo.phoneshop.domain.CHITIETDONHANG;
import vn.huyngo.phoneshop.domain.CHITIETGIOHANG;
import vn.huyngo.phoneshop.domain.DANHGIA;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.GIOHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.domain.SANPHAM_;
import vn.huyngo.phoneshop.domain.dto.ProductCriteriaDTO;
import vn.huyngo.phoneshop.service.OrderService;
import vn.huyngo.phoneshop.service.ProductService;
import vn.huyngo.phoneshop.service.ReviewService;
import vn.huyngo.phoneshop.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final OrderService orderService;

    public ItemController(ProductService productService, ReviewService reviewService, UserService userService,
            OrderService orderService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/product/{id}")
    public String getProductDetail(Model model, @PathVariable Long id, HttpServletRequest request,
            Authentication authentication) {
        SANPHAM product = this.productService.getDetailProduct(id).get();
        List<DANHGIA> danhGia = this.reviewService.getReviewByProduct(product);

        danhGia.forEach(DANHGIA::formatDate);
        boolean isAuthenticated = (authentication != null && authentication.isAuthenticated());
        model.addAttribute("product", product);
        model.addAttribute("reviews", danhGia);
        model.addAttribute("review", new DANHGIA());
        model.addAttribute("id", id);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "client/product/detail";
    }

    @GetMapping("/products")
    public String getProductPage(Model model, ProductCriteriaDTO productCriteriaDTO, HttpServletRequest request) {
        int page = 1;
        try {
            if (productCriteriaDTO.getPage().isPresent()) {
                page = Integer.parseInt(productCriteriaDTO.getPage().get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 6);

        if (productCriteriaDTO.getSort() != null && productCriteriaDTO.getSort().isPresent()) {
            String sort = productCriteriaDTO.getSort().get();
            if (sort.equals("gia-tang-dan")) {
                pageable = PageRequest.of(page - 1, 6, Sort.by(SANPHAM_.GIA).ascending());
            } else if (sort.equals("gia-giam-dan")) {
                pageable = PageRequest.of(page - 1, 6, Sort.by(SANPHAM_.GIA).descending());
            }
        }

        Page<SANPHAM> product = this.productService.fetchProductsWithSpec(pageable, productCriteriaDTO);
        List<SANPHAM> products = product.getContent();

        String query = request.getQueryString();
        if (query != null && !query.isBlank()) {
            // remove pape
            query = query.replace("page=" + page, "");
        }

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", product.getTotalPages());
        model.addAttribute("queryString", query);
        return "client/product/show";
    }

    @PostMapping("/product/{id}/review")
    public String submitReview(@PathVariable Long id, @ModelAttribute("review") DANHGIA danhgia,
            HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession(false);
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        String email = authentication.getName();
        NGUOIDUNG nguoidung = this.userService.getUserByEmail(email);
        SANPHAM sanpham = this.productService.getDetailProduct(id).orElse(null);
        if (sanpham != null) {
            danhgia.setSanPham(sanpham);
            danhgia.setNguoiDung(nguoidung);
            reviewService.handleSaveReview(danhgia);
        }
        return "redirect:/product/" + id;
    }

    @GetMapping("/thanks")
    public String getThanks(Model model) {
        return "client/cart/thanks";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.addPrToCart(email, productId, session, 1);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpServletRequest request) {
        NGUOIDUNG user = new NGUOIDUNG();
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("id");
        user.setMaNguoiDung(id);
        GIOHANG cart = this.productService.getCarByUser(user);
        List<CHITIETGIOHANG> cd = cart == null ? new ArrayList<CHITIETGIOHANG>() : cart.getChiTietGioHang();
        double totalPrice = 0;
        for (CHITIETGIOHANG cartDetail : cd) {
            totalPrice += cartDetail.getGia() * cartDetail.getSoLuong();
        }
        model.addAttribute("cartDetails", cd);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model, HttpServletRequest request) {
        NGUOIDUNG user = new NGUOIDUNG();
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("id");
        user.setMaNguoiDung(id);
        GIOHANG cart = this.productService.getCarByUser(user);
        List<CHITIETGIOHANG> cd = cart == null ? new ArrayList<CHITIETGIOHANG>() : cart.getChiTietGioHang();
        double totalPrice = 0;
        for (CHITIETGIOHANG cartDetail : cd) {
            totalPrice += cartDetail.getGia() * cartDetail.getSoLuong();
        }
        model.addAttribute("cartDetails", cd);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/checkout";
    }

    @PostMapping("/cart/{id}")
    public String deleteCartDetail(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long cartDetailId = id;
        this.productService.removeCartDetail(cartDetailId, session);
        return "redirect:/cart";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckoutPage(@ModelAttribute("cart") GIOHANG cart, Model model) {
        List<CHITIETGIOHANG> cd = cart == null ? new ArrayList<CHITIETGIOHANG>() : cart.getChiTietGioHang();
        this.productService.updateCartBeforeCheckout(cd);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String tenNguoiNhan,
            @RequestParam("receiverAddress") String diaChiNguoiNhan,
            @RequestParam("receiverPhone") String sdtNguoiNhan) {

        NGUOIDUNG currentUser = new NGUOIDUNG();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setMaNguoiDung(id);
        this.productService.placeOrder(currentUser, session, tenNguoiNhan, diaChiNguoiNhan, sdtNguoiNhan);

        return "redirect:/thanks";
    }

    @PostMapping("/add-to-cart")
    public String addProductFromViewDetail(
            @RequestParam("id") long id,
            @RequestParam("quantity") String quantityStr,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");

        long quantity = 0;
        try {
            quantity = Long.parseLong(quantityStr);
        } catch (NumberFormatException e) {
            quantity = 1; // Giá trị mặc định
        }

        this.productService.handleAddProductCart(email, id, session, quantity);

        return "redirect:/product/" + id;
    }

    @GetMapping("/historyBuy")
    public String getMethodName(Model model, HttpServletRequest request) {
        NGUOIDUNG user = new NGUOIDUNG();
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute("id");
        user.setMaNguoiDung(id);

        List<DONHANG> order = this.orderService.getOrderByUser(user);
        model.addAttribute("orders", order);
        return "client/cart/history";
    }

}
