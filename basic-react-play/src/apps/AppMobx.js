//状态管理  Mobx是一个独立的响应式库  可以独立于任何UI使用  可以和react绑定来使用


import {countStore} from "../store/counter";
import {observer} from "mobx-react-lite";

function AppMobx() {

    return <div>
        <button onClick={countStore.addCount}>mobx的使用</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        {countStore.count}
    </div>
}

export default observer(AppMobx)