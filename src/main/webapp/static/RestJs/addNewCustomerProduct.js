GET: $(document).ready( function(){
    $(function () {
        $.ajax(
            {
                type: "GET",
                url: "/getNewCustomerProductForm",
                success: function (result) {
                    $.each(result, function (index, customer) {
                        var customerProduct = '<option>' +
                            customer.productId +
                            '</option>';
                        $("#productIdAdd").append(customerProduct);
                    });
                }
            }
        )
    });
});