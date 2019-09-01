GET:$(document).ready(function () {
        // GET Request
        $("#summaryExpenseForm").submit(function (event) {
            event.preventDefault();
            getExpenseDataByDate();
        });
        function getExpenseDataByDate()
        {
            var firstDate=$("#startDate").val();
            var  endDate=$("#endDate").val();
            $.ajax(
                {
                    type: "GET",
                    url: "/calculateCash/"+ firstDate + '/'+endDate,
                    success: function (result) {
                        console.log(result);
                        var key = Object.keys(result);
                        var value = result[key];
                        console.log(value);
                        if(result['data']==="profit") {
                            var information = 'The Profit is of' + result['profit'];
                            $("#typography").append(information);
                        }
                        else if(result['data']==="breakevenpoint")
                        {
                            var information = 'The broke is of is of' + result['breakevenpoint'];
                        }
                        else
                        {
                            var information = 'The Loss is of' + result['loss'];
                            $("#typography").append(information);
                        }
                    },
                    error: function (e) {
                        alert("ERROR: ", e);
                        console.log("ERROR: ", e);
                    }
                }
            )
        }

});