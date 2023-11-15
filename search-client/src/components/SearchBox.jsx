import SearchForm from "./SearchForm.jsx";
import {useState} from "react";
import SearchDataList from "./SearchDataList.jsx";
import SearchPagination from "./SearchPagination.jsx";


//父组件  包含了  【搜索框组件】  +  【数据渲染区组件】  +   【分页组件】

export default function SearchBox() {


    //父类属性申明 以及修改父类属性的方法  将属性和修改属性的方法传入到子组件
    const [pagination, setPagination] = useState({

        keywordTest: '',  //关键字
        page: 0,          //第几页
        size: 5,          //每页多少条

        pages: 0,         //共多少页
        total: 0,         //总数据
        dataWeb: [],      //具体数据
        toggler: false    //是否有数据

    })

    //*父传子  Hooks 解决状态问题 组件状态复用 */
    return (

        /* bootstrap样式控制 npm i bootstrap scss */

        /* A 搜索框组件 */  /* B 数据展示区组件*/  /* C 分页组件组件 */

        <section className="mt-5 container py-5 ">


            <SearchForm pagination={pagination} update={setPagination}/>


            {
                pagination.toggler && <SearchDataList pagination={pagination} update={setPagination}/>
            }


            {
                pagination.total!==0 && <SearchPagination pagination={pagination} update={setPagination}/>
            }

        </section>
    )
}