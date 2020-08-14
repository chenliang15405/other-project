<template>
    
</template>

<script>
import { onBeforeMount, onMounted, onUpdated } from '@vue/composition-api'

export default {
    name: "06_lifeHook",
    setup() {
        // 其他的生命周期函数

        onBeforeMount(() => {
            console.log('onBeforeMount')
        })

        onMounted(() => {
            // 和vue2的mount()函数一致，如果是ssr，那么需要在这里发送ajax，因为ssr中，客户端没有created

            console.log('onMounted')
        })

        onUpdated(() => {
            console.log('onUpdated')
        })

    }
}
</script>

<style scoped>

</style>