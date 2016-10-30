<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

<c:forEach var="survey" items="${surveys}">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="${survey.id}">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#${survey.code}">
          <c:out value="${survey.name}"/>
        </a>
      </h4>
    </div>
    <div id="${survey.code}" class="panel-collapse collapse in" role="tabpanel">
      <div class="panel-body">
        <p>Her står info om survey, om den er aktiv, mulighet for å aktivere, resultat etc //TODO! </p>
      </div>
    </div>
  </div>
</c:forEach>
</div>