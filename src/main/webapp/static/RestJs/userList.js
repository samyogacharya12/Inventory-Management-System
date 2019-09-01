GET: $(document).ready( function(){
       getAllUserInfo();
       deleteUser();

function getAllUserInfo() {
    $.ajax({
        type: "GET",
        enctype: 'multipart/form-data',
        url: "/getListUser",
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (result) {
            $.each(result, function (i, userList) {
                var user = '<tr id="userRow">' +
                    '<td id="userId">' + userList.userId + '</td>' +
                    '<td>' + "<a href='#' id='userProfile" +"'   >" + userList.username+ '</a>' + '</td>' +
                    '<td>' + userList.password + '</td>' +
                    '<td>' + userList.firstName + '</td>' +
                    '<td>' + userList.middleName + '</td>' +
                    '<td>' + userList.lastName + '</td>' +
                    '<td>' + userList.userRoleId + '</td>' +
                    '<td>' + "<a id='userEdit"+ "' class=\"btn btn-link btn-warning btn-icon btn-sm edit\" >" + "<i class='tim-icons icon-pencil'>" + "</i>" + "</a>" + '</td>' +
                    // '<td>' + "<button id='userEdit"+"' type=\"button\" class=\"btn btn-success\" >"+ "Success" +"</button>"+ '</td>' +
                    '<td>' + "<a id='userDelete"+"'  class=\"btn btn-link btns-danger btn-icon btn-sm remove\" >" + "<i class='tim-icons icon-simple-remove'>" + "</i>" + "</a>" + '</td>' +
                    '</tr>';
                $('#userList #userByName').append(user);
            });
            // }
        },
        error: function (e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        },
    });
}

function  deleteUser() {
    $('#userList').on('click', 'a[id="userDelete"]', function (e) {
        var userId=$(this).closest('tr[id="users"]').children('td[id="userId"]').click().text();
        $.ajax({
            type: "GET",
            url: "/delete-user/" +userId,
            success: function (result) {
                        if (result.status == "success") {
                            swal('Deleted!', 'Your date has been deleted.', 'success')
                            document.location.reload();
                        }
                        else
                        {
                            swal("Sorry", "Your Data hasn't been deleted :", "error");
                        }
            },
            error : function(e) {
                swal("Sorry", "Your Data hasn't been deleted :", "error");
                console.log("ERROR: ", e);
            }

        })

    });

}


});










