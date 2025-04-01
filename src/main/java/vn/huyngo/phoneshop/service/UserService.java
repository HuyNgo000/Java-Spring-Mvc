package vn.huyngo.phoneshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.VAITRO;
import vn.huyngo.phoneshop.domain.dto.RegisterDTO;
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

    public void saveUser(NGUOIDUNG user) {
        this.userRepository.save(user);
    }

    public Page<NGUOIDUNG> GetAllUser(Pageable page) {
        return this.userRepository.findAll(page);
    }

    public List<NGUOIDUNG> GetAllUserByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public NGUOIDUNG handleSaveUser(NGUOIDUNG nguoiDung) {
        return this.userRepository.save(nguoiDung);
    }

    public NGUOIDUNG GetUserDetail(long id) {
        return this.userRepository.findByMaNguoiDung(id);
    }

    public void DeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public VAITRO getRoleByName(String name) {
        return this.roleRepository.findByTen(name);
    }

    public NGUOIDUNG registerDTOtoUser(RegisterDTO registerDTO) {
        NGUOIDUNG nguoidung = new NGUOIDUNG();
        nguoidung.setHoTen(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        nguoidung.setEmail(registerDTO.getEmail());
        nguoidung.setMatKhau(registerDTO.getPassword());
        return nguoidung;
    }

    public boolean checkEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public NGUOIDUNG getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
