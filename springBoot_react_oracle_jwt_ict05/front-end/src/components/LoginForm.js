import React, { Component } from 'react';
import classNames from 'classnames';

// 함수가 아닌, 클래스로 생성
//      --> Component를 상속받는다
class LoginForm extends Component {
    constructor(props) {
        super(props);           // 부모에게 전달
        this.state = {          // state 만들어주고
            active: "login",     // "login" 전달
            id: "",                // dto 생성하는 것임
            password: "",
            first_name: "",
            last_name: "",

            // AppContent.js에서 호출시, LoginForm 호출시, props로 전달.
            onLogin: props.onLogin,      // props.onLogin을 onLogin 안에 할당       // 사용자가 자격증명을 보낸 후 상위구성요소가 로그인 양식을 숨길 수  있다. 
            onRegister: props.onRegister     // 마찬가지
        };
    }

    // 필드 업데이트 값을 state에 저장하는 메서드
    onChangeHandler = (e) => {
        // 현재 이벤트에 들어온(target)의 name을 name에 넣어줌
        let name = e.target.name;
        let value = e.target.value;         // 값을 넣어줌

        this.setState({ [name]: value });     //  네임에 value값을 넘겨라 -> 들어온 갯수만큼 한 번에 넘어감
        // 위에 선언된 this.state에 name(id, password, first_name, first_name)과 value("","","","")에 각각 알맞게 들어감
    }

    // 로그인 처리
    onSubmitLogin = (e) => {
        console.log("<<< onSubmitLogin >>>");
        // submit 함수가 클릭되었을 때 onLogin 함수 처리
        this.props.onLogin(e, this.state.id, this.state.password);  // 이벤트시점에, id와 password를 넘김
    }


    // 등록 처리
    onSubmitRegister = (e) => {
        console.log("<<< onSubmitRegister >>> ");
        this.props.onRegister(
            e,
            this.state.id,
            this.state.password,
            this.state.first_name,
            this.state.last_name);    // 각 id, password, fistName, lastName을 넘겨줌

    }

    // 여기서 그리기 시작
    render() {
        return (
            <div className="row justify-content-center">
                <div className="col-4">
                    <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">

                        {/* login 버튼 */}
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "login" ? "active" : "")}
                                id="tab-login" onClick={() => this.setState({ active: "login" })}>Login</button>
                        </li>

                        {/* Register 버튼 */}
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "register" ? "active" : "")}
                                id="tab-register" onClick={() => this.setState({ active: "register" })}>Register</button>
                        </li>
                    </ul>
                </div>

                <div className="tab-content">

                    {/* 로그인 폼 */}
                    <div className={classNames("tab-pane", "fade", this.state.active === "login" ? "show active" : "")} id="pills-login">

                        <form onSubmit={this.onSubmitLogin}>
                            <div className="form-outline mb-4">
                                <input type="text" id="loginId" name="id" className="form-control" onChange={this.onChangeHandler} />
                                <label className="form-label" htmlFor="loginId">ID</label>
                            </div>

                            <div className="form-outline mb-4">
                                <input type="password" id="loginPassword" name="password" className="form-control" onChange={this.onChangeHandler} />
                                <label className="form-label" htmlFor="loginPassword">Password</label>
                            </div>

                            <button type="submit" className="btn btn-primary btn-block mb-4">Sign in</button>
                        </form>
                    </div>


                    {/* 등록 폼 */}
                    <div className={classNames("tab-pane", "fade", this.state.active === "register" ? "show active" : "")} id="pills-register">

                        <form onSubmit={this.onSubmitRegister}>
                            <div className="form-outline mb-4">
                                <input type="text" id="registerId" name="id" className="form-control" onChange={this.onChangeHandler} />
                                <label className="form-label" htmlFor="registerId">ID</label>
                            </div>

                            <div className="form-outline mb-4">
                                <input type="password" id="registerPassword" name="password" className="form-control" onChange={this.onChangeHandler} />
                                <label className="form-label" htmlFor="registerPassword">Password</label>
                            </div>

                            <div className="form-outline mb-4">
                                <input type="text" id="first_name" name="first_name" className="form-control" onChange={this.onChangeHandler} />
                                <label className="form-label" htmlFor="first_name">First Name</label>
                            </div>

                            <div className="form-outline mb-4">
                                <input type="text" id="last_name" name="last_name" className="form-control" onChange={this.onChangeHandler} />
                                <label className="form-label" htmlFor="last_name">Last Name</label>
                            </div>

                            <button type="submit" className="btn btn-primary btn-block mb-4">Sign up</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

export default LoginForm;