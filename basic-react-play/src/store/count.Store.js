import {makeAutoObservable} from "mobx";

class CountStore {
    count = 0

    constructor() {
        makeAutoObservable(this)
    }
    addCount =()=>{
        this.count++
    }
}

export {CountStore}