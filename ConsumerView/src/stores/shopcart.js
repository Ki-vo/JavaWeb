import {defineStore} from "pinia";
import {ref} from 'vue'

//用户模块
export const useCartStore = defineStore('goods', () => {
        const cart = ref([])
        //添加商品
        const addProduct = (id, name, price, num, cover_img) => {
            const product = {id, name, price, num, cover_img}
            cart.value.push(product)
        }
        //清空购物车
        const clearProduct = () => {
            cart.value.splice(0, cart.value.length)
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