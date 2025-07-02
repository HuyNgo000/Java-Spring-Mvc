<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>PhoneShop</title>
                    <meta content="width=device-width, initial-scale=1.0" name="viewport">
                    <meta content="" name="keywords">
                    <meta content="" name="description">

                    <!-- Google Web Fonts -->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link
                        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                        rel="stylesheet">

                    <!-- Icon Font Stylesheet -->
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                        rel="stylesheet">

                    <!-- Libraries Stylesheet -->
                    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                    <!-- Customized Bootstrap Stylesheet -->
                    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                    <!-- Template Stylesheet -->
                    <link href="/client/css/style.css" rel="stylesheet">
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <script>
                        document.addEventListener("DOMContentLoaded", function () {
                            let stars = document.querySelectorAll(".star");
                            let ratingInput = document.getElementById("ratingValue");
                            let currentRating = 0; // Lưu rating khi click

                            // Xử lý sự kiện hover
                            stars.forEach(star => {
                                star.addEventListener("mouseover", function () {
                                    let value = this.getAttribute("data-value");
                                    highlightStars(value);
                                });

                                star.addEventListener("mouseout", function () {
                                    highlightStars(currentRating); // Trả về trạng thái khi chưa hover
                                });

                                star.addEventListener("click", function () {
                                    currentRating = this.getAttribute("data-value"); // Lưu lại số sao khi click
                                    ratingInput.value = currentRating;
                                });
                            });

                            // Hàm làm nổi bật sao
                            function highlightStars(value) {
                                stars.forEach(star => star.classList.replace("text-warning", "text-muted")); // Reset màu
                                for (let i = 0; i < value; i++) {
                                    stars[i].classList.replace("text-muted", "text-warning"); // Đổi màu vàng
                                }
                            }
                        });
                    </script>
                    <script>
                        document.addEventListener("DOMContentLoaded", function () {
                            let userRating = parseInt(4); // Lấy số sao từ Thymeleaf
                            let stars = document.querySelectorAll("#starRating .fa-star");

                            for (let i = 0; i < userRating; i++) {
                                stars[i].classList.replace("text-muted", "text-warning");
                            }
                        });

                        $(document).ready(() => {
                            const avatarFile = $("#avatarFile");
                            const orgImage = "${updateUser.anhDaiDien}"
                            if (orgImage) {
                                const urlImage = "/images/avatar/" + orgImage;
                                $("#avatarPreview").attr("src", urlImage);
                                $("#avatarPreview").css({ "display": "block" });
                            }
                            avatarFile.change(function (e) {
                                const imgURL = URL.createObjectURL(e.target.files[0]);
                                $("#avatarPreview").attr("src", imgURL);
                                $("#avatarPreview").css({ "display": "block" });
                            });
                        });
                    </script>
                </head>

                <body>

                    <!-- Spinner Start -->
                    <div id="spinner"
                        class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                        <div class="spinner-grow text-primary" role="status"></div>
                    </div>
                    <!-- Spinner End -->


                    <!-- Navbar start -->
                    <jsp:include page="../layout/headerclient.jsp" />
                    <!-- Navbar End -->

                    <!-- Modal manager user Start -->
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                            aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered modal-xl">
                                <div class="modal-content bg-white rounded-4 shadow-lg border-0">
                                    <div class="modal-header border-0 pb-0">
                                        <h5 class="modal-title fw-bold text-primary" id="exampleModalLabel">QUẢN LÝ TÀI
                                            KHOẢN</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Đóng"></button>
                                    </div>
                                    <div class="modal-body p-4">
                                        <div class="row">
                                            <!-- Sidebar -->
                                            <div class="col-md-4 border-end">
                                                <div class="text-center mb-4">
                                                    <img src="/images/avatar/${sessionScope.avatar}"
                                                        class="rounded-circle shadow"
                                                        style="width: 150px; height: 150px; object-fit: cover;"
                                                        alt="Avatar">
                                                    <h5 class="mt-3 mb-1 fw-bold text-dark">${sessionScope.fullName}
                                                    </h5>
                                                    <span
                                                        class="badge bg-primary">${pageContext.request.userPrincipal.name}</span>
                                                </div>
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item border-0 px-0 pb-2">
                                                        <a href="/" class="btn btn-outline-primary w-100 mb-2"><i
                                                                class="fas fa-home me-2"></i>Trang chủ</a>
                                                    </li>
                                                    <li class="list-group-item border-0 px-0 pb-2">
                                                        <a href="/historyBuy"
                                                            class="btn btn-outline-primary w-100 mb-2"><i
                                                                class="fas fa-history me-2"></i>Lịch sử mua hàng</a>
                                                    </li>
                                                    <!-- <li class="list-group-item border-0 px-0 pb-2">
                                                        <a href="#"
                                                            class="btn border border-primary text-primary w-100 mb-2"><i
                                                                class="fas fa-cog me-2"></i>Cài đặt</a>
                                                    </li> -->
                                                    <li class="list-group-item border-0 px-0 pb-2">
                                                        <!-- Đổi nút Cài đặt thành Thay đổi mật khẩu -->
                                                        <a href="#" class="btn btn-outline-primary w-100 mb-2"
                                                            id="changePasswordTabBtn"><i
                                                                class="fas fa-key me-2"></i>Thay đổi mật khẩu</a>
                                                    </li>
                                                    <li class="list-group-item border-0 px-0">
                                                        <form method="post" action="/logout" class="d-grid">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                value="${_csrf.token}" />
                                                            <button class="btn btn-outline-danger w-100"><i
                                                                    class="fas fa-sign-out-alt me-2"></i>Đăng
                                                                xuất</button>
                                                        </form>
                                                    </li>
                                                </ul>
                                            </div>
                                            <!-- Main content -->
                                            <div class="col-md-8">
                                                <!-- Tabs -->
                                                <ul class="nav nav-tabs mb-3" id="userTab" role="tablist">
                                                    <li class="nav-item" role="presentation">
                                                        <button class="nav-link active" id="info-tab"
                                                            data-bs-toggle="tab" data-bs-target="#info-tab-pane"
                                                            type="button" role="tab" aria-controls="info-tab-pane"
                                                            aria-selected="true">
                                                            Cập nhật thông tin cá nhân
                                                        </button>
                                                    </li>
                                                    <li class="nav-item" role="presentation">
                                                        <button class="nav-link" id="password-tab" data-bs-toggle="tab"
                                                            data-bs-target="#password-tab-pane" type="button" role="tab"
                                                            aria-controls="password-tab-pane" aria-selected="false">
                                                            Thay đổi mật khẩu
                                                        </button>
                                                    </li>
                                                </ul>
                                                <div class="tab-content" id="userTabContent">
                                                    <!-- Tab cập nhật thông tin cá nhân -->
                                                    <div class="tab-pane fade show active" id="info-tab-pane"
                                                        role="tabpanel" aria-labelledby="info-tab">
                                                        <h5 class="fw-bold mb-3 text-primary">Cập nhật thông tin cá
                                                            nhân</h5>
                                                        <form:form method="post" action="/client/user/update"
                                                            modelAttribute="updateUser" enctype="multipart/form-data">
                                                            <div class="row g-3">
                                                                <div class="col-12 text-center mb-3">
                                                                    <img class="rounded-circle border border-2"
                                                                        style="width: 120px; height: 120px; object-fit: cover; display: none;"
                                                                        alt="avatar preview" id="avatarPreview">
                                                                </div>
                                                                <div class="col-12">
                                                                    <label class="form-label">Email</label>
                                                                    <form:input type="email" path="email"
                                                                        class="form-control" disabled="true" />
                                                                </div>
                                                                <div class="col-12 col-md-6">
                                                                    <c:set var="errorPhone">
                                                                        <form:errors path="sdt"
                                                                            cssClass="invalid-feedback" />
                                                                    </c:set>
                                                                    <label class="form-label">Số điện thoại</label>
                                                                    <form:input type="text" path="sdt"
                                                                        class="form-control ${not empty errorPhone ? 'is-invalid' : ''}" />
                                                                    ${errorPhone}
                                                                </div>
                                                                <div class="col-12 col-md-6">
                                                                    <c:set var="errorName">
                                                                        <form:errors path="hoTen"
                                                                            cssClass="invalid-feedback" />
                                                                    </c:set>
                                                                    <label class="form-label">Họ tên</label>
                                                                    <form:input type="text" path="hoTen"
                                                                        class="form-control ${not empty errorName ? 'is-invalid' : ''}" />
                                                                    ${errorName}
                                                                </div>
                                                                <div class="col-12 col-md-6">
                                                                    <c:set var="errorAddress">
                                                                        <form:errors path="diaChi"
                                                                            cssClass="invalid-feedback" />
                                                                    </c:set>
                                                                    <label class="form-label">Địa chỉ</label>
                                                                    <form:input type="text" path="diaChi"
                                                                        class="form-control ${not empty errorAddress ? 'is-invalid' : ''}" />
                                                                    ${errorAddress}
                                                                </div>
                                                                <div class="col-12 col-md-6">
                                                                    <label for="avatarFile" class="form-label">Ảnh đại
                                                                        diện</label>
                                                                    <input class="form-control" type="file"
                                                                        id="avatarFile" name="hoidanitFile"
                                                                        accept=".png, .jpg, .jpeg" />
                                                                </div>
                                                                <div class="col-12 text-end">
                                                                    <button type="submit"
                                                                        class="btn btn-outline-primary px-4 mt-2">Cập
                                                                        nhật</button>
                                                                </div>
                                                            </div>
                                                        </form:form>
                                                    </div>
                                                    <!-- Tab thay đổi mật khẩu -->
                                                    <div class="tab-pane fade" id="password-tab-pane" role="tabpanel"
                                                        aria-labelledby="password-tab">
                                                        <h5 class="fw-bold mb-3 text-primary">Thay đổi mật khẩu</h5>
                                                        <form:form method="post" action="/change-password"
                                                            modelAttribute="thayDoiMatKhau">

                                                            <div class="mb-3">
                                                                <label>Mật khẩu hiện tại:</label>
                                                                <form:password path="matKhauHienTai"
                                                                    class="form-control" />
                                                                <form:errors path="matKhauHienTai"
                                                                    cssClass="text-danger" />
                                                            </div>

                                                            <div class="mb-3">
                                                                <label>Mật khẩu mới:</label>
                                                                <form:password path="matKhauMoi" class="form-control" />
                                                                <form:errors path="matKhauMoi" cssClass="text-danger" />
                                                            </div>

                                                            <div class="mb-3">
                                                                <label>Xác nhận mật khẩu mới:</label>
                                                                <form:password path="xacNhanMatKhauMoi"
                                                                    class="form-control" />
                                                                <form:errors path="xacNhanMatKhauMoi"
                                                                    cssClass="text-danger" />
                                                            </div>

                                                            <c:if test="${not empty errorMessage}">
                                                                <div class="alert alert-danger">${errorMessage}</div>
                                                            </c:if>
                                                            <c:if test="${not empty successMessage}">
                                                                <div class="alert alert-success">${successMessage}</div>
                                                            </c:if>

                                                            <button type="submit" class="btn btn-outline-primary">Đổi
                                                                mật
                                                                khẩu</button>
                                                        </form:form>
                                                    </div>
                                                </div>
                                                <script>
                                                    // Khi bấm nút "Thay đổi mật khẩu"                                                                 ở sidebar thì chuyển sang tab đổi mật khẩu
                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        const changePasswordTabBtn = document.getElementById('changePasswordTabBtn');
                                                        if (changePasswordTabBtn) {
                                                            changePasswordTabBtn.addEventListener('click', function (e) {
                                                                e.preventDefault();
                                                                const passwordTab = document.getElementById('password-tab');
                                                                if (passwordTab) passwordTab.click();
                                                            });
                                                        }
                                                    });
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <!-- Modal manager user End -->

                    <!-- Cart Page Start -->
                    <div class="container-fluid py-5">
                        <div class="container py-5 mt-5">
                            <div class="table-responsive py-5">
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/">Trang chủ</a></li>
                                    <li class="breadcrumb-item active">Lịch sử mua hàng</li>
                                </ol>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Giá cả</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Thành tiền</th>
                                            <th scope="col">Trạng thái</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${empty orders}">
                                            <tr>
                                                <td colspan="6">
                                                    Không có đơn hàng nào được tạo
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="order" items="${orders}" varStatus="loop">
                                            <tr>
                                                <!-- <td colspan="2">Đơn hàng ${loop.index + 1}</td> -->
                                                <td colspan="2">Đơn hàng ${order.maDonHang}</td>
                                                <td colspan="1">
                                                    <fmt:formatNumber type="number" value="${order.tongGia}" /> đ
                                                </td>
                                                <td colspan="2">

                                                </td>
                                                <td colspan="1">
                                                    ${order.trangThai}
                                                </td>
                                            </tr>
                                            <c:forEach var="orderDetail" items="${order.chiTietDonHang}">
                                                <tr>
                                                    <th scope="row">
                                                        <div class="d-flex align-items-center">
                                                            <img src="/images/product/${orderDetail.sanPham.hinhAnh}"
                                                                class="img-fluid me-5 rounded-circle"
                                                                style="width: 80px; height: 80px;" alt="">
                                                        </div>
                                                    </th>
                                                    <td>
                                                        <p class="mb-0 mt-4">
                                                            <a href="/product/${orderDetail.sanPham.maSanPham}"
                                                                target="_blank">
                                                                ${orderDetail.sanPham.ten}
                                                            </a>
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <p class="mb-0 mt-4">
                                                            <fmt:formatNumber type="number"
                                                                value="${orderDetail.gia}" />
                                                            đ
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="input-group quantity mt-4" style="width: 100px;">
                                                            <input type="text"
                                                                class="form-control form-control-sm text-center border-0"
                                                                value="${orderDetail.soluong}">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <p class="mb-0 mt-4" data-cart-detail-id="${cartDetail.id}">
                                                            <fmt:formatNumber type="number"
                                                                value="${orderDetail.gia * orderDetail.soluong}" /> đ
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <a class="btn btn-outline-primary mt-3"
                                                            href="/product/${orderDetail.sanPham.maSanPham}">
                                                            Đánh giá</a>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- Cart Page End -->



                    <!-- Footer Start -->
                    <jsp:include page="../layout/footerclient.jsp" />
                    <!-- Footer End -->

                    <!-- Copyright Start -->
                    <div class="container-fluid copyright bg-dark py-4">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                                    <span class="text-light"><a href="#"><i
                                                class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All
                                        right
                                        reserved.</span>
                                </div>
                                <div class="col-md-6 my-auto text-center text-md-end text-white">
                                    <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                                    <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                                    <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                                    Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a>
                                    Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Copyright End -->



                    <!-- Back to Top -->
                    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                            class="fa fa-arrow-up"></i></a>


                    <!-- JavaScript Libraries -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="/client/lib/easing/easing.min.js"></script>
                    <script src="/client/lib/waypoints/waypoints.min.js"></script>
                    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="/client/js/main.js"></script>
                </body>

                </html>