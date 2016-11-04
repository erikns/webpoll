<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Nytt fritekstspørsmål</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="site-wrapper">
  			<div class="site-wrapper-inner">
  				<div class="cover-container">
                    <div class="panel panel-default">
                        <div class="panel-heading">
            				<h3 class="panel-title">Nytt fritekstspørsmål</h3>
                        </div>
            			<div class="panel-body">
                            <form id="freetext" action="newtextquestion" method="post">
								<div class="form-group">
									<p>
									<label for="questionname">Spørsmål:&nbsp;</label>
                            		<textarea class="form-control" id="questionname" name="questionname" form="freetext" rows="5"></textarea>
                            		<span class="errormsg"><c:out value="${errormsg}"/>&nbsp;</span>

									<input type="submit" id="submit-form" class="hidden">
									</p>
								</div>
            				</form>
                        </div>
                        <div class="panel-footer">
                        	<label for="submit-form" class="btn btn-primary">Lagre</label>
                            <a href="<c:url value="surveybuilder"/>" class="btn btn-default role="button">Avbryt</a>
                        </div>
                    </div>
			    </div>
    		</div>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/people.jpg"/>");</script>
	</body>
</html>