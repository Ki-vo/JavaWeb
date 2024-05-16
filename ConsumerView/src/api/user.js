import request from '@/utils/jwt'

export const checkLoginStatusService = () => {
    return request.get('/check')
}
//用户注册接口
export const userRegisterService = (registerData) => {
    return request.post('/user/register', registerData);
}
//用户登录接口
export const userLoginService = (loginData) => {
    return request.post('/user/login', loginData)
}
//用户注销接口
export const userDeregisterService = (data) => {
    return request.post('/user/delete', data)
}