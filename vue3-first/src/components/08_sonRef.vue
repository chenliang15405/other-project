<template>
    <div>
        <p>子组件</p>

        <p>{{str}}</p>
    </div>
</template>

<script>
import { ref } from '@vue/composition-api'

export default {
    name: "08_sonRef",
    setup() {

        const str = ref('this is son ref value')


        const setStr = () => {
            str.value = '自组件被赋值了'
        }

        return {
            str,
            setStr
        }
    }
}
</script>

<style scoped>

</style>