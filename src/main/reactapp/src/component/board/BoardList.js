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
import './BoardList.css';
// -------------------------------- //

// -------------------------- mui table sample ------------------------ //
// -------------------------------------------------------------------- //
// ----------------------------- Pagination --------------------------- //
import usePagination from '@mui/material/usePagination';
import {Pagination} from "@mui/material";

export default function BoardList(props) {

    // 0. 컴포넌트 상태변수 관리 [스프링으로부터 전달받은 객체]
    let [ pageDto, setPageDto ] = useState({
        boardDtos : [],
        totalPages : 0,
        totalCount : 0
    } );
    // 0. 스프링에게 전달할 객체
    const [ pageInfo, setPageInfo ] = useState( {
        page : 1,
        key : 'btitle',
        keyword : '',
        size : 5
    }); console.log(pageInfo)

    // const [ searchState, setSearchState ] = useState( false );

    const getBoard = (e) => {
        axios.get('/board', {params : pageInfo}).then(r => {
            // r.data : PageDto
            // r.data.boardDtos : PageDto 안에 List<BoardDto>
            setPageDto(r.data); // 응답받은 모든 게시물을 상태변수에 저장
            // setState : 해당 컴포넌트가 업데이트(새로고침/재렌더링/return재실행)
        });
    }

    // 실행조건 : 최초, 페이지 전환됐을때
    useEffect(() => { // 컴포넌트가 생성될때 1번 실행
        // 1. axios를 이용한 스프링의 컨트롤과 통신
        getBoard();
    }, [pageInfo.page, pageInfo.size]);

    // 2. 페이지 번호 클릭했을때
    const onPageSelect = (e, value) => {
        pageInfo.page = value; // 클릭한 페이지번호로 변경
        setPageInfo({...pageInfo}); // 새로고침 [ 상태변수의 주소값이 바뀌면 재랜더링]
    }

    // 3. 검색 버튼 눌렀을때
    const onSearch = (e) => {
        setPageInfo({...pageInfo, page : 1});
        getBoard();
    }



    return(<>
        <h3> 게시물 목록 </h3>
        <a href="/board/write">글 쓰기</a>
        <p> page : { pageInfo.page } totalCount : {pageInfo.totalPAge} </p>
        <select
            value={pageInfo.size}
            onChange={(e)=>{setPageInfo({...pageInfo, size : e.target.value})}}
        >
            <option value="5"> 5 </option>
            <option value="10"> 10 </option>
            <option value="15"> 15 </option>
        </select>

        {
            pageInfo.keyword != ''?
                (<> <button type={"button"}
                    onClick={(e) => {window.location.href="/board/list"}}> 검색제거 </button> </>)
                :
                (<></>)

        }


        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                {/* 테이블 내용 구역  */}
                <TableHead>
                    <TableRow>
                        <TableCell style={{width : '5%'}} align="center">번호</TableCell>
                        <TableCell style={{width : '60%'}} align="center">제목</TableCell>
                        <TableCell style={{width : '15%'}} align="center">작성자</TableCell>
                        <TableCell style={{width : '15%'}} align="center">작성일</TableCell>
                        <TableCell style={{width : '5%'}} align="center">조회수</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {pageDto.boardDtos.map((row) => (
                        <TableRow
                            key={row.name}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell align="center">{row.bno}</TableCell>
                            <TableCell align="left"><Link to={"/board/view?bno="+row.bno}>  {row.btitle} </Link></TableCell>
                            <TableCell align="center">{row.memail}</TableCell>
                            <TableCell align="center">{row.cdate}</TableCell>
                            <TableCell align="center">{row.bview}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
        <div style={{display:"flex", flexDirection:"column", alignItems:"center", margin:'10px'}}>
            {/* count : 전체페이지수 onChange : 페이지번호를 클릭/변경했을때 이벤트 */}
            <Pagination count={pageDto.totalPages} page={pageInfo.page} onChange={onPageSelect}/>

            {/* 검색 */}
            <div style = {{margin : "20px"}}>
                <select
                    value={pageInfo.key}
                    onChange={(e)=>{setPageInfo({...pageInfo, key : e.target.value})}}
                >
                    <option value={"btitle"}>제목</option>
                    <option value={"bcontent"}>내용</option>
                </select>
                <input type="text"
                       value={pageInfo.keyword}
                       onChange={(e)=>{setPageInfo({...pageInfo, keyword : e.target.value})}}
                />
                <button type="button" onClick={onSearch}>검색</button>
            </div>
        </div>
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