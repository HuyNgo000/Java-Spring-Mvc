package vn.huyngo.phoneshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.DONHANG_;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OderController {
    private final OrderService orderService;

    public OderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getTableOrder(Model model, @RequestParam("page") Optional<String> opPage) {
        int page = 1;
        try {
            if (opPage.isPresent()) {
                page = Integer.parseInt(opPage.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(DONHANG_.MA_DON_HANG).descending());
        Page<DONHANG> order = this.orderService.getOrder(pageable);
        List<DONHANG> orders = order.getContent();
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", order.getTotalPages());
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable Long id) {
        Optional<DONHANG> opOrder = this.orderService.fetchOrderById(id);
        DONHANG order = opOrder.get();
        model.addAttribute("orderDetails", order.getChiTietDonHang());
        return "admin/order/detail";
    }

    @GetMapping("admin/order/update/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        Optional<DONHANG> opOrder = this.orderService.fetchOrderById(id);
        DONHANG order = opOrder.get();
        model.addAttribute("order", order);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String updateOrder(Model model, @ModelAttribute("order") DONHANG update) {
        this.orderService.handleUpdateOrder(update);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getPageDeleteOrder(Model model, @PathVariable long id) {

        model.addAttribute("id", id);
        model.addAttribute("order", new DONHANG());

        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postHandleDeleteOrder(Model model, @ModelAttribute("newOrder") DONHANG order) {
        this.orderService.handleRemoveOrder(order.getMaDonHang());
        return "redirect:/admin/order";
    }

}
