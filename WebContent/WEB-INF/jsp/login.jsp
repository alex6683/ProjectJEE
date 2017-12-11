<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">
    <legend><h1>Connection</h1></legend>
    <form:form method="POST" commandName="person" >
        <table>
            <tr>
                <td>Email :</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Mot de passe :</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td/>
                <td colspan="3"><input type="submit" class="btn btn-success" value="Connection"/></td>
            </tr>
        </table>
        <br/>
        <p>Ou s'inscrire ici :</p>
        <ul>
            <a href="${pageContext.request.contextPath}/actions/connexion/registration">Creer un compte</a>
        </ul>
    </form:form>
</div>