import request from '@/utils/jwt'


export const userRegisterService = (registerData) => {
    return request.post('/user/register', registerData);
}

export const userLoginService = (loginData) => {
    return request.post('/user/login', loginData)
}

export const userDeregisterService = (data) => {
    return request.post('/user/delete', data)
}