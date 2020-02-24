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
		<button style="float: right" class="btn btn-primary "
			data-toggle="modal" data-target="#addImageModal" title="Ajouter">Ajouter
			photo</button>
		<section id="portfolio" class="clearfix">
			<div class="container">

				<form action="${pageContext.request.contextPath}/image/add"
					method="post" role="form" enctype="multipart/form-data"
					class="contactForm">
			
					<div class="form-group">
						<label for="title">Titre</label> <input required type="text"
							disabled class="form-control" value="${requestScope.image.title}" name="title" id="title">
					</div>
					<div class="form-group">
						<label for="description">Description</label> <input required
							type="text" class="form-control" value="${requestScope.image.description}" name="description" disabled
							id="description">
					</div>
					<div class="form-group">
						<label for="title">Mots clés</label> <input required type="text"
							class="form-control" value="${requestScope.image.keywords }" disabled name="keywords" id="keywords">
					</div>
			</form>
			</div>
		</section>
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