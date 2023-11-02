import {$http} from "../index";
let info = {
    name: "",
    age: 0,
    address: "",
    createTime: "",
    tags: [],
    id: null
}
// @ts-ignore
import {reactive} from "vue";

export let pageEdit = reactive([])


export async function getInfoEdit(id) {
    return await $http({
        method: "GET",
        url: "/people/" + id
    }).then(
        res => {
            console.log("data is=======>" + JSON.stringify(res) + "  id is " + id)
            if (res !== null) {
                info = res.data
                if (pageEdit.length!=0) {
                    // @ts-ignore
                    pageEdit.splice()
                }
               return  pageEdit.push(info)
            }
        }
    )
}


export default {
    setup() {
        return {
            pageEdit,
            info,

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


