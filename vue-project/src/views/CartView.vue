<script setup>
import {useUserStore, useCartStore} from "@/stores";
import {onMounted, ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {ArrowLeft} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";

const router = useRouter()

const userStore = useUserStore()
const cartStore = useCartStore()
let goods = cartStore.cart
let goodsData = ref(goods)
let count = goods.length
let selectedCount = ref(0)
let selectedPrice = ref(0)
let selectedGoods = ref([])
const updateData = function () {
  let count = 0
  let sum = 0
  let selected = selectedGoods.value
  for (const index in selected) {
    count += selected[index].num
    sum += selected[index].num * selected[index].price
  }
  selectedCount.value = count
  selectedPrice.value = sum
}
const updateSelected = function (selection) {
  selectedGoods.value = []
  for (const index in selection) {
    selectedGoods.value.push(selection[index])
  }
  updateData()
}
const delRow = function (index) {
  goodsData.value.splice(index, 1)

}
const submit = function () {
  if (selectedGoods.value.length === 0) {
    ElMessage.error("请勾选您要购买的商品")
  } else {
    ElMessageBox.confirm(
        '你确定要提交订单吗？',
        '确认消息',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: "warning"
        }
    ).then(() => {
      ElMessage.success("订单已提交")
      console.log("发送订单信息")
    })
  }
}
onMounted(() => {
  if (userStore.token === null) {
    ElMessage.error("请先登录")
    router.push('/login')
  } else {
    // good={
    //   img:'',
    //   name:'',
    //   price:0,
    //   num:0
    // }
    goods = cartStore.cart
    count = goods.length;
  }
})
</script>

<template>
  <el-container>
    <el-main class="shopping-cart">
      <el-row class="title">
        <el-col :span="2">
          <el-button @click="this.$router.go(-1)">
            <el-icon>
              <arrow-left/>
            </el-icon>
          </el-button>
        </el-col>
        <el-col :span="2">购物车</el-col>
      </el-row>
      <br><br>
      <el-scrollbar max-height="500px">

        <el-table :data="goodsData" border style="width: 100%" class="good" @selection-change="updateSelected">
          <el-table-column fixed type="selection" width="55" align="center"></el-table-column>
          <el-table-column label="商品图" align="center">
            <template #default="scoped">
              <el-image :src=scoped.row.cover_img style="height: 100px;width: 100px"/>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="商品名" align="center"/>
          <el-table-column prop="price" label="商品单价" align="center"/>
          <el-table-column label="商品数量" width="200" align="center">
            <template #default="scoped">
              <el-input-number :min="1" v-model="scoped.row.num" @change="updateData"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template #default="scoped">
              <el-button type="danger" @click="delRow(scoped.$index)">删除</el-button>
            </template>
          </el-table-column>
          <template #empty>
            <el-empty description="没有商品"></el-empty>
          </template>
        </el-table>

      </el-scrollbar>
      <br><br>
      <el-row>
        <el-col class="submit-button" :span="6">
          <el-button type="primary" @click="submit">提交订单</el-button>
        </el-col>
        <el-col :span="6"></el-col>
        <el-col :span="6">商品数量：{{ selectedCount }}</el-col>
        <el-col :span="6">合计价格：{{ selectedPrice }}</el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<style>
.shopping-cart {
  padding-left: 15%;
  padding-right: 15%;
  height: 100%;
}


.title {
  display: flex;
  text-align: center;
  align-items: center;
}


</style>