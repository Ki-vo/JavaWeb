<template>
  <div>
    <el-container style="height: 700px;">
      <el-header style="height: 60px;font-size: 32px;background-color: darkgrey;">
        <div style="position: absolute;right:20px ">
          <el-button plain id="login">
            <router-link to="/login">登录</router-link>
          </el-button>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>

        <el-button plain id="test" @click="test">
          测试
        </el-button>

      </el-container>
    </el-container>
  </div>
</template>

<script>

import request from "@/utils/jwt";

export default {
  data() {
    return {
      form: {
        name: ''
      }
    }
  },
  methods: {
    test() {
      request.post("/api/test")
          .then(result => {
            this.$alert(result, '标题名称', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: 'info',
                  message: `action: ${action}`
                });
              }
            });
          });
    }
  }
};

</script>

<style>

</style>