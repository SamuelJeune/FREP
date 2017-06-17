<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      PermisPiste - Jeu : ${jeu.libellejeu}
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid">
            <h1>Jeu : ${jeu.libellejeu}</h1>

            <c:if test="${not empty msg}">
                <div class="alert" style="background-color: #f39c12; color:whitesmoke;"> ${msg} </div>
            </c:if>

            <div class="jumbotron">
                <h2>Missions :</h2>
                <c:choose>
                    <c:when test="${empty missions}">
                        <p>Ce jeu ne possède pas de missions.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Libellé missions</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${missions}" var="mission">
                                <tr>
                                    <td><a href="/missions/${mission.nummission}">${mission.libmission}</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
                <h2>Apprenants :</h2>
                <c:choose>
                    <c:when test="${empty apprenants}">
                        <p>Aucun apprenant n'est inscrit à ce jeu.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover">
                            <tbody>
                            <c:forEach items="${apprenants}" var="apprenant">
                                <tr>
                                    <td>
                                        <a href="/apprenants/${apprenant.numapprenant}">${apprenant.prenomapprenant} ${apprenant.nomapprenant}</a>
                                    </td>
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