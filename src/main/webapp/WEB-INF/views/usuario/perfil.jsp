<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Perfil</title>
		<style type="text/css">
			.campo {
				margin-bottom: : 1em;
			}
			.campo input:FOCUS, .campo select:FOCUS {
				background: : #f8f8f8;
			}
			.table {
				width: 640px;
				text-align: center;
			}
			fieldset {
				width: 640px;
				margin: 0 auto;
			}
			fieldset.group .campo {
				float: left;
				margin-right: 2em;
			}
			.master {
				width: 960px;
				margin: 0 auto;
			}
		</style>
	</head>
	<body>
		<fieldset>
			<legend>Perfil</legend>
			<table class="table">
				<tr>
					<th>Nome do Usuário</th>
					<th>E-mail</th>
					<th>Data de Cadastro</th>
					<th>Perfil</th>
					<th>Ação</th>
				</tr>
				<tr>
					<td>${usuario.nome}</td>
					<td>${nusuario.email}</td>
					<td>${usuario.dataCadastro}</td>
					<td>${usuario.perfil}</td>
				</tr>
				<tr>
					<a href="#" title="Editar">Editar</a>
					<a href="#" title="Excluir">Excluir</a>
				</tr>
			</table>
		</fieldset>
	</body>
</html>