# npx create-react-app front-end
# cd front-end
# npm start

# npm i classnames --save      #  package.json에 "classnames": "^2.5.1", 추가
# npm install  bootstrap    # package.json에  최신버전의 "bootstrap": "^5.3.3", 추가됨
# => npm install -f bootstrap@^5.2.3
 
# public > favicon.ico, index.html 제외한 나머지 4개파일 삭제  => 차후

# src > App.css, App.js, index.js 제외한 나머지 5개파일 삭제 => 차후

# index.js bootstrap.min.css 추가 및 수정



import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';  // 모든 플젝에 적용하므로 누락주의

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <App />  
);


# App.js에서 지웠던 logo.svg 수정

function App() {
  return (
    <div className="App">


    </div>
  );
}

export default App;

# npm start    


# ------------[ Header.js 작성 ]---------------------------

1) src > components 폴더 생성 > Header.js 파일 생성 후 rsc+Enter

2) App.js 추가

# ------------[ AppContent.js 작성 ]---------------------------


1) src > components > AppContent.js 파일 생성 후 rsc + Enter
 - 로그인

 - 회원가입

2) App.js 추가 

# ------------[ index.html 수정 ]---------------------------
  <!-- <link rel="apple-touch-icon" href="%PUBLIC_URL%/logo192.png" />

  <link rel="manifest" href="%PUBLIC_URL%/manifest.json" /> -->
  위 두 줄 주석처리 하기 


# ------------[ WelcomeContent.js 작성 ]---------------------------
import React from 'react';

const WelcomContent = () => {
    return (
        <div className="row justy-content-md-center">
            <div className='jumbotrom jumbotron-fluid'>
                <div className='container'>
                    <h1 className='display-4'>Welcome</h1>
                    <p className='lead'>login to see protected content</p>'
                </div>
            </div>
        </div>
    );
};

export default WelcomContent;

2) 후 AppContent.js에 추가

3) AppContents.js에서 WelcomeContent.js 호출


# ------------[ App.css 수정 ]---------------------------
.App-logo {
  animation: App-logo-spin infinite 20s linear;
  height: 80px;
}

.App-header {
  background-color: #222;
  height: 150px;
  padding: 20px;
  color: white;
  text-align: center;
}

.App-title {
  font-size: 1.5em;
}

.App-intro {
  font-size: large;
}

@keyframes App-logo-spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}


# ------------[ axios install ]---------------------------

npm install axios
 --> package.json에 추가됨(1.12.2)

# ------------[ axios_helper.js 생성 ]---------------------------
src > helpers 폴더 생성 > axios_helper.js 생성

import React from 'react';
import axios from 'axios';

// axios_helper 역할 : 
// ==> backend와 통신
// ==> 로그인 완료시, JWT를 저장.

axios.defaults.baseURL = 'http://localhost:8081'        // 백엔드의 ip 주소 연결
axios.defaults.headers.post["Content-Type"] = 'application/json'

// 로그인 성공시 토큰값 들고가기
export const getAuthToken = () => {
    return window.localStorage.getItem("auth_token");      // key값 틀리지 않게 주의
}

export const setAuthToken = (token) => {
    window.localStorage.setItem("auth_token", token);     
}

export const request = (method, url, data) => {     // export를 한 번에 선언해서, 아래에 export default문을 안 써도 되게 함
    return axios({
        method: method,
        url: url,
        data: data
    })
}



# ------------[ LoginForm.js 생성 ]---------------------------
1) src > components > LoginForm.js 생성

2) 기존(rsc)으로 만들던 함수방식이 아닌, 클래스방식으로 작성
import React, { Component } from 'react';

// 함수가 아닌, 클래스로 생성
//      --> Component를 상속받는다
class LoginForm extends Component {
    constructor(props) {
        super(props);           // 부모에게 전달
        this.state = {          // state 만들어주고
            active: "login",     // "login" 전달
            id: "",                // dto 생성하는 것임
            password: "",
            firstName: "",
            lastName: "",
        };
    }

    // 여기서 그리기 시작
    render() {
        return (
            <div>


            </div>
        )
    }
}

