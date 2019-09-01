GET: $(document).ready( function() {
    getAllTrashInfo();


    function getAllTrashInfo() {
        $.ajax({
            type: "GET",
            url: "/getListTrash",
            success: function (result) {
                console.log(result);
                $.each(result, function (i, trashList) {
                    var trash = '<tr id="products">' +
                        '<td id="trashId">' + trashList.trashId+ '</td>' +
                        '<td>' + trashList.productId  + '</td>' +
                        '<td>' + trashList.productName + '</td>' +
                        '<td>' + trashList.productType + '</td>' +
                        '<td>' + trashList.price + '</td>' +
                        '<td>' + trashList.quantity + '</td>' +
                        '<td>' + trashList.magnifactureDate + '</td>' +
                        '<td>' + trashList.expiryDate + '</td>' +
                        '</tr>'
                    $('#trashList #trashData').append(trash);
                });

            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }
});