<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">

   <lengend><h1>Les informations de ${personData.name} ${personData.surname}</h1></lengend>

    <form:form method="POST" modelAttribute="person">
        <table>
            <tr>
                <td>Identifiant :</td>
                <td>${personData.identifier}</td>
            </tr>

            <tr>
                <td>Groupe :</td>
                <td><a
                        href="${pageContext.request.contextPath}/actions/person/personInGroup?id=${groupData.identifier}">
                        ${groupData.name}</a>
                </td>
            </tr>
            <tr>
                <td>Prenom :</td>
                <td>${personData.surname}</td>
            </tr>

            <tr>
                <td>Nom :</td>
                <td>${personData.name}</td>
            </tr>

            <c:if test="${personLogged != null}">
                <tr>
                    <td>Email :</td>
                    <td>${personData.email}</td>
                </tr>
            </c:if>

            <tr>
                <td>Site web :</td>
                <td>
                    <a target="_blank" href="${personData.webSite}">
                            ${personData.webSite}
                    </a>
                </td>
            </tr>

            <c:if test="${personLogged != null}">
                <tr>
                    <td>Naissance :</td>
                    <td>${personData.dateBirth}</td>
                </tr>
            </c:if>
            <tr>
                <td>Description :</td>
                <td>${personData.description}</td>
            </tr>
        </table>
    </form:form>
</div>