export default LoginForm;

3) AppContent.js에 LoginForm.js 추가


4) render() 그리는 코드 작성
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
                </div>
            </div>
        )
    }


5) 위에 작성한 버튼에 호출된 함수를 정의하기
(   render(){ ... } 위에 작성   )


    // 필드 업데이트 값을 state에 저장하는 메서드
    onChangeHandler = (e) => {
        // 현재 이벤트에 들어온(target)의 name을 name에 넣어줌
        let name = e.target.name;
        let value = e.target.value;         // 값을 넣어줌

        this.setState({ [name]: value });     //  네임에 value값을 넘겨라 -> 들어온 갯수만큼 한 번에 넘어감
        // 위에 선언된 this.state에 name(id, password, firstName, lastName)과 value("","","","")에 각각 알맞게 들어감
    }

    // 로그인 처리
    onSubmitLogin = (e) => {
        console.log("<<< onSubmitLogin >>>")
        // submit 함수가 클릭되었을 때 onLogin 함수 처리
        this.state.onLogin(e, this.state.id, this.state.password)  // 이벤트시점에, id와 password를 넘김
    }

    ...this.state = {          // state 만들어주고
            active: "login",     // "login" 전달
            id: "",                // dto 생성하는 것임
            password: "",
            firstName: "",
            lastName: "",
            onLogin: props.onLogin,      // props.onLogin을 onLogin 안에 할당       // 사용자가 자격증명을 보낸 후 상위구성요소가 로그인 양식을 숨길 수  있다. 
            onRegister: props.onRegister     // 마찬가지
        };...



6) 등록처리도 작성
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
                                    <input type="text" id="firstName" name="firstName" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="firstName">First Name</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="text" id="lastName" name="lastName" className="form-control" onChange={this.onChangeHandler} />
                                    <label className="form-label" htmlFor="lastName">Last Name</label>
                                </div>

                                <button type="submit" className="btn btn-primary btn-block mb-4">Sign up</button>
                            </form>
                    </div>


7) 마찬가지로 함수도 작성해줌


8) npm start해보면 화면이 login인지 register인지에 따라 잘 바뀌는 것을 볼 수 있음.


# ------------[ Buttons.js 생성 ]---------------------------

import React from 'react';

const Buttons = (props) => {
    return (
        <div className="row">
            <div className="col-md-12 text-center" style={{ marginTop: '30px' }}>
                <button className="btn btn-primary" style={{ margin: '10px' }} onClick={props.login}>
                    Login
                </button>
                <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.logout}>
                    LogOut
                </button>
            </div>
        </div>
    );
};

export default Buttons;


# ------------[ AppContent.js 에 추가해주기 ]---------------------------
버튼 클릭시 message.. 를 하기 위해서 AppContent.js 수정

1) class형으로 변환

import React from 'react';
import WelcomContent from './WelcomContent';
import LoginForm from './LoginForm';

class AppContent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: 'welcome'
        }
    };

    render() {
        return (
            <div>
                <Buttons login={this.login}
                    logout={this.logout} />

                <WelcomContent />
                <LoginForm />
            </div>
        )
    }
}

export default AppContent;


2) axios_helper.js에서 만들었던 setAuthToken, getAuthToken을 사용

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


3) 지금은 welcome과 login폼들이 한 번에 나옴.
-> 각각 welcome, login일대만 보일 수 있도록 수정
...
            <div>
                {/* Buttons 호출, props 전달, 위에 함수 정의 */}
                <Buttons login={this.login}
                    logout={this.logout} />

                {/* this.state.compoenetToShow값이 welcome일 때만 작동 */}
                {this.state.componentToShow === 'welcome' && <WelcomContent />}
                {/* this.state.componentToShow값이 login일 때만 작동 */}
                {this.state.componentToShow === 'login' && <LoginForm />}
            </div>
...

4) LoginForm.js에서 constructor(props){ ... } 에 추가했던 onLogin과 onRegister이 여기서 넘겨준 것임.

            ...
            lastName: "",
            // AppContent.js에서 호출시, LoginForm 호출시, props로 전달.
            onLogin: props.onLogin,      // props.onLogin을 onLogin 안에 할당       // 사용자가 자격증명을 보낸 후 상위구성요소가 로그인 양식을 숨길 수  있다. 
            onRegister: props.onRegister     // 마찬가지



