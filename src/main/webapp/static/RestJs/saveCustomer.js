$(document).ready(
    function() {

        // SUBMIT FORM
        $("#customerPersonalForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                customerId : $("#customerId").val(),
                customerName : $("#customerName").val(),
                permanentAddress : $("#permanentAddress").val(),
                temporaryAddress: $("#temporaryAddress").val(),
                phoneNumber: $("#phoneNumber").val(),
                email: $("#email").val(),
                buyDate: $("#buyDate").val(),
                country: $("#country").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/save-customer",
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