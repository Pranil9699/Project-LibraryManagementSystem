<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Members</title>
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
	<div class="container mt-5 bg-white">
		<h1>Members</h1>
		<table class="table">
			<thead>
				<tr class="text-center rounded rounded-5">
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>ContactInformation</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ADMIN}">
					<c:forEach items="${member}" var="member">
						<tr class="text-center rounded rounded-5">
							<th>${member.id}</th>
							<td>${member.name}</td>
							<td>${member.email}</td>
							<td>${member.contactInformation}</td>
							<td class="text-center">
								<button type="button"
									class="text-center rounded rounded-5 border-0"
									style="border: none; padding: 0; background: none;"
									data-bs-toggle="modal"
									data-bs-target="#editMemberModal${member.id}">
									<img src="edit.png" alt="Edit" width="30" height="30"
										class="rounded-circle me-2">
								</button> <!-- Edit Member Modal -->
								<div class="modal fade" id="editMemberModal${member.id}"
									tabindex="-1"
									aria-labelledby="editMemberModalLabel${member.id}"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title"
													id="editMemberModalLabel${member.id}">Edit Member</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form action="MemberEdit" method="post"
													style="text-align: left;">
													<input type="hidden" name="memberId" value="${member.id}">
													<div class="mb-3">
														<label for="name" class="form-label">Name</label> <input
															type="text" class="form-control" id="name" name="name"
															value="${member.name}" required="required">
													</div>
													<div class="mb-3">
														<label for="email" class="form-label">Email</label> <input
															type="email" class="form-control" id="email" name="email"
															value="${member.email}" required="required">
													</div>
													<div class="mb-3">
														<label for="contactInformation" class="form-label">ContactInformation</label>
														<input type="text" class="form-control"
															id="contactInformation" name="contactInformation"
															value="${member.contactInformation}">
													</div>
													<div class="text-center">
													<button type="submit" class="btn btn-primary">Save
														changes</button>
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
														</div>
												</form>
											</div>
										</div>
									</div>
								</div> <!-- End Edit Member Modal --> <!-- Delete Member Modal -->

								<button type="button"
									class="text-center rounded rounded-5 border-0"
									style="border: none; padding: 0; background: none;"
									data-bs-toggle="modal"
									data-bs-target="#deleteMemberModal${member.id}">
									<img src="delete.png" alt="Delete" width="30" height="30"
										class="rounded-circle me-2">
								</button>

								<div class="modal fade" id="deleteMemberModal${member.id}"
									tabindex="-1"
									aria-labelledby="deleteMemberModalLabel${member.id}"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title"
													id="deleteMemberModalLabel${member.id}">Delete Member</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<p>Are you sure you want to delete ${member.name}?</p>
											</div>
											<div class="modal-footer">
												<form action="MemberEdit" method="get">
													<input type="hidden" name="memberId" value="${member.id}">
													<button type="submit" class="btn btn-danger">Delete</button>
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Cancel</button>
												</form>
											</div>
										</div>
									</div>
								</div> <!-- End Delete Member Modal -->
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="text-end mb-3 text-center">
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#addMemberModal">Add Member</button>
	</div>

	<!-- Add Member Modal -->
	<div class="modal fade" id="addMemberModal" tabindex="-1"
		aria-labelledby="addMemberModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addMemberModalLabel">Add Member</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="MembersServlet" method="post"
						style="text-align: left;">
						<div class="mb-3">
							<label for="name" class="form-label">Name</label> <input
								type="text" class="form-control" id="name" name="name"
								required="required">
						</div>
						<div class="mb-3">
							<label for="email" class="form-label">Email</label> <input
								type="email" class="form-control" id="email" name="email"
								required="required">
						</div>
						<div class="mb-3">
							<label for="contactInformation" class="form-label">Contact
								Information</label> <input type="text" class="form-control"
								id="contactInformation" name="contactInformation">
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-primary">Create
								Member</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cancel</button>
						</div>
					</form>
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
