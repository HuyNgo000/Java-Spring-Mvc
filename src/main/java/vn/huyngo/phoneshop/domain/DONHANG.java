package vn.huyngo.phoneshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DONHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double tongGia;
    @ManyToOne
    @JoinColumn(name = "id_nguoidung")
    private NGUOIDUNG nguoidung;

    @OneToMany(mappedBy = "donhang")
    private List<CHITIETDONHANG> chitietdonhang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTongGia() {
        return tongGia;
    }

    public void setTongGia(Double tongGia) {
        this.tongGia = tongGia;
    }

    @Override
    public String toString() {
        return "DONHANG [id=" + id + ", tongGia=" + tongGia + "]";
    }

}
