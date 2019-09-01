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
	<link href="static/resources/jquery.dynatable.css" rel="stylesheet"/>
    <link href="static/resources/jquery.dynatable.css" rel="stylesheet"/>
    <style type="text/css" class="init">
    </style>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.js"> </script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"> </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="static/RestJs/customerList.js"> </script>
    <script src="static/RestJs/saveCurrentCustomerProduct.js"> </script>
    <script src="static/RestJs/saveNewCustomerProduct.js"> </script>
    <script src="static/RestJs/addNewCustomerProduct.js"> </script>
    <script src="static/RestJs/addCurrentCustomerProduct.js"> </script>
    <script src="static/RestJs/listCustomerByName.js"> </script>
    <script src="static/RestJs/customerEdit.js"> </script>
    <script src="static/RestJs/updateCustomerPersonal.js"> </script>
    <script src="static/RestJs/updateCustomerProduct.js"> </script>
    <script src="static/RestJs/listCustomerDatabyDate.js"> </script>
    <link href="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.css" rel="stylesheet"/>
    <script src="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.js"> </script>
    <style type="text/css" class="init">
        #customerDateForm {
            display: none;
        }

        #newCustomerProductCard{
            display: none;
        }

        #currentCustomerProductCard
        {
            display: none;
        }

        #updateCustomerPersonalCard
        {
            display: none;
        }

        #updateCustomerProductForm
        {
            display: none;
        }

    </style>
    <script>
        $(document).ready(function() {
            $("#filter").click(function() {
                $("#customerDateForm").show();
            });
        });
         $(document).ready(function () {
             $("#customerList").on('click', 'a[id="customerEdit"]', function (e) {
                 $("#updateCustomerPersonalCard").show();
                 $("#updateCustomerProductForm").hide();
                 $("#newCustomerProductCard").hide();
                 $("#customerDateForm").hide();
                 $("#searchCustomer").hide();
                 $("#customerList").hide();
                 $("#createExcelCustomer").hide();
                 $("#createPdfCustomer").hide();
                 $("#filter").hide();
                 $("#newCustomerPurchaseButton").hide();
                 $("#addNewCustomerButton").hide();
             })
         })


        $(document).ready(function () {
            $("#newCustomerPurchaseButton").click(function () {
                $("#newCustomerProductCard").show();
                $("#customerDateForm").hide();
                $("#searchCustomer").hide();
                $("#customerList").hide();
                $("#createExcelCustomer").hide();
                $("#createPdfCustomer").hide();
                $("#filter").hide();
                $("#newCustomerPurchaseButton").hide();
                $("#addNewCustomerButton").hide();
            })
        })
         $(document).ready(function () {
             $("#customerList").on('click', 'a[id="addCurrentCustomerProduct"]', function (e) {
                 $("#newCustomerProductCard").hide();
                 $("#customerDateForm").hide();
                 $("#searchCustomer").hide();
                 $("#customerList").hide();
                 $("#createExcelCustomer").hide();
                 $("#createPdfCustomer").hide();
                 $("#filter").hide();
                 $("#newCustomerPurchaseButton").hide();
                 $("#addNewCustomerButton").hide();
                 $("#currentCustomerProductCard").show();
             })
         })
        $(document).ready(function () {
            $("#customerProductInfoButton").click(function () {
                $("#updateCustomerPersonalForm").hide();
                $("#updateCustomerProductForm").show();
                $("#newCustomerProductForm").hide();
                $("#customerDateForm").hide();
                $("#searchCustomer").hide();
                $("#customerList").hide();
                $("#createExcelCustomer").hide();
                $("#createPdfCustomer").hide();
                $("#filter").hide();
                $("#newCustomerPurchaseButton").hide();
                $("#addNewCustomerButton").hide();
            })
        })

        $(document).ready(function () {
            $("#customerProductBackButton").click(function () {
                  $("#updateCustomerPersonalForm").show();
                  $("#updateCustomerProductForm").hide();
            })
        })




    </script>

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
     <li><a href="/"> <i class="tim-icons icon-chart-pie-36"></i> HOME <span class="fa fa-chevron-right"></span></a>
                  </li>
                  
                  <li><a href="/list-supplier">  <i class="tim-icons icon-single-02"></i> Supplier <span class="fa fa-chevron-right"></span></a></li>
                  <li><a href="/list-product">  <i class="tim-icons icon-basket-simple"></i> Product <span class="fa fa-chevron-right"></span></a>
                   
                  </li>
                 <li><a href="/list-Customer"> <i class="tim-icons icon-single-02"></i> Customer <span class="fa fa-chevron-right"></span></a>
                   
                  </li>
                    
                    <li><a href="/list-user"> <i class="tim-icons icon-single-02"></i> User <span class="fa fa-chevron-right"></span></a>
                   
                  </li>


                    <li>
            <a href="/get-report">
             <i class="tim-icons icon-bag-16"></i>
              Summary Report
            </a>
          </li>
          
             <li>
            <a href="/list-expense">
          <i class="tim-icons icon-notes"></i>
              Expenses
            </a>
          </li>
          
            <li>
            <a href="/list-trash">
          <i class="tim-icons icon-trash-simple"></i>
              Trash
            </a>
          </li>
          

                   <li><a href="/logout">  <i class="icon-double-right"></i>Log Out <span class="fa fa-chevron-right"></span></a> </li>
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
                <div class="modal modal-search fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- End Navbar -->
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
                <form id="searchCustomer" action="/getCustomerByName" method="get">
                <label><input type="search" id="customerName" name="customerName" class="form-control form-control-sm" placeholder="Search records" aria-controls="datatable"></label>
                <button class="btn btn-primary btn-sm">Search</button>
                </form>
                </div></div></div>
         
                <div class="row"><div class="col-sm-12">
                    <a id="addNewCustomerButton" href="/getCustomerForm" class="btn btn-primary btn-sm"> Add new </a>
                      <button id="filter" class="btn btn-primary btn-sm">Filter</button>
                    <form id="customerDateForm" action="/getCustomerDataByDate" method="GET">
                        <span class="icon"><i class="fa fa-search"></i></span>
                        <input type="Date" id="sellStartDate" name="sellStartDate" placeholder="Date..." />
                        <input type="Date" id="sellLastDate" name="sellLastDate"  placeholder="Date..." />
                        <button  type="submit" class="btn btn-fill btn-primary">Find</button>
                    </form>
                <table id="customerList" class="table">
                    <a id="newCustomerPurchaseButton" class="btn btn-primary btn-sm"> New Customer Product </a>
                <a id="createExcelCustomer" href="/createExcel" style="float:right;">
                <img src="images/excelimg.png" style="width:70px;">
                </a>
                <a id="createPdfCustomer" href="/createPdf" style="float:right;"><img src="images/Pdf_by_mimooh.svg.png" style="width:40px;" margin-left:10px;> </a>

                  <thead class=" text-primary">
                   <tr>
                   <th>
                   customer id
                   
                   </th>
                   
                   <th>
                   customer name
                   </th>
                   
                   <th>
                   quantity
                   </th>
                   
                   <th>
                   amount
                   </th>
                   
                   <th>
                   buy date
                   </th>
                   
                   <th>
                   permanent address
                   </th>
                   
                   <th>
                   temporary address
                   </th>
                   
                   <th>
                   phone number
                   </th>
                   
                   
                   <th>
                   product id
                   </th>
                   
                   <th>
                   product name
                   </th>

                     <th>
                      Added By
                     </th>

                   </tr>
                  </thead>

                    <tbody id="customerData">
                    </tbody>

                    <tbody id="getCustomerByName">

                    </tbody>

                    <tbody id="getCustomerDataByDate">

                    </tbody>

                    <tr id="aggregateTables">

                    </tr>
                      </table>
                </div>
                    <div id="newCustomerProductCard" class="card-body">
                        <div class="col-sm-12 col-md-6">
                            <a href="/list-Customer" class="btn btn-primary btn-sm"> Back</a>
                        </div>
                        <form id="newCustomerProductForm" action="/saveNewCustomerProduct" method="post">
                            <div class="col-md-3 px-md-1">
                                <div class="form-group">
                                    <label> Customer Id </label>
                                    <input type="text" id="customerIdAdd"  name="customerId"  class="form-control" />
                                </div>
                            </div>

                            <div class="col-md-4 pl-md-1">
                                <div class="form-group has-label">
                                    <label> Product Id</label>
                                    <select id="productIdAdd" name="productIdAdd">
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4 pl-md-1">
                                <div class="form-group">
                                    <label> Quantity</label>
                                    <input type="number" id="quantity" name="quantity"  placeholder="" class="form-control"/>
                                </div>
                            </div>



                            <div class="col-md-4 px-md-1">
                                <div class="form-group has-label">
                                    <label>Buy Date </label>
                                    <input type="date" id="buyDate" name="buyDate" placeholder="" class="form-control" required="true"/>
                                </div>
                            </div>


                            <div class="card-footer">
                                <div class="col-md-4">
                                    <button id="newCustomerProduct" type="submit" class="btn btn-fill btn-primary">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div id="currentCustomerProductCard" class="card-body">
                        <form id="currentCustomerProductForm" action="/saveCustomerProduct" method="post">
                            <div class="col-sm-12 col-md-6">
                                <a href="/list-Customer" class="btn btn-primary btn-sm"> Back</a>
                            </div>
                            <div class="col-md-3 px-md-1">
                                <div class="form-group">
                                    <label> Customer Id </label>
                                    <input type="text" id="currentCustomerId"  name="customerId" value="${customer.customerId}"  readonly class="form-control" />
                                </div>
                            </div>

                            <div class="col-md-5 pr-md-1">
                                <div class="form-group">
                                    <div>
                                        <label> product id </label>
                                        <select id="currentProductId" name="productId">
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4 pl-md-1">
                                <div class="form-group">
                                    <label> Quantity</label>
                                    <input type="number" id="currentQuantity" name="quantity"  placeholder="" class="form-control"/>
                                </div>
                            </div>


                            <div class="col-md-4 px-md-1">
                                <div class="form-group has-label">
                                    <label>Buy Date </label>
                                    <input type="date" id="currentBuyDate" name="buyDate" placeholder="" class="form-control" required="true"/>
                                </div>
                            </div>


                            <div class="card-footer">
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-fill btn-primary">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="updateCustomerPersonalCard" align="left" class="card-body">
                        <div class="col-sm-12 col-md-6">
                        <form id="updateCustomerPersonalForm" action="/updateCustomerPersonalData" method="post"  enctype="multipart/form-data">
                            <div class="col-sm-12 col-md-6">
                                <a href="/list-Customer" class="btn btn-primary btn-sm"> Back</a>
                            </div>
                            <div  class="dataTables_filter">
                                <a id="customerProductInfoButton" class="btn btn-primary btn-sm">ProductInfo</a>
                            </div>

                                <input type="hidden" id="customerIdPersonal" name="customerId" value="${customerView.customerId}" class="form-control" />
                            <input type="hidden" id="productIdPersonal" name="productId" value="${customerView.customerId}" class="form-control" />
                            <div class="row">
                                <div class="col-md-6 pr-md-1">
                                    <div class="form-group">
                                        <label>customer name</label>
                                        <input type="text"  id="customerNamePersonal"  name="customerName" value="${customerView.customerName}" placeholder="productname" class="form-control" />
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-6 pr-md-1">
                                    <div class="form-group">
                                        <label>permanent address </label>
                                        <input type="text" id="permanentAddressPersonal" id="permanentAddress" name="permanentAddress" value="${customerView.permanentAddress}" class="form-control" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                            <div class="col-md-6 pr-md-1">
                                    <div class="form-group">
                                        <label> temporary address  </label>
                                        <input type="text" id="temporaryAddressPersonal" id="temporaryAddress"  name="temporaryAddress" value="${customerView.temporaryAddress}" placeholder="" class="form-control" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 pr-md-1">
                                    <div class="form-group">
                                        <label> phone number  </label>
                                        <input type="text" id="phoneNumber"  name="phoneNumber" value="${customerView.phoneNumber}" placeholder="" class="form-control" />
                                    </div>
                                </div>
                            </div>


