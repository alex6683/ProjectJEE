<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>
<h1>Liste des personnes</h1>

<div>
    <table>
        <c:forEach items="${personsList}" var="personInList">
            <tr>
                <td>

                    <a href="${pageContext.request.contextPath}/actions/person/showPerson?id=${personInList.identifier}">
                            ${personInList.name} ${personInList.surname}
                    </a>
                </td>
                <td>
                        <%--EDIT ICI--%>
                </td>
                <td>
                    <c:if test="${personLogged != null}">
                    <a href="${pageContext.request.contextPath}/actions/lists/deletePerson?id=${personInList.identifier}">
                        Supprimer
                    </a>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<c:if test="${personLogged != null}">
    <p>
        <a href="redirect:/actions/person/editPerson">Ajouter une nouvelle personne</a>
    </p>
</c:if>

</body>
</html>