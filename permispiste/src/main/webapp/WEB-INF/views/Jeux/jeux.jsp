<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des jeux
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <div class="jumbotron">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Num. jeu</th>
                        <th>Libellé jeu</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${jeux}" var="jeu">
                        <spring:url value="/jeux/${jeu.numjeu}" var="detailsURL" />
                        <spring:url value="/jeux/${jeu.numjeu}/modifier" var="editURL" />
                        <spring:url value="/jeux/${jeu.numjeu}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${jeu.numjeu}</td>
                            <td>${jeu.libellejeu}</td>
                            <td>
                                <a href="${detailsURL}" class="btn btn-primary btn-sm" type="submit">Détails</a>
                                <a href="${editURL}" class="btn btn-primary btn-sm" type="submit">Modifier</a>
                                <form method="post" action="${deleteURL}"><button class="btn btn-danger btn-sm" type="submit">Supprimer</button></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/jeux/ajouter">Ajouter un jeu</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>