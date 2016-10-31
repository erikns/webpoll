<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- De forskjellige knappene: Forrige, Avbryt, Neste, og Fullfør --%>
<p>
<c:if test="${hasPreviousQuestion}">
	<button class="btn btn-primary" name="action" value="previous">Forrige</button>&nbsp;
</c:if>

<button class="btn btn-default" name="action" value="cancel">Avbryt</button>&nbsp;

<c:choose>
	<c:when test="${hasNextQuestion}">
		<button class="btn btn-primary" name="action" value="next">Neste</button>&nbsp;
	</c:when>
	<c:otherwise>
		<button class="btn btn-primary" name="action" value="submit">Ferdig</button>&nbsp;
	</c:otherwise>
</c:choose>
</p>