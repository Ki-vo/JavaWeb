<script setup>

import {onMounted, ref, onUnmounted} from "vue";
import {ArrowLeft} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {useRoute, useRouter} from "vue-router";
import {useUserStore, useCartStore} from "@/stores";
import {productGetByIdService} from "@/api/product";
import {addBrowseLog} from "@/api/log";

const route = useRoute()
const router = useRouter()

const userStore = useUserStore()
const cartStore = useCartStore()

const id = ref()
const name = ref()
const seller = ref()
const price = ref()
const rest = ref()
const img = ref()

let num = ref(0)
const openTime = ref()
const closeTime = ref()


const submit = function () {
  if (userStore.token === null || userStore.tag !== "user") {
    ElMessage.error('请先登录')
    router.push('/login')
  } else {
    const cart = ref(cartStore.cart)
    for (const key in cart.value) {
      if (cart.value[key].id === id) {
        cartStore.cart[key].num += num.value
        ElMessage.success("已加入购物车")
        router.push('/cart')
        return
      }
    }
  }
  cartStore.addProduct(id, name, price, num.value, img[0])
  ElMessage.success("已加入购物车")
  router.push('/cart')
}


onMounted(async () => {
  id.value = route.params.id
  const res = ref()
  res.value = (await productGetByIdService(id.value)).data
  id.value = res.value.id
  name.value = res.value.name
  seller.value = res.value.seller
  price.value = res.value.price
  rest.value = res.value.rest
  img.value = res.value.cover_img

  openTime.value = new Date().getSeconds()
})

onUnmounted(async () => {
  closeTime.value = new Date().getSeconds()
  const browseTime = closeTime.value - openTime.value
  if (browseTime > 5) {
    if (userStore.token !== null && userStore.tag === "user") {
      const userId = userStore.username
      const productId = id.value
      const browseDuration = browseTime
      const requestData = JSON.stringify({
        userId, productId, browseDuration
      })
      console.log(requestData)
      const res = await addBrowseLog(requestData)
      console.log(res.msg)
    }
  }
})
</script>

<template>
  <el-container>
    <el-main class="main">
      <el-row class="head">
        <el-col :span="2">
          <el-button @click="router.go(-1)">
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
          <el-image :src="img" :fit="'cover'"></el-image>
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
</style>