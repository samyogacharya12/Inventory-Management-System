<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="icon" type="image/png" href="resources/black-dashboard-html-v1.0.1/assets/img/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/css/util.css">
	<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/css/main.css">
		<link rel="stylesheet" type="text/css" href="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.css">
<meta charset="utf-8">
<title>Login</title>
<script type="text/javascript">
 function validateForm()
 {
   if(document.loginForm.username.value=="")
   {
     swal("username is empty");
     document.loginForm.username.focus();
     return false;
   }
   else if(document.loginForm.password.value=="")
   {
   swal("sorry!", "Your username or password is empty");
     document.loginForm.password.focus();
     return false;
   }
 }
</script>
</head>
<body>
					<p style="color: red"></p>
    	<div class="limiter">
		<div class="container-login100" style="background-image: url('static/black-dashboard-html-v1.0.1/assets/img/bg-01.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					Account Login
				</span>
    <form:form id="loginForm" name="loginForm" modelAttribute="login" action="login" class="login100-form validate-form p-b-33 p-t-5" method="post" onsubmit="return validateForm()">
        
		<div class="wrap-input100 validate-input" data-validate="Enter username">
						<input class="input100" type="text" name="username" placeholder="user name">
						<span class="focus-input100" data-placeholder="&#xe80f;"></span>
</div>
					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100" data-placeholder="&#xe80f;"></span>
					</div>
    <div class="container-login100-form-btn m-t-32">    
    <form:button id="login" class="login100-form-btn" name="login">Login</form:button>
    </div>
    </form:form>
    </div>
		</div>
	</div>
    	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/bootstrap/js/popper.js"></script>
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/daterangepicker/moment.min.js"></script>
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="static/black-dashboard-html-v1.0.1/assets/js/main.js"></script>
	    <script src="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.js"> </script> 
	<script src="static/black-dashboard-html-v1.0.1/assets/demo/sweetalert.min.js"> </script>  
</body>
</html>
