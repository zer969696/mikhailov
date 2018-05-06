<%@ page import="model.Concert" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: eevzerov
  Date: 5/6/18
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="width:100%">
    <tr>
        <th>Дата</th>
        <th>Гонорар</th>
        <th>Город</th>
        <th>Площадка</th>
    </tr>
    <% List<Concert> concerts = (List<Concert>) request.getAttribute("concerts"); %>
    <% for (Concert concert : concerts) {%>
    <tr>
        <td><%= concert.getDate()%></td>
        <td><%= concert.getPrice()%> руб.</td>
        <td><%= concert.getCity().getName()%></td>
        <td><%= concert.getPlace().getName()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>
