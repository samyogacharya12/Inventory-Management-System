GET: $(document).ready( function(){

    $("#supplierPersonalSearchDataForm").submit(function(event) {
        event.preventDefault();
        getSupplierPersonalData();
    });

    function getSupplierPersonalData() {
        var supplierName = $("#supplierUsername").val();
        $.ajax({
            type: "GET",
            url: "/gePersonalSupplierByName/" + supplierName,
            data: JSON.stringify(supplierName),
            success: function (result) {
                $('#supplierPersonalList tbody tr').empty();
                var user = '<tr id="suppliers">' +
                    '<td id="supplierId">' + result.data.supplierId + '</td>' +
                    '<td id="userProfile">' + result.data.supplierName  + '</td>' +
                    '<td>' + result.data.supplierType + '</td>' +
                    '<td>' + result.data.permanentAddress + '</td>' +
                    '<td>' + result.data.temporaryAddress + '</td>' +
                    '<td id="image">'+"<img src='supplierimagedisplay?supplierId=" +result.data.supplierId+ "'  alt=\"image-display\" class=\"center\" height=\"200px\" width=\"200px\" style=\"width:50%;\"/>"  + '</td>'+
                    '<td>' + "<a href='/getSupplierProductAddForm?supplierId=" +result.data.supplierId+ "' class=\"btn btn-link btn-warning btn-icon btn-sm -upload\"   >"+  '<i class="tim-icons icon-upload">' +  '</i>' + '</a>' + '</td>'+
                    '<td>' + "<a href='/getSupplierEditForm?supplierId=" + result.data.supplierId + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\"   >" + '<i class="tim-icons icon-pencil">' + '</i>' + '</a>' + '</td>'+
                    '</tr>';
                $('#supplierPersonalList #PersonalSupplierByName').append(user);
                console.log(result.data);
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }

});