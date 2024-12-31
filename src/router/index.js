import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/home/HomeView.vue'
import Layout from "@/views/Layout.vue";
import Cookies from "js-cookie";

Vue.use(VueRouter)

const routes = [
    // ======= 登录 ====== //
  {
    path: '/login',
    name: 'Login',
    component: () => import ("@/views/login/Login.vue")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import ("@/views/login/Register.vue")
  },

    // ======= 主页 ====== //
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home',
    children: [//子路由
      {
        path: 'home',
        name: 'Home',
        component: () => import ("@/views/home/HomeView.vue")
      },
      {
        path: 'user',
        name: 'User',
        component: () => import ("@/views/user/userList.vue")
      },
      {
        path: 'addUser',
        name: 'addUser',
        component: () => import ("@/views/user/addUser.vue")
      },
      {
        path: 'editUser',
        name: 'editUser',
        component: () => import ("@/views/user/editUser.vue")
      },
      {
        path: 'device',
        name: 'Device',
        component: () => import ("@/views/device/deviceList.vue")
      },
      {
        path: 'addDevice',
        name: 'addDevice',
        component: () => import ("@/views/device/addDevice.vue")
      },
      {
        path: 'editDevice',
        name: 'editDevice',
        component: () => import ("@/views/device/editDevice.vue")
      },
      {
        path: 'deviceStatics',
        name: 'deviceStatics',
        component: () => import ("@/views/device/deviceStatics.vue")
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import ("@/views/message/messageList.vue")
      },
    ]
  },
  {
    path: "*",
    component: () => import ("@/views/404.vue")
  }
  ]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to, from, next) => {
  if(to.path === '/login') next()
  const user = Cookies.get('user')
  // if(!user && to.path != '/login') return next('/login')
  next()
})
export default router
