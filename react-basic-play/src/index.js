import React from 'react';  //框架核心包
import ReactDOM from 'react-dom/client'; //专门做渲染相关的包

import './index.css'; //应用的全局样式文件
//import App from '../src/apps/App'; //引入根组件
import AppFun from '../src/apps/AppFun'; // 引入函数组件
//import AppHooks from '../src/apps/AppHooks'; // 引入函数组件Hooks

//渲染根组件APP到id=root的DOM节点上
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <AppFun/>
    /*<AppHooks/>*/
);


