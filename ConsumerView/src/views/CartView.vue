<script setup>
import {useUserStore, useCartStore} from "@/stores";
import {onMounted, ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {ArrowLeft} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
import {addOrder, getPayStatus} from "@/api/order";

const dialogFormVisible = ref(false)

//路由以及存储
const router = useRouter()
const userStore = useUserStore()
const cartStore = useCartStore()

let goods = cartStore.cart
let goodsData = ref(goods)
let selectedCount = ref(0)
let selectedPrice = ref(0)
let selectedGoods = ref([])
//更新选择项的data
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
//更新选择项
const updateSelected = function (selection) {
  selectedGoods.value = []
  for (const index in selection) {
    selectedGoods.value.push(selection[index])
  }
  updateData()
}
//删除一行商品
const delRow = function (index) {
  goodsData.value.splice(index, 1)
}
//清空购物车
const clear = function () {
  goodsData.value = []
  cartStore.clearProduct()
}
//提交订单
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
      dialogFormVisible.value = true
    })
  }
}
const sendOrder = async function () {
  let res = await getPayStatus()
  if (res === 0) {
    ElMessage.error("接口出错")
  } else {
    let status = res.data
    if (status === "success") {
      dialogFormVisible.value = false
      const requestData = []
      const selected = selectedGoods.value
      console.log(selected)
      for (let index in selected) {
        const userId = userStore.username
        const productId = selected[index].id
        const productPrice = selected[index].price
        const purchaseNum = selected[index].num
        requestData.push({
          userId, productId, productPrice, purchaseNum
        })
      }
      console.log(requestData)
      console.log("发送订单信息")
      const result = await addOrder(requestData)
      if (result.code === 1) {
        ElMessage.success("订单已提交")
      } else {
        ElMessage.error("订单提交失败")
      }
    } else {
      ElMessage.info("支付尚未完成，请稍后重试")
    }
  }


}

onMounted(() => {
  if (userStore.token === null || userStore.tag !== "user") {
    ElMessage.error("请先登录")
    router.push('/login')
  } else {
    goods = cartStore.cart
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
        <el-col :span="8"></el-col>
        <el-col :span="4">商品数量：{{ selectedCount }}</el-col>
        <el-col :span="4">合计价格：{{ selectedPrice }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="18"/>
        <el-col class="submit-button" :span="6">
          <el-button type="danger" @click="clear">清空购物车</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>

  <el-dialog v-model="dialogFormVisible" title="支付方式" width="500">
    <el-row>
      <el-col :span="6">
        <el-button size="large">微信支付</el-button>
      </el-col>
      <el-col :span="6">
        <el-button size="large">支付宝</el-button>
      </el-col>
    </el-row>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="sendOrder">
          我已完成支付
        </el-button>
      </div>
    </template>
  </el-dialog>
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