import {useLocation, useNavigate} from "react-router-dom";

export default function Detail() {



    const location = useLocation();
    console.log("获取传过来的参数location ", location)
    let obj = location.state;
    console.log("获取传过来的参数obj ", JSON.stringify(obj))

    const navigate = useNavigate()
    function back() {
        navigate("/")
    }

    return (
        <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">
            <div className="m-5">数据详情：</div>
            <div> 姓名： <input className="text-dark text-start m-1 rounded-1" type={"text"}
                               defaultValue={obj[0].name} readOnly={true}/></div>
            <div> 爱好： <input className="text-dark text-start m-1 rounded-1" type={"text"}
                               defaultValue={obj[0].hobby} readOnly={true}/></div>
            <div> 性别： <input className="text-dark text-start m-1 rounded-1" type={"text"}
                               defaultValue={obj[0].sex} readOnly={true}/></div>
            <div> 年龄： <input className="text-dark text-start m-1 rounded-1" type={"text"}
                               defaultValue={obj[0].age} readOnly={true}/></div>

            <button className="text-dark text-end m-5 rounded-2" onClick={back}>返回</button>
        </div>
    )
}