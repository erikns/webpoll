<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Spørsmål ${poll.currentQuestionCounte}</h3>
	</div>
	<div class="panel-body">
		<p>
			<strong>${question.text}</strong>
		</p>
		<form action="pollquestion" method="post" id="answerform">
			<textarea class="form-control" name="answer" form="answerform"
				rows="5"></textarea>

			<c:choose>
				<c:when test="${hasPreviousQuestion}">
					<input class="btn btn-primary" type="submit" name="action"
						value="Forrige">
				</c:when>
			</c:choose>
			<input class="btn btn-default" type="submit" name="action"
				value="Avbryt">
			<c:choose>
				<c:when test="${hasNextQuestion}">
					<input class="btn btn-primary" type="submit" name="action"
						value="Neste">
				</c:when>
				<c:otherwise>
					<input class="btn btn-primary" type="submit" name="action"
						value="Fullfør">
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</div>
