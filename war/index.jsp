<?xml version="1.0"?>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"
	session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=300px,user-scalable=no" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript" src="${f:url('/')}js/jquery.spinner.js"></script>
<title>${param.title}</title>
<link rel="stylesheet" href="${f:url('/')}css/screen.css" type="text/css"
	media="screen, projection" />
<link rel="stylesheet" href="${f:url('/')}css/print.css" type="text/css"
	media="print" />
<!--[if IE]>
  <link rel="stylesheet" href="${f:url('/')}css/ie.css" type="text/css" media="screen, projection" />
<![endif]-->
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen, projection" />
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-23554771-1']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
</head>
<body>
<div class="container">
<div id="header" class="span-24 large fancy">
<h1>こみかる(β)</h1>
<hr />
</div>
<div id="wrapper" class="span-24">
<div id="content" class="span-14 colborder">
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

こみかる - コミックの最新新刊情報をカレンダーで表示
</div>
<div id="side" class="span-9 last">


<h2>カレンダーURLの取得</h2>

<form action="#" id="wordform">
<div><label for="keywords">キーワード:</label><br><textarea id="keywords" name="keywords" cols="45" rows="5">
</textarea></div>


<button type="submit" class="button positive">
	  		<img src="images/icons/tick.png" alt="作成"> 作成
</button>
</form>
<hr class="space"/>
<div id="result" class="span-9"></div>

<script type="text/javascript">
$('#wordform').submit(function(event) {
    event.preventDefault();
    $('#result').spinner( { position: 'center',
                            img: '${f:url('/images/icons/spinner.gif')}',
                            height : 24,
                            width : 24 } );
    $.post("${f:url('/addcal/')}", $("#wordform").serialize(),
     function(data){
        $('#result').html(data);
         $('#result').spinner('remove');
     }
     );
});
</script>
	<jsp:include
	page="/sidemenu.jsp" /></div>
</div>
<hr class="space" />
<div id="footer" class="span-24">
<hr />
<address>Created by <a href="http://www.twitter.com/small_field">@small_field</a>. All Right Reserved.</address>
</div>
</div>
</body>
</html>
