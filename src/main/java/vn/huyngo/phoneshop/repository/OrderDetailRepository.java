package vn.huyngo.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.CHITIETDONHANG;
import vn.huyngo.phoneshop.domain.DONHANG;

@Repository
public interface OrderDetailRepository extends JpaRepository<CHITIETDONHANG, Long> {
    CHITIETDONHANG findByDonHang(DONHANG donHang);
}
