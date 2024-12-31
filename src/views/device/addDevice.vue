<template>
  <div style="padding: 20px">
      <div style="padding: 20px">添加设备</div>
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm">
          <el-form-item label="name" prop="name">
              <el-input v-model="form.name" placeholder="please input name"></el-input>
          </el-form-item>
          <el-form-item label="category" prop="category">
              <el-select v-model="form.category" placeholder="请选择设备类型">
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
          <el-button type="primary" @click = "save">提交</el-button>
          <el-button type="danger" @click = "reset">重置</el-button>
      </div>
  </div>
</template>

<script>
import request from "@/util/request";
import Cookies from "js-cookie";

export default {
    name: "addDevice",
    data(){
        return{
            date: Date(),
            form: {},
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
            rules: {
                name: [
                    {required: true, message: '名称不能为空',trigger: 'blur'},
                    {min:2,max: 30, message: '名称长度应在2-30个字间',trigger: 'blur'},
                ]
            }
        }
    },
    methods: {
        save() {
            this.$refs['ruleForm'].validate((valid) => {
                if (valid){
                    this.form.userID = this.user.id
                    this.form.createTime = new Date(this.date).getTime();
                    request.post('/device/save',this.form).then(res => {
                        if(res.code === '200') {
                            this.$notify.success('success')
                            this.reset()
                            this.$router.push("/device")
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
