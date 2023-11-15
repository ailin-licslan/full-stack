import "../../assets/base.scss"
import {useEffect, useState} from "react";
import {http, qs} from "../../http/index.js";
import SearchPagination from "../SearchPagination.jsx";
import {useNavigate} from "react-router-dom";

export default function Home() {


    let [infoList, setInfoList]= useState([
        {
            id:0,
            name:"",
            age:0,
            sex:"",
            hobby:""
        }
    ])


    useEffect(() => {
        async function getInfoList() {
            const response = await http.get('/getInfoList')
            let data = []
            if (response?.success && response.code === 1) {

                console.log("DATA is " + JSON.stringify(response.content.infoList.pageData[0].name))

               const dataList = response.content.infoList.pageData;

                for (let i = 0; i < dataList.length; i++) {
                    data.push({
                        id: dataList[i].id,
                        name: dataList[i].name,
                        age: dataList[i].age,
                        sex: dataList[i].sex,
                        hobby: dataList[i].hobby
                    })
                }
            }
           setInfoList(data)
        }
        getInfoList().then(r => console.info("YOU ARE SUCCESSFULLY"))
    },[infoList.name])



    //const navigate = useNavigate();
    const del = async (event, id) => {
        //debugger
        const data = await http.delete('/del/' + id)
        console.log("get data from server is :" + JSON.stringify(data))
        if (data?.success) {
            //navigate("/")
            location.reload()
        }
    };



    // data.push({
    //     id: 1,
    //     name: "xx",
    //     age: 26,
    //     hobby: "篮球",
    //     sex: "男"
    // })
    // data.push({
    //     id: 2,
    //     name: "xx",
    //     age: 26,
    //     hobby: "篮球",
    //     sex: "男"
    // })


    return (

        <div id="base" className="text-danger text-center m-5 bg-body-secondary">

        <table className="table table-striped">
            <thead>
            <tr>
                <th scope="col">序号</th>
                <th scope="col">姓名</th>
                <th scope="col">年龄</th>
                <th scope="col">性别</th>
                <th scope="col">爱好</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
            {
                infoList.map(itme => (
                    <tr key={itme.id}>
                        <th scope="row">{itme.id}</th>
                        <td>{itme.name}</td>
                        <td>{itme.age}</td>
                        <td>{itme.sex}</td>
                        <td>{itme.hobby}</td>
                        <td>
                            <button type="button" className="m-2 btn btn-danger" onClick={event => del(event,itme.id)}>删除</button>
                            <button type="button" className="m-2 btn btn-info">详情</button>
                            <button type="button" className="m-2 btn btn-info" >编辑</button>
                        </td>
                    </tr>
                ))
            }
            </tbody>
        </table>



    </div>
    )
}