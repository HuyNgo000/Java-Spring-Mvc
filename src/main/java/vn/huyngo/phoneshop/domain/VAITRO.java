package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "VAITRO")
public class VAITRO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maVaiTro;
    private String ten;
    private String moTa;

    @OneToMany(mappedBy = "vaiTro")
    private List<NGUOIDUNG> nguoiDung;

    public long getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(long maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<NGUOIDUNG> getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(List<NGUOIDUNG> nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    @Override
    public String toString() {
        return "VAITRO [maVaiTro=" + maVaiTro + ", ten=" + ten + ", moTa=" + moTa + ", nguoiDung=" + nguoiDung + "]";
    }

}
