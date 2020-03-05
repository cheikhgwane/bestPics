<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BestPics</title>
<!-- Bootstrap CSS File -->
<link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<!-- Libraries CSS Files -->
<link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/ionicons/css/ionicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<style>
#register_form{
	margin: 4% 0 0 0;
    width: 100%;
    box-shadow: 0px 0px 12px 0px rgba(0, 0, 0, 0.1);
    padding: 8%;
    overflow: hidden;
}
</style>
</head>
<body>
	<div>
		<div>
			<c:import url="include/entete.jsp" />
		</div>

		<div style="margin-top: 8%" class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<c:import url="include/menu.jsp" />
				</div>
				<div class="col-md-4">
					<div id="register_form">
						<div class="form">
							<form action="${pageContext.request.contextPath}/user/add" method="post" role="form"
								class="contactForm">
								<div class="form-group">
									<label for="nom">Nom</label> <input type="text"
										class="form-control" name="nom" id="nom"
										aria-describedby="error"> <small
										style="color: red !important" id="error"
										class="form-text text-muted">${error.nom}</small>
								</div>
								<div class="form-group">
									<label for="prenom">Prénom</label> <input type="text"
										class="form-control" name="prenom" id="prenom"
										aria-describedby="error"> <small
										style="color: red !important" id="error"
										class="form-text text-muted">${error.prenom}</small>
								</div>
								<div class="form-group">
									<label for="login">Login</label> <input type="text"
										class="form-control" name="login" id="login"
										aria-describedby="error"> <small
										style="color: red !important" id="error"
										class="form-text text-muted">${error.login}</small>
								</div>
								<c:choose>
									<c:when test="${sessionScope.utilisateur.admin}">
										<div class="form-check">
											<input type="checkbox" name="isAdmin" value="isAdmin"
												class="form-check-input" id="isAdmin"> <label
												class="form-check-label" for="isAdmin">Administrateur</label>
										</div>
									</c:when>
								</c:choose>
								<div class="form-group">
									<label for="password">Mot de passe</label> <input
										type="password" name="password" class="form-control"
										id="exampleInputPassword1" placeholder="Password"> <small
										style="color: red !important" id="error"
										class="form-text text-muted">${error.password}</small>
								</div>
									<p style="color:red;">${requestScope.status}</p>
								<div style="margin-top: 10px" class="text-center">
									<button class="btn btn-primary" type="submit"
										title="se connecter">Ajouter</button>
								</div>
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>