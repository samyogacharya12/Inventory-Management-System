GET:$(document).ready(
    function () {
        // GET Request
        $("#supplierDateForm").submit(function (event) {
            event.preventDefault();
            getSupplierDataByDate();
        });

      function  getSupplierDataByDate()
      {
        var supplyStartDate=$("#supplyDateFirst").val();
        var supplyLastDate=$("#supplyDateLast").val();
          $.ajax({
              type: "GET",
              url: "/getSupplierDataByDate/"+ supplyStartDate + '/' +supplyLastDate,
              success: function (result) {
                  console.log(result);
                  $('#supplierList tbody tr').empty();
                  var key=Object.keys(result);
                  var value=result[key];
                  console.log(value);
                  $.each(value["supplierProductByDate"],
                      function (index,products) {
                          var user =
                              '<tr id="searchUser">'+
                              '<td id="supplierId">' + value["supplierProductByDate"][index]["supplierId"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["supplierName"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["supplierType"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["quantity"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["cost"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["buyDate"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["temporaryAddress"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["permanentAddress"] + '</td>' +
                              '<td id="productId">' + value["supplierProductByDate"][index]["productId"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["productName"] + '</td>' +
                              '<td>' + value["supplierProductByDate"][index]["username"] + '</td>' +
                              '<td>' + "<a href='/getSupplierProductAddForm?supplierId=" + value["supplierProductByDate"][index]["supplierId"] + "' class=\"btn btn-link btn-warning btn-icon btn-sm -upload\" >" + '<i class="tim-icons icon-upload">' + '</i>' + '</a>' + '</td>' +
                              '<td>' + "<a href='/getSupplierBySupplierIdAndUniqueId?supplierId=" + value["supplierProductByDate"][index]["supplierId"] + '&' + "supplierUniqueId=" + value["supplierProductByDate"][index]["supplierUniqueId"] + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                              '<td>' + "<a id='supplierDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                              '</tr>';
                          // var user = "Book Name  "
                          //         + book.bookName
                          //         + ", Author  = " + book.author
                          //         + "<br>";
                          $('#supplierList #supplierDataByDate').append(
                              user)
                      });
                  var aggregateDatas = '<td>' + value['numberOfSupplier'] + '</td>' +
                      '<td>' + '' + '</td>' +
                      '<td>' + '' + '</td>' +
                      '<td>' + value['totalQuantity'] + '</td>' +
                      '<td>' + value['totalCost'] + '</td>' +
                      '<td>' + '' + '</td>' +
                      '<td>' + '' + '</td>' +
                      '<td>' + '' + '</td>' +
                      '<td>' + value['numberOfProduct'] + '</td>' +
                      '<td>' + '' + '</td>' +
                      '<td>' + '' + '</td>'
                  $('#supplierList #aggregateTables').append(aggregateDatas);
                  $.each(value["supplierDistinctName"], function (index, supplierName) {

                      var distinctName = '<option>' +
                          value['supplierDistinctName'][index]['supplierName'] +
                          '</option>'
                      $('#supplierFormSelect').append(distinctName);
                  });

              },
              error: function (e) {
                  alert("ERROR: ", e);
                  console.log("ERROR: ", e);
              }
          });
      }

    });