==> 때문에 AppContent.js도 값을 넘기도록 수정
            ...
                {/* this.state.compoenetToShow값이 welcome일 때만 작동 */}
                {this.state.componentToShow === 'welcome' && <WelcomContent />}

                {/* this.state.componentToShow값이 login일 때만 작동 */}
                {/* LoginForm 호출시, onLogin과 onRegister라는 props를 전달 */}
                {this.state.componentToShow === 'login' && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />}
            </div>


5) 마지막으로, onLogin, onRegister 함수들도 정의( render()위에!)

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
        .then((res) => {
            this.setState({ componentToShow: 'message' });
            setAuthToken(res.data.token);       // 로그인 성공시 스프링 부트에서 생성한 토큰을 저장후, 다른 메뉴 클릭시 토큰을 들고가서 인증받는다
        })
            .catch((error) => {
                this.setState({ componentToShow: 'welcome' });
                setAuthToken(null);
            })
    }

    onRegister = (e, id, password, firstName, lastName) => {        // 이벤트, id, password 모두 넘김
        e.preventDefault();
        request(
            "POST",
            "/login",
            {
                id: id,
                password: password,
                firstName: firstName,
                lastName: lastName
            }
        ).then((res) => {
            this.setState({ componentToShow: 'message' });
            // res의 data는 axios_helper.js의 request에서받아옴
            setAuthToken(res.data.token);       // 로그인 성공시 스프링 부트에서 생성한 토큰을 저장후, 다른 메뉴 클릭시 토큰을 들고가서 인증받는다
        })
            .catch((error) => {
                this.setState({ componentToShow: 'welcome' });
                setAuthToken(null);
            })
    }

# ------------[ axios_helper.js 수정 ]--------------------------- 


import axios from 'axios';



// axios_helper 역할 : 
// ==> backend와 통신
// ==> 로그인 완료시, JWT를 저장.

axios.defaults.baseURL = 'http://localhost:8081'        // 백엔드의 ip 주소 연결
axios.defaults.headers.post["Content-Type"] = 'application/json'

// 로그인 성공시 토큰값 들고가기
export const getAuthToken = () => {
    return window.localStorage.getItem("auth_token");      // key값 틀리지 않게 주의
}

export const setAuthToken = (token) => {
    window.localStorage.setItem("auth_token", token);
}

export const request = (method, url, data) => {     // export를 한 번에 선언해서, 아래에 export default문을 안 써도 되게 함

    const token = localStorage.getItem("auth_token");
    // 토큰이 존재하면 http의 헤더를 만듦 인증방식:Bearer(토큰을 소지하고있으면 접근허용)
    // 즉, 코드의 Authorization: Bearer ${token}은 서버에 “이 토큰으로 인증할게요”라고 알리는 HTTP 헤더.
    // Bearer은 소지자에게 권한이 있으니, Http 사용만료 등을 꼭 챙겨야함
    const headers = token ? { Authorization: `Bearer ${token}` } : {};      // el태그니까 `(백틱) 으로 감싸줌

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    })
}





# ------------[ CORS policy 에러시 백엔드 수정 ]---------------------------

1) DTO 패키지 만든 후, UserDTO 작성

package fullstack.jwt.back_end.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor		// 디폴트 생성자 역할
@AllArgsConstructor		// 매개변수 생성자 역할
@Builder		// lombok  (매개변수 순서에 맞추지 않아도 됨 (ex 매개변수생성자))  
@Data			// Getter, Setter 합쳐서 Data로 줄 수 있음 --> lombok에 있음
@Entity
@Table(name="react_login_tbl")
public class UserDTO {
	
	@Id		// primary key라서
	@Column(name="id")			// 컬럼값과 dto 이름이 매핑되지 않을 때 이렇게 지정해주면 된다. 
	private String id;
	private String password;
	//	 @Column("first_name");		// ==> 별칭 주는 방법 (pom.xml에 추가로 등록해줘야해서 지금은 주석)
	private String firstName;
	private String lastName;
	
