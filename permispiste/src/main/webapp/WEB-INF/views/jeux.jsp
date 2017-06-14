<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Liste des jeux
    </jsp:attribute>
    <jsp:body>
        <table>
            <thead>
            <tr>
                <th>Num. jeu</th>
                <th>Libellé jeu</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jeux}" var="jeu">
                <tr>
                    <td>${jeu.numjeu}</td>
                    <td>${jeu.libellejeu}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</tags:layout>