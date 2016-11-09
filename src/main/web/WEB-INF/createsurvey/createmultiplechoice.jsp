<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>P-Poll Nytt fritekstspørsmål</title>
		<style>
			body{padding-top:70px;}
			.container{width:750px;}
			/* footer{position:relative;bottom:auto;} */
    		@media(max-width: 767px){body{padding-top:0;}.container{width: auto;}}
		</style>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
    				<h3 class="panel-title">Nytt flervalgspørsmål</h3>
                </div>
    			<div class="panel-body">
                    <form id="multiplechoice" class="form-horizontal" 
                    	action="newmultiplechoicequestion" method="post">
						<div class="form-group">
							<label class="col-md-2 control-label" for="questionname">
								Spørsmål: 
							</label>
							<div class="col-md-10">
                    			<textarea class="form-control" id="questionname" name="questionname" form="multiplechoice" rows="5"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<h3>Alternativer</h3>
							</div>
							<div class="col-md-offset-2 col-md-10">
								<div class="checkbox">
									<label> 
										<input type="checkbox" name="canhavemultipleanswers" value="true">
										Kan svare på mer enn et alternativ
									</label>
								</div>
								<p>Det er maks 10 alternativer per spørsmål. Du trenger ikke fylle inn alle alternativ.</p>
							</div>
						</div>
						<c:forEach begin="1" end="10" varStatus="loop">
							<div class="form-group">	
								<label class="col-md-2 control-label" 
										for="option${loop.index}">
										Alternativ&nbsp;${loop.index}
								</label>
								<div class="col-md-10">
                    				<input type="text" id="option${loop.index}" 
                    					name="option" class="form-control"> 
                    			</div>
                    		</div>
						</c:forEach>
						<input type="submit" id="submit-form" class="hidden">
						<p class="errormsg"><c:out value="${errormsg}"/>&nbsp;</p>
    				</form>
                </div>
                <div class="panel-footer">
                	<label for="submit-form" class="btn btn-primary">Lagre</label>
                	<a href="<c:url value="surveybuilder"/>" class="btn btn-default role="button">Avbryt</a>
                </div>
            </div>
    	</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/people.jpg"/>");</script>
	</body>
</html>