<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel Management Application - Reservation List</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: Navy Blue">
            <div>
                <a href="http://localhost:8080/HotelManagement/" class="navbar-brand">User Management App</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employee</a></li>
                <li><a href="<%=request.getContextPath()%>/reservation" class="nav-link">Reservation</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container">
        <h2>Reservation List</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Reservation ID</th>
                    <th>Customer ID</th>
                    <th>Category ID</th>
                    <th>Check-in Date</th>
                    <th>Check-out Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td><c:out value="${reservation.reservationID}" /></td>
                        <td><c:out value="${reservation.customerID}" /></td>
                        <td><c:out value="${reservation.categoryID}" /></td>
                        <td><c:out value="${reservation.checkInDate}" /></td>
                        <td><c:out value="${reservation.checkOutDate}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
