import {useNavigate} from "react-router-dom";

//编程式导航

//传参方式
// nav('/about?id=1000')  ===> let[params] = useSearchParams()  let id = params.get('id')
// nav('/about/1000')  ===> let[params] = useParams()  let id = params.id

function Login() {

    //实现Login 跳转至 about页
    const nav = useNavigate()

    function goAbout() {
        nav("/about?id=100", {replace: true})
    }

    return (<div>Login <button onClick={goAbout}>跳转关于</button></div>)
}

export default Login