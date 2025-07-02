package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "NGUOIDUNG")
public class NGUOIDUNG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maNguoiDung;

    @NotNull
    @Email(message = "Email không hợp lệ", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotNull
    @Size(min = 2, message = "Mật khẩu phải có ít nhất 2 kí tự")
    private String matKhau;
    @NotNull
    @Size(min = 3, message = "Họ Tên phải có ít nhất 3 kí tự")
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String anhDaiDien;

    @ManyToOne
    @JoinColumn(name = "maVaiTro")
    private VAITRO vaiTro;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DONHANG> donHang;

    @OneToOne(mappedBy = "nguoiDung")
    private GIOHANG gioHang;

    @OneToMany(mappedBy = "nguoiDung")
    private List<DANHGIA> danhGia;

    private String nhaCungCap;

    @PrePersist
    public void prePersist() {
        if (this.nhaCungCap == null) {
            this.nhaCungCap = "LOCAL";
        }
    }

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

    public GIOHANG getGioHang() {
        return gioHang;
    }

    public void setGioHang(GIOHANG gioHang) {
        this.gioHang = gioHang;
    }

    public List<DANHGIA> getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(List<DANHGIA> danhGia) {
        this.danhGia = danhGia;
    }

    @Override
    public String toString() {
        return "NGUOIDUNG [maNguoiDung=" + maNguoiDung + ", email=" + email + ", matKhau=" + matKhau + ", hoTen="
                + hoTen + ", diaChi=" + diaChi + ", sdt=" + sdt + ", anhDaiDien=" + anhDaiDien + ", vaiTro=" + vaiTro
                + ", donHang=" + donHang + ", gioHang=" + gioHang + ", danhGia=" + danhGia + "]";
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

}
