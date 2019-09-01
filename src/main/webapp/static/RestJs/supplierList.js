GET: $(document).ready( function() {
    getAllUserInfo();
    deleteSupplier();

function getAllUserInfo() {
    $.ajax({
        type: "GET",
        url: "/getListSupplier",
        success: function (result) {
            var key = Object.keys(result);
            var value = result[key];
            console.log(value);
            $.each(value["supplierviews"], function (i, supplierList) {
                var user = '<tr id="suppliers">' +
                    '<td id="supplierId">' + value["supplierviews"][i]["supplierId"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["supplierName"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["supplierType"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["quantity"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["cost"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["buyDate"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["temporaryAddress"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["permanentAddress"] + '</td>' +
                    '<td id="productId">' + value["supplierviews"][i]["productId"] + '</td>' +
                    '<td id="supplierUniqueId" style="display:none;">'+ value["supplierviews"][i]["supplierUniqueId"] +'</td>'+
                    '<td>' + value["supplierviews"][i]["productName"] + '</td>' +
                    '<td>' + value["supplierviews"][i]["username"] + '</td>' +
                    '<td>' + "<a id='supplierProductAdd" + "' class=\"btn btn-link btn-warning btn-icon btn-sm -upload\" >" + '<i class="tim-icons icon-upload">' + '</i>' + '</a>' + '</td>' +
                    '<td>' + "<a id='supplierEdit"+ "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                    '<td>' + "<a id='supplierDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                    '</tr>'
                $('#supplierList #supplerData').append(user);
            });
            var aggregateDatas = '<td>' + value['numberofSupplier'] + '</td>' +
                '<td>' + '' + '</td>' +
                '<td>' + '' + '</td>' +
                '<td>' + value['supplierQuantity'] + '</td>' +
                '<td>' + value['supplierCost'] + '</td>' +
                '<td>' + '' + '</td>' +
                '<td>' + '' + '</td>' +
                '<td>' + '' + '</td>' +
                '<td>' + value['supplierProduct'] + '</td>' +
                '<td>' + '' + '</td>' +
                '<td>' + '' + '</td>'
            $('#supplierList #aggregateTables').append(aggregateDatas);
            $.each(value["supplierViews1"], function (index, supplierName) {

                var distinctName = '<option>' +
                    value['supplierViews1'][index]['supplierName'] +
                    '</option>'
                $('#supplierFormSelect').append(distinctName);
            });

        },
        error: function (e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        },
    });
}
    function deleteSupplier() {
        $('#supplierList').on('click', 'a[id="supplierDelete"]', function (e) {
            var supplierId = $(this).closest('tr[id="suppliers"]').children('td[id="supplierId"]').click().text();
            var productId = $(this).closest('tr[id="suppliers"]').children('td[id="productId"]').click().text();
            $.ajax({
                type: "GET",
                url: "/delete-supplier/" + supplierId + '/' +productId,
                success: function (result) {
                    if (result.status == "success") {
                        swal('Deleted!', 'Your date has been deleted.', 'success')
                        document.location.reload();
                    } else {
                        swal("Sorry", "Your Data hasn't been deleted :", "error");
                    }
                },
                error: function (e) {
                    swal("Sorry", "Your Data hasn't been deleted :", "error");
                    console.log("ERROR: ", e);
                }

            })

        });

    }
});