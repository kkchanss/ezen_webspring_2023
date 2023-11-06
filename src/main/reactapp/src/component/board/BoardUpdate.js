import {Link, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

export default function BoardUpdate(props) {
    const [ searchParams, setSearchParams] = useSearchParams();
    const bno = searchParams.get('bno');

    const [ board, setBoard ] = useState(null);

    useEffect(() => {
        axios.get('/board/one', { params : { bno : bno}}).then(r => {
            setBoard(r.data);
            console.log(r.data);
        })
    }, [])

    const btitleInputChange = (e) => {
        let btitleInput = e.target.value;
        setBoard({...board, btitle : btitleInput})
    }

    const bcontentInputChange = (e) => {
        let bcontentInput = e.target.value;
        setBoard({...board, bcontent : bcontentInput})
    }

    const onUpdate = (e) => {

        const boardForm = document.querySelectorAll('.boardForm')[0]
        const boardFormData = new FormData(boardForm);

        boardFormData.set('bno',bno);

        axios.put('/board', boardFormData).then(r => {
            if(r){
                alert('수정 완료!');
                window.location.href='/board/list';
            }else{
                alert('수정 실패...');
            }
        })
    }

    return(<>
        <div>
            <h3> 개별 게시물 {bno} </h3>
            <form className={"boardForm"}>
                제목 : <input type="text" value={board!= null ? board.btitle : ''} name={"btitle"}
                        onChange={(e) => {btitleInputChange(e)}}
                        />
                내용 : <textarea placeholder={'내용'} name={"bcontent"}
                               onChange={ (e) => {bcontentInputChange(e)}}></textarea> <br/>

                <button type="button" onClick={onUpdate}>수정</button>
            </form>
        </div>
    </>)
}