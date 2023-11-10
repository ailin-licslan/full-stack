import {Outlet, useNavigate} from "react-router-dom";

//编程式导航

//传参方式
// nav('/about?id=1000')  ===> let[params] = useSearchParams()  let id = params.get('id')
// nav('/about/1000')  ===> let[params] = useParams()  let id = params.id

function Layout() {


    return (<div>Layout
    {/*二级路由出口*/}
        <Outlet></Outlet>
    </div>)
}

export default Layout