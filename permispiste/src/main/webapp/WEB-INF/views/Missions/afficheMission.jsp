<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Mission : ${mission.libmission}
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <div class="jumbotron">
                Mission : ${mission.libmission}
                <br />
                Cette mission fait parti du jeu suivant : <a href="/jeux/${jeu.numjeu}">${jeu.libellejeu}</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>