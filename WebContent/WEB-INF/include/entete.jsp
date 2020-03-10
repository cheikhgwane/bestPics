<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header id="header" class="fixed-top">
	<div class="container">
		<div class="logo float-left">
			<h1 class="text-light">
				<a href="${pageContext.request.contextPath}"> <span>Best PICS<img width="70px"
						height="70px"
						src="${pageContext.request.contextPath}/img/logo.svg" alt="logo" />
				</span>
				</a>
			</h1>
		</div>

		<nav class="main-nav float-right d-none d-lg-block">
			<ul>
				<c:choose>
					<c:when test="${empty sessionScope.utilisateur }">
						<li><a  class="active" href="<c:url value='/login'/>">Se connecter</a></li>
							<li><a href="<c:url value='/register'/>">Ouvrir un compte</a></li>
					</c:when>
					<c:otherwise>
					<li class="active"><a href="${pageContext.request.contextPath}/home/">Home</a></li>
						<li><a  
							href="<c:url value='/album/user?userName=${sessionScope.utilisateur.login}'/>">Mes
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