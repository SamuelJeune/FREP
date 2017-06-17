<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Liste des objectifs
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container-fluid">
            <h1>Liste des objectifs :</h1>

            <div class="jumbotron">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>NUMERO</th>
                        <th>NOM DE L'OBJECTIF</th>
                        <th>ACTIONS</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${objectifs}" var="objectif">
                        <spring:url value="/objectifs/${objectif.numobjectif}" var="detailsURL" />
                        <spring:url value="/objectifs/${objectif.numobjectif}/modifier" var="editURL" />
                        <spring:url value="/objectifs/${objectif.numobjectif}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${objectif.numobjectif}</td>
                            <td>${objectif.libobjectif}</td>
                            <td>
                                <a href="${detailsURL}"><button  class="btn" type="submit" style="background-color: #18bc9c; color:whitesmoke;">Détailler</button></a>
                                <a href="${editURL}"><button  class="btn" type="submit" style="background-color: #2c3e50; color:whitesmoke;">Modifier</button></a>
                                <a href="${deleteURL}"><button  class="btn" type="submit" style="background-color: #e74c3c; color:whitesmoke;">Supprimer</button></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <a href="/objectifs/ajouter">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Ajouter un objectif
                    </button>
                </a>

            </div>
        </div>
    </jsp:body>
</tags:layout>