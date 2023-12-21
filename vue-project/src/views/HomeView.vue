<script setup>
import {onMounted, ref} from 'vue'
import {User, SwitchButton, ArrowRight, ArrowDown, ShoppingCart, View, CircleClose} from "@element-plus/icons-vue";
import {useCartStore, useUserStore} from "@/stores";
import DeregisterConfirm from "@/views/components/DeregisterConfirm.vue";

const userStore = useUserStore()
const cartStore = useCartStore()
let isLogin = ref(false)

const image = [
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg',
  'https://pic.616pic.com/ys_bnew_img/00/04/21/fZXPNPokqz.jpg'
]

const urls = [
  'http://www.baidu.com',
  'http://www.bilibili.com',
  'http://www.baidu.com',
  'http://www.baidu.com',
  'http://www.baidu.com',
  'http://www.baidu.com',
  'http://www.baidu.com',
  'http://www.baidu.com'
]
const deregisterConfirmRef = ref()

const quit = () => {
  userStore.clearLoginInfo()
  isLogin.value = false
  cartStore.clearProduct()
}

const deregister = () => {
  deregisterConfirmRef.value.open()
}

onMounted(() => {
  if (userStore.token !== null) {
    isLogin.value = true
  }
  console.log(userStore.token)
  console.log(userStore.username)
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <!--MenuBar-->
      <el-header height="60px" style="background-color: black">
        <el-row class="header">
          <el-col :span="20">
            <div class="grid-content"></div>
          </el-col>
          <el-col :span="2" class="cart">
            <el-link href="/cart">
              <el-button class="cart-icon">
                <el-icon :size="18">
                  <shopping-cart/>
                </el-icon>
              </el-button>
            </el-link>
          </el-col>
          <el-col :span="2" class="login">
            <el-dropdown v-if="isLogin">
              <el-button size="large" style="font-size: large">
                <el-icon>
                  <user/>
                </el-icon>
                {{ userStore.username }}
                <el-icon class="el-icon--right">
                  <arrow-down/>
                </el-icon>
              </el-button>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>
                    <el-icon>
                      <View/>
                    </el-icon>
                    个人信息
                  </el-dropdown-item>
                  <el-dropdown-item @click="deregister">
                    <el-icon>
                      <CircleClose/>
                    </el-icon>
                    注销
                  </el-dropdown-item>
                  <el-dropdown-item @click="quit">
                    <el-icon>
                      <switch-button/>
                    </el-icon>
                    退出
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
            <el-link v-else href="/login" :underline="false" class="login-button">登录</el-link>
          </el-col>
        </el-row>
      </el-header>
      <br><br>
      <!--主界面-->
      <el-main class="main_window">
        <el-row class="category_roll">
          <el-col :span="6" class="category">
            <el-scrollbar height="400px">
              <el-menu class="category-menu" :router="true">
                <el-menu-item index="1">
                  <span>种类一</span>
                </el-menu-item>
                <el-menu-item index="2">
                  <span>种类二</span>
                </el-menu-item>
                <el-menu-item index="3">
                  <span>种类三</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <span>种类四</span>
                </el-menu-item>
                <el-menu-item index="5">
                  <span>种类五</span>
                </el-menu-item>
                <el-menu-item index="6">
                  <span>种类六</span>
                </el-menu-item>
                <el-menu-item index="7">
                  <span>种类七</span>
                </el-menu-item>
                <el-menu-item index="8">
                  <span>种类八</span>
                </el-menu-item>
                <el-menu-item index="9">
                  <span>种类九</span>
                </el-menu-item>
              </el-menu>
            </el-scrollbar>
          </el-col>
          <el-col :span="18" class="roll">
            <el-carousel height="400px">
              <el-carousel-item>
                <img src="../assets/main1.jpg" height="470" width="590"/>
              </el-carousel-item>
              <el-carousel-item>
                <img src="../assets/main2.jpg" height="470" width="590"/>
              </el-carousel-item>
            </el-carousel>
          </el-col>
        </el-row>
        <br><br><br><br>
        <div class="show-more">
          <el-link href="/goods" :underline="false" style="color: grey" disabled>查看更多
            <el-icon>
              <arrow-right/>
            </el-icon>
          </el-link>
        </div>
        <br>
        <div class="hottest">
          <span v-for="(i,index) in 4" :key="index" class="image-item-1">
            <el-link :href=urls[index]>
              <el-card :body-style="{ padding: '0px' }">
                <el-image :src=image[index] class="image" style="height: 100px;width:100px"/>
                <div style="padding: 14px">
                  <span>Yummy hamburger</span>
                </div>
              </el-card>
            </el-link>
          </span>
          <span v-for="(i,index) in 4" :key="index" class="image-item-2">
            <el-link :href=urls[index+4]>
              <el-card :body-style="{ padding: '0px' }">
                <el-image :src=image[index+4] class="image" style="height: 100px;width:100px"/>
                <div style="padding: 14px">
                  <span>Yummy hamburger</span>
                </div>
              </el-card>
            </el-link>
          </span>
        </div>
      </el-main>
      <el-footer>

      </el-footer>
    </el-container>
  </div>

  <deregister-confirm ref="deregisterConfirmRef" @success="quit"></deregister-confirm>
</template>


<style scoped lang="scss">
.main_window {
  padding-left: 15%;
  padding-right: 15%;
}

.show-more {
  display: flex;
  justify-content: right;
  align-items: center;
}


.el-row {
  margin-bottom: 20px;
  height: auto;
}

.el-row.header {
  height: 60px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.el-col.cart {
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-col.cart.cart-icon {
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-col.login {
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-carousel__item {
  background-color: grey;
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.login-button {
  font-size: 20px;
  color: white;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.hottest {
  display: flex;
  flex-wrap: wrap;
}

.image-item-1 {
  flex-basis: 25%;
  text-align: center;
}

.image-item-1:first-child {
  text-align: left;
}

.image-item-1:last-child {
  text-align: right;
}

.image-item-2 {
  flex-basis: 25%;
  text-align: center;
}

.image-item-2:first-child {
  text-align: left;
}

.image-item-2:last-child {
  text-align: right;
}

.image {
  width: 100%;
}
</style>