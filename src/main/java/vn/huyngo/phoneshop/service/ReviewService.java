package vn.huyngo.phoneshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.huyngo.phoneshop.controller.admin.DashboardController;

import java.util.List;
import java.util.Optional;

import vn.huyngo.phoneshop.domain.DANHGIA;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.repository.ReviewRepository;

@Service
public class ReviewService {

    private final DashboardController dashboardController;

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository, DashboardController dashboardController) {
        this.reviewRepository = reviewRepository;
        this.dashboardController = dashboardController;
    }

    public List<DANHGIA> getReviewByProduct(SANPHAM sanpham) {
        return this.reviewRepository.findBySanPham(sanpham);
    }

    public List<DANHGIA> getReviewByUser(NGUOIDUNG nguoidung) {
        return this.reviewRepository.findByNguoiDung(nguoidung);
    }

    public DANHGIA handleSaveReview(DANHGIA danhGia) {
        return this.reviewRepository.save(danhGia);
    }

    public Page<DANHGIA> getAllReview(Pageable page) {
        return this.reviewRepository.findAll(page);
    }

    public List<DANHGIA> fetchReview() {
        return this.reviewRepository.findAll();
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
