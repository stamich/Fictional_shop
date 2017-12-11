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

    <title>Lista zamówień</title>
</head>
<html>
<body>

<div id="content">
    <span class="smalltitle">Zamówienia</span>
    <div style="height: 5px;"></div>
    <table id="table">
        <thead>
        <tr>
            <td>Numer zamówienia</td><td>Numer klienta</td><td>Numer produktu</td><td>Kwota</td><td>Data</td><td>Status</td><td>Usuwanie</td>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td>Numer zamówienia</td><td>Numer klienta</td><td>Numer produktu</td><td>Kwota</td><td>Data</td><td>Status</td><td>Usuwanie</td>
        </tr>
        </tfoot>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td><a href="<c:url value='/edit-${orders.orderId}-order' />">${orders.orderId}</a></td>
                <td>${orders.clientId}</td>
                <td>${orders.productId}</td>
                <td>${orders.amount}</td>
                <td>${orders.orderDate}</td>
                <td>${orders.orderStatus}</td>
                <td><a href="<c:url value='/delete-${orders.orderId}-order' />">Usuń</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="footer">
    <h5>Wszelkie prawa zastrzeżone &copy 2017 Michał Stawarski</h5>
</div>

</body>
</html>
