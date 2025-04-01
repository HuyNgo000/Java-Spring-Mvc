<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>${product.ten} - PhoneShop</title>
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
                            let userRating = parseInt(4);
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


                    <!-- Single Product Start -->
                    <div class="container-fluid py-5">
                        <div class="container-fluid py-5 mt-5">
                            <div class="container py-5">
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/">Home</a></li>
                                    <li class="breadcrumb-item active">Product detail</li>
                                </ol>
                                <div class="row g-4 mb-5">
                                    <div class="col-lg-8 col-xl-9">
                                        <div class="row g-4">
                                            <div class="col-lg-6">
                                                <div class="border rounded">
                                                    <a href="#">
                                                        <img src="../images/product/${product.hinhAnh}"
                                                            class="img-fluid rounded" alt="Image">
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <h4 class="fw-bold mb-3">${product.ten}</h4>
                                                <p class="mb-3">${product.noiSanXuat}</p>
                                                <h5 class="fw-bold mb-3">
                                                    <fmt:formatNumber type="number" value="${product.gia}" /> đ
                                                </h5>
                                                <div class="d-flex mb-4">
                                                    <i class="fa fa-star text-secondary"></i>
                                                    <i class="fa fa-star text-secondary"></i>
                                                    <i class="fa fa-star text-secondary"></i>
                                                    <i class="fa fa-star text-secondary"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <p class="mb-4">${product.moTaNgan}</p>
                                                <div class="input-group quantity mb-3" style="width: 100px;">
                                                    <div class="input-group-btn">
                                                        <button
                                                            class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                            <i class="fa fa-minus"></i>
                                                        </button>
                                                    </div>
                                                    <input type="text"
                                                        class="form-control form-control-sm text-center border-0"
                                                        value="1" data-cart-detail-index="0">
                                                    <div class="input-group-btn">
                                                        <button
                                                            class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                                <p class="mb-4">Số Lượng: ${product.soLuong}</p>
                                                <form action="/add-to-cart" method="post">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                        value="${_csrf.token}" />
                                                    <div class="form-group d-none">
                                                        <label>Id:</label>
                                                        <input class="form-control" type="text"
                                                            value="${product.maSanPham}" name="id" />
                                                    </div>
                                                    <div class="form-group d-none">
                                                        <label>Quantity:</label>
                                                        <input class="form-control" type="text" name="quantity"
                                                            id="chiTietGioHang0.soLuong" value="1" />
                                                    </div>
                                                    <button
                                                        class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i
                                                            class="fa fa-shopping-bag me-2 text-primary"></i> Add to
                                                        cart</button>
                                                </form>
                                            </div>
                                            <div class="col-lg-12">
                                                <nav>
                                                    <div class="nav nav-tabs mb-3">
                                                        <a href="#tab-1" data-bs-toggle="pill"
                                                            class="nav-link active border-white border-bottom-0"
                                                            aria-selected="true">Description</a>
                                                        <a href="#tab-2" data-bs-toggle="pill"
                                                            class="nav-link border-white border-bottom-0" type="button"
                                                            aria-selected="false">Reviews</a>
                                                    </div>
                                                </nav>
                                            </div>
                                            <div class="tab-content mb-5">
                                                <div id="tab-1" class="tab-pane fade show p-0 active">
                                                    <div class="row g-4">
                                                        <div class="col-lg-12" style="margin-top: 0px;">
                                                            <div class="row g-4">
                                                                <p>${product.moTaChiTiet}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="tab-2" class="tab-pane fade show p-0">
                                                    <div class="row g-4">
                                                        <div class="col-lg-12" style="margin-top: 0px;">
                                                            <div class="row g-4">
                                                                <c:forEach var="review" items="${reviews}">
                                                                    <div class="d-flex">
                                                                        <img src="/images/avatar/${review.nguoiDung.anhDaiDien}"
                                                                            class="img-fluid rounded-circle p-3 avatarhehe"
                                                                            alt="">
                                                                        <div class="col-lg-10">
                                                                            <p class="mb-2" style="font-size: 14px;">
                                                                                ${review.formatted}
                                                                            </p>
                                                                            <div class="d-flex justify-content-between">
                                                                                <h5>${review.nguoiDung.hoTen}</h5>
                                                                                <div id="starRating"
                                                                                    class="d-flex align-items-center">
                                                                                    <c:forEach var="i" begin="1"
                                                                                        end="5">
                                                                                        <i
                                                                                            class="fa fa-star ${i <= review.sao ? 'text-warning' : 'text-muted'}"></i>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </div>
                                                                            <div>
                                                                                <p>${review.binhLuan}</p>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </c:forEach>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${isAuthenticated}">
                                                <form:form action="/product/${id}/review" method="post"
                                                    modelAttribute="reviews">
                                                    <h4 class="mb-5 fw-bold">Leave a Reply</h4>
                                                    <div class="row g-4">
                                                        <div class="col-lg-6 d-flex align-items-center"
                                                            style="min-width: 300px;">
                                                            <div>
                                                                <img src="/images/avatar/${sessionScope.avatar}"
                                                                    class="img-fluid rounded-circle p-3 avatarhehe" />
                                                            </div>
                                                            <div class="d-flex justify-content-between">
                                                                <h5>${sessionScope.fullName}</h5>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="border-bottom rounded">
                                                                <textarea id="binhLuan" name="binhLuan"
                                                                    class="form-control border-0" cols="30" rows="4"
                                                                    placeholder="Your Review *"
                                                                    spellcheck="false"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="d-flex justify-content-between py-3 mb-5">
                                                                <div class="d-flex align-items-center">
                                                                    <p class="mb-0 me-3">Please rate:</p>
                                                                    <div class="d-flex align-items-center"
                                                                        style="font-size: 24px;">
                                                                        <i class="fa fa-star text-muted star"
                                                                            data-value="1"></i>
                                                                        <i class="fa fa-star text-muted star"
                                                                            data-value="2"></i>
                                                                        <i class="fa fa-star text-muted star"
                                                                            data-value="3"></i>
                                                                        <i class="fa fa-star text-muted star"
                                                                            data-value="4"></i>
                                                                        <i class="fa fa-star text-muted star"
                                                                            data-value="5"></i>
                                                                    </div>
                                                                    <input type="hidden" id="ratingValue" name="sao"
                                                                        value="0">
                                                                </div>
                                                                <button type="submit"
                                                                    class="btn btn-primary rounded-pill px-4 py-2">Gửi
                                                                    đánh
                                                                    giá</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form:form>
                                            </c:if>
                                            <c:if test="${!isAuthenticated}">
                                                <p><a href="/login">Đăng nhập</a> để đánh giá sản phẩm.</p>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-xl-3">
                                        <div class="row g-4 fruite">
                                            <div class="col-lg-12">
                                                <div class="mb-4">
                                                    <h4>Categories</h4>
                                                    <ul class="list-unstyled fruite-categorie">
                                                        <li>
                                                            <div class="d-flex justify-content-between fruite-name">
                                                                <a href="#"><i
                                                                        class="fas fa-apple-alt me-2"></i>Apple</a>
                                                                <span>(3)</span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="d-flex justify-content-between fruite-name">
                                                                <a href="#"><i
                                                                        class="fas fa-apple-alt me-2"></i>SamSung</a>
                                                                <span>(5)</span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="d-flex justify-content-between fruite-name">
                                                                <a href="#"><i
                                                                        class="fas fa-apple-alt me-2"></i>Redmi</a>
                                                                <span>(2)</span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="d-flex justify-content-between fruite-name">
                                                                <a href="#"><i
                                                                        class="fas fa-apple-alt me-2"></i>Oppo</a>
                                                                <span>(8)</span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="d-flex justify-content-between fruite-name">
                                                                <a href="#"><i
                                                                        class="fas fa-apple-alt me-2"></i>Huawei</a>
                                                                <span>(5)</span>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <!-- Single Product End -->

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