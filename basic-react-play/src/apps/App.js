/**
 * 学习参考 再学一次 react
 * https://www.bilibili.com/video/BV1Z44y1K7Fj/?p=5&spm_id_from=
 * pageDriver&vd_source=bbb985ceadc8e3199d1d5a091b58155b
 * */
//JSX JavaScript XML (HTML)  类似于HTML

// 1. 字符串、数值、布尔值、null、undefined、object（ [] / {} ）
// 2. 1 + 2、'abc'.split('')、['a', 'b'].join('-')
// 3. fn()
import {useState} from "react";
const name = "LICSLAN"

const x = () => {
    return 18
}

const flag = true

//KEY 不会出现在真实的dom结构中
const songs = [
    {id: 1, name: "庐州月"},
    {id: 2, name: "不分手恋爱"}
]


function App() {


    const [disabled, setDisabled] = useState(false)
    const [count, setCount] = useState(0)

    const inAdd = ()=>{

        const res = count%2

        console.log("res is ", res)

        if(count%2===0){
            setDisabled(false)
            console.log("按钮可以使用")
        }else {
            setDisabled(true)
            console.log("按钮禁用")
        }
        setCount(count+1)
        console.log(disabled.valueOf())
    }

    return (
        <div className="App">

            HELLO REACT : {name} &nbsp;&nbsp;&nbsp;&nbsp;
            <div>
                <br></br>
            </div>

            Age: {x()} &nbsp;&nbsp;&nbsp;&nbsp; {flag ? "right" : "not correct"}

            <hr></hr>

            <span>
                <ul>
                    {songs.map(song => <li key={song.id}>{song.name}</li>)}
                </ul>
            </span>

            <hr></hr>

            {flag && <div>THIS IS TEST</div>}

            <div>
                <br>

                </br>
            </div>
            <button disabled={disabled}>ceshi</button>
            <button onClick={()=>{inAdd()}}>控制</button>

        </div>
    );
}

export default App;
