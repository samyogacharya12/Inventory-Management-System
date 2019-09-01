GET: $(document).ready(function () {
     $("#productList").on('click', 'a[id="productEdit"]', function (e) {
         var productId = $(this).closest('tr[id="products"]').children('td[id="productId"]').click().text();
         $.ajax(
             {
                 type: "GET",
                 contentType: "application/json",
                 url: "/getProductEditForm/"+productId,
                 dataType: 'json',
                 success: function (result) {
                     $('#productList tbody tr').empty();
                     $.each(result, function (index, products) {
                         $("#productId").val(products.productId);
                         $("#productNameEdit").val(products.productName);
                         $("#productType").val(products.productType);
                         $("#quantity").val(products.quantity);
                         $("#price").val(products.price);
                         $("#magnifactureDate").val(products.magnifactureDate);
                         $("#expiryDate").val(products.expiryDate);
                         $("#file").val(products.file);

                     })
                 }
             }
         )


     })
});