GET: $(document).ready( function() {
    getAllSupplierInfo();


    function getAllSupplierInfo() {
        $.ajax({
            type: "GET",
            url: "/getSupplierPersonalList",

            success: function (result) {
                $.each(result, function (i, supplierList) {
                    var user = '<tr id="supplierPersonalRow">' +
                        '<td id="supplierId">' + supplierList.supplierId + '</td>' +
                        '<td id="userProfile">' + supplierList.supplierName + '</a>' + '</td>' +
                        '<td>' + supplierList.supplierType + '</td>' +
                        '<td>' + supplierList.permanentAddress + '</td>' +
                        '<td>' + supplierList.temporaryAddress + '</td>' +
                        '<td id="image">'+"<img src='supplierimagedisplay?supplierId=" +supplierList.supplierId+ "'  alt=\"image-display\" class=\"center\" height=\"200px\" width=\"200px\" style=\"width:50%;\"/>"  + '</td>'+
                         '<td>' + "<a id='supplierProductAdd"+ "' class=\"btn btn-link btn-warning btn-icon btn-sm -upload\"   >"+  '<i class="tim-icons icon-upload">' +  '</i>' + '</a>' + '</td>'+
                          '<td>' + "<a id='supplierPersonalEdit"  + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\"   >" + '<i class="tim-icons icon-pencil">' + '</i>' + '</a>' + '</td>'+
                        '</tr>';
                    // $('#image').html("<img src='supplierimagedisplay?supplierId=" +supplierList.supplierId+ "'  alt=\"image-display\" class=\"center\" height=\"200px\" width=\"200px\" style=\"width:50%;\"/>")
                    $('#supplierPersonalList #supplierPersonalData').append(user);
                });
                // }
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }
});