package vn.huyngo.phoneshop.controller.client;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.dto.RegisterDTO;
import vn.huyngo.phoneshop.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/client/auth/register")
    public String registerUser(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        NGUOIDUNG nguoidung = this.userService.registerDTOtoUser(registerDTO);
        String hasdPassword = this.passwordEncoder.encode(nguoidung.getMatKhau());
        nguoidung.setMatKhau(hasdPassword);
        nguoidung.setVaiTro(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(nguoidung);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginrPage(Model model) {
        return "client/auth/login";
    }

}
