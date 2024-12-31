<template>
  <div>
  <!-- 头部区域 -->
    <div style="height: 60px;line-height: 60px;background-color: bisque; margin-bottom: 2px;display: flex">
        <div style="width: 300px">
          <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px; left: 20px">
          <span style="margin-left: 20px; font-size: 24px; font-palette: light">设备管理系统</span>
        </div>
        <div style="flex: 1; text-align: right; padding-right: 20px">
            <el-dropdown size="medium">
              <span class="el-dropdown-link" style="cursor: pointer">
                  {{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <el-dropdown-menu slot="dropdown" style="margin-top: -5px">
                    <el-dropdown-item><div style="text-align: center" @click="logout">退出</div></el-dropdown-item>
                    <el-dropdown-item>狮子头</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
  <!-- 头部区域 -->
    <div style="display: flex">
        <div style="width: 150px; min-height: calc(100vh - 62px); overflow: hidden; margin-right: 2px; background-color: bisque">
            <el-menu :default-active="$route.path === '/' ? $route.path : $route.path.substring(1)" :default-operands="['/']" router class="el-menu-demo">
                <el-menu-item index="/">
                    <i class="el-icon-eleme"></i>
                    <span>首页</span>
                </el-menu-item>
                <el-submenu index="/">
                    <template slot="title">
                        <i class="el-icon-question"></i>
                        <span>用户</span>
                    </template>
                    <el-menu-item index="user">用户列表</el-menu-item>
                    <el-menu-item index="addUser">用户添加</el-menu-item>
                </el-submenu>
                <el-submenu index="/">
                    <template slot="title">
                        <i class="el-icon-question"></i>
                        <span>我的设备</span>
                    </template>
                    <el-menu-item index="device">设备列表</el-menu-item>
                    <el-menu-item index="addDevice">设备添加</el-menu-item>
                </el-submenu>
                <el-menu-item index="/message">消息列表</el-menu-item>
                <el-menu-item index="4">设备信息</el-menu-item>
                <el-menu-item index="/deviceStatics">统计信息</el-menu-item>
            </el-menu>
        </div>
        <div style="flex: 1; background-color: beige">

            <router-view/>
        </div>

    </div>
  </div>
</template>

<script>


import request from "@/util/request";
import Cookies from 'js-cookie'
export default {
    name: "Layout",
    data() {
        return {
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
        }
    },
    methods: {
        logout() {
            Cookies.remove('user')
            this.$router.push('/login')
        }
    }
}
</script>