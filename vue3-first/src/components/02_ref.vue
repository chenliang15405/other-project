<template>
    <div>
        <!--在其他地方访问不需要使用value属性即可-->
        <p>refcount的值： {{count}}</p>
        <button @click="count += 1">ref +1</button>
    </div>
</template>

<script>
import { ref } from '@vue/composition-api'


export default {

    // 不过ref看起来创建的响应式对象只能是基本类型，reactive创建的对象都是对象类型的响应式数据
    setup() {

        // 使用ref创建响应式数据，ref用来创建响应式数据比reactive更好

        const count = ref(0)
        // 访问该值的属性，需要使用value
        console.log(count.value)

        return {
            count
        }

    }

}
</script>

<style scoped>

</style>