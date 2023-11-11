import Pagination from "rc-pagination";
import "../assets/rc-pagination.scss"


//分页组件

export default function SearchPagination({pagination, update}) {

    function onChange(current){
        console.log("页码:"+ current)
        update(
            {
                ...pagination,
                page: current
            }
        )
        window.scrollTo({top:0})
    }

    return (
        <div className="d-flex justify-content-center">
            <Pagination
                total={pagination.total}
                pageSize={pagination.size}
                onChange={onChange}
                prevIcon={(<i className="bi bi-chevron-bar-left"></i>)}
                nextIcon={(<i className="bi bi-chevron-bar-right"></i>)}
                jumpNextIcon={(<span>...</span>)}
                jumpPrevIcon={(<span>...</span>)}
            />
        </div>
    )
}