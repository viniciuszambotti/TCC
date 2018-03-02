import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import CriarEmpresa from '@/components/CriarEmpresa'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/CriarEmpresa',
      name: 'CriarEmpresa',
      component: CriarEmpresa
    }
  ]
})
