<template>
    <div class="ValidCode disabled-select" style="width:100%; height:100%" @click="refreshCode">
      <span v-for="(item, index) in codeList" :key="index" :style="getStyle(item)">{{ item.code }}</span>
    </div>
</template>

<script>
// 验证码组件
export default {
    name: 'ValidCode',
    data () {
        return {
            length: 4,
            codeList: []
        }
    },
    watch: {
        refresh () {
            this.createdCode()
        }
    },
    mounted () {
        this.createdCode()
    },
    methods: {
        refreshCode () {
            this.createdCode()
        },
        createdCode () {
            const len = this.length
            const codeList = []
            const chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz0123456789'
            const charsLen = chars.length
            // 生成
            for (let i = 0; i < len; i++) {
                const rgb = [Math.round(Math.random() * 220), Math.round(Math.random() * 240), Math.round(Math.random() * 200)]
                codeList.push({
                    code: chars.charAt(Math.floor(Math.random() * charsLen)),
                    color: `rgb(${rgb})`,
                    fontSize: `${10 + (+[Math.floor(Math.random() * 10)] + 6)}px`,
                    padding: `${[Math.floor(Math.random() * 10)]}px`,
                    transform: `rotate(${Math.floor(Math.random() * 90) - Math.floor(Math.random() * 90)}deg)`
                })
            }
            // 指向
            this.codeList = codeList
            // 将当前数据派发出去
            // console.log(codeList.map(item => item.code).join(''))
            this.$emit('update:value', codeList.map(item => item.code).join(''))
        },
        getStyle (data) {
            return `color: ${data.color}; font-size: ${data.fontSize}; padding: ${data.padding}; transform: ${data.transform}`
        }
    }
}
</script>

<style scoped>
.ValidCode{
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}
.ValidCode span{
    display: inline-block;
}
</style>

