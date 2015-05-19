
<%@ page import="cadastro.Pessoas" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pessoas.label', default: 'Pessoas')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pessoas" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pessoas" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'pessoas.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="sexo" title="${message(code: 'pessoas.sexo.label', default: 'Sexo')}" />
					
						<g:sortableColumn property="telefone" title="${message(code: 'pessoas.telefone.label', default: 'Telefone')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pessoasInstanceList}" status="i" var="pessoasInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pessoasInstance.id}">${fieldValue(bean: pessoasInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: pessoasInstance, field: "sexo")}</td>
					
						<td>${fieldValue(bean: pessoasInstance, field: "telefone")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pessoasInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
