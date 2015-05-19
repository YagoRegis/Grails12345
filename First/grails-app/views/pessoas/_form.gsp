<%@ page import="cadastro.Pessoas" %>



<div class="fieldcontain ${hasErrors(bean: pessoasInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="pessoas.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${pessoasInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoasInstance, field: 'sexo', 'error')} ">
	<label for="sexo">
		<g:message code="pessoas.sexo.label" default="Sexo" />
		
	</label>
	<g:textField name="sexo" value="${pessoasInstance?.sexo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoasInstance, field: 'telefone', 'error')} required">
	<label for="telefone">
		<g:message code="pessoas.telefone.label" default="Telefone" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="telefone" type="number" value="${pessoasInstance.telefone}" required=""/>
</div>

