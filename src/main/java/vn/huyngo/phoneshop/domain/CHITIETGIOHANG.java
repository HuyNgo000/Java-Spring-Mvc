package vn.huyngo.phoneshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CHITIETGIOHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietGioHang;
    private long soLuong;
    private double gia;

    @ManyToOne
    @JoinColumn(name = "maGioHang")
    private GIOHANG gioHang;

    @ManyToOne
    @JoinColumn(name = "maSanPham")
    private SANPHAM sanPham;

    public Long getMaChiTietGioHang() {
        return maChiTietGioHang;
    }

    public void setMaChiTietGioHang(Long maChiTietGioHang) {
        this.maChiTietGioHang = maChiTietGioHang;
    }

    public long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(long soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public GIOHANG getGioHang() {
        return gioHang;
    }

    public void setGioHang(GIOHANG gioHang) {
        this.gioHang = gioHang;
    }

    public SANPHAM getSanPham() {
        return sanPham;
    }

    public void setSanPham(SANPHAM sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public String toString() {
        return "CHITIETGIOHANG [maChiTietGioHang=" + maChiTietGioHang + ", soLuong=" + soLuong + ", gia=" + gia
                + ", gioHang=" + gioHang + ", sanPham=" + sanPham + "]";
    }

}
