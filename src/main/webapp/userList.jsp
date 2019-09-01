<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="apple-touch-icon"  href="static/black-dashboard-html-v1.0.1/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="static/black-dashboard-html-v1.0.1/assets/img/favicon.png">
    <title>Insert title here</title>
    <link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link href="static/black-dashboard-html-v1.0.1/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css?v=1.0.0" rel="stylesheet" />
    <style type="text/css" class="init">
    </style>
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link href="static/black-dashboard-html-v1.0.1/assets/css/nucleo-icons.css" rel="stylesheet" />
    <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css?v=1.0.0" rel="stylesheet" />
    <link href="static/black-dashboard-html-v1.0.1/assets/demo/demo.css" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"  src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.css" rel="stylesheet"/>
    <script src="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.js"> </script>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"  src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript"  src="static/media/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="static/RestJs/userList.js"> </script>
    <script type="text/javascript" src="static/RestJs/userByUsername.js"> </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.js"> </script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"> </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="static/RestJs/userProfile.js"> </script>
    <script type="text/javascript" src="static/RestJs/updateUser.js"> </script>
    <script type="text/javascript" src="static/RestJs/userEdit.js"> </script>
    <style>
        #updateUserForm{
            display: none;
        }
        #userProfileDiv {
            display: none;
        }

    </style>
    <script>
        $(document).ready(function () {
            $("#userList").on('click', 'a[id="userEdit"]',function(e)
            {
                $("#updateUserForm").show();
                $("#tableData").hide();
                $("#searchUser").hide();
                $("#addNewUser").hide();
            });
        });
        $(document).ready(function () {
            $("#userList").on('click', 'a[id="userProfile"]', function (e) {
                $("#userProfileDiv").show();
                $("#tableData").hide();
                $("#searchUser").hide();
                $("#addNewUser").hide();
            });
        });

    </script>
</head>

