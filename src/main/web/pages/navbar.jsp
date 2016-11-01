<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>    
				<span class="icon-bar"></span>    
				<span class="icon-bar"></span>        
			</button>
			<a class="navbar-brand" href="<c:url value="/"/>">P-Poll</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar">
				<li>
					<a href="<c:url value="/"/>">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</a>
				</li>  
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${!empty loggedinuser}">
					<li><a href="<c:url value="lecturer"/>"><c:out value="${loggedinuser}"/></a></li>
					<li><a href="<c:url value="logout"/>">Logg ut</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="login"/>">Logg inn</a></li>
				</c:otherwise>
			</c:choose>
			
				
			</ul>
		</div>
	</div>
</nav>