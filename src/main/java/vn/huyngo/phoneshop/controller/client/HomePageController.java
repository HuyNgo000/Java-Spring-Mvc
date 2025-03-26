package vn.huyngo.phoneshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.service.ProductService;

@Controller
public class HomePageController {
    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<SANPHAM> sanpham = this.productService.fetchPrs();
        model.addAttribute("products", sanpham);
        return "client/homepage/show";
    }

    @GetMapping("/refuse")
    public String getRefusePage(Model model) {
        return "client/homepage/refuse";
    }

}
