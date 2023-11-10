import Home from "./router/Home";
import About from "./router/About";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import Login from "./router/Login";
import Layout from "./router/Layout";
import Board from "./router/Board";
import NotFound from "./router/NotFound";


//路由写法

function AppRouter() {
    return (

        <BrowserRouter>
            <Link to="/">首页   &nbsp;&nbsp;&nbsp;&nbsp;</Link>


            <Link to="/about">关于</Link>


            {/*路由配置*/}
            <Routes>
                {/*一级路由*/}
                <Route path={"/"} element={<Home/>}></Route>
                <Route path={"/about"} element={<About/>}></Route>


                <Route path="/layout" element={<Layout/>}>
                    {/*二级嵌套路由*/}
                    <Route path="/layout/board" element={<Board/>}></Route>
                </Route>



                <Route path={"/login"} element={<Login/>}></Route>

                <Route path="*" element={<NotFound/>}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRouter