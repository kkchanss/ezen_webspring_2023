import Task02 from "./Task02";

export default function Task02List(props) {

    // [예] AJAX가 response 한 데이터
    let response = [
        {content :'리액트 배우기'} ,
        {content :'자바 배우기'} ,
        {content :'파이썬 배우기'},
        {content :'C언어 배우기'}
    ];

    return(<>
        <div className="todowrap">
            <h1> 나만의 할일 목록 </h1>
            <div className="todo_top">
                <input className="input_tcontent" type="text"/>
                <button onClick="doPost()" type="button"> 등록 </button>
            </div>
                <div className="todo_bottom">
                {
                    response.map((r) => {
                        return(<Task02 content={r.content}/>);
                    })
                }
            </div>
        </div>
    </>);

}