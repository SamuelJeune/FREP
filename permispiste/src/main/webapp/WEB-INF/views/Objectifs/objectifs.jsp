<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des objectifs
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
                        <th>Num. objectif</th>
                        <th>Libellé objectif</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${objectifs}" var="objectif">
                        <spring:url value="/jeux/${objectif.numobjectif}" var="detailsURL" />
                        <spring:url value="/jeux/${objectif.numobjectif}/modifier" var="editURL" />
                        <spring:url value="/jeux/${objectif.numobjectif}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${objectif.numobjectif}</td>
                            <td>${objectif.libobectif}</td>
                            <td>
                                <a href="${detailsURL}" class="btn btn-primary btn-sm" type="submit">Détails</a>
                                <a href="${editURL}" class="btn btn-primary btn-sm" type="submit">Modifier</a>
                                <form method="post" action="${deleteURL}"><button class="btn btn-danger btn-sm" type="submit">Supprimer</button></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/objectifs//ajouter">Ajouter un objectif</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>