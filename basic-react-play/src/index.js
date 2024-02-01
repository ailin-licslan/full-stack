import React from 'react';  //框架核心包
import ReactDOM from 'react-dom/client'; //专门做渲染相关的包

import './index.css'; //应用的全局样式文件

/**
 * 特别说明 下面的每个注释单独放开 就可以启动每个学习示例了  ^_^
 * */

import App from '../src/apps/App'; //引入根组件

//import AppFun from '../src/apps/AppFun'; // 引入函数组件

//import AppRouter from "./apps/AppRouter"; // 引入路由组件

//import AppHooks from '../src/apps/AppHooks'; // 引入函数组件Hooks

//import AppMobx from "./apps/AppMobx"; // 引入状态管理组件

//import AppMobxComputed from "./apps/AppMobxComputed";

import AppMobxModule from "./apps/AppMobxModule";

//渲染根组件APP到id=root的DOM节点上
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

    <App/>
    /*<AppFun/>*/
    /*<AppHooks/>*/
    /*<AppRouter/>*/
    /*<AppMobx/>*/
    /*<AppMobxComputed/>*/
    // <AppMobxModule/>
);


