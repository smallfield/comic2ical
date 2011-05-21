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
<title>${param.title}</title>
<link rel="stylesheet" href="css/screen.css" type="text/css"
	media="screen, projection" />
<link rel="stylesheet" href="css/print.css" type="text/css"
	media="print" />
<!--[if IE]>
  <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen, projection" />
<![endif]-->
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen, projection" />
</head>
<body>
<div class="container"><jsp:include page="/header.jsp">
	<jsp:param name="title" value="${param.title}" />
</jsp:include>
<div id="wrapper" class="span-24">
<div id="content" class="span-17 colborder">${param.content}</div>
<div id="side" class="span-6 last"><jsp:include
	page="/sidemenu.jsp" /></div>
</div>
<hr class="space" />
<div id="footer" class="span-24">
<hr />
<jsp:include page="/footer.jsp" /></div>
</div>
</body>
</html>


