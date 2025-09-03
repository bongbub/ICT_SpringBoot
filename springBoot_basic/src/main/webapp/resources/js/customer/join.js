/* 
* join.js
 */


// onsubmit 시
function signInCheck(){
	
}

// ID 중복 확인 버튼 클릭 시
function confirmId(){
	// alert("중복확인");
	if(!document.inputform.user_id.value){
		alert("id를 입력하세요 ");
		document.inputform.user_id.focus();
		return false;
	}
	
	// 중복확인 버튼 클릭시 컨트롤러로 url을 전달, 컨트롤러에 소스 추가
	let url = "/spring_pj_ict05/idConfirmAction.do?user_id=" + document.inputform.user_id.value;
	window.open(url, "confirm", "menubar=no, width=500, height=400");	// 해당 url을 들고 창을 띄워라
	
}

// 회원가입 또는 이메일수정 셀렉박스 값 변경시 
function selectEmailChk(){
	if(document.inputform.user_email3.value == "0"){
		document.inputform.user_email2.value = "";
		document.inputform.user_email2.focus();
		return false;
	}else{
		document.inputform.user_email2.value = document.inputform.user_email3.value;
		return false;
	}
}

// 2. join.jsp - onSubmit 시 회원가입 페이지 필수 체크
function signInCheck(){
	/* 2-1. 중복확인 - 중복확인 버튼체크=> 안 눌렀을 때 0으로 설정 누르면 1로 변경 */
	/* <input type="hidden" name="hiddenUserId" value="0"> */
	
	// 2-2. hiddenUserid : 중복확인 버튼 안 눌렀을 때 체크(0:클릭안함, 1:클릭함)
	// 중복확인 버튼 클릭 안 했을 때 -> "중복확인 버튼 클릭해주세요!" 출력
	if(document.inputform.hiddenUserId.value == "0"){
			alert("중복확인 버튼 클릭해주세요!");
			document.inputform.dubChk.focus();
			return false;
	}
	
	// 비밀번호 불일치 => 과제
	if(document.inputform.user_password.value != document.inputform.re_password.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.inputform.user_password.focus();
		return false;
	}
}


// 3. 사용가능한 ID를 찾은 경우 => 자식창에서 부모창으로 user_id 값을 전달
/*
	opener : window 객체의 open() 메서드로,
			자식창(=중복확인창)에서 부모창(=회원가입창)에 접근
	hiddenUserid : 중복확인 버튼. 안눌렀을 때 체크(0:클릭안함, 1:클릭함);
	self.close() : 자식창 닫기
*/

function setUserid(userid){
	//alert(userid);
	opener.document.inputform.user_id.value = userid;	// 부모 user_id 값에 전달받은 user_id값 전달
	// hiddenUserId 1로 바꿔주기 -> 다시 반복 안 하게
	opener.document.inputform.hiddenUserId.value = "1";	// 중복확인창 클릭 인증
	
	self.close();  	// 본인 창 닫기
	
}