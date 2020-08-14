<template>
    <div>

    </div>
</template>

<script>
import { watch, ref, reactive } from '@vue/composition-api'

export default {
    name: "watch",
    setup() {
        const count = ref(0)

        // 监听
        watch(() => console.log(count.value))

        setTimeout(() => count.value++, 2000)


        // 监听某个数据源
        const state = reactive({count: 0})


        // 监听reactive的数据对象
        watch(
            () => state.count,
            (newVal, oldVal) => {
                console.log(newVal + "   " + oldVal)
            },
            {
                //第一次不监听
                lazy: true
            }
        )


        // 监听ref响应式对象
        watch(
            count,
            (newVal, oldVal) => {
                console.log(newVal + "   " + oldVal)
            },
            {
                //第一次不监听
                lazy: true
            }
        )


        const state1 = reactive({count: 0, name: 'zxv'})

        // 监听多个reactive的数据对象
        watch(
            [() => state1.count, () => state1.name],
            ([newValCount, newValName], [preValCount, preValName]) => {
                console.log(newValCount + "   " + newValName)
                console.log(preValCount + "   " + preValName)
            }
        )

        //监听多个ref的数据对象
        const c = ref(0)
        const name = ref('123')

        watch(
            [c, name],
            ([c, name],[preC, preName]) => {
                console.log(c + "   " + name)
                console.log(preC + "   " + preName)
            }
        )


        // 清楚watch监听
        const stop = watch(() => console.log(count))

        // 调用watch返回的函数，即可清除watch的监听
        stop()

    }
}
</script>

<style scoped>

</style>