//状态管理  Mobx是一个独立的响应式库  可以独立于任何UI使用  可以和react绑定来使用


import {countStore} from "../store/counter";
import {observer} from "mobx-react-lite";

function AppMobxComputed() {

    return <div>
        <button onClick={countStore.addCount}>mobx的使用</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        {countStore.count}
        &nbsp;&nbsp;&nbsp;&nbsp;
        {countStore.filterList.join("_")}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button onClick={countStore.addList}>修改数组</button>
    </div>
}

export default observer(AppMobxComputed)