<body class="">
<div class="wrapper">
    <div class="sidebar">
        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="javascript:void(0)" class="simple-text logo-normal">
                    <b>Welcome ${username}</b>
                </a>
            </div>
            <ul class="nav">
                <li class="active ">
                    <a href="/">
                        <i class="tim-icons icon-chart-pie-36"></i>
                        Dashboard
                    </a>
                </li>
                <li>
                    <a href="/list-supplier">
                        <i class="tim-icons icon-single-02"></i>
                        Suppliers
                    </a>
                </li>
                <li>
                    <a href="/list-product">
                        <i class="tim-icons icon-basket-simple"></i>
                        Products
                    </a>
                </li>
                <li>
                    <a href="/list-Customer">
                        <i class="tim-icons icon-single-02"></i>
                        Customers
                    </a>
                </li>
                <li>
                    <a href="/list-user">
                        <i class="tim-icons icon-single-02"></i>
                        Users
                    </a>
                </li>

                <li>
                    <a href="/get_report">
                        <i class="tim-icons icon-bag-16"></i>
                        Summary Report
                    </a>
                </li>

                <li>
                    <a href="/list-expense">
                        <i class="tim-icons icon-notes"></i>
                        <p>Expenses</p>
                    </a>
                </li>


                <li>
                    <a href="/list-trash">
                        <i class="tim-icons icon-trash-simple"></i>
                        <p>Trash</p>
                    </a>
                </li>

                <li>
                    <a href="/logout">
                        <i class="icon-double-right"></i>
                        Logout
                    </a>
                </li>


            </ul>
        </div>
    </div>
    <div class="main-panel">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-absolute navbar-transparent">
            <div class="container-fluid">
                <div class="navbar-wrapper">
                    <div class="navbar-toggle d-inline">
                        <button type="button" class="navbar-toggler">
                            <span class="navbar-toggler-bar bar1"></span>
                            <span class="navbar-toggler-bar bar2"></span>
                            <span class="navbar-toggler-bar bar3"></span>
                        </button>
                    </div>
                </div>
            </div>
        </nav>
        <div class="modal modal-search fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="SEARCH">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <i class="tim-icons icon-simple-remove"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Navbar -->
        <div class="content">
            <div class="row mt-5">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div id="datatable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                                <div class="row">
                                    <div class="col-sm-12 col-md-6">
                                        <div class="dataTables_length" id="datatable_length">
                                        </div></div>
                                    <div class="col-sm-12 col-md-6">
                                        <div id="datatable_filter" class="dataTables_filter">
                                            <form id="searchUser" action="/getUserByUsername" method="get">
                                                <label><input type="search" id="username" name="username" class="form-control form-control-sm" placeholder="Search records" aria-controls="datatable"></label>
                                                <button class="btn btn-primary btn-sm">Search</button>
                                            </form>
                                        </div></div></div>
                            <div class="row" id="tableData">
                                <div class="col-sm-12">
                                    <a id="addNewUser" href="/user-Form"  class="btn btn-primary btn-sm"> Add new </a>
                                    <table id="userList" class="table">
                                        <thead class=" text-primary">
                                        <tr>
                                            <th>
                                                User Id
                                            </th>
                                            <th>
                                                Username
                                            </th>
                                            <th>
                                                Password
                                            </th>
                                            <th class="text-center">
                                                First Name
                                            </th>

                                            <th>
                                                Middle Name
                                            </th>

                                            <th>
                                                Last Name
                                            </th>

                                            <th>
                                                User Role
                                            </th>
                                        </tr>
                                        </thead>

                                        <tbody id="userData">
                                        </tbody>
                                        <tbody id="userByName">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                                <div class="card-body">
                                    <div class="col-md-8">
                                                <form id="updateUserForm" action="/update-user" method="post" enctype="multipart/form-data">

                                                    <div class="row">
                                                        <div class="col-md-5 pr-md-1">
                                                            <div class="form-group">
                                                                <input type="hidden" id="userId" name="userId"  class="form-control" />
                                                            </div>
                                                        </div>


                                                    <div class="row">
                                                        <div class="col-md-5 pr-md-1">
                                                            <div class="form-group">
                                                                <label >Username </label>
                                                                <input type="text" id="updateUsername" name="username"  class="form-control" />
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3 px-md-1">
                                                            <div class="form-group">
                                                                <label>Password</label>
                                                                <input type="password" id="password" name="password"  placeholder="password" class="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4 pl-md-1">
                                                        <div class="form-group">
                                                            <label> Email address</label>
                                                            <input type="email" id="email" name="email"  placeholder="mike@email.com" class="form-control"/>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-md-6 pr-md-1">
                                                            <div class="form-group">
                                                                <label> First Name </label>
                                                                <input type="text" id="firstName"  name="firstName"  placeholder="firstName" class="form-control" />
                                                            </div>
                                                        </div>

                                                        <div class="col-md-6 pl-md-1">
                                                            <div class="form-group">
                                                                <label>Middle Name </label>
                                                                <input type="text" id="middleName" name="middleName"  placeholder="Middle Name" class="form-control" />
                                                            </div>
                                                        </div>

                                                        <div class="col-md-6 pl-md-1">
                                                            <div class="form-group">
                                                                <label> Last Name </label>
                                                                <input type="text" id="lastName"  name="lastName"  placeholder="Last Name" class="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label >Temporary Address </label>
                                                                <input type="text" id="updateTemporaryAddress" name="temporaryAdddress"  placeholder="" class="form-control" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4 pr-md-1">
                                                            <div class="form-group">
                                                                <label> Permanent Address </label>
                                                                <input type="text" id="updatePermanentAddress" name="permanentAddress"  placeholder="City" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 px-md-1">
                                                            <div class="form-group">
                                                                <label>Country </label>
                                                                <input type="text" id="country" name="country"  placeholder="Country" class="form-control" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 pl-md-1">
                                                            <div class="form-group">
                                                                <label> Phone number </label>
                                                                <input type="number" id="phoneNumber" name="phoneNumber"  class="form-control" />
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4 pl-md-1">
                                                            <div class="form-group">
                                                                <label> LandLine number </label>
                                                                <input type="number" id="landlineNumber" name="landlineNumber"   class="form-control" />
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label> User Role </label>
                                                            <select id="userRoleId" name="userRoleId">
                                                                <option value="1"${userEdit.userRoleId=='1'?'selected':''}>1</option>
                                                                <option value="2" ${userEdit.userRoleId=='2'?'selected':''}>2</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <input type="radio" id="genderMale" name="gender" value="male" ${userEdit.gender=='male'?'checked':'' or userEdit.gender == null?'checked':''}/> Male
                                                                <input type="radio" id="genderFemale"  name="gender" value="female" ${userEdit.gender=='female'?'checked':''}/> Female
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4 pr-md-1">
                                                            <div class="form-group">
                                                                <label> Upload Image </label> <input id="file" type="file" name="file">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                    <div class="card-footer">
                                                        <div class="col-md-12">
                                                            <button id="updateButton" type="submit" class="btn btn-fill btn-primary">Save</button>
                                                        </div>
                                                    </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
            </div>
                                <div class="card-body">
                                    <div id="userProfileDiv" class="table-responsive">
                                        <div id="profile" class="profile-usertitle-name">

                                        </div>
                                <table id="userProfile" class="table tablesorter ">
                                    <tbody id="userProfileData">
