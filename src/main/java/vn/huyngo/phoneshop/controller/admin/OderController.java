package vn.huyngo.phoneshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OderController {
    @GetMapping("/admin/order")
    public String getDashboard() {
        return "/admin/user/order/order";
    }
}
