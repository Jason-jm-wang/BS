<template>
    <div style="height: 100vh; display: flex; align-items: center; justify-content: center;background-color: cadetblue">
        <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
            <div style="flex: 1">
            </div>

            <div style="flex: 1">
                <el-form :model="user" :rules="rules" ref="loginForm" style="width: 80%">
                    <div style="font-size: 20px; font-weight: bold; text-align: center; margin-top: 20px; margin-bottom: 20px">用户注册</div>
                    <el-form-item prop="name">
                        <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入用户名" v-model="user.name"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="medium" prefix-icon="el-icon-lock" show-password placeholder="请输入密码" v-model="user.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="medium" prefix-icon="el-icon-lock" show-password placeholder="确认密码" v-model="user.confirm"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
                    </el-form-item>
                    <div style="display: flex; font-size: 13px; margin-bottom: 20px">
                        <div style="flex: 1">已有账号？请<span style="color: #42b983; cursor: pointer" @click="$router.push('/login')">登录</span></div>
                    </div>

                </el-form>
            </div>
        </div>

    </div>
</template>

<script>

import request from "@/util/request";
import Cookies from 'js-cookie'

export default {
    name: "Register",
    data(){
        const validatePass = (rule, value, callback) => {
            if(value === ''){
                callback(new Error("请确认密码"))
            } else if (value !== this.user.password) {
                callback(new Error("密码不匹配"))
            } else {
                callback()
            }
        }

        return{
            loginUser: {},
            user: {
                name: '',
                password: '',
                confirm: '',
            },
            rules: {
                name: [
                    {required: true, message: '名称不能为空',trigger: 'blur'},
                    {min:2,max: 30, message: '名称长度应在2-30个字间',trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '密码不能为空',trigger: 'blur'},
                    {min: 6,max: 30, message: '密码长度应在6-30个字间',trigger: 'blur'}
                ],
                confirm: [
                    {validator: validatePass()}
                ],
            }
        }
    },
    methods: {
        login() {
            this.$refs['loginForm'].validate((valid) => {
                if (valid){
                    request.post('/user/login',this.user).then(res => {
                        if(res.code === '200') {
                            this.loginUser = res.data
                            this.$notify.success("登录成功")
                            Cookies.set('user',JSON.stringify(this.loginUser))
                            this.$router.push('/')
                        } else{
                            this.$notify.error(res.msg)
                        }
                    })
                }
            })

        },
        getCode(code) {
            this.code = code.toLowerCase()
        }
    }
}
</script>