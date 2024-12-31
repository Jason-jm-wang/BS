<template>
    <div>
        <div style="margin-bottom: 5px">
            <el-input style="width: 240px" placeholder="Please input name"v-model="params.name"></el-input>
            <el-input style="width: 240px; margin-left:5px" placeholder="Please input id"v-model="params.id"></el-input>
            <el-button style="margin-left: 5px" type="primary"@click="load"><i class="el-icon-search"></i>search</el-button>
            <el-button style="margin-left: 5px" type="warning"@click="reset"><i class="el-icon-refresh"></i>reset</el-button>
        </div>

        <el-table :data="tableData" stripe size="mini">
            <el-table-column size="mini"prop="id"label="id"></el-table-column>
            <el-table-column size="mini"prop="name"label="name"></el-table-column>
            <el-table-column size="mini"prop="password"label="password"></el-table-column>
            <el-table-column size="mini"prop="email"label="email"></el-table-column>
            <el-table-column size="mini"prop="privilege"label="privilege"></el-table-column>
            <el-table-column size="mini"label="状态">
                <template v-slot="scope">
                    <el-switch
                        v-model="scope.row.status"
                        @change="changeStatus(scope.row)"
                        active-color="#13ce66"
                        inactive-color="#ff4949">
                    </el-switch>
                </template>
            </el-table-column>

            <el-table-column size="mini"label="操作" width="240px">
                <template v-slot="scope">
                    <el-button size="mini" type="primary" @click="$router.push('/editUser?id='+scope.row.id)">编辑</el-button>
                    <el-popconfirm
                        style="margin-left: 5px"
                        title="您确定删除此数据吗？"
                        @confirm="del(scope.row.id)"
                    >
                        <el-button size="mini" type="danger" slot="reference">删除</el-button>
                    </el-popconfirm>
                    <el-button size="mini" style="margin-left: 5px" type="warning" @click="handleChangePass(scope.row)">修改密码</el-button>
                </template>
            </el-table-column>
        </el-table>


        <div style="margin-top: 5px">
            <el-pagination
                    background
                    :current-page="params.pageNum"
                    :page-size="params.pageSize"
                    layout="prev, pager, next"
                    @current-change="handleCurrentChange"
                    :total="total">
            </el-pagination>
        </div>

        <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%">
            <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">
                <el-form-item label="新密码" prop="newPass">
                    <el-input v-model="form.newPass" auto-complete="off" show-password></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="savePass">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import request from "@/util/request";
import Cookies from "js-cookie";

export default {
    name: "User",
    data(){
        return{
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
            tableData:[],
            total: 10,
            form: {},
            dialogFormVisible: false,
            params: {
                pageNum: 1,
                pageSize: 8,
                name: '',
                id: null
            },
            rules: {
                newPass: [
                    {required: true, message: '密码不能为空',trigger: 'blur'},
                    {min: 6,max: 30, message: '密码长度应在6-30个字间',trigger: 'blur'}
                ],
            }
        }
    },
    created() {
        this.load();
    },
    methods: {
        handleChangePass(row) {
            this.form = JSON.parse(JSON.stringify(row))
            this.dialogFormVisible = true
        },
        changeStatus(row){
            if(this.user.id === row.id && !row.status){
                row.status = true
                this.$notify.warning("当前操作不合法")
                return
            }
            request.put('/user/update',row).then(res => {
                if(res.code === '200') {
                    this.$notify.success('操作成功')
                    this.load()
                } else{
                    this.$notify.error(res.msg)
                }
            })
        },
        savePass() {
            this.$refs['formRef'].validate((valid) => {
                if (valid){
                    request.put('/user/password',this.form).then(res => {
                        if(res.code === '200') {
                            this.$notify.success('修改成功')
                            if(this.form.id === this.user.id) {
                                Cookies.remove('user')
                                this.$router.push('/login')
                            } else {
                                this.load()
                                this.dialogFormVisible = false
                            }
                        } else{
                            this.$notify.error('修改失败')
                        }
                    })
                }
            })
        },
        load() {
            // fetch('http://localhost:9090/user/list').then(res => res.json()).then(res => {
            //     console.log(res)
            //     this.tableData = res;
            //     }
            // )
            request.get('/user/page',{
                params: this.params
            }).then(res => {
                if(res.code === '200'){
                    this.tableData = res.data.list
                    this.total = res.data.total
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
