GET: $(document).ready(function () {
    $("#supplierPersonalList").on('click', 'a[id="supplierProductAdd"]', function (e) {
        var supplierId = $(this).closest('tr[id="supplierPersonalRow"]').children('td[id="supplierId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getSupplierProductAddForm/" + supplierId,
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    $('#supplierPersonalList tbody tr').empty();
                    var key = Object.keys(result);
                    var value = result[key];
                    $.each(value["purchaseProducts"], function (i, productList) {
                        var productId = '<option>' +
                            value['purchaseProducts'][i]['productId'] +
                            '</option>'
                        $('#productIdSelect').append(productId);
                    });
                        $("#supplierId").val(value['supplier']['supplierId']);
                }
            });

    });

    $("#supplierList").on('click', 'a[id="supplierProductAdd"]', function (e) {
        var supplierId = $(this).closest('tr[id="suppliers"]').children('td[id="supplierId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getSupplierProductAddForm/" + supplierId,
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    $('#supplierList tbody tr').empty();
                    var key = Object.keys(result);
                    var value = result[key];
                    $.each(value["purchaseProducts"], function (i, productList) {
                        var productId = '<option>' +
                            value['purchaseProducts'][i]['productId'] +
                            '</option>'
                        $('#productIdSelect').append(productId);
                    });
                    $("#supplierId").val(value['supplier']['supplierId']);
                }
            });

    });





});