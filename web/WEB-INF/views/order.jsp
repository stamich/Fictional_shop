<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 09.05.17
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Zamówienie</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Zamówienia</h1>
            <p>Oto kolejne zamówienie:</p>
        </div>
    </div>
</section>

<section class="container">
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <div class="caption">
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <p>${product.unitPrice} PLN</p>
                        <p>Liczba sztuk w magazynie: ${product.unitsInStock}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>
