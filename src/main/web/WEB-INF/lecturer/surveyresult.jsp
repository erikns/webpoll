<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Undersøkelseresultat</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
    				<h3 class="panel-title">Resultat av undersøkelse</h3>
                </div>
    			<div class="panel-body">
                    <form class="form-inline" action="surveyresult" method="post">
                    	<div class="form-group">
                    		<p>Undersøkelsenavn: <c:out value="${survey.name}"/></p>
                    		<p>Antall svar: <c:out value="${survey.numberOfResponses}"/>
							<input type="submit" id="submit-form" class="hidden">
						</div>
					</form>
                </div>
                <div class="panel-footer">
					<p>
						<label for="submit-form" class="btn btn-primary">Refresh</label>
						<a href="<c:url value="clonesurvey"/>" class="btn btn-default" role="button">Klon Undersøkelse</a>
						<a href="<c:url value="lecturer"/>" class="btn btn-default" role="button">Tilbake</a>
					</p>
    			</div>
      		</div>

      		<c:forEach var="question" items="${survey.resultData}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><c:out value="${question.questionText}"/></h3>
				</div>
				<div class="panel-body">
					<c:forEach var="qa" items="${question.answers}">
						<ol>
							<li><c:out value="${qa.answerText}"/>
								<ul>
									<li>Antall svar: <c:out value="${qa.frequency}"/></li>
									<li>Prosentandel: <c:out value="${qa.percentage}"/>&#37;</li>
								</ul>
							</li>
						</ol>
						<br>
					</c:forEach>
				</div>	
			</div>
      		</c:forEach>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/stats.jpg"/>");</script>
	</body>
</html>