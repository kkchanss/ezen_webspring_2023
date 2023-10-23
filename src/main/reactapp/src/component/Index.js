/*
    Index : 여러 컴포넌트들을 연결하는 최상위 컴포넌트
 */

import { BrowserRouter, Routes, Link, Route } from "react-router-dom";
import Main from "./Main";
import Header from "./Header";
import Footer from "./Footer";

export default function Index(props) {
    return(<>
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path={'/'} element={< Main />} />
            </Routes>
            <Footer/>
        </BrowserRouter>
    </>)
}