import React, { useState } from 'react';
import { Button, Form } from 'react-bootstrap';         // Form import 주의(dom에 있는 Form을 하지 않도록)
import { useNavigate } from 'react-router-dom';

const SaveForm = () => {

    // 4-2. 변수선언 navigate
    const navigate = useNavigate();     // 컨트롤러의 redirect와 유사한기능! (fetch는 직접 url와 통신해서 가는거고, redirect와 유사한 요 기능은 그냥 만들어졋던 곳에 돌아가는 것임)

    // 3. 입력한 값들이 setBoard를 통해 board변수에 담기고, submit이 컨트롤러로 통신할 때 boardDTO 로 한꺼번에 전달됨
    const [board, setBoard] = useState({
        b_title: '',     // 초기값이 들어옴 -> 공백(비어있어야하므로)
        b_content: '',
        b_writer: '',
        b_password: '',
    })

    // 2. form에 onChange={changeValue} 추가, => input 값이 change될 때마다 changeValue 함수의 event에 이벤트context가 들어감
    const changeValue = (event) => {
        // input값이 변경됐을 때 호출되는 함수

        // 화면에서 입력받은 값으로 변경하겠다
        setBoard({
            ...board,       // 기존값에 영향없이 복사해오기 위해 --> ... 생략시 마지막 input 값만 저장됨. title넘어오면 title변경하고.. 해야하는데 마지막 값만 저장될 수 잇으므로 꼭 붙이는거 주의
            [event.target.name]: event.target.value     // 동적으로 key값을 만드는 법 => input값이 바뀔 때마다 value가 name으로 들어감
        })
        // 예를 들어,, title먼저 가져와서 수정했음 -> 다음 board를 가져왔을 때 ...이 없다면 b_title에 '' 이 들어감. (같은 공간을 공유하므로)=> 때문에 기존 데이터가 날아가고 마지막 password만 바뀌게 될 것임 (content작성시 title날아가고 writer작성시 content날아감 -> insert오류)
        // 때문에 ...을 붙여서 얕은 복사를 해줘야함
    }

    // 4.   아래 url로 요청시 서버 쪽에서 받아주는지 확인
    //      실제로 받았다면 save가 제대로 되었는지 확인
    const submitBoard = (event) => {

        event.preventDefault();     // 이벤트 동작을 막음

        // 스프링 부트와 통신
        fetch("http://localhost:8081/api/board", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(board)      // javascript 오브젝트를 json으로 변경해서 넘긴다 ( 받을 땐 res.json이었지? 참고:boardList.js)     // 백엔드에서 데이터를 insert하고 2001을 리턴(CREATED)
        })
            .then((res) => {
                // res에는 201이 담기겟지?
                console.log(1, res);
                if (res.status === 201) {
                    // 반환값이 201=>성공일 경우
                    return res.json();          // json으로 돌려 받아라
                } else {
                    return null;        // 실패면 null
                }
            })
            .then((res) => {     // 결과를 가져와서
                console.log('정상성공', res);
                if (res != null) {
                    // 4-1.
                    navigate('/boardList')          // 4-1. App.js의 Route에서 BoardList() -> 게시글 목록을 호출
                } else {
                    alert('게시글 작성에 실패했습니다');
                }
            })
            .catch((error) => {     //실패했을 경우
                console.log('실패', error);
            })
        // 결과를 콜백함수로 받기
        // 성공여부
    }


    return (
        <div>
            {/* 1. 입력 */}
            <Form onSubmit={submitBoard} >  {/* submit 버튼 클릭시 submitBoard() 함수를 호출, onClick으로 해도 가능 */}

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>글제목</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter Title"
                        onChange={changeValue}
                        name="b_title" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>글내용</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter content"
                        onChange={changeValue}
                        name="b_content" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>아이디</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter id"
                        onChange={changeValue}
                        name="b_writer" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Enter password"
                        onChange={changeValue}
                        name="b_password" />
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>

            </Form>
        </div>
    );
};

export default SaveForm;