	// 토큰은 크기를 굉장히 크게 줘야한다 해쉬코드가 길기 때문에!
	private String token;

}




2) Controller 수정
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(){
		return null;
	}


# ------------[ config 패키지 생성 --> 설정 패키지 ]---------------------------
1) config 패키지 생성 --> 설정 패키지
각 번호가 백엔드 타는 순서를 뜻함

2)  클래스 파일 각각 생성
(1) WebConfig.java                      // 프론트엔드가 보낸 요청을 수락(Cors Policy에 의한 NOT ACCESS 해결)
SecurityConfig.java                     // 시큐리티
UserAuthenticatinoEntryPoint.java       // 승인되지 않은 HTTP코드를 반환
JwtAuthFilter.java                      // 모든 엔드포인트를 구성하기 위해 보안구성
UserAuthProvider.java                   // 토큰 생성 및 검증  // JWT를 생성하고 읽으려면 비밀키가 필요. 애플리케이션에서 yml 파일에서 구성하고 여기에 주입한다. ==> 주입클래스
PasswordConfig                          // 암호화에 대한 인코딩 알고리즘 선택 클래스



# ------------[ (1) WebConfig.java 작성!! ]---------------------------

package fullstack.jwt.back_end.config;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// 1) 첫번째로 타는 클래스
// 프론트엔드가 보낸 요청(==> 토큰, 즉 '자격증명')을 수락(Cors Policy에 의한 NOT ACCESS 해결)

@Configuration
@EnableWebMvc
public class WebConfig {
	
	// org.springframework.web.cors.CorsConfiguration로 임포트!!! 임포트 주의~!!
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	
	
	@Bean			// 빈을 주입해서 객체 생성
	public FilterRegistrationBean corsFilter() {
		
		// 프론트엔드가 보낸 요청(토큰)을 보내면 그것을 받아들여아한다. 
		CorsConfiguration config = new CorsConfiguration();	
		
		System.out.println(" <<< WebConfig - 1 >>> ");
		
		
		// 헤더 관련
		config.setAllowCredentials(true);			// 허용
		config.addAllowedOrigin("http://localhost:3000");
		config.setAllowedHeaders(Arrays.asList(		// 값이 여러개인 헤더를 배열형태로 받아 List로 바꿔줌
				// 주의! springFramework의 Headers로 가져와야 함 꼭!
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_TYPE,
				HttpHeaders.ACCEPT
		));	
		System.out.println(" <<< WebConfig - 2 >>> ");
		
		
		// 각 매핑에 대한 허락 요청
		config.setAllowedMethods(Arrays.asList(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()
		));
		
		config.setMaxAge(3600L);			// 옵션 요청이 수락되는 시간 -> 1시간 설정
		source.registerCorsConfiguration("/**", config);			// 들어오는 모든 url에 위의 작업을 다 적용.
		
		System.out.println(" <<< WebConfig - 3 >>> ");
		
		
		
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));			// source에 위의 정보들이 다 담겨있음 -> 전체 모든 config정보를 모든 사이트에 적용하기 위해 
		bean.setOrder(-102);		// 가장 낮은 위치 - 다른 코드에 영향이 없게 하기 위해서
		
		
		
		return bean;
	}
}



# ------------[ (2) SequrityConfig.java 작성!! ]---------------------------
package fullstack.jwt.back_end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//2) 두번째로 타는 클래스

// 시큐리티 
@RequiredArgsConstructor			// 매개변수 생성자
@Configuration						// config 부분이니까 넣어줘야하는 어노테이션ㄴ
@EnableWebSecurity					// 시큐리티 작동선언
public class SecurityConfig {

	
	// 매개변수 생성자 지정
	// 작성 => @RequiredArgsConstructordmf 을 선언해주고, 매개변수 앞에 final을 붙여주면 됨. ex) final 변수명
	private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;			// 만들어둔 UserAuthenticationEntryPoint 클래스를 매개변수 중 하나로 선언
	private final UserAuthProvider userAuthProvider;						// 이것도. 만들어뒀던 UserAuthProvider 클래스를 매개변수로 선언
	
	
	
