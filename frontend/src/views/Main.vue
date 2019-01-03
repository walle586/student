<template>
  <el-container>
    <el-header>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :router="true"
        @select="handleSelect"
      >
        <el-menu-item index="1" route="/main/student">学生管理</el-menu-item>
        <el-menu-item index="2" route="/main/course">课程管理</el-menu-item>
        <el-submenu index="3">
          <template slot="title">成绩管理</template>
          <el-menu-item index="3-1" route="/main/grade">成绩管理</el-menu-item>
          <el-menu-item index="3-2" route="/main/statistics">成绩统计</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-header>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "home",
  components: {},
  data() {
    return {
      activeIndex: "1"
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  computed: {
    
  }
};
</script>
<style>
body {
  margin: 0;
  padding: 0;
}
</style>
