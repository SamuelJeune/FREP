<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="refresh" content="0;URL=javascript:fermer();">
        <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css">
        <title><jsp:invoke fragment="title"/></title>
    </head>

    <body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Home</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div>
                <ul class="nav navbar-nav">
                    <%--<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>--%>
                    <li class="navbar-link">
                        <a href="/apprenants">Apprenants</a>
                    </li>
                    <li class="navbar-link">
                        <a href="/jeux">Jeux</a>
                    </li>
                    <li>
                        <a href="/missions">Missions</a>
                    </li>
                    <li>
                        <a href="/objectifs">Objectifs</a>
                    </li>
                    <li>
                        <a href="/actions">Actions</a>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


        <div id="content">
            <jsp:doBody/>
        </div>

        <footer class="footer">
            <div class="container">
                <p class="text-muted text-center"> PermisPiste </p>
            </div>
        </footer>


        <script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
        <script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>

    </body>
</html>