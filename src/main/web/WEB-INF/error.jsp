<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/pages/meta.jsp"/>
    <title>P-Poll Feilmelding</title>
</head>
<body>
    <div class="site-wrapper">
        <div class="site-wrapper-inner">
            <div class="cover-container">
                <div class="inner cover text-center">
                    <h1 class="cover-heading">${errorHeading}</h1>
                    <p class="lead"><c:out value="${errorMessage}"/></p>
                    <div>
                        <a href="index" class="btn btn-default" role="button">Til fremsiden</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/pages/footer.jsp"/>
    <jsp:include page="/pages/js.jsp"/>
    <script>
        $.backstretch([
            "<c:url value="assets/img/people.jpg"/>",
            "<c:url value="assets/img/lecture.jpg"/>",
            "<c:url value="assets/img/maclecture.jpg"/>"
        ], {duration: 5000, fade: "slow"});
    </script>
</body>
</html>
