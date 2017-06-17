<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Mission : ${mission.libmission}
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <div class="jumbotron">
                Mission : ${mission.libmission}
                <br />
                Cette mission fait parti du jeu suivant : <a href="/jeux/${jeu.numjeu}">${jeu.libellejeu}</a>
                <br />
                Objectifs de la mission :
                <c:choose>
                    <c:when test="${empty objectifs}">
                        Cette mission ne possède pas d'objectifs.
                    </c:when>
                    <c:otherwise>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Num. ojectif</td>
                                    <td>Libellé objectif</td>
                                    <td>Actions</td>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${objectifs}" var="objectif">
                                <tr>
                                    <td><a href="/objectifs/${objectif.numobjectif}">Objectif n°${objectif.numobjectif}</a></td>
                                    <td>${objectif.libobjectif}</td>
                                    <td><form method="post" action="/missions/${mission.nummission}/retirer-objectif/${objectif.numobjectif}"><button type="submit">Supprimer</button></form> </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                <a href="/missions/${mission.nummission}/fixer-objectif">Ajouter un objectif</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>