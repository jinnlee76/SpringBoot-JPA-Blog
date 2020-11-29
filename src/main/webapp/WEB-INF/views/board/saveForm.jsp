<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Header -->
<%@ include file="../layout/header.jsp" %>


<!-- Body -->
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title</label> 
			<input type="text" class="form-control" placeholder="Enter 타이틀" id="title">
		</div>
		
		<div class="form-group">
			<label for="Content">Content:</label> 
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
		
	
	</form>
	<button id="btn-board-save" class="btn btn-primary">글쓰기완료</button>

</div>
 <script>
      $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board.js"></script>

	<!--  footer -->
<%@ include file="../layout/footer.jsp" %>
	
	



