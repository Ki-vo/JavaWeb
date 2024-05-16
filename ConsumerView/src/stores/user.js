import {defineStore} from "pinia";
import {ref} from 'vue'

//用户模块
export const useUserStore = defineStore('user', () => {
        //tag表示token的所有者 ("user"/"sales"/"admin")
        const tag = ref(null)
        const token = ref(null)
        const username = ref(null)
        //设置用户信息
        const setLoginInfo = (newTag, newUsername, newToken) => {
            tag.value = newTag;
            token.value = newToken
            username.value = newUsername
        }
        //清空用户信息
        const clearLoginInfo = () => {
            tag.value = null
            token.value = null
            username.value = null
        }
        return {
            tag,
            token,
            username,
            setLoginInfo,
            clearLoginInfo
        }
    },
    {
        persist: true
    })