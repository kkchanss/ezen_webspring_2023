import { Link } from 'react-router-dom';
import styles from '../css/header.css'
import axios from "axios";
import { useState} from "react";


export default function Header( props ){

    let [ login, setLogin] = useState(null);

    // 로그아웃
    const logOut = (e) => {
        axios
            .get('/member/logout')
            .then(r => {
                if(r.data) { // 로그아웃을 성공했으면
                    alert('로그아웃되었습니다.');
                    setLogin(null);
                }
            })
    }

    // - 회원정보 호출 [ 로그인 여부 확인 ]
    axios
        .get('/member/get')
        .then(r => {
            // 2. 만약에 로그인이 되어있으면
            if(r.data != '') {
                setLogin(r.data);
            }

        })

    return <>
        <header>
            <h2> 이젠리액트 </h2>
            <ul>
                <li> <Link to='/example'> 리액트예제 </Link> </li>
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
                            <li> <div onClick={logOut}> 내정보 </div> </li>
                            <li> <div onClick={logOut}> 로그아웃 </div> </li>
                        </>)
                }

            </ul>
        </header>
    </>
}