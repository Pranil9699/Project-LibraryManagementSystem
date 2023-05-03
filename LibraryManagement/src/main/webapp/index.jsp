<%-- index.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Forward the request to your servlet
    String contextPath = request.getContextPath();
    response.sendRedirect(contextPath + "/LibraryManagement");
%>
