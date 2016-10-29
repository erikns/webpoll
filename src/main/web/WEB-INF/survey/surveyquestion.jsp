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
		<div class="site-wrapper">
  			<div class="site-wrapper-inner">
  				<div class="cover-container">
  					<c:choose>
  						<c:when test="${question.questionType eq 'MULTIPLE_CHOICE_RADIO'}">
  							<jsp:include page="surveyradio.jsp"/>
  						</c:when>
  						<c:when test="${question.questionType eq 'MULTIPLE_CHOICE_CHECKBOX'}">
  							<jsp:include page="surveycheckbox.jsp"/>
  						</c:when>
  						<c:when test="${question.questionType eq 'FREE_TEXT'}">
  							<jsp:include page="surveytextarea.jsp"/>
  						</c:when>
  					</c:choose>
				</div>
    		</div>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/maclecture.jpg"/>");</script>
	</body>
</html>