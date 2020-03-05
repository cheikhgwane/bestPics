<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Image</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" />
<!-- #about -->
<link
	href="${pageContext.request.contextPath}/lib/animate/animate.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/lib/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/lib/lightbox/css/lightbox.min.css"
	rel="stylesheet" />
</head>
<body>
	<div>
		<c:import url="include/entete.jsp" />
	</div>

	<div style="margin-top: 8%" class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<div class="card" style="float: left; margin: 5px;">
					<img class="card-img-top"
						src="${pageContext.request.contextPath}/image/getOne?imageUrl=${image.fileImage}"
						alt="cover">

				</div>
			</div>
			<div class="col-md-3">
				<c:choose>
					<c:when
						test="${sessionScope.utilisateur.id eq image.album.proprietaire.id }">
						<form class="contactForm" method="post"
							action="${pageContext.request.contextPath}/image/modify">
							<input type="hidden" name="imageId"
								value="${requestScope.image.id}" />
							<div class="form-group">
								<label for="title">Titre</label> <input required type="text"
									class="form-control" value="${requestScope.image.titre}"
									name="title" id="title">
							</div>
							<div class="form-group">
								<label for="description">Date création</label> <input disabled
									required type="text" class="form-control"
									value="${requestScope.image.dateCreation}" name="dateCreation"
									id="dateCreation">
							</div>
							<div class="form-group">
								<label for="description">Description</label> <input required
									type="text" class="form-control"
									value="${requestScope.image.description}" name="description"
									id="description">
							</div>
							<div class="form-group">
								<label for="keywords">Mots clés</label> <input required
									type="text" class="form-control"
									value="${requestScope.image.keywords}" name="keywords"
									id="keywords">
							</div>
							<div class="form-group">
								<label for="width_height">Dimensions</label> <input required
									type="text" class="form-control" disabled
									value="${requestScope.image.hauteur} x ${requestScope.image.largeur}"
									name="keywords" id="keywords">
							</div>
							<div style="margin-top: 10px" class="text-center">
								<button class="btn btn-primary" type="submit" title="Modifier">Modifier</button>
							</div>

						</form>
					</c:when>
					<c:otherwise>
						<form class="contactForm">
							<div class="form-group">
								<label for="title">Titre</label> <input required type="text"
									class="form-control" disabled value="${requestScope.image.titre}"
									name="title" id="title">
							</div>
							<div class="form-group">
								<label for="description">Description</label> <input required
									type="text" class="form-control" disabled
									value="${requestScope.image.description}" name="description"
									id="description">
							</div>
							<div class="form-group">
								<label for="keywords">Mots clés</label> <input required
									type="text" class="form-control"
									value="${requestScope.image.keywords}" disabled name="keywords"
									id="keywords">
							</div>
							<div class="form-group">
								<label for="width_height">Dimensions</label> <input required
									type="text" class="form-control" disabled
									value="${requestScope.image.hauteur} x ${requestScope.image.largeur}"
									name="keywords" id="keywords">
							</div>
						</form>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>

	<script src="lib/jquery/jquery.min.js"></script>
	<script src="lib/jquery/jquery-migrate.min.js"></script>
	<script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/mobile-nav/mobile-nav.js"></script>
	<script src="lib/wow/wow.min.js"></script>
	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/counterup/counterup.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<script src="lib/isotope/isotope.pkgd.min.js"></script>
	<script src="lib/lightbox/js/lightbox.min.js"></script>
	<!-- Template Main Javascript File -->
	<script src="js/main.js"></script>
</body>
</html>