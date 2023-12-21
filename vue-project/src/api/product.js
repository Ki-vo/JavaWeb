import request from "@/utils/jwt";

//获取所有商品信息
export const getListService = () => {
    return request.post('/product/list');
}

export const productAddService = (data) => {
    return request.post('/product/add', data)
}

export const productGetByIdService = (id) => {
    return request.get('/product', {
        params: {id}
    })
}

export const productDelService = (id) => {
    return request.get('/product/del', {
        params: {id}
    })
}