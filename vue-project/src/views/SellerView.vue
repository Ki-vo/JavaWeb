<script setup>
import PageContainer from "@/views/components/PageContainer.vue";
import {onMounted, ref} from 'vue'
import {getListService, productDelService} from "@/api/product";
import {Delete, Edit} from "@element-plus/icons-vue";
import ProductEdit from "@/views/components/ProductEdit.vue";
import {ElMessage} from "element-plus";

const loading = ref(false)
const productList = ref([])
const cate = ref(['汽车', '家居', '服装', '食品', '数码', '玩具'])


const getProductList = async () => {
  loading.value = true
  const res = await getListService()
  productList.value = res.data
  loading.value = false
}

const productEditRef = ref()
const onAddProduct = () => {
  productEditRef.value.open({})
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
  await getProductList()
}
const onSuccess = () => {
  getProductList()
}
onMounted(() => {
  getProductList();
})
</script>

<template>
  <page-container title="商品管理">
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
          {{ cate[scoped.row.kind - 1]}}
        </template>
      </el-table-column>
      <el-table-column label="商家" prop="seller"></el-table-column>
      <el-table-column label="单价" prop="price" sortable></el-table-column>
      <el-table-column label="库存" prop="rest" sortable></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{row,$index}">
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
</template>

<style lang="scss" scoped>
</style>