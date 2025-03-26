package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "SANPHAM")
public class SANPHAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSanPham;
    @NotNull
    @Size(min = 3, message = "Tên sản phẩm phải có ít nhất 3 kí tự")
    private String ten;
    @NotNull(message = "Không được để trống")
    private Double gia;
    private String hinhAnh;
    @NotNull
    @NotEmpty(message = "Không được để trống")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String moTaChiTiet;
    @NotNull
    @NotEmpty(message = "Không được để trống")
    private String moTaNgan;
    @NotNull(message = "Không được để trống")
    private Long soLuong;
    private Long daBan;
    private String noiSanXuat;
    private String mucTieu;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private List<DANHGIA> danhGia;

    public Long getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Long maSanPham) {
        this.maSanPham = maSanPham;
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

    public List<DANHGIA> getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(List<DANHGIA> danhGia) {
        this.danhGia = danhGia;
    }

    @Override
    public String toString() {
        return "SANPHAM [maSanPham=" + maSanPham + ", ten=" + ten + ", gia=" + gia + ", hinhAnh=" + hinhAnh
                + ", moTaChiTiet=" + moTaChiTiet + ", moTaNgan=" + moTaNgan + ", soLuong=" + soLuong + ", daBan="
                + daBan + ", noiSanXuat=" + noiSanXuat + ", mucTieu=" + mucTieu + ", danhGia=" + danhGia + "]";
    }

}
