<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Spørsmål ${questionNumber}</h3>
	</div>
	<div class="panel-body">
		<p>
			<strong>${question.text}</strong>
		</p>
		<form action="pollquestion" method="post">
			<c:set var="count" value="1" scope="page" />
			<%-- Antar at man får inn et question object, som har en liste over alternativer --%>
			<c:forEach var="o" items="${question.options}">
				<%-- o = option --%>
				<div class="radio">
					<label> <input type="radio" name="answer" value="${count}">
						${o}
					</label>
				</div>
				<c:set var="count" value="${count + 1}" scope="page" />
			</c:forEach>
			
			<c:choose>
				<c:when test="${hasPreviousQuestion}">
					<input class="btn btn-primary" type="submit" name="action" value="Forrige">
				</c:when>
			</c:choose>
			<input class="btn btn-default" type="submit" name="action" value="Avbryt">
			<c:choose>
				<c:when test="${hasNextQuestion}">
					<input class="btn btn-primary" type="submit" name="action" value="Neste">
				</c:when>
				<c:otherwise>
					<input class="btn btn-primary" type="submit" name="action" value="Finish">
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</div>
