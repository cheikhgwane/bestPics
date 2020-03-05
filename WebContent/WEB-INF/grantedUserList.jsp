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
	<div>
		<div>
			<c:import url="include/entete.jsp" />
		</div>

		<div style="margin-top: 8%" class="container-fluid">
			<div class="row">
			<div class="col-md-2">
					<c:import url="include/menu.jsp" />
				</div>
				<div class="col-md-6">
					<c:choose>
					  <c:when test="${not empty requestScope.grantedUsers}">
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
							<c:forEach items="${requestScope.grantedUsers}" var="utilisateur">
								<tr>
									<td><c:out value="${utilisateur.nom}" /></td>
									<td><c:out value="${utilisateur.prenom }" /></td>
									<td><c:out value="${utilisateur.login }" /></td>
									<td><a href="${pageContext.request.contextPath}/user/delete?idUser=${utilisateur.id }">Supprimer</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					  </c:when>
					  <c:otherwise> <h3>Aucun utilisateur autorisé</h3></c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

	</div>
</body>
</html>