import {createStore} from "vuex";
import createPersistedState from 'vuex-persistedstate'

const store = createStore({
    state() {
        return {
            count: 2,
            token: null,
            username: '',
            goods: [{
                id: 0,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: '光子精灵S',
                price: 2000,
                num: 3
            }, {
                id: 1,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: 'earth',
                price: 100,
                num: 2
            }, {
                id: 2,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: '光子精灵S',
                price: 2000,
                num: 3
            }, {
                id: 3,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: 'earth',
                price: 100,
                num: 2
            }, {
                id: 1004,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: 'earth',
                price: 100,
                num: 2
            }, {
                id: 5,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: 'earth',
                price: 100,
                num: 2
            }, {
                id: 6,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: 'earth',
                price: 100,
                num: 2
            }, {
                id: 7,
                img: 'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
                name: 'earth',
                price: 100,
                num: 2
            },]
        }
    },
    mutations: {
        setToken(state, token) {
            state.token = token
        },
        setUsername(state, username) {
            state.username = username
        },
        delToken(state) {
            state.token = null
        },
        delUsername(state) {
            state.username = ''
        }
    },
    actions: {
        saveToken({commit}, token) {
            commit('setToken', token)
        },
        saveUsername({commit}, username) {
            commit('setUsername', username)
        },
        clearLoginInfo({commit}) {
            commit('delUsername')
            commit("delToken")
        }
    },
    plugins: [createPersistedState({
        storage: window.localStorage,
        paths: ['token', 'username', 'count', 'goods']
    })]
})

export default store;