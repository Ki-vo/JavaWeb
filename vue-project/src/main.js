import './assets/main.scss'

import {createApp} from 'vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from "@/router";
import store from "@/store";

import App from './App.vue'
import pinia from "@/stores";

// Vue.prototype.$baseurl = process.env.VUE_APP_BASEURL

const app = createApp(App);
app.use(ElementPlus);
app.use(store)
app.use(router)
app.use(pinia)
// app.provide($baseurl,process.env.VUE_APP_BASEURL)
app.mount('#app')
