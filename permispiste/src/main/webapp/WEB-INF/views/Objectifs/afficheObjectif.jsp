<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Objectif : ${objectif.libobjectif}
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <div class="jumbotron">
                Libellé : ${objectif.libobjectif}
                <br />
                Missions liées :
                <c:choose>
                    <c:when test="${empty missions}">
                        aucune mission liée.
                    </c:when>
                    <c:otherwise>
                        <table>
                            <thead>
                                <tr>
                                    <th>Num. mission</th>
                                    <th>Libellé mission</th>
                                    <th>Actions</th>
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
                <a href="/objectifs/${objectif.numobjectif}/ajouter-mission">Lier à une mission</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>