<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Ny undersøkelse</title>
		<style>
			body{padding-top:70px;}
			.container{width:750px;}
			/* footer{position:relative;bottom:auto;} */
    		@media(max-width: 767px){body{padding-top:0;}.container{width:auto;}}
		</style>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
    				<h3 class="panel-title">Ny undersøkelse</h3>
                </div>
    			<div class="panel-body">
                    <form class="form-inline" action="savesurvey" method="post">
                    	<div class="form-group">
                    		<p>
                    		Undersøkelsenavn: <c:out value="${surveymodel.name}"/>&nbsp;
                    		<a href="<c:url value="changename"/>" class="btn btn-default" role="button">Endre navn</a>
                    		</p>
							<input type="submit" id="submit-form" class="hidden">
						</div>
					</form>
					<c:set var="count" value="0" scope="page" />
					<c:forEach var="question" items="${surveymodel.questions}">
						<form class="form-inline" action="surveybuilder" method="post">
						<div class="form-group">
							<ul>
							<li><c:out value="${question.questionText}"/> 
							<button class="btn btn-default" name="questionnumber" value="<c:out value="${count}"/>">Slett</button></li>
							</ul>
							<c:set var="count" value="${count + 1}" scope="page"/>
                   		</div>
                    	</form>
                    </c:forEach>
                </div>
                <div class="panel-footer">
                		<p>
                		<a href="<c:url value="newtextquestion"/>" class="btn btn-default" role="button">Nytt fritekstspørsmål</a>
                		<a href="<c:url value="newmultiplechoicequestion"/>" class="btn btn-default" role="button">Nytt flervalgspørsmål</a>
    					<c:choose>
						<c:when test="${count eq 0}">
							<label for="submit-form" class="btn btn-primary disabled"
							data-toggle="tooltip" data-placement="top" title="En undersøkelse må ha minst ett spørsmål">Ferdig</label>
						</c:when>
							<c:otherwise>
							<label for="submit-form" class="btn btn-primary">Ferdig</label>
							</c:otherwise>
						</c:choose>
						<a href="<c:url value="lecturer"/>" class="btn btn-default role="button">Avbryt</a>
						</p>
    					</div>
    				</form>
                </div>
      		</div>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/people.jpg"/>");</script>
		<script>$(function () {$('[data-toggle="tooltip"]').tooltip()})</script>
	</body>
</html>