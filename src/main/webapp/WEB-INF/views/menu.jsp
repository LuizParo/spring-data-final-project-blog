<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fieldset style="font-family: monospace; font-size: 10pt;">
	<legend>Menu</legend>
	<nav>
		<a href="<c:url value="/usuario/form" />">Add Usuário</a>
		<a href="<c:url value="/usuario/list" />">Lista de Usuários</a>
	</nav>
	<nav>
		<a href="<c:url value="/autor/form" />">Add Autor</a>
		<a href="<c:url value="/autor/list" />">Lista de Autores</a>
	</nav>
</fieldset>