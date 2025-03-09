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
@Table(name = "NGUOIDUNG")
public class NGUOIDUNG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maNguoiDung;
    private String email;
    private String matKhau;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String anhDaiDien;

    @ManyToOne
    @JoinColumn(name = "maVaiTro")
    private VAITRO vaiTro;

    @OneToMany(mappedBy = "nguoiDung")
    private List<DONHANG> donHang;

    public long getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(long maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public VAITRO getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VAITRO vaiTro) {
        this.vaiTro = vaiTro;
    }

    public List<DONHANG> getDonHang() {
        return donHang;
    }

    public void setDonHang(List<DONHANG> donHang) {
        this.donHang = donHang;
    }

    @Override
    public String toString() {
        return "NGUOIDUNG [maNguoiDung=" + maNguoiDung + ", email=" + email + ", matKhau=" + matKhau + ", hoTen="
                + hoTen + ", diaChi=" + diaChi + ", sdt=" + sdt + ", anhDaiDien=" + anhDaiDien + ", vaiTro=" + vaiTro
                + ", donHang=" + donHang + "]";
    }

}
