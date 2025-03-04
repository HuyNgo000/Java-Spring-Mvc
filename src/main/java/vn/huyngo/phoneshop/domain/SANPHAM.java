package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SANPHAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private Double gia;
    private String hinhAnh;
    private String moTaChiTiet;
    private String moTaNgan;
    private Long soLuong;
    private Long daBan;
    private String noiSanXuat;
    private String mucTieu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTaChiTiet() {
        return moTaChiTiet;
    }

    public void setMoTaChiTiet(String moTaChiTiet) {
        this.moTaChiTiet = moTaChiTiet;
    }

    public String getMoTaNgan() {
        return moTaNgan;
    }

    public void setMoTaNgan(String moTaNgan) {
        this.moTaNgan = moTaNgan;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public Long getDaBan() {
        return daBan;
    }

    public void setDaBan(Long daBan) {
        this.daBan = daBan;
    }

    public String getNoiSanXuat() {
        return noiSanXuat;
    }

    public void setNoiSanXuat(String noiSanXuat) {
        this.noiSanXuat = noiSanXuat;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    @Override
    public String toString() {
        return "SANPHAM [id=" + id + ", ten=" + ten + ", gia=" + gia + ", hinhAnh=" + hinhAnh + ", moTaChiTiet="
                + moTaChiTiet + ", moTaNgan=" + moTaNgan + ", soLuong=" + soLuong + ", daBan=" + daBan + ", noiSanXuat="
                + noiSanXuat + ", mucTieu=" + mucTieu + "]";
    }

}
