package vn.huyngo.phoneshop.controller.client;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.huyngo.phoneshop.domain.DANHGIA;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.domain.dto.ChangePasswordDTO;
import vn.huyngo.phoneshop.service.ProductService;
import vn.huyngo.phoneshop.service.ReviewService;
import vn.huyngo.phoneshop.service.UploadService;
import vn.huyngo.phoneshop.service.UserService;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService, ReviewService reviewService, UserService userService,
            UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model, @RequestParam("page") Optional<String> opPage,
            @RequestParam("search") Optional<String> opSearch, HttpServletRequest request,
            Authentication authentication) {
        int page = 1;
        try {
            if (opPage.isPresent()) {
                page = Integer.parseInt(opPage.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        // HttpSession session = request.getSession(false);
        // session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        // String email = authentication.getName();
        // NGUOIDUNG user = this.userService.getUserByEmail(email);

        NGUOIDUNG user = null; // Khai báo biến user trước

        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            }

            String email = authentication.getName();
            user = this.userService.getUserByEmail(email);
        }

        String search = opSearch.isPresent() ? opSearch.get() : "";
        Pageable pageable = PageRequest.of(page - 1, 16);
        Page<SANPHAM> product = this.productService.searchProducts(pageable, search);
        List<SANPHAM> prd = product.getContent();
        List<SANPHAM> applePr = this.productService.getProductsByFactory("Apple");
        List<SANPHAM> oppoPr = this.productService.getProductsByFactory("Oppo");
        List<SANPHAM> samSungPr = this.productService.getProductsByFactory("SamSung");
        List<SANPHAM> redmiPr = this.productService.getProductsByFactory("Redmi");
        List<SANPHAM> huaweiPr = this.productService.getProductsByFactory("Huawei");
        List<DANHGIA> review = this.reviewService.fetchReview();
        review.forEach(DANHGIA::formatDate);
        String query = request.getQueryString();
        if (query != null && !query.isBlank()) {
            // remove pape
            query = query.replace("page=" + page, "");
        }
        model.addAttribute("products", prd);
        model.addAttribute("apples", applePr);
        model.addAttribute("oppos", oppoPr);
        model.addAttribute("samsungs", samSungPr);
        model.addAttribute("redmis", redmiPr);
        model.addAttribute("huaweis", huaweiPr);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", product.getTotalPages());
        model.addAttribute("queryString", query);
        model.addAttribute("reviews", review);
        model.addAttribute("thayDoiMatKhau", new ChangePasswordDTO());
        if (user != null) {
            model.addAttribute("updateUser", user);
        }
        return "client/homepage/show";
    }

    @GetMapping("/refuse")
    public String getRefusePage(Model model) {
        return "client/homepage/refuse";
    }

    @PostMapping("/client/user/update")
    public String updateUser(Model model, @ModelAttribute("updateUser") @Valid NGUOIDUNG update,
            BindingResult updateBindingResult, @RequestParam("hoidanitFile") MultipartFile file,
            HttpServletRequest request,
            Authentication authentication) {

        // if (updateBindingResult.hasErrors()) {
        // return "admin/user/update";
        // }
        HttpSession session = request.getSession(false);
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        String email = authentication.getName();
        NGUOIDUNG user = this.userService.getUserByEmail(email);
        NGUOIDUNG currentUser = this.userService.GetUserDetail(user.getMaNguoiDung());

        if (!file.isEmpty()) {
            String img = this.uploadService.handleSaveUploadFile(file, "avatar");
            currentUser.setAnhDaiDien(img);
        }

        if (currentUser != null) {
            currentUser.setDiaChi(update.getDiaChi());
            currentUser.setHoTen(update.getHoTen());
            currentUser.setSdt(update.getSdt());
            this.userService.handleSaveUser(currentUser);

            session.setAttribute("fullName", currentUser.getHoTen());
            session.setAttribute("avatar", currentUser.getAnhDaiDien());
        }
        return "redirect:/";
    }

    @PostMapping("/change-password")
    public String xuLyDoiMatKhau(@Valid @ModelAttribute("thayDoiMatKhau") ChangePasswordDTO form,
            BindingResult result,
            Model model,
            Principal principal) {

        NGUOIDUNG nguoiDung = userService.getUserByEmail(principal.getName());
        if (nguoiDung == null) {
            model.addAttribute("errorMessage", "Người dùng không tồn tại");
            model.addAttribute("updateUser", nguoiDung);
            model.addAttribute("openChangePasswordModal", true);
            return "client/homepage/show";
        }

        if (!passwordEncoder.matches(form.getMatKhauHienTai(), nguoiDung.getMatKhau())) {
            model.addAttribute("errorMessage", "Mật khẩu hiện tại không đúng");
            model.addAttribute("updateUser", nguoiDung);
            model.addAttribute("openChangePasswordModal", true);
            return "client/homepage/show";
        }

        if (!form.getMatKhauMoi().equals(form.getXacNhanMatKhauMoi())) {
            model.addAttribute("errorMessage", "Xác nhận mật khẩu mới không khớp");
            model.addAttribute("updateUser", nguoiDung);
            model.addAttribute("openChangePasswordModal", true);
            return "client/homepage/show";
        }

        nguoiDung.setMatKhau(passwordEncoder.encode(form.getMatKhauMoi()));
        userService.handleSaveUser(nguoiDung);

        model.addAttribute("successMessage", "Đổi mật khẩu thành công");
        model.addAttribute("thayDoiMatKhau", new ChangePasswordDTO());
        model.addAttribute("updateUser", nguoiDung);
        model.addAttribute("openChangePasswordModal", true); // <<-- Quan trọng
        return "client/homepage/show";
    }

}
