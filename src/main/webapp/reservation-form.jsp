<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel Management Application - Reservation Form</title>
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
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <h2>Reservation Form</h2>
                <form action="reservations" method="post">
                    <input type="hidden" name="customer_id" value="${1}">
                    <fieldset class="form-group">
                        <label for="category_id">Room Category</label>
                        <select id="category_id" name="category_id" class="form-control">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryId}" data-price="${category.price}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="check_in_date">Check-in Date</label>
                        <input type="date" id="check_in_date" name="check_in_date" class="form-control" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="check_out_date">Check-out Date</label>
                        <input type="date" id="check_out_date" name="check_out_date" class="form-control" required>
                    </fieldset>
                    <!-- <fieldset class="form-group">
                        <label for="price">Price</label>
                        <input type="text" id="price" name="price" class="form-control" readonly>
                    </fieldset> -->
                    <button type="submit" class="btn btn-success">Reserve</button>
                </form>
            </div>
        </div>
    </div>
    <script>
        document.getElementById('category_id').addEventListener('change', function () {
            var selectedOption = this.options[this.selectedIndex];
            document.getElementById('price').value = selectedOption.getAttribute('data-price');
        });
    </script>
</body>
</html>
