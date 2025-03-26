package vn.huyngo.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.CHITIETGIOHANG;
import vn.huyngo.phoneshop.domain.GIOHANG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CHITIETGIOHANG, Long> {

    CHITIETGIOHANG findByGioHangAndSanPham(GIOHANG gioHang, SANPHAM sanPham);

    CHITIETGIOHANG findByMaChiTietGioHANG(Long maChiTietGioHANG);

    CHITIETGIOHANG findByGioHang(GIOHANG gioHang);

    void deleteById(long id);
}
