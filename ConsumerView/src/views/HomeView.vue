<script setup>
import {onMounted, ref} from 'vue'
import {ArrowRight} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
import {getHotProductService} from "@/api/product";

const router = useRouter()


const image = ref([])
const ids = ref([])
const names = ref([])
const cate = ref(['汽车', '家居', '服装', '食品', '数码', '玩具'])

const push_detail = (id) => {
  router.push({name: 'DetailView', params: {id: id}})
}
onMounted(async () => {
  const res = ref([])
  res.value = (await getHotProductService()).data
  for (const item in res.value) {
    ids.value.push(res.value[item]["id"])
    names.value.push(res.value[item]["name"])
    image.value.push(res.value[item]["coverImgAddr"])
  }
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <!--主界面-->
      <div class="main_window">
        <el-row class="category_roll">
          <el-col :span="6" class="category">
            <el-scrollbar>
              <el-menu class="category-menu" :router="true">
                <el-menu-item v-for="(item,index) in cate" :key="index" :index="index">
                  <span>{{ item }}</span>
                </el-menu-item>
              </el-menu>
            </el-scrollbar>
          </el-col>
          <el-col :span="18" class="roll">
            <el-carousel height="400px">
              <el-carousel-item>
                <img src="@/assets/main1.jpg" height="470" width="590" alt="CAROUSEL_IMAGE">
              </el-carousel-item>
              <el-carousel-item>
                <img src="@/assets/main2.jpg" height="470" width="590" alt="CAROUSEL_IMAGE">
              </el-carousel-item>
            </el-carousel>
          </el-col>
        </el-row>
        <br><br><br><br>
        <div class="show-more">
          <el-link href="/goods" :underline="false" style="color: grey;margin-right: 5px" disabled>查看更多
            <el-icon>
              <arrow-right/>
            </el-icon>
          </el-link>
        </div>
        <br>
        <div class="hottest">
          <div v-for="(i,index) in 8" :key="index" style="margin:0 2px 15px 2px">
            <el-card body-class="card_body" shadow="hover" @click="push_detail(ids[index])">
              <img :src=image[index] style="max-width: 80%" alt="null"/>
              <template #footer>
                {{ names[index] }}
              </template>
            </el-card>
          </div>
        </div>
      </div>
      <el-footer>

      </el-footer>
    </el-container>
  </div>
</template>

<style scoped lang="scss">
.common-layout {
  margin-top: 100px;
}

.main_window {
  padding-left: 15%;
  padding-right: 15%;
}

.category {
  height: 400px;
}

.el-carousel__item {
  background-color: grey;
  display: flex;
  justify-content: center;
  align-items: center;
}

.show-more {
  display: flex;
  justify-content: right;
  align-items: center;
}

.hottest {
  display: grid;
  grid-template-columns: repeat(4, 1fr);

}

.el-card {
  width: 100%;
  height: 100%;
}
</style>