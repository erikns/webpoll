<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
<div class="panel no-bg">
<c:forEach var="survey" items="${surveys}">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="heading<c:out value="${survey.code}"/>">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<c:out value="${survey.id}"/>">
          <c:out value="${survey.name}"/>&nbsp;<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
        </a>
      </h4>
    </div>
    <div id="collapse<c:out value="${survey.id}"/>" class="panel-collapse collapse" role="tabpanel">
      <div class="panel-body">
        <p>Dato laget: <c:out value="${survey.formattedCreationTime}"/></p>
        <p>Deadline: <c:out value="${not empty survey.deadline ? survey.formattedDeadline :'Ikke satt'}"/></p>
        <p>Kode: <c:out value="${survey.code}"/></p>
        <p>Antall svar: <c:out value="${survey.numberOfResponses}"/></p>

        <form class="form-inline" action="opensurvey" method="post">
          <input type="hidden" name="id" value="<c:out value="${survey.id}"/>">
          <input type="hidden" name="active" value="<c:out value="${survey.activated}"/>">
          <input type="submit" id="submit-form<c:out value="${survey.id}"/>" class="hidden">
        </form>
      </div>
      <div class="panel-footer">
        <p>
          <label for="submit-form<c:out value="${survey.id}"/>" class="btn btn-primary">
              <c:out value="${survey.activated ? 'Se resultat' : 'Aktiver'}"/>
          </label>
        </p>
      </div>
    </div>
  </div>
</c:forEach>
</div>
</div>