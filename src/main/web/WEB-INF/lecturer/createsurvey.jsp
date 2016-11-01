<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Ny undersøkelse</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="site-wrapper">
  			<div class="site-wrapper-inner">
  				<div class="cover-container">
                    <div class="panel panel-default">
                        <div class="panel-heading">
            				<h3 class="panel-title">Ny undersøkelse</h3>
                        </div>
            			<div class="panel-body">
                            <form class="form-inline" action="createsurvey" method="post">
                            	<div class="form-group">
                            		<label for="surveyName">Undersøkelsenavn: </label>
									<input type="text" id="surveyName" name="surveyName" class="form-control" value="<c:out value=""/>">
									<input type="submit" id="submit-form" class="hidden">
								</div>
								<div class="form-group">
									<c:set var="count" value="1" scope="page" />
									<c:forEach var="question" items="">
										<p><c:out value=""/>&nbsp;
										<button class="btn btn-default" name="delete" value="${count}">Slett</button></p>
										<c:set var="count" value="${count + 1}" scope="page" />
									</c:forEach>
                           		</div>
                            </form>
                        </div>
                        <div class="panel-footer">
                        	<form class="form-inline" action="createquestion" method="post">
                        		<div class="form-group">
                        			<button class="btn btn-default" name="action" value="freetext">Nytt fritekstspørsmål</button>&nbsp;
            						<button class="btn btn-default" name="action" value="multiplechoice">Nytt flervalgspørsmål</button>&nbsp;
            					<c:choose>
								<c:when test="${count eq 1}">
									<label for="submit-form" class="btn btn-primary disabled"
									data-toggle="tooltip" data-placement="top" title="En undersøkelse må ha minst ett spørsmål">Ferdig</label>
								</c:when>
									<c:otherwise>
									<label for="submit-form" class="btn btn-primary">Ferdig</label>
									</c:otherwise>
								</c:choose>
            					</div>
            				</form>
                        </div>
                    </div>
			    </div>
    		</div>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/people.jpg"/>");</script>
		<script>$(function () {$('[data-toggle="tooltip"]').tooltip()})</script>
	</body>
</html>