// 1. 리액트 확장자 : jsx, js

    // 1-1. css 속성
    //

// 1. 컴포넌트 문법 원형
import styles from './컴포넌트.css';

export default function CSS컴포넌트( props ){

    // 1. CSS를 객체에 속성[카멜표기법]으로 선언하기
    const cssSytle = {
        backgroundColor: 'red',
        width: '500px',
        height: '100px',
        margin: '0 auto'
    }

    return(<>
        <input/>
        <div style={cssSytle}> CSS 적용하는 방법1 </div>
        <div style={{
            backgroundColor: 'blue',
            width: '500px',
            height: '100px',
            margin: '0 auto'
        }}> CSS 적용하는 방법2 </div>
        <div className="box3"  > CSS 적용하는 방법3 </div>

    </>);
}