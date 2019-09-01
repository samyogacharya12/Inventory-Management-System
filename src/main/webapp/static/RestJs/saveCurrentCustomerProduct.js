$(document).ready(
    function() {

        // SUBMIT FORM
        $("#currentCustomerProductForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            saveCustomerProduct();
        });

        function saveCustomerProduct() {

            // PREPARE FORM DATA
            var formData = {
                customerId : $("#currentCustomerId").val(),
                productId : $("#currentProductId").val(),
                quantity : $("#currentQuantity").val(),
                buyDate: $("#currentBuyDate").val(),
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/saveCustomerProduct",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status == "success") {
                        console.log(result);
                        swal("Your Data has been saved");
                        document.location.reload();
                    } else {
                        swal("Sorry", "Your Data hasn't been saved", "error");
                    }
                    console.log(result);
                },
                error : function(e) {
                    swal("Sorry", "Your Data hasn't been saved :", "error");
                    console.log("ERROR: ", e);
                }
            });

        }

    })