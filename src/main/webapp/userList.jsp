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
  <!-- Nucleo Icons -->
  <link href="static/black-dashboard-html-v1.0.1/assets/css/nucleo-icons.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css?v=1.0.0" rel="stylesheet" />
  <!-- CSS Just for demo purpose, don't include it in your project -->
	<link rel="stylesheet" type="text/css" href="static/media/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="static/resources/syntax/shCore.css">
	<link rel="stylesheet" type="text/css" href="static/resources/demo.css">
	<style type="text/css" class="init">
	
	</style>
	<script type="text/javascript"  src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"  src="static/media/js/jquery.dataTables.js"></script>
	<script type="text/javascript"  src="static/resources/syntax/shCore.js"></script>
	<script type="text/javascript"  src="static/resources/demo.js"></script>
</head>
<body class="nav-md">
    <div class="wrapper">
    <div class="sidebar">
    <div class="sidebar-wrapper ps">
    <div class="logo">
     <a href="javascript:void(0)" class="simple-text logo-mini"> 
     </a>
     <a href="javascript:void(0)" class="simple-text logo-normal">
    <b>Welcome ${username}</b>
     </a> 
     <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
     <ul class="nav">
          <li class="active ">
            <a href="/">
              <i class="tim-icons icon-chart-pie-36"></i>
              Dashboard
            </a>
          </li>
          <li>
            <a href="/list_supplier">
              <i class="tim-icons icon-single-02"></i>
              Suppliers
            </a>
          </li>
          <li>
            <a href="/list_product"">
            <i class="tim-icons icon-basket-simple"></i>
              Products
            </a>
          </li>
          <li>
            <a href="/list_Customer">
              <i class="tim-icons icon-single-02"></i>
              Customers
            </a>
          </li>
          <li>
            <a href="/get_list">
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
            <a href="/get_expenses">
          <i class="tim-icons icon-notes"></i>
              Expenses
            </a>
          </li>
          
          
            <li>
            <a href="/get_trash">
          <i class="tim-icons icon-trash-simple"></i>
              Trash
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
            <br>
            <a class="navbar-brand" href="javascript:void(0)"></a>
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
          </button>
          </div>
          </nav>
          
           <div class="content">
        <div class="col-md-8 ml-auto mr-auto">
          <h2 class="text-center"></h2>
          <p class="text-center">
          </p>
        </div>    
    <div class="row mt-5">
          <div class="col-md-12">
            <div class="card">
              <div class="card-body">
                <div class="toolbar">
                  <!--        Here you can write extra buttons/actions for the toolbar              -->
                </div>
                <div id="datatable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                <div class="row">
                <div class="col-sm-12 col-md-6">
                <div class="dataTables_length" id="datatable_length">
                </div></div>
              
               <div class="col-sm-12 col-md-6">
                <div id="datatable_filter" class="dataTables_filter">
                
                <form action="/getUserByName" method="get">
                  <label><input type="search" name="username" class="form-control form-control-sm" placeholder="Search records" aria-controls="datatable"></label>
                <button class="btn btn-primary btn-sm">Search</button>
                </form>
                </div></div></div>
              
       <div class="row">
       <div class="col-sm-12">
          <a href="/user_Form" class="btn btn-primary btn-sm"> Add new </a>
                  <table class="table" id="">
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
        
                    <tbody>
                 <c:forEach var="users" items="${user}">
<tr>   
    <td> ${users.user_id} </td>
    <td><a href="username?user_id=${users.user_id}"> ${users.username} </a> </td>
    <td> ${users.password} </td>
    <td> ${users.first_name} </td>
    <td> ${users.middle_name} </td>
    <td> ${users.last_name} </td>
    <td> ${users.user_role_id}</td>
    <td> <a href="user?user_id=${users.user_id}" class="btn btn-link btn-warning btn-icon btn-sm edit"><i class="tim-icons icon-pencil"></i></a> </td>
    <td> <a href="deleteuser?user_id=${users.user_id}" class="btn btn-link btn-danger btn-icon btn-sm remove"><i class="tim-icons icon-simple-remove"></i></a> </td>
    </tr>
</c:forEach>                         
</tbody>

        
<tbody>
<tr>   
    <td> ${username.user_id} </td>
    <td><a href="username?user_id=${username.user_id}"> ${username.username} </a> </td>
    <td> ${username.password} </td>
    <td> ${username.first_name} </td>
    <td> ${username.middle_name} </td>
    <td> ${username.last_name} </td>
    <td> ${username.user_role_id}</td>
    <td> <a href="user?user_id=${username.user_id}" class="btn btn-link btn-warning btn-icon btn-sm edit"></a> </td>
    <td> <a href="deleteuser?user_id=${username.user_id}" class="btn btn-link btn-danger btn-icon btn-sm remove"></a> </td>
    </tr>
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
         </div>
         </div>                        
  <!--   Core JS Files   -->
  
   <script src="static/resources/jquery.tablesorter.js.download"></script>
   <script src="static/resources/jquery.dataTables.min.js.download"></script>
  <script src="static/black-dashboard-html-v1.0.1/assets/js/core/jquery.min.js"></script>
  <script src="static/black-dashboard-html-v1.0.1/assets/js/core/popper.min.js"></script>
  <script src="static/black-dashboard-html-v1.0.1/assets/js/core/bootstrap.min.js"></script>
  <script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!--  Google Maps Plugin    -->
  <!-- Place this tag in your head or just before your close body tag. -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <!-- Chart JS -->
  <script src="../assets/js/plugins/chartjs.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="../assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="../assets/js/black-dashboard.min.js?v=1.0.0"></script>
  <!-- Black Dashboard DEMO methods, don't include it in your project! -->
  <script src="../assets/demo/demo.js"></script>
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
  </script>
  
  <script>
    $(document).ready(function() {
      $('#datatable').DataTable({
        "pagingType": "full_numbers",
        "lengthMenu": [
          [10, 25, 50, -1],
          [10, 25, 50, "All"]
        ],
        responsive: true,
        language: {
          search: "_INPUT_",
          searchPlaceholder: "Search records",
        }
      });

      var table = $('#datatable').DataTable();
      // Edit record
      table.on('click', '.edit', function() {
        $tr = $(this).closest('tr');
        var data = table.row($tr).data();
        alert('You press on Row: ' + data[0] + ' ' + data[1] + ' ' + data[2] + '\'s row.');
      });
    });
  </script>
 <script>
$('select').autocompleteDropdown();
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="static/Autocomplete-Dropdown-List-Plugin-jQuery/dist/js/jquery.autocompleteDropdown-min.js"></script>
</body>
</html>