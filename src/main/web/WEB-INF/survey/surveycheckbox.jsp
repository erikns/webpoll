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
		<form action="surveyquestion" method="post">
			<c:set var="count" value="1" scope="page" />
			<c:forEach var="o" items="${question.options}">
				<%-- o = option --%>
				<div class="checkbox">
					<label> <input type="checkbox" name="answer" value="${o}">
						<c:out value="${o}"/>
					</label>
				</div>
				<c:set var="count" value="${count + 1}" scope="page" />
			</c:forEach>
			<jsp:include page="surveybuttons.jsp"/>
		</form>
	</div>
</div>
