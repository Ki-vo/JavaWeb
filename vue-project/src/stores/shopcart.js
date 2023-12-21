import {defineStore} from "pinia";
import {ref} from 'vue'

//用户模块
export const useCartStore = defineStore('goods', () => {
        const product = ref()
        const cart = ref([])

        const addProduct = (id, name, price, num, cover_img) => {
            product.value = {id, name, price, num, cover_img}
            cart.value.push(product.value)
        }

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