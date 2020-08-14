import Vue from 'vue'
import App from './App.vue'

import compositionAPI from '@vue/composition-api'

Vue.use(compositionAPI)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
