<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet" />
<title>Connexion</title>
<style>
</style>
</head>
</head>
<body>
	<div>
		<c:import url="include/entete.jsp" />
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<img src="img/head.svg" alt="" class="img-fluid" />
			</div>
			<div class="col-md-4">
				<div id="contact">
					<div class="form">
						<form action="" method="post" role="form" class="contactForm">

							<div class="form-group">
								<label for="login">Login</label> <input type="text"
									class="form-control" name="login" id="login"
									aria-describedby="error"> <small
									style="color: red !important" id="error"
									class="form-text text-muted">${error.login}</small>
							</div>
							<div class="form-group">
								<label for="password">Mot de passe</label> <input
									type="password" name="password" class="form-control"
									id="exampleInputPassword1" placeholder="Password"> <small
									style="color: red !important" id="error"
									class="form-text text-muted">${error.password}</small>
							</div>
							<div style="margin-top: 10px" class="text-center">
								<button type="submit" title="se connecter">Se connecter</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



