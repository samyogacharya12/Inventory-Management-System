GET:$(document).ready(
    function () {
        // GET Request
        $("#expenseDateForm").submit(function (event) {
            event.preventDefault();
            getExpenseDataByDate();
        });

        function  getExpenseDataByDate()
        {
            var expenseStartDate=$("#expenseStartDate").val();
            var expenseLastDate=$("#expenseLastDate").val();
            $.ajax({
                type: "GET",
                url: "/getExpenseDataByDate/"+expenseStartDate+'/'+expenseLastDate,
                success: function (result) {
                    console.log(result);
                    $('#expenseList tbody tr').empty();
                    var key=Object.keys(result);
                    var value=result[key];
                    console.log(value);
                    $.each(value["expenseByExpenseDate"],
                        function (index,expenses) {
                            var expense = '<tr id="searchUser">'+
                                         '<td id="expenseId">' + value["expenseByExpenseDate"][index]["expenseId"] + '</td>' +
                                         '<td>' + value["expenseByExpenseDate"][index]["expenseName"] + '</td>' +
                                         '<td>' + value["expenseByExpenseDate"][index]["cost"] + '</td>' +
                                         '<td>' + value["expenseByExpenseDate"][index]["expenseDate"] + '</td>' +
                                          '<td>' + value["expenseByExpenseDate"][index]["username"] + '</td>' +
                                          '<td>' + "<a href='/getExpenseEditForm?expenseId="+value["expenseByExpenseDate"][index]["expenseId"]+ "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>'+
                                          '<td>' +  "<a id='expenseDelete" + "' class=\"btn btn-link btn-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>'+
                                            '</tr>'
                            $('#expenseList #expenseDataByDate').append(expense)
                        });
                    var aggregateDatas = '<td>' + value['numberOfExpense'] + '</td>' +
                        '<td>' + '' + '</td>' +
                        '<td>' + value['totalCost'] + '</td>'
                    $('#expenseList #aggregateTables').append(aggregateDatas);

                },
                error: function (e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }

    });