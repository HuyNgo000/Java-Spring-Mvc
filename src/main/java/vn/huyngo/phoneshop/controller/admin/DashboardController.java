package vn.huyngo.phoneshop.controller.admin;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.huyngo.phoneshop.domain.dto.RevenueStatDTO;
import vn.huyngo.phoneshop.service.ProductService;
import vn.huyngo.phoneshop.service.StatisticsService;

@Controller
public class DashboardController {
    private final ProductService productService;
    private final StatisticsService statisticsService;

    public DashboardController(ProductService productService, StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {

        List<RevenueStatDTO> byDay = statisticsService.getRevenueByDay();
        List<RevenueStatDTO> byMonth = statisticsService.getRevenueByMonth();
        List<RevenueStatDTO> byYear = statisticsService.getRevenueByYear();

        List<RevenueStatDTO> dailyRevenue = statisticsService.getRevenueByLatestMonth();
        model.addAttribute("dailyRevenue", dailyRevenue);
        model.addAttribute("byDay", byDay);
        model.addAttribute("byMonth", byMonth);
        model.addAttribute("byYear", byYear);
        model.addAttribute("countUser", this.productService.countUser());
        model.addAttribute("countProduct", this.productService.countProduct());
        model.addAttribute("countOrder", this.productService.countOrder());
        model.addAttribute("countReview", this.productService.countReview());
        return "admin/dashboard/admin";
    }

    @GetMapping("/admin/statistics/by-date")
    public String filterRevenueByDate(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Model model) throws JsonProcessingException {

        List<RevenueStatDTO> byDateRange = statisticsService.getRevenueByDateRange(start, end);
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(byDateRange);
        model.addAttribute("filteredRevenue", byDateRange);
        model.addAttribute("filteredRevenueJson", jsonData);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("countUser", this.productService.countUser());
        model.addAttribute("countProduct", this.productService.countProduct());
        model.addAttribute("countOrder", this.productService.countOrder());
        model.addAttribute("countReview", this.productService.countReview());
        return "admin/dashboard/filterStatistics";
    }

}
