import SearchBox from "./components/SearchBox.jsx";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import About from "./components/view/About.jsx";
import Home from "./components/view/Home.jsx";
import "./assets/base.scss"
import Update from "./components/view/Update.jsx";
export default function App() {

    return (
        <>
            <BrowserRouter>

                <Link id="home" className="mh-100 m-5  text-decoration-none rounded-1" to="/">首页</Link>
                <Link id="about" className="mh-100 m-5  text-decoration-none rounded-1" to="/about">关于我</Link>
                <Link id="search" className="mh-100 m-5  text-decoration-none rounded-1" to="/search">搜索</Link>

                <Routes>
                    <Route path="/update" element={<Update />} />
                    <Route path={"/"} element={<Home/>}></Route>
                    <Route path={"/about"} element={<About/>}></Route>
                    <Route path={"/search"} element={<SearchBox/>}></Route>


                   {/* <Route path="/layout" element={<Layout/>}>
                        二级嵌套路由
                        <Route path="/layout/board" element={<Board/>}></Route>
                    </Route>
                    <Route path={"/login"} element={<Login/>}></Route>
                    <Route path="*" element={<NotFound/>}></Route>*/}

                </Routes>
            </BrowserRouter>



        </>
    )
}

