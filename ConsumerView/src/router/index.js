import {createRouter, createWebHistory} from "vue-router";

import LoginView from "@/views/LoginView.vue";
import HomeView from "@/views/HomeView.vue";
import CartView from "@/views/CartView.vue";
import TestView from "@/views/TestView.vue";
import DetailView from "@/views/DetailView.vue";
import SalesView from "@/views/SalesView.vue"
import ManagerLoginView from "@/views/ManagerLoginView.vue";
import AdminView from "@/views/AdminView.vue";
import NavBarView from "@/views/NavBarView.vue";

const routes = [
    {
        path: "/", component: NavBarView, redirect: '/home',
        children: [
            //普通用户
            {path: '/home', component: HomeView},
            {path: '/cart', component: CartView},
            {path: '/detail', component: DetailView},
            {name: 'DetailView', path: '/detail/:id', component: DetailView},
            //测试
            {path: '/test', component: TestView}
        ]
    },
    {path: '/login', component: LoginView},
    //销售人员&管理员
    {path: '/admin/login', component: ManagerLoginView},
    {path: '/sales', component: SalesView},
    {path: "/admin", component: AdminView},
]

const router = createRouter({
    history: createWebHistory(import.meta.env.VITE_BASE_URL),
    routes: routes
});

export default router