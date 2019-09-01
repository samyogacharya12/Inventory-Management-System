GET: $(document).ready(function () {
    $("#customerList").on('click', 'a[id="customerEdit"]', function (e) {
        var customerId = $(this).closest('tr[id="customers"]').children('td[id="customerId"]').click().text();
        var productId = $(this).closest('tr[id="customers"]').children('td[id="productId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getCustomerPersonalEditForm/" + customerId + '/'+ productId,
                dataType: 'json',
                success: function (result) {
                    $('#customerList tbody tr').empty();
                    $.each(result,
                        function (index, customer) {
                        console.log(result);
                            $("#customerIdPersonal").val(customer.customerId);
                            $("#productIdPersonal").val(customer.productId);
                            $("#customerNamePersonal").val(customer.customerName);
                            $("#permanentAddressPersonal").val(customer.permanentAddress);
                            $("#temporaryAddressPersonal").val(customer.temporaryAddress);
                            $("#phoneNumber").val(customer.phoneNumber);
                            // $("#email").val(customer.email);
                            // $("#buyDate").val(customer.buyDate);
                            // $("#country").val(customer.country);
                        });

                }
            });
    });

    $("#customerProductInfoButton").click(function () {
        var customerId=$("#customerIdPersonal").val();
        var productId=  $("#productIdPersonal").val();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getCustomerProductEditForm/"+customerId+'/'+productId,
                dataType: 'json',
                success: function (result) {
                    $.each(result,
                        function (index, customer) {
                            console.log(result);
                            $('#customerList tbody tr').empty();
                            $("#customerIdProduct").val(customer.customerId);
                            $("#productId").val(customer.productId);
                            $("#customerPurchaseId").val(customer.customerPurchaseId);
                            $("#quantityCustomerProduct").val(customer.quantity);
                            $("#buyDateCustomerProduct").val(customer.buyDate);
                            $("#amount").val(customer.amount);
                        });
                }
            });
    });
});