	// 위에 @RequiredArgsConstructordmf를 선언해주지 않았다면 아래와 같이 매개변수 생성자를 선언해줬을 것임.
//	public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, UserAuthProvider userAuthProvider) {
//		this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
//		this.userAuthProvider = userAuthProvider;
//	}
	
	
	// 시큐리티 필터 체인
	// Security Filter Chain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception{
		
		System.out.println(" <<< SecurityConfig.java   --  securityFilterChain()  >>> ");
		
		
		http.exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)				// 보안문제 발생시 사용자 지정 메세지 반환
			.and()
			.addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)	// Spring Sequrity의 인증필터(BasicAuthenticationFilter.class) 전에, JwtAuthFilter부터 사용해라 => Jwt는 토큰이므로, 스프링시큐리티가 먼저 사용되면 접근 자체가 안될 수도 있음. 
			.csrf().disable()		// 시큐리티를 걸면, 각 화면에 csrf를 지정해줘야함. 그것을 비활성화해서 복잡성을 줄이는 작업
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		// SessionCreationPolicy.STATELESS 애플리케이션을 스프링에 전달하면, 스프링에서 세션과 쿠키를 생성하지 않도록 함. (JWT사용을 위해)
			.and()
			.authorizeHttpRequests((requests) -> requests			// 매개변수 requests를 들고가서 
					.antMatchers(HttpMethod.POST, "/login","/register").permitAll()				//permitAll()-> 모두가 사용가능하게 함. ==> 로그인과 회원가입은 모두가 사용할 수 있어야함 -> 인증이 필요하지 않은 유일한 엔드포인트
					.anyRequest().authenticated()
					);
		return http.build();		//작성한 것들을 가져가서 build시켜라
	}
	
	
}


# ------------[ (3) SequrityConfig.java 작성!! ]---------------------------
# 근데 중간에 들어가는 JwtAuthFilter.java 매개변수 생성자가 필요하기 때문에 Jwt를 미리 작성해도 됨.
# 하지만 실행 순서상 SequerityConfig.java가 두 번째로 타는 클래스라 볼 수 있음. (내부에 들어가는 매개변수 클래스들이 먼저 타므로 2번째의 최종)

package fullstack.jwt.back_end.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//2) 두번째로 타는 클래스  하지만 내부에 있는 매개변수들이 더 먼저 타므로 2-3번째로 타는 클래스라고 볼 수 있음

// 시큐리티 
@RequiredArgsConstructor			// 매개변수 생성자
@Configuration						// config 부분이니까 넣어줘야하는 어노테이션ㄴ
@EnableWebSecurity					// 시큐리티 작동선언
public class SecurityConfig {

	
	// 매개변수 생성자 지정
	// 작성 => @RequiredArgsConstructordmf 을 선언해주고, 매개변수 앞에 final을 붙여주면 됨. ex) final 변수명
	private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;			// 만들어둔 UserAuthenticationEntryPoint 클래스를 매개변수 중 하나로 선언
	private final UserAuthProvider userAuthProvider;						// 이것도. 만들어뒀던 UserAuthProvider 클래스를 매개변수로 선언
	
	
	
