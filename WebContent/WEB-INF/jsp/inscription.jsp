<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>

	<form:form method="POST" commandName="person"  class="form-horizontal">
	<fieldset>
		<legend>Inscription</legend>
		
		<div class="form-group">
			<label class="col-lg-2 control-label">Numero groupe</label>
			<div for="groupID" class="col-md-2">
				 <select class="form-control" id="select" name="groupSignUp">
		         	 <c:forEach items="${listGroup}" var="group">
	         	 		<option>${group.name}</option>
		         	 </c:forEach>
		        </select>
				<small class="errors help-block"><form:errors path="groupID" cssClass="error" /></small>
				
			</div>
		</div>
		
		<div class="form-group">
			<label for="surname" class="col-lg-2 control-label">Pr�nom</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="firstName" placeholder="Pr�nom"> -->
				<form:input path="surname" value="${personLogged.surname}" class="form-control" />
				<small class="errors help-block"><form:errors path="surname" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-lg-2 control-label">Nom</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="lastName" placeholder="Nom"> -->
				<form:input path="name" value="${personLogged.name}" class="form-control" />
				<small class="errors help-block"><form:errors path="name" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="email" class="col-lg-2 control-label">Email</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="inputEmail"	placeholder="Email"> -->
				<form:input path="email" value="${personLogged.email}" class="form-control" />
				<small class="errors help-block"><form:errors path="email" cssClass="error" /></small>
				
			</div>
		</div>
		
		<div class="form-group">
			<label for="webSite" class="col-lg-2 control-label">Site web</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="web" placeholder="Web"> -->
				<form:input path="webSite" value="${personLogged.webSite}" class="form-control"/>
				<small class="errors help-block"><form:errors path="webSite" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="dateBirth" class="col-lg-2 control-label">Date de naissance</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="naissance" placeholder="Naissance"> -->
				<form:input path="dateBirth" value="${personLogged.dateBirth}" class="form-control"/>
				<small class="errors help-block"><form:errors path="dateBirth" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="password" class="col-lg-2 control-label">Mot de passe</label>
			<div class="col-lg-2">
<!-- 				<input type="password" class="form-control" id="inputPassword" placeholder="Password"> -->
				<form:password path="password" value="${personLogged.password}"  class="form-control"/>
				<small class="errors help-block"><form:errors path="password" cssClass="error" /></small>
			</div>
		</div>

        <div class="form-group">
            <label for="description" class="col-lg-2 control-label">Description</label>
            <div class="col-lg-2">
                <!-- 				<input type="password" class="form-control" id="inputPassword" placeholder="Password"> -->
                <form:input path="description" value="${personLogged.description}"  class="form-control"/>
                <small class="errors help-block"><form:errors path="description" cssClass="error" /></small>
            </div>
        </div>
		<input type="submit" class="btn btn-warning" value="Inscription"/>
	</fieldset>
</form:form>
