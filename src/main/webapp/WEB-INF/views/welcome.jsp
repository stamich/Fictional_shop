<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!DOCTYPE html>
    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">

    <title>Multishop - panel główny</title>
</head>
<body>
<h1>Multishop.pl</h1>
<h1>Panel głowny</h1>

<h2>Wybierz sposób wyszukiwania:</h2>

<h3>
    <a href=" <c:url value="productsList" /> ">Asosrtyment</a>
    <a href=" <c:url value="clientsList" /> ">Klienci</a>
    <a href=" <c:url value="ordersList" /> ">Zamówienia</a>

    <a href=" <c:url value="multishop" /> ">Sklep</a>
</h3>

</body>
</html>
