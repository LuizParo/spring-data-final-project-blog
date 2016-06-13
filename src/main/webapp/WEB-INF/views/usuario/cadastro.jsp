<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Usuário</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		<c:url var="save" value="/usuario" />
		<form:form modelAttribute="usuario" action="${save}" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>Cadastro de Usuários</legend>
				<form:hidden path="id"/>
				<div class="campo">
					<form:label path="nome">Nome do Usuário</form:label><br/>
					<form:input type="text" path="nome" title="Inserir Nome" required="true" />
				</div>
				<div class="campo">
					<form:label path="email">E-mail do Usuário</form:label><br/>
					<form:input type="email" path="email" title="Inserir Email" required="true" />
				</div>
				<div class="campo">
					<form:label path="senha">Senha</form:label><br/>
					<form:password path="senha" required="true"/>
				</div>
				<div class="campo">
					<label for="file">Avatar</label><br/>
					<input type="file" name="file" required="true" />
				</div>
				<div class="campo">
					<form:label path="perfil">Perfil</form:label><br/>
					<form:select path="perfil" required="true">
						<form:option value="ADMIN" label="ADMIN" />
						<form:option value="AUTOR" label="AUTOR" />
						<form:option value="LEITOR" label="LEITOR" />
					</form:select>
				</div>
				<div>
					<input type="submit" value="Salvar" />
					<input type="reset" value="Limpar" />
				</div>
			</fieldset>
		</form:form>
	</body>
</html>