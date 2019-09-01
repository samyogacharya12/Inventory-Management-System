$(document).ready(
    function() {

        // SUBMIT FORM
        $("#supplierProductUpdateForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            updateSupplierProduct();
        });

        function updateSupplierProduct() {

            // PREPARE FORM DATA
            var formData = {
                supplierId : $("#supplierProductId").val(),
                productId : $("#productId").val(),
                quantity: $("#quantity").val(),
                cost: $("#supplierProductCost").val(),
                buyDate: $("#supplierProductBuyDate").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/update-supplierproduct",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status == "success") {
                        swal("Good job!", "Your Data has been updated!", "success");
                        document.location.reload();
                    } else {
                        swal("Sorry", "Your Data hasn't been updated", "error");
                    }
                    console.log(result);
                },
                error : function(e) {
                    swal("Sorry", "Your Data hasn't been updated :", "error");
                    console.log("ERROR: ", e);
                }
            });

        }

    })