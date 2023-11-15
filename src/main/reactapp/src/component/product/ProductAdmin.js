/* 1. mui Tabs[탭 메뉴] import */
import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import {TabContext, TabList, TabPanel} from "@mui/lab";
import CategoryWrite from "./CategoryWrite";
import ProductInfo from "./ProductInfo";
import ProductList from "./ProductList";
import ProductWrite from "./ProductWrite";
import axios from "axios";
import {useState} from "react";

export default function ProductAdmin(props){
    // 2.
    const [value, setValue] = useState('1');

    // 3.
    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    // 0. 출력할 카테고리 목록
    const [categoryList, setCategoryList] = useState([]);

    // 2. 카테고리 출력
    const printCategory = (e) => {
        axios.get("/product/category").then(r => {
            console.log(r.data)
            setCategoryList(r.data)
        })
    }

    return(<>
        {/* 4. */}
        <TabContext value={value}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                {/* 탭에 포함된 제목들 */}
                <TabList onChange={handleChange}
                         aria-label="lab API tabs example"
                         indicatorColor="secondary"
                         centered
                >
                    <Tab label="카테고리 등록" value="1" /> { /* value : 탭순서번호(식별) */}
                    <Tab label="제품 등록" value="2" />
                    <Tab label="제품 목록" value="3" />
                    <Tab label="제품 상태" value="4" />
                </TabList>
            </Box>
            {/* 탭 선택시 출력되는 내용물 */}
            <TabPanel value="1"><CategoryWrite categoryList={categoryList} printCategory={printCategory} /></TabPanel>
            <TabPanel value="2"><ProductWrite categoryList={categoryList} printCategory={printCategory} /></TabPanel>
            <TabPanel value="3"><ProductList/></TabPanel>
            <TabPanel value="4"><ProductInfo/></TabPanel>
        </TabContext>
    </>)
}