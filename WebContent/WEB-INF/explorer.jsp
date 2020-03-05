<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>BestPics</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="keywords" />
<meta content="" name="description" />

<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<!-- #about -->
<link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/ionicons/css/ionicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
<!-- Bootstrap CSS File -->


<!-- Main Stylesheet File -->

<link href="css/style.css" rel="stylesheet" />
</head>

<body>
	<!--==========================
  Header
  ============================-->
	<c:import url="include/entete.jsp" />

	<div style="margin-top: 8%" class="container-fluid">
		<section id="portfolio" class="clearfix">
			<div class="container">
				<div class="row portfolio-container">
					<c:if test="${empty requestScope.images }">
						<h3>Aucune photo disponible</h3>
					</c:if>
					<c:forEach items="${requestScope.images}" var="image">
						<div class="col-lg-4 col-md-6 portfolio-item filter-web"
							data-wow-delay="0.1s">
							<div class="portfolio-wrap">
								<img src="${pageContext.request.contextPath}/image/getOne?imageUrl=${image.fileImage}"
									class="img-fluid" alt="" />
								<div class="portfolio-info">
									<h4>
										${image.titre}
									</h4>
									<p>${image.description}</p>
									<p> Album : ${image.album.nomAlbum }</p>
									<div>
										<a
											href="${pageContext.request.contextPath}/image/getOne?imageUrl=${image.fileImage}"
											class="link-preview" data-lightbox="portfolio"
											data-title="${image.titre}" title="${image.titre}"><i
											class="ion ion-eye"></i></a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</div>
</body>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-migrate.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/mobile-nav/mobile-nav.js"></script>
<script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/counterup/counterup.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/isotope/isotope.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/lightbox/js/lightbox.min.js"></script>
<!-- Contact Form JavaScript File -->
<!-- Template Main Javascript File -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
