package vn.huyngo.phoneshop.controller.admin;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.huyngo.phoneshop.domain.CHITIETDONHANG;
import vn.huyngo.phoneshop.domain.DONHANG;
import vn.huyngo.phoneshop.domain.DONHANG_;
import vn.huyngo.phoneshop.domain.NGUOIDUNG;
import vn.huyngo.phoneshop.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.itextpdf.text.pdf.BaseFont;

import org.springframework.web.bind.annotation.PostMapping;
import java.io.OutputStream;

@Controller
public class OderController {
    private final OrderService orderService;

    public OderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getTableOrder(Model model, @RequestParam("page") Optional<String> opPage, HttpServletRequest request,
            Authentication authentication) {
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

        Map<Long, Double> totalPricePerOrder = new HashMap<>();

        // Tính tổng giá của từng đơn hàng
        for (DONHANG donHang : orders) {
            double totalPrice = donHang.getChiTietDonHang().stream()
                    .mapToDouble(item -> item.getSoluong() * item.getSanPham().getGia())
                    .sum();
            // Lưu vào Map với key là mã đơn hàng và value là tổng giá
            totalPricePerOrder.put(donHang.getMaDonHang(), totalPrice);
        }

        // Đưa vào model để hiển thị trên View
        model.addAttribute("totalPricePerOrder", totalPricePerOrder);
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", order.getTotalPages());
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable Long id) {
        Optional<DONHANG> opOrder = this.orderService.fetchOrderById(id);
        DONHANG order = opOrder.get();
        double totalPrice = 0;
        totalPrice += order.getChiTietDonHang().stream()
                .mapToDouble(item -> item.getSoluong() * item.getSanPham().getGia()).sum();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderDetails", order.getChiTietDonHang());
        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    @GetMapping("admin/order/update/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        Optional<DONHANG> opOrder = this.orderService.fetchOrderById(id);
        DONHANG order = opOrder.get();
        double totalPrice = 0;
        totalPrice += order.getChiTietDonHang().stream()
                .mapToDouble(item -> item.getSoluong() * item.getSanPham().getGia()).sum();
        model.addAttribute("totalPrice", totalPrice);
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

    @PostMapping("/export-invoice")
    public void exportInvoice(long orderId, HttpServletResponse response) {
        try {
            // Lấy thông tin đơn hàng
            Optional<DONHANG> opOrder = this.orderService.fetchOrderById(orderId);
            DONHANG order = opOrder.get();
            if (order == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng");
                return;
            }
            List<CHITIETDONHANG> orderDetails = order.getChiTietDonHang();

            // Thiết lập header trả về file PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice_" + orderId + ".pdf");

            OutputStream out = response.getOutputStream();
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(document, out);
            document.open();

            // Nạp font Unicode (ví dụ: Arial hoặc DejaVu)
            String fontPath = "C:/Windows/Fonts/arial.ttf"; // Đường dẫn tới font trên máy chủ
            BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);

            // Tiêu đề
            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", new Font(bf, 18, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            // Thông tin cửa hàng
            document.add(new Paragraph("Tên cửa hàng: Toàn Ngọc Mobile", font));
            document.add(new Paragraph("Địa chỉ: 62 Nguyễn Tất Thành, Đắk Mâm, Krông Nô, Đắk Nông", font));
            document.add(new Paragraph("Điện thoại: 0986487676", font));
            document.add(new Paragraph("Email: huyngo080582@gmail.com", font));
            document.add(new Paragraph("Website: www.toanngoc.vn", font));
            document.add(new Paragraph(" "));

            // Thông tin khách hàng và đơn hàng
            document.add(new Paragraph("Mã đơn hàng: " + order.getMaDonHang(), font));
            document.add(new Paragraph("Ngày đặt hàng: " + order.getNgayDatHang(), font));
            document.add(new Paragraph("Khách hàng: " + order.getTenNguoiNhan(), font));
            document.add(new Paragraph("SĐT: " + order.getSdtNguoiNhan(), font));
            document.add(new Paragraph("Địa chỉ giao hàng: " + order.getDiaChiNguoiNhan(), font));
            document.add(new Paragraph("Phương thức thanh toán: " + order.getHinhThucThanhToan(), font));
            document.add(new Paragraph(" "));

            // Bảng chi tiết đơn hàng
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[] { 1, 4, 2, 2, 2 });

            Font headerFont = new Font(bf, 12, Font.BOLD);
            table.addCell(new PdfPCell(new Phrase("STT", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá (VNĐ)", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Thành tiền (VNĐ)", headerFont)));

            int stt = 1;
            double tongTien = 0;
            for (CHITIETDONHANG detail : orderDetails) {
                table.addCell(String.valueOf(stt++));
                table.addCell(detail.getSanPham().getTen());
                table.addCell(String.format("%,.0f", detail.getGia()));
                table.addCell(String.valueOf(detail.getSoluong()));
                double thanhTien = detail.getGia() * detail.getSoluong();
                table.addCell(String.format("%,.0f", thanhTien));
                tongTien += thanhTien;
            }

            // Tổng cộng
            PdfPCell cell = new PdfPCell(new Phrase("Tổng cộng", headerFont));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            table.addCell(String.format("%,.0f", tongTien));

            document.add(table);
            document.add(new Paragraph(" "));

            // Ghi chú
            document.add(new Paragraph("Hóa đơn này được tạo tự động từ hệ thống bán hàng.", font));
            document.add(new Paragraph("Vui lòng giữ hóa đơn để bảo hành sản phẩm.", font));
            document.add(new Paragraph("Mọi thắc mắc xin liên hệ hotline: 0986487676", font));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Trân trọng cảm ơn quý khách đã mua hàng tại Toàn Ngọc Mobile!", font));

            document.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
