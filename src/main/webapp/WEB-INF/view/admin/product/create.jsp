<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>datatables-demo
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Manager Product</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
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
                                <h1 class="mt-4">Manager product</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active"><a href="/admin/product">Product</a></li>
                                    <li class="breadcrumb-item active">Create product</li>
                                </ol>
                                <div>
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3>Create a product</h3>
                                            <hr />
                                            <form:form method="post" action="/admin/product/create"
                                                modelAttribute="newProduct" enctype="multipart/form-data" class="row">
                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="errorName">
                                                        <form:errors path="ten" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Name</label>
                                                    <form:input type="text" path="ten"
                                                        Class="form-control ${not empty errorName ? 'is-invalid' : ''}" />
                                                    ${errorName}
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="errorPrice">
                                                        <form:errors path="gia" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">price</label>
                                                    <form:input type="text" path="gia"
                                                        Class="form-control ${not empty errorPrice ? 'is-invalid' : ''}" />
                                                    ${errorPrice}
                                                </div>

                                                <div class="mb-3 col-12">
                                                    <c:set var="errorDetailD">
                                                        <form:errors path="moTaChiTiet" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Detail Description</label>
                                                    <form:input type="text" path="moTaChiTiet"
                                                        Class="form-control ${not empty errorDetailD ? 'is-invalid' : ''}" />
                                                    ${errorDetailD}
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="errorShortD">
                                                        <form:errors path="moTaNgan" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Short Description</label>
                                                    <form:input type="text" path="moTaNgan"
                                                        Class="form-control ${not empty errorShortD ? 'is-invalid' : ''}" />
                                                    ${errorShortD}
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <c:set var="errorQuantity">
                                                        <form:errors path="soLuong" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Quantity</label>
                                                    <form:input type="number" path="soLuong"
                                                        Class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}" />
                                                    ${errorQuantity}
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Factory</label>
                                                    <form:select class="form-select" path="noiSanXuat">
                                                        <form:option value="Iphone">Iphone</form:option>
                                                        <form:option value="SamSung">SamSung</form:option>
                                                        <form:option value="Redmi">Redmi</form:option>
                                                        <form:option value="Oppo">Oppo</form:option>
                                                        <form:option value="Huawei">Huawei</form:option>
                                                    </form:select>

                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Screen</label>
                                                    <form:input type="text" path="manHinh" Class="form-control" />

                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="avatarFile" class="form-label">Image</label>
                                                    <input class="form-control" type="file" id="avatarFile"
                                                        name="hoidanitFile" accept=".png, .jpg, .jpeg" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Rom</label>
                                                    <form:input type="text" path="boNho" Class="form-control" />
                                                </div>
                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px; display: none;" alt="avatar preview"
                                                        id="avatarPreview">
                                                </div>
                                                <div class="col-12 mb-5">
                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                </div>

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