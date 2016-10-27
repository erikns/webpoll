<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-default">
        <div class="panel-heading">
                <h3 class="panel-title">Spørsmål ${poll.currentQuestionCounte}</h3>
        </div>
        <div class="panel-body">
                <p><strong>${question.text}</strong></p>
                <form action="" method="">
                	<c:set var="count" value="1" scope="page" />
                	<%-- Antar at man får inn et question object, som har en liste over alternativer --%>
                	<c:forEach var="o" items="${question.options}"> <%-- o = option --%>	
    			 <div class="checkbox">
                                		<label>
                                        		<input type="checkbox" value="${o}">
                                        		${o}
                                		</label>
                        	</div>
                        	<c:set var="count" value="${count + 1}" scope="page"/>
    		</c:forEach>
                      <input class="btn btn-default" type="submit" value="Avbryt">
                      <input class="btn btn-primary" type="submit" value="Neste">
                </form>
        </div>
</div>
