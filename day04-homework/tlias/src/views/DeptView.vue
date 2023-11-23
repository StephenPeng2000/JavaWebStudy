<template>
  <el-container>
    <el-header style="font-size: 40px; background-color: rgb(238, 241, 246)">
      tlias 智能学习辅助系统
    </el-header>
    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1']">
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-message"></i>系统信息管理</template
            >
            <el-menu-item index="1-1">
              <router-link to="/emp">员工管理</router-link></el-menu-item
            >

            <el-menu-item index="1-2">
              <router-link to="/dept">部门管理</router-link></el-menu-item
            >
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="name" label="部门"> </el-table-column>
          <el-table-column prop="updatetime" label="最后操作时间">
          </el-table-column>
          <el-table-column fixed="right" label="操作">
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="primary"
                size="small"
                >编辑</el-button
              >
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <span class="demonstration"></span>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400"
          >
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      tableData: [],
      formEmp: {
        name: "",
        gender: "",
        job: "",
        entrydate: "",
        //入职日期有开始日期和结束日期，因此这里是一个数组
        updatetime: [],
      },
    };
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
  },
  mounted() {
    axios
      .get("http://yapi.eehp.cn/mock/61/user/findAll")
      .then((res) => (this.tableData = res.data.data));
  },
};
</script>

<style></style>
