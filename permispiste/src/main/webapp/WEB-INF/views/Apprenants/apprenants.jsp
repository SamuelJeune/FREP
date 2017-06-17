<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des apprenants
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <h1 class="text-center">Liste des apprenants</h1>

        <div class="jumbotron">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Num. apprenant</th>
                    <th>Nom apprenant</th>
                    <th>Prénom apprenant</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${apprenants}" var="apprenant">
                <spring:url value="/apprenants/${apprenant.numapprenant}" var="profileURL" />
                <spring:url value="/apprenants/${apprenant.numapprenant}/modifier" var="editURL" />
                <spring:url value="/apprenants/${apprenant.numapprenant}/supprimer" var="deleteURL" />
                <tr>
                    <td>${apprenant.numapprenant}</td>
                    <td>${apprenant.nomapprenant}</td>
                    <td>${apprenant.prenomapprenant}</td>
                    <td>
                        <a href="${profileURL}"><button  class="btn btn-primary btn-sm" type="submit">Détails</button></a>
                        <a href="${editURL}"><button  class="btn btn-success btn-sm" type="submit">Modifier</button></a>
                        <form method="post" action="${deleteURL}"><button  class="btn btn-danger btn-sm" type="submit">Supprimer</button></form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            <a href="/apprenants/ajouter">Ajouter un apprenant</a>
        </div>
        </div>

    </jsp:body>
</tags:layout>