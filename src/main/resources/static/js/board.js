/**
 * 
 */
let index = {
	
	init: function() {
		$("#btn-board-save").on("click", () => { //function(){}, ()=> {} this를 바인딩하기위해서!!
			this.save();
		});
		 $("#btn-update").on("click", () => { //function(){}, ()=> {} this를 바인딩하기위해서!!
			this.update();
		});
		
	     $("#btn-delete").on("click", () => { //function(){}, ()=> {} this를 바인딩하기위해서!!
			this.deleteById();
		});
		
		   $("#btn-reply-save").on("click", () => { //function(){}, ()=> {} this를 바인딩하기위해서!!
			this.replySave();
		});
	},

	save: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		//console.log(data);
		
		// ajax호출시 default가 비동기 호출
		$.ajax({
        // 회원가입 수행 요청
	          type: "POST",
	          url: "/api/board",
              data: JSON.stringify(data), //http body데이터
              contentType: "application/json; charset=utf-8", // body데이터 type
			  dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 string
	                                       //생긴게 json이라면 javascript 오브젝트로 전환
		}).done (function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/	";
		}).fail(function(error){
			alert(JSON.stringify(error));			
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
	},
	
	update: function() {
		let id=$("#id").val();
		      
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
	    console.log(id);
		console.log(data);
		// ajax호출시 default가 비동기 호출
		$.ajax({
	          type: "PUT",
	          url: "/api/board/"+id,
              data: JSON.stringify(data), //http body데이터
              contentType: "application/json; charset=utf-8", // body데이터 type
			  dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 string
	                                       //생긴게 json이라면 javascript 오브젝트로 전환
		}).done (function(resp){
			alert("수정이 완료되었습니다.");
			location.href="/	";
		}).fail(function(error){
			alert(JSON.stringify(error));			
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
	},
	
	deleteById: function() {
	    let id =$("#id").text();
		// ajax호출시 default가 비동기 호출
		$.ajax({
	          type: "DELETE",
	          url: "/api/board/"+id,
			  dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 string
	                                       //생긴게 json이라면 javascript 오브젝트로 전환
		}).done (function(resp){
			alert("삭제가 완료되었습니다.");
			location.href="/	";
		}).fail(function(error){
			alert(JSON.stringify(error));			
		});// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
	},	 
	
	replySave: function(){ 
		let data = {			 
			userId: $("#userId").val(),
			boardId = $("#boardId"),
			content: $("#reply-content").val()
		};
		alert( `/api/board/${data.boardId}/reply`);
		
		// ajax호출시 default가 비동기 호출
	$.ajax({
	          type: "POST",
	          url: `/api/board/${data.boardId}/reply`,
              data: JSON.stringify(data), //http body데이터
              contentType: "application/json; charset=utf-8", // body데이터 type
			  dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 string
	                                       //생긴게 json이라면 javascript 오브젝트로 전환
		}).done (function(resp){
			alert("댓글작성이 완료되었습니다.");
			location.href=`/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));			
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
	}
}
index.init();