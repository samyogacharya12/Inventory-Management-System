<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}

.notification {
  background-color: #555;
  color: white;
  text-decoration: none;
  padding: 15px 26px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification:hover {
  background: red;
}

.notification .badge {
  position: absolute;
  top: -10px;
  right: -10px;
  padding: 5px 10px;
  border-radius: 50%;
  background-color: red;
  color: white;
}
</style>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../assets/img/favicon.png">
  <title>
    Black Dashboard by Creative Tim
  </title>
  <!--     Fonts and icons     -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
  <!-- Nucleo Icons -->
  <link href="static/black-dashboard-html-v1.0.1/assets/css/nucleo-icons.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css?v=1.0.0" rel="stylesheet" />
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="static/black-dashboard-html-v1.0.1/assets/demo/demo.css" rel="stylesheet" /></head>
<body>
  <div class="wrapper">
    <div class="sidebar">
      <!--
        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red"
    -->
      <div class="sidebar-wrapper">
        <div class="logo">
          <a href="javascript:void(0)" class="simple-text logo-mini">
          </a>
          <a href="javascript:void(0)" class="simple-text logo-normal">
     
          </a>
        </div>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
        <ul class="nav">
          <li class="active ">
            <a href="/">
              <i class="tim-icons icon-chart-pie-36"></i>
              <p>Dashboard</p>
            </a>
          </li>
          <li>
            <a href="/list-supplier">
              <i class="tim-icons icon-single-02"></i>
              <p>Suppliers</p>
            </a>
          </li>
          <li>
            <a href="/list-product">
            <i class="tim-icons icon-basket-simple"></i>
              <p>Products</p>
            </a>
          </li>
          <li>
            <a href="/list-Customer">
              <i class="tim-icons icon-single-02"></i>
              <p>Customers</p>
            </a>
          </li>
          <li>
            <a href="/list-user">
              <i class="tim-icons icon-single-02"></i>
              <p>Users</p>
            </a>
          </li>
     
          <li>
            <a href="/get-report">
             <i class="tim-icons icon-bag-16"></i>
              <p>Summary Report</p>
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
              <p>Logout</p>
            </a>
          </li>
         
        </ul>
        </div>
      </div>
    </div>
    
              <div class="main-panel">
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
     <div class="row"><div class="col-sm-12">
                    <a href="/addproduct" class="btn btn-primary btn-sm"> Add new </a>
                 
                  <table class="table" id="">
                  <thead class=" text-primary">
                   <tr>
                   
                   <th>
                   trash id
                   </th>
                   
                   <th>
                   product id
                   </th>
                   
                   <th>
                   product name
                   </th>
                   
                   <th>
                   product type
                   </th>
                   
                   <th>
                   price
                   </th>
                   
                   <th>
                   quantity
                   </th>
                   
                   <th>
                   magnifacture date
                   </th>
                   
                   <th>
                   expiry date
                   </th>
                   </tr>
                  </thead>
                        <tbody>
                 <c:forEach var="trashes" items="${trash}">
<tr>   
    <td> ${trashes.trashId} </td>
    <td>${trashes.productId } </td>
    <td> ${trashes.productName} </td>
    <td> ${trashes.productType} </td>
    <td> ${trashes.price} </td>
    <td> ${trashes.quantity} </td>
    <td> ${trashes.magnifactureDate}</td>
    <td> ${trashes.expiryDate}</td>
    </tr>
</c:forEach>                         
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
    
    </div>
</body>
</html>