<%--                                    <tr>--%>
<%--                                        <td>--%>
<%--                                            User Id--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            ${user.userId}--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            Email--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.email}--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>

<%--                                    <tr>--%>
<%--                                        <td>--%>
<%--                                            Permanent Address--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.permanentAddress}--%>
<%--                                        </td>--%>


<%--                                        <td>--%>
<%--                                            Temporary Address--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            ${user.temporaryAdddress}--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>

<%--                                    <tr>--%>
<%--                                        <td>--%>
<%--                                            Phone Number--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.phoneNumber}--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            LandLine Number--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.landlineNumber}--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>

<%--                                    <tr>--%>
<%--                                        <td>--%>
<%--                                            Gender--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.gender}--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            Country--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.country}--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>

<%--                                    <tr>--%>

<%--                                        <td>--%>
<%--                                            date of birth--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.dateOfBirth}--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            joined date--%>
<%--                                        </td>--%>

<%--                                        <td>--%>
<%--                                            ${user.joinedDate}--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
                                    </tbody>
                                </table>
                                    </div>
                                </div>

                        </div>
            </div>
                                </div>
                            </div>
                        </div>
        </div>

<!--   Core JS Files   -->
<script src="static/RestJs/updateUser.js"></script>
<script src="static/black-dashboard-html-v1.0.1/assets/js/core/jquery.min.js"></script>
<script src="static/black-dashboard-html-v1.0.1/assets/js/core/popper.min.js"></script>
<script src="static/black-dashboard-html-v1.0.1/assets/js/core/bootstrap.min.js"></script>
<script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  Google Maps Plugin    -->
<!-- Place this tag in your head or just before your close body tag. -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Chart JS -->
<script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin    -->
<script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
<script src="static/black-dashboard-html-v1.0.1/assets/js/black-dashboard.min.js?v=1.0.0"></script>
<!-- Black Dashboard DEMO methods, don't include it in your project! -->
<script src="static/black-dashboard-html-v1.0.1/assets/demo/demo.js"></script>
<script>
    $(document).ready(function() {
        $().ready(function() {
            $sidebar = $('.sidebar');
            $navbar = $('.navbar');
            $main_panel = $('.main-panel');

            $full_page = $('.full-page');

            $sidebar_responsive = $('body > .navbar-collapse');
            sidebar_mini_active = true;
            white_color = false;

            window_width = $(window).width();

            fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



            $('.fixed-plugin a').click(function(event) {
                if ($(this).hasClass('switch-trigger')) {
                    if (event.stopPropagation) {
                        event.stopPropagation();
                    } else if (window.event) {
                        window.event.cancelBubble = true;
                    }
                }
            });

            $('.fixed-plugin .background-color span').click(function() {
                $(this).siblings().removeClass('active');
                $(this).addClass('active');

                var new_color = $(this).data('color');

                if ($sidebar.length != 0) {
                    $sidebar.attr('data', new_color);
                }

                if ($main_panel.length != 0) {
                    $main_panel.attr('data', new_color);
                }

                if ($full_page.length != 0) {
                    $full_page.attr('filter-color', new_color);
                }

                if ($sidebar_responsive.length != 0) {
                    $sidebar_responsive.attr('data', new_color);
                }
            });

            $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
                var $btn = $(this);

                if (sidebar_mini_active == true) {
                    $('body').removeClass('sidebar-mini');
                    sidebar_mini_active = false;
                    blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
                } else {
                    $('body').addClass('sidebar-mini');
                    sidebar_mini_active = true;
                    blackDashboard.showSidebarMessage('Sidebar mini activated...');
                }

                // we simulate the window Resize so the charts will get updated in realtime.
                var simulateWindowResize = setInterval(function() {
                    window.dispatchEvent(new Event('resize'));
                }, 180);

                // we stop the simulation of Window Resize after the animations are completed
                setTimeout(function() {
                    clearInterval(simulateWindowResize);
                }, 1000);
            });

            $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
                var $btn = $(this);

                if (white_color == true) {

                    $('body').addClass('change-background');
                    setTimeout(function() {
                        $('body').removeClass('change-background');
                        $('body').removeClass('white-content');
                    }, 900);
                    white_color = false;
                } else {

                    $('body').addClass('change-background');
                    setTimeout(function() {
                        $('body').removeClass('change-background');
                        $('body').addClass('white-content');
                    }, 900);

                    white_color = true;
                }


            });

            $('.light-badge').click(function() {
                $('body').addClass('white-content');
            });

            $('.dark-badge').click(function() {
                $('body').removeClass('white-content');
            });
        });
    });


    $(document).ready(function() {
        $().ready(function() {
            $sidebar = $('.sidebar');
            $navbar = $('.navbar');
            $main_panel = $('.main-panel');

            $full_page = $('.full-page');

            $sidebar_responsive = $('body > .navbar-collapse');
            sidebar_mini_active = true;
            white_color = false;

            window_width = $(window).width();

            fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



            $('.fixed-plugin a').click(function(event) {
                if ($(this).hasClass('switch-trigger')) {
                    if (event.stopPropagation) {
                        event.stopPropagation();
                    } else if (window.event) {
                        window.event.cancelBubble = true;
                    }
                }
            });

            $('.fixed-plugin .background-color span').click(function() {
                $(this).siblings().removeClass('active');
                $(this).addClass('active');

                var new_color = $(this).data('color');

                if ($sidebar.length != 0) {
                    $sidebar.attr('data', new_color);
                }

                if ($main_panel.length != 0) {
                    $main_panel.attr('data', new_color);
                }

                if ($full_page.length != 0) {
                    $full_page.attr('filter-color', new_color);
                }

                if ($sidebar_responsive.length != 0) {
                    $sidebar_responsive.attr('data', new_color);
                }
            });

            $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
                var $btn = $(this);

                if (sidebar_mini_active == true) {
                    $('body').removeClass('sidebar-mini');
                    sidebar_mini_active = false;
                    blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
                } else {
                    $('body').addClass('sidebar-mini');
                    sidebar_mini_active = true;
                    blackDashboard.showSidebarMessage('Sidebar mini activated...');
                }

                // we simulate the window Resize so the charts will get updated in realtime.
                var simulateWindowResize = setInterval(function() {
                    window.dispatchEvent(new Event('resize'));
                }, 180);

                // we stop the simulation of Window Resize after the animations are completed
                setTimeout(function() {
                    clearInterval(simulateWindowResize);
                }, 1000);
            });

            $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
                var $btn = $(this);

                if (white_color == true) {

                    $('body').addClass('change-background');
                    setTimeout(function() {
                        $('body').removeClass('change-background');
                        $('body').removeClass('white-content');
                    }, 900);
                    white_color = false;
                } else {

                    $('body').addClass('change-background');
                    setTimeout(function() {
                        $('body').removeClass('change-background');
                        $('body').addClass('white-content');
                    }, 900);

                    white_color = true;
                }


            });

            $('.light-badge').click(function() {
                $('body').addClass('white-content');
            });

            $('.dark-badge').click(function() {
                $('body').removeClass('white-content');
            });
        });
    });
</script>
    </div>
</div>
</body>
</html>