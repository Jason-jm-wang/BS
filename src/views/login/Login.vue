<template>
  <div style="height: 100vh; display: flex; align-items: center; justify-content: center;background-color: cadetblue">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
        <div style="flex: 1">
        </div>

        <div style="flex: 1">
            <el-form :model="user" :rules="rules" ref="loginForm" style="width: 80%">
                <div style="font-size: 20px; font-weight: bold; text-align: center; margin-top: 20px; margin-bottom: 20px">物联网设备管理平台</div>
                <el-form-item prop="name">
                    <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入用户名" v-model="user.name"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" prefix-icon="el-icon-lock" show-password placeholder="请输入密码" v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item prop="code">
                    <div style="display: flex">
                        <el-input style="flex: 1" size="medium" prefix-icon="el-icon-check" v-model="user.code"></el-input>
                        <div style="flex: 1; height: 36px">
                            <valid-code @update:value="getCode"></valid-code>
                        </div>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
                </el-form-item>
                <div style="display: flex; font-size: 13px; margin-bottom: 20px">
                    <div style="flex: 1">还没有账号？请<span style="color: #42b983; cursor: pointer" @click="$router.push('/register')">注册</span></div>
                    <div style="flex: 1; text-align: right">忘记密码</div>
                </div>

            </el-form>
        </div>
    </div>

  </div>
</template>

<script>

import request from "@/util/request";
import Cookies from 'js-cookie'
import ValidCode from "@/components/ValidCode.vue";

export default {
    name: "Login",
    components: {
        ValidCode
    },
    data(){
        const validateCode = (rule, value, callback) => {
            if(value === ''){
                callback(new Error("请输入验证码"))
            } else if (value.toLowerCase() !== this.code) {
                callback(new Error("验证码错误"))
            } else {
                callback()
            }
        }

        return{
            code: '',     //正确验证码
            loginUser: {},
            user: {
                code: '', //用户输入验证码
                name: '',
                password: ''
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
                code: [
                    {validator: validateCode}
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


<!--<template>-->
<!--  <div style="height: 100vh; overflow: hidden; position: relative">-->
<!--    <el-card class="cover" v-if="loginUser.id">-->
<!--        <slide-verify :l="42"-->
<!--                      :r="10"-->
<!--                      :w="310"-->
<!--                      :h="155"-->
<!--                      slider-text="向右滑动"-->
<!--                      @success="onSuccess"-->
<!--                      @fail="onFail"-->
<!--                      @refresh="onRefresh"-->
<!--        ></slide-verify>-->
<!--    </el-card>-->


<!--      <div style="width: 500px; height: 400px; background-color: aliceblue; border-radius: 10px;-->
<!--      margin: 100px auto; padding: 50px">-->
<!--          <div style="margin: 30px; text-align: center; font-size: 30px; font-weight: bold; color: cadetblue">登录</div>-->
<!--          <el-form :model="user" :rules="rules" ref="loginForm">-->
<!--              <el-form-item prop="name">-->
<!--                  <el-input placeholder="请输入用户名" prefix-icon="el-icon-user" size="medium" v-model="user.name"></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item prop="password">-->
<!--                  <el-input placeholder="请输入密码" show-password prefix-icon="el-icon-lock" size="medium"  v-model="user.password"></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item>-->
<!--                  <el-button style="width: 100%" size="medium" type="primary" @click="login">登录</el-button>-->
<!--              </el-form-item>-->
<!--          </el-form>-->
<!--      </div>-->

<!--  </div>-->
<!--</template>-->

<!--<script>-->

<!--import request from "@/util/request";-->
<!--import Cookies from 'js-cookie'-->

<!--export default {-->
<!--    name: "Login",-->
<!--    data(){-->
<!--        return{-->
<!--            loginUser: {},-->
<!--            user: {},-->
<!--            rules: {-->
<!--                name: [-->
<!--                    {required: true, message: '名称不能为空',trigger: 'blur'},-->
<!--                    {min:2,max: 30, message: '名称长度应在2-30个字间',trigger: 'blur'},-->
<!--                ],-->
<!--                password: [-->
<!--                    {required: true, message: '密码不能为空',trigger: 'blur'},-->
<!--                    {min: 6,max: 30, message: '密码长度应在6-30个字间',trigger: 'blur'}-->
<!--                ],-->
<!--            }-->
<!--        }-->
<!--    },-->
<!--    methods: {-->
<!--        login() {-->
<!--            this.$refs['loginForm'].validate((valid) => {-->
<!--                if (valid){-->
<!--                    request.post('/user/login',this.user).then(res => {-->
<!--                        if(res.code === '200') {-->
<!--                            this.loginUser = res.data-->
<!--                        } else{-->
<!--                            this.$notify.error(res.msg)-->
<!--                        }-->
<!--                    })-->
<!--                }-->
<!--            })-->

<!--        },-->
<!--        onSuccess(){-->
<!--            this.$notify.success("登录成功")-->
<!--            Cookies.set('user',JSON.stringify(this.loginUser))-->

<!--            this.$router.push('/')-->
<!--        },-->
<!--        onFail(){-->
<!--            this.msg = 'onFail'-->
<!--        },-->
<!--        onRefresh(){-->
<!--            this.msg = 'refresh'-->
<!--        }-->
<!--    }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.cover {-->
<!--    width: fit-content;;-->
<!--    background-color: white;-->
<!--    position: absolute;-->
<!--    top: 50%;-->
<!--    left: 50%;-->
<!--    transform: translate(-50%, -50%);-->
<!--    z-index: 1000;-->
<!--}-->
<!--</style>-->


