<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des actions
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <h1 class="text-center">Liste des actions</h1>

            <div class="jumbotron">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Num. action</th>
                        <th>action parent</th>
                        <th>Libellé action</th>
                        <th>Score minimum<th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${actions}" var="action">
                        <spring:url value="/actions/${action.numaction}" var="detailsURL" />
                        <spring:url value="/actions/${action.numaction}/modifier" var="editURL" />
                        <spring:url value="/actions/${action.numaction}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${action.numaction}</td>
                            <td><a href="/actions/${action.actNumaction}">${action.actNumaction}</a></td>
                            <td>${action.libaction}</td>
                            <td>${action.scoremin}</td>
                            <td>
                                <a href="${detailsURL}" class="btn btn-primary btn-sm" type="submit">Détails</a>
                                <a href="${editURL}" class="btn btn-primary btn-sm" type="submit">Modifier</a>
                                <form method="post" action="${deleteURL}"><button class="btn btn-danger btn-sm" type="submit">Supprimer</button></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/actions/ajouter">Ajouter une action</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>