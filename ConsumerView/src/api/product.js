import request from "@/utils/jwt";

//获取所有商品信息
export const getAllProductService = () => {
    return request.post('/product/list');
}
//获取单个类别所有商品信息
export const getListByTypeService = (type) => {
    return request.get('/product/type', {
        params: {type}
    });
}
//添加商品
export const productAddService = (data) => {
    return request.post('/product/add', data)
}
//更改商品信息
export const productUpdateService = (data) => {
    return request.post('/product/update', data)
}
//根据ID获取商品信息
export const productGetByIdService = (id) => {
    return request.get('/product', {
        params: {id}
    })
}
//删除商品
export const productDelService = (id) => {
    return request.get('/product/del', {
        params: {id}
    })
}
//获取八个热门商品信息
export const getHotProductService = () => {
    return request.post('/product/hot')
}