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

		<c:choose>
			<c:when
				test="${sessionScope.utilisateur.id eq requestScope.album.proprietaire.id }">
				<h4>
					<a
						href="${pageContext.request.contextPath}/album/albumInfo?albumId=${requestScope.album.id}">${requestScope.album.nomAlbum}(${requestScope.albumSize}
						images)</a>

				</h4>
				
				

		<button style="float: right" class="btn btn-outline-primary"
			data-toggle="modal" data-target="#addImageModal"
			title="Ajouter photo">Ajouter photo</button>

		<button style="float: right" class="btn btn-outline-primary"
			data-toggle="modal" data-target="#shareModal" title="Partager">Inviter</button>
			</c:when>
			<c:otherwise>
				<h4>${requestScope.album.nomAlbum}(${requestScope.albumSize} image)</h4>
			</c:otherwise>
		</c:choose>
		<section id="portfolio" class="clearfix">
			<div class="container">

				<div class="row portfolio-container">

					<c:if test="${empty requestScope.album.images }">
						<h3>Album Vide</h3>
					</c:if>
					<c:forEach items="${requestScope.album.images}" var="image">
						<div class="col-lg-4 col-md-6 portfolio-item filter-web"
							data-wow-delay="0.1s">
							<div class="portfolio-wrap">
								<img
									src="${pageContext.request.contextPath}/image/getOne?imageUrl=${image.fileImage}"
									class="img-fluid" alt="" />
								<div class="portfolio-info">
									<h4>
										<a
											href="${pageContext.request.contextPath}/image/imgInfo?imageId=${image.id}">${image.titre}</a>
									</h4>
									<p>${image.description}</p>
									<div>
										<a
											href="${pageContext.request.contextPath}/image/getOne?imageUrl=${image.fileImage}"
											class="link-preview" data-lightbox="portfolio"
											data-title="${image.titre}" title="${image.titre}"><i
											class="ion ion-eye"></i></a> <a
											href="${pageContext.request.contextPath}/image/delete?imageId=${image.id}"
											class="btn btn-primary" title="Supprimer"><i
											class="ion ion-ios-trash"></i></a>
									</div>
								</div>
							</div>

						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</div>


	<!-- add Modal -->
	<div class="modal fade" id="addImageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Ajouter
						image</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/image/add"
						method="post" role="form" enctype="multipart/form-data"
						class="contactForm">
						<input type="hidden" name="albumId"
							value="${requestScope.album.id}" />
						<div class="form-group">
							<label for="title">Titre</label> <input required type="text"
								class="form-control" name="title" id="title">
						</div>
						<div class="form-group">
							<label for="description">Description</label> <input required
								type="text" class="form-control" name="description"
								id="description">
						</div>
						<div class="form-group">
							<label for="title">Mots clés</label> <input required type="text"
								class="form-control" name="keywords" id="keywords">
						</div>
						<div class="form-group">
							<label for="login">Fichier</label> <input required type="file"
								class="form-control" name="imageFile" id="albumCover">
						</div>
						<div style="margin-top: 10px" class="text-center">
							<button class="btn btn-primary" type="submit" title="Ajouter">Ajouter
								photo</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="shareModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Ajouter
						image</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/album/share"
						method="post" role="form" enctype="multipart/form-data"
						class="contactForm">
						<input type="hidden" name="albumId" value="${requestScope.album.id}" />
						<div class="form-group">
							<label for="sharedWith"> </label> 
							<select class=form-control
								multiple name="sharedWith">
								<c:forEach items="${requestScope.utilisateurs }"
									var="utilisateur">
									<option value="${utilisateur.id}">${utilisateur.login }
									</option>
								</c:forEach>
							</select>
						</div>
						<button class="btn btn-primary" type="submit" title="Modifier">Inviter</button>
					</form>
				</div>
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