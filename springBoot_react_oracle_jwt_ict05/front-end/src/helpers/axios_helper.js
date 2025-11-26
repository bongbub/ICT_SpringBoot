
import axios from 'axios';

// Axios : 브라우저에서 HTTP 요청(GET/POST..등) 을 보내는 자바스크립트 라이브러리
// url : 어디로 보낼지, method:어떤건지(get, post...) , 
// headers: 메모 (Content-Type: apllication/json => 본문은 json임)
//              (Authorication: Bearer<토큰> => 이 토큰으로 인증할게)
// body : 실제 보내는 데이터. 

// axios_helper 역할 : 
// ==> backend와 통신
// ==> 로그인 완료시, JWT를 저장.

axios.defaults.baseURL = 'http://localhost:8081'        // 백엔드의 ip 주소 연결 // 기본주소 설정
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
    const headers = (token && token !== 'null' && token !== 'undefined') ? { Authorization: `Bearer ${token}` } : {};      // el태그니까 `(백틱) 으로 감싸줌

    console.log('axios 탔음');
    console.log('method : ', method);
    console.log('url : ', url);
    console.log('data : ', data);

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    })
}