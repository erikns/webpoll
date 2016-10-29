<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- De forskjellige knappene: Forrige, Avbryt, Neste, og Fullfør --%>
<p>
<c:if test="${hasPreviousQuestion}">
	<input class="btn btn-primary" type="submit" name="action" value="Forrige">&nbsp;
</c:if>

<input class="btn btn-default" type="submit" name="action" value="Avbryt">&nbsp;

<c:choose>
	<c:when test="${hasNextQuestion}">
		<input class="btn btn-primary" type="submit" name="action" value="Neste">&nbsp;
	</c:when>
	<c:otherwise>
		<input class="btn btn-primary" type="submit" name="action" value="Ferdig">&nbsp;
	</c:otherwise>
</c:choose>
</p>