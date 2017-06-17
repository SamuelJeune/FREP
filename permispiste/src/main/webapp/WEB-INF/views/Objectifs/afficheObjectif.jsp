<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Objectif : ${objectif.libobjectif}
    </jsp:attribute>
    <jsp:body>

        <div class="container-fluid">
            <h1>Libellé : ${objectif.libobjectif}</h1>

            <c:if test="${not empty msg}">
                <div class="alert" style="background-color: #f39c12; color:whitesmoke;"> ${msg} </div>
            </c:if>

            <div class="jumbotron">
                <h2>Missions liées :</h2>
                <c:choose>
                    <c:when test="${empty missions}">
                        <p>aucune mission liée.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>NUMERO DE LA MISSION</th>
                                    <th>NOM DE LA MISSION</th>
                                    <th>ACTIONS</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${missions}" var="mission">
                                    <tr>
                                        <td>Mission n°${mission.nummission}</td>
                                        <td>${mission.libmission}</td>
                                        <td><form method="post" action="/objectifs/${objectif.numobjectif}/retirer-mission/${mission.nummission}"><button type="submit">Supprimer</button></form></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>

                <a href="/objectifs/${objectif.numobjectif}/ajouter-mission">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Lier à une mission
                    </button>
                </a>

            </div>
        </div>
    </jsp:body>
</tags:layout>