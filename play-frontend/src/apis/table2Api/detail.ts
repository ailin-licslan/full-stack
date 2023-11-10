import {$http} from "../index";
let info = {
    name: "",
    age: 0,
    address: "",
    time: "",
    tags: [],
    id: null
}
// @ts-ignore
import {reactive} from "vue";

export let pageDetail = reactive([])


export async function getInfo(id) {
    return await $http({
        method: "GET",
        url: "/people/" + id
    }).then(
        res => {
            console.log("data is=======>" + JSON.stringify(res) + "  id is " + id)
            if (res !== null) {
                info = res.data
                if (pageDetail.length!=0) {
                    // @ts-ignore
                    pageDetail.splice()
                }
                pageDetail.push(info)
            }
        }
    )
}


export default {
    setup() {
        return {
            pageDetail: [
                // {
                //     key: '1',
                //     name: 'xx',
                //     age: 32,
                //     sex: 1,
                // },
                // {
                //     key: '2',
                //     name: 'ff',
                //     age: 42,
                //     sex: 1,
                // },
            ],

            columns: [
                {
                    title: '姓名',
                    dataIndex: 'name',
                    key: 'name',
                },
                {
                    title: '年龄',
                    dataIndex: 'age',
                    key: 'age',
                },
                {
                    title: '地址',
                    dataIndex: 'address',
                    address: 'address',
                },
                {
                    title: '创建时间',
                    dataIndex: 'createTime',
                    key: 'createTime',
                },
                {
                    title: '标签',
                    dataIndex: 'tags',
                    key: 'tags',
                },

            ],
        };
    },
};


