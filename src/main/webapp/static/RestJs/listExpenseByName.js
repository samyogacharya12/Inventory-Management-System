GET: $(document).ready( function() {
    $("#searchExpenseByName").submit(function (event) {
        event.preventDefault();
        getExpenseByName();
    });

    function getExpenseByName() {
        var expenseName = $("#expenseName").val();
        $.ajax({
            type: "GET",
            url: "/getByExpenseName/" +expenseName,
            data: JSON.stringify(expenseName),
            success: function (result) {
                var key = Object.keys(result);
                var value = result[key];
                console.log(value);
                $.each(value["expenseByExpenseName"], function (i, expenseList) {
                    $('#expenseList tbody tr').empty();
                    var expense = '<tr id="expenses">' +
                        '<td id="expenseId">' + value["expenseByExpenseName"][i]["expenseId"] + '</td>' +
                        '<td>' + value["expenseByExpenseName"][i]["expenseName"] + '</td>' +
                        '<td>' + value["expenseByExpenseName"][i]["cost"] + '</td>' +
                        '<td>' + value["expenseByExpenseName"][i]["expenseDate"] + '</td>' +
                        '<td>' + value["expenseByExpenseName"][i]["username"] + '</td>' +
                        '<td>' + "<a href='/getExpenseEditForm?expenseId="+value["expenseByExpenseName"][i]["expenseId"]+ "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>'+
                        '<td>' +  "<a href='/delete-expense?expenseId="+value["expenseByExpenseName"][i]["expenseId"] + "' class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>'+
                        '</tr>'
                    $('#expenseList #expenseDataByName').append(expense);
                });
                var aggregateDatas = '<td>' + value['numberOfExpense'] + '</td>' +
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
});