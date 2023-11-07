//mobx 多个组件业务统一处理

//1.拆分count & List 模块 每个模块定义自己独立的state actions

//2.在store/index.js中导入拆分之后的模块 进行模块组合

//3.使用React 的 useContext机制导出  useStore 方法供业务 组件统一使用

import {useStore} from "../store";
import {observer} from "mobx-react-lite";

function AppMobxModule() {

    const rootStore = useStore()
    return <div>

        {rootStore.countStore.count}
        <button onClick={rootStore.countStore.addCount}>+1</button>

    </div>

}

export default observer(AppMobxModule)