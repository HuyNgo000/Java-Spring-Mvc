<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>Home Page</title>
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
                    <style>
                        .page-link.disabled {
                            color: var(--bs-pagination-disabled-color);
                            pointer-events: none;
                            background-color: var(--bs-pagination-disabled-bg);
                        }
                    </style>

                    <script>
                        function updateURL() {
                            const inputValue = document.getElementById('myInput').value;
                            const url = new URL(window.location.href);

                            const searchParams = url.searchParams;
                            searchParams.set('page', '1');
                            searchParams.delete('search');

                            // Thêm hoặc cập nhật query param 'text'
                            searchParams.set('search', inputValue);

                            window.location.href = url;

                            document.addEventListener("DOMContentLoaded", function () {
                                let userRating = parseInt(4);
                                let stars = document.querySelectorAll("#starRating .fa-star");

                                for (let i = 0; i < userRating; i++) {
                                    stars[i].classList.replace("text-muted", "text-warning");
                                }
                            });
                        }

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
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>




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


                    <!-- Hero Start -->
                    <jsp:include page="../layout/banner.jsp" />
                    <!-- Hero End -->


                    <div class="row g-4">
                        <div class="col-lg-12 mx-auto" style="max-width: 1320px;">
                            <div class="row g-4">
                                <div class="col-xl-3"></div>
                                <div class="col-6"></div>
                                <div class="col-xl-3 ">
                                    <div class="input-group w-100 mx-auto d-flex">
                                        <input id="myInput" type="text" class="form-control p-3" placeholder="keywords"
                                            aria-describedby="search-icon-1">
                                        <button onclick="updateURL()" id="search-icon-1" class="input-group-text p-3"><i
                                                class="fa fa-search"></i></button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>



                    <!-- Fruits Shop Start-->
                    <div class="container-fluid fruite">
                        <div class="container py-5">
                            <div class="tab-class text-center">
                                <div class="row g-4">
                                    <div class="col-lg-3 text-start" style="display: flex;">
                                        <h1>All Product</h1>

                                    </div>
                                    <div class="col-lg-9 text-end">
                                        <ul class="nav nav-pills d-inline-flex text-center mb-5">
                                            <li class="nav-item" style="display: flex;">
                                                <a class="d-flex m-2 py-2 bg-light rounded-pill active"
                                                    data-bs-toggle="pill" href="#tab-1">
                                                    <span class="text-dark" style="width: 130px;">All Products</span>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill"
                                                    href="#tab-2">
                                                    <span class="text-dark" style="width: 130px;">Apple</span>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill"
                                                    href="#tab-3">
                                                    <span class="text-dark" style="width: 130px;">SamSung</span>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill"
                                                    href="#tab-4">
                                                    <span class="text-dark" style="width: 130px;">Oppo</span>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill"
                                                    href="#tab-5">
                                                    <span class="text-dark" style="width: 130px;">Redmi</span>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill"
                                                    href="#tab-6">
                                                    <span class="text-dark" style="width: 130px;">Huawei</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane fade show p-0 active">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="row g-4">
                                                    <c:forEach var="product" items="${products}">
                                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${product.hinhAnh}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${product.noiSanXuat}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${product.maSanPham}">${product.ten}</a>
                                                                    </h4>
                                                                    <p style="font-size: 13px;">${product.moTaNgan}</p>
                                                                    <div
                                                                        class="d-flex flex-lg-wrap justify-content-center">
                                                                        <p style="font-size: 15px; text-align: center; width: 100%; "
                                                                            class="text-dark fw-bold mb-3">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${product.gia}" />đ
                                                                        </p>
                                                                        <form
                                                                            action="/add-product-to-cart/${product.maSanPham}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="tab-2" class="tab-pane fade show p-0">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="row g-4">
                                                    <c:forEach var="apple" items="${apples}">
                                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${apple.hinhAnh}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${apple.noiSanXuat}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${apple.maSanPham}">${apple.ten}</a>
                                                                    </h4>
                                                                    <p style="font-size: 13px;">${apple.moTaNgan}</p>
                                                                    <div
                                                                        class="d-flex flex-lg-wrap justify-content-center">
                                                                        <p style="font-size: 15px; text-align: center; width: 100%; "
                                                                            class="text-dark fw-bold mb-3">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${apple.gia}" />đ
                                                                        </p>
                                                                        <form
                                                                            action="/add-product-to-cart/${apple.maSanPham}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="tab-3" class="tab-pane fade show p-0">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="row g-4">
                                                    <c:forEach var="samsung" items="${samsungs}">
                                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${samsung.hinhAnh}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${samsung.noiSanXuat}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${samsung.maSanPham}">${samsung.ten}</a>
                                                                    </h4>
                                                                    <p style="font-size: 13px;">${samsung.moTaNgan}</p>
                                                                    <div
                                                                        class="d-flex flex-lg-wrap justify-content-center">
                                                                        <p style="font-size: 15px; text-align: center; width: 100%; "
                                                                            class="text-dark fw-bold mb-3">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${samsung.gia}" />đ
                                                                        </p>
                                                                        <form
                                                                            action="/add-product-to-cart/${samsung.maSanPham}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="tab-4" class="tab-pane fade show p-0">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="row g-4">
                                                    <c:forEach var="oppo" items="${oppos}">
                                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${oppo.hinhAnh}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${oppo.noiSanXuat}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${oppo.maSanPham}">${oppo.ten}</a>
                                                                    </h4>
                                                                    <p style="font-size: 13px;">${oppo.moTaNgan}</p>
                                                                    <div
                                                                        class="d-flex flex-lg-wrap justify-content-center">
                                                                        <p style="font-size: 15px; text-align: center; width: 100%; "
                                                                            class="text-dark fw-bold mb-3">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${oppo.gia}" />đ
                                                                        </p>
                                                                        <form
                                                                            action="/add-product-to-cart/${oppo.maSanPham}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="tab-5" class="tab-pane fade show p-0">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="row g-4">
                                                    <c:forEach var="redmi" items="${redmis}">
                                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${redmi.hinhAnh}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${redmi.noiSanXuat}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${redmi.maSanPham}">${redmi.ten}</a>
                                                                    </h4>
                                                                    <p style="font-size: 13px;">${redmi.moTaNgan}</p>
                                                                    <div
                                                                        class="d-flex flex-lg-wrap justify-content-center">
                                                                        <p style="font-size: 15px; text-align: center; width: 100%; "
                                                                            class="text-dark fw-bold mb-3">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${redmi.gia}" />đ
                                                                        </p>
                                                                        <form
                                                                            action="/add-product-to-cart/${redmi.maSanPham}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="tab-6" class="tab-pane fade show p-0">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="row g-4">
                                                    <c:forEach var="huawei" items="${huaweis}">
                                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${huawei.hinhAnh}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">${huawei.noiSanXuat}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${huawei.maSanPham}">${huawei.ten}</a>
                                                                    </h4>
                                                                    <p style="font-size: 13px;">${huawei.moTaNgan}</p>
                                                                    <div
                                                                        class="d-flex flex-lg-wrap justify-content-center">
                                                                        <p style="font-size: 15px; text-align: center; width: 100%; "
                                                                            class="text-dark fw-bold mb-3">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${huawei.gia}" />đ
                                                                        </p>
                                                                        <form
                                                                            action="/add-product-to-cart/${huawei.maSanPham}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <nav aria-label="Page navigation example" class="mt-4">
                                        <ul style="display: flex;" class="pagination justify-content-center">
                                            <li class="page-item">
                                                <a class="${1 eq currentPage ? 'disabled page-link' : 'page-link'}"
                                                    href="/?page=${currentPage - 1}${queryString}"
                                                    aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <c:forEach begin="1" end="${totalPages }" varStatus="loop">
                                                <li class="page-item"><a
                                                        class="${(loop.index) eq currentPage ? 'active page-link' : 'page-link'}"
                                                        href="/?page=${loop.index}${queryString}">${loop.index}</a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item">
                                                <a class="${totalPages eq currentPage ? 'disabled page-link' : 'page-link'}"
                                                    href="/?page=${currentPage + 1}${queryString}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Fruits Shop End-->

                    <!-- Featurs Section Start -->
                    <jsp:include page="../layout/featur.jsp" />
                    <!-- Featurs Section End -->


                    <!-- Tastimonial Start -->
                    <div class="container-fluid testimonial">
                        <div class="container py-5">
                            <div class="testimonial-header text-center">
                                <h4 class="text-primary">Our Testimonial</h4>
                                <h1 class="display-5 mb-5 text-dark">Our Client Saying!</h1>
                            </div>
                            <div class="owl-carousel testimonial-carousel owl-loaded owl-drag">
                                <c:forEach var="review" items="${reviews}">
                                    <div class="testimonial-item img-border-radius bg-light rounded p-4">
                                        <div class="position-relative">
                                            <i class="fa fa-quote-right fa-2x text-secondary position-absolute"
                                                style="bottom: 30px; right: 0;"></i>
                                            <div class="mb-4 pb-4 border-bottom border-secondary">
                                                <p class="mb-0">${review.binhLuan}</p>

                                            </div>
                                            <div class="d-flex align-items-center flex-nowrap">
                                                <div class="bg-secondary rounded">
                                                    <img src="/images/avatar/${review.nguoiDung.anhDaiDien}"
                                                        class="img-fluid rounded" style="width: 100px; height: 100px;"
                                                        alt="">
                                                </div>
                                                <div class="ms-4 d-block">
                                                    <a href="/product/${review.sanPham.maSanPham}">
                                                        <h4 class="text-dark">${review.nguoiDung.hoTen}</h4>
                                                    </a>
                                                    <p class="m-0 pb-3">${review.formatted}</p>
                                                    <div id="starRating" class="d-flex align-items-center">
                                                        <c:forEach var="i" begin="1" end="5">
                                                            <i
                                                                class="fa fa-star ${i <= review.sao ? 'text-warning' : 'text-muted'}"></i>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- Tastimonial End -->


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