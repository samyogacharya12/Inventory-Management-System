GET: $(document).ready(function () {
    $("#userList").on('click', 'a[id="userEdit"]',function(e)
    {
        var userId = $(this).closest('tr[id="userRow"]').children('td[id="userId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/retrieveUserById/" + userId,
                    dataType: 'json',
                    success: function (result) {
                    $('#userList tbody tr').empty();
                    $.each(result,
                        function (index, user) {
                        console.log(user.userId)
                          $("#userId").val(user.userId);
                           $("#updateUsername").val(user.username);
                           $("#password").val(user.password);
                           $("#email").val(user.email);
                           $("#firstName").val(user.firstName);
                           $("#middleName").val(user.middleName);
                           $("#lastName").val(user.lastName);
                           $("#updateTemporaryAddress").val(user.temporaryAdddress);
                           $("#updatePermanentAddress").val(user.permanentAddress);
                           $("#country").val(user.country);
                           $("#phoneNumber").val(user.phoneNumber);
                           $("#landlineNumber").val(user.landlineNumber);
                           $("#userRoleId").val(user.userRoleId);
                           if(user.gender=='male')
                           {
                               $("#genderMale").val(user.gender);
                           }
                           else
                           {
                               $("#genderFemale").val(user.gender);
                           }
                           $("#file").val(user.file);
                        });
                }
            });
    })


});