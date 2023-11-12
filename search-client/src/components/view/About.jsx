import "../../assets/base.scss"
import {useEffect, useState} from "react";
import {http} from "../../http/index.js";
import {BrowserRouter, Link, Route, Routes, useNavigate} from "react-router-dom";
import SearchBox from "../SearchBox.jsx";
import Update from "./Update.jsx";
import {info} from "sass";

export default function About() {


    const [infoYou, setInfoYou] = useState({
        age: 0,
        name: "",
        sex: "男",
        hobby: ""
    })


    const You = {
        age: 32,
        name: "LICSLAN",
        sex: "男",
        hobby: "篮球"
    }


    useEffect(() => {
        async function getInfo() {

            const data = await http.get('/getLinInfo?id=1')

            console.log("get data from server is :" + JSON.stringify(data))


            let dataInfo = {}
            if (data?.success) {
                dataInfo = {
                    ...infoYou,
                    age: data.content.info.age,
                    name: data.content.info.name,
                    sex: data.content.info.sex,
                    hobby: data.content.info.hobby
                }
            } else {
                dataInfo = {
                    ...infoYou,
                    age: You.age,
                    name: You.name,
                    sex: You.sex,
                    hobby: You.hobby
                }
            }

            setInfoYou(dataInfo)

        }

        //调用方法执行一下
        getInfo().then(r => console.info("You are Successfully"))


    }, [infoYou.age, infoYou.name])

    const navigate = useNavigate();
    const handleClick = () => {

        console.log("INFO YOU IS :" + JSON.stringify(infoYou))

        navigate('/update', { state: infoYou });
    };

    return <div id="base" className="text-black-50 text-center m-5 bg-body-secondary ">

        About me：

        <div className="text-dark text-start m-5"> 爱好： {infoYou.hobby}</div>

        <div className="text-dark text-start m-5"> 姓名： {infoYou.name}</div>

        <div className="text-dark text-start m-5"> 性别： {infoYou.sex}</div>

        <div className="text-dark text-start m-5"> 年龄： {infoYou.age}</div>

        {/*
        <Link id="update" className="mh-100 m-5  text-decoration-none rounded-1"
              to={{pathname: '/update', state: {age:infoYou.age, name:infoYou.name}}}>
            <button className="text-dark text-end m-5 rounded-2" onClick={print}> 修改</button>
        </Link>*/}


{/*        <Link id="update" className="mh-100 m-5  text-decoration-none rounded-1"
              to={{
                  pathname: `/update`,
                  state: {age: infoYou.age, name: infoYou.name, hobby: infoYou.hobby}
                }}
        >
            <button className="text-dark text-end m-5 rounded-2" onClick={print}> 修改</button>
        </Link>*/}


{/*        <Link
            id="update"
            className="mh-100 m-5 text-decoration-none rounded-1"
            to={{
                pathname: `/update`,
                state: { age: infoYou.age, name: infoYou.name, hobby: infoYou.hobby }
            }}
        >
            <button className="text-dark text-end m-5 rounded-2" onClick={print}>
                修改
            </button>
        </Link>*/}


        <button className="text-dark text-end m-5 rounded-2" onClick={handleClick}>修改</button>

    </div>
}