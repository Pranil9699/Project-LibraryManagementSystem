<nav class="navbar navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<img src="Library.png" alt="Project Image" width="50" height="50"
				class="rounded-circle me-2"> <a class="navbar-brand" href="#"
				style="color: #0d47a1;">Library Management System</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="home.jsp" style="color: #0d47a1;">Home</a></li>
					 <c:if test="${not empty ADMIN}">
					<li class="nav-item"><a class="nav-link" href="BooksServlet"
						style="color: #0d47a1;">Books</a></li>
					<li class="nav-item"><a class="nav-link" href="MembersServlet"
						style="color: #0d47a1;">Members</a></li>
					<li class="nav-item"><a class="nav-link" href="BorrowsServlet"
						style="color: #0d47a1;">Borrowings</a></li>
					<li class="nav-item"><a class="nav-link" href="UserServlet"
						style="color: #0d47a1;">Profile</a>
						
						
						
						</li>
					<li class="nav-item"><a class="nav-link" href="AdminServlet"
						style="color: #0d47a1;">Logout</a></li>
						</c:if>
				</ul>
			</div>
		</div>
	</nav>
	