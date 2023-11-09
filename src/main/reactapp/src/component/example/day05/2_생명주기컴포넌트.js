// ------------------------- JS 형식 ------------------------ //
import {useEffect, useState} from "react";

export default function 생명주기컴포넌트(){

    // 1. useState 함수를 이용한 초기값 0으로 하는 [변수, 수정함수] 리턴 받음
    let [ value, setValue ] = useState(0);
    const valueUpdate = (e) => { setValue(++value); }

    let [ value2, setValue2] = useState(0);
    const value2Update = (e) => { setValue2(++value2); }

    // 2. 컴포넌트 생명주기 1.탄생 / 2.업데이트 / 3.제거
        // 1. 컴포넌트 탄생 / 업데이트 <= 컴포넌트가 첫 실행과 업데이트할때 실행되는 함수
    useEffect(() => {
      console.log('[1]Effect 실행');
    });
        // 2. 컴포넌트 탄생 <= 컴포넌트가 첫 실행될때만 실행되는 함수
    useEffect(() => {
        console.log('[2]Effect 실행');
    }, []);
        // 3.
    useEffect(() => {
        console.log('[3]Effect 실행');
    }, [value]);
    // 화살표 함수 () => {}

    // ------------------- JSX 형식 START --------------- //
    return(<>
        <div> {value} </div>
        <button onClick={ valueUpdate }> valueUpdate </button>
        <div> {value2} </div>
        <button onClick={ value2Update }> value2Update </button>
    </>);
    // ------------------- JSX 형식 END  --------------- //

}
// ------------------------- JS 형식 ------------------------ //