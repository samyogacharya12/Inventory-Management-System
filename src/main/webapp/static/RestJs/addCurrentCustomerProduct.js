GET: $(document).ready(function () {
    $("#customerList").on('click', 'a[id="addCurrentCustomerProduct"]', function (e) {
        var customerId = $(this).closest('tr[id="customers"]').children('td[id="customerId"]').click().text();
        var productId = $(this).closest('tr[id="customers"]').children('td[id="productId"]').click().text();

    $.ajax(
        {
            type: "GET",
            contentType: "application/json",
            url: "/getExistingCustomerProductForm/" + customerId + '/' + productId,
            dataType: 'json',
            success: function (result) {
                console.log(result);
                $('#customerList tbody tr').empty();
                var key = Object.keys(result);
                var value = result[key];
                $("#currentCustomerId").val(value["customerView"]["customerId"]);
                $.each(value["product"], function (index, supplierList) {
                    var productId= '<option>'+
                                       value["product"][index]+
                                    '</option>';
                    $("#currentProductId").append(productId);
                });

            }
        }
    )
    });
});