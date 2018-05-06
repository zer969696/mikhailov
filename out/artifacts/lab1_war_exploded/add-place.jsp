<%@ page import="model.City" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: eevzerov
  Date: 5/6/18
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD PLACE</title>
</head>
<body>
<form action="add-place" method="post">
    <b>Добавьте Площадку</b> <br> <br>
    <label for="name">Введите название:</label>
    <input id="name" type="text" name="name"> <br>
    <label for="city">Выберите город:</label>
    <select id="city" name="city">
        <% List<City> cityList = (List<City>) request.getAttribute("cities"); %>
        <% for (City city : cityList) { %>
            <option value="<%=city.getId()%>"><%= city.getName()%></option>
        <% } %>
    </select> <br>
    <label for="capacity">Введите вместимость:</label>
    <input id="capacity" type="text" name="capacity"> <br> <br>
    <input type="submit">
</form>
</body>
</html>
