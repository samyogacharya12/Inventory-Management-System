$(document).ready(
    function() {

        // SUBMIT FORM
        $("#saveExpenseForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            saveExpense();
        });

        function saveExpense() {

            // PREPARE FORM DATA
            var formData = {
                expenseId : $("#expenseId").val(),
                expenseName: $("#expenseName").val(),
                cost: $("#cost").val(),
                expenseDate: $("#expenseDate").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/save-expense",
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