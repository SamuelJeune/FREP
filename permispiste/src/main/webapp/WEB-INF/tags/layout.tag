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
    <nav class="navbar navbar-default" style="background-color: #2c3e50;">
        <div class="container-fluid">
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-target">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/" style="color:white;">Permis Piste</a>
                    </div>
                </li>
                <li>
                    <div class="container"style="margin-top:7px;">
                    <form class="form-inline">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">search</span>
                            <input type="text" class="form-control" placeholder="Nom apprenant" aria-describedby="basic-addon1">
                        </div>
                    </form>
                    </div>

                </li>
            </ul>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li> <a href="/" style="color:white;">Accueil</a> </li>
                    <li> <a href="/missions" style="color:white;">Missions</a> </li>
                    <li> <a href="/apprenants" style="color:white;">Apprenants</a></li>
                    <li> <a href="/jeux" style="color:white;">Jeux</a> </li>
                    <li> <a href="/objectifs" style="color:white;">Objectifs</a></li>
                </ul>
            </div>
        </div>
    </nav>


        <div id="content">
            <jsp:doBody/>
        </div>

        <footer class="footer">
            <div class="container">
                <p class="text-muted text-center"> PermisPiste </p>
            </div>
        </footer>


        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>

    </body>
</html>