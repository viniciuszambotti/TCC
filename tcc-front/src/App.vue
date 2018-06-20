<template>
  <div id="app">
    <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">
    <md-toolbar v-show="this.$route.path !== '/'&& this.$route.path !== '/Login'&&this.$route.path !== '/CriarAnalise/1'  && this.$route.path !== '/CriarEmpresa/1' && this.$route.path !== '/CriarDepartamento/1'">
      <md-button class="md-icon-button" @click="toggleLeftSidenav">
        <md-icon>menu</md-icon>
      </md-button>

      <h2 class="md-title">Web análise</h2>
    </md-toolbar>

    <md-sidenav  class="md-left" ref="leftSidenav" @open="open('Left')" @close="close('Left')">
      <md-toolbar class="md-large">
        <div class="md-toolbar-container">
          <h3 class="md-title">Menu</h3>
        </div>
      </md-toolbar>

      <md-list>
        <md-list-item>
          <md-button v-on:click="changePage('/Main')" class=" md-list-action">
            <md-icon>home</md-icon>
            Página inicial
          </md-button>
        </md-list-item>
        <md-list-item v-show="this.$session.get('tipo') === '2'">
          <md-button v-on:click="changePage('/CriarAnalise/2')" class=" md-list-action">
            <md-icon>insert_chart</md-icon>
            Criar/editar analise
          </md-button>
        </md-list-item>
        <md-list-item v-show="this.$session.get('tipo') === '2'">
          <md-button v-on:click="changePage('/CriarEmpresa/2')" class=" md-list-action">
            <md-icon>business_center</md-icon>
            Criar nova empresa
          </md-button>
        </md-list-item>
        <md-list-item v-show="this.$session.get('tipo') === '2'">
          <md-button v-on:click="changePage('/CriarDepartamento/2')" class=" md-list-action">
            <md-icon>business_center</md-icon>
            Criar novo departamento
          </md-button>
        </md-list-item>
        <md-list-item v-show="this.$session.get('tipo') === '2'">
          <md-button v-on:click="changePage('/CriarUsuario/2')" class=" md-list-action">
            <md-icon>person</md-icon>
            Criar/editar Usuario
          </md-button>
        </md-list-item>
        <md-list-item>
          <md-button v-on:click="logout()" class=" md-list-action">
            <md-icon>exit_to_app</md-icon>
            Sair
          </md-button>
        </md-list-item>
      </md-list>
    </md-sidenav>
    <router-view></router-view>
  </div>
</template>

<script>
import router from 'vue-router';
export default {
  name: 'app',
  data() {
    return {
      logged:this.$session.get('enable')
    }
  },
  methods: {
    init(){
      console.log()

    },
    toggleLeftSidenav() {
      this.$refs.leftSidenav.toggle();
    },
    closeLeftSidenav() {
      this.$refs.leftSidenav.close();
    },
    open(ref) {
    },
    close(ref) {
    },
    logout() {
      this.$session.destroy()
      this.closeLeftSidenav()
      this.$router.push('/Login')
    },
    changePage: function(name){
      this.closeLeftSidenav()
      this.$router.push(name)
    }
  },
  beforeMount(){
    this.init()
  }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
