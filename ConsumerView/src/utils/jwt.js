import axios from "axios";
import pinia from "@/stores";
import {useUserStore} from "@/stores";
//后端地址
const baseURL = import.meta.env.VITE_BASE_API
//axios对象连接后端
const request = axios.create({
    baseURL: baseURL, //后端的ip地址
    timeout: 30000 //响应时间
})
// request拦截器
// 可以在请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    //设置请求头
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    if (config.data instanceof FormData) {
        config.headers['Content-Type'] = 'multipart/form-data'
    }
    //传递token
    const user = useUserStore(pinia)
    let token = user.token
    if (token) {
        config.headers['token'] = token
    }
    return config
}, error => {
    console.log('request error' + error)
    return Promise.reject(error)
})

// response拦截器
request.interceptors.response.use(response => {
    //res即前面的result.data
    let result = response.data
    if (typeof result === 'string') {
        result = result ? JSON.parse(result) : result
    }
    return result
}, error => {
    console.log('response error' + error)
    return Promise.reject(error)
})

export default request
export {baseURL}