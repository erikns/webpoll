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
					<h2 class="form-signin-heading">Velg type undersøkelse du ønsker å opprette</h2>
					
					<button class="btn btn-lg btn-primary btn-block" type="submit">Flervalg</button>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Tekst</button>
				</form>
				
  			</div>
		</div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		
		
		
	</body>
</html>