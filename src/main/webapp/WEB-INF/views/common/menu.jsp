<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div align="right">
	<table>
		<tr>
			<td width="80">
				<a href="/user/register"><spring:message code="header.joinMember" /></a>
			</td>
			<!-- 코드 그룹 관리 메뉴 추가 -->
			<td width="120">
				<a href="/codegroup/list"><spring:message code="menu.codegroup.list" /></a>
			</td>
			<!-- 코드 관리 메뉴 추가 --> 
			<td width="120">
				<a href="/codedetail/list"><spring:message code="menu.codedetail.list" /></a>
			</td> 
</tr> 
		</tr>
	</table>
</div>
<hr>
