package vn.huyngo.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.GIOHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;

@Repository
public interface CartRepository extends JpaRepository<GIOHANG, Long> {
    GIOHANG findByNguoiDung(NGUOIDUNG nguoiDung);

    void deleteById(long id);
}
