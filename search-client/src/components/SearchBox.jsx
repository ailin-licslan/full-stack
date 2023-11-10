import SearchForm from "./SearchForm.jsx";
import {useState} from "react";
import SearchDataList from "./SearchDataList.jsx";
import SearchPagination from "./SearchPagination.jsx";

export default function SearchBox() {


    const [pagination, setPagination] = useState({
        keywordTest: '',
        page: 0,
        size: 5,

        pages: 0,
        total: 0,
        dataWeb: [],
        toggler: false
    })

    //*父传子*/
    return (

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