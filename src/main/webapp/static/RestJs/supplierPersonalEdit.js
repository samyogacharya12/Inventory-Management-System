GET: $(document).ready(function () {
    $("#supplierPersonalList").on('click', 'a[id="supplierPersonalEdit"]', function (e) {
        var supplierId = $(this).closest('tr[id="supplierPersonalRow"]').children('td[id="supplierId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getSupplierEditForm/"+supplierId,
                dataType: 'json',
                success: function (result) {
                    if (result.status = "success") {
                        $('#supplierPersonalList tbody tr').empty();
                        $.each(result,
                            function (index, supplier) {
                                $("#supplierIdPersonal").val(supplier.supplierId);
                                $("#supplierNamePersonal").val(supplier.supplierName);
                                $("#supplierTypePersonal").val(supplier.supplierType);
                                $("#supplierPermanentAddressPersonal").val(supplier.permanentAddress);
                                $("#supplierTemporaryAddressPersonal").val(supplier.temporaryAddress);
                                $("#supplierImage").val(supplier.image);
                            })

                    }
                }
            });

    })
});