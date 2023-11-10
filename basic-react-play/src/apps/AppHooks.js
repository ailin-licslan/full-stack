//告别难以理解的class
//解决业务逻辑难以拆分问题
//使状态逻辑复用变得简单可行
//函数组件在设计思想上更加契合react的理念


/**
 * useState
 * 1.导入useState函数 react
 * 2.执行这个函数并传入初始值  必须在函数组件中
 * 3.[数据 & 数据修改的方法]
 * 4.使用数组 修改数据
 * */


import {useState} from "react";

function AppHooks() {

    //替代类组件中的  state = {count:0}  count ==> 属性  setCount ==> 修改数据的方法
    const [count, setCount] = useState(0)

    return (
        <div>
            <button onClick={() => setCount(count + 1)}>数字: {count}</button>
        </div>
    )

}

export default AppHooks