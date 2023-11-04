<template>
  <a-table :columns="columns" :data-source="dataSource" bordered>
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
          <a @click="edit(record.key)">Edit</a>
        </span>
      </div>
    </template>
  </a-table>

  <router-link to="/boot">
    <button>返回列表页</button>
  </router-link>

</template>
<script lang="ts">
import { cloneDeep } from 'lodash-es';
import { defineComponent, reactive, ref, UnwrapRef } from 'vue';
import Edit, {editUpdate, getInfoEdit, pageEdit} from "../apis/table2Api/edit";

const columns = [
  {
    title: 'name',
    dataIndex: 'name',
    width: '25%',
    slots: { customRender: 'name' },
  },
  {
    title: 'age',
    dataIndex: 'age',
    width: '15%',
    slots: { customRender: 'age' },
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
    slots: { customRender: 'operation' },
  },
];
interface DataItem {
  key: string;
  name: string;
  age: number;
  address: string;
  createTime: string;
  tags:[]
}
const data: DataItem[] = [];
// for (let i = 0; i < 100; i++) {
//   data.push({
//     key: i.toString(),
//     name: `Edrward ${i}`,
//     age: 32,
//     address: `London Park no. ${i}`,
//     createTime:'',
//     tags:[]
//   });
// }
export default defineComponent({
  setup() {
    let o ={}
    //debugger
    for (let i = 0; i < pageEdit.length; i++) {
      data.push({
        key: pageEdit[i].id.toString(),
        name: pageEdit[i].name,
        age: pageEdit[i].age,
        address: pageEdit[i].address,
        createTime: pageEdit[i].createTime,
        tags: pageEdit[i].tags
      })

      o = {
        key: pageEdit[i].id.toString(),
        name: pageEdit[i].name,
        age: pageEdit[i].age,
        address: pageEdit[i].address,
        createTime: pageEdit[i].createTime,
        tags: pageEdit[i].tags
      }
      console.log("object:  "+JSON.stringify(o))

    }

    const dataSource = ref(data);

    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const edit = (key: string) => {
      //debugger
      editableData[key] = cloneDeep(dataSource.value.filter(item => key === item.key)[0]);
      console.log("EDIT is :", JSON.stringify(editableData))
    };
    const save = (key: string) => {
      Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);
      //console.log("save is :", JSON.stringify(editableData)+ "datasource is :"+ JSON.stringify(dataSource.value))

      for (let i = 0; i < dataSource.value.length; i++) {
        let obj = {
          id: Number(dataSource.value[i].key),
          name: dataSource.value[i].name,
          age: dataSource.value[i].age,
          address: dataSource.value[i].address,
          createTime: dataSource.value[i].createTime,
          tags: dataSource.value[i].tags
        }
        //调用保存接口
        editUpdate(obj)

      }






      delete editableData[key];
      console.log("delete is :", JSON.stringify(editableData))



    };
    const cancel = (key: string) => {
      delete editableData[key];
    };



    return {
      dataSource,
      columns,
      editingKey: '',
      editableData,
      edit,
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



},




);
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
