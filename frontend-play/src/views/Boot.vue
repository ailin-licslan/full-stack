<!--JavaScript-->
<script lang="ts" setup>

import {pageData} from '../apis/table1Api/getList';
import table from "../apis/table1Api/table1";
import table2 from "../apis/table2Api/table2";
import table3 from "../apis/table3Api/table3";
import type {UnwrapRef} from 'vue';
import {computed, reactive} from 'vue';
import {CheckOutlined, EditOutlined} from '@ant-design/icons-vue';
import {cloneDeep} from 'lodash-es';
import {table2Remove} from "../apis/table2Api/delete";
import {onSearch, pageDataTab} from "../apis/table2Api/search";
import {defineComponent} from "vue/dist/vue";

interface DataItem {
  key: string;
  name: string;
  age: number;
  address: string;
}


const columns = [
  {
    title: 'name',
    dataIndex: 'name',
    width: '30%',
  },
  {
    title: 'age',
    dataIndex: 'age',
  },
  {
    title: 'address',
    dataIndex: 'address',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];


const count = computed(() => table3.setup().dataSource.value.length + 1);
const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});


const edit = (key: string) => {
  editableData[key] = cloneDeep(table3.setup().dataSource.value.filter(item => key === item.key)[0]);
};


const save = (key: string) => {
  // @ts-ignore
  Object.assign(table3.setup().dataSource.value.filter(item => key === item.key)[0], editableData[key]);
  delete editableData[key];
};


const onDelete = (key: string) => {
  table3.setup().dataSource.value = table3.setup().dataSource.value.filter(item => item.key !== key);
};


const handleAdd = () => {
  const newData = {
    key: `${count.value}`,
    name: `Edward King ${count.value}`,
    age: 32,
    address: `London, Park Lane no. ${count.value}`,
  };
  table3.setup().dataSource.value.push(newData);
};










</script>


<!--列表页处理-->
<template>

  <!--表格一测试-->
  <a-table :dataSource="pageData" :columns="table.setup().columns"/>




  <span><div> </div></span> <span><div> </div></span>
  <span><div> </div></span> <span><div> </div></span>


  <!--表格二测试-->
  <a-input-search
      v-model:value="value"
      placeholder="input search text"
      enter-button
      style="width: 500px"
      @search="onSearch(value)"
  />
  <a-button style="margin-left: 700px" type="primary">新增</a-button>
  <a-table :columns="table2.setup().columns" :data-source="pageDataTab" :customRow='toAbout'>

    <template #name="{ text }">
      <a>{{ text }}</a>
    </template>

    <template #customTitle>
    <span>
      姓名
    </span>
    </template>

    <template #tags="{ text: tags }">
    <span>
      <a-tag
          v-for="tag in tags"
          :key="tag"
          :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
      >
        {{ tag.toUpperCase() }}
      </a-tag>
    </span>

    </template>


    <template #time="{ text }">
      <a>{{ text }}</a>
    </template>


    <template #action="{ record }">
    <span>

      <!--  详情接口页面 TODO    -->
<!--      <router-link :to="'/saas-client/detail/'+scope.row.id">-->
<!--      <router-link :to="{path:'/detail/'} + record.id">{{ record.name }}</router-link>-->


      <router-link :to="'/detail/' + record.id">{{ record.name }}</router-link>








<!--      <a>{{ record.name }}</a>-->
      <a-divider type="vertical"/>


      <!--      删除功能-->
       <a-popconfirm
           v-if="table2.setup().data"
           title="Sure to delete?"
           @confirm="table2Remove(record.id)"
       >
          <a>删除</a>
        </a-popconfirm>
      <a-divider type="vertical"/>



      <!--  删除功能  TODO  -->
      <router-link :to="'/edit/' + record.id"><a class="ant-dropdown-link">编辑</a></router-link>


    </span>
    </template>


  </a-table>
















  <span><div> </div></span> <span><div> </div></span>
  <span><div> </div></span> <span><div> </div></span>


  <!--表格三测试-->
<!--  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="handleAdd">新增按钮</a-button>
  <a-table bordered :data-source="table3.setup().dataSource.value" :columns="columns">

    <template #bodyCell="{ column, text, record }">
      <template v-if="column.dataIndex === 'name'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].name" @pressEnter="save(record.key)"/>
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)"/>
          </div>

          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)"/>
          </div>

        </div>
      </template>

      <template v-else-if="column.dataIndex === 'operation'">
        <a-popconfirm
            v-if="table3.setup().dataSource.value.length"
            title="Sure to delete?"
            @confirm="onDelete(record.key)"
        >
          <a>Delete</a>
        </a-popconfirm>
      </template>
    </template>

  </a-table>-->


</template>


<style scoped>
.editable-cell {
  position: relative;

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    margin-top: 4px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}
</style>







