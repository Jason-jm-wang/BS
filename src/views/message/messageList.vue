<template>
    <div>

        <div style="margin-bottom: -5px">
            <el-form :inline="true">
                <el-form-item label="所属设备编号">
                    <el-input style="width: 200px" placeholder="Please input deviceID" v-model="params.deviceID"></el-input>
                </el-form-item>

                <el-form-item label="消息类型">
                </el-form-item>
                <el-form-item label="消息描述">
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
            <el-table-column size="medium" prop="alert" label="alert">
                <template slot-scope="scope">
                    <span :class="formatStatusColor(scope.row.alert)">{{ formatStatus(scope.row.alert) }}
                        <i :class="formatIcon(scope.row.alert)"></i>
                    </span>
                </template>
            </el-table-column>
            <el-table-column width="50px"  size="medium" prop="id" label="id"></el-table-column>
            <el-table-column width="150px" size="medium" prop="msg" label="消息内容"></el-table-column>
            <el-table-column width="160px" size="medium" prop="time" :formatter="formatData" label="时间" sortable></el-table-column>
            <el-table-column size="medium" prop="deviceID" label="所属设备"></el-table-column>
            <el-table-column width="100px" size="medium" prop="lat" label="经度"></el-table-column>
            <el-table-column width="100px" size="medium" prop="lng" label="纬度"></el-table-column>
            <el-table-column width="100px" size="medium" prop="value" label="发送值"></el-table-column>

            <el-table-column size="mini"label="操作" width="240px">
                <template v-slot="scope">
                    <el-button size="mini" type="primary" @click="$router.push('/editmessage?id='+scope.row.id)">编辑</el-button>
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
        

    </div>
</template>

<style>
// 状态的三种颜色样式
.color1{
    color: aquamarine;
}
.color2{
    color: lightcoral;
}

</style>



<script>
import request from "@/util/request";
import Cookies from "js-cookie";

export default {
    name: "Message",
    data(){
        return{
            message: {},
            tableData:[
                alert
            ],
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
            params: {
                // pageNum: 1,
                // pageSize: 200,
                deviceID: '',
                id: null,
                userID: 0
            }
        }
    },
    created() {
        this.load();
    },
    methods: {
        // 格式化状态显示
        formatStatus(alert) {
            if (alert === 0) {
                return '正常'
            } else if (alert === 1) {
                return '警报'
            } else {
                return ''
            }
        },
        // 格式化状态显示的颜色
        formatStatusColor(alert) {
            if (alert === 0) {
                return 'color1'
            } else if (alert === 1) {
                return 'color2'
            } else {
                return ''
            }
        },
        formatIcon(alert) {
            if (alert === 0) {
                return 'el-icon-circle-check'
            } else if (alert === 1) {
                return 'el-icon-time'
            } else {
                return ''
            }
        },
        load() {
            this.params.userID = this.user.id
            request.get('/message/page',{
                params: this.params
            }).then(res => {
                if(res.code === '200'){
                    this.tableData = res.data.list
                }else{
                    this.$notify.error(res.msg)
                }
            })
        },
        reset(){
            this.params = {
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

        del(id){
            request.delete("/message/delete/" + id).then(res => {
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
