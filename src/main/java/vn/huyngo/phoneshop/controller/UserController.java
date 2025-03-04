package vn.huyngo.phoneshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<NGUOIDUNG> arruser = this.userService.GetAllUser();
        System.out.println(arruser);
        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String geTabletUserPage(Model model) {
        List<NGUOIDUNG> users = this.userService.GetAllUser();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new NGUOIDUNG());
        return "admin/user/create";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable Long id) {
        NGUOIDUNG user = this.userService.GetUserDetail(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/user-detail";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") NGUOIDUNG hoidanit) {
        System.out.println("run here" + hoidanit);
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }
}
