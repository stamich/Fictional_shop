<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 27.11.17
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!DOCTYPE html>

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

    <title>Title</title>
</head>
<body>
<div id="content">
    <span class="smalltitle">Tworzenie rekordu</span>
    <div style="height: 5px;"></div>
    <form:form method="POST" modelAttribute="product">
        <form:input type="hidden" path="unitsInStock" id="unitsInStock"/>
        <form:input type="hidden" path="unitsInOrder" id="unitsInOrder"/>
        <form:input type="hidden" path="active" id="active"/>
        <table id="datatable1">
            <tr>
                <td><label for="productId">Numer produktu: </label> </td>
                <td><form:input path="productId" id="productId"/></td>
                <td><form:errors path="productId" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="productName">Nazwa: </label> </td>
                <td><form:input path="productName" id="productName"/></td>
                <td><form:errors path="productName" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="unitPrice">Cena jedn.: </label> </td>
                <td><form:input path="unitPrice" id="unitPrice"/></td>
                <td><form:errors path="unitPrice" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="productDescription">Opis: </label> </td>
                <td><form:input path="productDescription" id="productDescription"/></td>
                <td><form:errors path="productDescription" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="productManufacturer">Producent: </label> </td>
                <td><form:input path="productManufacturer" id="productManufacturer"/></td>
                <td><form:errors path="productManufacturer" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="productCategory">Kategoria: </label> </td>
                <td><form:input path="productCategory" id="productCategory"/></td>
                <td><form:errors path="productCategory" cssClass="error"/></td>
            </tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Zapisz"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
