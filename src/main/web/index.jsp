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
		<div class="container">
			<div class="jumbotron">
  				<h1 class="text-center">Project Webpoll <small>The Kahoot clone</small></h1>
  				<p class="lead text-center">Create &amp; answer  polls anywhere, anytime.</p>
  				<br>
  				<br>
  				<div class="col-md-5 col-md-offset-4">
	  				<form class="form-inline">
	  					<div class="form-group">
	  						<label for="pollcode">Poll code</label>
	  						<input type="text" class="form-control" id="pollcode" placeholder="123456">
	  						<button type="submit" class="btn btn-primary btn-default">Go!</button>
	  					</div>
	  				</form>
  				</div>
  			</div>
  			<div class="row">
	  			<div class="col-md-6">
	  				<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
	  				<h2 class="text-center">Simple</h2>
	  				<p class="text-center">Lorem ipsum doler</p>
	  			</div>
	  			<div class="col-md-6">
	  				<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
	  				<h2 class="text-center">Innovative</h2>
	  				<p class="text-center">sit amet, consectetur</p>
	  			</div>
  			</div>

  			<div class="row">
				<div class="col-md-6">
					<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
  					<h2 class="text-center">Cinematic</h2>
  					<p class="text-center">adipiscing elit. Nulla</p>
  				</div>
  				<div class="col-md-6">
  					<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
  					<h2 class="text-center">Built with love</h2>
  					<p class="text-center">mattis rhoncus nibh,</p>
  				</div>
  			</div>
		</div>
		<jsp:include page="pages/footer.jsp"/>
		<jsp:include page="pages/js.jsp"/>
	</body>
</html>