<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: saraykin
  Date: 21.09.2018
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <style>
        td select,
        td input {
            width: 150px;
        }

        .error input,
        .error textarea {
            border: 1px solid red;
        }

        .error {
            color: red;
        }
    </style>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<h1 align="center">Edit/Add Computer Component</h1>
<form>
    <table width="200" class="table_borders2">
        <tr>
            <td width="100" class="td_style" height="30">Наименование</td>
            <td width="100">
                <input name="componentName" type="text" value="${computerComponent.name}" align="center">
            </td>
        </tr>
        <tr>
            <td class="td_style" height="30">Необходимость</td>
            <td>
                <select name="isNecessary" value="${computerComponent.isNecessary}" align="center">
                    <option value="1">да</option>
                    <option value="0">нет</option>
                </select>
            </td>
        </tr>
        <tr>
            <td height="30" class="td_style">Количество</td>
            <td>
                <input name="amount" type="number" value="${computerComponent.amount}">
            </td>
        </tr>
        <tr>
            <td height="30"><input type="button" onclick="save(this.form)" value="Сохранить"></td>
            <td height="30" class="td_style">
                <input type="button" onclick="location.href='/';" value="Вернуться">
            </td>
        </tr>
    </table>
    <p align="center">
        <c:if test="${warning != null}">
            ${warning}
        </c:if>
    </p>


</form>

<script>
    var error;

    function showError(container, errorMessage) {
        container.className = 'error';
        var msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
        error = true;
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error-message") {
            container.removeChild(container.lastChild);
            error = false;
        }

    }

    function save(form) {
        var elems = form.elements;

        resetError(elems.componentName.parentNode);
        if (!elems.componentName.value) {
            showError(elems.componentName.parentNode, 'Введите наименование');
        }

        resetError(elems.isNecessary.parentNode);
        if (!elems.isNecessary.value) {
            showError(elems.isNecessary.parentNode, ' Выберите значение');
        }

        resetError(elems.amount.parentNode);
        if (!elems.amount.value) {
            showError(elems.amount.parentNode, 'Введите количество');
        }
        if (!error) {
            document.location.href = "/save?id=${computerComponent.id}&isNecessary=" + elems["isNecessary"].value + "&name=" + elems["componentName"].value + "&amount=" + elems["amount"].value;
        }


    }
</script>


</body>
</html>
