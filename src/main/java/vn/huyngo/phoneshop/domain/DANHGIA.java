package vn.huyngo.phoneshop.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class DANHGIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDanhGia;

    @ManyToOne
    @JoinColumn(name = "maSanPham")
    private SANPHAM sanPham;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung")
    private NGUOIDUNG nguoiDung;

    private int sao; // Điểm đánh giá (1-5)

    @Column(columnDefinition = "MEDIUMTEXT")
    private String binhLuan; // Nội dung đánh giá

    private LocalDateTime ngayTao = LocalDateTime.now();

    @Transient // Không lưu vào DB
    private String formatted;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public void formatDate() {
        if (this.ngayTao != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            this.formatted = this.ngayTao.format(formatter);
        }
    }

    public Long getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(Long maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public SANPHAM getSanPham() {
        return sanPham;
    }

    public void setSanPham(SANPHAM sanPham) {
        this.sanPham = sanPham;
    }

    public NGUOIDUNG getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NGUOIDUNG nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public int getSao() {
        return sao;
    }

    public void setSao(int sao) {
        this.sao = sao;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "DANHGIA [maDanhGia=" + maDanhGia + ", sanPham=" + sanPham + ", nguoiDung=" + nguoiDung + ", sao=" + sao
                + ", binhLuan=" + binhLuan + ", ngayTao=" + ngayTao + "]";
    }

}
