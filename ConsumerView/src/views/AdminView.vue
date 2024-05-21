<script setup>
import {Delete, Edit, RefreshRight} from "@element-plus/icons-vue";
import PageContainer from "@/views/components/PageContainer.vue";
import {useUserStore} from "@/stores";
import {ElMessage, ElMessageBox} from "element-plus";
import {onMounted, ref} from "vue";
import {addSalesService, deleteSalesService, getListService, resetPasswordService} from "@/api/sales";
import router from "@/router";
import {getAllProductService} from "@/api/product";
import {deleteBrowseLog, getOperateLog} from "@/api/log";
import {getTotalRevenueService} from "@/api/revenue";

const store = useUserStore()
const loading = ref(false)
const menu_item = ref('1')
const addSalesVisible = ref(false);
const default_pwd = '1234'
const cate = ref(['汽车', '家居', '服装', '食品', '数码', '玩具'])
const roles = ref({
  "user": "顾客",
  "sales": "销售人员",
  "admin": "管理员"
})
const cate_id = ref('')
const salesmanList = ref([])
const productList = ref([])
const revenueList = ref([])
const operateLogList = ref([])

const filter_arr = ref([])
const filter_value = ref([])
for (const index in cate.value) {
  filter_arr.value.push({text: cate.value[index], value: index})
}
const filterHandler = (value, row) => {
  return (row['cate_id'] - 1).toString() === value
}

const getSalesList = async () => {
  loading.value = true
  const res = await getListService()
  salesmanList.value = res.data
  loading.value = false
}

const getProductList = async () => {
  loading.value = true
  const res = await getAllProductService()
  productList.value = res.data
  loading.value = false
}

const getRevenueList = async () => {
  loading.value = true
  const res = await getTotalRevenueService()
  revenueList.value = res.data
  loading.value = false
}

const getOperateLogList = async () => {
  loading.value = true
  const res = await getOperateLog()
  operateLogList.value = res.data
  loading.value = false
}

const onAddSales = async () => {
  const res = await addSalesService(cate_id.value + 1)
  if (res.code === 1) {
    ElMessage.success("账号添加成功")
  } else {
    ElMessage.error("账号添加失败:" + res.msg)
  }
  addSalesVisible.value = false
  await getSalesList()
}

const onDelSales = (row) => {
  ElMessageBox.confirm(
      '确定删除该账号吗？',
      '删除账号',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await deleteSalesService(row.id)
    if (res.code === 1) {
      ElMessage.success('删除成功')
    } else {
      ElMessage.error(res.msg)
    }
    await getSalesList()
  })
}

