<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <!-- Bootstrap CSS -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
<table id="accidentTable" class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">№</th>
        <th scope="col">ИД</th>
        <th scope="col">Имя</th>
        <th scope="col">Содержание</th>
        <th scope="col">Адрес</th>
        <th scope="col">Тип</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accidents}" var="accident" varStatus="listIndex">

            <tr>
                <td>${listIndex.index + 1}</td>
                <td>${accident.id}</td>
                <td><a href="<c:url value="/edit?id=${accident.id}"/>">${accident.name}</a></td>
                <td>${accident.text}</td>
                <td>${accident.address}</td>
                <td>${accident.type.name}</td>
            </tr>

    </c:forEach>
    </tbody>
</table>

</body>
</html>