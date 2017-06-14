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
        <table>
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
                        <a href="${profileURL}"><button type="submit">Détails</button></a>
                        <a href="${editURL}"><button type="submit">Modifier</button></a>
                        <form method="post" action="${deleteURL}"><button type="submit">Supprimer</button></form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</tags:layout>