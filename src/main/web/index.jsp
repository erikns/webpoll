<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="pages/meta.jsp"/>
		<title>P-Poll</title>
	</head>

	<body>
		<jsp:include page="pages/navbar.jsp"/>
		<div class="site-wrapper">
  			<div class="site-wrapper-inner">
  				<div class="cover-container">
          				<div class="inner cover text-center">
            				<h1 class="cover-heading">P-Poll</h1>
            				<p class="lead">Lag &amp; svar på undersøkelser hvor som helst, når som helst.</p>
            				<form class="form-inline" action="surveycheck" method="post">
			  			<div class="form-group">
			  				<p><label for="pollcode">Kode</label>
			  				<input type="text" class="form-control" id="pollcode" name="code"  required autofocus>
			  				<input type="submit" class="btn btn-primary" value="Ok"></p>
			  			</div>
			  			<p class="errormsg"><c:out value="${errormsg}"/>&nbsp;</p>
	  				</form>
          				</div>
      				</div>
    			</div>
    		</div>
        </div>
		<jsp:include page="pages/footer.jsp"/>
		<jsp:include page="pages/js.jsp"/>
		<script>
			$.backstretch([
				"<c:url value="assets/img/people.jpg"/>",
    				"<c:url value="assets/img/lecture.jpg"/>",
    				"<c:url value="assets/img/maclecture.jpg"/>"
  				], {duration: 5000, fade: "slow"});
		</script>
	</body>
</html>