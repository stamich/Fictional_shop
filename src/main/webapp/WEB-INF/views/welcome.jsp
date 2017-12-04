<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 30.04.17
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">

    <title>Multishop - panel główny</title>
</head>
<body>
<h1>Multishop.pl</h1>
<h1>Panel głowny</h1>

<h2>Wybierz sposób wyszukiwania:</h2>

<h3>
    <a href=" <c:url value="products" /> ">Asosrtyment</a>
    <a href=" <c:url value="clients" /> ">Klienci</a>
    <a href=" <c:url value="orders" /> ">Zamówienia</a>
</h3>

</body>
</html>
