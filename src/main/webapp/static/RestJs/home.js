$.ajax({
    type: "GET",
    url: "/getFrontDatas",
    success: function (result) {
        var key = Object.keys(result);
        var value = result[key];
        $.each(result, function (i,products) {
            console.log(products);
            $("#numberofsales").html("<i class=\"tim-icons icon-trash-simple\" style=\"color:blue\"></i> &nbsp").append(value["numberofsales"]);
            $("#numberOfUsers").html("<i class=\"tim-icons icon-delivery-fast text-info\"></i> &nbsp").append(value["user"]);
            $("#numberOfExpiredProduct").html("<i id=\"expiredIcon\" class=\"tim-icons icon-trash-simple\" style=\"color:blue\"></i> &nbsp").append(value["expiredproduct"]);
            $("#revenue").html("<i class=\"tim-icons icon-user-run\" style=\"color:blue\"></i> &nbsp").append(value["revenue"]);
            $("#numberOfCustomers").html("<i class=\"tim-icons icon-user-run\" style=\"color:blue\"></i> &nbsp").append(value["numberofcustomers"]);
            $("#numberOfProducts").html("<i class=\"tim-icons icon-bag-16\" style=\"color:blue\"></i>  &nbsp").append(value["numberofproduct"]);
            $("#numberOfSuppliers").html("<i class=\"tim-icons icon-delivery-fast text-info\"></i>  &nbsp").append(value["numberofsuppliers"]);
            $("#countExpiredProduct").html(value["expiredproduct"]);
        });

        $.each(value["product"], function (i,product) {
                console.log( value["product"][i]["productId"] );
                var user='<li>'+
                         '<a href="#" class="nav-item dropdown-item">'+'ProductId:' +  value["product"][i]["productId"]  +  "&nbsp and &nbsp"  +value["product"][i]["productName"] + "&nbsp is Expired" + '</a>'
                          '</li>';
                        $("#expiredProductList").append(user);
            // $("#expiredProductList").html(value["product"][i]["productId"] + "and"+ value["product"][i]["productName"] + "is Expired" + "<br>");
            });
    },
    error: function (e) {
        alert("ERROR: ", e);
        console.log("ERROR: ", e);
    },
});