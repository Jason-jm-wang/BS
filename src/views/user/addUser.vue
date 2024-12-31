<template>
  <div style="padding: 20px">
      <div style="padding: 20px">添加用户</div>

      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm">

          <el-form-item label="name" prop="name">
              <el-input v-model="form.name" placeholder="please input name"></el-input>
          </el-form-item>
          <el-form-item label="email" prop="email">
              <el-input v-model="form.email" placeholder="please input email"></el-input>
          </el-form-item>
          <el-form-item label="password" prop="password">
              <el-input v-model="form.password" placeholder="please input password"></el-input>
          </el-form-item>
      </el-form>

      <div style="text-align: center;margin-top: 30px">
          <el-button type="primary" @click = "save">提交</el-button>
          <el-button type="danger" @click = "reset">重置</el-button>
      </div>
  </div>
</template>

<script>
import request from "@/util/request";
export default {
    name: "addUser",
    data(){
        const checkEmail = (rule, value, callback) => {
            if(!value){
                callback(new Error("邮箱不能为空"));
            }else if(!/^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$/.test(value)) {
                callback(new Error("请输入合法邮箱"));
            }
            callback();
        };
        const checkPassword = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('年龄不能为空'));
            }
            console.log(value)
                if (!Number.isInteger(value)) {
                    callback(new Error('请输入数字值'));
                } else {
                    if (value < 18) {
                        callback(new Error('必须年满18岁'));
                    } else {
                        callback();
                    }
                }
        };
        return{
            form: {},
            rules: {
                name: [
                    {required: true, message: '名称不能为空',trigger: 'blur'},
                    {min:2,max: 30, message: '名称长度应在2-30个字间',trigger: 'blur'},
                ],
                email: [
                    {validator: checkEmail, trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '密码不能为空',trigger: 'blur'},
                    {min: 6,max: 30, message: '密码长度应在6-30个字间',trigger: 'blur'}
                ],
            }
        }
    },
    methods: {
        save() {
            this.$refs['ruleForm'].validate((valid) => {
                if (valid){
                    request.post('/user/save',this.form).then(res => {
                        if(res.code === '200') {
                            this.$notify.success('success')
                            this.reset()
                            this.$router.push("/user")
                        } else{
                            this.$notify.error(res.msg)
                        }
                    })
                }
            })

        },
        reset() {
            this.form = {}
        }
    }
}
</script>
