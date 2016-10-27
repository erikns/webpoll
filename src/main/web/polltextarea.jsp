<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-default">
        <div class="panel-heading">
                <h3 class="panel-title">Spørsmål ${poll.currentQuestionCounte}</h3>
        </div>
        <div class="panel-body">
                <p><strong>${question.text}</strong></p>
                <form action="/pollanswer" method="post" id="answerform">
    	           <textarea class="form-control" name="freetext" form="answerform" rows="5"></textarea>
                	<input class="btn btn-default" type="submit" value="Avbryt">
                	<input class="btn btn-primary" type="submit" value="Neste">
                </form>
        </div>
</div>
