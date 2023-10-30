import {reactive} from "vue";
import {$http} from "../index";
export let pageDataTab = reactive([])


async function getListPeople() {
    return await $http({
        method: "GET",
        url: "/people/getList"
    })
}
getListPeople().then(res => {
    //debugger
    if (res !== null && res.code === 0) {
        res.data.forEach(e => {
            //端口号
            e.serverPort = res.serverPort
            e.ip = res.ip
            pageDataTab.push(e)
        })
    } else {
        pageDataTab.push(People)
    }
})


const People = {
    id:100,
    name: "Tester",
    age: "35",
    address: "上海市",
    createTime:"2023-10-26 11:27:34",
    tags:['nice', 'developer'],
    serverPort: 10000,
    ip: "127.0.0.1"
}



export async function onSearch(key: string) {
    return await $http({
        method: "GET",
        url: "/people?name=" + key
    }).then(
        res => {
            console.log("data is=======>" + JSON.stringify(res) + "  key is " + key)
            if (res !== null) {
                // @ts-ignore
                //pageDataTab = table2.setup().dataSource.filter(item => item.name !== key);
                //window.location.href = window.location.href
            }
        }
    )
}