<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 31.04.17
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!DOCTYPE html>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="<c:url value='/resources/theme1/css/style.css' />" rel="stylesheet"></link>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">

    <spring:url value="http://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" var="jquerydtcss" />
    <link href="${jquerydtcss}" rel="stylesheet" />

    <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" var="jqueryJs" />
    <script src="${jqueryJs}"></script>

    <spring:url value="http://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js" var="jquerydt" />
    <script src="${jquerydt}"></script>

    <script type="text/javascript" >
        $(document).ready(function (){
            $('#table').DataTable({
            });
        });
    </script>

    <title>Lista produktów</title>
</head>
<html>
<body>

<div id="content">
    <span class="smalltitle">Produkty</span>
    <div style="height: 5px;"></div>
    <table id="table">
        <thead>
        <tr>
            <td>Numer produktu</td><td>Nazwa</td><td>Cena jedn.</td><td>Opis</td><td>Producent</td><td>Kategoria</td><td>Na stanie</td><td>Zamówione</td><td>Aktywny</td><td>Usuwanie</td>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td>(Edycja)</td><td>Nazwa</td><td>Cena jedn.</td><td>Opis</td><td>Producent</td><td>Kategoria</td><td>Na stanie</td><td>Zamówione</td><td>Aktywny</td><td>Usuwanie</td>
        </tr>
        </tfoot>
        <c:forEach items="${products}" var="product">
            <tr>
                <td><a href="<c:url value='/edit-${product.productId}-product' />">${product.productId}</a></td>
                <td>${product.productName}</td>
                <td>${product.unitPrice}</td>
                <td>${product.productDescription}</td>
                <td>${product.productManufacturer}</td>
                <td>${product.productCategory}</td>
                <td>${product.unitsInStock}</td>
                <td>${product.unitsInOrder}</td>
                <td>${product.active}</td>
                <td><a href="<c:url value='/delete-${product.productId}-product' />">Usuń</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="footer">
    <h5>Wszelkie prawa zastrzeżone &copy 2017 Michał Stawarski</h5>
</div>

</body>
</html>
