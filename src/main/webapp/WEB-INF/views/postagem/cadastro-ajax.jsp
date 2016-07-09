<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Postagem</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
		<script type="text/javascript" src="<c:url value="/js/jquery-2.1.4.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/postagem.js" />"></script>
	</head>
	<body>
		<c:import url="../menu.jsp" />
		<br/>
		<form id="save-ajax">
			<security:csrfInput/>
			<fieldset>
				<legend>Cadastro de Postagem</legend>
				<div class="campo">
					<label for="titulo">Título do Post</label><br/>
					<input type="text" name="titulo" title="Título do Post" />
					<span id="titulo-error" class="error" ></span>
				</div>
				<div class="campo">
					<label for="texto">Texto do Post</label><br/>
					<textarea rows="15" cols="80" name="texto" title="Texto do Post" ></textarea>
					<span id="texto-error" class="error" ></span>
				</div>
				<div class="campo">
					<label for="categorias">Seleciona a(s) Categoria(s)</label><br/>
					<select name="categorias" multiple >
						<c:forEach items="${categorias}" var="categoria">
							<option value="${categoria.id}">${categoria.descricao}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<input type="submit" value="Salvar" />
					<input type="reset" value="Limpar" />
				</div>
			</fieldset>
		</form>
		
		<div id="info"></div>
	</body>
</html>