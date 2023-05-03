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
		<h1>Available Books</h1>
		<table class="table">
			<thead>
				<tr class="text-center rounded rounded-5">
					<th>ID</th>
					<th>BookName</th>
					<th>Author</th>
					<th>ISBN</th>
					<th>Publisher</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ADMIN}">
					<c:forEach items="${book }" var="book">
						<tr class="text-center rounded rounded-5">
							<th>${ book.id}</th>
							<td>${ book.name}</td>
							<td>${ book.author}</td>
							<td>${ book.isbn}</td>
							<td>${ book.publisher}</td>
							<td class="text-center">
								<button type="button"
									class="text-center rounded rounded-5 border-0"
									style="border: none; padding: 0; background: none;"
									data-bs-toggle="modal"
									data-bs-target="#editBookModal${book.id}">
									<img src="edit.png" alt="Project Image" width="30" height="30"
										class="rounded-circle me-2">
								</button> <!-- Edit Book Modal -->
								<div class="modal fade" id="editBookModal${ book.id}"
									tabindex="-1" aria-labelledby="editBookModalLabel${ book.id}"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="editBookModalLabel${ book.id}">Edit
													Book</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form action="BookEdit" method="post"
													style="text-align: left;">
													<input type="hidden" name="bookId" value="${ book.id}">
													<div class="mb-3">
														<label for="title" class="form-label">Name</label> <input
															type="text" class="form-control" id="title" name="title"
															value="${ book.name}" required="required">
													</div>
													<div class="mb-3">
														<label for="author" class="form-label">Author</label> <input
															type="text" class="form-control" id="author"
															name="author" value="${ book.author}">
													</div>
													<div class="mb-3">
														<label for="isbn" class="form-label">ISBN</label> <input
															type="text" class="form-control" id="isbn" name="isbn"
															value="${ book.isbn}">
													</div>
													<div class="mb-3">
														<label for="publisher" class="form-label">Publisher</label>
														<input type="text" class="form-control" id="publisher"
															name="publisher" value="${ book.publisher}">
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
								</div> <!-- End Edit Book Modal --> <!-- Delete Book Modal -->



								<button type="button"
									class="text-center rounded rounded-5 border-0"
									style="border: none; padding: 0; background: none;"
									data-bs-toggle="modal"
									data-bs-target="#deleteBookModal${book.id}">
									<img src="delete.png" alt="Project Image" width="30"
										height="30" class="rounded-circle me-2">
								</button>
								<div class="modal fade" id="deleteBookModal${book.id }"
									tabindex="-1" aria-labelledby="deleteBookModalLabel${book.id }"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="deleteBookModalLabel${book.id }">Delete
													Book</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<p>Are you sure you want to delete this book?</p>
											</div>
											<div class="modal-footer">
												<form action="BookEdit" method="get">
													<input type="hidden" name="bookId" value="${book.id }">
													<button type="submit" class="btn btn-danger">Delete</button>
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Cancel</button>
												</form>
											</div>
										</div>
									</div>
								</div> <!-- End Delete Book Modal -->
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="text-end mb-3 text-center">
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#addBookModal">Add Book</button>
	</div>

	<!-- Add Book Modal -->
	<div class="modal fade" id="addBookModal" tabindex="-1"
		aria-labelledby="addBookModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addBookModalLabel">Add Book</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="BooksServlet" method="post" style="text-align: left;">
						<div class="mb-3">
							<label for="name" class="form-label">Name</label> <input
								type="text" class="form-control" id="name" name="name"
								required="required">
						</div>
						<div class="mb-3">
							<label for="author" class="form-label">Author</label> <input
								type="text" class="form-control" id="author" name="author">
						</div>
						<div class="mb-3">
							<label for="publisher" class="form-label">Publisher</label> <input
								type="text" class="form-control" id="publisher" name="publisher">
						</div>
						<div class="mb-3">
							<label for="isbn" class="form-label">ISBN</label> <input
								type="text" class="form-control" id="isbn" name="isbn">
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-primary">Create
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
