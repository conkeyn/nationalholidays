// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Element from 'element-ui'
import 'element-ui/packages/theme-chalk/src/index.scss'
import router from './router'
import store from './vuex/store'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Element)
Vue.use(Vuex)

Vue.config.productionTip = false
//Vue.prototype.axios = axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  axios,
  router,
  store,
  template: '<App/>',
  components: { App }
})
