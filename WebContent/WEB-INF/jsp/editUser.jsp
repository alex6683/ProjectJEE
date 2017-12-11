<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="head-bootstrap.jsp"%>

<div class="container">
	<form:form method="POST" commandName="person" class="form-horizontal">
		<fieldset>
			<legend><h1>Modification de vos informations</h1></legend>
			<div class="form-group">
				<label class="col-lg-2 control-label">Groupe</label>
				<div class="col-md-2">
					<select class="form-control" id="select" name="group">
						<c:forEach items="${listGroup}" var="group">
							<option>${group.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="surname" class="col-lg-2 control-label">Prenom</label>
				<div class="col-lg-2">
					<form:input path="surname" value="${personLogged.surname}"
								class="form-control" />
					<small class="errors help-block"><form:errors
							path="surname" cssClass="error" /></small>
					<small class=
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-lg-2 control-label">Nom</label>
				<div class="col-lg-2">
					<form:input path="name" value="${personLogged.name}"
								class="form-control" />
					<small class="errors help-block"><form:errors
							path="name" cssClass="error" /></small>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-lg-2 control-label">Email</label>
				<div class="col-lg-2">
					<form:input path="email" value="${personLogged.email}"
								class="form-control" />
					<small class="errors help-block"><form:errors path="email"
																  cssClass="error" /></small>

				</div>
			</div>

			<div class="form-group">
				<label for="webSite" class="col-lg-2 control-label">Site web</label>
				<div class="col-lg-2">
					<form:input path="webSite" value="${personLogged.webSite}"
								class="form-control" />
					<small class="errors help-block"><form:errors path="webSite"
																  cssClass="error" /></small>
				</div>
			</div>

			<div class="form-group">
				<label for="dateBirth" class="col-lg-2 control-label">Date de
					naissance</label>
				<div class="col-lg-2">
					<fmt:formatDate value="${personLogged.dateBirth}" type="date" pattern="yyyy-MM-dd" var="formatedDate"/>
					<form:input path="dateBirth" type="date" value ="${formatedDate}" class="form-control" />
					<small class="errors help-block"><form:errors
							path="dateBirth" cssClass="error" /></small>
				</div>
			</div>

			<div class="form-group">
				<label for="password" class="col-lg-2 control-label">Mot de passe</label>
				<div class="col-lg-2">
					<form:password path="password" value="${personLogged.password}" class="form-control" />
					<small class="errors help-block"><form:errors
							path="password" cssClass="error" /></small>
				</div>
			</div>

			<div class="form-group">
				<label for="description" class="col-lg-2 control-label">Description</label>
				<div class="col-lg-2">
					<form:input path="description" value="${personLogged.description}"
								class="form-control" />
					<small class="errors help-block"><form:errors
							path="description" cssClass="error" /></small>
				</div>
			</div>

			<div class="form-group">
				<label class="col-lg-2 control-label"></label>
				<div class="col-lg-2">
					<input type="submit" class="btn btn-warning" value="Enregistrer" />
				</div>
			</div>
		</fieldset>
	</form:form>
</div>