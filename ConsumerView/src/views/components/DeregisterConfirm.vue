<script setup>
import {ref} from 'vue'
import {useUserStore} from "@/stores";
import {userDeregisterService} from "@/api/user";
import {ElMessage} from "element-plus";

const store = useUserStore()
const dialogFormVisible = ref(false)
const form = ref({
  name: store.username,
  password: ''
})

const open = () => {
  dialogFormVisible.value = true
  form.value = {
    name: store.username,
    password: ''
  }
}
const emit = defineEmits(['success'])
defineExpose({
  open
})
const onSubmit = async () => {
  const username = form.value.name;
  const password = form.value.password;
  if (password === '') {
    ElMessage.error("请输入密码")
  } else {
    const requestData = JSON.stringify({username, password});
    const res = await userDeregisterService(requestData)
    dialogFormVisible.value = false
    if (res.code === 1) {
      ElMessage.success("注销成功")
      await store.dispatch('clearLoginInfo')
      location.reload()
      emit('success')
    } else {
      ElMessage.error(res.msg)
    }
  }


}
</script>

<template>
  <el-dialog v-model="dialogFormVisible" title="请您输入密码验证身份">
    <el-form :model="form">
      <el-form-item label="用户名" :label-width="140">
        <el-input v-model="form.name" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码" :label-width="140">
        <el-input v-model="form.password" autocomplete="off"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style>

</style>