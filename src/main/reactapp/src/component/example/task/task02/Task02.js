
import styles from './task02.css';
export default function Task02( props ){ { /* 하나의 게시물 구역 */ }
    return(<>
        <div class="todo">
            <div class="tcontent"> {props.content} </div>
            <div class="etcbtns">
                <button onclick="doPut()" type="button">상태변경</button>
                <button onclick type="button">제거하기</button>
            </div>
        </div>
    </>);
}