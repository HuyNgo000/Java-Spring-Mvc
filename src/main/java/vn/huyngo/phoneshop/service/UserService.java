package vn.huyngo.phoneshop.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.VAITRO;
import vn.huyngo.phoneshop.repository.RoleRepository;
import vn.huyngo.phoneshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "hello service";
    }

    public List<NGUOIDUNG> GetAllUser() {
        return this.userRepository.findAll();
    }

    public List<NGUOIDUNG> GetAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public NGUOIDUNG handleSaveUser(NGUOIDUNG nguoidung) {
        return this.userRepository.save(nguoidung);
    }

    public NGUOIDUNG GetUserDetail(long id) {
        return this.userRepository.findById(id);
    }

    public void DeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public VAITRO getRoleByName(String name) {
        return this.roleRepository.findByTen(name);
    }
}
