<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="apple-touch-icon"  href="static/black-dashboard-html-v1.0.1/assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="static/black-dashboard-html-v1.0.1/assets/img/favicon.png">
<title>Supplier Information</title>
<link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/favicon.ico">
  <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
  <!-- Nucleo Icons -->
  <link href="static/black-dashboard-html-v1.0.1/assets/css/nucleo-icons.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css?v=1.0.0" rel="stylesheet" />
  <!-- CSS Just for demo purpose, don't include it in your project -->
	<link href="static/resources/jquery.dynatable.css" rel="stylesheet"/>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.js"> </script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"> </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="static/RestJs/supplierList.js"> </script>
    <script src="static/RestJs/listSupplierByName.js"> </script>
    <script src="static/RestJs/saveSupplierProduct.js"> </script>
    <script src="static/RestJs/listSupplierDataByDate.js"> </script>
    <script src="static/RestJs/supplierProductAdd.js" > </script>
    <script src="static/RestJs/supplierEdit.js" > </script>
    <script src="static/RestJs/updateSupplierPersonal.js" > </script>
    <script src="static/RestJs/updateSupplierProduct.js" > </script>
    <link href="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.css" rel="stylesheet"/>
    <script src="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.js"> </script>
	<style type="text/css" class="init">
        #supplierDateForm {
            display: none;
        }
        #saveSupplierProductForm
        {
            display: none;
        }
        #supplierPersonalUpdateForm
        {
            display: none;
        }

        #supplierProductUpdateForm
        {
          display: none;
        }
	</style>
    <script>
        $(document).ready(function () {
            $("#supplierList").on('click', 'a[id="supplierProductAdd"]', function (e) {
              $("#supplierList").hide();
              $("#SupplierProductAnalysisButton").hide();
              $("#saveSupplierProductForm").show();
              $("#filter").hide();
              $("#supplierDateForm").hide();
              $("#supplierForm").hide();
              $("#supplierPersonalAddButton").hide();
              $("#excelFile").hide();
              $("#pdfFile").hide();
              $("#supplierReportButton").hide();
            });
        });

        $(document).ready(function () {
            $("#supplierList").on('click', 'a[id="supplierEdit"]', function (e) {
                $("#supplierList").hide();
                $("#SupplierProductAnalysisButton").hide();
                $("#saveSupplierProductForm").hide();
                $("#supplierPersonalUpdateForm").show();
                $("#filter").hide();
                $("#supplierDateForm").hide();
                $("#supplierForm").hide();
                $("#supplierPersonalAddButton").hide();
                $("#excelFile").hide();
                $("#pdfFile").hide();
                $("#supplierReportButton").hide();
            });
        });
        $(document).ready(function () {
            $("#supplierProductInfoButton").click(function () {
                $("#supplierList").hide();
                $("#SupplierProductAnalysisButton").hide();
                $("#saveSupplierProductForm").hide();
                $("#supplierPersonalUpdateForm").hide();
                $("#supplierProductUpdateForm").show();
                $("#filter").hide();
                $("#supplierDateForm").hide();
                $("#supplierForm").hide();
                $("#supplierPersonalAddButton").hide();
                $("#excelFile").hide();
                $("#pdfFile").hide();
                $("#supplierReportButton").hide();
            })
        })

        $(document).ready(function () {
            $("#supplierProductBackButton").click(function () {
                $("#SupplierProductAnalysisButton").hide();
                $("#supplierPersonalUpdateForm").show();
                $("#supplierProductUpdateForm").hide();
            });
        });


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
   <li><a href="/"> <i class="tim-icons icon-chart-pie-36"></i> Dashboard <span class="fa fa-chevron-right"></span></a>
                  </li>
                  
                  <li><a href="/list-supplier">  <i class="tim-icons icon-single-02"></i> Supplier <span class="fa fa-chevron-right"></span></a></li>
                  <li><a href="/list-product">  <i class="tim-icons icon-basket-simple"></i> Product <span class="fa fa-chevron-right"></span></a>
                   
                  </li>
                 <li><a href="/list-Customer"> <i class="tim-icons icon-single-02"></i> Customer <span class="fa fa-chevron-right"></span></a>
                   
                  </li>
                    
                    <li><a href="/list-trash"> <i class="tim-icons icon-single-02"></i> User <span class="fa fa-chevron-right"></span></a>
                   
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
          <i class="tim-icons icon-trash-sismple"></i>
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
                    <form id="supplierForm" action="/getSupplierByName" method="get">
                        <select  name="supplierName" id="supplierFormSelect">

                        </select>
                        <button class="btn btn-primary btn-sm">Search</button>
                    </form>
                </div>
                </div>
                </div>
              
       <div class="row">
       <div class="col-sm-12">
          <a id="supplierPersonalAddButton" href="/getSupplierForm" class="btn btn-primary btn-sm"> Add new </a>
          <button id="filter"  class="btn btn-primary btn-sm">Filter</button>
          <a id="supplierReportButton" href="/getSupplierPersonalInfo" class="btn btn-primary btn-sm"> Supplier Report</a>
           <a id="SupplierProductAnalysisButton" href="/getSupplierProductAnalysis"  class="btn btn-primary btn-sm">Supplier Product Analysis </a>
           <form id="supplierDateForm" action="/getSupplierDataByDate" method="GET">
               <span class="icon"><i class="fa fa-search"></i></span>
               <input type="Date" id="supplyDateFirst" name="supplyStartDate" placeholder="Date..." />
               <input type="Date" id="supplyDateLast" name="supplyLastDate"  placeholder="Date..." />
               <button  type="submit" class="btn btn-fill btn-primary">Find</button>
           </form>
                  <table id="supplierList" class="table">
                    <a id="excelFile" href="/createExcelSupplier" style="float:right;"><img src="images/excelimg.png" style="width:80px;"> </a>
                     <a id="pdfFile" href="/createPdfSupplier" style="float:right;"><img src="images/Pdf_by_mimooh.svg.png" style="width:40px; margin-left:10px;"> </a>
                    <thead class="text-primary">
                      <tr>
                        <th>
                          Supplier Id
                        </th>
                        <th>
                          Supplier Name
                        </th>
                        <th>
                          Supplier Type
                        </th>
                        
                        <th class="text-center">
                          quantity
                        </th>
                        
                        <th>
                        cost
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
        
       
                    <tbody id="supplerData">
                    </tbody>


                      <tbody id="supplierDataByName">

                      </tbody>

                      <tbody id="supplierDataByDate">

                      </tbody>


                      <tr id="aggregateTables">

                      </tr>
                  </table>
                </div>
              </div>
           </div>
           </div>
                <div class="card-body">
                    <form id="saveSupplierProductForm" action="/save-supplierproduct" method="post">
                        <div class="col-sm-12 col-md-6">
                            <a  href="/list-supplier" class="btn btn-primary btn-sm"> Back</a>
                        </div>
                        <div class="col-md-4 px-md-1">
                            <div class="form-group">
                                <label>Supplier Id </label>
                                <input type="text" id="supplierId" name="supplierId" value="${supplierAdd.supplierId}" readonly class="form-control" />
                            </div>
                        </div>

                        <div class="col-md-4 pl-md-1">
                            <div  class="form-group has-label">
                                <label> Product Id</label>
                                <br>
                                <select id="productIdSelect" name="productId">
                                    <c:forEach var="product2" items="${product1}">
                                        <option  value="${product2.productId}">
                                                ${product2.productId}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 pr-md-1">
                                <div class="form-group has-label">
                                    <label> Cost </label>
                                    <input type="number" id="cost"  name="cost"  placeholder="" class="form-control" required="true"/>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-6 pr-md-1">
                                <div class="form-group has-label">
                                    <label> Buy Date </label>
                                    <input type="date" id="buyDate"  name="buyDate"  placeholder="" class="form-control" required="true"/>
                                </div>
                            </div>
                        </div>

                        <div class="card-footer">
                            <div class="col-md-4">
                                <!-- markup -->
                                <button id="supplierProductButton" class="btn btn-primary btn-fill">Save</button>
                                <!-- for more actions that you can use onclick, please check out assets/assets-for-demo/js/demo.js -->
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-body">
                    <form id="supplierPersonalUpdateForm" action="/update-supplierpersonal" method="post"  enctype="multipart/form-data">
                        <div i class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12 col-md-6">
                                    <div class="dataTables_length">
                                    </div>
                                    <a href="/list-supplier" class="btn btn-primary btn-sm"> Back</a>
                                </div>
                                <div class="col-sm-12 col-md-6">
                                    <div  class="dataTables_filter">
                                        <a id="supplierProductInfoButton"  class="btn btn-primary btn-sm">ProductInfo</a>
                                    </div>
                            </div>
                        </div>
                        <div class="row">
                            <input type="hidden" id="updateSupplierId" name="supplierId" value="${supplierEdit.supplierId}" class="form-control" />
                            <input type="hidden" id="supplierUniqueId" name="supplierUniqueId" value="${supplierEdit.supplierId}" class="form-control" />
                            <div class="col-md-3 px-md-1">
                                <div class="form-group">
                                    <label>Supplier Name</label>
                                    <input type="text" id="supplierName"   name="supplierName" value="${supplierEdit.supplierName}" placeholder="productname" class="form-control" />
                                </div>
                            </div>

                            <div class="col-md-3 px-md-1">
                                <div class="form-group">
                                    <label>Supplier Type</label>
                                    <input type="text" id="supplierType" name="supplierType" value="${supplierEdit.supplierType}" class="form-control" />
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-6 pr-md-1">
                                <div class="form-group">
                                    <label> Permanent Address  </label>
                                    <input type="text" id="permanentAddress"  name="permanentAddress" value="${supplierEdit.permanentAddress}" placeholder="" class="form-control" />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 pr-md-1">
                                <div class="form-group">
                                    <label> Temporary Address  </label>
                                    <input type="text" id="temporaryAddress"  name="temporaryAddress" value="${supplierEdit.temporaryAddress}" placeholder="" class="form-control" />
                                </div>
                            </div>
                        </div>

                            <div class="row">
                                <div class="col-md-6 pr-md-1">
                                    <div class="form-group">
                                        <label> Email  </label>
                                        <input type="email" id="email"  name="email" placeholder="" class="form-control" />
                                    </div>
                                </div>
                            </div>




                        <div class="col-md-4 px-md-1">
                            <div class="form-group">
                                <label>Image </label>
                                <input type="file" id="file" name="file" value="${supplierEdit.image}" />
                            </div>
                        </div>


                        <div class="card-footer">
                            <div class="col-md-4">
                                <button id="updateSupplierPersonal" type="submit" class="btn btn-fill btn-primary">Save</button>
                            </div>
                        </div>
                        </div>
                    </form>

                </div>

                <div class="card-body">
                    <form id="supplierProductUpdateForm" action="/update-supplierproduct" method="post">
                        <a id="supplierProductBackButton" class="btn btn-primary btn-sm">Back</a>
                        <div class="col-md-4 px-md-1">
                            <div class="form-group">
                                <label>Supplier Id </label>
                                <input type="text" id="supplierProductId" name="supplierId"  value="${supplierproduct.supplierId}" readonly class="form-control"/>
                            </div>
                        </div>


                        <div class="col-md-4 px-md-1">
                            <div class="form-group">
                                <label>Product Id </label>
                                <select id="productId" name="productId" class="chosen">
                                </select>
                            </div>
                        </div>

                        <div class="col-md-4 pl-md-1">
                            <div class="form-group">
                                <label> Quantity</label>
                                <input type="number" id="quantity" name="quantity" value="${supplierproduct.quantity}" placeholder="" class="form-control"/>
                            </div>
                        </div>

                        <div class="row">
                        <div class="col-md-6 pr-md-1">
                            <div class="form-group">
                                <label> Cost </label>
                                <input type="number" id="supplierProductCost"  name="cost" value="${supplierproduct.cost}" placeholder="" class="form-control" />
                            </div>
                        </div>
                </div>


                        <div class="row">
                            <div class="col-md-6 pr-md-1">
                                <div class="form-group">
                                    <label> buyDate </label>
                                    <input type="text" id="supplierProductBuyDate"  name="buyDate" value="${supplierproduct.cost}" placeholder="" class="form-control" />
                                </div>
                            </div>
                        </div>


                        <div class="card-footer">
                            <div class="col-md-4">
                                <button id="supplierProductUpdateButton" type="submit"  class="btn btn-fill btn-primary">Save</button>
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
             $("#filter").click(function() {
                 $("#supplierDateForm").show();
             });
         });
  </script>
  <script>
function goBack() {
  window.history.back();
}
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
</body>
<script type="text/javascript">
		$(".chosen").chosen();
		</script>
</html>