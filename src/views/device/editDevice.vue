<template>
    <div style="padding: 20px">
        <div style="padding: 20px">编辑设备</div>
        <el-form :inline="true" :model="form">
            <el-form-item label="设备名称">
                <el-input style="width: 200px" placeholder="Please input name" v-model="form.name"></el-input>
            </el-form-item>

            <el-form-item label="设备类型">
                <el-select v-model=form.category placeholder="请选择设备类型">
                    <el-option label="传感器类设备" value="传感器类设备"></el-option>
                    <el-option label="执行器类设备" value="执行器类设备"></el-option>
                    <el-option label="嵌入式设备" value="嵌入式设备"></el-option>
                    <el-option label="车载设备" value="车载设备"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="创建时间">
                <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model="date" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                    <el-time-picker type="fixed-time" placeholder="选择时间" v-model="date" style="width: 100%;"></el-time-picker>
                </el-col>
            </el-form-item>
            <el-form-item label="设备描述">
                <el-input style="width: 200px; margin-left:5px" placeholder="Please input description" v-model="form.description"></el-input>
            </el-form-item>
        </el-form>

        <div style="text-align: center;margin-top: 30px">
            <el-button type="primary" @click = "save">保存</el-button>
            <el-button type="danger" @click = "$router.push('/user')">取消</el-button>
        </div>
    </div>
</template>

<script>
import request from "@/util/request";
import router from "@/router";
import Cookies from "js-cookie";
export default {
    name: "editDevice",
    data(){
        return{
            date: Date(),
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
            form: {}
        }
    },
    created() {
        const id = this.$route.query.id
        request.get("/device/" + id).then(res => {
            this.form = res.data
        })
    },
    methods: {
        router() {
            return router
        },
        save() {
            this.form.userID = this.user.id
            this.form.createTime = new Date(this.date).getTime();
            request.put('/device/update',this.form).then(res => {
                if(res.code === '200') {
                    this.$notify.success('success')
                    this.$router.push("/device")
                } else{
                    this.$notify.error(res.msg)
                }
            })
        }
    }
}
</script>
