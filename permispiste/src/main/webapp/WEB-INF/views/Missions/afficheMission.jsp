<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Mission : ${mission.libmission}
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid">
            <h1>Mission : ${mission.libmission}</h1>

            <c:if test="${not empty msg}">
                <div class="alert" style="background-color: #f39c12; color:whitesmoke;"> ${msg} </div>
            </c:if>

            <div class="jumbotron">
                <p>
                    Cette mission fait parti du jeu suivant : <a href="/jeux/${jeu.numjeu}">${jeu.libellejeu}</a>
                </p>
                <h2>Objectifs de la mission : </h2>
                <c:choose>
                    <c:when test="${empty objectifs}">
                        <p>Cette mission ne possède pas d'objectifs.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>NUMERO DE L'OBJECTIF</th>
                                    <th>NOM DE L'OBJECTIF</th>
                                    <th>ACTIONS</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${objectifs}" var="objectif">
                                <tr>
                                    <td><a href="/objectifs/${objectif.numobjectif}">Objectif n°${objectif.numobjectif}</a></td>
                                    <td>${objectif.libobjectif}</td>
                                    <td>
                                        <form method="post" action="/missions/${mission.nummission}/retirer-objectif/${objectif.numobjectif}" style="display: inline">
                                            <button  class="btn" type="submit" style="background-color: #e74c3c; color: whitesmoke;">Supprimer</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>

                <a href="/missions/${mission.nummission}/fixer-objectif">
                    <button  class="btn" type="submit" style="background-color: dodgerblue; color:whitesmoke;">
                        <span class="glyphicon glyphicon-plus"></span> Ajouter un objectif
                    </button>
                </a>

            </div>
        </div>
    </jsp:body>
</tags:layout>