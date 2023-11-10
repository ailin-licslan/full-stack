import {makeAutoObservable} from "mobx";

class CounterStore {
//定义数据状态
    count = 0;
    list = [1,2,3,4,5,6,7]

//响应式处理
    constructor() {
        makeAutoObservable(this)
    }

    //定义计算属性
    get filterList(){
        return this.list.filter(o=>o>2)
    }

    //修改LIST
    addList =()=>{
        this.list.push(10,15,20)
    }

//确定action函数  修改函数
    addCount = () => {
        this.count++
    }

}

//实例化并到处实例
const countStore = new CounterStore()
export {countStore}