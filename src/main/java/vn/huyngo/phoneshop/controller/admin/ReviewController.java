package vn.huyngo.phoneshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.huyngo.phoneshop.domain.DANHGIA;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.service.ReviewService;

@Controller
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/admin/review")
    public String getReviewPage(Model model, @RequestParam("page") Optional<String> opPage) {
        int page = 1;
        try {
            if (opPage.isPresent()) {
                page = Integer.parseInt(opPage.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<DANHGIA> review = this.reviewService.getAllReview(pageable);
        List<DANHGIA> reviews = review.getContent();

        model.addAttribute("reviews", reviews);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", review.getTotalPages());
        return "admin/review/show";
    }

    @PostMapping("/admin/review/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/admin/review";
    }
}
