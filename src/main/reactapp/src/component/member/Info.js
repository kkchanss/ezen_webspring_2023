import {useEffect, useState} from "react";
import axios from "axios";

export  default function Info(props) {

    // axios로부터 전달받은 로그인 된 회원정보를 상태변수에 저장
    const [ member, setMember] = useState(null); // { 객체 }

    // 로그인 정보를 호출해서 출력하기.
    useEffect(() => {
        axios.get('/member/get').then(r => {
            setMember(r.data);
        })
    }, []);

    // 1. 이름 입력 했을때. 상태 변경.
    // function mnameInputChange(e) {}
    const mnameInputChange = (e) => {
        console.log(e.target.value); // onChange 이벤트를 실행한 주체자[e.target]의 값 호출 [.value]
        let mnameInput = e.target.value;
        setMember({...member, mname : mnameInput});
    }

    // 2. 전화번호 변경 [ 이벤트 속성 직접 처리 ]
    const [ newPassword, setNewPassword] = useState({mpassword:'', mpassword2:''});
    const onUpdate = (e) => {

        // 기존 비밀번호가 일치한지 유효성검사[x] ---> 백엔드 해야할 일
        // 새로운 비밀번호 2개 일치한지 유효성 검사 ---> 프론트 해야할 일
        let mpassword = document.querySelector('.mpassword').value;
        let mpassword2 = document.querySelector('.mpassword2').value;
        if(newPassword.mpassword != newPassword.mpassword2) { return; }
        //setMember({...member, mpassword : newPassword.mpassword})
        let info = {
            mno : member.mno,
            memail : member.memail,
            mpassword : newPassword.mpassword,
            mphone : member.mphone
        }
        console.log(info);

        axios
            .put('/member/put', info)
            .then(r => {
                if(r.data) {
                    alert('수정 완료');
                    window.location.href = '/';
                }
            })
    }

    // 3. 회원탈퇴 [ view 요청 --> springcontroller전달 --> service 전달 -> entity조작 -> 레코드 삭제 ]
    const onDelete = (e) => {
        if(window.confirm('정말 탈퇴하시겠습니까?')) { // 확인 버튼 눌렀을때
            axios                                     // 상태변수에 있는 회원번호를 쿼리스트링 형식으로 전달
                .delete('/member/delete', { params : { mno : member.mno}})
                .then( r => {
                        if(r.data) {
                            alert('탈퇴 성공');
                            sessionStorage.removeItem('login_token');
                            window.location.href = "/";
                        }
                    }
                )
        }

    }

/*    const memberAway = (e) => {

    }*/

/*    const mphoneInputChange = (e) => {
        let mphoneInput = e.target.target;
        setMember({...member, mphone : mphoneInput});
        // 요약 : setMember({...member, mphone : e.target.target});
    }*/

    return(<>
        <div className="loginContainer">
            <h3> ReactEzen Info </h3>
            <form>
                회원 등급 <div> { member != null ? member.mrole : ''} </div>
                이메일 <input disabled  type="text" placeholder={'@포함 7~30글자'} className={'memail'} value={member != null ? member.memail : ''} /> <br/>
                새 비밀번호 <input type="password" placeholder={'특수문자 조합 5~30글자'} className={'mpassword'}
                          value={newPassword.mpassword}
                          onChange={ (e) => { setNewPassword({...newPassword, mpassword: e.target.value})}}
                    /> <br/>
                새 비밀번호 확인 <input type="password" placeholder={'특수문자 조합 5~30글자'} className={'mpassword2'}
                         value={newPassword.mpassword2}
                         onChange={ (e) => { setNewPassword({...newPassword, mpassword2: e.target.value})}}
                    /> <br/>
                이름 <input type="text" placeholder={'이름'} className={'mname'}
                          value={member != null ? member.mname : ''} onChange={ mnameInputChange } /> <br/>
                전화번호 <input type="text" placeholder={'연락처'} className={'mphone'}
                    value={member != null ? member.mphone : ''}
                    onChange={
                        (e) => { setMember({...member, mphone : e.target.value})}
                    }/> <br/>
                <button onClick={onUpdate} type={"button"}>정보 수정</button>
                <button onClick={onDelete} type={"button"}>회원 탈퇴</button>
            </form>
        </div>
    </>)
}

/*
        // setMember(mnameInput); // 문제점 : mname만 들어가짐
        // member 상태변수에 전체 수정이 아닌 member 상태변수내 특정 속성만 변경 해야함..

        //let changeMember = member; // 기존 객체를 새로운 객체?에 대입
        //changeMember.mname = mnameInput; // 객체의 특정 속성만 새로운 값 대입
        //setMember(changeMember); // 수정된 새로운 객체를 상태변수에 대입
            // 문제점 : setState()는 상태변수의 주소값이 변경될 때 반응/렌더링

        //let changeMember = {...member}; // 기존 객체를 새로운 객체?에 대입
            // !! : 1. 객체 복사 방법, 2. 배열 복사 방법 (map, ...)
            // ... Spread Operator : 얕은 복제
            // { ...객체명 }
            // { ...객체명, 속성명 : 값 } // 복사할때 해당 속성
        //changeMember.mname = mnameInput; // 객체의 특정 속성만 새로운 값 대입
        //setMember(changeMember); // 수정된 새로운 객체를 상태변수에 대입
 */