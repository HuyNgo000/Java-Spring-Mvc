package vn.huyngo.phoneshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "admin/user/show";
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
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable Long id) {
        NGUOIDUNG currentUser = this.userService.GetUserDetail(id);
        model.addAttribute("updateUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(Model model, @ModelAttribute("updateUser") NGUOIDUNG hoidanit) {
        NGUOIDUNG currentUser = this.userService.GetUserDetail(hoidanit.getId());
        if (currentUser != null) {
            currentUser.setDiaChi(hoidanit.getDiaChi());
            currentUser.setHoTen(hoidanit.getHoTen());
            currentUser.setSdt(hoidanit.getSdt());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("deleteUser") NGUOIDUNG create) {
        this.userService.handleSaveUser(create);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getUserDeletePage(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        model.addAttribute("deleteUser", new NGUOIDUNG());
        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(Model model, @ModelAttribute("deleteUser") NGUOIDUNG delete) {
        this.userService.DeleteUser(delete.getId());
        return "redirect:/admin/user";
    }
}
