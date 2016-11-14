<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/pages/meta.jsp" />
<title>P-Poll Undersøkelseresultat</title>
	<style>
		body{padding-top:70px;padding-bottom:70px;}
		@media(max-width:767px){body{padding-top:0;padding-bottom:0px;}}
	</style>



<!--Load the AJAX API-->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	// Load the Visualization API and the corechart package.
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	<c:set var="questionnumber" value="1"/>
	<c:forEach var="question" items="${survey.resultData}">

	// Set a callback to run when the Google Visualization API is loaded.
	google.charts
			.setOnLoadCallback(drawChart<c:out value="${questionnumber}"/>);

	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawChart<c:out value="${questionnumber}"/>() {

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Svar');
		data.addColumn('number', 'Antall');
		<c:forEach var="qa" items="${question.answers}">
		data.addRow(['<c:out value="${qa.answerText}" />',
				<c:out value="${qa.frequency}" />]);
		</c:forEach>

		// Set chart options
		var options = {
			'title' : '\"${question.questionText}\"'
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.PieChart(document
				.getElementById('chart_question${questionnumber}'));
		chart.draw(data, options);
	}
	<c:set var="questionnumber" value="${questionnumber + 1}"/>
	</c:forEach>
</script>





</head>

<body>
	<jsp:include page="/pages/navbar.jsp" />
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Resultat av undersøkelse</h3>
			</div>
			<div class="panel-body">
				<form class="form-inline" id="refresh" action="surveyresult"
					method="get">
					<div class="form-group">
						<p>
							Undersøkelsenavn:
							<c:out value="${survey.name}" />
						</p>
						<p>
							Antall svar:
							<c:out value="${survey.numberOfResponses}" />
							<input type="submit" id="submit-form" class="hidden">
					</div>
				</form>
			</div>
			<div class="panel-footer">
				<p>
					<label for="submit-form" class="btn btn-primary">Refresh</label>
					<button type="submit" form="refresh" formmethod="post"
						formaction="clonesurvey" class="btn btn-default">Klon
						undersøkelse</button>
					<a href="<c:url value="lecturer"/>" class="btn btn-default"
						role="button">Tilbake</a>
				</p>
			</div>
		</div>

		<c:set var="questionnumber" value="1" />
		<c:forEach var="question" items="${survey.resultData}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><c:out value="${question.questionText}"/></h3>
				</div>
				<div class="panel-body">
					<div id="chart_question${questionnumber}"
						style="width: 500px; height: 200px;"></div>
					<c:set var="questionnumber" value="${questionnumber + 1}" />
				</div>
			</div>
		</c:forEach>
	</div>
	<jsp:include page="/pages/footer.jsp" />
	<jsp:include page="/pages/js.jsp" />
	<script>
		$.backstretch("<c:url value="/assets/img/stats.jpg"/>");
	</script>
</body>
</html>