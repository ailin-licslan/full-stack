// @ts-ignore
import axios, {AxiosError, AxiosRequestConfig} from "axios";
// @ts-ignore
import {message} from "ant-design-vue";





// 1.定义统一的后端返回的数据报文格式
const axiosInstance = axios.create();
export type BkResponse = {
    data: any;
    code: number;
    message: string;
    succeed: true;
    serverPort: number;
    ip: string;
}






// 2.设置请求根路径
// @ts-ignore
axiosInstance.defaults.baseURL = import.meta.env.VITE_APP_API_BASEURL;













// 3.配置响应拦截器
// @ts-ignore
export const $http = async (config: AxiosRequestConfig) => {
    // store.isLoading(false)
    try {
        const axiosRes = await axiosInstance<BkResponse>(config)
        const bkResponse = axiosRes.data

        if (!bkResponse?.succeed) {
            let errTitle: string = 'Error'
            if (bkResponse.code === 401) {
                errTitle = 'Unauthorized'
                message.error('未授权')
            } else if (bkResponse.code === 403) {
                errTitle = 'Forbidden'
            } else if (bkResponse.code === 8888) {
                errTitle = '8888Error'
            } else if (bkResponse.code === 500) {
                errTitle = 'ServerError'
            } else {
                errTitle = 'Unknown'
            }
            const err = new Error(bkResponse?.message || 'Unknown')
            err.name = errTitle
            throw err
        }
        // @ts-ignore
        //console.log("数据库查询的数据:  "+JSON.stringify(bkResponse.data) + "  The Server is running at :"+ bkResponse.serverPort)
        return bkResponse
    } catch (err) {
        if (err instanceof AxiosError) {
            //message.error('network error!')
            return null;
        }

    } finally {
        // store.close()
    }

}