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

            <div class="alert" style="background-color: #a0daee; color:black;">
                Pour suivre la progressions, cliquer sur un jeu pour dérouler les missions, les objectifs et les actions.
            </div>

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

                
                <h2>Progression :</h2>

                <div class="container panel-group">
                    <c:forEach items="${jeux}" var="jeu">
                        <div class="panel panel-default">

                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapseJeu${jeu.numjeu}">
                                        Jeu n°${jeu.numjeu} : ${jeu.libellejeu}
                                        <div class="progress">
                                            <div class="progress-bar bg-success" role="progressbar" style="width: ${scoreForJeux.get(jeu)*100 / missions.get(jeu).size()}%; color : black;" aria-valuenow="${scoreForJeux.get(jeu)}" aria-valuemin="0" aria-valuemax="${missions.get(jeu).size()}">
                                                    ${scoreForJeux.get(jeu)} / ${missions.get(jeu).size()}
                                            </div>
                                        </div>
                                    </a>
                                </h4>
                            </div>

                            <div id="collapseJeu${jeu.numjeu}" class="panel-collapse collapse panel-group container">
                                <c:forEach items="${missions.get(jeu)}" var="mission">
                                    <div class="panel panel-default">

                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" href="#collapseJeu${jeu.numjeu}Mission${mission.nummission}">
                                                    Mission n°${mission.nummission} : ${mission.libmission}
                                                    <div class="progress">

                                                        <div class="progress-bar" role="progressbar" style="width: ${scoreForMissions.get(mission)*100 / objectifs.get(mission).size()}%; color : black;" aria-valuenow="${scoreForMissions.get(mission)}" aria-valuemin="0" aria-valuemax="${objectifs.get(mission).size()}">
                                                                ${scoreForMissions.get(mission)} / ${objectifs.get(mission).size()}
                                                        </div>
                                                    </div>
                                                </a>
                                            </h4>
                                        </div>

                                        <div id="collapseJeu${jeu.numjeu}Mission${mission.nummission}" class="panel-collapse collapse container panel-group">
                                            <c:forEach items="${objectifs.get(mission)}" var="objectif">
                                                <div class="panel panel-default">

                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a data-toggle="collapse" href="#collapseJeu${jeu.numjeu}Mission${mission.nummission}Objectif${objectif.numobjectif}">
                                                                Objectif n°${objectif.numobjectif} : ${objectif.libobjectif}
                                                                <div class="progress">

                                                                    <div class="progress-bar" role="progressbar" style="width: ${scoreForObjectifs.get(objectif)*100 / actions.get(objectif).size()}%; color : black;" aria-valuenow="${scoreForObjectifs.get(objectif)}" aria-valuemin="0" aria-valuemax="${actions.get(objectif).size()}">
                                                                            ${scoreForObjectifs.get(objectif)} / ${actions.get(objectif).size()}
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </h4>
                                                    </div>

                                                    <div id="collapseJeu${jeu.numjeu}Mission${mission.nummission}Objectif${objectif.numobjectif}" class="panel-collapse collapse">
                                                        <h5>Pour valider une action, cliquer sur générer un score (Vous pouvez recommencer jusqu'à ce que le score soit supérieur à la note minimale)</h5>
                                                        <c:forEach items="${actions.get(objectif)}" var="action">
                                                            <div class="panel-body">
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
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>