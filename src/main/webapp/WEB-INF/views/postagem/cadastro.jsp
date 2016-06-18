<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Postagem</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		<c:url var="save" value="/postagem" />
		<form:form modelAttribute="postagem" action="${save}" method="post">
			<fieldset>
				<legend>Cadastro de Postagem</legend>
				<form:hidden path="id"/>
				<div class="campo">
					<form:label path="titulo">Título do Post</form:label><br/>
					<form:input type="text" path="titulo" title="Título do Post" required="true" />
				</div>
				<div class="campo">
					<form:label path="texto">Texto do Post</form:label><br/>
					<form:textarea rows="15" cols="80" path="texto" title="Texto do Post" required="true" />
				</div>
				<div class="campo">
					<form:label path="categorias">Seleciona a(s) Categoria(s)</form:label><br/>
					<form:select path="categorias" multiple="true">
						<form:options items="${categorias}" itemValue="id" itemLabel="descricao" />
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