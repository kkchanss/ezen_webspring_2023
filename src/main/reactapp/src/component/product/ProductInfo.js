import { BarChart } from '@mui/x-charts/BarChart';
import {PieChart} from "@mui/x-charts";
import {useEffect, useState} from "react";
import axios from "axios"; // npm install @mui/x-charts

export default function ProductInfo(props){

    const [barChartData, setBarChartData] = useState([]);

    // 1. 막대차트에 필요한 데이터 요청 axios
    const getBarChart = (e) => {
        axios.get('/product/barchart').then(r => {
            console.log(r.data);
            setBarChartData(r.data);
        })
    }
    useEffect(() => {
        getBarChart();
    }, []);

    // 2. 원형차트에 필요한 데이터 요청 axios
    const getPieChart = (e) => {
        axios.get('/product/piechart').then(r => {

        })
    }

    useEffect(() => {
        getPieChart();
    }, [])

    return(<>
        <div style={{display : 'flex'}}>
            <div>
                <h3> 제품별 재고 수 (막대차트) </h3>
                {/* barChartData에 데이터가 있을때만 차트 표시 / 0이면 차트 표시x */}
                {
                    barChartData.length !== 0 ?
                    (<>
                        <BarChart
                            xAxis={[
                                {
                                    id: 'barCategories',
                                    data: barChartData.map((p) => { return p.pname}),
                                    scaleType: 'band',
                                }

                            ]}
                            series={[
                                {
                                    data: barChartData.map((p => {return p.pstock})),
                                },
                            ]}
                            width={500}
                            height={300}
                        />
                    </>) : (<></>)
                }
            </div>

            <div>
                <h3> 카테고리별 제품 수 (원형차트) </h3>
                <PieChart
                    series={[
                        {
                            data: [
                                { id: 0, value: 10, label: 'series A' },
                                { id: 1, value: 15, label: 'series B' },
                                { id: 2, value: 20, label: 'series C' },
                            ],
                        },
                    ]}
                    width={500}
                    height={300}
                />
            </div>

        </div>

        <div>

        </div>
    </>)
}