import axios from "axios";
import qs from "qs"

const http = axios.create({
    baseURL: 'http://127.0.0.1:8080'
})

http.interceptors.response.use(response => response.data)

export {
    http, qs
}