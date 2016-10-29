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
                    <div class="panel panel-default">
                        <div class="panel-heading">
            				<h3>
                            <c:out value="${survey.surveyName}"/>
                            <small>av<c:out value="${survey.creator}"/>, 
                            <c:out value="${survey.surveyDate}"/></small>
                            <h3>
                        </div>
            			<div class="panel-body">
                            <p>Deadline: <c:out value="${survey.surveyDeadline}"/></p>
            				<jsp:useBean id="now" class="java.util.Date"/>
            				<c:choose>
            					<c:when test="${survey.surveyDeadline lt now}">
            						<p>Tiden er ute!</p>
                                    <p><a class="btn btn-default" href="index" role="button">Til fremsiden</a>&nbsp;
            						<a class="btn btn-primary disabled" href="#" role="button">Start</a></p>
            					</c:when>
            					<c:otherwise>
                                    <a class="btn btn-default" href="index" role="button">Til fremsiden</a>
            						<a class="btn btn-primary" href="surveyquestion" role="button">Start</a>
            					</c:otherwise>
            				</c:choose>
                        </div>
                    </div>
			    </div>
    		</div>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/maclecture.jpg"/>");</script>
	</body>
</html>