import SearchForm from "./SearchForm.jsx";
import {useState} from "react";
import SearchDataList from "./SearchDataList.jsx";

export default function SearchBox() {



    const [pagination, setPagination] = useState({
        keywordTest: '',
        page: 0,
        size: 20,

        pages: 0,
        total: 0,
        data: [],
        toggler: false
    })

    //*父传子*/
    return (

        <section className="mt-5 container py-5 bg-secondary-subtle">

            {/*      {pagination.keywordTest}*/}

            <SearchForm pagination={pagination} update={setPagination}/>

            {
                pagination.toggler && <SearchDataList pagination={pagination} update={setPagination}/>
            }
        </section>
    )
}