import React, { Component } from 'react';
import WelcomContent from './WelcomContent';
import LoginForm from './LoginForm';
import Buttons from './Buttons';
import { request, setAuthToken } from '../helpers/axios_helper';

class AppContent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: 'welcome'
        }
    };


    // Buttons 호출시, props 구현
    // 함수 정의 (Buttons 클릭했을 때 함수 내 정보를 들고가라)
    login = () => {
        this.setState({ componentToShow: "login" })
    }
    // 함수 정의 (Buttons 클릭했을 때 함수 내 정보를 들고가라+토큰제거)
    logout = () => {
        this.setState({ componentToShow: "welcome" })
        // 로그아웃을 하는 순간 토큰값을 제거
        setAuthToken(null);     // 토큰설정하는 메서드 -> null을 넣어줌으로써 토큰값을 없앰.
    }



    // LoginForm 호출시 props 구현  => AuthToken값들을 넘겨주기 위해
    onLogin = (e, id, password) => {        // 이벤트, id, password 모두 넘김
        e.preventDefault();
        // 요청처리
        request(
            "POST",
            "/login",
            {
                id: id,
                password: password
            }
        )
            .then((res) => {        // 콜백함수 -> res에 로그인 결과가 들어감
                this.setState({ componentToShow: 'message' });
                setAuthToken(res.data.token);       // 로그인 성공시 스프링 부트에서 생성한 토큰을 저장후, 다른 메뉴 클릭시 토큰을 들고가서 인증받는다
            })
            .catch((error) => {
                this.setState({ componentToShow: 'welcome' });
                setAuthToken(null);
            })
    }

    onRegister = (e, id, password, first_name, last_name) => {        // 이벤트, id, password 모두 넘김
        e.preventDefault();
        request(
            "POST",
            "/register",
            {
                id: id,
                password: password,
                first_name: first_name,
                last_name: last_name
            }
        ).then((res) => {           // 콜백함수 -> res에 회원가입 결과가 들어감
            this.setState({ componentToShow: 'message' });
            // res의 data는 axios_helper.js의 request에서받아옴
            alert('회원가입이 완료되었습니다. 로그인해주세요.');
            this.setState({ componentToShow: 'login' });
        })
            .catch((error) => {
                this.setState({ componentToShow: 'welcome' });
                setAuthToken(null);
            })
    }


    render() {
        return (
            <div>
                {/* Buttons 호출, props 전달, 위에 함수 정의 */}
                <Buttons login={this.login}
                    logout={this.logout} />

                {/* this.state.compoenetToShow값이 welcome일 때만 작동 */}
                {this.state.componentToShow === 'welcome' && <WelcomContent />}

                {/* this.state.componentToShow값이 login일 때만 작동 */}
                {/* LoginForm 호출시, onLogin과 onRegister라는 props를 전달 */}
                {this.state.componentToShow === 'login' && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />}
            </div>
        )
    }
}

export default AppContent;