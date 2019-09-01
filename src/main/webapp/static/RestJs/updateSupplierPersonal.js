$(document).ready(
    function() {

        // SUBMIT FORM
        $("#updateSupplierPersonal").click(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            updatePost();
        });

        function updatePost() {

            // PREPARE FORM DATA
            var formData = $('#supplierPersonalUpdateForm')[0];
            var data=new FormData(formData)

            // DO POST
            $.ajax({
                type : "POST",
                enctype: 'multipart/form-data',
                url : "/update-supplierpersonal",
                data : data,
                processData: false, //prevent jQuery from automatically transforming the data into a query string
                contentType: false,
                cache: false,
                timeout: 600000,
                success : function(result) {
                    if (result.status=="success") {
                        console.log(result);
                        swal("Good job!", "Your Data has been updated!", "success");
                        document.location.reload();
                    } else {
                        swal("Sorry", "Your Data hasn't been updated", "error")
                    }
                },
                    error:function(e) {
                        swal("Sorry", "Your Data hasn't been updated :", "error")
                        console.log("ERROR: ", e);
                    }

            });
        }
    });