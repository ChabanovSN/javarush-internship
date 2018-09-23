<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Computer Components List</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        body {
            font-family: Helvetica, Arial, sans-serif;
            font-size: 0.9em;
            text-rendering: optimizeLegibility;
        }
    </style>

</head>
<body>
<h1 align="center"><a href="/1">Computer Components List</a></h1>
<p>
</p>

<form>
    <table align="center" border="0" width="600" class="table_borders">
        <tr style="border-bottom: 1px solid red">
            <td align="center">
                <input name="name" type="text">
                <input type="button" onclick="clickSearch(this.form);" value="Найти">
            </td>
            <td align="center" height="20">
                <select id="cd-dropdown" class="showFilter" onchange="top.location=this.value" align="center">
                    <option value="">Фильтр</option>
                    <option value="/1?filter=showAllComponents">Показать все детали</option>
                    <option value="/1?filter=showNecessary">Только обязательные</option>
                    <option value="/1?filter=showUnnecessary">Только необязательные</option>
                </select>
            </td>
            <td align="center">
                <input type="button" onclick="location.href='/add';" value="Добавить деталь">
            </td>
        </tr>
    </table>
</form>
<p></p>

<table border="0.2" width="600" class="table_borders">

    <tr align="center" class="tr_borders">
        <td height="30" valign="top">Наименование</td>
        <td valign="top">Необходимость</td>
        <td valign="top">Количество</td>
        <td valign="top">Удалить</td>
        <td valign="top">Редактировать</td>
    </tr>
    <c:forEach items="${componentsByPage}" var="component">
        <tr>
            <td class="td_style"> ${component.name} </td>
            <td align="center">
                <c:if test="${1 == component.isNecessary}">
                    да
                </c:if>
                <c:if test="${0 == component.isNecessary}">
                    нет
                </c:if>
            </td>
            <td style="text-align: center;">${component.amount}</td>
            <td style="text-align: center;">
                <a href="/${pageNumber}?crudOperation=delete&id=${component.id}">
                    <img src="/images/deleteIcon.png" width="20" height="20" alt="delete"/>
                </a>
            </td>
            <td style="text-align: center;">
                <a href="/edit?id=${component.id}">
                    <img src="/images/edit.png" width="20" height="20" alt="delete"/>
                </a></td>
        </tr>
    </c:forEach>
</table>
<p align="center">
    <c:forEach var="i" begin="1" end="${numberOfPages}" step="1">
        <a href="/${i}?filter=${filterAndSorting}">${i}</a>
    </c:forEach>
</p>
<table class="table_borders">
    <tr>
        <td class="td_style">
            Можно собрать: ${numberOfComputerSets} комплектов
        </td>
    </tr>

</table>


<script>
    function clickSearch(form) {
        var elems = form.elements;
        document.location.href = "/search?name=" + elems["name"].value;
    }

</script>
</body>

</html>