import {computed, reactive, ref, watch} from "vue";

// @ts-ignore
import image from '../image/waye.jpg'

export default {

    setup() {

        const test = reactive({
            x: 10, y: 20
        })

        const hobby = ref("")
        const date = reactive({
            year: "2023",
            month: "11"
        })

        function increment() {
            // 在 JavaScript 中需要 .value
            count.value++
        }

        const toggle = () => {
            //debugger
            data.show = !data.show
            console.info(data.show)
        }
        const edit = () => {
            data.name = "Hi"
            data.age = 100
            aa.value = "yyy"
            console.log(aa.value + " ==== " + data.name)
        }
        const add = (x, y) => {
            data.age += x + y
            console.log(data.age)
        }

        let add2 = () => {
            console.log("add2")
            return test.x + test.y
        }

        /*
         * computed vs methods
          我们可以使用 methods 来替代 computed，效果上两个都是一样的，但是 computed 是基于它的依赖缓存，
         * 只有相关依赖发生改变时才会重新取值。而使用 methods ，在重新渲染的时候，函数总会重新调用执行。
        * */
        const sub = computed(() => {
            console.log("sub")
            return test.x - test.y
        })
        const msgTest = "xxxxDDD"


        const question = ref('')
        const answer = ref('Questions usually contain a question mark. ;-)')

        watch(hobby, (newValue, oldValue) => {
            debugger
                console.log("newValue is : ", newValue, "oldValue is : ", oldValue)
            },
        )


        return {
            data, a, b, add, edit, toggle, aa, increment,
            count, web, dataSum, test, add2, sub, msgTest, date, hobby, question, answer
        }

    }
}
const aa = ref("xxx")
const a = "ARE YOU OK WITH VUE STUDY?"
const b = ref(20)
//响应式对象
const data = reactive({
    age: 1, name: "LIN", show: true
})
//响应式对象
const web = reactive({
    img: image, url: "www.xxx.com", fontStatus: false
})
const count = ref(0)

//声明复杂对象
const dataSum = reactive({
    num: ["10", "11", "12"],
    person: {
        name: "LIN",
        gender: "male"
    },
    teacher: [
        {id: 100, name: "Jack", web: "www.licslan.com"},
        {id: 101, name: "Jack1", web: "www.baidu.com"},
    ],
    url: "<i style='color:blue;'>www.baidu.com</i>"

})









