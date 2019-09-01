GET: $(document).ready(
    function() {
        // GET REQUEST
        $("#supplierForm").submit(function (event) {
            event.preventDefault();
            getSupplierByName();
        });

        // DO GET
        function getSupplierByName() {
            var supplierName = $("#supplierFormSelect").val();
            $.ajax({
                type: "GET",
                url: "/getSupplierByName/" + supplierName,
                data: JSON.stringify(supplierName),
                success: function (result) {
                    var key=Object.keys(result);
                    var value=result[key];
                    console.log(value);
                    $('#supplierList tbody tr').empty();
                    $.each(value["findSupplier"],
                        function (i, suppliers) {
                            var user = '<tr id="supplierName">' +
                                '<td>'+ value["findSupplier"][i]["supplierId"]+ '</td>'+
                                '<td>'+ value["findSupplier"][i]["supplierName"]+  '</td>'+
                                '<td>' + value["findSupplier"][i]["supplierType"] + '</td>'+
                                '<td>' + value["findSupplier"][i]["quantity"] + '</td>' +
                                '<td>' + value["findSupplier"][i]["cost"] + '</td>'+
                                '<td>'+  value["findSupplier"][i]["buyDate"] +'</td>'+
                                '<td>' + value["findSupplier"][i]["permanentAddress"] + '</td>'+
                                '<td>'+  value["findSupplier"][i]["temporaryAddress"] + '</td>'+
                                '<td>' + value["findSupplier"][i]["productId"] + '</td>'+
                                '<td>'+  value["findSupplier"][i]["productName"]+ '</td>'+
                                '<td>'+  value["findSupplier"][i]["username"] +  '</td>'+
                                '<td>'+ "<a href='/getSupplierProductAddForm?supplierId=" +value["findSupplier"][i]["supplierId"] + "' class=\"btn btn-link btn-warning btn-icon btn-sm -upload\" >"+  '<i class="tim-icons icon-upload">' + '</i>' +  '</a>' + '</td>'+
                                '<td>' + "<a href='/getSupplierBySupplierIdAndProductId?supplierId="+value["findSupplier"][i]["supplierId"] + '&'+ "supplierUnqueId="+value["findSupplier"][i]["supplierUnqiueId"]+ "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>'+
                                '<td>' + "<a href='deletesupplier?supplierId="+value["findSupplier"][i]["supplierId"]+ '&' + "productId="+value["findSupplier"][i]["productId"]  + "'  class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >"+ "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" +   '</td>'+
                                '</tr>'
                            $('#supplierList #supplierDataByName').append(
                                user)
                        });
                    var aggregateDatas= '<td>' + value['totalsupplier'] + '</td>'+
                        '<td>' +  ''  +'</td>'+
                        '<td>'+ '' +'</td>'+
                        '<td>'+ value['supplierquantity'] +'</td>'+
                        '<td>'+ value['totalsuppliercost'] +'</td>'+
                        '<td>'+ '' +'</td>'+
                        '<td>'+ '' +'</td>'+
                        '<td>'+ '' +'</td>'+
                        '<td>'+value['supplierproduct']  + '</td>'+
                        '<td>'+ '' +'</td>'+
                        '<td>'+ '' +'</td>'
                    $('#supplierList #aggregateTables').append(aggregateDatas);
                    $.each(value["supplierViews1"], function (index, supplierName) {

                        var distinctName= '<option>'+
                            value['supplierDistinctName'][index]['supplierName']+
                            '</option>'
                        $('#supplierFormSelect').append(distinctName);
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
