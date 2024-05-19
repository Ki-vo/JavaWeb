import request from "@/utils/jwt";

export const salesLoginService = (loginData) => {
    return request.post('/sales/login', loginData);
}

export const salesExitService = () => {
    return request.get('/sales/exit')
}

export const getTypeById = (id) => {
    return request.get('/sales/type', {
        params: {id}
    });
}

export const getListService = () => {
    return request.get('/sales/list');
}

export const addSalesService = (type) => {
    return request.get('/sales/add', {
        params: {type}
    })
}

export const deleteSalesService = (id) => {
    return request.get('/sales/del', {
        params: {id}
    })
}

export const resetPasswordService = (id) => {
    return request.get('/password/reset/sales', {
        params: {id}
    })
}