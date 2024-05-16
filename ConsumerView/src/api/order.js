import request from "@/utils/jwt";

export const getPayStatus = () => {
    return request.post('/order/pay')
        .catch((err) => {
            return 0
        })
}

export const addOrder = (data) => {
    return request.post('/order/add', data);
}

export const getOrderByCateId = (cateId) => {
    return request.get('/order/list', {
        params: {cateId}
    })
}