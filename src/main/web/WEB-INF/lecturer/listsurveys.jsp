<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

<c:forEach var="survey" items="${surveys}">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="<c:out value="${survey.name}"/>">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#<c:out value="${survey.code}"/>">
          <c:out value="${survey.name}"/>
        </a>
      </h4>
    </div>
    <div id="<c:out value="${survey.code}"/>" class="panel-collapse collapse in" role="tabpanel">
      <div class="panel-body">
        <p>Dato laget: <c:out value="${survey.dateCreated}"/></p>
        <p>Deadline: <c:out value="${not empty survey.deadline ? survey.deadline :'Ikke satt'}"/></p>
        <p>Kode: <c:out value="${survey.code}"/></p>
        <p>Antall svar: <c:out value="${survey.numberOfResponses}"/></p>
      </div>
      <div class="panel-footer">
        <p>
          <a class="btn btn-default" href="<c:url value="surveyoverview"/>" role="button">
            <c:out value="${not empty survey.deadline ? 'Se resultat' : 'Aktiver'}"/>
          </a>
        </p>
      </div>
    </div>
  </div>
</c:forEach>
</div>