<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Books</title>
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

	<div class="container mt-3 p-3">
		<div class="row">
			<div class="col-md-6 mx-auto">
				<div class="card bg-light rounded rounded-5">
					<div class="card-header bg-primary text-white text-center">
						<h4>ADMIN Form</h4>
					</div>
					<div class="card-body">
						<form action="UserServlet" method="POST">
							<div class="mb-3">
							<input type="hidden" name="userId" type="text" value="${user.id}">
								<label for="username" class="form-label">Username</label> <input
									type="text" class="form-control" id="username" name="username"
									value="${user.username}" required="required">
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password</label> <input
									type="password" class="form-control" id="password"
									name="password" value="${user.password}" required="required">
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" id="email" name="email" required="required"
									value="${user.email}">
							</div>
							<div class="p-1 text-center">
								<button type="submit" class="btn btn-primary">Save</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
	<!-- Footer  -->
	<%@ include file="footer.jsp"%>
</body>
</html>