const onResetPassword = (row) => {
  ElMessageBox.confirm(
      '确定重置该账号密码吗？',
      '重置密码',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await resetPasswordService(row.id)
    if (res.code === 1) {
      ElMessage.success('重置成功')
      location.reload()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const onDelOperateLog = (row) => {
  ElMessageBox.confirm(
      '确定删除该条日志吗？',
      '删除日志',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(async () => {
    const res = await deleteBrowseLog(row.id)
    if (res.code === 1) {
      ElMessage.success('删除成功')
    } else {
      ElMessage.error(res.msg)
    }
    await getOperateLogList()
  })
}

onMounted(async () => {
  if (store.token === null || store.tag !== "admin") {
    ElMessage.error('请先登录')
    router.push('/admin/login')
  } else {
    await getSalesList()
    await getProductList()
    await getRevenueList()
    await getOperateLogList()
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
          <span>销售人员账号管理</span>
        </el-menu-item>
        <el-menu-item index="2" @click="menu_item='2'">
          <span>商品销售业绩</span>
        </el-menu-item>
        <el-menu-item index="3" @click="menu_item='3'">
          <span>商品管理</span>
        </el-menu-item>
        <el-menu-item index="4" @click="menu_item='4'">
          <span>操作日志</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="20">
      <page-container title="销售人员账户管理" style="width: 100%" v-if="menu_item==='1'">
        <template #extra>
          <el-button type="primary" @click="addSalesVisible=true">添加账户</el-button>
        </template>
        <el-table v-loading="loading" :data="salesmanList" stripe
                  :default-sort="{ prop: 'id' }">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column label="账户ID" prop="id" sortable width="150"></el-table-column>
          <el-table-column label="负责商品类别" align="center">
            <template #default="scoped">
              {{ cate[scoped.row.type - 1] }}
            </template>
          </el-table-column>
          <el-table-column label="当前密码状态" align="center">
            <template #default="{row}">
              <div v-if="default_pwd===row.password">默认</div>
              <div v-else>已修改</div>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template #default="{row}">
              <el-button type="primary" :icon="RefreshRight" circle plain
                         @click="onResetPassword(row)"></el-button>
              <el-button type="danger" :icon="Delete" circle plain @click="onDelSales(row)"></el-button>
            </template>
          </el-table-column>
          <el-table-column label="业绩" align="center">
            <template #default="{row}">
              <el-button plain @click="()=>{
                filter_value.splice(0,filter_value.length);
                filter_value.push((row.type-1).toString());
                menu_item='2';
              }">打开
              </el-button>
            </template>
          </el-table-column>

          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
      </page-container>
      <page-container title="商品销售业绩" style="width: 100%" v-if="menu_item==='2'">
        <el-table v-loading="loading" :data="revenueList" stripe sum-text="合计"
                  :default-sort="{ prop: 'id' }">
          <el-table-column type="index" label="序号" width="100"></el-table-column>
          <el-table-column label="商品编号" prop="productId" sortable>
          </el-table-column>
          <el-table-column label="商品种类" :filters="filter_arr" :filter-multiple="false"
                           :filter-method="filterHandler" :filtered-value="filter_value">
            <template #default="scoped">
              {{ cate[scoped.row.cate_id - 1] }}
            </template>
          </el-table-column>
          <el-table-column label="销售量" prop="quantity" sortable></el-table-column>
          <el-table-column label="销售额" prop="totalPrice" sortable></el-table-column>
          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
      </page-container>
      <page-container title="商品管理" style="width: 100%" v-if="menu_item==='3'">
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
          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
      </page-container>
      <page-container title="操作日志" style="width: 100%" v-if="menu_item==='4'">
        <el-table v-loading="loading" :data="operateLogList " stripe height="520"
                  :default-sort="{ prop: 'id', order: 'descending' }">
          <el-table-column label="日志序号" prop="id" sortable width="150"></el-table-column>
          <el-table-column label="用户ID" prop="userId"></el-table-column>
          <el-table-column label="角色">
            <template #default="scoped">
              {{ roles[scoped.row.role] }}
            </template>
          </el-table-column>
          <el-table-column label="IP地址" prop="ipAddress"></el-table-column>
          <el-table-column label="操作内容" prop="operation"></el-table-column>
          <el-table-column label="操作时间" prop="operationTime" sortable></el-table-column>
          <el-table-column label="操作" width="100">
            <template #default="{row}">
              <el-button type="danger" :icon="Delete" circle plain @click="onDelOperateLog(row)"></el-button>
            </template>
          </el-table-column>
          <template #empty>
            <el-empty description="没有数据"></el-empty>
          </template>
        </el-table>
      </page-container>
    </el-col>
    <el-dialog v-model="addSalesVisible" title="新增销售" :draggable="true" width="300">
      <el-select v-model="cate_id" placeholder="请选择管理的商品类别">
        <el-option v-for="(item,index) in cate" :key="index" :label="item" :value="index"></el-option>
      </el-select>
      <template #footer>
        <el-button @click="addSalesVisible = false">取消</el-button>
        <el-button type="primary" @click="onAddSales">
          确认
        </el-button>
      </template>
    </el-dialog>
  </el-row>
</template>
<style lang="scss" scoped>
</style>