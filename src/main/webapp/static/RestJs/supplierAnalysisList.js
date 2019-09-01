GET: $(document).ready( function() {
    supplierProductAnalysisList();

    function supplierProductAnalysisList() {
        $.ajax({
            type: "GET",
            url: "/getSupplierProductAnalysisList",
            success: function (result) {
                $.each(result, function (i, supplierAnalysisList) {
                    var product = '<tr id="supplierAnalysisRow">' +
                        '<td id="supplierId">' + supplierAnalysisList.supplierId + '</td>' +
                        '<td id="productId">' + supplierAnalysisList.productId + '</td>' +
                        '<td>' +supplierAnalysisList.productName +  '</td>'+
                        '<td>' + supplierAnalysisList.pastCost + '</td>' +
                        '<td>' + supplierAnalysisList.presentCost + '</td>' +
                        '<td>' + supplierAnalysisList.costIncreament + '</td>' +
                        '<td>' + supplierAnalysisList.costDecreament + '</td>' +
                        '<td>' + supplierAnalysisList.referenceSupplierId + '</td>' +
                        '<td>' + supplierAnalysisList.referenceProductId + '</td>' +
                        '</tr>'
                    $('#supplierAnalysisList #supplierAnalysisData').append(product);
                });
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }
});