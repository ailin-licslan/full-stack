// @ts-ignore
import {reactive} from "vue";
import {$http} from "../index";

async function getListPage() {
    return await $http({
        method: "GET",
        url: "/person/getList"
    })
}

getListPage().then(res => {
    //debugger
    if (res !== null && res.code === 0) {
        res.data.forEach(e => {
            //性别处理
            if (e.sex === true) {
                e.sex = '男'
            } else {
                e.sex = '女'
            }
            //端口号
            e.serverPort = res.serverPort
            e.ip = res.ip
            pageData.push(e)
        })
    } else {
        pageData.push(Person)
    }
})


const Person = {
    name: "Tester",
    age: "35",
    sex: "男",
    serverPort: 10000,
    ip: "127.0.0.1"
}

export const pageData = reactive([])