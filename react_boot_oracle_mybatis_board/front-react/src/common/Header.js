import { Link } from 'react-router-dom';
import { Button, Container, Form, Nav, Navbar, NavDropdown } from 'react-bootstrap'; // 구조분해 할당(1개는 먼저 만들어야 나머지가 자동완성)



function Header() {
    // 구글 - react bootstrap 검색 > components > Navbars > Scrolling 코드 복사
    // Header() 작성 후 -> npm i react-bootstrap bootstrap  -> package.json 에 bootstrap과 react-bootstrap 설치됐는지 확인
    return (
        <>
            <Navbar bg="dark" variant="dark">
                <Container fluid>
                    <Navbar.Brand href="#">로고</Navbar.Brand>
                    <Navbar.Toggle aria-controls="navbarScroll" />
                    <Navbar.Collapse id="navbarScroll">
                        <Nav
                            className="me-auto my-2 my-lg-0"
                            style={{ maxHeight: '100px' }}
                            navbarScroll
                        >
                            {/* <Nav.Link href="#action1">Home</Nav.Link> */}
                            <Link to="/home" className="nav-link">Home</Link>

                            <Link to="/joinForm" className="nav-link">회원가입</Link>

                            <Link to="/loginForm" className="nav-link">로그인</Link>

                            <Link to="/boardList" className="nav-link">게시판</Link>

                            <Link to="/saveForm" className="nav-link">글쓰기</Link>

                            <NavDropdown title="마이페이지" id="navbarScrollingDropdown">
                                <NavDropdown.Item href="#">장바구니</NavDropdown.Item>
                                <NavDropdown.Item href="#">구매</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#">환불</NavDropdown.Item>
                            </NavDropdown>

                            <Nav.Link href="#" disabled>
                                Link
                            </Nav.Link>
                        </Nav>
                        <Form className="d-flex">
                            <Form.Control
                                type="search"
                                placeholder="Search"
                                className="me-2"
                                aria-label="Search"
                            />
                            <Button variant="outline-success">Search</Button>
                        </Form>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    );
}

export default Header;