	// 위에 @RequiredArgsConstructordmf를 선언해주지 않았다면 아래와 같이 매개변수 생성자를 선언해줬을 것임.
//	public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, UserAuthProvider userAuthProvider) {
//		this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
//		this.userAuthProvider = userAuthProvider;
//	}
	
	
	// 시큐리티 필터 체인
	// Security Filter Chain
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception{
		
		System.out.println(" <<< SecurityConfig.java   --  securityFilterChain()  >>> ");
		
		
		http.exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)				// 보안문제 발생시 사용자 지정 메세지 반환
			.and()
			.addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)	// Spring Sequrity의 인증필터(BasicAuthenticationFilter.class) 전에, JwtAuthFilter부터 사용해라 => Jwt는 토큰이므로, 스프링시큐리티가 먼저 사용되면 접근 자체가 안될 수도 있음. 
			.csrf().disable()		// 시큐리티를 걸면, 각 화면에 csrf를 지정해줘야함. 그것을 비활성화해서 복잡성을 줄이는 작업
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		// SessionCreationPolicy.STATELESS 애플리케이션을 스프링에 전달하면, 스프링에서 세션과 쿠키를 생성하지 않도록 함. (JWT사용을 위해)
			.and()
			.authorizeHttpRequests((requests) -> requests			// 매개변수 requests를 들고가서 
					.antMachers(HttpMethod.POST, "/login","/register").permitAll()				//permitAll()-> 모두가 사용가능하게 함. ==> 로그인과 회원가입은 모두가 사용할 수 있어야함 -> 인증이 필요하지 않은 유일한 엔드포인트
					.anyRequest().authenticated()
					);
		return http.build();		//작성한 것들을 가져가서 build시켜라
	}
	
	
}







# ------------[ (4) UserAuthenticationEntryPoint.java 작성!! ]---------------------------

package fullstack.jwt.back_end.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import fullstack.jwt.back_end.dto.ErrorDTO;

// SecurityConfig.java에 사용되는 매개변수 2개 중 1개인 클래스
// 2-1번째로 타는 클래스

// 승인되지 않은 HTTP코드를 반환  	// 예외처리 클래스 

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	// implements AuthenticationEntryPoint를 구현(implements)한 후, 메서드 오버라이드(Add Methods 해줌)

	
	// 참조변수 생성
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	
	@Override
	public void commence(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException)throws IOException, ServletException {
		
		
		System.out.println("  <<< UserAuthenticationEntryPoint.java  -- commence()  >>>  ");
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 			// 승인되지 않은 HTTP 코드를 반환한다
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);		// 헤더에 json 형식을 사용한다는 뜻
		
		OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Unauthorized path"));
		
	}
	
}

# ------------[ (4-1) ErrorDTO.java 작성!! ]---------------------------
package fullstack.jwt.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 에러시 DTO

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorDTO {
	
	private String message;

}


# ------------[ (5-1) UserRepository.java 작성!! ]---------------------------
# repository 패키지 생성 > UserReposiroty라는 이름의 인터페이스 생성

package fullstack.jwt.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fullstack.jwt.back_end.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, String>{
	
}



# ------------[ (5-2) AppException.java 작성!! ]---------------------------
# exception 패키지 생성 > AppException.java라는 클래스 생성

package fullstack.jwt.back_end.exception;

import org.springframework.http.HttpStatus;

// 사용자 정의 exception
public class AppException extends RuntimeException {
	
	// 멤버변수 정의
	private final HttpStatus code;
	
	// 매개변수 생성자
	public AppException(String message, HttpStatus code) {
		super(message);		// super에 message전달
		this.code = code;
	}
	
	public HttpStatus getCode() {
		return code;
	}
}

# ------------[ (5-3) CredentialsDTO.java 작성!! ]---------------------------
# dto 패키지에 CredentialsDTO.java 클래스 생성

package fullstack.jwt.back_end.dto;

// 로그인시, 비밀번호 인증할때의 DTO

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CredentialsDTO {
	
	private String id;
	private char[] password;
}


# ------------[ (5-4) UserService.java 작성!! ]---------------------------
# service 패키지 생성 > UserService 라는 이름의 클래스 생성


# ------------[ (5-5) SignUpDTO.java 작성!! ]---------------------------
# SignUpDTO 라는 이름의 클래스 생성 -> DTO

package fullstack.jwt.back_end.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원가입 DTO


@NoArgsConstructor		// 디폴트 생성자 역할
@AllArgsConstructor		// 매개변수 생성자 역할
@Builder		// lombok  (매개변수 순서에 맞추지 않아도 됨 (ex 매개변수생성자))  
@Data			// Getter, Setter 합쳐서 Data로 줄 수 있음 --> lombok에 있음
@Entity
@Table(name="react_login_tbl")
public class UserDTO {
	
