import './assets/main.scss'

import {createApp} from 'vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from "@/router";

import App from './App.vue'
import pinia from "@/stores";


const app = createApp(App);
app.use(ElementPlus);
app.use(router)
app.use(pinia)
app.mount('#app')
