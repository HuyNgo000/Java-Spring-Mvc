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
                            <div class="modal-dialog modal-fullscreen" style="height: 925px;">
                                <div class="modal-content rounded-0">
                                    <div class="modal-header">
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="container" style="background-color: white;">
                                        <div class="row g-4" style="margin-bottom: -50px;">
                                            <div class="col-lg-2 pt-3"
                                                style="background-color: #ccc;margin-bottom: 50px;">
                                                <div class="row g-4">
                                                    <div class="col-lg-12">
                                                        <div class="mb-3">
                                                            <ul class="list-unstyled fruite-categorie">
                                                                <li class="d-flex flex-column"
                                                                    style="min-width: 300px;">
                                                                </li>
                                                                <div class="d-flex">
                                                                    <div> <img
                                                                            style="width: 50px; height: 50px; border-radius: 50%; overflow: hidden;"
                                                                            src="/images/avatar/${sessionScope.avatar}" />
                                                                    </div>
                                                                    <div class="text-center text-dark my-3 mx-3"
                                                                        style="font-size: 20px;font-weight: bold;font-family: 'Times New Roman', serif;">
                                                                        ${sessionScope.fullName}
                                                                    </div>
                                                                </div>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12 px-0">
                                                        <div class="mb-3">
                                                            <ul class="ul">
                                                                <li><a href="/"
                                                                        class="btn btn-primary btn-muser text-dark">Trang
                                                                        chủ</a>
                                                                </li>
                                                                <li><a href="historyBuy"
                                                                        class="btn btn-primary btn-muser text-dark">Lịch
                                                                        sử
                                                                        mua hàng</a>
                                                                </li>
                                                                <li><a href="#"
                                                                        class="btn btn-primary btn-muser text-dark">Cài
                                                                        đặt</a>
                                                                </li>
                                                                <hr>
                                                                <li>
                                                                    <form method="post" action="/logout">
                                                                        <input type="hidden"
                                                                            name="${_csrf.parameterName}"
                                                                            value="${_csrf.token}" />
                                                                        <button
                                                                            class="btn-muser btn-logout text-dark">Đăng
                                                                            xuất</button>
                                                                    </form>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-10 pt-5">
                                                <div class="row g-4 justify-content-center">
                                                    <div class="row mx-auto" style="width: 1000px;height: 800px;">
                                                        <div class="col-12 px-5 mx-auto">
                                                            <form:form method="post" action="/client/user/update"
                                                                modelAttribute="updateUser"
                                                                enctype="multipart/form-data">
                                                                <div class="mb-3" style="display: none;">
                                                                    <label class="form-label">ID</label>
                                                                    <form:input type="text" path="maNguoiDung"
                                                                        Class="form-control" />
                                                                </div>

                                                                <div class="col-12 mb-3">
                                                                    <img class=" mx-auto mb-4"
                                                                        style="width: 200px; height: 200px; border-radius: 50%; overflow: hidden; display: none; margin-bottom: 10px;"
                                                                        alt="avatar preview" id="avatarPreview">
                                                                </div>

                                                                <div class="mb-3">
                                                                    <label class="form-label">Email
                                                                        address</label>
                                                                    <form:input type="email" path="email"
                                                                        Class="form-control" disabled="true" />
                                                                </div>

                                                                <div class="mb-3">
                                                                    <c:set var="errorPhone">
                                                                        <form:errors path="sdt"
                                                                            cssClass="invalid-feedback" />
                                                                    </c:set>
                                                                    <label class="form-label">Phone
                                                                        Number</label>
                                                                    <form:input type="text" path="sdt"
                                                                        Class="form-control ${not empty errorPhone ? 'is-invalid' : ''}" />
                                                                    ${errorPhone}
                                                                </div>

                                                                <div class="mb-3">
                                                                    <c:set var="errorName">
                                                                        <form:errors path="hoTen"
                                                                            cssClass="invalid-feedback" />
                                                                    </c:set>
                                                                    <label class="form-label">Full
                                                                        Name</label>
                                                                    <form:input type="text" path="hoTen"
                                                                        Class="form-control ${not empty errorName ? 'is-invalid' : ''}" />
                                                                    ${errorName}
                                                                </div>

                                                                <div class="row">
                                                                    <div class="mb-3 col-12 col-md-6">
                                                                        <c:set var="errorAddress">
                                                                            <form:errors path="diaChi"
                                                                                cssClass="invalid-feedback" />
                                                                        </c:set>
                                                                        <label class="form-label">Address</label>
                                                                        <form:input type="text" path="diaChi"
                                                                            Class="form-control ${not empty errorAddress ? 'is-invalid' : ''}" />
                                                                        ${errorAddress}
                                                                    </div>
                                                                    <div class="mb-3 col-12 col-md-6">
                                                                        <label for="avatarFile"
                                                                            class="form-label">Avatar</label>
                                                                        <input class="form-control" type="file"
                                                                            id="avatarFile" name="hoidanitFile"
                                                                            accept=".png, .jpg, .jpeg" />
                                                                    </div>
                                                                </div>

                                                                <button type="submit" style="float: right;"
                                                                    class="btn btn-warning my-3">Update</button>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </div>
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
                                    <li class="breadcrumb-item active"><a href="/">Home</a></li>
                                    <li class="breadcrumb-item active">Cart</li>
                                </ol>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Products</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total</th>
                                            <th scope="col">Handle</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${empty cartDetails}">
                                            <tr>
                                                <td colspan="6">
                                                    Không có sản phẩm trong giỏ hàng
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="cartDetail" items="${cartDetails}" varStatus="status">
                                            <tr>
                                                <th scope="row">
                                                    <div class="d-flex align-items-center">
                                                        <img src="/images/product/${cartDetail.sanPham.hinhAnh}"
                                                            class="img-fluid me-5 rounded-circle"
                                                            style="width: 80px; height: 80px;" alt="">
                                                    </div>
                                                </th>
                                                <td>
                                                    <p class="mb-0 mt-4"><a
                                                            href="/product/${cartDetail.sanPham.maSanPham}">${cartDetail.sanPham.ten}</a>
                                                    </p>
                                                </td>
                                                <td>
                                                    <p class="mb-0 mt-4">
                                                        <fmt:formatNumber type="number" value="${cartDetail.gia}" />
                                                    </p>
                                                </td>
                                                <td>
                                                    <div class="input-group quantity mt-4" style="width: 100px;">
                                                        <div class="input-group-btn">
                                                            <button
                                                                class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                                <i class="fa fa-minus"></i>
                                                            </button>
                                                        </div>
                                                        <input type="text"
                                                            class="form-control form-control-sm text-center border-0"
                                                            value="${cartDetail.soLuong}"
                                                            data-cart-detail-id="${cartDetail.maChiTietGioHang}"
                                                            data-cart-detail-price="${cartDetail.gia}"
                                                            data-cart-detail-index="${status.index}">
                                                        <div class="input-group-btn">
                                                            <button
                                                                class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                                <i class="fa fa-plus"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="mb-0 mt-4"
                                                        data-cart-detail-id="${cartDetail.maChiTietGioHang}">
                                                        <fmt:formatNumber type="number"
                                                            value="${cartDetail.soLuong * cartDetail.gia}" />đ
                                                    </p>
                                                </td>
                                                <td>
                                                    <form action="/cart/${cartDetail.maChiTietGioHang}" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}"
                                                            value="${_csrf.token}" />
                                                        <button class="btn btn-md rounded-circle bg-light border mt-4">
                                                            <i class="fa fa-times text-danger"></i>
                                                        </button>
                                                    </form>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${not empty cartDetails}">
                                <div class="row g-4 justify-content-end" id="hehe">
                                    <div class="col-12 col-md-8">
                                        <div class="bg-light rounded">
                                            <div class="p-4">
                                                <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span>
                                                </h1>
                                                <div class="d-flex justify-content-between mb-4">
                                                    <h5 class="mb-0 me-4">Subtotal:</h5>
                                                    <p class="mb-0" data-cart-total-price="${totalPrice}">
                                                        <fmt:formatNumber type="number" value="${totalPrice}" />đ
                                                    </p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <h5 class="mb-0 me-4">Shipping</h5>
                                                    <div class="">
                                                        <p class="mb-0">0đ</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div
                                                class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                                <h5 class="mb-0 ps-4 me-4">Total</h5>
                                                <p class="mb-0 pe-4" data-cart-total-price="${totalPrice}">
                                                    <fmt:formatNumber type="number" value="${totalPrice}" />đ
                                                </p>
                                            </div>
                                            <form:form action="/confirm-checkout" method="post" modelAttribute="cart">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <div style="display: none;">
                                                    <c:forEach var="cartDetail" items="${cart.chiTietGioHang}"
                                                        varStatus="status">
                                                        <div class="mb-3">
                                                            <div class="form-group">
                                                                <label>Id:</label>
                                                                <form:input class="form-control" type="text"
                                                                    value="${cartDetail.maChiTietGioHang}"
                                                                    path="chiTietGioHang[${status.index}].maChiTietGioHang" />
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Quantity:</label>
                                                                <form:input class="form-control" type="text"
                                                                    value="${cartDetail.soLuong}"
                                                                    path="chiTietGioHang[${status.index}].soLuong" />
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <button
                                                    class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4">Xác
                                                    nhận thanh toán
                                                </button>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
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