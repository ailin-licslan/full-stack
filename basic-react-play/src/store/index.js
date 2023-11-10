//组合子模块
//封装统一导出供业务使用的方法

//1.申明一个rootStore

import {CountStore} from "./count.Store";
import {ListStore} from "./list.Store";
import React from "react"

class RootState {
    constructor() {
        this.countStore = new CountStore()
        this.listStore = new ListStore()
    }
}

const rootStore = new RootState()

const context = React.createContext(rootStore)

const useStore = () => React.useContext(context)

export {useStore}