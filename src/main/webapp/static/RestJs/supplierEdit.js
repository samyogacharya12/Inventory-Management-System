GET: $(document).ready(function () {
    $("#supplierList").on('click', 'a[id="supplierEdit"]', function (e) {
        var supplierId = $(this).closest('tr[id="suppliers"]').children('td[id="supplierId"]').click().text();
        var supplierUniqueId = $(this).closest('tr[id="suppliers"]').children('td[id="supplierUniqueId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getSupplierBySupplierIdAndUniqueId/" + supplierId + '/' + supplierUniqueId,
                dataType: 'json',
                success: function (result) {
                    $('#supplierList tbody tr').empty();
                    $.each(result,
                        function (index, supplier) {
                            $("#updateSupplierId").val(supplier.supplierId);
                            $("#supplierUniqueId").val(supplier.supplierUniqueId);
                            $("#supplierName").val(supplier.supplierName);
                            $("#supplierType").val(supplier.supplierType);
                            $("#permanentAddress").val(supplier.permanentAddress);
                            $("#temporaryAddress").val(supplier.temporaryAddress);
                            $("#email").val(supplier.email);
                            $("#file").val(supplier.file);
                        });

                }
            });
    });

    $("#supplierProductInfoButton").click(function () {
    var supplierId=$("#updateSupplierId").val();
      var supplierUniqueId=  $("#supplierUniqueId").val();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getSupplierProductEditForm/"+supplierId+'/'+supplierUniqueId,
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    $('#supplierList tbody tr').empty();
                    var key = Object.keys(result);
                    var value = result[key];
                    $("#supplierProductId").val(value["supplierView"]["supplierId"]);
                    $("#quantity").val(value["supplierView"]["quantity"]);
                    $("#supplierProductCost").val(value["supplierView"]["cost"]);
                    $("#supplierProductBuyDate").val(value["supplierView"]["buyDate"]);
                    var supplierProductId='<option>' +
                                   value["supplierView"]["productId"]+
                                   '</option>';
                    $("#productId").append(supplierProductId);

                    $.each(value["purchaseProducts"], function (i, supplierList) {
                        var remainingProducts='<option>'+
                                                        value["purchaseProducts"][i]["productId"]+
                                                       '</option>';
                        $("#productId").append(remainingProducts);
                    });

                }
            });
    });
});