<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Informations album</title>
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
						src="${pageContext.request.contextPath}/image/getOne?imageUrl=${album.albumUrl}"
						alt="cover">

				</div>
			</div>
			<div class="col-md-3">
				<c:choose>
					<c:when
						test="${sessionScope.utilisateur.id eq album.proprietaire.id }">
						<form action="${pageContext.request.contextPath}/album/modify"
							method="post" role="form" enctype="multipart/form-data"
							class="contactForm">
							<div class="form-group">
								<input type="hidden" value="${requestScope.album.id }"
									name="albumId"> <label for="albumName">Nom
									album</label> <input required type="text" class="form-control"
									value="${requestScope.album.nomAlbum}" name="albumName"
									id="albumName">
							</div>
							<div class="form-group">
								<label for="description">Total image</label> <input type="text"
									class="form-control" disabled value="${requestScope.albumSize}"
									name="albumSize" id="albumSize">
							</div>
							<div class="form-group">
								<label for="login">Couverture album</label> <input type="file"
									class="form-control" name="albumCover" id="albumCover">
							</div>

							<div class="form-check">
								<c:choose>
									<c:when test="${requestScope.album.privateAlbum eq true}">
										<input class="form-check-input" name="isPrivate" checked
											type="checkbox" value="isPrivate" id="isPrivate">
										<label class="form-check-label" for="isPrivate">*
											Rendre cet album public</label>
										<small id="error" class="form-text text-muted">* Si
											cochez, ne prend pas en compte la liste des utilisateurs
											selectionnés</small>
									</c:when>
									<c:otherwise>
										<input class="form-check-input" name="isPrivate"
											type="checkbox" value="isPrivate" id="isPrivate">
										<label class="form-check-label" for="isPrivate">*
											Rendre cet album public</label>
										<small id="error" class="form-text text-muted">* Si
											cochez, ne prend plus en  en compte la liste des utilisateurs
											ajoutés</small>
									</c:otherwise>
								</c:choose>
							</div>
							<button class="btn btn-primary" type="submit" title="Modifier">Modifier</button>
						</form>
					</c:when>
					<c:otherwise>
						<form action="${pageContext.request.contextPath}/album/modify"
							method="post" role="form" enctype="multipart/form-data"
							class="contactForm">
							<div class="form-group">
								<label for="title">Nom album</label> <input disabled required
									type="text" class="form-control"
									value="${requestScope.album.nomAlbum}" name="title" id="title">
							</div>
							<div class="form-group">
								<label for="description">Total image</label> <input type="text"
									disabled class="form-control" value="${requestScope.albumSize}"
									name="albumSize" id="albumSize">
							</div>
							<div class="form-check">
								<input class="form-check-input" name="isPrivate" type="checkbox"
									value="isPrivate" value="${requestScope.album.privateAlbum }"
									id="isPrivate"> <label class="form-check-label"
									for="isPrivate"> Album privé </label>
							</div>
						</form>
					</c:otherwise>
				</c:choose>

			</div>
					<div class="col-md-6">
				<c:choose>
					<c:when test="${not empty requestScope.grantedUsers and sessionScope.utilisateur.id eq album.proprietaire.id }">
						<table class="table">
							<thead class="thead-light">
								<tr>
									<th scope="col">Nom</th>
									<th scope="col">Prénom</th>
									<th scope="col">Login</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.grantedUsers}"
									var="utilisateur">
									<tr>
										<td><c:out value="${utilisateur.nom}" /></td>
										<td><c:out value="${utilisateur.prenom }" /></td>
										<td><c:out value="${utilisateur.login }" /></td>
										<td><a
											href="${pageContext.request.contextPath}/user/delete?idUser=${utilisateur.id }">Supprimer</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<h3>Aucun utilisateur autorisé</h3>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/jquery/jquery-migrate.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/mobile-nav/mobile-nav.js"></script>
	<script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/counterup/counterup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/isotope/isotope.pkgd.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/lightbox/js/lightbox.min.js"></script>
	<!-- Template Main Javascript File -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>