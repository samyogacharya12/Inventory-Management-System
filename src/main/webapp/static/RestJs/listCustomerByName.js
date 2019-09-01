GET: $(document).ready(
    function() {
        // GET REQUEST
        $("#searchCustomer").submit(function (event) {
            event.preventDefault();
            getCustomerByName();
        });

        // DO GET
        function getCustomerByName() {
            var customerName = $("#customerName").val();
            $.ajax({
                type: "GET",
                url: "/getCustomerByName/" + customerName,
                data: JSON.stringify(customerName),
                success: function (result) {
                    var key=Object.keys(result);
                    var value=result[key];
                    console.log(value);
                    $('#customerList tbody tr').empty();
                    $.each(value["findCustomers"],
                        function (i, customers) {
                            var user = '<tr>'+
                                '<td>'+ value["findCustomers"][i]["customerId"]+ '</td>'+
                                '<td>'+ value["findCustomers"][i]["customerName"]+  '</td>'+
                                '<td>' + value["findCustomers"][i]["quantity"] + '</td>'+
                                '<td>' + value["findCustomers"][i]["amount"] + '</td>' +
                                '<td>' + value["findCustomers"][i]["buyDate"] + '</td>'+
                                '<td>'+  value["findCustomers"][i]["permanentAddress"] +'</td>'+
                                '<td>' + value["findCustomers"][i]["temporaryAddress"] + '</td>'+
                                '<td>'+  value["findCustomers"][i]["phoneNumber"] + '</td>'+
                                '<td>' + value["findCustomers"][i]["productId"] + '</td>'+
                                '<td>' + value["findCustomers"][i]["productName"] + '</td>'+
                                '<td>' + "<a href='/getCustomerProductForm?customerId="+ value["findCustomers"][i]["customerId"] + "&" + "productId=" +value["findCustomers"][i]["productId"]  +"' >"+ "<i class='tim-icons icon-upload'>" + "</i>" + "</a>" + '</td>'+
                                '<td>' + "<a href='/getCustomerEditForm?customerId=" + value["findCustomers"][i]["customerId"] + "&" + "productId=" +value["findCustomers"][i]["productId"] + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                                '<td>' + "<a id='customerDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                                '</tr>'
                            $('#customerList #getCustomerByName').append(
                                user)
                        });
                    var aggregateDatas = '<td>' +value["totalCustomer"]  + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + value['totalQuantity'] + '</td>' +
                        '<td>' + value['totalAmount'] + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + value['totalProduct'] + '</td>' +
                        '<td>' + '' + '</td>'
                    $('#customerList #aggregateTables').append(aggregateDatas);
                    console.log(result.data);
                },
                error: function (e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    });
