<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-row :gutter="18">
          <el-col :span="6">
            <el-select v-model="type" placeholder="请选择" clearable>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="8">
            <el-input clearable placeholder="请输入搜索内容" prefix-icon="el-icon-search" v-model="query"></el-input>
          </el-col>
          <el-col :span="8">
            <el-button round @click="do_search">搜索课程</el-button>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="6">
        <el-row  type="flex" justify="end">
          <el-col :span="18">
            <el-button round type="primary" @click="to_course_add">添加课程</el-button>
            <el-button round type="danger">批量删除</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="courseCode" label="课程代码"></el-table-column>
      <el-table-column prop="courseName" label="课程名称"></el-table-column>
      <el-table-column prop="courseType" label="课程类型"></el-table-column>
      <el-table-column prop="credit" label="学分" width="180"></el-table-column>
      <el-table-column fixed="right" label="操作" width="180">
        <template slot-scope="scope">
          <!-- <el-button icon="el-icon-search" circle size="mini"></el-button> -->
          <el-button
            type="primary"
            icon="el-icon-edit"
            circle
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
          ></el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            circle
            size="mini"
            @click="handleDelete(scope.$index, scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[2,100, 200, 300, 400]"
        :page-size="page_size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page_total"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      type: "",
      options: [],
      tableData: null,
      query: "",
      currentPage: 1,
      page_size: 100,
      page_total: 0
    };
  },
  methods: {
    handleSelectionChange(val) {},
    handleSizeChange(val) {
      this.page_size = val;
      this.do_search();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.do_search();
    },
    handleEdit(index, row) {
      this.$router.push({
        name: "courseUpdate",
        params: { code: row.courseCode }
      });
    },
    handleDelete(index, row) {
      axios
        .get("/mock/deleteCourse", {
          params: { code: row.courseCode }
        })
        .then(response => {
          this.$notify({
            title: "成功",
            message: response.data,
            type: "success"
          });
        })
        .then(this.tableData.splice(index, 1));
    },
    to_course_add() {
      this.$router.push("course_add");
    },
    do_search() {
      axios
        .get("/mock/getAllCourse", {
          params: {
            type: this.type,
            q: this.query,
            page_size: this.page_size,
            page_index: this.currentPage
          }
        })
        .then(response => {
          this.tableData = response.data.table;
          this.page_size = response.data.size;
          this.page_total = response.data.total;
        })
        .then((this.query = ""));
    }
  },
  mounted: function() {
    axios
      .get("/mock/getTypes")
      .then(response => (this.options = response.data));
    this.do_search();
  }
};
</script>
