<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Header -->
<%@ include file="../layout/header.jsp" %>


<!-- Body -->
<div class="container">
	<form>
		
		<input type="hidden"  id="id" value="${boards.id}"/>
		 		
		<div class="form-group">
			<input value="${boards.title}" type="text" class="form-control" placeholder="Enter 타이틀" id="title">
		</div>		
		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content">${boards.content}</textarea>
		</div>
		
	</form>
	<button id="btn-update" class="btn btn-primary">글수정완료</button>

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
	
	



