<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Apprenant : ${apprenant.prenomapprenant} ${apprenant.nomapprenant}
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">

            <div class="jumbotron">
                Apprennant : ${apprenant.prenomapprenant} ${apprenant.nomapprenant}
                <br />
                Inscriptions :
                    <c:choose>
                        <c:when test="${empty jeux}">
                            Cet apprenant n'est inscrit à aucun jeu.
                        </c:when>
                        <c:otherwise>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <td>Num. jeu</td>
                                        <td>Libellé jeu</td>
                                        <td>Acions</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${jeux}" var="jeu">
                                        <spring:url value="/jeux/${jeu.numjeu}" var="jeuURL" />
                                        <spring:url value="/apprenants/${apprenant.numapprenant}/desinscrire/${jeu.numjeu}" var="unsubscribeURL" />
                                        <tr>
                                            <td><a href="${jeuURL}">${jeu.numjeu}</a></td>
                                            <td><a href="${jeuURL}">${jeu.libellejeu}</a></td>
                                            <td>
                                                <form method="POST" action="${unsubscribeURL}"><button class="btn btn-danger btn-sm" type="submit">Désinscrire</button></form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                <a href="/apprenants/${apprenant.numapprenant}/inscrire">Inscire à un jeu</a>
            </div>


            <div class="jumbotron">
                <p>Missions en attente :</p>
                <c:choose>
                <c:when test="${empty missions}">
                    Cet apprenant n'a pas de mission en attente.
                </c:when>
                <c:otherwise>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td>Num. Missions</td>
                            <td>Libellé Missions</td>
                            <td>Actions</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${missions}" var="mission">
                            <spring:url value="/missions/${mission.nummission}" var="missionURL" />
                            <tr>
                                <td><a href="${missionURL}">${mission.nummission}</a></td>
                                <td><a href="${missionURL}">${mission.libmission}</a></td>
                                <td><a href="#" ><button  class="btn btn-primary btn-sm" type="submit">Voir les actions de cette mission</button></a> </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
                </c:choose>
            </div>

        </div>
    </jsp:body>
</tags:layout>