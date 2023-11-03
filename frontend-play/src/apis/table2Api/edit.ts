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
import {useRouter}  from "vue-router";



export let pageEdit = reactive([])


export async function getInfoEdit(id) {
    return await $http({
        method: "GET",
        url: "/people/" + id
    }).then(
        res => {
            console.log("data is  edit=======>" + JSON.stringify(res) + "  id is " + id)
            if (res !== null) {
                info = res.data
                if (pageEdit.length!=0) {
                    // @ts-ignore
                    pageEdit.splice()
                }
               return pageEdit.push(info)
            }
        }
    )
}


export async function editUpdate(info) {
    return await $http({
        method: "POST",
        url: "/update-people/",
        data: info,
    }).then(
        res => {
            if (res !== null) {
                //重新刷新页面
                //window.location.href = window.location.href
                console.log("更新成功！")
                // 保留当前页记录，页面跳转至 /boot 页
                //useRouter().push('/boot').then(r => console.log("kkkkkkkkkkk"));
                this.$router.push({path:'/boot'})

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
                    key: 'address',
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


