// // Set new default font family and font color to mimic Bootstrap's default styling
// Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
// Chart.defaults.global.defaultFontColor = '#292b2c';

// // Area Chart Example
// var ctx = document.getElementById("myAreaChart");
// var myLineChart = new Chart(ctx, {
//   type: 'line',
//   data: {
//     labels: ["Mar 1", "Mar 2", "Mar 3", "Mar 4", "Mar 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"],
//     datasets: [{
//       label: "Sessions",
//       lineTension: 0.3,
//       backgroundColor: "rgba(2,117,216,0.2)",
//       borderColor: "rgba(2,117,216,1)",
//       pointRadius: 5,
//       pointBackgroundColor: "rgba(2,117,216,1)",
//       pointBorderColor: "rgba(255,255,255,0.8)",
//       pointHoverRadius: 5,
//       pointHoverBackgroundColor: "rgba(2,117,216,1)",
//       pointHitRadius: 50,
//       pointBorderWidth: 2,
//       data: [10000, 30162, 26263, 18394, 18287, 28682, 31274, 33259, 25849, 24159, 32651, 31984, 38451],
//     }],
//   },
//   options: {
//     scales: {
//       xAxes: [{
//         time: {
//           unit: 'date'
//         },
//         gridLines: {
//           display: false
//         },
//         ticks: {
//           maxTicksLimit: 7
//         }
//       }],
//       yAxes: [{
//         ticks: {
//           min: 0,
//           max: 40000,
//           maxTicksLimit: 5
//         },
//         gridLines: {
//           color: "rgba(0, 0, 0, .125)",
//         }
//       }],
//     },
//     legend: {
//       display: false
//     }
//   }
// });

// document.addEventListener('DOMContentLoaded', function () {
//   const ctx = document.getElementById("myAreaChart");
//   if (!ctx || typeof chartLabels === 'undefined' || typeof chartData === 'undefined') return;

//   new Chart(ctx, {
//     type: 'line',
//     data: {
//       labels: chartLabels,
//       datasets: [{
//         label: "Doanh thu theo ngày (VND)",
//         data: chartData,
//         lineTension: 0.3,
//         backgroundColor: "rgba(2,117,216,0.2)",
//         borderColor: "rgba(2,117,216,1)",
//         pointRadius: 5,
//         pointBackgroundColor: "rgba(2,117,216,1)",
//         pointBorderColor: "rgba(255,255,255,0.8)",
//         pointHoverRadius: 5,
//         pointHoverBackgroundColor: "rgba(2,117,216,1)",
//         pointHitRadius: 50,
//         pointBorderWidth: 2
//       }]
//     },
//     options: {
//       responsive: true,
//       scales: {
//         x: {
//           title: {
//             display: true,
//             text: 'Ngày'
//           }
//         },
//         y: {
//           beginAtZero: true,
//           title: {
//             display: true,
//             text: 'Doanh thu (VND)'
//           },
//           ticks: {
//             callback: function (value) {
//               return value.toLocaleString() + " VND"; // Định dạng số với dấu phẩy
//             }
//           }
//         }
//       },
//       plugins: {
//         legend: {
//           display: true
//         }
//       }
//     }
//   });
// });


document.addEventListener('DOMContentLoaded', function () {
  const ctx = document.getElementById("myAreaChart");
  if (!ctx || typeof chartLabels === 'undefined' || typeof chartData === 'undefined') return;

  // Thiết lập chiều rộng canvas theo số lượng dữ liệu
  const pointWidth = 150; // Mỗi điểm dữ liệu chiếm 80px
  ctx.width = chartLabels.length * pointWidth;
  ctx.height = 400;

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: chartLabels,
      datasets: [{
        label: "Doanh thu theo ngày (VND)",
        data: chartData,
        lineTension: 0.3,
        backgroundColor: "rgba(2,117,216,0.2)",
        borderColor: "rgba(2,117,216,1)",
        pointRadius: 5,
        pointBackgroundColor: "rgba(2,117,216,1)",
        pointBorderColor: "rgba(255,255,255,0.8)",
        pointHoverRadius: 5,
        pointHoverBackgroundColor: "rgba(2,117,216,1)",
        pointHitRadius: 50,
        pointBorderWidth: 2
      }]
    },
    options: {
      responsive: false, // Ngăn Chart.js tự resize
      maintainAspectRatio: false,
      scales: {
        x: {
          title: {
            display: true,
            text: 'Ngày'
          }
        },
        y: {
          beginAtZero: true,
          title: {
            display: true,
            text: 'Doanh thu (VND)'
          },
          ticks: {
            callback: function (value) {
              return new Intl.NumberFormat('vi-VN').format(value) + " VND";
            }
          }
        }
      },
      plugins: {
        legend: {
          display: true
        }
      }
    }
  });
});







