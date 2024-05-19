<script setup>

import {onMounted, ref, onUnmounted} from "vue";
import {ArrowLeft} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {useRoute, useRouter} from "vue-router";
import {useUserStore, useCartStore} from "@/stores";
import {productGetByIdService} from "@/api/product";
import {addBrowseLog} from "@/api/log";
import {baseURL} from "@/utils/jwt";
import {checkLoginStatusService} from "@/api/user";

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


const submit = async function () {
  await checkLoginStatusService()
  if (userStore.token === null || userStore.tag !== "user") {
    ElMessage.error('请先登录')
    router.push('/login')
  } else {
    const cart = ref(cartStore.cart)
    for (const key in cart.value) {
      if (cart.value[key].id === id.value) {
        cartStore.cart[key].num += num.value
        ElMessage.success("已加入购物车")
        router.push('/cart')
        return
      }
    }
  }
  cartStore.addProduct(id.value, name.value, price.value, num.value, img.value)
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
  img.value = baseURL + "/cover/" + res.value.coverImg

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
    <el-header class="head">
      <el-button @click="router.go(-1)" style="margin: 0 30px">
        <el-icon>
          <arrow-left/>
        </el-icon>
      </el-button>
      <h2 style="font-size: 30px"> 商品介绍</h2>
    </el-header>

    <el-main class="main">
      <el-row style="padding: 15px 0">
        <el-col :span="10">
          <div style=" background-color: #ccc;width: 100% ;height: 100%;
              display: flex;justify-content: center;align-items: center;margin: 0 80px">
            <img :src="img" style="width: 75%"/>
          </div>
        </el-col>
        <el-col :span="6"/>
        <el-col :span="6" style="margin: 50px 0">
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
  margin: 0 10%;
  padding: 50px 20px;
  background-color: #f8f8f8;
  border-radius: 20px;
  min-height: 580px;
}

.head {
  display: flex;
  align-items: center;
}
</style>