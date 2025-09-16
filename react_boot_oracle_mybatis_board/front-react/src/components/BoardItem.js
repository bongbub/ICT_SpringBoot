import React from 'react';
import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const BoardItem = (props) => {

    const { b_num, b_title, b_content, b_password, b_writer, b_readCnt, b_regDate, b_comment_count } = props.board;     // 구조분해할당

    return (
        <div>
            <Card>
                <Card.Body>
                    <Card.Title>글번호 : {b_num}</Card.Title>
                    <Link to={"/board/" + b_num} className="btn btn-primary">상세보기</Link>      {/* boardList를 통해 넘어온 b_num은 key임!!! : Link to를 통해 App.js로 넘겨줌 */}
                    <Card.Title>글제목 : {b_title}</Card.Title>
                    <Card.Title>글내용 : {b_content}</Card.Title>
                    <Card.Title>작성자 : {b_writer}</Card.Title>
                    <Card.Title>비밀번호 : {b_password}</Card.Title>
                    <Card.Title>조회수 : {b_readCnt}</Card.Title>
                    <Card.Title>작성일 : {b_regDate}</Card.Title>
                    <Card.Title>댓글수 : {b_comment_count}</Card.Title>
                </Card.Body>
            </Card>
        </div >
    );
};

export default BoardItem;