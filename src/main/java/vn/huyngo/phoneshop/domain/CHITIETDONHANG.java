package vn.huyngo.phoneshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CHITIETDONHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long soluong;
    private double gia;

    @ManyToOne
    @JoinColumn(name = "id_donhang")
    private DONHANG donhang;

    @ManyToOne
    @JoinColumn(name = "id_sanpham")
    private SANPHAM sanpham;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoluong() {
        return soluong;
    }

    public void setSoluong(Long soluong) {
        this.soluong = soluong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public DONHANG getDonhang() {
        return donhang;
    }

    public void setDonhang(DONHANG donhang) {
        this.donhang = donhang;
    }

    public SANPHAM getSanpham() {
        return sanpham;
    }

    public void setSanpham(SANPHAM sanpham) {
        this.sanpham = sanpham;
    }

    @Override
    public String toString() {
        return "CHITIETDONHANG [id=" + id + ", soluong=" + soluong + ", gia=" + gia + ", donhang=" + donhang
                + ", sanpham=" + sanpham + "]";
    }

}
