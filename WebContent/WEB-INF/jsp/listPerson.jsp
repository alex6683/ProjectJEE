<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">
    <legend><h1>Liste des personnes</h1></legend>
    <div>
        <ul style="list-style-type: circle">
            <c:forEach items="${listPersons}" var="person">
                <li>
                    <a href="${pageContext.request.contextPath}/actions/person/personData?id=${person.identifier}">
                            ${person.name} ${person.surname}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

