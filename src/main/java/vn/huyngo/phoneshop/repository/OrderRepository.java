package vn.huyngo.phoneshop.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.dto.RevenueStatDTO;

@Repository
public interface OrderRepository extends JpaRepository<DONHANG, Long> {
        List<DONHANG> findByNguoiDung(NGUOIDUNG nguoiDung);

        Page<DONHANG> findAll(Pageable Pageable);

        Optional<DONHANG> findByMaThanhToan(String maThanhToan);

        boolean existsByNguoiDungAndChiTietDonHang_SanPham_maSanPhamAndTrangThai(NGUOIDUNG nguoiDung, Long maSanPham,
                        String trangThai);

        // Doanh thu theo ngày
        @Query("SELECT new vn.huyngo.phoneshop.domain.dto.RevenueStatDTO(" +
                        "FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d'), " +
                        "SUM(ct.soluong * ct.gia)) " +
                        "FROM CHITIETDONHANG ct JOIN ct.donHang d " +
                        "GROUP BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d') " +
                        "ORDER BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d')")
        List<RevenueStatDTO> getRevenueByDay();

        // Doanh thu theo tháng
        @Query("SELECT new vn.huyngo.phoneshop.domain.dto.RevenueStatDTO(" +
                        "FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m'), " +
                        "SUM(ct.soluong * ct.gia)) " +
                        "FROM CHITIETDONHANG ct JOIN ct.donHang d " +
                        "GROUP BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m') " +
                        "ORDER BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m')")
        List<RevenueStatDTO> getRevenueByMonth();

        // Doanh thu theo năm
        @Query("SELECT new vn.huyngo.phoneshop.domain.dto.RevenueStatDTO(" +
                        "FUNCTION('YEAR', d.ngayDatHang), " +
                        "SUM(ct.soluong * ct.gia)) " +
                        "FROM CHITIETDONHANG ct JOIN ct.donHang d " +
                        "GROUP BY FUNCTION('YEAR', d.ngayDatHang) " +
                        "ORDER BY FUNCTION('YEAR', d.ngayDatHang)")
        List<RevenueStatDTO> getRevenueByYear();

        @Query("SELECT new vn.huyngo.phoneshop.domain.dto.RevenueStatDTO(" +
                        "FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d'), " +
                        "SUM(ct.soluong * ct.gia)) " +
                        "FROM CHITIETDONHANG ct JOIN ct.donHang d " +
                        "WHERE d.ngayDatHang BETWEEN :start AND :end " +
                        "GROUP BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d') " +
                        "ORDER BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d')")
        List<RevenueStatDTO> getRevenueByDateRange(@Param("start") LocalDate start,
                        @Param("end") LocalDate end);

        @Query("""
                            SELECT new vn.huyngo.phoneshop.domain.dto.RevenueStatDTO(
                                FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d'),
                                SUM(ct.soluong * ct.gia)
                            )
                            FROM CHITIETDONHANG ct JOIN ct.donHang d
                            WHERE d.ngayDatHang BETWEEN :startDate AND :endDate
                            GROUP BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d')
                            ORDER BY FUNCTION('DATE_FORMAT', d.ngayDatHang, '%Y-%m-%d')
                        """)
        List<RevenueStatDTO> getRevenueByDay(
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

}
