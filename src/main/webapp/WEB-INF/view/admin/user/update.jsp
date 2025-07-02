<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>datatables-demo
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Cập nhật người dùng</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
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
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Quản lý người dùng</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active"><a href="/admin/user">Người dùng</a></li>
                                    <li class="breadcrumb-item active">Cập nhật người dùng</li>
                                </ol>
                                <div>
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3>Cập nhật người dùng</h3>
                                            <hr />
                                            <form:form method="post" action="/admin/user/update"
                                                modelAttribute="updateUser" enctype="multipart/form-data">
                                                <div class="mb-3" style="display: none;">
                                                    <label class="form-label">ID</label>
                                                    <form:input type="text" path="maNguoiDung" Class="form-control" />
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Email</label>
                                                    <form:input type="email" path="email" Class="form-control"
                                                        disabled="true" />
                                                </div>

                                                <div class="mb-3">
                                                    <c:set var="errorPhone">
                                                        <form:errors path="sdt" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Số điện thoại</label>
                                                    <form:input type="text" path="sdt"
                                                        Class="form-control ${not empty errorPhone ? 'is-invalid' : ''}" />
                                                    ${errorPhone}
                                                </div>

                                                <div class="mb-3">
                                                    <c:set var="errorName">
                                                        <form:errors path="hoTen" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Họ tên</label>
                                                    <form:input type="text" path="hoTen"
                                                        Class="form-control ${not empty errorName ? 'is-invalid' : ''}" />
                                                    ${errorName}
                                                </div>

                                                <div class="mb-3 col-12">
                                                    <c:set var="errorAddress">
                                                        <form:errors path="diaChi" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Địa chỉ</label>
                                                    <form:input type="text" path="diaChi"
                                                        Class="form-control ${not empty errorAddress ? 'is-invalid' : ''}" />
                                                    ${errorAddress}
                                                </div>

                                                <div class="row">
                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label for="avatarFile" class="form-label">Ảnh đại diện</label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            name="hoidanitFile" accept=".png, .jpg, .jpeg" />
                                                    </div>

                                                    <div class="mb-3 col-12 col-md-6">
                                                        <label class="form-label">Vai Trò</label>
                                                        <form:select class="form-select" path="vaiTro.ten">
                                                            <form:option value="ADMIN">ADMIN</form:option>
                                                            <form:option value="USER">USER</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>

                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px; display: none;" alt="avatar preview"
                                                        id="avatarPreview">
                                                </div>

                                                <button type="submit" class="btn btn-warning mb-3">Cập nhật</button>
                                            </form:form>
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