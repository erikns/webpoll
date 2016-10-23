<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/pages/meta.jsp"/>
		<title>Prosjekt WebPoll</title>
	</head>

	<body>
		<jsp:include page="/pages/navbar.jsp"/>
		<div class="container">
			<div class="jumbotron">
			
  				<form class="form-signin">
					
					<button class="btn btn-lg btn-primary btn-block" type="submit">Opprett ny undersøkelse</button>
			
				</form>
				
  			</div>
  			<div class="col-md-6">
	  				<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
	  				<h2 class="text-center">Undersøkelse 1</h2>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Se resultat</button>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Kopier undersøkelse</button>
	  			</div>
	  			
	  			<div class="col-md-6">
	  				<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
	  				<h2 class="text-center">Undersøkelse 2</h2>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Se resultat</button>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Kopier undersøkelse</button>
	  			</div>
	  			
	  			<div class="col-md-6">
	  				<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
	  				<h2 class="text-center">Undersøkelse 3</h2>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Se resultat</button>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Kopier undersøkelse</button>
	  			</div>
	  			
	  			<div class="col-md-6">
	  				<img src="http://placehold.it/150x150" alt="" class="img-responsive img-circle center-block">
	  				<h2 class="text-center">Undersøkelse 4</h2>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Se resultat</button>
	  				<button class="btn btn-lg btn-primary btn-block" type="submit">Kopier undersøkelse</button>
	  			</div>
	  			
		</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		
		
		
	</body>
</html>