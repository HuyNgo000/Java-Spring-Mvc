<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title> Sản Phẩm - PhoneShop</title>
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

                    <!-- CSS -->
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css">

                    <meta name="_csrf" content="${_csrf.token}" />
                    <meta name="_csrf_header" content="${_csrf.headerName}" />

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



                </head>

                <body>

                    <!-- Spinner Start -->
                    <div id="spinner"
                        class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                        <div class="spinner-grow text-primary" role="status"></div>
                    </div>
                    <!-- Spinner End -->

                    <jsp:include page="../layout/headerclient.jsp" />

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

                    <!-- Single Product Start -->
                    <div class="container-fluid py-5 mt-5">
                        <div class="container py-5 mt-5">
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item active"><a href="/">Trang chủ</a></li>
                                <li class="breadcrumb-item active">Sản phẩm</li>
                            </ol>
                            <div class="row g-4 mb-5">
                                <div class="row g-4 fruite">
                                    <div class="col-12 col-md-4">
                                        <div class="row g-4">
                                            <div class="col-12" id="factoryFilter">
                                                <div class="mb-2"><b>Hãng sản xuất</b></div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-1"
                                                        value="Apple">
                                                    <label class="form-check-label" for="factory-1">Apple</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-2"
                                                        value="SamSung">
                                                    <label class="form-check-label" for="factory-2">SamSung</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-3"
                                                        value="Redmi">
                                                    <label class="form-check-label" for="factory-3">Redmi</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-4"
                                                        value="Oppo">
                                                    <label class="form-check-label" for="factory-4">Oppo</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-5"
                                                        value="Huawei">
                                                    <label class="form-check-label" for="factory-5">Huawei</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-6"
                                                        value="ACER">
                                                    <label class="form-check-label" for="factory-6">Acer</label>
                                                </div>

                                            </div>
                                            <div class="col-12" id="screenFilter">
                                                <div class="mb-2"><b>Màn Hình</b></div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="screen-1"
                                                        value="6.">
                                                    <label class="form-check-label" for="screen-1">6.7 inch</label>
                                                </div>

                                                <!-- <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="screen-2"
                                                        value="6.">
                                                    <label class="form-check-label" for="screen-2">6.1 inch</label>
                                                </div> -->
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="screen-3"
                                                        value="4.">
                                                    <label class="form-check-label" for="screen-3">4.7 inch</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="screen-4"
                                                        value="5.">
                                                    <label class="form-check-label" for="screen-4">5.4 inch</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="screen-5"
                                                        value="7.">
                                                    <label class="form-check-label" for="screen-5">7.8 inch</label>
                                                </div>



                                            </div>
                                            <div class="col-12" id="romFilter">
                                                <div class="mb-2"><b>Bộ nhớ trong</b></div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="rom-1"
                                                        value="1TB">
                                                    <label class="form-check-label" for="rom-1">1TB</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="rom-2"
                                                        value="512GB">
                                                    <label class="form-check-label" for="rom-2">512GB</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="rom-3"
                                                        value="256GB">
                                                    <label class="form-check-label" for="rom-3">256GB</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="rom-4"
                                                        value="128GB">
                                                    <label class="form-check-label" for="rom-4">128GB</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="rom-5"
                                                        value="64GB">
                                                    <label class="form-check-label" for="rom-5">64GB</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="rom-6"
                                                        value="32GB">
                                                    <label class="form-check-label" for="rom-6">32GB</label>
                                                </div>

                                            </div>
                                            <div class="col-12" id="priceFilter">
                                                <div class="mb-2"><b>Mức giá</b></div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-2"
                                                        value="duoi-10-trieu">
                                                    <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-3"
                                                        value="10-20-trieu">
                                                    <label class="form-check-label" for="price-3">Từ 10 - 20
                                                        triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-4"
                                                        value="20-30-trieu">
                                                    <label class="form-check-label" for="price-4">Từ 20 - 30
                                                        triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-5"
                                                        value="tren-30-trieu">
                                                    <label class="form-check-label" for="price-5">Trên 30 triệu</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="mb-2"><b>Sắp xếp</b></div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="sort-1"
                                                        value="gia-tang-dan" name="radio-sort">
                                                    <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="sort-2"
                                                        value="gia-giam-dan" name="radio-sort">
                                                    <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="sort-3"
                                                        value="gia-nothing" name="radio-sort" checked>
                                                    <label class="form-check-label" for="sort-3">Không sắp xếp</label>
                                                </div>

                                            </div>
                                            <div class="col-12">
                                                <button
                                                    class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4"
                                                    id="btnFilter">
                                                    Lọc Sản Phẩm
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-8 text-center">
                                        <div class="row g-4">
                                            <c:if test="${totalPages ==  0}">
                                                <div>Không tìm thấy sản phẩm</div>
                                            </c:if>
                                            <c:forEach var="product" items="${products}">
                                                <div class="col-md-6 col-lg-4">
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
                                                                <a href="/product/${product.maSanPham}">
                                                                    ${product.ten}
                                                                </a>

                                                            </h4>
                                                            <p style="font-size: 13px;">
                                                                ${product.moTaNgan}</p>
                                                            <div
                                                                class="d-flex  flex-lg-wrap justify-content-center flex-column">
                                                                <p style="font-size: 15px; text-align: center; width: 100%;"
                                                                    class="text-dark  fw-bold mb-3">
                                                                    <fmt:formatNumber type="number"
                                                                        value="${product.gia}" />
                                                                    đ
                                                                </p>
                                                                <!-- <form action="/add-product-to-cart/${product.maSanPham}"
                                                                    method="post">
                                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                                        value="${_csrf.token}" /> -->

                                                                <button data-product-id="${product.maSanPham}"
                                                                    class="btnAddProductsToCart mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                        class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                    Add to cart
                                                                </button>
                                                                <!-- </form> -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <c:if test="${totalPages > 0}">
                                                <div class="pagination d-flex justify-content-center mt-5">
                                                    <li class="page-item">
                                                        <a class="${1 eq currentPage ? 'disabled page-link' : 'page-link'}"
                                                            href="/products?page=${currentPage - 1}${queryString}"
                                                            aria-label="Previous">
                                                            <span aria-hidden="true">&laquo;</span>
                                                        </a>
                                                    </li>
                                                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                                        <li class="page-item">
                                                            <a class="${(loop.index) eq currentPage ? 'active page-link' : 'page-link'}"
                                                                href="/products?page=${loop.index}${queryString}">
                                                                ${loop.index}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item">
                                                        <a class="${totalPages eq currentPage ? 'disabled page-link' : 'page-link'}"
                                                            href="/products?page=${currentPage + 1}${queryString}"
                                                            aria-label="Next">
                                                            <span aria-hidden="true">&raquo;</span>
                                                        </a>
                                                    </li>

                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Single Product End -->

                    <jsp:include page="../layout/footerclient.jsp" />


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
                    <!-- JavaScript -->
                    <script
                        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
                </body>

                </html>