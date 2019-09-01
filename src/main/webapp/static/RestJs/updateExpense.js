$(document).ready(
    function() {

        // SUBMIT FORM
        $("#updateExpenseForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            saveExpense();
        });

        function saveExpense() {

            // PREPARE FORM DATA
            var formData = {
                expenseId : $("#expenseIdEdit").val(),
                expenseName: $("#expenseNameEdit").val(),
                cost: $("#cost").val(),
                expenseDate: $("#expenseDate").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/update-expense",
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