/**
 * 
 */
let index = {
	
	init: function() {
		$("#btn-save").on("click", () => { //function(){}, ()=> {} this를 바인딩하기위해서!!
			this.save();
		});
			$("#btn-update").on("click", () => { //function(){}, ()=> {} this를 바인딩하기위해서!!
			this.update();
		});
	},

	save: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);
		
		// ajax호출시 default가 비동기 호출
		$.ajax({
        // 회원가입 수행 요청
	          type: "POST",
	          url: "/auth/joinProc",
              data: JSON.stringify(data), //http body데이터
              contentType: "application/json; charset=utf-8", // body데이터 type
			  dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 string
	                                       //생긴게 json이라면 javascript 오브젝트로 전환
		}).done (function(resp){
			alert("회원가입이 완료되었습니다.");
			location.href="/	";
		}).fail(function(error){
			alert(JSON.stringify(error));			
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
	},
	
	update : function() {
	   let data = {
	        id : $(id).val(),
	        username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};		
		// ajax호출시 default가 비동기 호출
		$.ajax({
	          type: "PUT",
	          url: "/user",
              data: JSON.stringify(data), //http body데이터
              contentType: "application/json; charset=utf-8", // body데이터 type
			  dataType: "json" //요청을 서버로해서 응답이 왔을때 기본적으로 string
	                                       //생긴게 json이라면 javascript 오브젝트로 전환
		}).done (function(resp){
			alert("회원정보 변경이 완료되었습니다.");
			location.href="/	";
		}).fail(function(error){
			alert(JSON.stringify(error));			
		});// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
     } 
  }
index.init();