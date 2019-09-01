GET:$(document).ready(
    function () {
        // GET Request
        $("#customerDateForm").submit(function (event) {
            event.preventDefault();
            getCustomerDataByDate();
        });

        function  getCustomerDataByDate()
        {
            var sellStartDate=$("#sellStartDate").val();
            var sellLastDate=$("#sellLastDate").val();
            $.ajax({
                type: "GET",
                url: "/getCustomerDataByDate/"+sellStartDate+'/'+sellLastDate,
                success: function (result) {
                    console.log(result);
                    $('#customerList tbody tr').empty();
                    var key=Object.keys(result);
                    var value=result[key];
                    $.each(value["customerProductByDate"],
                        function (index,products) {
                            console.log(value["customerProductByDate"][index]["customerId"]);
                            var user =
                                '<tr>'+
                                '<td id="customerId">' + value["customerProductByDate"][index]["customerId"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["customerName"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["quantity"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["amount"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["buyDate"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["permanentAddress"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["temporaryAddress"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["phoneNumber"] + '</td>' +
                                '<td id="productId">' + value["customerProductByDate"][index]["productId"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["productName"] + '</td>' +
                                '<td>' + value["customerProductByDate"][index]["username"] + '</td>' +
                                '<td>' + "<a href='/getCustomerProductForm?customerId="+ value["customerProductByDate"][index]["customerId"] + "&" + "productId=" +value["customerProductByDate"][index]["productId"]  +"' >"+ "<i class='tim-icons icon-upload'>" + "</i>" + "</a>" + '</td>'+
                                '<td>' + "<a href='/getCustomerEditForm?customerId=" + value["customerProductByDate"][index]["customerId"] + "&" + "productId=" +value["customerProductByDate"][index]["productId"] + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                                '<td>' + "<a id='customerDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                                '</tr>';
                            // var user = "Book Name  "
                            //         + book.bookName
                            //         + ", Author  = " + book.author
                            //         + "<br>";
                            $('#customerList #getCustomerDataByDate ').append(
                                user)
                        });
                    var aggregateDatas = '<td>' +value["numberOfCustomer"]  + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + value['totalQuantity'] + '</td>' +
                        '<td>' + value['totalAmount'] + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + value['numberOfProduct'] + '</td>' +
                        '<td>' + '' + '</td>'
                    $('#customerList #aggregateTables').append(aggregateDatas);

                },
                error: function (e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }

    });