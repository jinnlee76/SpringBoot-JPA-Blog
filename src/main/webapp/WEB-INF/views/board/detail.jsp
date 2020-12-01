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
		
		<div>
			<div class="card">
			             <form>
			             <input type="hidden" id="userId" value="${principal.user.id }"/>
			             <input type="hidden"  id="boardId" value="${boards.id }"/>			         
						<div class="card-body"><textarea id="reply-content" class="form-control" row="1"></textarea></div>
						<div class="card-footer"><button type=button" id="btn-reply-save" class="btn btn-primary">등록</button></div>
                         </form>			
			</div>
		</div>
		<br/>
		
		
	<div class="card">
			<div class="card-footer">댓글 리스트</div>
			<ul id="reply--box" class="list-group">
			 	<c:forEach var="reply"  items="${boards.reply}">
				<li id="reply--1" class="list-group-item  d-flex justify-content-between">
					<div>${reply.content }</div>
					<div class="d-flex">
						<div class="font-italic"> 작성자 : ${reply.user.username } &nbsp;</div>
						<button class="badge">삭제</button>
					</div>
				</li>
				</c:forEach>
			
			</ul>
		</div>
	

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
	
	



