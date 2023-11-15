import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {http, qs} from "../../http/index.js";

export default function Add() {

    const navigate = useNavigate()
    const [update, setUpdate] = useState({
        id: 0,
        age: "",
        name: "",
        sex: "",
        hobby: ""
    })



    // //const {infoYou} = useParams()
    // // 使用 useLocation 钩子获取传递的参数
    // const location = useLocation();
    // const infoYou = location.state;
    // console.log("获取传过来的参数infoYou ", infoYou)



    function onchangeName(current) {
        setUpdate({
            ...update,
            name: current.target.value
        })
    }

    function onchangeAge(current) {
        setUpdate({
            ...update,
            age: current.target.value
        })
    }

    function onchangeSex(current) {
        setUpdate({
            ...update,
            sex: current.target.value
        })
    }

    function onchangeHobby(current) {
        setUpdate({
            ...update,
            hobby: current.target.value
        })
    }
    //
    //
    async function saveInfo() {

        console.log("save is : " + JSON.stringify(update))

        const query = qs.stringify(update)

        const data = await http.post('/saveInfo' + '?' + query)

        console.log("save data is ===> " + JSON.stringify(data))

        if (data?.success && data.code === 1) {
            //跳转到信息页
            navigate("/")
        } else {
            alert(JSON.stringify(data.content))
        }
    }

    function back() {
        navigate("/")
    }

    return (

        <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">
            <div className="m-5">修改信息操作：</div>
            <div> 姓名： <input onChange={onchangeName} value={update.name} className="text-dark text-start m-1 rounded-1" type={"text"}
                               /></div>
            <div> 爱好： <input onChange={onchangeHobby} value={update.hobby}  className="text-dark text-start m-1 rounded-1" type={"text"}
                               /></div>
            <div> 性别： <input onChange={onchangeSex} value={update.sex}  className="text-dark text-start m-1 rounded-1" type={"text"}
                               /></div>
            <div> 年龄： <input onChange={onchangeAge} value={update.age}  className="text-dark text-start m-1 rounded-1" type={"text"}
                               /></div>
            <button className="text-dark text-end m-5 rounded-2" onClick={saveInfo}>新增保存</button>
            <button className="text-dark text-end m-5 rounded-2" onClick={back} >返回</button>
        </div>
    )
}