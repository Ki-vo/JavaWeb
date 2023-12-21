import {createRouter, createWebHistory} from "vue-router";

import LoginView from "@/views/LoginView.vue";
import TestView from "@/views/HomeView.vue";
import CartView from "@/views/CartView.vue";
import HomeView from "@/views/TestView.vue";
import DetailView from "@/views/DetailView.vue";
import SellerView from "@/views/SellerView.vue"

const routes = [
    {path: '/login', component: LoginView},
    {path: '/', component: TestView},
    {path: '/cart', component: CartView},
    {path: '/test', component: HomeView},
    {path: '/detail', component: DetailView},
    {path: '/sell', component: SellerView}
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes
});

export default router