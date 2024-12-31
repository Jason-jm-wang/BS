<template>
    <div>
        <div class="item">用户名：{{user.name}}</div>
        <div class="item">邮箱：{{user.email}}</div>
    </div>
</template>

<script>
import request from "../../util/request";
import Cookies from "js-cookie";

export default {
    name: "home",
    data(){
        return {
            user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
        }
    }
}
</script>

<style>
.item {
    margin: 10px;
    color: slategrey;
}
</style>