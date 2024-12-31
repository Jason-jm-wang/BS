<template>
    <div style="padding: 20px">
        <div style="padding: 20px">编辑用户</div>
        <el-form :inline="true" :model="form">
            <el-form-item label="name">
                <el-input v-model="form.name" placeholder="please input name"></el-input>
            </el-form-item>
            <el-form-item label="email">
                <el-input v-model="form.email" placeholder="please input email"></el-input>
            </el-form-item>
            <el-form-item label="password">
                <el-input v-model="form.password" placeholder="please input name"></el-input>
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
export default {
    name: "editUser",
    data(){
        return{
            form: {}
        }
    },
    created() {
        const id = this.$route.query.id
        request.get("/user/" + id).then(res => {
            this.form = res.data
        })
    },
    methods: {
        router() {
            return router
        },
        save() {
            request.put('/user/update',this.form).then(res => {
                if(res.code === '200') {
                    this.$notify.success('success')
                    this.$router.push("/user")
                } else{
                    this.$notify.error(res.msg)
                }
            })
        }
    }
}
</script>
