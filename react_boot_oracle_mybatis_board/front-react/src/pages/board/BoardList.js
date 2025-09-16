import React, { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';
import BoardItem from '../../components/BoardItem';

const BoardList = () => {


    const [boardList, setBoardList] = useState([]);     // 초기값을 모르므로 빈배열로 boardList에 대입

    // 리액트 hook중, 반드시 실행 후 넘어가는 것 -> useEffect
    useEffect(() => {
        // 익명함수 ()와 함수명이 생략된 arrow function (=>)

        // fetch()를 통해 톰캣서버에게 데이터를 요청
        fetch("http://localhost:8081/api/boardList", {
            method: "GET"
        })
            .then(res => res.json()     //응답이 오면 javasript object로 변환하겠다
            )
            .then(res => {
                console.log(1, res);
                setBoardList(res);          // setter를 통해 res값으로 boardList변수에 변경을 해서 리스트가 뿌려진다 
            })
    }, []); // 한 번만 실행할 시 빈배열[]을 준다  => 렌더링할 때 위의 함수구문이 반드시 한 번 실행됨    []-> 디펜던시인데, setState로 렌더링될 때마다 실행되면 안되고 한 번만 실행되게 함


    return (
        <div>
            <Container>
                <h3>BoardList</h3>
                <br />

                {/* boardList 길이만큼 <BoardItem> 을 뿌린다. -> setBoardList(res)에 의해 조회한 값을 뿌린다. (boardList: items, board:var) */}
                {boardList.map(board =>
                    // key생략시 unique key props error 
                    // 이후 자식 컴포넌트에게 props값 전달
                    <BoardItem key={board.b_num} board={board} />
                )}
            </Container>
        </div>
    );
};

export default BoardList;