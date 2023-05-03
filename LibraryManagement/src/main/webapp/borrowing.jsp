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
	<div class="container mt-5 bg-white">
		<h1>Borrowings</h1>
		<table class="table">
			<thead>
				<tr class="text-center rounded rounded-5">
					<th>ID</th>
					<th>Book</th>
					<th>Member</th>
					<th>Borrow Date</th>
					<th>Expected Return Date</th>
					<th>Actions(Return Book/Delete)</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ADMIN}">
					<c:forEach items="${borrowedBooks}" var="borrowing">
						<tr class="text-center rounded rounded-5">
							<th>${borrowing.id}</th>
							<td>${borrowing.book.name}</td>
							<td>${borrowing.member.name}</td>
							<td>${borrowing.borrowDate}</td>
							<td>${borrowing.returnDate}</td>
							<td class="text-center">
								<button type="button"
									class="text-center rounded rounded-5 border-0"
									style="border: none; padding: 0; background: none;"
									data-bs-toggle="modal"
									data-bs-target="#editBorrowingModal${borrowing.id}">
									<img src="edit.png" alt="Edit" width="30" height="30"
										class="rounded-circle me-2">
								</button> <!-- Edit Borrowing Modal -->
								<div class="modal fade" id="editBorrowingModal${borrowing.id}"
									tabindex="-1"
									aria-labelledby="editBorrowingModalLabel${borrowing.id}"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title"
													id="editBorrowingModalLabel${borrowing.id}">Return
													Book</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form action="BorrowingEdit" method="post"
													style="text-align: left;">
													<input type="hidden" name="borrowingId"
														value="${borrowing.id}"> <input type="hidden"
														name="bookId" value="${borrowing.book.id}"> <input
														type="hidden" name="memberId"
														value="${borrowing.member.id}">
													<div class="mb-3">
														<label for="bookName" class="form-label">Book</label> <input
															type="text" class="form-control" id="bookName"
															name="bookName" value="${borrowing.book.name}" disabled>
													</div>
													<div class="mb-3">
														<label for="memberName" class="form-label">Member</label>
														<input type="text" class="form-control" id="memberName"
															name="memberName" value="${borrowing.member.name}"
															disabled>
													</div>
													<div class="mb-3">
														<label for="borrowDate" class="form-label">Borrow
															Date</label> <input type="date" class="form-control"
															id="borrowDate" name="borrowDate"
															value="${borrowing.borrowDate}" required disabled>
													</div>
													<div class="mb-3">
														<label for="returnDate" class="form-label">Return
															Date</label> <input type="date" class="form-control"
															id="returnDate" name="returnDate"
															value="${borrowing.returnDate}" disabled>
													</div>
													<div class="mb-3">
														<label for="returned" class="form-label">Returned</label>
														<select class="form-control" id="returned" name="returned">
															<option value="false" selected="selected">No</option>
															<option value="true">Yes</option>
														</select>
													</div>
													<div class="modal-footer">
														<button type="submit" class="btn btn-primary">Save
															changes</button>
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Close</button>

													</div>
												</form>
											</div>
										</div>
									</div>
								</div> <!-- End Edit Borrow Modal -->
								<button type="button" class="text-center rounded rounded-5"
									style="border: none; padding: 0; background: none;"
									data-bs-toggle="modal"
									data-bs-target="#deleteBorrowingModal${borrowing.id}">
									<img src="delete.png" alt="Delete" width="30" height="30"
										class="rounded-circle me-2">
								</button> <%-- <button type="button" class="text-center rounded rounded-5"
									data-bs-toggle="modal"
									data-bs-target="#deleteBorrowingModal${borrowing.id}">
									<img src="delete.png" alt="Delete" width="30" height="30"
										class="rounded-circle me-2">
								</button> --%> <!-- Delete Borrow Modal -->
								<div class="modal fade" id="deleteBorrowingModal${borrowing.id}"
									tabindex="-1"
									aria-labelledby="deleteBorrowingModalLabel${borrowing.id}"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title"
													id="deleteBorrowingModalLabel${borrowing.id}">Delete
													Borrowing</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<p>Are you sure you want to delete this borrowing?</p>
											</div>
											<div class="modal-footer">
												<form action="BorrowingEdit" method="get">
													<input type="hidden" name="borrowingId"
														value="${borrowing.id}"> <input type="hidden"
														name="bookId" value="${borrowing.book.id}">
													<button type="submit" class="btn btn-danger">Delete</button>
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Cancel</button>

												</form>
											</div>
										</div>
									</div>
								</div> <!-- End Delete Borrow Modal -->
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>


	<!-- End Container -->
	<!-- Button trigger modal -->

	<div class="text-end mb-3 text-center">
		<button type="button" class="btn btn-primary " data-bs-toggle="modal"
			data-bs-target="#borrowBookModal">Borrow Book</button>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="borrowBookModal" tabindex="-1"
		aria-labelledby="borrowBookModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="borrowBookModalLabel">Borrow Book</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="BorrowsServlet" method="post">
						<div class="mb-3">
							<label for="bookId" class="form-label">Book</label> <select
								class="form-select" id="bookId" name="bookId" required>
								<option value="">Select a book</option>
								<c:forEach items="${booksY}" var="book">
									<option value="<c:out value="${book.id }" />"><c:out
											value="${book.id }" />-
										<c:out value="${book.name }" /></option>
								</c:forEach>
							</select>
						</div>
						<div class="mb-3">
							<label for="userId" class="form-label">User ID</label> <select
								class="form-select" id="userId" name="userId" required>
								<option value="">Select a User ID</option>
								<c:forEach items="${members}" var="member">
									<option value="${member.id}">${member.id}-
										${member.name}</option>
								</c:forEach>
							</select>
						</div>

						<div class="modal-footer text-center">

							<button type="submit" class="btn btn-primary">Borrow
								Book</button>
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
