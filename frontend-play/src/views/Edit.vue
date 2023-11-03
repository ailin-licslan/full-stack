<!--<script>

import {getInfoEdit, pageEdit} from "@/apis/table2Api/edit";
import {defineComponent} from "vue";
import Edit from "../apis/table2Api/edit";


export default defineComponent({
  computed: {
    Edit() {
      return Edit
    },
    pageEdit() {
      return pageEdit
    }
  },
  mounted: function () {
    getInfoEdit(this.$route.params.id)
  },
})
</script>-->
<!--<template>
    <a-table :dataSource="pageEdit" :columns="Edit.setup().columns" />
</template>-->


<template>
  <a-table :columns="columns" :data-source="pageEdit" bordered>

    <template v-for="col in ['name', 'age', 'address','createTime','tags','key']" #[col]="{ text, record }" :key="col">
      <div>
        <a-input
            v-if="editableData[record.key]"
            v-model:value="editableData[record.key][col]"
            style="margin: -5px 0"
        />
        <template v-else>
          {{ text }}
        </template>
      </div>
    </template>



    <template #operation="{ record }">
      <div class="editable-row-operations">
        <span v-if="editableData[record.key]">
          <a @click="save(record.key)">Save</a>
          <a-popconfirm title="Sure to cancel?" @confirm="cancel(record.key)">
            <a>Cancel</a>
          </a-popconfirm>
        </span>
        <span v-else>
          <a @click="editFun(record.key)">Edit</a>
        </span>
      </div>
    </template>
  </a-table>
</template>
<script lang="ts">
import {cloneDeep} from 'lodash-es';
import {defineComponent, reactive, ref, UnwrapRef} from 'vue';
import Edit, {getInfoEdit, pageEdit} from "../apis/table2Api/edit";

const columns = [
  {
    title: 'name',
    dataIndex: 'name',
    width: '15%',
    slots: {customRender: 'name'},
  },
  {
    title: 'age',
    dataIndex: 'age',
    width: '10%',
    slots: {customRender: 'age'},
  },
  {
    title: 'address',
    dataIndex: 'address',
    width: '20%',
    slots: {customRender: 'address'},
  },
  {
    title: 'createTime',
    dataIndex: 'createTime',
    width: '25%',
    slots: {customRender: 'createTime'},
  },
  {
    title: 'tags',
    dataIndex: 'tags',
    width: '30%',
    slots: {customRender: 'tags'},
  },

  {
    title: 'operation',
    dataIndex: 'operation',
    slots: {customRender: 'operation'},
  },
];

interface DataItem {
  key: string;
  name: string;
  age: number;
  address: string;
  createTime: string;
  tags: string;
}

const data: DataItem[] = [];

export default defineComponent({
  setup() {

    //console.log("xxxxxx  "+ JSON.stringify(edit.setup().pageEdit))
    // data.push({
    //   key: "1",
    //   name: "xxxx",
    //   age: 0,
    //   address: "xxx",
    //   time: "2023-11-02 06:57:23",
    //   tags: "xx"
    // });
    let o ={}
    debugger
    for (let i = 0; i < pageEdit.length; i++) {
      o ={
        key: pageEdit[i].id.toString(),
        name: pageEdit[i].name,
        age: pageEdit[i].age,
        address: pageEdit[i].address,
        createTime: pageEdit[i].createTime,
        tags: pageEdit[i].tags
      }
      console.log(JSON.stringify(o))

    }
    data.push(<DataItem>o)

    const dataSource = ref(data);


    //console.log("dataSource -- xxxxxx  "+ JSON.stringify(dataSource))

    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const editFun = (key: string) => {

      editableData[key] = cloneDeep(dataSource.value.filter(item => key === item.key)[0]);


      console.log("vvvvvvvvv======>"+JSON.stringify(editableData))

    };
    const save = (key: string) => {
      Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);
      delete editableData[key];
    };
    const cancel = (key: string) => {
      delete editableData[key];
    };

    return {
      columns,
      editingKey: '',
      editableData,
      editFun,
      save,
      cancel,
    };
  },

  mounted: function () {
    getInfoEdit(this.$route.params.id)
  },
  computed: {
    Edit() {
      return Edit
    },
    pageEdit() {
      return pageEdit
    }
  },
});
</script>


<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>






