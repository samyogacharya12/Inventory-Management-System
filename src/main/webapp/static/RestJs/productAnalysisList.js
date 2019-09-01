GET: $(document).ready( function() {
    productAnalysisList();

    function productAnalysisList() {
        $.ajax({
            type: "GET",
            url: "/getListProductAnalysis",
            success: function (result) {
                $.each(result, function (i, productList) {
                    var product = '<tr id="products">' +
                        '<td id="productId">' + productList.productId + '</td>' +
                        '<td>' +productList.productName +  '</td>'+
                        '<td>' + productList.pastPrice + '</td>' +
                        '<td>' + productList.presentPrice + '</td>' +
                        '<td>' + productList.priceIncreament + '</td>' +
                        '<td>' + productList.priceDecreament + '</td>' +
                        '<td>' + productList.referenceProductId + '</td>' +
                        '</tr>'
                    $('#productAnalysisList #productData').append(product);
                });
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }
});