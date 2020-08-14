<template>
    <div>
        <p>当前 {{count}}</p>
        <button @click="count+=1">+1</button>
    </div>
</template>

<script>
import { reactive } from '@vue/composition-api'

export default {
    // setup函数中没有this对象，可以使用context对象代替this
    setup(props, ctx) {
        // 在befordCreate之后执行
        console.log(props)

        console.log(ctx)

        // reactive接收一个普通对象，返回一个响应式对象, reactive对象只能在setup函数中使用
        const state = reactive({count: 0})

        console.log(state)

        // return将创建的响应式对象返回出去，tempalte中才可以访问该对象
        return state

    },
    beforeCreate() {
        // 第一个执行
    },
    created() {
        // 在setup函数之后执行
    },
    // 需要接收props
    props: {
        p1: String
    }

}
</script>

<style scoped>

</style>