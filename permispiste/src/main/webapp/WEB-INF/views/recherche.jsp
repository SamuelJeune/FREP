<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Recherche
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid">

            <h1>Résultat de la recherche "${recherche}" : </h1>

            <div class="jumbotron">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>NUMERO</th>
                            <th>NOM DE L'APPRENANT</th>
                            <th>PRENOM DE L'APPRENANT</th>
                            <th>ACTIONS</th>
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
                                    <a href="${profileURL}"><button  class="btn" type="submit" style="background-color: #18bc9c; color:whitesmoke;">Détails</button></a>
                                    <a href="${editURL}"><button  class="btn" type="submit" style="background-color: #2c3e50; color:whitesmoke;">Modifier</button></a>
                                    <form method="post" action="${deleteURL}" style="display: inline"><button  class="btn" type="submit" style="background-color: #e74c3c; color: whitesmoke;">Supprimer</button></form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</tags:layout>