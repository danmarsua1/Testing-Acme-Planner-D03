<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.dashboard.form.label.numberPublicTasks" path="numberPublicTasks"/>
	<acme:form-textbox code="administrator.dashboard.form.label.numberPrivateTasks" path="numberPrivateTasks"/>
	<acme:form-textbox code="administrator.dashboard.form.label.numberFinishedTasks" path="numberFinishedTasks"/>
	<acme:form-textbox code="administrator.dashboard.form.label.numberNotFinishedTasks" path="numberNotFinishedTasks"/>
	<acme:form-textbox code="administrator.dashboard.form.label.minExecutionStart" path="minExecutionStart"/>
	<acme:form-textbox code="administrator.dashboard.form.label.maxExecutionStart" path="maxExecutionStart"/>
	<acme:form-textbox code="administrator.dashboard.form.label.minExecutionEnd" path="minExecutionEnd"/>
	<acme:form-textbox code="administrator.dashboard.form.label.maxExecutionEnd" path="maxExecutionEnd"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageExecutionStart" path="averageExecutionStart"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageExecutionEnd" path="averageExecutionEnd"/>
	<acme:form-textbox code="administrator.dashboard.form.label.stddevExecutionStart" path="stddevExecutionStart"/>
	<acme:form-textbox code="administrator.dashboard.form.label.stddevExecutionEnd" path="stddevExecutionEnd"/>
	<acme:form-textbox code="administrator.dashboard.form.label.minWorkload" path="minWorkload"/>
	<acme:form-textbox code="administrator.dashboard.form.label.maxWorkload" path="maxWorkload"/>
	<acme:form-textbox code="administrator.dashboard.form.label.averageWorkload" path="averageWorkload"/>
	<acme:form-textbox code="administrator.dashboard.form.label.stddevWorkload" path="stddevWorkload"/>
	
	<acme:form-return code="administrator.dashboard.form.button.return" />
</acme:form>
