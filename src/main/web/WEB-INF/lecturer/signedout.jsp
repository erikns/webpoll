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
		<div class="site-wrapper">
  			<div class="site-wrapper-inner">
  				<div class="cover-container">
                    <div class="panel panel-default">
                        <div class="panel-heading">
            				<h3 class="panel-title">Utlogget</h3>
                        </div>
                        <div class="panel-body">
                        	<p>Du er nå utlogget.</p>
                        </div>
                        <div class="panel-footer">
                        	<a href="index" class="btn btn-default" role="button">Til fremsiden</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<jsp:include page="/pages/footer.jsp"/>
		<jsp:include page="/pages/js.jsp"/>
		<script>$.backstretch("<c:url value="/assets/img/macdesk.jpg"/>");</script>
	</body>
</html>