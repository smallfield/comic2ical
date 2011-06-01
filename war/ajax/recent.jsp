<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<div>

<table>
	<tr>
		<th>作者</th>
		<th>作品</th>
		<th>出版社</th>
	</tr>


	<c:forEach var="e" items="${list}">
		<tr  onclick="location.href='${f:h(e.amazonUrl)}'" style="cursor: pointer;">
			<td>${f:h(e.author)}</td>
			<td>${f:h(e.title)}</td>
			<td>${f:h(e.seriesRef.model.publisherRef.model.name)}</td>
		</tr>
	</c:forEach>
</table>


</div>