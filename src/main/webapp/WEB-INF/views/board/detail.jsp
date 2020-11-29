<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Header -->
<%@ include file="../layout/header.jsp" %>


<!-- Body -->
<div class="container">

        <button class = " btn btn-secondary" onclick="history.back()">돌아가기</button>
        <c:if  test="${boards.user.id==principal.user.id}">
            <a href="/board/${boards.id}/updateForm"  class = " btn btn-warning">수정</a>
        	<button id="btn-delete" class = " btn btn-danger">삭제</button>
        </c:if>
        <br/>
        
        <div>
           글 번호 : <span id="id"><i>${boards.id }</i></span>
           작성자 : <span><i>${boards.user.username }</i></span>
        </div>
        
        
		<div class="form-group">
			<h3>${boards.title}</h3>
		</div>
		<hr/>
		<div class="form-group">
			<div>${boards.content }</div>
		</div>
		<hr/>	
	

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
	
	



