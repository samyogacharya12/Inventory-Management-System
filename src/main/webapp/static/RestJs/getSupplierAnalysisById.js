GET: $(document).ready(
    function() {
        // GET REQUEST
        $("#searchSupplierAnalysisForm").submit(function (event) {
            event.preventDefault();
            getDataByName();
        });

        // DO GET
        function getDataByName() {
            var supplierId = $("#supplierIdSearch").val();
            $.ajax({
                type: "GET",
                url: "/getSupplierAnalysisDataBySupplierId/"+supplierId,
                success: function (result) {
                    $('#supplierAnalysisList tbody tr').empty();
                    $.each(result,
                        function (i, supplierAnalysisList) {
                            var product = '<tr>' +
                                '<td id="supplierId">' + supplierAnalysisList.supplierId + '</td>' +
                                '<td id="productId">' + supplierAnalysisList.productId + '</td>' +
                                '<td>' + supplierAnalysisList.productName +  '</td>'+
                                '<td>' + supplierAnalysisList.pastCost + '</td>' +
                                '<td>' + supplierAnalysisList.presentCost + '</td>' +
                                '<td>' + supplierAnalysisList.costIncreament + '</td>' +
                                '<td>' + supplierAnalysisList.costDecreament + '</td>' +
                                '<td>' + supplierAnalysisList.referenceSupplierId + '</td>' +
                                '<td>' + supplierAnalysisList.referenceProductId + '</td>' +
                                '</tr>'
                            $('#supplierAnalysisList #getSupplierByName').append(
                                product)
                        });
                    console.log(result);
                },
                error: function (e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    });
