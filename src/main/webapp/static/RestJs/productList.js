GET: $(document).ready( function() {
    getAllProductInfo();
    deleteProduct();
    addToTrash();


    function getAllProductInfo() {
        $.ajax({
            type: "GET",
            url: "/getListProduct",
            success: function (result) {
                var key = Object.keys(result);
                var value = result[key];
                console.log(value);
                $.each(value["purchaseProducts"], function (i, productList) {
                    var product = '<tr id="products">' +
                        '<td id="productId">' + value["purchaseProducts"][i]["productId"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["productName"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["productType"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["price"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["quantity"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["magnifactureDate"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["expiryDate"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["purchaseDate"] + '</td>' +
                        '<td>' + value["purchaseProducts"][i]["username"] + '</td>' +
                        '<td>'+ "<img src='productimagedisplay?productId="+value["purchaseProducts"][i]["productId"] + "' alt=\"image-display\" class=\"center\" height=\"200px\" width=\"200px\" style=\"width:50 %;\"/>" + '</td>'+
                    '<td>' + "<a id='productEdit" + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                    '<td>' + "<a id='productDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                    '<td>' + "<a id='addToTrash" + "' >" + "<i class='tim-icons icon-trash-simple'>" + "</i>" + "</a>" + '</td>'+
                        '</tr>'
                    $('#productList #productData').append(product);
                });
                var aggregateDatas = '<td>' + value['totalnoproduct'] + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + value['totalnoprice'] + '</td>' +
                    '<td>' + value['totalquantity'] + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + '' + '</td>'+
                    '<td>' +  ''   +'</td>'
                 $('#productList #aggregateTables').append(aggregateDatas);

            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }

    function deleteProduct() {
        $('#productList').on('click', 'a[id="productDelete"]', function (e) {
            var productId = $(this).closest('tr[id="products"]').children('td[id="productId"]').click().text();
            $.ajax({
                type: "GET",
                url: "/delete-product/"+productId,
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

    function  addToTrash(){
    $('#productList').on('click', 'a[id="addToTrash"]', function (e) {
        var productId = $(this).closest('tr[id="products"]').children('td[id="productId"]').click().text();
        $.ajax({
            type: "GET",
            url: "/addToTrash/"+productId,
            success: function (result) {
                if (result.status == "success") {
                    swal('Updated', 'Your '+productId + 'has been updated to trash.', 'success')
                    document.location.reload();
                } else {
                    swal("Sorry", "Your Data hasn't been updated to trash :", "error");
                }
            },
            error: function (e) {
                swal("Sorry", "Your Data hasn't been updated :", "error");
                console.log("ERROR: ", e);
            }

        })

    })

    }
});