$(document).ready(
    function() {

        // SUBMIT FORM
        $("#newCustomerProductForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                customerId : $("#customerIdAdd").val(),
                productId : $("#productIdAdd").val(),
                quantity : $("#quantity").val(),
                buyDate: $("#buyDate").val(),
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/saveNewCustomerProduct",
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