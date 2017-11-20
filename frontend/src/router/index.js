import Vue from 'vue'
import Router from 'vue-router'
import Holiday from '@/page/Holiday.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/Holiday',
      component:Holiday
    }
  ]
})