	@Id		// primary key라서
	@Column(name="id")			// 컬럼값과 dto 이름이 매핑되지 않을 때 이렇게 지정해주면 된다. 
	private String id;
	private String password;
	//	 @Column("first_name");		// ==> 별칭 주는 방법 (pom.xml에 추가로 등록해줘야해서 지금은 주석)
	private String first_name;
	private String last_name;
	
	// 토큰은 크기를 굉장히 크게 줘야한다 해쉬코드가 길기 때문에!
	private String token;

}


# ------------[ (5-6) UserMapper.java 작성!! ]---------------------------
# mappers 패키지 생성 > UserMapper라는 이름의 인터페이스 생성

package fullstack.jwt.back_end.mappers;

import org.apache.ibatis.annotations.Mapper;

import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;

@Mapper
public interface UserMapper {

	
	UserDTO toUserDTO(UserDTO user);
	UserDTO signUpToUser(SignUpDTO userDTO);
}


# ------------[ (5-7) userAuthProvider.java 작성!! ]---------------------------
# !!!!!!!!! pom.xml에 jwt 추가해야함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 중요
package fullstack.jwt.back_end.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.service.UserService;
import lombok.RequiredArgsConstructor;


// 2-2-1 번째로 타는 클래스

// JWT를 생성하고 읽으려면 비밀키가 필요하다
// 애플리케이션에서 yml 파일에서 구성하고 여기에 주입한다. 

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
	
	// anotaion의 value를 임포트해야함 주의
	@Value("${security.jwt.token.secret-key:secret-value}")
	private String secretKey;
	private final UserService userService;			// 직접 생성(service패키지>UserService.java(클래스))  // 2-2-0 번재 실행되는 클래스
	
	
	@PostConstruct
	protected void init() {
		System.out.println(" <<<< UserAuthProvider.java  --  init() >>>  ");
		
		// 일단 텍스트로 된 비밀키를 피하기 위해 base64로 인코딩해서 다시 담음
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String id) {
		System.out.println(" <<<< UserAuthProvider.java  --  createToken() >>>  ");
		
		Date now = new Date();			// java.util
		Date validity = new Date(now.getTime() + 360000);		// 토큰만료시간 설정 (1시간)
		
		// JWT를 사용하려면 pom.xml에 java-jwt 추가해야함
		/*
		<!-- jwt 토큰 생성을 위해 추가(중요) -->
      	<!-- https://mvnrepository.com/artifact/com.auth0/java-jwt -->
      	<dependency>
          	<groupId>com.auth0</groupId>
          	<artifactId>java-jwt</artifactId>
          	<version>3.10.3</version>
      	</dependency>
		 */
		
		
		return JWT.create()
				.withIssuer(id)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(Algorithm.HMAC256(secretKey));		// jwt의 3번째 구조 sign(서명)엔 모든 데이터가 잇어야 하므로 알고리즘 HMAC256 에 secretKey를 들고감
	}
	
	public AuthenticateAction validationToken(String token) {
		System.out.println(" <<<< UserAuthProvider.java  --  validationToken() >>>  ");
		System.out.println(" <<<< UserAuthProvider.java  --  token >>>  " + token);
		
		
		// 임포트 주의 	(import com.auth0.jwt.JWTVerifier;)
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();	
		
		System.out.println(" <<<< UserAuthProvider.java  --  validationToken()  1   >>>  ");
		
		DecodedJWT decoded = verifier.verify(token);		// JWT를 확인하기 위해 먼저 디코딩한다.  유효시간을 초과하면 예외가 발생한다.
		
		
		System.out.println(" <<<< UserAuthProvider.java  --  validationToken()  2   >>>  ");
		
		UserDTO user = userService.findById(decoded.getIssuer());
		
		
		// 사용자가 데이터베이스에 존재하는지 확인
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
		
	
	}
}



# ------------[ (5-8) JwtAuthFilter.java 작성!! ]---------------------------

package fullstack.jwt.back_end.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

//	2-2-2 번째로 타는 클래스
// userAuthProvider.java + UserAuthenticationEntryPoin.java 클래스를 합쳐, SecurityConfig.java  ==> 이 3개의 클래스가 한 묶음이라 볼 수 이씅ㅁ


