import {defineStore} from "pinia";
import {ref} from 'vue'

//用户模块
export const useUserStore = defineStore('user', () => {
        const token = ref(null)
        const username = ref('')

        const setLoginInfo = (newUsername, newToken) => {
            token.value = newToken
            username.value = newUsername
        }
        const clearLoginInfo = () => {
            token.value = null
            username.value = ''
        }
        return {
            token,
            username,
            setLoginInfo,
            clearLoginInfo
        }
    },
    {
        persist: true
    })