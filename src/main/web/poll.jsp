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
		<div class="site-wrapper">
      			<div class="site-wrapper-inner">
      				<div class="cover-container">
      					<h3>${poll.surveyName} <small>av ${poll.creator}, ${poll.surveyDate}</small></h3>
      					<p>Deadline: ${poll.surveyDeadline}</p>
      					 <jsp:useBean id="now" class="java.util.Date"/>
      					<c:choose>
      						<c:when test="${poll.surveyDeadline lt now}">
      							<p>Tiden er ute!</p>
      							<a class="btn btn-primary disabled" href="#" role="button" >Start</a>
      						</c:when>
      						<c:otherwise>
      							<a class="btn btn-primary" href="/pollquestion" role="button" >Start</a>
      						</c:otherwise>
      					</c:choose>
				</div>
        			</div>
        		</div>
		
		<jsp:include page="pages/footer.jsp"/>
		<jsp:include page="pages/js.jsp"/>
		<script>$.backstretch("<c:url value="assets/img/maclecture.jpg"/>");</script>
	</body>
</html>