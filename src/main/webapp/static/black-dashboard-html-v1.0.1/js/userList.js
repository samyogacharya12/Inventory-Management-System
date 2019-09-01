GET: $(document).ready(
    function() {

        // GET REQUESTs
        $("#getAllUsers").click(function(event) {
            event.preventDefault();
            ajaxGet();
        });

        // DO GET
        function ajaxGet() {
            $.ajax({
                type : "GET",
                url : "list-user",
                success : function(result) {
                    if (result.status == "success") {
                        $('#userList').empty();
                        var userList="";
                        $.each(result.data,
                         function(i, users)
                         {
                             userList="User Id" +users.userId
                                       "Username" +users.username
                                        "Password" +users.password
                                        "firstName" +users.firstName
                                         "middleName" +users.middleName
                                          "lastName" +users.lastName
                                           "userRoleId" +users.userRoleId
                             $('#userList').append(
                                 users)
                    });
                    } else {
                        $("#userList").html("<strong>Error</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error : function(e) {
                    $("#userList").html("<strong>Error</strong>");
                    console.log("ERROR: ", e);
                }
            });
        }
    })