<%--                            <div class="row">--%>
<%--                                <div class="col-md-6 pr-md-1">--%>
<%--                                    <div class="form-group">--%>
<%--                                        <label> email  </label>--%>
<%--                                        <input type="email" id="email"  name="email" value="${customerView.phoneNumber}" placeholder="" class="form-control" />--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>





                            <div class="card-footer">
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-fill btn-primary">Save</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
                    <div class="card">
                    <div  align="left" class="card-body">
                        <div class="left-auto">
                        <form id="updateCustomerProductForm" action="/updateCustomerProduct" method="post"  enctype="multipart/form-data">
                            <div  class="dataTables_filter">
                            <a id="customerProductBackButton" class="btn btn-primary btn-sm">Back</a>
                            </div>
                            <div class="col-md-4 px-md-1">
                                <div class="form-group">
                                    <label>Customer Id </label>
                                    <input type="text" id="customerIdProduct" name="customerId" value="${customerProduct.customerId}" readonly class="form-control" />
                                </div>
                            </div>


                            <div class="col-md-4 px-md-1">
                                <div class="form-group">
                                    <label>Product Id </label>
                                    <input type="text" id="productId" name="productId" value="${customerProduct.productId}"  readonly placeholder="" class="form-control" />
                                </div>
                            </div>




                            <div class="col-md-4 px-md-1">
                                <div class="form-group">
                                    <input type="hidden" id="customerPurchaseId" name="customerPurchaseId" value="${customerProduct.customerPurchaseId}" placeholder="" class="form-control" />
                                </div>
                            </div>

                            <div class="col-md-4 pl-md-1">
                                <div class="form-group">
                                    <label> Quantity</label>
                                    <input type="number" id="quantityCustomerProduct" name="quantity" value="${customerProduct.quantity}" placeholder="" class="form-control"/>
                                </div>
                            </div>

                            <div class="col-md-4 pl-md-1">
                                <div class="form-group">
                                    <label> Buy Date</label>
                                    <input type="text" id="buyDateCustomerProduct" name="buyDate" value="${customerProduct.buyDate}" placeholder="" class="form-control"/>
                                </div>
                            </div>



                            <div class="row">
                                <div class="col-md-6 pr-md-1">
                                    <div class="form-group">
                                        <input type="hidden" id="amount"  name="amount" value="${customerproduct.amount}" placeholder="" class="form-control" />
                                    </div>
                                </div>
                            </div>


                            <div class="card-footer">
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-fill btn-primary">Save</button>
                                </div>
                            </div>
                        </form>
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
           </div>      
  <!--   Core JS Files   -->
  
	<script src="static/jquery.tablesorter.js.download"></script>
	<script src="static/jquery.tablesorter.js.download"> </script>
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

      // Delete a record
      table.on('click', '.remove', function(e) {
        $tr = $(this).closest('tr');
        table.row($tr).remove().draw();
        e.preventDefault();
      });

      //Like record
      table.on('click', '.like', function() {
        alert('You clicked on Like button');
      });
    });
  </script>

  
  
  
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
    
  var html ='<div class="table">\
  <div class="text-primary">\
  <form action="/getcustomerbyDate" method="get">\
      <span class="icon"><i class="fa fa-search"></i></span>\
      <input type="Date" name="sellDate[]" id="search" placeholder="Date..." />\
       <input type="Date" name="sellDate[]" id="search1" placeholder="Date..." />\
       <button type="submit" class="btn btn-fill btn-primary">Find</button>\
       </form>\
  </div>\
</div>';

function search(elem) {
  elem.outerHTML = html;
}
  </script>
    </div>
</body>
</html>