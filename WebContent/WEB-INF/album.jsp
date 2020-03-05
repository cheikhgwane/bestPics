<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Album</title>
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
				<div class="card" style="width: 7rem;">
					<img class="card-img-top"
						src="${pageContext.request.contextPath}/img/gallery.png"
						alt="Card image cap">
					<div class="card-body">
						<button class="btn btn-outline " data-toggle="modal"
							data-target="#addAlbumModal">
							<img width="32" height="32"
								src="${pageContext.request.contextPath}/img/add.svg" />
						</button>
						<p>Ajouter album</p>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<c:choose>
					<c:when test="${empty requestScope.albums }">
						<h3>Vous n'avez pas encore créer d'album</h3>
					</c:when>
					<c:otherwise>
						<c:forEach items="${requestScope.albums }" var="album">
							<div class="card" style="width: 18rem; float: left; margin: 5px;">
								<img class="card-img-top" height="200px" width="100px"
									src="${pageContext.request.contextPath}/image/getOne?imageUrl=${album.albumUrl}"
									alt="cover">
								<div class="card-body">
									<h5 class="card-title">
										<a
											href="${pageContext.request.contextPath}/album?albumId=${album.id}">${album.nomAlbum}</a>
									</h5>
									<a href="${pageContext.request.contextPath}/album/albumInfo?albumId=${album.id}"
										class="btn btn-primary">Modifier</a> <a
										href="${pageContext.request.contextPath}/album/delete?albumId=${album.id}"
										class="btn btn-primary">Supprimer</a>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>


	<!-- add Modal -->
	<div class="modal fade" id="addAlbumModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Ajouter
						album</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/album/add"
						method="post" role="form" enctype="multipart/form-data"
						class="contactForm">

						<div class="form-group">
							<label for="login">Nom album</label> <input required type="text"
								class="form-control" name="albumName" id="albumName">
						</div>
						<div class="form-group">
							<label for="login">Couverture album</label> <input required
								type="file" class="form-control" name="albumCover"
								id="albumCover">
						</div>

						<div class="form-check">
							<input class="form-check-input" name="isPrivate" type="checkbox"
								value="isPrivate" id="isPrivate"> <label
								class="form-check-label" for="isPrivate"> Album privé </label>
						</div>

						<div style="margin-top: 10px" class="text-center">
							<button class="btn btn-primary" type="submit" title="Ajouter">Créer
								album</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/jquery/jquery-migrate.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Template Main Javascript File -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>