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
        Apprennant : ${apprenant.prenomapprenant} ${apprenant.nomapprenant}
        <br />
        Inscriptions :
            <c:choose>
                <c:when test="${empty jeux}">
                    Cet apprenant n'est inscrit à aucun jeu.
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <td>Num. jeu</td>
                                <td>Libellé jeu</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${jeux}" var="jeu">
                                <spring:url value="/jeux/${jeu.numjeu}" var="jeuURL" />
                                <tr>
                                    <td><a href="${jeuURL}">${jeu.numjeu}</a></td>
                                    <td><a href="${jeuURL}">${jeu.libellejeu}</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        <a href="/apprenants/${apprenant.numapprenant}/inscrire">Inscire à un jeu</a>

    </jsp:body>
</tags:layout>