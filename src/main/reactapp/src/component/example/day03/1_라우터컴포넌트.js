/*
    컴포넌트 만들기
        - 파일명 : 아무거나.js 혹은 아무거나.jsx [ 권장 : 컴포넌트명과 동일]
        - 컴포넌트 원형
            - 컴포넌트명 : 첫글자는 대문자 [ 무조건, 카멜 표기법 ]
            export default function 컴포넌트(props) {
                return(<></>)
            }
        - 설치
            1. https://www.npmjs.com/


 */

import { BrowserRouter, Routes, Route, Link } from "react-router-dom"; // npm i react-router-dom 설치 후
import 컴포넌트1 from '../day01/1_컴포넌트'; // 다른 폴더에 있는 컴포넌트 호출
import 컴포넌트2 from "../day01/2_컴포넌트";
import 컴포넌트3 from "../day01/3_컴포넌트";
import 컴포넌트4 from "../day01/4_컴포넌트";

export default function 라우터컴포넌트(props) {
    return(<>
        <BrowserRouter> { /* 브라우저 라우터 시작 */ }
            <고정컴포넌트 /> { /* BrowserRouter 안에 있고 Routes 밖에 있는 컴포넌트 */ }
            <Routes> { /* 화면잉 전환되는 컴포넌트들의 URL 정의 공간 */ }
                { /* http://localhost:3000/day01/컴포넌트1 */ }
                 <Route path='/day01/컴포넌트1' element={ <컴포넌트1/>} /> { /* 컴포넌트로 연결할 가상 URL 경로 정정 */ }
                <Route path='/day01/컴포넌트2' element={ <컴포넌트2/>} /> { /* 컴포넌트로 연결할 가상 URL 경로 정정 */ }
                <Route path='/day01/컴포넌트3' element={ <컴포넌트3/>} /> { /* 컴포넌트로 연결할 가상 URL 경로 정정 */ }
                <Route path='/day01/컴포넌트4' element={ <컴포넌트4/>} /> { /* 컴포넌트로 연결할 가상 URL 경로 정정 */ }
            </Routes>
        </BrowserRouter>

    </>)
}

function 고정컴포넌트(props) {
    return(<>
        <div> { /* a태그는 페이지 리로드 */ }
            <a href={'/day01/컴포넌트1'}> 컴포넌트1 </a>
            <a href={'/day01/컴포넌트2'}> 컴포넌트2 </a>
            <a href={'/day01/컴포넌트3'}> 컴포넌트3 </a>
            <a href={'/day01/컴포넌트4'}> 컴포넌트4 </a>
        </div>

        <div> { /* Link컴포넌트는 페이지 리로드x */ }
            <Link to={'/day01/컴포넌트1'}> 컴포넌트1 </Link>
            <Link to={'/day01/컴포넌트2'}> 컴포넌트2 </Link>
            <Link to={'/day01/컴포넌트3'}> 컴포넌트3 </Link>
            <Link to={'/day01/컴포넌트4'}> 컴포넌트4 </Link>
        </div>
    </>)
}