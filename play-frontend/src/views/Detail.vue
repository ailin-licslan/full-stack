<script>

import {getInfo, pageDetail} from "../apis/table2Api/detail";
import {defineComponent} from "vue";
import Detail from "../apis/table2Api/detail";


export default defineComponent({
  computed: {
    Detail() {
      return Detail
    },
    pageDetail() {
      return pageDetail
    }
  },
  mounted: function () {
    getInfo(this.$route.params.id)
  },
})
</script>
<template>
  <a-table :dataSource="pageDetail" :columns="Detail.setup().columns"/>
  <router-link to="/boot">
    <button>返回列表页</button>
  </router-link>

</template>

<style scoped>

</style>