<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/pages/meta.jsp" />
<title>P-Poll Aktiver undersøkelse</title>
</head>

<body>
	<jsp:include page="/pages/navbar.jsp" />
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Aktiver undersøkelse</h3>
					</div>
					<div class="panel-body">
						<form action="startsurvey" method="post">
							<div class="form-group">
								<p>
									<label for="deadline">Deadline:&nbsp;</label> 
									dager<input type="number" name="days" min="0"> 
									timer<input type="number" name="hours" min="0" max="23">
									minutter<input type="number" name="minutes" min="0" max="59">
									<c:out value="${errormsg}" />&nbsp;</span> <input type="submit"
										id="submit-form" class="hidden">
								</p>
							</div>
						</form>
					</div>
					<div class="panel-footer">
						<label for="submit-form" class="btn btn-primary">Aktiver</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/pages/footer.jsp" />
	<jsp:include page="/pages/js.jsp" />
	<script>
		$.backstretch("<c:url value="/assets/img/calendar.jpg"/>");
	</script>
</body>
</html>