GET: $(document).ready( function() {
    getAllExpenseInfo();
    deleteExpense();

    function getAllExpenseInfo() {
        $.ajax({
            type: "GET",
            url: "/getListExpenses",
            success: function (result) {
                var key = Object.keys(result);
                var value = result[key];
                console.log(value);
                $.each(value["expenseList"], function (i, expenseList) {
                    var expense = '<tr id="expenses">' +
                        '<td id="expenseId">' + value["expenseList"][i]["expenseId"] + '</td>' +
                        '<td>' + value["expenseList"][i]["expenseName"] + '</td>' +
                        '<td>' + value["expenseList"][i]["cost"] + '</td>' +
                        '<td>' + value["expenseList"][i]["expenseDate"] + '</td>' +
                        '<td>' + value["expenseList"][i]["username"] + '</td>' +
                        '<td>' + "<a id='expenseEdit"+ "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>'+
                        '<td>' +  "<a id='expenseDelete" + "' class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>'+
                        '</tr>'
                    $('#expenseList #expenseData').append(expense);
                });
                var aggregateDatas = '<td>' + value['numberOfExpenses'] + '</td>' +
                    '<td>' + '' + '</td>' +
                    '<td>' + value['totalCost'] + '</td>'
                $('#expenseList #aggregateTables').append(aggregateDatas);

            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            },
        });
    }

     function deleteExpense()
     {
         $('#expenseList').on('click', 'a[id="expenseDelete"]', function (e) {
             var expenseId = $(this).closest('tr[id="expenses"]').children('td[id="expenseId"]').click().text();
             $.ajax({
                 type: "GET",
                 url: "/delete-expense/" + expenseId,
                 success: function (result) {
                     if (result.status == "success") {
                         swal('Deleted!', 'Your date has been deleted.', 'success')
                         document.location.reload();
                     } else {
                         swal("Sorry", "Your Data hasn't been deleted :", "error");
                     }
                 },
                 error: function (e) {
                     swal("Sorry", "Your Data hasn't been deleted :", "error");
                     console.log("ERROR: ", e);
                 }

             })

         });
     }

});