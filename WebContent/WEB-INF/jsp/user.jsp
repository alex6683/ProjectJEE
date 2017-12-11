<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">
    <legend><h1>${personLogged.surname} ${personLogged.name}</h1></legend>
    <h3>Vos informations</h3>
    <form:form method="POST" commandName="person">
        <table>
            <tr>
                <td>ID : </td>
                <td>${personLogged.identifier}</td>
            </tr> M2FSI

            <tr>
                <td>Groupe :</td>
                <td>${groupPersonLogged.name}</td>
            </tr>

            <tr>
                <td>Nom :</td>
                <td>${personLogged.name}</td>
            </tr>

            <tr>
                <td>Prenom :</td>
                <td>${personLogged.surname}</td>
            </tr>

            <tr>
                <td>Email :</td>
                <td>${personLogged.email}</td>
            </tr>

            <tr>
                <td>Site web :</td>
                <td><a target="_blank" href="http://www.${personLogged.webSite}">${personLogged.webSite}</a></td>
            </tr>


            <tr>
                <td>Date de Naissance :</td>
                <td>${personLogged.dateBirth}</td>
            </tr>

            <tr>
                <td>Mot de passe :</td>
                <td>${personLogged.password}</td>
            </tr>

            <tr>
                <td>Description :</td>
                <td>${personLogged.description}</td>
            </tr>
        </table>
        <br/>
        <a href="${pageContext.request.contextPath}/actions/connexion/editUser" class="btn btn-info">
            Editer votre profil
        </a>
        <a href="${pageContext.request.contextPath}/actions/connexion/logOut" class="btn btn-warning">
            Deconnexion
        </a>
        <a href="${pageContext.request.contextPath}/actions/connexion/deleteUser?id=${personLogged.identifier}" class="btn btn-danger">
            Supprimer le compte
        </a>
    </form:form>
</div>