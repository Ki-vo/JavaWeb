<script setup>
import {useStore} from "vuex";
import {ref} from "vue";

const store = useStore()
const data = ref({
  number: '123'
})

const test = async () => {
  let pinis = localStorage.getItem('user')
  console.log(pinis)
  pinis = JSON.parse(pinis)
  let token=pinis.token
  console.log(token)
}

</script>

<template>
  <el-input v-model="data.number"></el-input>
  <button @click="test">click</button>
</template>

<style scoped>

</style>