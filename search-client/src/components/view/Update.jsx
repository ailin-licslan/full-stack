import {useLocation} from "react-router-dom";

export default function Update() {

    //const {infoYou} = useParams()
    // 使用 useLocation 钩子获取传递的参数
    const location = useLocation();
    console.log("获取传过来的参数location ", location)
    const infoYou = location.state;
    console.log("获取传过来的参数infoYou ", infoYou)

    return (
        <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">

            <div className="m-5">修改信息操作：</div>

            <div> 爱好： <input className="text-dark text-start m-1 rounded-1" type={"text"} placeholder={infoYou.hobby}/></div>

            <div> 姓名： <input className="text-dark text-start m-1 rounded-1" type={"text"} placeholder={infoYou.name}/></div>

            <div> 性别： <input className="text-dark text-start m-1 rounded-1" type={"text"} placeholder={infoYou.sex}/></div>

            <div> 年龄： <input className="text-dark text-start m-1 rounded-1" type={"text"} placeholder={infoYou.sex}/></div>

            <button className="text-dark text-end m-5 rounded-2">保存</button>

        </div>
    )


}