import {Link, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

export default function BoardView(props) {

    // 1. HTTP 경로상의 쿼리스트링 매개변수 호출
    const [ searchParams, setSearchParams] = useSearchParams();
    const bno = searchParams.get('bno');

    // 2. 현재 게시물의 정보를 가지는  상태관리 변수
    const [ board, setBoard ] = useState([]);

    // 3. 현재 로그인된 회원의 번호
    const login = JSON.parse(sessionStorage.getItem('login_token'));
    const mno = login != null ? login.mno : null;

    // 3. 삭제 axios
    const onDelete = (e) => {
        axios.delete('/board', {params: {bno:bno}})
            .then( r => {
                if(r.data) { alert('게시물 삭제 성공'); window.location.href='/board/list'}
                else alert('게시물 삭제 실패')
            })
    }

    // 4. 개별 게시물 axios
    useEffect(() => {
        axios.get('/board/one', { params : { bno : bno}}).then(r => {
            setBoard(r.data);
            console.log(r.data);
        })
    }, [])

    return(<>
        <div>
            <h3> 개별 게시물 {bno} </h3>
            <div> {board.btitle} </div>
            <div> {board.bcontent} </div>

            {/* 삭제와 수정은 본인(본인확인)만 가능 */}
            {/* 삼항연사자를 통한 컴포넌트 출력 */}
            {
                board.mno == mno?
                (<>
                    <button type="button" onClick={onDelete}>삭제</button>
                    <Link to={'/board/update?bno='+bno}> <button type="button">수정</button> </Link>
                </>)
                : (<></>)
            }
        </div>
    </>)
}