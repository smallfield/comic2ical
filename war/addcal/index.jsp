<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<p class="success"> <img src="images/icons/tick.png" alt="成功">以下のキーワードで新刊の検索を行う、カレンダーを作成しました。</p>


<div class="clearfix"><ul class="keywords">
		<c:forEach var="e" items="${keywords}">
			<li>${f:h(e)}</li>
		</c:forEach>
	</ul>
</div>

<p>以下のURLから参照可能です。</p>

<p class="notice"><a href="${f:h(url)}">${f:h(url)}</a></p>

<p>iPhone,Macの方は、以下のリンクから、スケジュールに登録できます。</p>
<p class="notice"><a href="${f:h(webcalurl)}">${f:h(webcalurl)}</a></p>