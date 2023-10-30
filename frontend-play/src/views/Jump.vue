<template>
  <div class="home">
    <div style="background:#ECECEC; padding:30px">
      <a-card :bordered="false" style="width: 100%">
        <a-table :columns="Projectcolumns" :data-source="Projectdata" :bordered='true' :customRow='toAbout'>
          <template slot="operation" slot-scope="text, record">详情 {{record.name}}}</template>
        </a-table>
      </a-card>
    </div>
  </div>
</template>

<script>
const Projectcolumns = [
  {
    title: '名字',
    dataIndex: 'pname',
    key: 'pname',
    align:'center',
  },{
    title: '年龄',
    dataIndex: 'age',
    key: 'age',
    width:'20%',
    align:'center',
  },{
    title: '操作',
    dataIndex: 'operation',
    key: 'operation',
    width:'20%',
    align:'center',
    scopedSlots: { customRender: 'operation' },// 直接对应插槽名为operation的模板
  }
];

const Projectdata = [];

for (let i = 0; i < 100; i++) {
  Projectdata.push({
    key: i.toString(),
    pname: `Edrward ${i}`,
    age: 32,
  });
}

export default {
  name: 'Home',
  components: {
    // HelloWorld
  },
  data() {
    return {
      Projectdata,
      Projectcolumns,
    };
  },
  methods: {
    toAbout(record, index){
      return {
        on: {
          click: () => {
            console.log(record,index)
            this.$router.push({
              path:'/about'
            })
          }
        }
      }
    },
  },
}
</script>
