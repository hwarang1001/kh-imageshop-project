<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<h2>
	<spring:message code="codegroup.header.read" />
</h2>
<form:form id="codeGroup" modelAttribute="codeGroup" method="post">
	<table>
		<tr>
			<td><spring:message code="codegroup.groupCode" /></td>
			<td><form:input path="groupCode" readonly="true" /></td>
			<td><font color="red"><form:errors path="groupCode" /></font></td>
		</tr>
		<tr>
			<td><spring:message code="codegroup.groupName" /></td>
			<td><form:input path="groupName" readonly="true" /></td>
			<td><font color="red"><form:errors path="groupName" /></font></td>
		</tr>
	</table>
	<div>
		<button type="submit" id="btnEdit">
			<spring:message code="action.edit" />
		</button>
		<button type="submit" id="btnRemove">
			<spring:message code="action.remove" />
		</button>
		<button type="submit" id="btnList">
			<spring:message code="action.list" />
		</button>
	</div>
</form:form>

<script>
	$(document).ready(function() {
		var formObj = $("#codeGroup"); // form 객체 정의

		$("#btnEdit").on("click", function(e) {
			e.preventDefault();
			var groupCodeVal = $("#groupCode").val();
			self.location = "modify?groupCode=" + groupCodeVal;
		});

		$("#btnRemove").on("click", function(e) {
			e.preventDefault();
			formObj.attr("action", "/codegroup/remove"); // URL 소문자 맞춤
			formObj.submit(); // 폼 전송
		});

		$("#btnList").on("click", function(e) {
			e.preventDefault();
			self.location = "/codegroup/list"; // URL 소문자 맞춤
		});
	});
</script>

