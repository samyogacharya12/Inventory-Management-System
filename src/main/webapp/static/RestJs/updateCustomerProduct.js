$(document).ready(
    function() {

        // SUBMIT FORM
        $("#updateCustomerProductForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            updateCustomerProduct();
        });

        function updateCustomerProduct() {

            // PREPARE FORM DATA
            var formData = {
                customerId : $("#customerIdProduct").val(),
                productId : $("#productId").val(),
                customerPurchaseId: $("#customerPurchaseId").val(),
                quantity : $("#quantityCustomerProduct").val(),
                buyDate: $("#buyDateCustomerProduct").val(),
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/updateCustomerProduct",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status == "success") {
                        console.log(result);
                        swal("Your Data has been updated");
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