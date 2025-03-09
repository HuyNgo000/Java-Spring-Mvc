package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DONHANG")
public class DONHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDonHang;
    private Double tongGia;
    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NGUOIDUNG nguoiDung;

    @OneToMany(mappedBy = "donHang")
    private List<CHITIETDONHANG> chiTietDonHang;

    public Long getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Long maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Double getTongGia() {
        return tongGia;
    }

    public void setTongGia(Double tongGia) {
        this.tongGia = tongGia;
    }

    public NGUOIDUNG getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NGUOIDUNG nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public List<CHITIETDONHANG> getChiTietDonHang() {
        return chiTietDonHang;
    }

    public void setChiTietDonHang(List<CHITIETDONHANG> chiTietDonHang) {
        this.chiTietDonHang = chiTietDonHang;
    }

    @Override
    public String toString() {
        return "DONHANG [maDonHang=" + maDonHang + ", tongGia=" + tongGia + ", nguoiDung=" + nguoiDung
                + ", chiTietDonHang=" + chiTietDonHang + "]";
    }

}
