<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>Prosjekt WebPoll</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
			<div class="panel panel-default margin-top">
				<div class="panel-body">
					<h1>Innlogget bruker: <c:out value="${loggedinusr}"/></h1>
				</div>
				<div class="panel-footer">
					<a href="logout" class="btn btn-default" role="button">Logg ut</a>
				</div>
			</div>

			<jsp:include page="listsurveys.jsp"/>
		</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/people.jpg"/>");</script>
	</body>
</html>