import { Link } from 'react-router-dom';
import styles from '../css/header.css'
import axios from "axios";
import {useEffect, useState} from "react";


export default function Header( props ){

    let [ login, setLogin] = useState(null);

    // 로그아웃
    const logOut = (e) => {
        axios
            .get('/member/logout')
            .then(r => {
                if(r.data) { // 로그아웃을 성공했으면
                    alert('로그아웃되었습니다.');
                    sessionStorage.removeItem('login_token')
                    setLogin(null);
                }
            })
    }

    // - 회원정보 호출 [ 로그인 여부 확인 ]
    // --------------------------- 컴포넌트 생성될때 1번 -------------------- //
    useEffect(() => {
        axios
            .get('/member/get')
            .then(r => {
                console.log('login.get')
                // 2. 만약에 로그인이 되어있으면
                if(r.data !== '') {
                    // 브라우저 세션/쿠키 // 브라우저 F12 -> application -> localStorage / sessionStorage
                        // localStorage
                        // 모든 브라우저 탭/창 공유 [페이지 전환 해도 유지], 브라우저가 꺼져도 유지, 자동로그인 기능, 로그인 상태 유지
                        // vs
                        // sessionStorage
                        // 페이지 전환 해도 유지, 탭/창 종료되면 사라짐, 로그인 여부
                        // 세션/쿠키 저장 : .setItem(key, value)
                        // 세선/쿠키 호출 : .getItem(key)
                    sessionStorage.setItem('login_token', JSON.stringify(r.data));
                    setLogin(JSON.parse(sessionStorage.getItem('login_token')));
                }

            })
    }, []);



    return <>
        <header>
            <h2> 이젠리액트 </h2>
            <ul>
                <li> <Link to='/example'> 리액트 예제 </Link> </li>
                <li> <Link to='/'> TODO </Link> </li>
                <li> <Link to='/'> 비회원게시판 </Link> </li>
                <li> <Link to='/'> 회원게시판 </Link> </li>

                {/* 삼항연산자 */}
                {
                    login == null
                        ? (<>
                            <li> <Link to='/login'> 로그인 </Link> </li>
                            <li> <Link to='/signup'> 회원가입 </Link> </li>
                        </>)
                        : (<>
                            <li> { login.memail }님 </li>
                            <li> <a href={'/info'}> 내정보 </a> </li>
                            <li> <div onClick={logOut}> 로그아웃 </div> </li>
                        </>)
                }

            </ul>
        </header>
    </>
}