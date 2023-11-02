/*
    Index : 여러 컴포넌트들을 연결하는 최상위 컴포넌트
        - 가상URL 정의해서 컴포넌트를 연결하는 공간/컴포넌트
 */

import { BrowserRouter, Routes, Link, Route } from "react-router-dom";
import Main from "./Main";
import Header from "./Header";
import Footer from "./Footer";
import 컴포넌트1 from "./example/day01/1_컴포넌트";
import 컴포넌트2 from "./example/day01/2_컴포넌트";
import 컴포넌트3 from "./example/day01/3_컴포넌트";
import 컴포넌트4 from "./example/day01/4_컴포넌트";
import CSS컴포넌트 from "./example/day02/1.CSS적용컴포넌트";
import CommentList from "./example/day02/CommentList";
import ExampleList from "./example/ExampleList";
import 도서목록 from "./example/task/task01/과제1_도서목록";
import Task02List from "./example/task/task02/Task02List";
import Login from "./member/Login";
import Signup from "./member/Signup";
import Axios컴포넌트 from "./example/day04/1_Axios컴포넌트";
import Info from "./member/Info";

/* Board import */
import BoardList from "./board/BoardList";
import BoardWrite from "./board/BoardWrite";

export default function Index(props) {
    return(<>
        <div className={"webContainer"}>
            <BrowserRouter>
                <Header/>
                <Routes>
                    {/* MAIN */}
                    <Route path={'/'} element={< Main />} />

                    {/* EXAMPLE*/}
                    <Route path={'/example'} element={ < ExampleList />} />
                        <Route path={'/example/day01/컴포넌트1'} element={ < 컴포넌트1 />} />
                        <Route path={'/example/day01/컴포넌트2'} element={ < 컴포넌트2 />} />
                        <Route path={'/example/day01/컴포넌트3'} element={ < 컴포넌트3 />} />
                        <Route path={'/example/day01/컴포넌트4'} element={ < 컴포넌트4 />} />
                        <Route path={'/example/day02/CSS컴포넌트'} element={ < CSS컴포넌트 />} />
                        <Route path={'/example/day02/CommentList'} element={ < CommentList />} />
                        <Route path={'/example/task/Task02List'} element={ < Task02List />} />
                        <Route path={'/example/task/도서목록'} element={ < 도서목록 />} />
                        <Route path={'/example/day04/Axios컴포넌트'} element={ < Axios컴포넌트 /> } />
                    { /* MEMBER */ }
                    <Route path={'/login'} element={ < Login />} />
                    <Route path={'/signup'} element={ < Signup />} />
                    <Route path={'/info'} element={ < Info />} />

                    { /* BOARD */ }
                    <Route path={'/board/list'} element={ <BoardList />} />
                    <Route path={'/board/write'} element={ <BoardWrite /> } />
                </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    </>)
}