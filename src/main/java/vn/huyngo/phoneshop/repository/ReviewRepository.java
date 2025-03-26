package vn.huyngo.phoneshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.DANHGIA;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;

@Repository
public interface ReviewRepository extends JpaRepository<DANHGIA, Long> {
    List<DANHGIA> findBySanPham(SANPHAM sanPham);

    List<DANHGIA> findByNguoiDung(NGUOIDUNG nguoiDung);

    void deleteById(long id);

    List<DANHGIA> findAll();

    Page<DANHGIA> findAll(Pageable Pageable);

}
