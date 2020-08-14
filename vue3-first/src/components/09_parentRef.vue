<template>
    <div>


        <son-com ref="refCom"></son-com>

        <p>父组件</p>
        <button @click="getSonValue">获取子组件的值</button>

    </div>
</template>

<script>
import { ref } from '@vue/composition-api'
import sonCom from './08_sonRef'

export default {
    name: "09_parentRef",
    components: {
        "son-com": sonCom
    },
    setup() {
        const refCom = ref(null)

        const getSonValue = () => {
            // 通过在子组件上面加上ref，可以获取到子组件中的属性
            console.log(refCom)
            console.log(refCom.value.str)

            //并且可以通过ref修改子组件中的值, 调用子组件中的函数
            refCom.value.setStr()
        }

        return {
            refCom,
            getSonValue
        }
    }
}
</script>

<style scoped>

</style>