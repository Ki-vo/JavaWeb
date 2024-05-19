import request from "@/utils/jwt";

export const adminLoginService = (loginData) => {
    return request.post('/admin/login', loginData);
}

export const adminExitService = () => {
    return request.get('/admin/exit');
}