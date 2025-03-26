package vn.huyngo.phoneshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.service.ProductService;
import vn.huyngo.phoneshop.service.UploadService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/admin/product")
    public String getProductTable(Model model, @RequestParam("page") Optional<String> opPage) {
        int page = 1;
        try {
            if (opPage.isPresent()) {
                page = Integer.parseInt(opPage.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<SANPHAM> product = this.productService.fetchProducts(pageable);
        List<SANPHAM> pr = product.getContent();
        // List<SANPHAM> pr = this.productService.fetchPrs();
        model.addAttribute("products", pr);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", product.getTotalPages());
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getProductCreatePage(Model model) {
        model.addAttribute("newProduct", new SANPHAM());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid SANPHAM create,
            BindingResult BindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = BindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>" + error.getField() + "-" + error.getDefaultMessage());
        }

        if (BindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "product");
        create.setHinhAnh(avatar);
        this.productService.handleSaveProduct(create);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getProductUpdatePage(Model model, @PathVariable Long id) {
        Optional<SANPHAM> currentProduct = this.productService.getDetailProduct(id);
        model.addAttribute("updateProduct", currentProduct.get());
        return "admin/product/update";
    }

    @RequestMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable Long id) {
        SANPHAM product = this.productService.getDetailProduct(id).get();
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(Model model, @ModelAttribute("updateProduct") @Valid SANPHAM update,
            BindingResult updateBindingResult, @RequestParam("hoidanitFile") MultipartFile file) {

        if (updateBindingResult.hasErrors()) {
            return "admin/product/update";
        }

        Optional<SANPHAM> optionalProduct = this.productService.getDetailProduct(update.getMaSanPham());

        if (optionalProduct.isPresent()) {
            SANPHAM currentProduct = optionalProduct.get();

            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setHinhAnh(img);
            }

            currentProduct.setTen(update.getTen());
            currentProduct.setGia(update.getGia());
            currentProduct.setMoTaChiTiet(update.getMoTaChiTiet());
            currentProduct.setMoTaNgan(update.getMoTaNgan());
            currentProduct.setSoLuong(update.getSoLuong());
            currentProduct.setNoiSanXuat(update.getNoiSanXuat());
            currentProduct.setMucTieu(update.getMucTieu());
            this.productService.handleSaveProduct(currentProduct);

        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + update.getMaSanPham());
            model.addAttribute("errorMessage", "Sản phẩm không tồn tại!");
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getProductDeletePage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        model.addAttribute("deleteProduct", new SANPHAM());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String deleteProduct(Model model, @ModelAttribute("deleteProduct") SANPHAM delete) {
        this.productService.deleteProduct(delete.getMaSanPham());
        return "redirect:/admin/product";
    }
}