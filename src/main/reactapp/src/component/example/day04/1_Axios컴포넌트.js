/*
    - HTTP 기반의 비동기 통신 함수
    Axios(React 라이브러리) <-----> AJAX(jquery라이브러리)
        1. 설치
            1. 터미널 [ alt+f12 ]
            2. 리액트 프로젝트 폴더내 설치
            3. npm i axios
        2. 컴포넌트에서 axios 사용하기
            1. import
                파일 상단에 import axios from 'axios';
            2. ajax vs axios
                $.ajax({ url : '', method: '',  })
        3.
            - JSON 객체를 HTTP BODY 형식으로 보낼때
            axios
                .methodType(url, {JSON객체})
                .then( 반환매개변수 => {})

 */
import axios from 'axios';
export default function Axios컴포넌트(props) {

    // 컴포넌트(함수) 안에서 함수 정의하기
    // 1. 기본함수
    function 함수1(e) { console.log(e) }

    // 2. 화살표함수를 저장하는 함수
    const 함수2 = (e) => { console.log(e) }

    // 3. 화살표함수를 매개변수 받기
    const 함수3 = (e, data) => { console.log(e); console.log(data); }
    // -------------------------- AXIOS ------------------------------ //
    // 1. GET
    function doGet() {
        // axios.메소드(url).then(반환매개변수 => {})
        axios
            .get('https://jsonplaceholder.typicode.com/posts')
            .then( response => { console.log(response); } )
        axios
            .get('https://jsonplaceholder.typicode.com/posts/1')
            .then( result => { console.log(result); } )
        axios
            .get('https://jsonplaceholder.typicode.com/comments?postId=1')
            .then( r => {console.log(r);})
        axios
            .get('https://jsonplaceholder.typicode.com/comments', {params : {'postId' : 1}})
            .then( r => { console.log(r)})
    }
    // 2. POST
    function doPost() {
        // axios.메소드(url).then(반환매개변수 => {})
        axios
            .post('https://jsonplaceholder.typicode.com/posts')
            .then( r => { console.log(r.data); } )
    }
    // 3. PUT
    function doPut() {
        let updateInfo = { id : 1, title : 'updateFoo', body : 'updateBar', userId : 1}
        axios
            .put('https://jsonplaceholder.typicode.com/posts/1', updateInfo)
            .then( r => { console.log(r.data); } )
    }
    // 4. DELETE
    function doDelete() {
        axios
            .delete('https://jsonplaceholder.typicode.com/posts/1')
            .then( r => { console.log(r.data); } )
    }
    return(<>
        <h3> AXIOS 테스트 </h3>
        { /* JSX에서 이벤트속성 [ 1. 이벤트명(카멜표기법) 2. { 함수명 } ] */ }
        <button type={ "button"} onClick={함수1} >함수1</button>
        <button type={ "button"} onClick={함수2} >함수2</button>
        <button type={ "button"} onClick={ e => 함수3(e,3)} >함수3</button>

        <button type={ "button"} onClick={doGet}>doGet AXIOS</button>
        <button type={ "button"} onClick={doPost}>doPost AXIOS</button>
        <button type={ "button"} onClick={doPut}>doPut AXIOS</button>
        <button type={ "button"} onClick={doDelete}>doDelete AXIOS</button>
    </>)
}