// 모든 엔드포인트를 구성하기 위해 보안구성
//프론트(react)의, axios_helper.js 클래스에서 넘긴 token 정보를 받아와야함
// OncePerRequestFilter(요청당 한번만 필터하기) 를 상속(extends)를 받아옴

@RequiredArgsConstructor		// 매개변수 생성자
public class JwtAuthFilter extends OncePerRequestFilter{		// 요청당 한번만 사용되길 원하므로

	
	// 매개변수 생성자 안에 들어갈 변수
	private final UserAuthProvider userAuthProvider;			// 2-2-1번째로 타는 클래스(먼저작성)
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		System.out.println(" <<<  JwtAuthFilter.java  --  doFilterInternal()  >>> ");
		
		
		/*리액트 코드 일부 발췌
		onst token = localStorage.getItem("auth_token");
		const headers = token ? { Authorization: `Bearer ${token}` } : {};*/
		
		
		// HttpHeaders -> springframework 거임. import 주의
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(header != null) {	// 길이가 정확하고 Bearer 토큰이어야 한다. 
			String[] elements = header.split(" ");		// 공백을 기준으로 쪼갬
			
			if(elements.length == 2 && "Bearer".equals(elements[0])) {
				try {
					// 자격증명(JWT)이 유효하면, 보안 컨텍스트에 인증빈을 추가한다 ->  검증 통과
					SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validationToken(elements[1]));		// 클래스명.스태틱().setAuth..
				} catch(RuntimeException e) {
					// 에러가 발생하면 클리어
					SecurityContextHolder.clearContext(); 
					throw e;		// 그리고 에러처리
				}
			}
			
		}
		
		filterChain.doFilter(request, response); 			// 반드시 필터 끝에서 doFilter() 메서드를 호출해라
		
	}		
	
}


# ------------[ (6) PasswordConfig.java 작성!! ]---------------------------
package fullstack.jwt.back_end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 암호화에 대한 인코딩 알고리즘 선택
@Component
public class PasswordConfig {

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}



# firstName -> first_name , LastName -> last_name 변경 (백엔드 뿐만 아니라 리액트 LoginForm, AppContent 등도!)




# ------------[ AuthController.java 수정 ]---------------------------

package fullstack.jwt.back_end.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fullstack.jwt.back_end.config.UserAuthProvider;
import fullstack.jwt.back_end.dto.CredentialsDTO;
import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	
	// 로거 가져오기 (slf4j로 임포트 해야하는 것 주의)
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private final UserService userService;
	private final UserAuthProvider userAuthProvider;
	
	// http://localhost:8081    => user / 부트에서 생성한 비밀번호 붙여넣기
	@GetMapping({"", "/"})			
	public String index() {
		
		System.out.println("<<< AuthController.java   --  index() >>>");
		
		return "index";			// 실제 페이지가 아님 => @RestController로 인해, 주소 아닌 값을 브라우저에 출력하는 것임.
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentailsDTO){
		
		System.out.println("<<< AuthController.java   --   login() >>>");
		
		UserDTO user = userService.login(credentailsDTO);
		System.out.println("<<< AuthController.login id=" + user.getId() + " >>>");
		
		System.out.println("token : " + userAuthProvider.createToken(user.getId()));
		user.setToken(userAuthProvider.createToken(user.getId()));		// id를 받아와서 토큰을 새로 생성
		
		return ResponseEntity.ok(user);				// 크롬브라우저 F12 개발자도구 > Headers : 200 OK   => 새로 생성한 JWT(토큰)을 반환함
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO){
		
		System.out.println("<<< AuthController.java   --   register() >>>");
		
		UserDTO user = userService.register(signUpDTO);			// 등록이므로 리액트에서 넘어온 4개의 정보 + token  => insert
		
		System.out.println("token : " + userAuthProvider.createToken(user.getId()));
		user.setToken(userAuthProvider.createToken(user.getId()));		// id를 받아와서 토큰을 새로 생성
		
		return ResponseEntity.created(URI.create("/users/" + user.getId()))
				.body(user);			// 크롬브라우저 F12 개발자도구 > Headers : 201 OK   => Created 반환
	}
}
