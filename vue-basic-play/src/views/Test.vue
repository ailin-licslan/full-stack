<script setup>
import TestData from "../data/TestData";
</script>

<template>
  <main>
    Test is : {{ TestData.setup().data }}
    <br>
    Age is : {{ TestData.setup().data.age }}
    <br>
    Name is : {{ TestData.setup().data.name }}
    <br>
    A is : {{ TestData.setup().a }}
    B is : {{ TestData.setup().b }}
    <br>
    <button v-on:click="TestData.setup().edit()">修改</button>
    <br>
    name is : {{ TestData.setup().data.name }}
    <br>
    回车: <input type="text" @keyup.enter="TestData.setup().add(20,5)">
    <br>
    {{ TestData.setup().data.show }}
    <br>
    <button @click="TestData.setup().toggle">切换显示</button>
    <br>
    aa is {{ TestData.setup().aa }}
    <br>
    <button @click="TestData.setup().increment"> 点击 -
      {{ TestData.setup().count }}
    </button>
    <br>
    <p v-show="TestData.setup().data.show">www.badi.com</p>


    <br>
    <!--  频繁删除dom元素  会影响性能  频繁切换状态的话  选择 v-show  -->
    <p v-if="TestData.setup().data.show">www.badi.com--if</p>

    <br>
    <p v-if="TestData.setup().data.age === 1">www.badi.com+b</p>
    <p v-else-if="TestData.setup().data.age > 1 && TestData.setup().data.age<=101">www.badi.com+1</p>
    <p v-else>www.badi.com+100</p>

    <br>
    <h3>www.test.com</h3>
    <input type="text" v-bind:value="TestData.setup().web.url">

    <h3>src="../image/waya.jpg"</h3>
    <img :src="TestData.setup().web.img">

    <h3>class="textColor"</h3>
    <b v-bind:class="{textColor:TestData.setup().web.fontStatus}">vue学习</b>
    <br>



    <span>v-for 遍历学习</span>
    <ul>
      <li v-for="value in TestData.setup().dataSum.num">
        {{value}}
      </li>
    </ul>

    <ul>
      <li v-for="(value,index) in TestData.setup().dataSum.num">
        value===>{{value}}, index====>{{index}}
      </li>
    </ul>

    <ul>
      <li v-for="(value,key,index) in TestData.setup().dataSum.person">
       key===>{{key}}, value===>{{value}}, index====>{{index}}
      </li>
    </ul>

    <ul>
      <template v-for="(value,key,index) in TestData.setup().dataSum.person">
        <li v-if="index===1">
          key===>{{key}}, value===>{{value}}, index====>{{index}}
        </li>
      </template>
    </ul>

    <ul>
      <li v-for="(value,index) in TestData.setup().dataSum.teacher"
          :title="value.name" :key="value.id">
        value.id===>{{value.id}},
        value.name===>{{value.name}}
        value.web===>{{value.web}}
        index====>{{index}}
      </li>
    </ul>

    <span>count is ====> {{TestData.setup().count.value}}</span>
    <span>单向数据绑定: <input type="text" :value="TestData.setup().count.value"></span>
    <span>双向数据绑定: <input type="text" v-model="TestData.setup().count.value"></span>
    <span>双向数据绑定Lazy化: <input type="text" v-model.lazy="TestData.setup().count.value"></span>

    <span>v-text & v-html</span>

    <h3 v-text="TestData.setup().dataSum.url"></h3>

    <h3 v-html="TestData.setup().dataSum.url"></h3>

    <span>computed 学习 有缓存</span>
    <h3>add: {{TestData.setup().add2()}}</h3>
    <h3>add: {{TestData.setup().add2()}}</h3>

    <h3>sub: {{TestData.setup().sub}}</h3>
    <h3>sub: {{TestData.setup().sub}}</h3>


    <span>Watch学习</span>
    <br>

    爱好
    <select v-model="TestData.setup().hobby">
      <option value="">请选择</option>
      <option value="1">篮球</option>
      <option value="2">足球</option>
      <option value="3">游泳</option>
    </select>
    年
    <select v-model="TestData.setup().date.year">
      <option value="">请选择</option>
      <option value="2021">2021</option>
      <option value="2022">2022</option>
      <option value="2023">2023</option>
    </select>
    月
    <select v-model="TestData.setup().date.month">
      <option value="">请选择</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
    </select>






  </main>

  <div>


  </div>

</template>

<style>
.textColor {
  color: #108ee9;
}
</style>