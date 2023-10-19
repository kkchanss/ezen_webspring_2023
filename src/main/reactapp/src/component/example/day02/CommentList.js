import Comment from "./Comment";

export default function CommentList( props ){

    // [예] AJAX가 response 한 데이터
    let response = [
        {name : '유재석', content :'안녕하세요~'} ,
        {name : '강호동', content :'안녕하세요~2'} ,
        {name : '신동엽', content :'안녕하세요~3'}

    ];

    return(<>
        <div className={"commentListBox"}>
            {
                response.map((r) => {
                    return(<Comment name={r.name} content={r.content}/>);
                })
            }
        </div>
    </>);
}