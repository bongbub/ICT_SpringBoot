import React, { useEffect, useState } from 'react';
import { Button, Card } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';


// props에 BoardItem에서 b_num을 전달한다. 
const Detail = (props) => {
    console.log('detail', props);

    // 네비변수
    const navigate = useNavigate();

    // (1). 파라미터 읽어오기
    const propsParams = useParams();            // props로 넘겨준 값 가져오기
    const b_num = propsParams.b_num;            // 그 중 b_num이라는 값 뽑기

    // 상태관리
    const [board, setBoard] = useState({
        // 비어있는 값에서 useEffect(한번만 실행)을 통해 값을 가져와서 넣어줘야하므로, 처음엔 비어있는값으로초기화
        b_num: '',
        b_title: '',
        b_content: '',
        b_writer: '',
        b_read_cnt: '',
        b_reg_date: '',
    })


    // 상세페이지
    useEffect(() => {
        // (2). 스프링부트의 상세페이지로 이동하기 
        fetch('http://localhost:8081/api/board/' + b_num)   // b_num은 props내에 있음(이전페이지 BoardItem에서 전달해줬으니까)
            // (3). 결과를 콜백함수로 돌려받아 res에 담음
            .then(res => res.json())        // 한건의 결과(res)가 res.json 즉, json형태로 반환됨 => 1건 select
            .then((res) => {
                // 해당 결과를 렌더링해줘야하므로 
                //(4). 상세정보를 setBoard를 통해 board dto에 담고 화면에 뿌린다.
                setBoard(res);      // setter을 이용하여 board 값을 세팅함  (상세정보를 setBoard로 전달 -> board (dto)로 전달 -> return으로 뿌려주기 위해)
            })
    }, [])  // 한 번만 타야하므로


    // 수정페이지
    const updateBoard = () => {
        navigate('/updateForm/' + b_num)      // App.js의 Route에서 updateForm(수정페이지) 호출.
    }


    // 삭제페이지
    const deleteBoard = () => {
        fetch('http://localhost:8081/api/board/' + b_num, {
            method: "DELETE",
        })
            .then((res) => res.text())
            .then((res) => {
                if (res === "ok") {       // 대소문자 주의
                    // App.js의 Route에서 BoardList(게시글 목록) 호출       // 지금은 삭제처리시 b_show가 N이됨
                    navigate('/boardList')
                    console.log(res.state)
                } else {
                    alert('게시글 삭제에 실패했습니다.');
                }
            })
    }


    return (
        // (5). 화면에 결과값이 뿌려진다.
        <div>
            <h3>Detail</h3>
            <Card>
                <Card.Title>글번호 : {board.b_num}</Card.Title>
                <Card.Title>글제목 : {board.b_title}</Card.Title>
                <Card.Title>글내용 : {board.b_content}</Card.Title>
                <Card.Title>작성자 : {board.b_writer}</Card.Title>
                <Card.Title>조회수 : {board.b_read_cnt}</Card.Title>
                <Card.Title>작성일 : {board.b_reg_date}</Card.Title>
            </Card>

            <Button variant="warning" onClick={updateBoard}>수정</Button>
            {'     '}       {/* 공백주기*/}
            <Button variant="success" onClick={deleteBoard}>삭제</Button>
            <hr />
        </div>
    );
};

export default Detail;