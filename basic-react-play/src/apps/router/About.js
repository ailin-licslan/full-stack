import {useSearchParams} from "react-router-dom";

function About() {
    const [params] =useSearchParams()

    const id = params.get("id")

    return <div> About id : {id}</div>
}


export default About