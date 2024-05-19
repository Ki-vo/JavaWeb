<script setup>
import {ArrowDown, CircleClose, Lock, ShoppingCart, SwitchButton, User} from "@element-plus/icons-vue";
import {useCartStore, useUserStore} from "@/stores";
import {onMounted, ref, onUnmounted, onBeforeUnmount} from "vue";
import DeregisterConfirm from "@/views/components/DeregisterConfirm.vue";
import {ElMessage} from "element-plus";
import {editPasswordService} from "@/api/password";
import {userExitService} from "@/api/user";
import {salesExitService} from "@/api/sales";
import {adminExitService} from "@/api/admin";

const userStore = useUserStore()
const cartStore = useCartStore()

const isLogin = ref(false)
const dialogFormVisible = ref(false)

const deregisterConfirmRef = ref()
const form = ref({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})
//刷新导航栏状态
const flush = () => {
  isLogin.value = userStore.token !== null;
}
//修改密码
const editPassword = async () => {
  const {oldPassword, newPassword, confirmPassword} = form.value;
  if (oldPassword === '' || newPassword === '' || confirmPassword === '') {
    ElMessage({
      type: 'error',
      message: '信息不完整'
    })
  } else if (newPassword !== confirmPassword) {
    ElMessage({
      type: 'error',
      message: '请保证两次新密码相同'
    })
  } else {
    const id = userStore.username
    const tag = userStore.tag
    const fd = new FormData()
    fd.append("id", id)
    fd.append("tag", tag)
    fd.append("oldPassword", oldPassword)
    fd.append("newPassword", newPassword)
    const result = await editPasswordService(fd);
    if (result.code === 1) {
      ElMessage({
        type: 'success',
        message: '密码修改成功'
      });
      dialogFormVisible.value = false
      userStore.clearLoginInfo()
      location.reload()
    } else {
      if (result.msg === "WRONG_PASSWORD") {
        ElMessage({
          type: 'error',
          message: '密码错误'
        })
      } else {
        ElMessage({
          type: 'error',
          message: '密码修改失败:' + result.msg
        })
      }
    }
  }
}
//退出登录
const quit = async () => {
  switch (userStore.tag) {
    case "user":
      await userExitService();
      break;
    case "sales":
      await salesExitService();
      break;
    case "admin":
      await adminExitService();
      break;
    default:
      break;
  }
  userStore.clearLoginInfo()
  isLogin.value = false
  cartStore.clearProduct()
  location.reload()
}
//注销账户
const deregister = () => {
  if (userStore.tag !== "user") {
    ElMessage.error("不可注销")
  } else {
    deregisterConfirmRef.value.open()
  }
}
onMounted(async () => {
  // //检验token是否失效
  // if (userStore.token !== null) {
  //   const res = (await checkLoginStatusService())
  //   console.log(res)
  //   if (res.code === 0) {
  //     //token失效
  //     ElMessage.error("认证身份失效")
  //     userStore.clearLoginInfo()
  //   }
  // }
  flush()
})
onBeforeUnmount(() => {
  quit()
})
</script>
<template>
  <!--布局容器-->
  <el-container>
    <el-header height="80px" class="navbar">
      <!--首页按钮-->
      <el-link href="/home">
        <el-button size="large">
          首页
        </el-button>
      </el-link>
      <div style="flex-grow: 1"/>
      <!--购物车按钮-->
      <el-link href="/cart" v-if="userStore.tag==='user'&&userStore.token!==null">
        <el-button class="cart-icon" size="large">
          <el-icon :size="25">
            <shopping-cart/>
          </el-icon>
        </el-button>
      </el-link>
      <!--管理者登录按钮-->
      <el-link v-if="!isLogin" href="/admin/login" :underline="false" class="admin-button">管理者登录</el-link>
      <!--顾客登录按钮-->
      <el-dropdown v-if="isLogin" style="display: flex;align-items: center;">
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
            <el-dropdown-item @click="dialogFormVisible=true">
              <el-icon>
                <Lock/>
              </el-icon>
              修改密码
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
    </el-header>
    <!--路由界面-->
    <router-view class="full_screen_background"></router-view>
  </el-container>
  <!--注销界面-->
  <deregister-confirm ref="deregisterConfirmRef" @success="quit"></deregister-confirm>
  <!-- 修改密码界面-->
  <el-dialog v-model="dialogFormVisible" title="修改密码" width="500">
    <el-form :model="form">
      <el-form-item label="旧密码" :label-width="formLabelWidth">
        <el-input v-model="form.oldPassword" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="新密码" :label-width="formLabelWidth">
        <el-input v-model="form.newPassword" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="确认密码" :label-width="formLabelWidth">
        <el-input v-model="form.confirmPassword" autocomplete="off"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div>
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editPassword">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style>
.full_screen_background {
  background-color: #ffbbbb
}

.navbar {
  background-color: black;
  display: flex;
  padding: 0 50px;
}

.cart-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 20px;
}

.login-button {
  padding: 0 20px;
  font-size: 18px;
  color: white;
}

.admin-button {
  margin: 0 30px;
}
</style>