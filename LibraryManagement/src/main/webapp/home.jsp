<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Library Management System -Home Page</title>
<!-- Bootstrap 5 CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">

<!-- Bootstrap JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">

<!-- JavaScript (requires jQuery) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" type="text/css" href="MyStyle.css">
</head>


<body>
	<c:if test="${not empty msg}">
		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
			aria-labelledby="messageModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="messageModalLabel">Massage:</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>
							<c:out value="${msg}" />
						</p>
					</div>
				</div>
			</div>
		</div>

		<script>
			$(document).ready(function() {
				$('#messageModal').modal('show');
				setTimeout(function() {
					$('#messageModal').modal('hide');
				}, 5000);
			});
		</script>
	</c:if>
	<%@ include file="navbar.jsp"%>


	<c:if test="${not empty ADMIN}">
	
		<div class="container mt-5 ">
			<div class="row">
				<div class="col-md-12 bg-light rounded">
					<h1 class="display-6">Welcome to our Library Management System</h1>
					<p>We are committed to providing you with the best reading
						experience. Our collection of books is constantly updated to keep
						up with the latest releases and bestsellers. Whether you are a
						student, a professional or just an avid reader, we have something
						for you.</p>
					<p>Our mission is to promote literacy and lifelong learning by
						providing access to high-quality reading materials. We believe
						that books can change lives and inspire individuals to achieve
						their full potential.</p>
					<p>
						<strong>Library Charges:</strong> Please note that a late fee of
						RS.2 per day is applicable for overdue books. Members are also
						required to pay for any lost or damaged books.
					</p>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${ empty ADMIN}">

		<div class="container mt-5">
			<div class="row">
				<div class="col-md-8 bg-light rounded">
					<h1 class="display-6">Welcome to our Library Management System</h1>
					<p>We are committed to providing you with the best reading
						experience. Our collection of books is constantly updated to keep
						up with the latest releases and bestsellers. Whether you are a
						student, a professional or just an avid reader, we have something
						for you.</p>
					<p>Our mission is to promote literacy and lifelong learning by
						providing access to high-quality reading materials. We believe
						that books can change lives and inspire individuals to achieve
						their full potential.</p>
					<p>
						<strong>Library Charges:</strong> Please note that a late fee of
						$0.50 per day is applicable for overdue books. Members are also
						required to pay for any lost or damaged books.
					</p>
				</div>

				<div class="col-md-4">
					<div class="card"
						style="background-color: #ffffff; border-radius: 10px;">
						<div class="card-header"
							style="background-color: #ff4d4d; color: #ffffff; border-radius: 10px 10px 0 0;">
							<h3 class="text-center">ADMIN Login</h3>
						</div>
						<div class="card-body" style="background-color: #f0f0f0;">
							<form action="AdminServlet" method="POST">
								<div class="mb-3">
									<label for="username" class="form-label">UserName</label> <input type="text" class="form-control"
										id="username" name="username" required
										style="border-color: #ff4d4d;">
								</div>
								<div class="mb-3">
									<label for="password" class="form-label">Password</label> <input
										type="password" class="form-control" id="password"
										name="password" required autocomplete="current-password"
										style="border-color: #ff4d4d;">

								</div>
								<div class="text-center">
									<button type="submit" class="btn btn-primary"
										style="background-color: #ff4d4d; border-color: #ff4d4d;">Login</button>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</c:if>




	<%@ include file="footer.jsp"%>
</body>

</html>