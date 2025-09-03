/* 
* modify.js
 */


// onsubmit 시
function signInCheck(){
	
}

// 회원가입 또는 이메일수정 셀렉박스 값 변경시 
function selectEmailChk(){
	if(document.modifyform.user_email3.value == "0"){
		document.modifyform.user_email2.value = "";
		document.modifyform.user_email2.focus();
		return false;
	}else{
		document.modifyform.user_email2.value = document.modifyform.user_email3.value;
		return false;
	}
	
	
}

// 2. join.jsp - onSubmit 시 회원가입 페이지 필수 체크
function modifyCheck(){
	// 비밀번호 불일치
	if(document.modifyform.user_password.value == document.modifyform.re_password.value){
		alert("비번일치");
	}
	else {
		alert("비밀번호 확인이 일치하지 않습니다.");
		document.modifyform.user_password.value="";
		document.modifyform.user_password.focus();
		return false;
	}

}



