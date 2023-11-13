import {useLocation, useNavigate} from "react-router-dom";
import {http, qs} from "../../http/index.js";
import {useState} from "react";

export default function Update() {


    const navigate = useNavigate()

    const [update, setUpdate] = useState({
        id: 0,
        age: 0,
        name: "",
        sex: "",
        hobby: ""
    })

    //const {infoYou} = useParams()
    // 使用 useLocation 钩子获取传递的参数
    const location = useLocation();
    console.log("获取传过来的参数location ", location)
    const infoYou = location.state;
    console.log("获取传过来的参数infoYou ", infoYou)


    async function updateInfo() {

        debugger
        const dataUpdate ={
            id: infoYou.id,
            age: update.age,
            name: update.name,
            sex: update.sex,
            hobby: update.hobby

        }
        const query = qs.stringify(dataUpdate)
        console.log("修改操作的参数是：" + query)

        const data = await http.post('/updateInfo' + '?' + query)

        console.log("update data is ===> " + JSON.stringify(data) + "  ========  " + infoYou.id)
        if (data?.success && data.code === 1) {
            //跳转到信息页
            navigate("/about", {state: infoYou.id})
        } else {
            alert(JSON.stringify(data.content))
        }
    }


    //保存记录
    async function saveInfo() {

        console.log("save is : " + JSON.stringify(update))

        const query = qs.stringify(update)

        const data = await http.post('/saveInfo' + '?' + query)

        console.log("save data is ===> " + JSON.stringify(data))

        if (data?.success && data.code === 1) {
            //跳转到信息页
            navigate("/home")
        } else {
            alert(JSON.stringify(data.content))
        }
    }


    //TODO 待优化  可以是同一个方法  做属性判断

    /*    function common(e) {
            let name = e.target.name
            let value = e.target.value
            console.log("受控组件使用~~~" + name + " === " + value)
            setUpdate({
                ...update,
                [name]: value
            })
        }*/


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

    return (

        <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">
            <div className="m-5">修改信息操作：</div>
            <div> 姓名： <input onChange={onchangeName} className="text-dark text-start m-1 rounded-1" type={"text"}
                               value={update.name}/></div>
            <div> 爱好： <input onChange={onchangeHobby} className="text-dark text-start m-1 rounded-1" type={"text"}
                               value={update.hobby}/></div>
            <div> 性别： <input onChange={onchangeSex} className="text-dark text-start m-1 rounded-1" type={"text"}
                               value={update.sex}/></div>
            <div> 年龄： <input onChange={onchangeAge} className="text-dark text-start m-1 rounded-1" type={"text"}
                               value={update.age}/></div>
            <button className="text-dark text-end m-5 rounded-2" onClick={saveInfo}>新增保存</button>

            <button className="text-dark text-end m-5 rounded-2" onClick={updateInfo}>修改保存</button>
        </div>




        /*        <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">

                    <div className="m-5">修改信息操作：</div>

                    <div> 姓名： <input onChange={common} className="text-dark text-start m-1 rounded-1" type={"text"}
                                       value={update.name}/></div>
                    <div> 爱好： <input onChange={common} className="text-dark text-start m-1 rounded-1" type={"text"}
                                       value={update.hobby}/></div>
                    <div> 性别： <input onChange={common} className="text-dark text-start m-1 rounded-1" type={"text"}
                                       value={update.sex}/></div>
                    <div> 年龄： <input onChange={common} className="text-dark text-start m-1 rounded-1" type={"text"}
                                       value={update.age}/></div>
                    <button className="text-dark text-end m-5 rounded-2" onClick={saveInfo}>保存</button>
                </div>*/
    )


}