import {useEffect} from "react";
import {http, qs} from "../http/index.js";
import '../assets/SearchDataList.scss'


//数据展示区组件

export default function SearchDataList({pagination, update}) {


    //调用接口 发送网络请求
    useEffect(() => {

        console.log("---------- START ----------")

        async function reqSearchList() {
            const query = qs.stringify({
                keyword: pagination.keywordTest,
                size: pagination.size,
                page: pagination.page
            })
            const data = await http.get('/getPageList' + '?' + query)
            console.log("====> " + JSON.stringify(data))
            //定义一个对象来装需要渲染的数据
            let dataList = {}
            if (data?.success && data.content.pagination.pages !== 0) {
                dataList = {
                    ...pagination,
                    pages: data.content.pagination.pages,
                    total: data.content.pagination.total,
                    dataWeb: data.content.pagination.pageData
                }
            } else {
                dataList = {
                    ...pagination,
                    pages: 0,
                    total: 0,
                    dataWeb: []
                }
            }
            //给父类组件的对象修改赋值
            update(dataList)
        }

        //调用方法执行一下
        reqSearchList().then(r => console.info("You are Successfully"))

        //依赖的数据变化 就会去请求接口  这里是[]包裹 一定要注意!!!
    }, [pagination.keywordTest, pagination.size, pagination.page])


    if (pagination.dataWeb.length !== 0 && pagination.pages !== 0) {
        //渲染组件数据查到数据
        return (
            <div className=" py-5">
                <div className="row row-cols-xl-4 row-cols-lg-3 row-cols-md-2 row-cols-1">

                    {
                        pagination.dataWeb.map(item => (
                            <div key={item.id} className="col py-1">
                                <div className="card mx-auto rounded-0 mb-3 p-1">
                                    <img src={item.url} alt=""/>
                                    <div className="card-body">
                                        <h3 className="text-danger">￥ {item.price}</h3>
                                        <p className="p-title">{item.title}</p>
                                        <p>{item.type}</p>
                                        <div className="d-flex">
                                            <button className="btn btn-warning rounded-0 btn-sm">收藏</button>
                                            <button className="btn btn-outline-danger rounded-0 btn-sm">加入购物车
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))
                    }

                </div>
            </div>
        )
    } else {
        //渲染组件数据没有查到数据
        return (
            <h5 className=" py-1">没有搜到关键字
                <span className="text-danger mx-2">
                    {pagination.keywordTest}
                </span>
                数据
            </h5>
        )
    }


}
