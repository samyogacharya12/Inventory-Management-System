$(document).ready(
    function() {

        // SUBMIT FORM
        $("#saveSupplierProductForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                supplierId : $("#supplierId").val(),
                productId : $("#productIdSelect").val(),
                cost: $("#cost").val(),
                buyDate : $("#buyDate").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/save-supplierproduct",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status == "success") {
                        swal("Good job!", "Your Data has been saved!", "success")
                        document.location.reload();
                    } else {
                        swal("Sorry", "Your Data hasn't been saved", "error")
                    }
                    console.log(result);
                },
                error : function(e) {
                    swal("Sorry", "Your Data hasn't been saved :", "error")
                    console.log("ERROR: ", e);
                }
            });

        }

    })