<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<fieldset class="header">
			<h1>Blog do Curso de Spring Data JPA | Devmedia</h1>
		</fieldset>
		
		<c:import url="menu.jsp" />
		<br/>
		
		<fieldset>
			<c:forEach items="${postagens}" var="postagem">
				<div>
					<div>
						<h2>${postagem.titulo}</h2>
						<p>Autor: <a href='<c:url value="/autor/${postagem.autor.nome}" />'>${postagem.autor.nome}</a> | Data: ${postagem.dataPostagem}</p>
					</div>
					<div>
						<p>${postagem.texto}</p>
					</div>
					<div>
						<p>
							<c:forEach items="${postagem.categorias}" var="categoria">
								<a href='<c:url value="/categoria/${categoria.permalink}"/>' title="${categoria.descricao}">| ${categoria.descricao}</a>
							</c:forEach>
						</p>
					</div>
				</div>
			</c:forEach>
		</fieldset>
	</body>
</html>