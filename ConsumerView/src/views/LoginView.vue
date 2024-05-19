<script setup>
import {User, Lock, Message} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
import {ref} from 'vue'
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores";

const store = useUserStore()
const router = useRouter()
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
//注册界面的各项数据
const registerData = ref({
  username: '',
  password: '',
  rePassword: '',
  email: ''
})
//登录界面的各项数据
const loginData = ref({
  username: '',
  password: '',
})
//注册时确认密码的检查方法
const checkRePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error("请确认密码"));
  } else if (value !== registerData.value.password) {
    callback(new Error("两次输入密码不同"));
  } else {
    callback();
  }
}
//表单检查规则
const rules = {
  username: [
    {required: true, message: '用户名不可为空', trigger: ['blur', 'change']},
    {min: 3, max: 20, message: '用户名长度必须为3~20位', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: ['blur', 'change']},
    {min: 3, max: 20, message: '密码长度必须为3~20位', trigger: 'blur'}
  ],
  rePassword: [
    {validator: checkRePassword, trigger: 'blur'}
  ],
  email: [
    {required: true, message: '请输入邮箱地址', trigger: ['blur', 'change']},
    {type: 'email', message: '无效的邮箱地址', trigger: 'blur'}
  ]
}

const clearRegisterData = () => {
  registerData.value = {
    username: '',
    password: '',
    rePassword: '',
    email: ''
  }
}
const clearLoginData = () => {
  loginData.value = {
    username: '',
    password: '',
  }
}

//调用注册接口
import {userRegisterService, userLoginService} from '@/api/user'

//注册按钮执行方法
const register = async () => {
  const {username, password, email} = registerData.value;
  if (username === '' || password === '' || email === '') {
    ElMessage({
      type: 'error',
      message: '信息不完整'
    })
  } else {
    const requestData = JSON.stringify({username, password, email});
    console.log(requestData);
    let result = await userRegisterService(requestData);
    if (result.code === 1) {
      ElMessage({
        type: 'success',
        message: '注册成功'
      });
      isRegister.value = false;
    } else {
      if (result.msg === 'EXIST_NAME') {
        ElMessage({
          type: 'error',
          message: '用户名已存在'
        })
      } else {
        ElMessage({
          type: 'error',
          message: '注册失败'
        })
      }
    }
  }
}
const login = async () => {
  const {username, password} = loginData.value;
  const requestData = JSON.stringify({username, password});
  console.log(requestData);
  let result = await userLoginService(requestData);
  let token = result.data

  await store.setLoginInfo("user", username, token)
  console.log(result)
  if (result.code === 1) {
    ElMessage({
      type: 'success',
      message: '登录成功'
    });
    router.go(-1)
  } else {
    ElMessage({
      type: 'error',
      message: '用户名或密码错误'
    })
  }
}
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册表单 -->
      <el-form
          :model="registerData"
          ref="registerForm"
          size="large"
          autocomplete="off"
          v-if="isRegister"
          :rules="rules"
      >
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名"
                    v-model="registerData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码"
                    v-model="registerData.password"></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input :prefix-icon="Lock" type="password" placeholder="请确认输入密码"
                    v-model="registerData.rePassword"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input :prefix-icon="Message" placeholder="请输入邮箱"
                    v-model="registerData.email"></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="register">
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = false;clearRegisterData()">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>
      <!-- 登录表单 -->
      <el-form
          :model="loginData"
          ref="loginForm"
          size="large"
          autocomplete="off"
          :rules="rules"
          v-else>
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名"
                    v-model="loginData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
                    v-model="loginData.password"></el-input>
        </el-form-item>
        <!--        <el-form-item class="flex">-->
        <!--          <div class="flex">-->
        <!--            <el-checkbox>记住我</el-checkbox>-->
        <!--            <el-link type="primary" :underline="false">忘记密码？</el-link>-->
        <!--          </div>-->
        <!--        </el-form-item>-->
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="login">
            登录
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = true;clearLoginData()">
            注册 →
          </el-link>
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