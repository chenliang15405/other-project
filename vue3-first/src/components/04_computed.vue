<template>

    <div>
        <p>ref count : {{refCount}}</p>
        <p>computed count: {{computedCount}}</p>


        <button @click="refCount++">count + 1</button>

    </div>
    
</template>

<script>
import { computed, ref } from '@vue/composition-api'

export default {
    name: "computed",
    setup() {
        // 使用computed的计算属性
        const refCount = ref(0)

        const computedCount = computed(() => refCount.value + 1)

        return {
            refCount,
            computedCount
        }
    }

}
</script>

<style scoped>

</style>