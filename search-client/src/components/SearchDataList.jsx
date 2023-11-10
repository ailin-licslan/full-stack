import {useEffect} from "react";
import {http, qs} from "../http/index.js";

export default function SearchDataList({pagination, update}) {

    useEffect(() => {
        async function reqSearchList() {
            const query = qs.stringify({
                keyword: pagination.keywordTest,
                size: pagination.size,
                page: pagination.page
            })
            const data = await http.get('/getPageList' + '?' + query)
            console.log("====> " + JSON.stringify(data))

            let dataList = {}
            if (data?.success && data.content.pagination.pages !== 0) {
                dataList = {
                    ...pagination,
                    pages: data.content.pagination.pages,
                    total: data.content.pagination.total,
                    data: data.content.pagination.data
                }

            } else {
                dataList = {
                    ...pagination,
                    pages: 0,
                    total: 0,
                    data: []
                }
            }

            update(dataList)

            console.log("====>o  " + JSON.stringify(pagination))

        }


        reqSearchList()

    }, pagination.keywordTest, pagination.size, pagination.pages)


    if (pagination.total === 0) {
        return (
            <h5 className=" py-1">没有搜到关键字
                <span className="text-danger mx-2">
                    {pagination.keywordTest}
                </span>
                数据
            </h5>
        )
    }

    return (
        <div>有数据</div>
    )


}
