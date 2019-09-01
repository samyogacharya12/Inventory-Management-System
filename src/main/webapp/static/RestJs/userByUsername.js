GET: $(document).ready( function(){

    $("#searchUser").submit(function(event) {
        event.preventDefault();
        ajaxGet();
    });

    function ajaxGet() {
        var username = $("#username").val();
        $.ajax({
            type: "GET",
            url: "/getUserByUsername/" + username,
            data: JSON.stringify(username),
            success: function (result) {
                $('#userList tbody tr').empty();
                // $.each(result,
                //     function (i, users) {
                        var user = '<tr id="users">' +
                            '<td id="userId">' + result.data.userId + '</td>' +
                            '<td id="userProfile">' + '<a id="user" href=' + "username?userId=" + result.data.userId + '>' + result.data.username + '</a>' + '</td>' +
                            '<td>' + result.data.password + '</td>' +
                            '<td>' + result.data.firstName + '</td>' +
                            '<td>' + result.data.middleName + '</td>' +
                            '<td>' + result.data.lastName + '</td>' +
                            '<td>' + result.data.userRoleId + '</td>' +
                            '<td>' + '<a href=' + "getEditForm?userId=" + result.data.userId + '>' + '<i class="tim-icons icon-pencil">' + '</i>' + '</a>' + '</td>' +
                            '<td>' + '<a id=' + '"userDelete" class="btn btn-link btn-danger btn-icon btn-sm remove">' + '<i class="tim-icons icon-simple-remove">' + '</i>' + '</a>' + '</td>' +
                            '</tr>';
                        // var user = "Book Name  "
                        //         + book.bookName
                        //         + ", Author  = " + book.author
                        //         + "<br>";
                        $('#userList #userByName').append(user)
                    // });
                console.log(result.data);
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    }

});