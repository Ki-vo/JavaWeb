<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册表单 -->
      <el-form :model="user" :rules="rules" size="large" v-if="isRegister" ref="registerUser">
        <el-form-item class="title">
          <h1>注册</h1>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="user.password" type="text" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="rePassword">
          <el-input v-model="user.rePassword" type="password" placeholder="请再次输入密码" show-password></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" @click="Register">注册</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister=false">← 返回</el-link>
        </el-form-item>
      </el-form>
      <!-- 登录表单 -->
      <el-form :model="user" :rules="rules" ref="loginUser" size="large" v-else>
        <el-form-item class="title">
          <h1>登录</h1>
        </el-form-item>
        <el-form-item label="用户名" prop="un">
          <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pw">
          <el-input v-model="user.password" type="password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div>
            <el-checkbox>记住我</el-checkbox>
          </div>
          <div>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" @click="Login">登录</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister=true">注册 →</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import request from "@/utils/jwt";


export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.user.rePassword !== '') {
          this.$refs.registerUser.validateField('password');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.user.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      isRegister: false,
      user: {
        username: '',
        password: '',
        rePassword: ''
      },
      rules: {
        username: [
          {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          },
          {
            min: 3,
            max: 10,
            message: '用户名长度必须为3~10位',
            trigger: 'blur'
          }
        ],
        password: [
          {
            validator:validatePass,
            trigger:'blur'
          },
          {
            min: 5,
            max: 16,
            message: '密码长度必须为5~16位',
            trigger: 'blur'
          }
        ],
        rePassword: [
          {
            validator: validatePass2,
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    Register() {
      const registerData = {
        username: this.user.username,
        password: this.user.password,
        rePassword: this.user.rePassword
      };
      console.log(registerData)
    },
    Login() {
      this.$refs.loginUser.validate((valid) => {
        if (valid) {
          alert('submit!');
          const loginData = {
            username: this.user.username,
            password: this.user.password
          };
          request.post("/api/login", loginData)
              .then(result => {
                localStorage.setItem("logintoken", result.data)
                if (result)
                  this.$router.push("/");
              })
              .catch(error => {
                if (error.response) {
                  console.log(error.response.data);
                  alert("error");
                }
              })
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    }
  }
}
</script>

<style>

.login-page {
  height: 100vh;
  background-color: #fff;
}

.login-page .bg {
  height: 100vh;
  background: url("@/assets/logo2.png") no-repeat 60% center / 240px auto,
  url("@/assets/login_bg.jpg") no-repeat center / cover;
  border-radius: 0 20px 20px 0;
}

.login-page .form {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  user-select: none;
}

.login-page .form .title {
  margin: 0 auto;
}

.login-page .form .button {
  width: 100%;
}

.login-page .form .flex {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

</style>