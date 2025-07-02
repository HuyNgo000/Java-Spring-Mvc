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
    @Min(value = 0, message = "Số lượng phải >= 0")
    @NotNull(message = "Không được để trống")
    private Long soLuong;
    private String boNho;
    private String noiSanXuat;
    private String manHinh;

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

    public String getBoNho() {
        return boNho;
    }

    public void setBoNho(String boNho) {
        this.boNho = boNho;
    }

    public String getNoiSanXuat() {
        return noiSanXuat;
    }

    public void setNoiSanXuat(String noiSanXuat) {
        this.noiSanXuat = noiSanXuat;
    }

    public String getmanHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
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
                + ", moTaChiTiet=" + moTaChiTiet + ", moTaNgan=" + moTaNgan + ", soLuong=" + soLuong + ", boNho="
                + boNho + ", noiSanXuat=" + noiSanXuat + ", manHinh=" + manHinh + ", danhGia=" + danhGia + "]";
    }

}
