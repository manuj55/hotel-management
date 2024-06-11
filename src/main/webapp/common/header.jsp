<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
			<div>
				<a href="http://localhost:8080/HotelManagement/" class="navbar-brand"> Hotel
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Employees</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/available"
					class="nav-link">Room search</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/assign-room"
					class="nav-link">Employee assign to room</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/AdminParking"
					class="nav-link">Assign Parking</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/PriceForm"
					class="nav-link">Dynamic Pricing</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/Analytics"
					class="nav-link">Analytics</a></li>
			</ul>

		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
			<li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
		</ul>
		
	</nav>
</header>