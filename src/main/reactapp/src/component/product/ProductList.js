import {useEffect, useState} from "react";
import axios from "axios";



export default function ProductList(props){

    let [productList, setProductList] = useState([]);

    // 1. 제품 호출 [컴포넌트 최초 실행될때]
    const onProductAll = (e) => {
        axios.get('/product')
            .then(r => {
                console.log(r.data);
                setProductList(r.data);
            })
    }

    useEffect((e) => {
        onProductAll();
    }, []);

    return(<>
        <h3>제품 목록</h3>
        <table>
            <tr>
                <th>제품 번호</th> <th>대표이미지</th> <th>카테고리명</th>
                <th>제품명</th> <th>제품가격</th> <th>상태</th>
                <th>재고</th> <th>비고</th>
            </tr>
        </table>

        {
            productList.map(p => {
                return (<>
                    <tr>
                        <td>{p.pno}</td>
                        <td>
                            <img style={{ width: '100%'}}
                                 src={"http://localhost:8080/static/media/"+p.imgList[0].uuidFile}/>
                        </td>
                        <td>{p.categoryDto.pcname}</td>
                        <td>{p.pname}</td>
                        <td>{p.pprice}</td>
                        <td>{p.pstate}</td>
                        <td>{p.pstock}</td>
                        <td>{p.pcomment}</td>
                    </tr>
                </>)
            })
        }
    </>)
}