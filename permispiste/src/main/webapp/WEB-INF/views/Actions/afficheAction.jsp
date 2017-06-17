<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Action : ${action.libaction}
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty msg}">
            ${msg}
        </c:if>
        <div class="container">
            <div class="jumbotron">
                Action : ${action.libaction}
                <br />
                <c:choose>
                    <c:when test="${not empty action.actNumaction}">
                        Action parent : <a href="/actions/${action.actNumaction}"> ${action.actNumaction} - ${action.libaction}</a>
                    </c:when>
                    <c:otherwise>
                        Cette action ne possède pas d'action parent.
                        <br />
                        <a href="/actions/${action.numaction}/modifier">Ajouter une action parent</a>
                    </c:otherwise>
                </c:choose>
                <br />
                <c:choose>
                    <c:when test="${objectifs.size() > 0}">
                        Cette action est inclue dans les objectifs suivants :
                        <table>
                            <thead>
                                <tr>
                                    <td>Num. objectif</td>
                                    <td>Libellé objectif</td>
                                    <td>Actions</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${objectifs}" var="objectif">
                                    <tr>
                                        <td><a href="/objectifs/${objectif.numobjectif}">Objectif n°${objectif.numobjectif}</a></td>
                                        <td>${objectif.libobjectif}</td>
                                        <td>
                                            <form method="post" action="/actions/${action.numaction}/retirer-objectif/${objectif.numobjectif}">
                                                <button type="submit">Supprimer</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        Cette action n'est inclue dans aucun objectif.
                    </c:otherwise>
                </c:choose>
                <a href="/actions/${action.numaction}/ajouter-objectif">Ajouter un objectif</a>
            </div>
        </div>
    </jsp:body>
</tags:layout>