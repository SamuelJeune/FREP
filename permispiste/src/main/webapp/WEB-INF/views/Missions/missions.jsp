<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Liste des missions
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container-fluid">
            <h1>Liste des missions :</h1>

            <div class="jumbotron">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>NUMERO</th>
                        <th>NOM DE LA MISSION</th>
                        <th>NOM DU JEU</th>
                        <th>ACTIONS</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${missions}" var="mission">
                        <spring:url value="/missions/${mission.nummission}" var="detailsURL" />
                        <spring:url value="/missions/${mission.nummission}/modifier" var="editURL" />
                        <spring:url value="/missions/${mission.nummission}/supprimer" var="deleteURL" />
                        <tr>
                            <td>${mission.nummission}</td>
                            <td>${mission.libmission}</td>
                            <td><a href="/jeux/${mission.numjeu}">${mission.jeu.libellejeu}</a></td>
                            <td>
                                <a href="${detailsURL}"><button  class="btn" type="submit" style="background-color: #18bc9c; color:whitesmoke;">Détails</button></a>
                                <a href="${editURL}"><button  class="btn" type="submit" style="background-color: #2c3e50; color:whitesmoke;">Modifier</button></a>
                                <form method="post" action="${deleteURL}" style="display: inline"><button  class="btn" type="submit" style="background-color: #e74c3c; color: whitesmoke;">Supprimer</button></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <a href="/missions/ajouter">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Ajouter un mission
                    </button>
                </a>

            </div>
        </div>

    </jsp:body>
</tags:layout>