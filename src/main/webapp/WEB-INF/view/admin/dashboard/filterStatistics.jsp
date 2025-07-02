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
                <title>Dashboard-Thống kê</title>
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>

            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Dashboard</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active">Thống kê</li>
                                </ol>
                                <div class="row">
                                    <div class="col-xl-3 col-md-6">
                                        <div class="card bg-primary text-white mb-4">
                                            <div class="card-body">Số lượng người dùng (${countUser})</div>
                                            <div class="card-footer d-flex align-items-center justify-content-between">
                                                <a class="small text-white stretched-link" href="/admin/user">View
                                                    Details</a>
                                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-3 col-md-6">
                                        <div class="card bg-warning text-white mb-4">
                                            <div class="card-body">Số lượng sản phẩm (${countProduct})</div>
                                            <div class="card-footer d-flex align-items-center justify-content-between">
                                                <a class="small text-white stretched-link" href="/admin/product">View
                                                    Details</a>
                                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-3 col-md-6">
                                        <div class="card bg-success text-white mb-4">
                                            <div class="card-body">Số lượng đơn hàng (${countOrder})</div>
                                            <div class="card-footer d-flex align-items-center justify-content-between">
                                                <a class="small text-white stretched-link" href="/admin/order">View
                                                    Details</a>
                                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-3 col-md-6">
                                        <div class="card bg-danger text-white mb-4">
                                            <div class="card-body">Số lượng đánh giá (${countReview})</div>
                                            <div class="card-footer d-flex align-items-center justify-content-between">
                                                <a class="small text-white stretched-link" href="/admin/review">View
                                                    Details</a>
                                                <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form class="mb-4" method="get" action="/admin/statistics/by-date">
                                    Từ: <input type="date" name="start" value="${start}">
                                    Đến: <input type="date" name="end" value="${end}">
                                    <button type="submit">Lọc</button>
                                </form>

                                <div class="row">
                                    <div class="col-xl-12 col-md-6">
                                        <div class="card mb-4" id="reportContentFilter">
                                            <div class="card-header">
                                                <i class="fas fa-chart-bar me-1"></i>
                                                Bar Chart
                                            </div>
                                            <div class="card-body" style="overflow-x: auto;"><canvas id="revenueChart"
                                                    width="100%" height="40"></canvas></div>
                                        </div>
                                        <button onclick="downloadPDFFilter()">In PDF</button>
                                    </div>
                                </div>

                                <c:if test="${not empty filteredRevenue}">

                                    <script>
                                        const labels = [
                                            <c:forEach var="item" items="${filteredRevenue}" varStatus="status">
                                                '${item.time}'<c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        ];

                                        const dataPoints = [
                                            <c:forEach var="item" items="${filteredRevenue}" varStatus="status">
                                                ${item.revenue}<c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                        ];

                                        const barWidth = 100; // Độ rộng mỗi cột (pixel)
                                        const chartWidth = labels.length * barWidth;
                                        const canvas = document.getElementById('revenueChart');
                                        canvas.width = chartWidth;
                                        canvas.height = 400; // Chiều cao cố định

                                        const data = {
                                            labels: labels,
                                            datasets: [{
                                                label: 'Doanh thu (VND)',
                                                data: dataPoints,
                                                backgroundColor: 'rgba(2,117,216,1)',
                                                borderColor: 'rgba(2,117,216,1)',
                                                borderWidth: 1
                                            }]
                                        };

                                        const config = {
                                            type: 'bar',
                                            data: data,
                                            options: {
                                                responsive: false, // QUAN TRỌNG: không để Chart.js co dãn
                                                maintainAspectRatio: false,
                                                scales: {
                                                    y: {
                                                        beginAtZero: true,
                                                        title: {
                                                            display: true,
                                                            text: 'Doanh thu (VND)'
                                                        },
                                                        ticks: {
                                                            callback: function (value) {
                                                                return new Intl.NumberFormat('vi-VN').format(value) + ' VND';
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        };

                                        new Chart(canvas, config);
                                    </script>
                                </c:if>

                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                    crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
                    crossorigin="anonymous"></script>
            </body>

            </html>