<template>
    <div>
        <p>to_refs的值: {{count}}</p>
        <button @click="add">to_refs +1</button>
    </div>
    
</template>

<script>
import { reactive, toRefs } from '@vue/composition-api'

// toRefs 函数可以将reative创建出来的响应式对象，转换为普通对象，只不过这个对象上的每个属性节点，都是ref()类型的响应式数据

export default {
    name: "toRefs",

    setup() {

        const state = reactive({count: 1, name: '333'})

        // vue3中methods的方法，也需要定义在setup函数中
        const add = () => {
            state.count ++
        }

        // 可以返回一个对象，对象中包含多个参数，不过 reative创建的对象如果使用扩展运算符，那么就不会是响应式对象
        // 所以需要使用toRefs将该对象转换为响应式
        return {
            ...toRefs(state),
            add
        }
    }

}
</script>

<style scoped>

</style>