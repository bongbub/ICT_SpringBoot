/**
 * New > Javascript File > main.js 
 * (3-3). 자바스크립트에 이벤트 추가 : 햄버거 아이콘을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에 작성
 */

const toggleBtn = document.querySelector(".navbar_toggleBtn");	// 햄버거 버튼
const menu = document.querySelector(".navbar_menu");
const icon = document.querySelector(".navbar_icons");


// ()=>{...}	: arrow function -> 익명함수 function(){ ... } 에서 function까지 생략
toggleBtn.addEventListener('click', () => {	// 햄버거 버튼 클릭시
	menu.classList.toggle('active');	// 메뉴 활성화
	icon.classList.toggle('active');	// 아이콘 활성화
});

