import axios from "axios";
import {useState} from "react";

export default function Signup( props ){

    // 1. 회원가입 버튼을 클릭했을때
    const onSignup = (e) => {
        console.log(e)

        let info = {
            memail : document.querySelector('.memail').value,
            mpassword : document.querySelector('.mpassword').value,
            mname : document.querySelector('.mname').value,
            mphone : document.querySelector('.mphone').value
        }; console.log(info);

        axios
            .post('/member/post', info)
            .then(r => {
                if(r.data) {
                    alert('회원가입 성공');
                    window.location.href= '/login';
                }else {
                    alert('회원가입 실패');
                }
            })
    }

    // 2. 이메일 중복 검사 [ 이메일 입력할때마다. ]
    let [ memail, setMemail] = useState(''); // import { useState } from 'react';
    let [ memailCheck, setMemailCheck ] = useState('');
    const emailInputChange = (e) => {
        console.log('emailInputChange');
        // 1. [기존 방법]
            // let memail = document.querySelector('.memail').value;
            // console.log(memail);
        // 2. [useState]
        let memail = e.target.value; setMemail(memail);
        // -------------------------- //
        // axios <---> ajax 비동기 통신 함수
        // axios.HTTP메소드명('URL', { params : { 속성명 : 값, 속성명 : 값}}) : 쿼리스트링 형식
        // axios.HTTP메소드명('URL', JSON객체) : body 형식
        axios.get('/member/findMemail', {params : {'memail' : memail}})
            .then(r => {
                console.log('axions');
                if(r.data) { setMemailCheck('사용중인 아이디입니다.') } // 중복
                else { setMemailCheck('사용가능한 아이디입니다.') } // 중복 x
            })
    }

    return(<>
        <div className="loginContainer">
            <h3> 회원가입 페이지 </h3>
            <form>
                이메일 <input  type="text" placeholder={'@포함 7~30글자'} className={'memail'} value = { memail } onChange={emailInputChange} /> <br/>
                <div> {memailCheck} </div>
                비밀번호 <input type="password" placeholder={'특수문자 조합 5~30글자'} className={'mpassword'} /> <br/>
                비밀번호 확인 <input type="password" placeholder={'특수문자 조합 5~30글자'} className={'mpassword2'} /> <br/>
                이름 <input type="text" placeholder={'이름'} className={'mname'} /> <br/>
                전화번호 <input type="text" placeholder={'연락처'} className={'mphone'}/> <br/>
                <button onClick={onSignup} type={"button"}>회원가입</button>
            </form>
        </div>
    </>)
}