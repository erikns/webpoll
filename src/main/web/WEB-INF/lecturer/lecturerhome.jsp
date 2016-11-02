<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Foreleser</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
			<div class="panel panel-default margin-top">
				<div class="panel-body">
					<a class="btn btn-primary" href="<c:url value="instantiatesurvey"/>" role="button">Ny undersøkelse</a>
				</div>
			</div>

			<jsp:include page="listsurveys.jsp"/>
		</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/people.jpg"/>");</script>
	</body>
</html>