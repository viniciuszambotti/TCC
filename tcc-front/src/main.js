// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'vue-material/dist/vue-material.css'

var VueMaterial = require('vue-material')
var Axios = require('axios')
import VueAxios from 'vue-axios'

// Axios.headers.get['Content-Type'] = 'application/x-www-form-urlencoded';
// Axios.headers.get['Access-Control-Allow-Origin'] = '*';

Vue.use(VueMaterial)
Vue.use(VueAxios, Axios)

Vue.config.productionTip = false

Vue.material.registerTheme('default', {
  primary: {
    color: 'cyan',
    hue: '700'
  },
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
