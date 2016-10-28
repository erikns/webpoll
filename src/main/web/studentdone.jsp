<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="pages/meta.jsp"/>
		<title>Prosjekt WebPoll</title>
	</head>

	<body>
		<jsp:include page="pages/navbar.jsp"/>
		<div class="container">
			<div class="panel panel-default utloggetboks center">
				<div class="panel-body">
					<p class="text-center">Du er ferdig!</p>
				</div>
			</div>
		</div>
		<jsp:include page="pages/footer.jsp"/>
		<jsp:include page="pages/js.jsp"/>
		<script>$.backstretch("<c:url value="assets/img/macdesk.jpg"/>");</script>
	</body>
</html>