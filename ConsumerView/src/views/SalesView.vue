<script setup>
import PageContainer from "@/views/components/PageContainer.vue";
import {onMounted, ref} from 'vue'
import {getListByTypeService, productDelService} from "@/api/product";
import {Delete, Edit, Goods, List} from "@element-plus/icons-vue";
import ProductEdit from "@/views/components/ProductEdit.vue";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import {getTypeById} from "@/api/sales";
import {useUserStore} from "@/stores";
import {getBrowseLog} from "@/api/log";
import {getOrderByCateId} from "@/api/order";

const store = useUserStore()
const router = useRouter()
const loading = ref(false)
const productList = ref([])
const browserLogList = ref([])
const purchaseLogList = ref([])
const cate = ref(['汽车', '家居', '服装', '食品', '数码', '玩具'])
const sales_type = ref(null)
const menu_item = ref("1")

const getProductList = async (type) => {
  loading.value = true
  const res = await getListByTypeService(type)
  productList.value = res.data
  loading.value = false
}

const getBrowseLogList = async () => {
  const res = await getBrowseLog(store.username)
  browserLogList.value = res.data
  console.log(browserLogList.value)
}

const getPurchaseLogList = async () => {
  const res = await getOrderByCateId(sales_type.value)
  purchaseLogList.value = res.data
  console.log(purchaseLogList.value)
}

const productEditRef = ref()
const onAddProduct = () => {
  productEditRef.value.open({type: sales_type.value})
  console.log("Add")
}
const onEditProduct = (row) => {
  productEditRef.value.open(row)
  console.log("Edit" + row)
}
const onDelProduct = async (row) => {
  console.log("Del" + row)
  const res = await productDelService(row.id)
  if (res.code === 1) {
    ElMessage.success('删除成功')
  } else {
    ElMessage.error(res.msg)
  }
  await getProductList(sales_type.value)
}
const onSuccess = () => {
  getProductList(sales_type.value)
}
onMounted(async () => {
  if (store.token === null || store.tag !== "sales") {
    ElMessage.error('请先登录')
    router.push('/admin/login')
  } else {
    sales_type.value = (await getTypeById(store.username)).data
    console.log(sales_type.value)
    await getProductList(sales_type.value)
    await getBrowseLogList()
    await getPurchaseLogList()
  }
})
</script>

<template>
  <el-row>
    <el-col :span="4">
      <el-menu
          :default-active="menu_item"
          background-color="#eee"
          style="height: 630px">
        <el-menu-item index="1" @click="menu_item='1'">
          <el-icon>
            <Goods/>
          </el-icon>
          <span>商品管理</span>
        </el-menu-item>
        <el-sub-menu index="2">
          <template #title>
            <el-icon>
              <List/>
            </el-icon>
            <span>日志管理</span>
          </template>
          <el-menu-item index="2-1" @click="menu_item='2-1'">浏览日志</el-menu-item>
          <el-menu-item index="2-2" @click="menu_item='2-2'">购买日志</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-col>
    <el-col :span="20">
      <page-container title="商品管理" style="width: 100%" v-if="menu_item==='1'">
        <template #extra>
          <el-button type="primary" @click="onAddProduct">添加商品</el-button>
        </template>
        <el-table v-loading="loading" :data="productList " stripe height="520"
                  :default-sort="{ prop: 'id', order: 'descending' }">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column label="商品编号" prop="id" sortable></el-table-column>
          <el-table-column label="商品名" prop="name"></el-table-column>
          <el-table-column label="种类">
            <template #default="scoped">
              {{ cate[scoped.row.cate_id - 1] }}
            </template>
          </el-table-column>
          <el-table-column label="商家" prop="seller"></el-table-column>
          <el-table-column label="单价" prop="price" sortable></el-table-column>
          <el-table-column label="库存" prop="rest" sortable></el-table-column>
          <el-table-column label="操作" width="100">
            <template #default="{row}">
              <el-button type="primary" :icon="Edit" circle plain @click="onEditProduct(row)"></el-button>
              <el-button type="danger" :icon="Delete" circle plain @click="onDelProduct(row)"></el-button>
            </template>
          </el-table-column>

          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
        <product-edit ref="productEditRef" @success="onSuccess"></product-edit>
      </page-container>
      <page-container title="用户浏览日志" style="width: 100%" v-if="menu_item==='2-1'">
        <el-table v-loading="loading" :data="browserLogList" stripe height="520"
                  :default-sort="{ prop: 'id', order: 'descending' }">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column label="日志ID" prop="id" sortable></el-table-column>
          <el-table-column label="用户ID" prop="userId" sortable></el-table-column>
          <el-table-column label="商品ID" prop="productId"></el-table-column>
          <el-table-column label="浏览时长" prop="browseDuration"></el-table-column>
          <el-table-column label="记录时间" prop="timeStamp"></el-table-column>
          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
      </page-container>
      <page-container title="用户购买日志" style="width: 100%" v-if="menu_item==='2-2'">
        <el-table v-loading="loading" :data="purchaseLogList" stripe height="520"
                  :default-sort="{ prop: 'id', order: 'descending' }">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column label="日志ID" prop="id" sortable></el-table-column>
          <el-table-column label="用户ID" prop="userId" sortable></el-table-column>
          <el-table-column label="商品ID" prop="productId"></el-table-column>
          <el-table-column label="购买数量" prop="purchaseNum"></el-table-column>
          <el-table-column label="记录时间" prop="timeStamp"></el-table-column>
          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
      </page-container>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
</style>