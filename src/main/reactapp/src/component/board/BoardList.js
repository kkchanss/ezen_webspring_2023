/*
    mui : 리액트 전용 라이브러리
        1. 설치
            npm install @mui/material @emotion/react @emotion/styled
            npm install @mui/material @mui/styled-engine-sc styled-components
        2. 예제
            호출할 컴포넌트 상단에 import 하기
            import Button from '@mui/material/Button';

 */

import axios from 'axios';
import {useState, useEffect} from "react";
// ---------------- mui table 관련 컴포넌트 import ----------------------- //
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {Link} from "react-router-dom";
// -------------------------------- //

// -------------------------- mui table sample ------------------------ //


export default function BoardList(props) {

    // 0. 컴포넌트 상태변수 관리
    let [ rows, setRows ] = useState( [] )

    useEffect(() => { // 컴포넌트가 생성될때 1번 실행
        // 1. axios를 이용한 스프링의 컨트롤과 통신
        axios.get('/board').then(r => {
            setRows(r.data); // 응답받은 모든 게시물을 상태변수에 저장
            // setState : 해당 컴포넌트가 업데이트(새로고침/재렌더링/return재실행)
        });
    }, []);



/*    let rows= [
        { bno : 1, btitle : '안녕1', mno : 1, cdate : '2023-11-02', bview : 0},
        { bno : 2, btitle : '안녕2', mno : 1, cdate : '2023-11-02', bview : 0},
        { bno : 3, btitle : '안녕3', mno : 1, cdate : '2023-11-02', bview : 0},
        { bno : 4, btitle : '안녕4', mno : 1, cdate : '2023-11-02', bview : 0},
        { bno : 5, btitle : '안녕5', mno : 1, cdate : '2023-11-02', bview : 0},
        { bno : 6, btitle : '안녕6', mno : 1, cdate : '2023-11-02', bview : 0}
    ];*/

    return(<>

        <h3> 게시물 목록 </h3>
        <a href="/board/write">글 쓰기</a>

        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                {/* 테이블 내용 구역  */}
                <TableHead>
                    <TableRow>
                        <TableCell align="right">번호</TableCell>
                        <TableCell align="right">제목</TableCell>
                        <TableCell align="right">작성자</TableCell>
                        <TableCell align="right">작성일</TableCell>
                        <TableCell align="right">조회수</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow
                            key={row.name}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell align="right">{row.bno}</TableCell>
                            <TableCell align="right"><Link to={"/board/view?bno="+row.bno}>  {row.btitle} </Link></TableCell>
                            <TableCell align="right">{row.mno}</TableCell>
                            <TableCell align="right">{row.cdate}</TableCell>
                            <TableCell align="right">{row.bview}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    </>)
}

{ /* <div>

           <table>
               <tr>
                   <th>번호</th> <th>제목</th> <th>작성자</th>
                   <th>작성일</th> <th>조회수</th>
               </tr>
               {
                   rows.map((row)=>{
                       return(<>
                            <tr>
                                <td>{row.bno}</td> <td>{row.btitle}</td> <td>{row.mno}</td>
                                <td>{row.cdate}</td> <td>{row.bview}</td>
                            </tr>
                       </>)
                   })
               }
           </table>
       </div> */ }