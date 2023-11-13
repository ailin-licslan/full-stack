import {useState} from "react";


//搜索框组件

export default function SearchForm({pagination,update}){

    const inputText = "百度一下"

    // 操作父类里面的关键字变量
    const [input,setInput]=useState(pagination.keywordTest)


    //通用方法
    function extractedCommons() {
        // if (input.trim().length === 0) {
        //     alert("请输入关键字...")
        //     return
        // }

        update({
            ...pagination,
            keywordTest: input,
            page: 1,
            toggler: true
        })
    }

    function onHandleClick(){
        extractedCommons();
    }

    function onHandleKeyUp(e){
        if(e.key==="Enter"){
            extractedCommons()
        }
    }

    //*输入框数据的双向绑定 输入框和父类组件里面的keywordTest   /* 双向绑定  onChange={event => setInput(event.target.value)} */
    return (
       <div className="row">
           <div className="col-lg-8 offset-lg-2 py-5">
               <div className="input-group input-group-lg">


                   <input type="text" className="form-control rounded-0"
                          autoFocus="autoFocus"
                          placeholder={inputText}
                          onChange={event => setInput(event.target.value)}
                          onKeyUp={onHandleKeyUp}
                   />
                   <button className="btn btn-primary rounded-0" onClick={onHandleClick}>
                       <i className="bi bi-search px-3 px-sm-4"></i>
                   </button>
               </div>
           </div>
       </div>
    )
}