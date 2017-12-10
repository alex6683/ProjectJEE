<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>
	
<!-- 	<h1>Inscription</h1> -->

<%-- 	<form:form method="POST" commandName="person"> --%>
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>Numero :</td> -->
<%-- 				<td><form:input path="idPers"  /></td> --%>

<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="idPers" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>Groupe :</td> -->
<%-- 				<td><form:input path="idGroup"  /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="idGroup" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>Pr�nom :</td> -->
<%-- 				<td><form:input path="firstName" /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="firstName" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>Nom :</td> -->
<%-- 				<td><form:input path="lastName" /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="lastName" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>Email :</td> -->
<%-- 				<td><form:input path="mail" /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="mail" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>Site web :</td> -->
<%-- 				<td><form:input path="web" /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="web" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>Naissance :</td> -->
<%-- 				<td><form:input path="naissance" /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="naissance" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td>Password :</td> -->
<%-- 				<td><form:input path="password" /></td> --%>
<%-- 				<td><small class="errors help-block"><form:errors --%>
<%-- 							path="password" cssClass="error" /></small></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<td colspan="3"><input type="submit" class="btn btn-warning" /></td> -->
<!-- 			</tr> -->

<!-- 		</table> -->
<%-- 	</form:form> --%>

	
	<form:form method="POST" commandName="person"  class="form-horizontal">
	<fieldset>
		<legend>Inscription</legend>
		
		<div class="form-group">
			<label for="idPers" class="col-lg-2 control-label">Votre id</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="firstName" placeholder="Pr�nom"> -->
				<form:input path="idPers" value="${personLogged.idPers}" class="form-control" />
				<small class="errors help-block"><form:errors path="firstName" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="idGroup" class="col-lg-2 control-label">Numero groupe</label>
			<div class="col-md-2">
				 <select class="form-control" id="select" name="groupSignUp">
		         	 <c:forEach items="${listGroup}" var="group">
	         	 		<option>${group.nameGroup}</option>
		         	 </c:forEach>
		        </select>
				<small class="errors help-block"><form:errors path="idGroup" cssClass="error" /></small>
				
			</div>
		</div>
		
		<div class="form-group">
			<label for="firstName" class="col-lg-2 control-label">Pr�nom</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="firstName" placeholder="Pr�nom"> -->
				<form:input path="firstName" value="${personLogged.firstName}" class="form-control" />
				<small class="errors help-block"><form:errors path="firstName" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="lastName" class="col-lg-2 control-label">Nom</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="lastName" placeholder="Nom"> -->
				<form:input path="lastName" value="${personLogged.lastName}" class="form-control" />
				<small class="errors help-block"><form:errors path="lastName" cssClass="error" /></small>				
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputEmail" class="col-lg-2 control-label">Email</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="inputEmail"	placeholder="Email"> -->
				<form:input path="mail" value="${personLogged.mail}" class="form-control" />
				<small class="errors help-block"><form:errors path="mail" cssClass="error" /></small>
				
			</div>
		</div>
		
		<div class="form-group">
			<label for="web" class="col-lg-2 control-label">Site web</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="web" placeholder="Web"> -->
				<form:input path="web" value="${personLogged.web}" class="form-control"/>
				<small class="errors help-block"><form:errors path="web" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="naissance" class="col-lg-2 control-label">Date de naissance</label>
			<div class="col-lg-2">
<!-- 				<input type="text" class="form-control" id="naissance" placeholder="Naissance"> -->
				<form:input path="naissance" value="${personLogged.naissance}" class="form-control"/>
				<small class="errors help-block"><form:errors path="naissance" cssClass="error" /></small>
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputPassword" class="col-lg-2 control-label">Mot de passe</label>
			<div class="col-lg-2">
<!-- 				<input type="password" class="form-control" id="inputPassword" placeholder="Password"> -->
				<form:password path="password" value="${personLogged.password}"  class="form-control"/>
				<small class="errors help-block"><form:errors path="password" cssClass="error" /></small>
				
			</div>
		</div>
		<input type="submit" class="btn btn-warning" value="S'inscrire"/>
	</fieldset>
</form:form>
</body>
</html>
