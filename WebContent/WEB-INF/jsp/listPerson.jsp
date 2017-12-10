<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<h1>Liste des personnes</h1>

<div>
    <ul style="list-style-type: circle">
        <c:forEach items="${personsList}" var="personInList">
                <li>
                    <a href="${pageContext.request.contextPath}/actions/person/personData?id=${personInList.identifier}">
                            ${personInList.name} ${personInList.surname}
                    </a>
                </li>
        </c:forEach>
    </ul>
</div>
