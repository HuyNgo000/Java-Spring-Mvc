package vn.huyngo.phoneshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @GetMapping("/client/product/detail")
    public String getProductDetail(Model model) {
        return "/client/product/detail";
    }
}
