<template>
  <div id="app">
    <link rel="stylesheet" href="//fonts.googleapis.com/icon?family=Material+Icons">
    <md-toolbar>
      <md-button class="md-icon-button" @click="toggleLeftSidenav">
        <md-icon>menu</md-icon>
      </md-button>

      <h2 class="md-title">My App</h2>
    </md-toolbar>

    <md-sidenav class="md-left" ref="leftSidenav" @open="open('Left')" @close="close('Left')">
      <md-toolbar class="md-large">
        <div class="md-toolbar-container">
          <h3 class="md-title">Menu</h3>
        </div>
      </md-toolbar>

      <md-list>
        <md-list-item>
          <md-button v-on:click="changePage('/')" class=" md-list-action">
            <md-icon>home</md-icon>
            Página inicial
          </md-button>
        </md-list-item>
        <md-list-item>
          <md-button v-on:click="changePage('/CriarAnalise')" class=" md-list-action">
            <md-icon>insert_chart</md-icon>
            Criar/editar analise
          </md-button>
        </md-list-item>
        <md-list-item>
          <md-button v-on:click="changePage('/CriarEmpresa')" class=" md-list-action">
            <md-icon>business_center</md-icon>
            Criar nova empresa
          </md-button>
        </md-list-item>
        <md-list-item>
          <md-button v-on:click="changePage('/CriarUsuario')" class=" md-list-action">
            <md-icon>person</md-icon>
            Criar/editar Usuario
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
  methods: {
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
    changePage: function(name){
    this.closeLeftSidenav()
      this.$router.push(name)
    }
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
