package vn.huyngo.phoneshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETDONHANG")
public class CHITIETDONHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietDonHang;
    private Long soluong;
    private double gia;

    @ManyToOne
    @JoinColumn(name = "maDonHang")
    private DONHANG donHang;

    @ManyToOne
    @JoinColumn(name = "maSanPham")
    private SANPHAM sanPham;

    public Long getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setMaChiTietDonHang(Long maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public Long getSoluong() {
        return soluong;
    }

    public void setSoluong(Long soluong) {
        this.soluong = soluong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public DONHANG getDonHang() {
        return donHang;
    }

    public void setDonHang(DONHANG donHang) {
        this.donHang = donHang;
    }

    public SANPHAM getSanPham() {
        return sanPham;
    }

    public void setSanPham(SANPHAM sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public String toString() {
        return "CHITIETDONHANG [maChiTietDonHang=" + maChiTietDonHang + ", soluong=" + soluong + ", gia=" + gia
                + ", donHang=" + donHang + ", sanPham=" + sanPham + "]";
    }

}
