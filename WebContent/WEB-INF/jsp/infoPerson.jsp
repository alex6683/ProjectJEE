<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>
<h1>Les informations de ${infoPerson.name} ${infoPerson.surname}</h1>

<form:form method="POST" modelAttribute="person">
    <table>
        <tr>
            <td>Identifiant :</td>
            <td>${infoPerson.identifier}</td>
        </tr>

        <tr>
            <td>Groupe :</td>
            <td><a
                    href="${pageContext.request.contextPath}/actions/person/showPersInGroup?id=${groupPersonListed.identifier}">
                    ${groupPersonListed.name}</a>
            </td>
        </tr>
        <tr>
            <td>Prenom :</td>
            <td>${infoPerson.surname}</td>
        </tr>

        <tr>
            <td>Nom :</td>
            <td>${infoPerson.name}</td>
        </tr>

        <c:if test="${personLogged != null}">
            <tr>
                <td>Email :</td>
                <td>${infoPerson.email}</td>
            </tr>
        </c:if>

        <tr>
            <td>Site web :</td>
            <td>
                <a target="_blank" href="${infoPerson.webSite}">
                        ${infoPerson.webSite}
                </a>
            </td>
        </tr>

        <c:if test="${personLogged != null}">
            <tr>
                <td>Naissance :</td>
                <td>${infoPerson.dateBirth}</td>
            </tr>
        </c:if>
        <tr>
            <td>Description :</td>
            <td>${infoPerson.description}</td>
        </tr>
    </table>
</form:form>
</body>
</html>