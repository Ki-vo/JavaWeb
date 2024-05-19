<script setup>
import {User, Lock} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
import {ref} from 'vue'
import {useRouter} from "vue-router";

const store = useUserStore()
const router = useRouter()
//登录界面的各项数据
const loginData = ref({
  username: '',
  password: '',
  loginChoice: 'salesman'
})
//表单检查规则
const rules = {
  username: [
    {required: true, message: 'ID不可为空', trigger: ['blur', 'change']},
    {min: 3, max: 20, message: '用户名长度必须为3~20位', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: ['blur', 'change']},
    {min: 3, max: 20, message: '密码长度必须为3~20位', trigger: 'blur'}
  ]
}

//调用登录接口
import {salesLoginService} from "@/api/sales";
import {adminLoginService} from "@/api/admin";
import {useUserStore} from "@/stores";

//登录按钮执行方法
const login = async () => {
  const {username, password, loginChoice} = loginData.value;
  const requestData = JSON.stringify({username, password});
  console.log(requestData);
  console.log(loginChoice)

  let result = {}
  if (loginChoice === "salesman") {
    result = await salesLoginService(requestData);
    let token = result.data
    await store.setLoginInfo("sales", username, token)
    console.log(result)
    if (result.code === 1) {
      ElMessage({
        type: 'success',
        message: '登录成功'
      });
      router.push("/sales")
    } else {
      ElMessage({
        type: 'error',
        message: '用户名或密码错误'
      })
    }
  } else if (loginChoice === "admin") {
    result = await adminLoginService(requestData);
    let token = result.data
    await store.setLoginInfo("admin", username, token)
    console.log(result)
    if (result.code === 1) {
      ElMessage({
        type: 'success',
        message: '登录成功'
      });
      router.push("/admin")
    } else {
      ElMessage({
        type: 'error',
        message: '用户名或密码错误'
      })
    }
  }
}
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 登录表单 -->
      <el-form
          :model="loginData"
          ref="loginForm"
          size="large"
          autocomplete="off"
          :rules="rules">
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item>
          <el-radio-group v-model="loginData.loginChoice">
            <el-radio-button label="salesman">销售人员</el-radio-button>
            <el-radio-button label="admin">管理员</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名"
                    v-model="loginData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
                    v-model="loginData.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="login">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url('@/assets/login_bg.jpg') no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>