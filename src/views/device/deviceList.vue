<template>
    <div>

        <div style="margin-bottom: -5px">
            <el-form :inline="true">
                <el-form-item label="设备名称">
                    <el-input style="width: 200px" placeholder="Please input name" v-model="params.name"></el-input>
                </el-form-item>

                <el-form-item label="设备类型">
                    <el-select v-model=params.category placeholder="请选择设备类型">
                        <el-option label="传感器类设备" value="传感器类设备"></el-option>
                        <el-option label="执行器类设备" value="执行器类设备"></el-option>
                        <el-option label="嵌入式设备" value="嵌入式设备"></el-option>
                        <el-option label="车载设备" value="车载设备"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="设备描述">
                    <el-input style="width: 200px; margin-left:5px" placeholder="Please input description" v-model="params.description"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="margin-left: 5px" size="tiny" type="primary" @click="load"><i class="el-icon-search"></i>search</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button style="margin-left: 5px" size="tiny" type="warning" @click="reset"><i class="el-icon-refresh"></i>reset</el-button>
                </el-form-item>
            </el-form>
        </div>

        <el-table :data="tableData" stripe size="medium" height="450px">
            <el-table-column width="50px"  size="medium" prop="id" label="id"></el-table-column>
            <el-table-column size="medium" prop="name" label="设备名称"></el-table-column>
            <el-table-column size="medium" prop="category" label="设备类型"></el-table-column>
            <el-table-column size="medium" prop="description" label="描述"></el-table-column>
            <el-table-column width="160px" size="medium" prop="createTime" label="创建时间" :formatter="formatData" sortable></el-table-column>
            <el-table-column width="160px" size="medium" prop="activeTime" label="更新时间" :formatter="formatData" sortable></el-table-column>

            <el-table-column size="mini"label="操作" width="240px">
                <template v-slot="scope">
                    <el-button size="mini" type="primary" @click="$router.push('/editDevice?id='+scope.row.id)">编辑</el-button>
                    <el-popconfirm
                        style="margin-left: 5px"
                        title="您确定删除此数据吗？"
                        @confirm="del(scope.row.id)"
                    >
                        <el-button size="mini" type="danger" slot="reference">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>



<!--        <div style="margin-top: 5px">-->
<!--            <el-pagination-->
<!--                    background-->
<!--                    :current-page="params.pageNum"-->
<!--                    :page-size="params.pageSize"-->
<!--                    layout="prev, pager, next"-->
<!--                    @current-change="handleCurrentChange"-->
<!--                    :total="total">-->
<!--            </el-pagination>-->
<!--        </div>-->

    </div>
</template>

<script>
import request from "@/util/request";
import Cookies from "js-cookie";

export default {
    name: "Device",
    data(){
        return{
            device: {},
            tableData:[],
            total: 10,
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
            params: {
                // pageNum: 1,
                // pageSize: 200,
                name: '',
                id: null,
                userID: 0
            }
        }
    },
    created() {
        this.load();
    },
    methods: {
        load() {
            this.params.userID = this.user.id
            request.get('/device/page',{
                params: this.params
            }).then(res => {
                if(res.code === '200'){
                    this.tableData = res.data.list
                }
            })
        },
        reset(){
            this.params = {
                pageNum: 1,
                pageSize: 8,
                name: '',
                id: null
            }
            this.load()
        },
        formatData(row, column, cellValue, index){

            //把传过来的日期进行回炉重造一下，又创建了一个js的 Date对象，进行重新构造，转为String字符串
            //最终返回 s 就可以了
            var s =	new Date(cellValue).toLocaleString();
            return s;
        },

        handleCurrentChange(pageNum){
            this.params.pageNum = pageNum
            this.load()
        },
        del(id){
            request.delete("/device/delete/" + id).then(res => {
                if(res.code === '200'){
                    this.$notify.success("success")
                    this.load()
                } else{
                    this.$notify.error(res.msg)
                }
            })
        }
    }
}
</script>
