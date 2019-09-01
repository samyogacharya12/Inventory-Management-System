GET: $(document).ready(
    function() {
        // GET REQUEST
        $("#searchProductAnalysisForm").submit(function (event) {
            event.preventDefault();
            getDataByName();
        });

        // DO GET
        function getDataByName() {
            var productName = $("#productName").val();
            $.ajax({
                type: "GET",
                url: "/getDataByName/" + productName,
                data: JSON.stringify(productName),
                success: function (result) {
                    $('#productAnalysisList tbody tr').empty();
                    $.each(result.data,
                        function (i, productAnalysisList) {
                            var user = '<tr>' +
                                '<td>'+ productAnalysisList.productId + '</td>'+
                                '<td>'+ productAnalysisList.productName+  '</td>'+
                                '<td>' + productAnalysisList.pastPrice + '</td>'+
                                '<td>' + productAnalysisList.presentPrice + '</td>' +
                                '<td>' + productAnalysisList.priceIncreament + '</td>'+
                                '<td>'+  productAnalysisList.priceDecreament +'</td>'+
                                '<td>' + productAnalysisList.referenceProductId + '</td>'+
                                '</tr>'
                            $('#productAnalysisList #getProductByName').append(
                                user)
                        });
                    console.log(result.data);
                },
                error: function (e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    });
