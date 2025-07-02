// // Set new default font family and font color to mimic Bootstrap's default styling
// Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
// Chart.defaults.global.defaultFontColor = '#292b2c';

// // Bar Chart Example
// var ctx = document.getElementById("myBarChart");
// var myLineChart = new Chart(ctx, {
//   type: 'bar',
//   data: {
//     labels: ["January", "February", "March", "April", "May", "June"],
//     datasets: [{
//       label: "Revenue",
//       backgroundColor: "rgba(2,117,216,1)",
//       borderColor: "rgba(2,117,216,1)",
//       data: [4215, 5312, 6251, 7841, 9821, 14984],
//     }],
//   },
//   options: {
//     scales: {
//       xAxes: [{
//         time: {
//           unit: 'month'
//         },
//         gridLines: {
//           display: false
//         },
//         ticks: {
//           maxTicksLimit: 6
//         }
//       }],
//       yAxes: [{
//         ticks: {
//           min: 0,
//           max: 15000,
//           maxTicksLimit: 5
//         },
//         gridLines: {
//           display: true
//         }
//       }],
//     },
//     legend: {
//       display: false
//     }
//   }
// });

// document.addEventListener('DOMContentLoaded', function () {
//   const ctx = document.getElementById("myBarChart");
//   if (!ctx || typeof chartLabelss === 'undefined' || typeof chartDatas === 'undefined') return;

//   Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
//   Chart.defaults.global.defaultFontColor = '#292b2c';

//   new Chart(ctx, {
//     type: 'bar',
//     data: {
//       labels: chartLabelss, // Ví dụ: ["2024-01", "2024-02", ...]
//       datasets: [{
//         label: "Doanh thu theo tháng (VND)",
//         backgroundColor: "rgba(2,117,216,1)",
//         borderColor: "rgba(2,117,216,1)",
//         data: chartDatas // Ví dụ: [4215, 5312, 6251, ...]
//       }]
//     },
//     options: {
//       responsive: true,
//       scales: {
//         xAxes: [{
//           gridLines: {
//             display: false
//           },
//           ticks: {
//             maxTicksLimit: 12
//           }
//         }],
//         yAxes: [{
//           ticks: {
//             beginAtZero: true,
//             callback: function (value) {
//               return value.toLocaleString() + " VND";
//             }
//           },
//           gridLines: {
//             display: true
//           }
//         }]
//       },
//       legend: {
//         display: true
//       }
//     }
//   });
// });


document.addEventListener('DOMContentLoaded', function () {
  const ctx = document.getElementById("myBarChart");

  if (!ctx || typeof chartLabelss === 'undefined' || typeof chartDatas === 'undefined') return;

  // Tính chiều rộng canvas dựa trên số lượng cột (ví dụ: 100px mỗi cột)
  const columnWidth = 100;
  ctx.width = chartLabelss.length * columnWidth;
  ctx.height = 400; // Chiều cao cố định (tùy chỉnh theo ý bạn)

  // Chart.js global options
  Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
  Chart.defaults.global.defaultFontColor = '#292b2c';

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: chartLabelss,
      datasets: [{
        label: "Doanh thu theo tháng (VND)",
        backgroundColor: "rgba(2,117,216,1)",
        borderColor: "rgba(2,117,216,1)",
        data: chartDatas
      }]
    },
    options: {
      responsive: false, // ⛔ Rất quan trọng: Không để Chart tự resize
      maintainAspectRatio: false,
      scales: {
        xAxes: [{
          gridLines: {
            display: false
          },
          ticks: {
            maxTicksLimit: 12
          }
        }],
        yAxes: [{
          ticks: {
            beginAtZero: true,
            callback: function (value) {
              return value.toLocaleString() + " VND";
            }
          },
          gridLines: {
            display: true
          }
        }]
      },
      legend: {
        display: true
      }
    }
  });
});
