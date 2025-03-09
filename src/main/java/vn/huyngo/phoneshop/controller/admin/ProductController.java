package vn.huyngo.phoneshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.service.ProductService;
import vn.huyngo.phoneshop.service.UploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/admin/product")
    public String getProductTable(Model model) {
        List<SANPHAM> pr = this.productService.fetchProducts();
        model.addAttribute("products", pr);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getProductCreatePage(Model model) {
        model.addAttribute("newProduct", new SANPHAM());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") SANPHAM create,
            @RequestParam("hoidanitFile") MultipartFile file) {
        String avatar = this.uploadService.handleSaveUploadFile(file, "product");
        create.setHinhAnh(avatar);
        this.productService.handleSaveProduct(create);
        return "redirect:/admin/product";
    }
}
