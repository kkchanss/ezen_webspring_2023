import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 라우터매개변수 from "./component/example/day06/1_라우터매개변수";
// import 라우터컴포넌트 from "./component/example/day03/1_라우터컴포넌트";
// import 컴포넌트1 from "./component/example/day01/1_컴포넌트";
import Index from "./component/Index";
import 상태관리컴포넌트 from "./component/example/day05/1_상태관리컴포넌트";
import 생명주기컴포넌트 from "./component/example/day05/2_생명주기컴포넌트";

const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(<React.StrictMode><App /></React.StrictMode>);
root.render(< Index />);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
