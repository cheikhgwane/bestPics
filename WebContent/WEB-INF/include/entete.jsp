<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header id="header" class="fixed-top">
	<div class="container">
		<div class="logo float-left">
			<h1 class="text-light">
				<a href="#header"> <span>Best PICS<img width="70px"
						height="70px"
						src="${pageContext.request.contextPath}/img/logo.svg" alt="logo" />
				</span>
				</a>
			</h1>
		</div>

		<nav class="main-nav float-right d-none d-lg-block">
			<ul>
				<li class="active"><a href="">Home</a></li>
				<c:choose>
					<c:when test="${empty sessionScope.utilisateur }">
						<li><a href="<c:url value='/login'/>">Se connecter</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="<c:url value='/album/?user=${sessionScope.utilisateur.login}'/>">Mes
								photos</a></li>
						<c:if test="${sessionScope.utilisateur.admin }">
							<li><a
								href="<c:url value='/user'/>">Gestion des Comptes</a></li>
						</c:if>
						<li><a href="<c:url value='/logout'/>">Deconnexion</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</nav>
		<!-- .main-nav -->
	</div>
</header>