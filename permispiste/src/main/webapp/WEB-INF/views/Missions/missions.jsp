<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des missions
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <h1 class="text-center">Liste des missions</h1>

            <div class="jumbotron">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Num. mission</th>
                        <th>jeu associé</th>
                        <th>Libellé mission</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${missions}" var="mission">
                        <spring:url value="/missions/${mission.nummission}" var="detailsURL" />
                        <spring:url value="/missions/${mission.nummission}/modifier" var="editURL" />
                        <spring:url value="/missions/${mission.nummission}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${mission.nummission}</td>
                            <td><a href="/jeux/${mission.numjeu}">${mission.jeu.libellejeu}</a></td>
                            <td>${mission.libmission}</td>
                            <td>
                                <a href="${detailsURL}" class="btn btn-primary btn-sm" type="submit">Détails</a>
                                <a href="${editURL}" class="btn btn-primary btn-sm" type="submit">Modifier</a>
                                <form method="post" action="${deleteURL}"><button class="btn btn-danger btn-sm" type="submit">Supprimer</button></form>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/missions/ajouter">Ajouter une mission</a>
            </div>
        </div>

    </jsp:body>
</tags:layout>