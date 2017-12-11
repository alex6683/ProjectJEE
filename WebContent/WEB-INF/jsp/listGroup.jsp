<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">
    <legend><h1>Liste des groupes</h1></legend>
    <table>
        <c:forEach items="${listGroups}" var="group">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/actions/person/personInGroup?id=${group.identifier}">
                            ${group.name}
                    </a>
                </td>
                <c:if test="${personLogged != null}">
                    <td>
                        <a href="${pageContext.request.contextPath}/actions/lists/deleteGroup?id=${group.identifier}">
                            Supprimer groupe vide
                        </a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${personLogged != null}">
        <tr>
            <td colspan="3">
                <a href="${pageContext.request.contextPath}/actions/group/addGroup" class="btn btn-info" >Ajouter un groupe</a>
            </td>
        </tr>
    </c:if>
</div>
