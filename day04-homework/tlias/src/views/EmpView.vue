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
        <el-form :inline="true" :model="formEmp" class="demo-form-inline">
          <el-form-item label="姓名">
            <el-input
              v-model="formEmp.name"
              placeholder="请输入员工姓名"
            ></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="formEmp.gender" placeholder="请选择">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="入职日期">
            <el-date-picker
              v-model="formEmp.entrydate"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="name" label="姓名"> </el-table-column>
          <el-table-column prop="gender" label="性别"> </el-table-column>
          <el-table-column prop="job" label="职位">
            <template slot-scope="scope">
              {{ scope.row.gender == 1 ? "男" : "女" }}
            </template>
          </el-table-column>
          <el-table-column prop="entrydate" label="入职日期"> </el-table-column>
          <el-table-column prop="updatetime" label="最后更新时间">
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
