package vn.huyngo.phoneshop.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "DONHANG")
public class DONHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDonHang;
    private Double tongGia;
    private String tenNguoiNhan;
    private String diaChiNguoiNhan;
    private String sdtNguoiNhan;
    private String trangThai;
    private String maThanhToan;
    private String trangThaiThanhToan;
    private String hinhThucThanhToan;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date ngayDatHang;
    private LocalDate ngayDatHang;

    public LocalDate getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(LocalDate ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NGUOIDUNG nguoiDung;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CHITIETDONHANG> chiTietDonHang;

    public String getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(String maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public Long getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Long maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Double getTongGia() {
        return tongGia;
    }

    public void setTongGia(Double totalPrice) {
        this.tongGia = totalPrice;
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

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChiNguoiNhan() {
        return diaChiNguoiNhan;
    }

    public void setDiaChiNguoiNhan(String diaChiNguoiNhan) {
        this.diaChiNguoiNhan = diaChiNguoiNhan;
    }

    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "DONHANG [maDonHang=" + maDonHang + ", tongGia=" + tongGia + ", tenNguoiNhan=" + tenNguoiNhan
                + ", diaChiNguoiNhan=" + diaChiNguoiNhan + ", sdtNguoiNhan=" + sdtNguoiNhan + ", trangThai=" + trangThai
                + ", nguoiDung=" + nguoiDung + ", chiTietDonHang=" + chiTietDonHang + "]";
    }

}
