package vn.huyngo.phoneshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;

@Repository
public interface UserRepository extends JpaRepository<NGUOIDUNG, Long> {
    NGUOIDUNG save(NGUOIDUNG hoidanit);

    List<NGUOIDUNG> findOneByEmail(String email);

    NGUOIDUNG findByMaNguoiDung(long maNguoiDung);

    void deleteById(long id);

    boolean existsByEmail(String email);

    NGUOIDUNG findByEmail(String email);

    Page<NGUOIDUNG> findAll(Pageable pageable);
}
