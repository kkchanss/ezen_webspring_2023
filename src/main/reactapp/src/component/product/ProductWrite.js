import axios from "axios";

// [소켓1]
import { useContext } from "react";
import { SocketContext } from "../Index";

export default function ProductWrite(props){

    // [소켓2] 상위 컴포넌트에 있는 context 호출
    const clientSocket = useContext(SocketContext).current;

    console.log(clientSocket);

    // 1. 제품 등록
    const onProductAdd = (e)=>{
        let productForm = document.querySelectorAll('.productForm')[0];
        let productFormData = new FormData(productForm);

        console.log(productFormData);

        axios.post("/product", productFormData).then(r => {
            if(r.data) {
                // [소켓3] : 서버에게 메시지 보내기
                clientSocket.send('새로운 제품이 등록되었습니다');
                productForm.reset();
            }
            else alert("등록 실패")
        })
    }

    return(<>
        <div style={{width:'300px', margin : '0 auto'}}></div>
        <h3>제품 등록</h3>
        <form className={"productForm"}>
            <select name={"pcno"}>
                { props.categoryList.map((c)=> {
                        return (<>
                            <option value={c.pcno}>{c.pcname}</option>
                        </>)
                    })
                }
            </select>
            <input type={"text"} name={"pname"} placeholder={"제품명"}/> <br/>
            <textarea name={"pcomment"} placeholder={"제품설명"}></textarea> <br/>
            <input type={"text"} name={"pprice"} placeholder={"제품가격"}/> <br/>
            <input type={"text"} name={"pstock"} placeholder={"초기재고"}/> <br/>
            <input type={"file"} name={"fileList"} multiple /> <br/>
            <button  type={"button"} onClick={onProductAdd}>등록</button>
        </form>

    </>)
}