GET: $(document).ready(function () {
    $("#expenseList").on('click', 'a[id="expenseEdit"]', function (e) {
        var expenseId = $(this).closest('tr[id="expenses"]').children('td[id="expenseId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/getExpenseEditForm/"+expenseId,
                dataType: 'json',
                success: function (result) {
                    $('#expenseList tbody tr').empty();
                    $.each(result, function (index, expense) {
                        $("#expenseIdEdit").val(expense.expenseId);
                        $("#expenseNameEdit").val(expense.expenseName);
                        $("#cost").val(expense.cost);
                        $("#expenseDate").val(expense.expenseDate);
                    })
                }
            }
        )


    })
});