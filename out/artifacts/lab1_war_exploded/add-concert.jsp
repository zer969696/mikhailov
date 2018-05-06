<%@ page import="model.City" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Place" %><%--
  Created by IntelliJ IDEA.
  User: eevzerov
  Date: 5/6/18
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD CONCERT</title>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"></head>
<body>
<form action="add-concert" method="post">
    <b>Добавьте событие</b> <br> <br>
    <label for="price">Введите гонорар:</label>
    <input id="price" type="text" name="price"> <br>
    <label for="city">Выберите город:</label>
    <select id="city" name="city">
        <% List<City> cityList = (List<City>) request.getAttribute("cities"); %>
        <% for (City city : cityList) { %>
        <option value="<%=city.getId()%>"><%= city.getName()%></option>
        <% } %>
    </select> <br>
    <label for="place">Выберите площадку:</label>
    <select id="place" name="place">
        <%--<% List<Place> placeList = (List<Place>) request.getAttribute("places"); %>--%>
        <%--<% for (Place place : placeList) { %>--%>
        <%--<option value="<%=place.getId()%>"><%= place.getName()%></option>--%>
        <%--<% } %>--%>
    </select> <br>
    <label for="datepicker">Выберите дату:</label>
    <input type="text" id="datepicker" name="date"></p>
    <input type="submit">
</form>
</body>
<script>
    $("#city").change(function () {
        $("#place option").remove();
        var idCity = $(this).val();

        $.ajax({
            url: 'places',
            data: {
                idCity: idCity
            },
            type: 'get',
            success: function (resp) {
                for (var id in resp) {
                    $('#place')
                        .append($("<option></option>")
                            .attr("value", id)
                            .text(resp[id]));
                }
            }
        });

        // $('#place')
        //     .append($("<option></option>")
        //         .attr("value", key)
        //         .text(value));
    });

    $(function() {
        $("#datepicker").datepicker();
    });
</script>
</html>
