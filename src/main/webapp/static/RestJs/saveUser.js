$(document).ready(
    function() {

        // SUBMIT FORM
        $("#btnsubmit").click(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = $('#userForm')[0];
            var data=new FormData(formData)

            // DO POST
            $.ajax({
                type : "POST",
                enctype: 'multipart/form-data',
                url : "/save-user",
                data : data,
                processData: false, //prevent jQuery from automatically transforming the data into a query string
                contentType: false,
                cache: false,
                timeout: 600000,
                success : function(result) {
                    if (result.status == "success")
                    {
                        console.log(result)
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