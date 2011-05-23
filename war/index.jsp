<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"
	session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<jsp:include page="/layout.jsp">
	<jsp:param name="title" value="Index" />
	<jsp:param name="content">
		<jsp:attribute name="value">
           <h2>これは何？</h2>
        <p>
          お気に入りのコミックを、タイトルや作者名で検索して、新刊発売日をiPhoneの予定表や、Googleカレンダーに表示させる事のできるWebCalのURLを取得することが出来ます。利用料は無料です。新刊発売日の情報は逐次更新されますので、一度WebCalのURLを登録すれば、ずっと最新の発売日情報を取得することが出来ます。
        </p>
        <p>また、Amazonで購入可能なコミックに関しては、商品へのリンクが表示されるので、直接購入することもできます。</p>
			<h2>使い方</h2>
			<p>
<ol>
	<li>キーワード欄に、新刊発売日を知りたいコミックの名前や、作者名を入力します。一部でも構いません。改行やスペース区切りで複数のキーワードを入力できます。</li>
	<li>作成ボタンをクリックします。</li>
	<li>WebCal用のURLが表示されるので、これらをクライアントソフトに追加します。iPhone,Macの場合には、webcal://で始まるURLをクリックすれば、自動で予定表に登録されます。</li>
</ol>
			</p>
<p>現在${f:h(count)}件のデータがあります。</p>
        </jsp:attribute>
	</jsp:param>
</jsp:include>