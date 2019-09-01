GET: $(document).ready(
    function() {
        // GET REQUEST
        $("#searchProductForm").submit(function (event) {
            event.preventDefault();
            getProductByName();
        });

        // DO GET
        function getProductByName() {
            var productName = $("#productName").val();
            $.ajax({
                type: "GET",
                url: "/getProductByName/" + productName,
                data: JSON.stringify(productName),
                success: function (result) {
                    var key=Object.keys(result);
                    var value=result[key];
                    console.log(value);
                    $('#productList tbody tr').empty();
                    $.each(value["findProduct"],
                        function (i, products) {
                            var user = '<tr id="productName">' +
                                '<td>'+ value["findProduct"][i]["productId"]+ '</td>'+
                                '<td>'+ value["findProduct"][i]["productName"]+  '</td>'+
                                '<td>' + value["findProduct"][i]["productType"] + '</td>'+
                                '<td>' + value["findProduct"][i]["price"] + '</td>' +
                                '<td>' + value["findProduct"][i]["quantity"] + '</td>'+
                                '<td>'+  value["findProduct"][i]["magnifactureDate"] +'</td>'+
                                '<td>' + value["findProduct"][i]["expiryDate"] + '</td>'+
                                '<td>'+  value["findProduct"][i]["purchaseDate"] + '</td>'+
                                '<td>' + value["findProduct"][i]["username"] + '</td>'+
                                '<td>'+ "<img src='productimagedisplay?productId="+value["findProduct"][i]["productId"] + "' alt=\"image-display\" class=\"center\" height=\"200px\" width=\"200px\" style=\"width:50 %;\"/>" + '</td>'+
                                '<td>' + "<a href='/getProductEditForm?productId=" + value["findProduct"][i]["productId"] + "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                                '<td>' + "<a id='productDelete" + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                                '<td>' + "<a href='/addToTrash?productId=" +value["findProduct"][i]["productId"]+ "' >" + "<i class='tim-icons icon-trash-simple'>" + "</i>" + "</a>" + '</td>'+
                                '</tr>'
                            $('#productList #getProductByName').append(
                                user)
                        });
                    var aggregateDatas = '<td>' + value['totalNoOfProduct'] + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + value['totalPrice'] + '</td>' +
                        '<td>' + value['totalNoOfQuantity'] + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + '' + '</td>'+
                        '<td>' +  ''   +'</td>'
                    $('#productList #aggregateTables').append(aggregateDatas);
                    console.log(result.data);
                },
                error: function (e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    });
