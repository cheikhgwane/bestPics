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

  </head>

  <body>
    <!--==========================
  Header
  ============================-->
  <c:import url="include/entete.jsp" />
  
      <!--==========================
    Intro Section
  ============================-->
  <section id="intro" class="clearfix">
	<div class="container">
	  <div class="intro-img">
		<img src="img/head.svg" alt="" class="img-fluid" />
	  </div>

	  <div class="intro-info">
		<h2>Tous vos photos à  un seul endroit!<br /></h2>
		<div>
		  <a href="#about" class="btn-get-started scrollto">Faire un tour</a>
		</div>
	  </div>
	</div>
  </section>
  <!-- #intro -->

  <main id="main">
	<!--==========================
	About Us Section
  ============================-->
	<section id="about">
	  <div class="container">
		<header class="section-header">
		  <h3>Comment Ã§a marche ?</h3>
		</header>

		<div class="row about-extra">
		  <div class="col-lg-6 wow fadeInUp order-1 order-lg-2">
			<img src="img/about-extra-2.svg" class="img-fluid" alt="" />
		  </div>

		  <div class="col-lg-6 wow fadeInUp pt-4 pt-lg-0 order-2 order-lg-1">
			<p>
			  Ouvrer un compte, créer des albums, ajouter des photos.
			</p>
			<p>
			  Définisser les thémes de vos albums.
			</p>
			<p>
			  Enfin décider de la visibilité de vos albums (public ou privée)!
			</p>
		  </div>
		</div>
	  </div>
	</section>
  </main>

  <!--==========================
  Footer
============================-->
  <footer id="footer">
	<div class="footer-top">
	  <div class="container">
		<div class="row">
		  <div class="col-lg-4 col-md-6 footer-info">
			<h3>BestPics</h3>
			<p>
			  RÃ©seau de partage de photos
			</p>
		  </div>
		</div>
	  </div>
	</div>

	<div class="container">
	  <div class="copyright">
		&copy; Copyright <strong>BestPics</strong>. All Rights Reserved
	  </div>
	</div>
  </footer>
  <!-- #footer -->


    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

    <!-- JavaScript Libraries -->
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
