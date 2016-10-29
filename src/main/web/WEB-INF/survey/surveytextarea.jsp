<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Spørsmål <c:out value="${questionNumber}"/></h3>
	</div>
	<div class="panel-body">
		<p>
			<strong><c:out value="${question.text}"/></strong>
		</p>
		<form action="surveyquestion" method="post" id="answerform">
			<textarea class="form-control" name="answer" form="answerform"
				rows="5"></textarea>
				<br>
			<jsp:include page="surveybuttons.jsp"/>
		</form>
	</div>
</div>
