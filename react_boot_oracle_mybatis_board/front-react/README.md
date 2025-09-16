# 플젝 생성
npx create-react-app 플젝명

# 실행 : (생성한 플젝으로 이동 후 ) npm start


# 리액트 플젝 하위 폴더 정리
#  public > index.html 외에 모두 지워도 됨
#  src    > App.css, App.test.js, index.css, logo.svg, reportWebVitals.js, setupTests.js 삭제


# Header.js 작성
# install
npm i react-bootstrap bootstrap
import { Link } from 'react-router-dom';            // npm i react-router-dom
import { Button, Container, Form, Nav, Navbar, NavDropdown } from 'react-bootstrap'; 
        // 구조분해 할당(1개는 먼저 만들어야 나머지가 자동완성)


# index.js
import 'bootstrap/dist/css/bootstrap.min.css';      // react bootstrap > Getting started        > css 복사해서 붙여넣기

# App.js 작성
    <BrowserRouter>
        <Header /> 추가
    </BrowserRouter>


# header 실행

# App.js > Header.js > App.js > 해당 컴포넌트로 이동 > index.html에서 렌더링



# npm i react-route-dom 설치
# App.js 작성
import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <BrowserRouter>   {/* 링크 연동 */}
        <Header />
        <Routes>
          <Route path="/joinForm" exact={true} element={<JKoinForm />} />    {/*Header.js에서 Link to 로 연결한 주소값과 맞춰줌.  -> 회원가입*/}
          <Route path="/loginForm" exact={true} element={<LoginForm />} />    {/* 로그인 */}
          <Route path="/boardList" exact={true} element={<BoardList />} />    {/* 리스트 */}
          <Route path="/saveForm" exact={true} element={<SaveForm />} />    {/* 글쓰기 */}
          <Route path="/board/:b_num" exact={true} element={<Detail />} />    {/* 상세 */}
          <Route path="/updateForm/:b_num" exact={true} element={<UpdateForm />} />    {/* 수정  -> 글번호를 넘김 */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

# App.js에서 연결할 각 js를 생성만 한다 (not defined error가 발생하므로)
# LoginForm 작성 예시       rsc-> enter
# 컴포넌트명 마지막 글자를 지우고 엔터치면 자동으로 import가 된다.
import React from 'react';

const LoginForm = () => {
    return (
        <div>
            <h3>로그인</h3>
        </div>
    );
};

export default LoginForm;



# ---------------------- 게시판 목록  ----------------------------------------


# 백엔드 Controller, Service, Mapper 작성 후
# 게시글 목록 boardList
백엔드 실행 (에러가 없어야 함).


# BoardList.js에서 결과를 받아 -> BoardItem.js 로 넘겨준다.
Controller에 Cors 추가 (@CrossOrigin) 
게시글목록 -> F12 -> blocked by CORS policy가 발생하면 -> Controller 클래스 맨 위  @CrossOrigin 어노테이션 추가 
                        => 컨트롤러 진입 직전에 동작
                        => 외부에서 자바스크립트 요청이 오는 것을 허용한다. F5해서 콘솔 다시 확인


# -------------------------------------------------------------
# --------------------- POSTMAN설치 및 백엔드 테스트 ----------------

# ------------------- [ 게시글등록 ] ---------------------

saveForm.js 작성 > 등록한 글이 게시글 목록에 추가되어 뿌려짐
Form import 꼭 주의하기! (react-bootstrap에서 가져와야함)


# -------------------- [ 게시글 상세 > 삭제 ] ---------------------------

Detail.js 작성 - 상세페이지 > 삭제버튼 클릭 > 삭제처리

# -------------------- [ 게시글 상세 > 수정 ] ---------------------------

Detail.js 작성 - 상세페이지 > 수정버튼 클릭 > 수정처리 페이지(UpdateForm.js) 이동
mapper에 writer 수정추가 ( 아니면 작성자는 수정이 안됨 ) 기존소스는 세션값을 읽어왔으므로 작성자를 수정할 수 없지만 지금은 그냥 테스트니깡~