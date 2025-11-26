import React, { useEffect, useState } from 'react';
import { Button, Form } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';

const UpdateForm = () => {

    const navigate = useNavigate();
    const { b_num } = useParams();

    // 2. 입력한 값들이 setBoard를 통해 board변수에 담기고 
    const [board, setBoard] = useState({
        b_title: '',
        b_content: '',
        b_writer: '',
        b_password: '',
    })

    // 1. 1건 조회부터 해야함 -> 조회를 해야 input의 value값에 기존 정보를 보여줄 수 있음
    useEffect(() => {     // 익명함수 
        fetch('http://localhost:8081/api/board/' + b_num)
            .then((res) => res.json())
            .then((res) => {
                // 상세정보를 setBoard에 전달, board에 전달되어 화면에 뿌린다. (한번만! )
                setBoard(res);
            })
    }, [])

    // 5. form에 onChange={changeValue} 추가, 상세정보로 가져온 기존 값이 change될때마다 호출한 함수 changeValue의 (e)에 이벤트 발생
    const changeValue = (event) => {
        setBoard({      // 화면에 입력한 값 넘기기      // 기존값에 새롭게 입력된 수정정보들이 들어감
            ...board,                               // a. 기존 상세페이지에
            [event.target.name]: event.target.value     // b. 수정값들이 반영됨
        })
    }
    // 6. 스프링부트로 수정처리 요청
    const submitBoard = (event) => {
        event.preventDefault();

        // 스프링 부트와 통신
        fetch("http://localhost:8081/api/board/" + b_num, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(board)     // @RequestBody로 받는 부분 js오브젝트를 json변환해서 넘김 . 백엔드에서 데이터를 update하고 200을 리턴
        })
            .then((res) => {
                console.log(1, res);
                if (res.status === 200) {
                    // 반환값이 200 => 성공일 경우
                    return res.json();          // json으로 돌려 받아라
                } else {
                    return null;        // 실패면 null
                }
            })
            .then((res) => {     // 결과를 가져와서
                console.log('정상성공', res);
                if (res != null) {
                    // 4-1.
                    navigate('/board/' + b_num)          // 4-1. App.js의 Route에서 BoardList() -> 게시글 목록을 호출
                } else {
                    alert('게시글 수정에 실패했습니다');
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
            {/* 3. 상세정보를 화면에 뿌림 value={결과}*/}
            {/* 4. input값 변경 -> changeValue() 호출*/}
            <Form onSubmit={submitBoard} >  {/* submit 버튼 클릭시 submitBoard() 함수를 호출, onClick으로 해도 가능 */}

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>글제목</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter Email"
                        onChange={changeValue}
                        name="b_title"
                        value={board.b_title} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>글내용</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter content"
                        onChange={changeValue}
                        name="b_content"
                        value={board.b_content} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>아이디</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter id"
                        onChange={changeValue}
                        name="b_writer"
                        value={board.b_writer} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Enter password"
                        onChange={changeValue}
                        name="b_password"
                    />
                </Form.Group>

                <Button variant="primary" type="submit">
                    수정하기
                </Button>

            </Form>
        </div>
    );
};

export default UpdateForm;