<template>
    <div>
        <h3 ref="h3Ref">template ref dom</h3>
    </div>
</template>

<script>
import { ref, onMounted } from '@vue/composition-api'

export default {
    name: "07_ref_template",
    setup() {

        const h3Ref = ref(null)

        onMounted(() => {
            // 这里拿到的是页面中ref指向的h3Ref的文本的值
            console.log(h3Ref.value)
        })

        return {
            h3Ref
        }

    }
}
</script>

<style scoped>

</style>