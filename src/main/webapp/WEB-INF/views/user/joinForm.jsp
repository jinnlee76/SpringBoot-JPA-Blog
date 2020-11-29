<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Header -->
<%@ include file="../layout/header.jsp" %>


<!-- Body -->
<div class="container">
	<!--  	<form action="/user/join" method ="POST"> -->
	 <form action="">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text"
				class="form-control" placeholder="Enter Username" id="username">
		</div>
		
		<div class="form-group">
			<label for="email">Email:</label> <input type="email"
				class="form-control" placeholder="Enter email address" id="email">
		</div>
		
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				class="form-control" placeholder="Enter password" id="password">
		</div>
		
		
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입</button>
	
</div>

<script src="/js/user.js"></script>	
<!--  footer -->
<%@ include file="../layout/footer.jsp" %>
	
	



