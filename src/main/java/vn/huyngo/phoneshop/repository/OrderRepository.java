package vn.huyngo.phoneshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;

@Repository
public interface OrderRepository extends JpaRepository<DONHANG, Long> {
    List<DONHANG> findByNguoiDung(NGUOIDUNG nguoiDung);

    Page<DONHANG> findAll(Pageable Pageable);

}
