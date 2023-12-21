import axios from "axios";
//创建一个新的axios对象
const baseURL = '/api'
const request = axios.create({
    baseURL: baseURL, //后端的ip地址
    timeout: 30000 //响应时间
})
// request拦截器
// 可以在请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    if (config.data instanceof FormData) {
        config.headers['Content-Type'] = 'multipart/form-data'
    }
    const res = localStorage.getItem('user')
    const user = JSON.parse(res)
    let token = user.token
    console.log(token)
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