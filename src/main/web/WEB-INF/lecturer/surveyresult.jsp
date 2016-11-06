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
						<label for="submit-form" class="btn btn-primary">Ferdig</label>
						<a href="<c:url value="lecturer"/>" class="btn btn-default role="button">Tilbake</a>
					</p>
    			</div>
      		</div>

      		<c:forEach var="question" items="${survey.resultData}">


      		</c:forEach>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/lecture.jpg"/>");</script>
	</body>
</html>