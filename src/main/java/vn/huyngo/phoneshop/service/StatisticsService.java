package vn.huyngo.phoneshop.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.huyngo.phoneshop.domain.dto.RevenueStatDTO;
import vn.huyngo.phoneshop.repository.OrderRepository;

@Service
public class StatisticsService {
    private final OrderRepository orderRepository;

    public StatisticsService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<RevenueStatDTO> getRevenueByDay() {
        return orderRepository.getRevenueByDay();
    }

    public List<RevenueStatDTO> getRevenueByMonth() {
        return orderRepository.getRevenueByMonth();
    }

    public List<RevenueStatDTO> getRevenueByYear() {
        return orderRepository.getRevenueByYear();
    }

    public List<RevenueStatDTO> getRevenueByDateRange(LocalDate start, LocalDate end) {
        return orderRepository.getRevenueByDateRange(start, end);
    }

    public List<RevenueStatDTO> getRevenueByLatestMonth() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.withDayOfMonth(1); // ngày đầu tháng hiện tại
        LocalDate end = now; // ngày hiện tại

        return orderRepository.getRevenueByDay(start, end);
    }
}
