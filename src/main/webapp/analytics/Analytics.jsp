<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
 <div>
        <h2>Analytics</h2>
        <div>
            <div>
                <h3>Total Customers</h3>
                <p> ${totalCustomers}</p>
            </div>
            <div>
                <h3>Total Bookings</h3>
                <p><%= request.getAttribute("totalBookings") %></p>
            </div>
            <div>
                <h3>Total Available Rooms</h3>
                <p><%= request.getAttribute("totalAvailableRooms") %></p>
            </div>
        </div>
        <canvas id="myChart"></canvas>
    </div>
    
    <script>
    var totalCustomers = ${totalCustomers != null ? totalCustomers : 0};
    var totalBookings = ${totalBookings != null ? totalBookings : 0};
    var totalAvailableRooms = ${totalAvailableRooms != null ? totalAvailableRooms : 0};
    
        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Total Customers', 'Total Bookings', 'Available Rooms'],
                datasets: [{
                    label: 'Statistics',
                    data: [<%= request.getAttribute("totalCustomers") %>, <%= request.getAttribute("totalBookings") %>, <%= request.getAttribute("totalAvailableRooms") %>],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(75, 192, 192, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(75, 192, 192, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>