<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tags:layout>
    <jsp:attribute name="title">
      F.R.E.P. - Jeu : ${jeu.libellejeu}
    </jsp:attribute>
    <jsp:body>
        <%--<c:if test="${not empty msg}">--%>
            <%--${msg}--%>
        <%--</c:if>--%>
        Jeu : ${jeu.libellejeu}
        <br />
        Missions :
        <c:choose>
            <c:when test="${empty missions}">
                Ce jeu ne possède pas de missions.
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <td>Num. mission</td>
                        <td>Libellé missions</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${missions}" var="mission">
                        <tr>
                            <td>${mission.nummission}</td>
                            <td>${mission.libmission}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</tags:layout>