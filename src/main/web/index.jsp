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
          				<div class="inner cover text-center">
            				<h1 class="cover-heading">Project Webpoll</h1>
            				<p class="lead">Create &amp; answer polls anywhere, anytime.</p>
            				<form class="form-inline" action="surveycheck" method="post">
			  			<div class="form-group">
			  				<p><label for="pollcode">Poll code</label>
			  				<input type="text" class="form-control" id="pollcode" name="code" placeholder="123456" required autofocus>
			  				<input type="submit" class="btn btn-primary" value="Go!"></p>
			  			</div>
			  			<!-- WTF -->
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