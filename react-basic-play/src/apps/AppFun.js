//函数组件
import React from "react";

function AppFun() {

    return (
        <div>
            <Test/>
        </div>
    )
}

//类组件
class TestApp extends React.Component {
    constructor() {
        super();
    }

    state = {
        name: "LICSLAN"
    }

    changeName = () => {

        this.setState({
            name: "修改成功！"
        })
    }

    render() {
        return <button onClick={this.changeName}>结果： {this.state.name}</button>;
    }

}

function Test() {

    const test = (e) => {
        //阻止默认行为
        e.preventDefault()
        console.log("函数组件中的事件被触发了~")
    }

    const test2 = (msg) => {
        console.log("传参数了~ " + msg)
    }

    //return <div> <a onClick={test}  href="www.baidu.com">HELLO TEST</a> </div>
    //return <div><button onClick={() => test2("this is msg")} href="www.baidu.com">HELLO TEST</button></div>

    return (<div>
        <TestApp/>
    </div>)
}

export default AppFun;