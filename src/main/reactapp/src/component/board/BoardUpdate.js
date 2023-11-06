import {Link, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

export default function BoardUpdate(props) {
    const [ searchParams, setSearchParams] = useSearchParams();
    const bno = searchParams.get('bno');

    const [ board, setBoard ] = useState([]);

    useEffect(() => {
        axios.get('/board/one', { params : { bno : bno}}).then(r => {
            setBoard(r.data);
            console.log(r.data);
        })
    }, [])

    return(<>
        <div>
            <h3> 개별 게시물 {bno} </h3>
            제목 : <input type="text" value={board.btitle}/>
            내용 : <input type="text" value={board.bcontent}/>
            <button type="button">수정</button>
        </div>
    </>)
}