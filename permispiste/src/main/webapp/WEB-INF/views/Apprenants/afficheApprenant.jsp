<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Apprenant : ${apprenant.prenomapprenant} ${apprenant.nomapprenant}
    </jsp:attribute>
    <jsp:body>

        <div class="container-fluid">
            <h1>Apprenant : ${apprenant.prenomapprenant} ${apprenant.nomapprenant}</h1>

            <c:if test="${not empty msg}">
                <div class="alert" style="background-color: #f39c12; color:whitesmoke;"> ${msg} </div>
            </c:if>

            <div class="jumbotron">
                <h2>Inscriptions :</h2>
                    <c:choose>
                        <c:when test="${empty jeux}">
                            <p>Cet apprenant n'est inscrit à aucun jeu.</p>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>NUMERO DU JEU</th>
                                        <th>NOM DU JEU</th>
                                        <th>ACTIONS</th>
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
                <a href="/apprenants/${apprenant.numapprenant}/inscrire">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Inscire à un jeu
                    </button>
                </a>
            </div>

            <ul>
                <c:forEach items="${jeux}" var="jeu">
                    <li>
                        Jeu n°${jeu.numjeu} : ${jeu.libellejeu}
                        /
                        Missions complétées : ${scoreForJeux.get(jeu)} / ${missions.get(jeu).size()}
                        <ul>
                            <c:forEach items="${missions.get(jeu)}" var="mission">
                                <li>
                                    Mission n°${mission.nummission} : ${mission.libmission}
                                    /
                                    Objectifs complétées : ${scoreForMissions.get(mission)} / ${objectifs.get(mission).size()}
                                    <ul>
                                        <c:forEach items="${objectifs.get(mission)}" var="objectif">
                                            Objectif n°${objectif.numobjectif} : ${objectif.libobjectif}
                                            /
                                            Actions complétées : ${scoreForObjectifs.get(objectif)} / ${actions.get(objectif).size()}
                                            <ul>
                                                <c:forEach items="${actions.get(objectif)}" var="action">
                                                    Action n°${action.numaction} : ${action.libaction}
                                                    /
                                                    <c:choose>
                                                        <c:when test="${not empty obtients.get(action)}">
                                                            Score : ${obtients.get(action).valeur} / 20
                                                        </c:when>
                                                        <c:otherwise>
                                                            Pas de score
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <a href="/actions/${action.numaction}/generer/${apprenant.numapprenant}"> Générer un score</a>
                                                    <br />
                                                </c:forEach>
                                            </ul>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </jsp:body>
</tags:layout>