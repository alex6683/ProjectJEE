<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-3">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/actions/home/homePage">DirectoryManager</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse-3">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/actions/lists/personList">Personnes</a></li>
                <li><a href="${pageContext.request.contextPath}/actions/lists/groupList">Groupes</a></li>
                <c:if test="${personLogged == null}">
                    <li><a href="${pageContext.request.contextPath}/actions/connexion/login">Connection</a></li>
                </c:if>
                <c:if test="${personLogged != null}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="supportedContentDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Mon compte</a>
                    <div class="dropdown-menu" aria-labelledby="supportedContentDropdown">
                        <a class="dropdown-item " href="${pageContext.request.contextPath}/actions/connexion/user">Profil</a> <br/>
                        <a class="dropdown-item " href="${pageContext.request.contextPath}/actions/connexion/logOut">Deconnection</a>
                    </div>
                    </c:if>
                <li>
                    <a class="btn btn-default btn-outline btn-circle collapsed"  data-toggle="collapse" href="#nav-collapse3" aria-expanded="false" aria-controls="nav-collapse3">Recherche</a>
                </li>
            </ul>
            <div class="collapse nav navbar-nav nav-collapse slide-down" id="nav-collapse3">
                <form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/actions/searchBar/search" method="get">
                    <div class="form-group">
                        <input type="text" class="form-control" name="personSearcher" placeholder="Rechercher..." />
                    </div>
                    <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                </form>
            </div>
        </div>
    </div>
</nav>
