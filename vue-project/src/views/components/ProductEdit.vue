<script setup>
import {ref} from 'vue'
import {Plus} from "@element-plus/icons-vue";
import {productAddService, productGetByIdService} from "@/api/product";
import {ElMessage} from "element-plus";
import {baseURL} from "@/utils/jwt";

const emit = defineEmits(['success'])

const visibleDrawer = ref(false)

const formRef = ref()
const formModel = ref({
  id: 0,
  name: '',
  cate_id: 1,
  seller: '',
  price: 0.00,
  rest: 0,
  cover_img: '',
  detail_img: ''
})
const cate = ref(['汽车', '家居', '服装', '食品', '数码', '玩具'])

//上传图片逻辑
const coverImgUrl = ref('')
const onUploadCoverImg = (uploadFile) => {
  coverImgUrl.value = URL.createObjectURL(uploadFile.raw)
  formModel.value.cover_img = uploadFile.raw
}

const detailImgUrl = ref('')
const onUploadDetailImg = (uploadFile) => {
  detailImgUrl.value = URL.createObjectURL(uploadFile.raw)
  formModel.value.detail_img = uploadFile.raw
}
const open = async (row) => {
  visibleDrawer.value = true
  coverImgUrl.value = ''
  detailImgUrl.value = ''
  if (row.id) {
    console.log("ID:" + row.id)
    const res = await productGetByIdService(row.id)
    console.log(res.data)
    const {id, name, seller, price, rest} = res.data
    const cate_id = res.data.kind
    const cover_img = res.data.coverImgAddr
    const detail_img = res.data.detailImgAddr
    formModel.value = {id, name, cate_id, seller, price, rest, cover_img, detail_img}
    coverImgUrl.value = baseURL + cover_img
    detailImgUrl.value = baseURL + detail_img
  } else {
    formModel.value = {
      id: 0,
      name: '',
      cate_id: 1,
      seller: '',
      price: 0.00,
      rest: 0,
      cover_img: '',
      detail_img: ''
    }

  }
}


defineExpose({
  open
})


//提交
const onSubmit = async () => {
  const fd = new FormData()
  for (let key in formModel.value) {
    fd.append(key, formModel.value[key])
  }
  if (formModel.value.id) {
    console.log("Edit")
    visibleDrawer.value = false
  } else {
    //request
    console.log(formModel.value)
    console.log(fd)
    const res = await productAddService(fd)
    if (res.code === 1) {
      ElMessage.success('添加成功')
    } else {
      ElMessage.error("ERROR:" + res.msg)
    }
    visibleDrawer.value = false
    emit('success', 'add')
  }
}

</script>

<template>
  <el-drawer v-model="visibleDrawer" :title="formModel.id?'编辑商品':'添加商品'" size="50%">
    <el-form :model="formModel" ref="formRef" label-width="100px">
      <el-form-item label="商品名" prop="name">
        <el-input v-model="formModel.name" placeholder="请输入商品名"></el-input>
      </el-form-item>
      <el-form-item label="商品类型" prop="cate_id">
        <el-select v-model="formModel.cate_id" placeholder="请选择商品类型">
          <el-option v-for="i in 6" :key="i" :label="cate[i-1]" :value="i"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商家" prop="seller">
        <el-input v-model="formModel.seller" placeholder="请输入商家"></el-input>
      </el-form-item>
      <el-form-item label="单价" prop="price">
        <el-input-number v-model="formModel.price" :precision="2" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label="库存" prop="rest">
        <el-input-number v-model="formModel.rest" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label="封面" prop="cover_img">
        <el-upload
            class="avatar-uploader"
            :auto-upload="false"
            :show-file-list="false"
            :on-change="onUploadCoverImg"
        >
          <img v-if="coverImgUrl" :src="coverImgUrl" class="avatar"/>
          <el-icon v-else class="avatar-uploader-icon">
            <Plus/>
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="详情图" prop="detail_img">
        <el-upload
            class="avatar-uploader"
            :auto-upload="false"
            :show-file-list="false"
            :on-change="onUploadDetailImg"
        >
          <img v-if="detailImgUrl" :src="detailImgUrl" class="avatar"/>
          <el-icon v-else class="avatar-uploader-icon">
            <Plus/>
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style lang="scss" scoped>
.avatar-uploader {
  :deep {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}
</style>