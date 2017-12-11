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

    <title>Lista klientów</title>
</head>
<html>
<body>

<div id="content">
    <span class="smalltitle">Klienci</span>
    <div style="height: 5px;"></div>
    <table id="table">
        <thead>
        <tr>
            <td>Numer klienta</td><td>Imię</td><td>Nazwisko</td><td>Adres</td><td>Miasto</td><td>Kraj</td><td>E-mail</td><td>Telefon</td><td>Usuwanie</td>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td>(Edycja)</td><td>Imię</td><td>Nazwisko</td><td>Adres</td><td>Miasto</td><td>Kraj</td><td>E-mail</td><td>Telefon</td><td>Usuwanie</td>
        </tr>
        </tfoot>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td><a href="<c:url value='/edit-${client.clientId}-client' />">${client.clientId}</a></td>
                <td>${client.clientName}</td>
                <td>${client.clientSurname}</td>
                <td>${client.clientAdress}</td>
                <td>${client.clientCity}</td>
                <td>${client.clientCountry}</td>
                <td>${client.clientEmail}</td>
                <td>${client.clientPhone}</td>
                <td><a href="<c:url value='/delete-${client.clientId}-client' />">Usuń</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="footer">
    <h5>Wszelkie prawa zastrzeżone &copy 2017 Michał Stawarski</h5>
</div>

</body>
</html>

