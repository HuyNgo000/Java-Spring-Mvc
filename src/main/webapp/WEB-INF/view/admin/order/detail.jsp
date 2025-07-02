<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>datatables-demo
                        <meta charset="utf-8" />
                        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                        <meta name="author" content="Hỏi Dân IT" />
                        <title>Chi Tiết Đơn Hàng</title>
                        <link href="/css/styles.css" rel="stylesheet" />
                        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                            crossorigin="anonymous"></script>
                    </head>

                    <body class="sb-nav-fixed">
                        <jsp:include page="../layout/header.jsp" />
                        <div id="layoutSidenav">
                            <jsp:include page="../layout/sidebar.jsp" />
                            <div id="layoutSidenav_content">
                                <main>
                                    <div class="container-fluid px-4">
                                        <h1 class="mt-4">Quản lý đơn hàng</h1>
                                        <ol class="breadcrumb mb-4">
                                            <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                            <li class="breadcrumb-item active"><a href="/admin/order">Đơn hàng</a></li>
                                            <li class="breadcrumb-item active">Chi tiết đơn hàng</li>
                                        </ol>
                                        <div>
                                            <div class="row">
                                                <div class="col-12 col-12 mx-auto">
                                                    <hr />
                                                    <table class="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Sản phẩm</th>
                                                                <th scope="col">Tên</th>
                                                                <th scope="col">Giá</th>
                                                                <th scope="col">Số lượng</th>
                                                                <th scope="col">Tổng tiền</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="orderDetail" items="${orderDetails}">
                                                                <tr>
                                                                    <td> <img style="width: 110px;"
                                                                            src="/images/product/${orderDetail.sanPham.hinhAnh}"
                                                                            class="img-fluid  p-3 " alt=""></td>
                                                                    <td>${orderDetail.sanPham.ten}</td>
                                                                    <td>
                                                                        <fmt:formatNumber type="number"
                                                                            value="${orderDetail.gia}" />đ
                                                                    </td>
                                                                    <td>${orderDetail.soluong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber type="number"
                                                                            value="${orderDetail.soluong*orderDetail.gia}" />
                                                                        đ
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                    <button type="button" class="btn btn btn-primary ms-2 mt-3"
                                                        style="float: right;" data-bs-toggle="modal"
                                                        data-bs-target="#exportInvoiceModal">
                                                        Xuất hóa đơn
                                                    </button>
                                                    <!-- Modal Xuất hóa đơn -->
                                                    <div class="modal fade" id="exportInvoiceModal" tabindex="-1"
                                                        aria-labelledby="exportInvoiceModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content" style="width: max-content;">
                                                                <form:form method="post" action="/export-invoice">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="btn-close"
                                                                            data-bs-dismiss="modal"
                                                                            aria-label="Đóng"></button>
                                                                    </div>

                                                                    <div class="container-invoice">
                                                                        <input type="hidden" name="orderId"
                                                                            id="modalOrderId"
                                                                            value="${order.maDonHang}" />
                                                                        <h1 class="my-3" style="text-align: center;">HÓA
                                                                            ĐƠN
                                                                            BÁN HÀNG
                                                                        </h1>

                                                                        <div class="store-info">
                                                                            <p><strong>Tên cửa hàng:</strong> Toàn Ngọc
                                                                                Mobile
                                                                            </p>
                                                                            <p><strong>Địa chỉ:</strong> 62 Nguyễn Tất
                                                                                Thành, Đắk Mâm, Krông Nô, Đắk Nông</p>
                                                                            <p><strong>Điện thoại:</strong> 0986487676
                                                                            </p>
                                                                            <p><strong>Email:</strong>
                                                                                huyngo080582@gmail.com
                                                                            </p>
                                                                            <p><strong>Website:</strong> www.toanngoc.vn
                                                                            </p>
                                                                        </div>

                                                                        <div class="customer-info">

                                                                            <p><strong>Mã đơn hàng:</strong>
                                                                                ${order.maDonHang}</p>
                                                                            <p><strong>Ngày đặt hàng:</strong>
                                                                                ${order.ngayDatHang}</p>
                                                                            <p><strong>Khách hàng:</strong>
                                                                                ${order.tenNguoiNhan}</p>
                                                                            <p><strong>SĐT:</strong>
                                                                                ${order.sdtNguoiNhan}
                                                                            </p>
                                                                            <p><strong>Địa chỉ giao hàng:</strong>
                                                                                ${order.diaChiNguoiNhan}</p>
                                                                            <p><strong>Phương thức thanh toán:</strong>
                                                                                ${order.hinhThucThanhToan}</p>
                                                                            </p>
                                                                        </div>

                                                                        <h2>CHI TIẾT ĐƠN HÀNG</h2>

                                                                        <table>
                                                                            <thead class="highlight">
                                                                                <tr>

                                                                                    <th>Tên sản phẩm</th>
                                                                                    <th>Đơn giá (VNĐ)</th>
                                                                                    <th>Số lượng</th>
                                                                                    <th>Thành tiền (VNĐ)</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <c:forEach var="orderDetail"
                                                                                    items="${orderDetails}">
                                                                                    <tr>

                                                                                        <td>${orderDetail.sanPham.ten}
                                                                                        </td>
                                                                                        <td>
                                                                                            <fmt:formatNumber
                                                                                                type="number"
                                                                                                value="${orderDetail.gia}" />
                                                                                            đ
                                                                                        </td>
                                                                                        <td>${orderDetail.soluong}</td>
                                                                                        <td>
                                                                                            <fmt:formatNumber
                                                                                                type="number"
                                                                                                value="${orderDetail.soluong*orderDetail.gia}" />
                                                                                            đ
                                                                                        </td>
                                                                                    </tr>

                                                                                </c:forEach>
                                                                                <tr class="total-row">
                                                                                    <td colspan="3">Tạm tính</td>
                                                                                    <td>
                                                                                        <fmt:formatNumber type="number"
                                                                                            value="${totalPrice}" />
                                                                                        đ
                                                                                    </td>
                                                                                </tr>
                                                                                <tr class="total-row">
                                                                                    <td colspan="3">Phí vận chuyển</td>
                                                                                    <td>Free</td>
                                                                                </tr>
                                                                                <tr class="total-row highlight">
                                                                                    <td colspan="3">Tổng cộng</td>
                                                                                    <td>
                                                                                        <fmt:formatNumber type="number"
                                                                                            value="${totalPrice}" />
                                                                                        đ
                                                                                    </td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>

                                                                        <div class="note">
                                                                            <p>Hóa đơn này được tạo tự động từ hệ thống
                                                                                bán
                                                                                hàng.</p>
                                                                            <p>Vui lòng giữ hóa đơn để bảo hành sản
                                                                                phẩm.
                                                                            </p>
                                                                            <p>Mọi thắc mắc xin liên hệ hotline:
                                                                                <strong>0986487676</strong>
                                                                            </p>
                                                                        </div>

                                                                        <div class="thanks my-3">
                                                                            Trân trọng cảm ơn quý khách đã mua hàng tại
                                                                            Toàn
                                                                            Ngọc Mobile!
                                                                        </div>
                                                                    </div>

                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary"
                                                                            data-bs-dismiss="modal">Hủy</button>
                                                                        <button type="submit"
                                                                            class="btn btn-primary">Xuất
                                                                            PDF</button>
                                                                    </div>
                                                                </form:form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                </main>
                                <jsp:include page="../layout/footer.jsp" />
                            </div>
                        </div>
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                            crossorigin="anonymous"></script>
                        <script src="/js/scripts.js"></script>
                    </body>

                    </html>