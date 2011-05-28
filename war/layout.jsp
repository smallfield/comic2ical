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
<div class="container"><jsp:include page="/header.jsp">
	<jsp:param name="title" value="${param.title}" />
</jsp:include>
<div id="wrapper" class="span-24">
<div id="content" class="span-14 colborder">${param.content}</div>
<div id="side" class="span-9 last"><jsp:include
	page="/sidemenu.jsp" /></div>
</div>
<hr class="space" />
<div id="footer" class="span-24">
<hr />
<jsp:include page="/footer.jsp" /></div>
</div>
</body>
</html>


