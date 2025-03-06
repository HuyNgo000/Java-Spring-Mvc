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
public class NGUOIDUNG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String matKhau;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String anhDaiDien;

    @ManyToOne
    @JoinColumn(name = "id_vaitro")
    private VAITRO vaitro;

    public VAITRO getVaitro() {
        return vaitro;
    }

    public void setVaitro(VAITRO vaitro) {
        this.vaitro = vaitro;
    }

    public List<DONHANG> getDonhang() {
        return donhang;
    }

    public void setDonhang(List<DONHANG> donhang) {
        this.donhang = donhang;
    }

    @OneToMany(mappedBy = "nguoidung")
    private List<DONHANG> donhang;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setAnhDaiDien(String anhdaidien) {
        this.anhDaiDien = anhdaidien;
    }

    @Override
    public String toString() {
        return "NGUOIDUNG [id=" + id + ", email=" + email + ", matKhau=" + matKhau + ", hoTen=" + hoTen + ", diaChi="
                + diaChi + ", sdt=" + sdt + ", anhDaiDien=" + anhDaiDien + "]";
    }

}
