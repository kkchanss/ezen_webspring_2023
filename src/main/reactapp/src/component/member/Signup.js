import axios from "axios";

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

    return(<>
        <div className="loginContainer">
            <h3> 회원가입 페이지 </h3>
            <form>
                이메일 <input type="text" placeholder={'@포함 7~30글자'} className={'memail'} /> <br/>
                비밀번호 <input type="password" placeholder={'특수문자 조합 5~30글자'} className={'mpassword'} /> <br/>
                비밀번호 확인 <input type="password" placeholder={'특수문자 조합 5~30글자'} className={'mpassword2'} /> <br/>
                이름 <input type="text" placeholder={'이름'} className={'mname'} /> <br/>
                전화번호 <input type="text" placeholder={'연락처'} className={'mphone'}/> <br/>
                <button onClick={onSignup} type={"button"}>회원가입</button>
            </form>
        </div>
    </>)
}