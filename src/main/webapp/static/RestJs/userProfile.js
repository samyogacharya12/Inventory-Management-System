GET: $(document).ready(function () {
    $("#userList").on('click', 'a[id="userProfile"]', function (e)
    {
        var userId = $(this).closest('tr[id="userRow"]').children('td[id="userId"]').click().text();
        $.ajax(
            {
                type: "GET",
                contentType: "application/json",
                url: "/retrieveUserProfileById/" + userId,
                dataType: 'json',
                data: JSON.stringify(userId),
                success: function (result) {
                    $('#userList tbody tr').empty();
                    // $.each(result,
                    //     function (index, user) {
                            console.log(result.data.userId);
                            // var userImage="<img src='/imagedisplay?userId=" +user.userId+ "'  class=\"center\" height=\"200px\" width=\"200px\" style=\"width:50%;\"/>";
                            $("#profile").html("<img src='/imagedisplay?userId="+result.data.userId+ "'  class=\"center\" height=\"300px\" width=\"300px\" style=\"width:50%;\"/>");
                            var userProfileTable='<tr>'+
                                                  '<td>'+ 'User Id' +'</td>'+
                                                   '<td>'+ result.data.userId +'</td>' +
                                                   '<td>'+ 'Email' +'</td>'+
                                                     '<td>'+ result.data.email +'</td>'+
                                                      '</tr>'+
                                                       '<tr>'+
                                                       '<td>'+ 'Permanent Address' +'</td>'+
                                                       '<td>'+result.data.permanentAddress  +'</td>'+
                                                        '<td>'+ 'Temporary Address'   +'</td>'+
                                                         '<td>'+ result.data.temporaryAdddress  +'</td>'+
                                                       '</tr>'+
                                                        '<tr>'+
                                                          '<td>'+'Phone Number'  +'</td>'+
                                                          '<td>'+ result.data.phoneNumber  +'</td>'+
                                                           '<td>'+ 'Landline Number'  +'</td>'+
                                                            '<td>'+ result.data.landlineNumber +'</td>'+
                                                         '</tr>'+
                                                         '<tr>'+
                                                          '<tr>'+
                                                           '<td>'+ 'Gender' +'</td>'+
                                                           '<td>'+  result.data.gender +'</td>'+
                                                            '<td>'+ 'Country' +'</td>' +
                                                            '<td>'+ result.data.country +'</td>'+
                                                          '</tr>'+
                                                           '<tr>'+
                                                         '<td>'+ 'Date Of Birth' +'</td>'+
                                                           '<td>'+result.data.dateOfBirth   +'</td>'+
                                                           '<td>'+'Joined Date'  +'</td>'+
                                                            '<td>'+result.data.joinedDate  +'</td>'+
                                                              '</tr>';
                                               $("#userProfile #userProfileData").append(userProfileTable);

                        // });



                }
            });
    })
});


// $(document).ready(function() {
//         $('table').on('click', 'a[id="user"]', function (e) {
//         var userName = $(this).closest('tr').children('td:nth-child(2)').text();
//         console.log(userName);
//         $.ajax({
//             type: "GET",
//             url: "/getUserProfile/"+ userName,
//             data: JSON.stringify(userName),
//             success: function (result) {
//                     $.each(result.data, function (i, projectuser) {
//                         console.log(projectuser);
//                         var username = projectuser.firstName + '' + projectuser.middleName + '' + projectuser.lastName;
//                         console.log(projectuser);
//                         $('#profile').html(username);
//                         var user = '<tr>' +
//                             '<td>' + 'User Id' + '</td>' +
//                             '<td>' + projectuser.userId + '</td>' +
//                             '<td>' + 'Email' + '</td>' +
//                             '<td>' + projectuser.email + '</td>' +
//                             '</tr>' +
//                             '<tr>' +
//                             '<td>' + 'Permanent Address' + '</td>' +
//                             '<td>' + projectuser.permanentAddress + '</td>'+
//                           '<td>' + 'Temporary Address' + '</td>' +
//                         '<td>' + projectuser.temporaryAddress + '</td>' +
//                         '</tr>' +
//                         '<tr>' +
//                         '<td>' + 'Phone Number' + '</td>' +
//                         '<td>' + projectuser.phoneNumber + '</td>' +
//                         '<td>' + 'LandLine Number' + '</td>' +
//                         '<td>' + projectuser.landlineNumber + '</td>' +
//                         '</tr>' +
//                         '<tr>' +
//                         '<td>' + 'Gender' + '</td>' +
//                         '<td>' + projectuser.gender + '</td>' +
//                         '<td>' + 'Country' + '</td>' +
//                         '<td>' + projectuser.country + '</td>' +
//                         '</tr>' +
//                         '<tr>' +
//                         '<td>' + 'Date of Birth' + '</td>' +
//                         '<td>' + projectuser.dateOfBirth + '</td>' +
//                         '<td>' + 'Join Date' + '</td>' +
//                         '<td>' + projectuser.joinDate + '</td>' +
//                         '</tr>';
//                         $('#userProfile tbody').append(user);
//                     });
//             },
//             error: function (e) {
//                 alert("ERROR: ", e);
//                 console.log("ERROR: ", e);
//             }
//             ,
//         });
//     })
// });
//
//
//
//
//
//
//
//
//
