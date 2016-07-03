<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Editar Usuário</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		<fieldset>
			<legend>Editar dados do Perfil</legend>
			<c:url value="saveSenha" var="/usuario/update/senha" />
			<form:form modelAttribute="usuario" action="/blog/usuario/update/senha" method="post">
				<form:hidden path="id"/>
				<fieldset class="grupo">
					<legend>Editar Senha</legend>
					<div class="campo">
						<form:label path="senha">Senha</form:label>
						<form:password path="senha" />
						<form:errors path="senha" cssClass="error" />
					</div>
					<div>
						<input type="submit" value="Salvar" />
						<input type="reset" value="Limpar" />
					</div>
				</fieldset>
			</form:form>
		
			<c:url var="save" value="/usuario/update" />
			<form:form modelAttribute="usuario" action="${save}" method="post">
				<fieldset class="grupo">
					<legend>Editar Nome e/ou E-mail</legend>
					<form:hidden path="id"/>
					<div class="campo">
						<form:label path="nome">Nome do Usuário</form:label><br/>
						<form:input type="text" path="nome" title="Inserir Nome" value="${nome}" />
						<form:errors path="nome" cssClass="error" />
					</div>
					<div class="campo">
						<form:label path="email">E-mail do Usuário</form:label><br/>
						<form:input type="email" path="email" title="Inserir Email" value="${email}" />
						<form:errors path="email" cssClass="error" />
					</div>
					<div>
						<input type="submit" value="Salvar" />
						<input type="reset" value="Limpar" />
					</div>
				</fieldset>
			</form:form>
		</fieldset>
	</body>
</html>