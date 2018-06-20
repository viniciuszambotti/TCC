import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/Main'
import CriarEmpresa from '@/components/CriarEmpresa'
import CriarAnalise from '@/components/CriarAnalise'
import CriarUsuario from '@/components/CriarUsuario'
import Analise from '@/components/Analise'
import Departamento from '@/components/CriarDepartamento'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/Main',
      name: 'Main',
      component: home
    },
    {
      path: '/CriarEmpresa/:ed',
      name: 'CriarEmpresa',
      component: CriarEmpresa
    },
    {
      path: '/CriarUsuario/:ed',
      name: 'CriarUsuario',
      component: CriarUsuario
    },
    {
      path: '/CriarAnalise/:ed',
      name: 'CriarAnalise',
      component: CriarAnalise
    },
    {
      path: '/Analise/:nome',
      name: 'Analise',
      component: Analise
    },
    {
      path: '/CriarDepartamento/:ed',
      name: 'Departamento',
      component: Departamento
    }
  ]
})
