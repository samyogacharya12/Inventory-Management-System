GET: $(document).ready( function(){

    $("#searchTrashForm").submit(function(event) {
        event.preventDefault();
        trashByProductName();
    });

    function trashByProductName() {
        var productName = $("#productName").val();
        $.ajax({
            type: "GET",
            url: "/getTrashByProductName/" + productName,
            data: JSON.stringify(productName),
            success: function (result) {
                $('#trashList tbody tr').empty();
                $.each(result,
                    function (i, trashList) {
                        var trash = '<tr>' +
                            '<td id="trashId">' + trashList.trashId+ '</td>' +
                            '<td>' + trashList.productId  + '</td>' +
                            '<td>' + trashList.productName + '</td>' +
                            '<td>' + trashList.productType + '</td>' +
                            '<td>' + trashList.price + '</td>' +
                            '<td>' + trashList.quantity + '</td>' +
                            '<td>' + trashList.magnifactureDate + '</td>' +
                            '<td>' + trashList.expiryDate + '</td>' +
                             '</tr>'
                $('#trashList #trashDataByProductName').append(trash)
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