package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class GIOHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maGioHang;
    private int tong;
    @OneToOne
    @JoinColumn(name = "maNguoiDung")
    private NGUOIDUNG nguoiDung;
    @OneToMany(mappedBy = "gioHang")
    private List<CHITIETGIOHANG> chiTietGioHang;

    public Long getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(Long maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }

    public NGUOIDUNG getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NGUOIDUNG nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public List<CHITIETGIOHANG> getChiTietGioHang() {
        return chiTietGioHang;
    }

    public void setChiTietGioHang(List<CHITIETGIOHANG> chiTietGioHang) {
        this.chiTietGioHang = chiTietGioHang;
    }

    @Override
    public String toString() {
        return "GIOHANG [maGioHang=" + maGioHang + ", tong=" + tong + ", nguoiDung=" + nguoiDung + ", chiTietGioHang="
                + chiTietGioHang + "]";
    }

}
