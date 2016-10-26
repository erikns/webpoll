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
      					<c:choose>
      						<c:when test="${question.type eq 'multipleradio'}">
      							<jsp:include page="pollradio.jsp"/>
      						</c:when>
      						<c:when test="${question.type eq 'multiplecheckbox'}">
      							<jsp:include page="pollcheckbox.jsp"/>
      						</c:when>
      						<c:when test="${question.type eq 'textarea'}">
      							<jsp:include page="polltextarea.jsp"/>
      						</c:when>
      						<c:otherwise>
      							
      						</c:otherwise>
      					</c:choose>
      					<jsp:include page="pollradio.jsp"/>
      					<jsp:include page="pollcheckbox.jsp"/>
      					<jsp:include page="polltextarea.jsp"/>
				</div>
        			</div>
        		</div>
		<!-- <div class="container">
			<div class="question-panel-outer">
			<div class="question-panel-inner">
				<div class="panel-heading">
					<h3 class="panel-title">Spørsmål 1</h3>
				</div>
				<div class="panel-body">
					<p>bla bla bla</p>
					<form action="" method="">
					<div class="radio">
						<label>
						<input type="radio" name="pollanswer" id="optionsRadios1" value="1" >
						Option one is this and that&mdash;be sure to include why it's great
						</label>
					</div>
					<div class="radio">
						<label>
						<input type="radio" name="pollanswer" id="optionsRadios2" value="2">Option two can be something else and selecting it will deselect option one
						 </label>
					</div>
					<div class="radio">
						<label>
						<input type="radio" name="pollanswer" id="optionsRadios3" value="3">
						Option 3
						</label>
					</div>
					</form>
				</div>
			</div>
			</div>
		</div> -->
		<jsp:include page="pages/footer.jsp"/>
		<jsp:include page="pages/js.jsp"/>
		<script>$.backstretch("<c:url value="assets/img/maclecture.jpg"/>");</script>
	</body>
</html>