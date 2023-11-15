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
import BoardView from "./board/BoardView";
import BoardUpdate from "./board/BoardUpdate";
import ProductAdmin from "./product/ProductAdmin";

/* 리액트 훅 라이브러리 */
import { useState, useEffect, useRef, createContext } from 'react';

/* MUI 라이브러리 호출 */
import { useSnackbar } from "notistack";

/* 리액트 Context 변수 */
export const SocketContext = createContext();

export default function Index(props) {

    /* MUI 라이브러리 객체 호출 */
    const { enqueueSnackbar } = useSnackbar();

    // 일반변수 : let 변수명 = 10 : 함수 안에서 선언되었으므로 함수 재실행/재랜더링 될때 초기화 반복적으로 이루어짐
        // 변수 출력시 : 10
    // Ref상태변수 : let 변수명 = useRef(10) : 함수안에서 선언이 되었지만 해당 컴포넌트 업데이트(재랜더링)될때 초기화 안됨
        // Ref상태변수 출력시 : {current : 10}
        // Ref상태변수는 current 속성에 초기값을 저장하고 객체를 가지는 구조
        // 웹소켓은 반복적으로 초기화가 되면 안되니까 일단 변수보다는 useRef에 저장하면
        // 웹소켓은 반복적으로 초기화가 되면 안되니까 일단 변수보단 useRef에 저장하면 좀더 효율적인 메모리 가능
    // 2. 웹소켓
    // =========================== 소켓 start =========================== //
    console.log(createContext());
    // * 웹소켓 객체를 담은 useRef변수 생성
    let clientSocket = useRef(null);
    // 1. 만약에 웹소켓 객체가 비어있으면
    if(!clientSocket.current) {
        // 1. 서버소켓과 연결하기
        clientSocket.current = new WebSocket("ws://localhost:8080/chat");
        // 2. 클라이언트소켓의 각 기능/메소드들의 기능 구현하기
            // 1. 서버소켓과 연동 성공했을때. 이후 행동/메소드 정의
        clientSocket.current.onopen = (e) => { console.log(e) }
            // 2. 서버소켓과 연동 오류났을때. 이후 행동/메소드 정의
        clientSocket.current.onerror = (e) => { console.log(e) }
            // 3. 서버소켓과 연결 끊겼을때. 이후 행동/메소드 정의
        clientSocket.current.onclose = (e) => { console.log(e) }
            // 4. 서버소켓으로부터 메시지를 받았을때. 이후 행동/메소드 정의
        clientSocket.current.onmessage = (e) => {
            console.log(e)
            alert(e.data);
        }
    }

    // =========================== 소켓 end ============================= //

    return(<>
        <div className={"webContainer"}>
            <SocketContext.Provider value={clientSocket}>
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
                        <Route path={'/board/view'} element={ <BoardView /> } />
                        <Route path={'/board/update'} element={ <BoardUpdate /> } />

                        {/* ADMIN */}
                        <Route path={'/admin/product'} element={ <ProductAdmin/>}/>
                    </Routes>
                    <Footer/>
                </BrowserRouter>
            </SocketContext.Provider>
        </div>
    </>)
}