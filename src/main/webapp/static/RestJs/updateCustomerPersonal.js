$(document).ready(
    function() {

        // SUBMIT FORM
        $("#updateCustomerPersonalForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            updatePersonalCustomer();
        });

        function updatePersonalCustomer() {

            // PREPARE FORM DATA
            var formData = {
                customerId : $("#customerIdPersonal").val(),
                customerName : $("#customerNamePersonal").val(),
                permanentAddress : $("#permanentAddressPersonal").val(),
                temporaryAddress: $("#temporaryAddressPersonal").val(),
                phoneNumber: $("#phoneNumber").val(),
                // email: $("#email").val(),
                // buyDate: $("#buyDate").val(),
                // country: $("#country").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/updateCustomerPersonalData",
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