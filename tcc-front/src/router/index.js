import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import CriarEmpresa from '@/components/CriarEmpresa'
import CriarAnalise from '@/components/CriarAnalise'
import CriarUsuario from '@/components/CriarUsuario'
import Analise from '@/components/Analise'

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
    },
    {
      path: '/CriarUsuario',
      name: 'CriarUsuario',
      component: CriarUsuario
    },
    {
      path: '/CriarAnalise',
      name: 'CriarAnalise',
      component: CriarAnalise
    },
    {
      path: '/Analise',
      name: 'Analise',
      component: Analise
    }
  ]
})
