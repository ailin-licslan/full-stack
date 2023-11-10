import {$http} from "../index";


//表格二 删除方法
export async function table2Remove(id) {
    return await $http({
        method: "DELETE",
        url: "/people/" + id
    }).then(
        res => {
            console.log("data is=======>" + JSON.stringify(res) + "  id is " + id)
            if (res !== null) {
                //重新刷新页面
                window.location.href = window.location.href
            }
        }
    )
}


























