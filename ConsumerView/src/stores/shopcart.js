import {defineStore} from "pinia";
import {ref} from 'vue'

//用户模块
export const useCartStore = defineStore('goods', () => {
        const product = ref()
        const cart = ref([])
        //添加商品
        const addProduct = (id, name, price, num, cover_img) => {
            product.value = {id, name, price, num, cover_img}
            cart.value.push(product.value)
        }
        //清空购物车
        const clearProduct = () => {
            product.value = null
            cart.value = []
        }
        return {
            cart,
            addProduct,
            clearProduct
        }
    },
    {
        persist: true
    })