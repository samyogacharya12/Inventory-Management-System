GET: $(document).ready( function() {
    getAllCustomerInfo();
    deleteCustomer();



    function getAllCustomerInfo() {
        $.ajax({
            type: "GET",
            url: "/getListCustomer",
            success: function (result) {
                var key = Object.keys(result);
                var value = result[key];
                console.log(value);
                $.each(value["customerView"], function (i, customerList) {
                    var user = '<tr id="customers">' +
                        '<td id="customerId">' + value["customerView"][i]["customerId"] + '</td>' +
                        '<td>' + value["customerView"][i]["customerName"] + '</td>' +
                        '<td>' + value["customerView"][i]["quantity"] + '</td>' +
                        '<td>' + value["customerView"][i]["amount"] + '</td>' +
                        '<td>' + value["customerView"][i]["buyDate"] + '</td>' +
                        '<td>' + value["customerView"][i]["permanentAddress"] + '</td>' +
                        '<td>' + value["customerView"][i]["temporaryAddress"] + '</td>' +
                        '<td>' + value["customerView"][i]["phoneNumber"] + '</td>' +
                        '<td id="productId">' + value["customerView"][i]["productId"] + '</td>' +
                        '<td id="customerPurchaseId" style="display:none;">' + value["customerView"][i]["customerPurchaseId"] + '</td>' +
                        '<td>' + value["customerView"][i]["productName"] + '</td>' +
                        '<td>' + value["customerView"][i]["username"] + '</td>' +
                        '<td>' + "<a id='addCurrentCustomerProduct"  +"' >"+ "<i class='tim-icons icon-upload'>" + "</i>" + "</a>" + '</td>'+
                        '<td>' + "<a id='customerEdit" + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                        '<td>' + "<a id='customerDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                        '</tr>';
                    $('#customerList #customerData').append(user);
                });
                var aggregateDatas = '<td>' +value["countNoOfCustomer"]  + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + value['sumOfQuantity'] + '</td>' +
                    '<td>' + value['sumOfAmount'] + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + value['countNoOfProduct'] + '</td>' +
                    '<td>' + '' + '</td>'
                $('#customerList #aggregateTables').append(aggregateDatas);

            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }
    function deleteCustomer() {
        $('#customerList').on('click', 'a[id="customerDelete"]', function (e) {
            var customerId = $(this).closest('tr[id="customers"]').children('td[id="customerId"]').click().text();
            var productId = $(this).closest('tr[id="customers"]').children('td[id="productId"]').click().text();
            $.ajax({
                type: "GET",
                url: "/deleteCustomer/" + customerId + '/' +productId,
                success: function (result) {
                    if (result.status == "success") {
                        swal('Deleted!', 'Your date has been deleted.', 'success')
                        document.location.reload();
                    } else {
                        swal("Sorry", "Your Data hasn't been deleted :", "error");
                    }
                },
                error: function (e) {
                    swal("Sorry", "Your Data hasn't been deleted :", "error");
                    console.log("ERROR: ", e);
                }

            })

        });

    }
});