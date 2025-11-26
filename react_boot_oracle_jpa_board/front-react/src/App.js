import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Header from './common/Header';
import LoginForm from './pages/user/LoginForm';
import JoinForm from './pages/user/JoinForm';
import BoardList from './pages/board/BoardList';
import SaveForm from './pages/board/SaveForm';
import Detail from './pages/board/Detail';
import UpdateForm from './pages/board/UpdateForm';


function App() {
  return (
    <div className="App">
      <BrowserRouter>   {/* 링크 연동 */}
        <Header />
        <Routes>
          <Route path="/joinForm" exact={true} element={<JoinForm />} />    {/*Header.js에서 Link to 로 연결한 주소값과 맞춰줌.  -> 회원가입*/}
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
