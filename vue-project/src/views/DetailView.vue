<script setup>

import {onMounted, ref} from "vue";
import {ArrowLeft} from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {useRouter} from "vue-router";
import {useUserStore, useCartStore} from "@/stores";

const router = useRouter()

const userStore = useUserStore()
const cartStore = useCartStore()
const img = [
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg'
]
const id = 1000
const name = '光子精灵S'
const seller = '奥迪双钻'
const price = 2000
const rest = 100
const intro = 'http://8.134.116.81:7000/img/detail1.aac92556.jpg'

let num = ref(0)

const submit = function () {
  const cart = ref(cartStore.cart)
  for (const key in cart.value) {
    if (cart.value[key].id === id) {
      cartStore.cart[key].num += num.value
      ElMessage.success("已加入购物车")
      router.push('/cart')
      return
    }
  }
  cartStore.addProduct(id, name, price, num.value, img[0])
  ElMessage.success("已加入购物车")
  router.push('/cart')
}


onMounted(() => {
  if (userStore.token === null) {
    ElMessage.error('请先登录')
    router.push('/login')
  }
})

</script>

<template>
  <el-container>
    <el-main class="main">
      <el-row class="head">
        <el-col :span="2">
          <el-button @click="this.$router.go(-1)">
            <el-icon>
              <arrow-left/>
            </el-icon>
          </el-button>
        </el-col>
      </el-row>
      <br>
      <el-row style="font-size: 22px"> 商品介绍</el-row>
      <br>
      <el-row>
        <el-col :span="12">
          <el-carousel height="400px">
            <el-carousel-item v-for="item in img" :key="item">
              <el-image :src="item" :fit="'cover'"></el-image>
            </el-carousel-item>
          </el-carousel>
        </el-col>
        <el-col :span="2"></el-col>
        <el-col :span="6">
          <h2>{{ name }}</h2>
          <div>商家：{{ seller }}</div>
          <h3 style="color: orange">￥{{ price }}</h3>
          <div style="font-size: small">库存剩余：{{ rest }}</div>
          <br>
          <el-input-number v-model="num" :min="1" :max="rest"></el-input-number>
          <br><br>
          <el-button type="primary" size="large" @click="submit">立即购买</el-button>
        </el-col>
      </el-row>
      <h1>详细介绍</h1>
      <div class="intro">
        <el-image :src=intro></el-image>
      </div>
    </el-main>
  </el-container>
</template>

<style>
.main {
  padding-left: 15%;
  padding-right: 15%;
  height: 100%;
}

.head {
  display: flex;
  text-align: center;
  align-items: center;
}

.intro {
  display: flex;
  align-items: center;
  text-align: center;
}
</style>