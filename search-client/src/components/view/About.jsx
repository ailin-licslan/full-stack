import "../../assets/base.scss"
import {useEffect, useState} from "react";
import {http} from "../../http/index.js";
import {useLocation, useNavigate} from "react-router-dom";

export default function About() {
    let testAt = {}

    const location = useLocation();
    console.log("获取传过来的参数location ", location)
    let id = location.state;
    console.log("获取传过来的参数id ", id)



    const [infoYou, setInfoYou] = useState({
        id: 0,
        age: 0,
        name: "",
        sex: "男",
        hobby: "",
        flag: false
    })


    const You = {
        id: 0,
        age: 32,
        name: "默认姓名 - LICSLAN",
        sex: "默认性别 - 男",
        hobby: "默认爱好 - 篮球"
    }


    useEffect(() => {
        async function getInfo() {
           // debugger
            if (id === null) {
                id = 1;
            }
            const data = await http.get('/getLinInfo?id=' + id)
            console.log("get data from server is :" + JSON.stringify(data))
            let dataInfo = {}

            if (data?.success ) {
                if (data.content.info.length === 0) {
                    await axTest()
                    if (testAt !== null) {
                        dataInfo = {
                            ...infoYou,
                            id: testAt.id,
                            age: testAt.age,
                            name: testAt.name,
                            sex: testAt.sex,
                            hobby: testAt.hobby
                        }
                    }
                } else {
                    dataInfo = {
                        ...infoYou,
                        id: data.content.info[0].id,
                        age: data.content.info[0].age,
                        name: data.content.info[0].name,
                        sex: data.content.info[0].sex,
                        hobby: data.content.info[0].hobby
                    }
                }

            } else {
                dataInfo = {
                    ...infoYou,
                    id: You.id,
                    age: You.age,
                    name: You.name,
                    sex: You.sex,
                    hobby: You.hobby
                }
            }

            if (dataInfo.name !== null) {
                setInfoYou(dataInfo)
            }


        }

        //调用方法执行一下
        getInfo().then(r => console.info("You are Successfully"))


    }, [infoYou.age, infoYou.name])


    const navigate = useNavigate();
    const handleClick = () => {
        const dataInfo = {
            id: infoYou.id,
            age: infoYou.age,
            name: infoYou.name,
            sex: infoYou.sex,
            hobby: infoYou.hobby,
            flag: true
        }
        //console.log("INFO YOU IS :" + JSON.stringify(infoYou))
        navigate('/update', {state: dataInfo});
    };

    const handleClickUpdate = () => {
        navigate('/update', {state: infoYou});
    }

    const back = () => {
        navigate("/")
    }

    const del = async (event, id) => {
        //debugger
        const data = await http.delete('/del/' + id)
        console.log("get data from server is :" + JSON.stringify(data))
        if (data?.success) {
            navigate("/")
        }
    };


    //获取最小的id的
    const axTest = async () => {
        const res = await http.get('/getIdMin')
        console.log("TEStAt  " + JSON.stringify(res))
        testAt = {
            id: res.content.idMin.id,
            age: res.content.idMin.age,
            name: res.content.idMin.name,
            sex: res.content.idMin.sex,
            hobby: res.content.idMin.hobby
        }
    }


    return <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">

        About me：

        <div className="text-dark text-start m-5"> 爱好： {infoYou.hobby}</div>

        <div className="text-dark text-start m-5"> 姓名： {infoYou.name}</div>

        <div className="text-dark text-start m-5"> 性别： {infoYou.sex}</div>

        <div className="text-dark text-start m-5"> 年龄： {infoYou.age}</div>

        <button className="text-dark text-end m-5 rounded-2" onClick={handleClick}>新增</button>

        <button className="text-dark text-end m-5 rounded-2" onClick={handleClickUpdate}>修改</button>

        <button className="text-dark text-end m-5 rounded-2" onClick={event => del(event, infoYou.id)}>删除</button>

        <button className="text-dark text-end m-5 rounded-2" onClick={back}>返回</button>

    </div>
}