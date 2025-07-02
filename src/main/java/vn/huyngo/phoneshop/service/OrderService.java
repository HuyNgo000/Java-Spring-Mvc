package vn.huyngo.phoneshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.huyngo.phoneshop.domain.CHITIETDONHANG;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.domain.SANPHAM;
import vn.huyngo.phoneshop.repository.OrderDetailRepository;
import vn.huyngo.phoneshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    public List<DONHANG> getOrderByUser(NGUOIDUNG user) {
        return this.orderRepository.findByNguoiDung(user);
    }

    public Page<DONHANG> getOrder(Pageable page) {
        return this.orderRepository.findAll(page);
    }

    public Optional<DONHANG> fetchOrderById(Long id) {
        return this.orderRepository.findById(id);
    }

    public void handleUpdateOrder(DONHANG order) {
        Optional<DONHANG> opOrder = this.fetchOrderById(order.getMaDonHang());
        if (opOrder.isPresent()) {
            DONHANG currenorder = opOrder.get();
            currenorder.setTrangThai(order.getTrangThai());
            this.orderRepository.save(currenorder);
        }
    }

    public void handleRemoveOrder(Long id) {
        Optional<DONHANG> opOder = this.fetchOrderById(id);
        if (opOder.isPresent()) {
            DONHANG order = opOder.get();
            List<CHITIETDONHANG> orderDetails = order.getChiTietDonHang();
            for (CHITIETDONHANG orderDetail : orderDetails) {
                this.orderDetailRepository.deleteById(orderDetail.getMaChiTietDonHang());
                SANPHAM product = orderDetail.getSanPham();
                product.setSoLuong(product.getSoLuong() + orderDetail.getSoluong());
            }
        }

        this.orderRepository.deleteById(id);
    }

    public boolean hasPurchased(Long userId, Long productId) {
        NGUOIDUNG nguoiDung = new NGUOIDUNG();
        nguoiDung.setMaNguoiDung(userId); // Tạo đối tượng NGUOIDUNG với ID người dùng

        // Kiểm tra trong repository
        return orderRepository.existsByNguoiDungAndChiTietDonHang_SanPham_maSanPhamAndTrangThai(nguoiDung, productId,
                "Pending");
    }
}
