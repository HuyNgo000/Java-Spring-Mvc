package vn.huyngo.phoneshop.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.huyngo.phoneshop.domain.NGUOIDUNG;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NGUOIDUNG nguoidung = this.userService.getUserByEmail(username);
        if (nguoidung == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new User(
                nguoidung.getEmail(),
                nguoidung.getMatKhau(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + nguoidung.getVaiTro().getTen())));
    }

}
