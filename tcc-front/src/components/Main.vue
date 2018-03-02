<template>
  <div>
    <md-layout style="margin-top: 3%" md-gutter>

      <md-layout  md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">
        <md-card class="md-primary cardlayout">
          <md-card-media>
            <img src="../assets/turnover.jpg">
          </md-card-media>

          <md-card-header>
            <div class="md-title">Turnover</div>
            <div class="md-subhead">Análise de RH</div>
          </md-card-header>

          <md-card-actions>
            <!-- <md-button>Action</md-button> -->
            <md-button>Acessar</md-button>
          </md-card-actions>

          <md-card-content>
            conceito frequentemente utilizado na área de Recursos Humanos (RH) para designar a rotatividade de pessoal em uma organização,
            ou seja, as entradas e saídas de funcionários em determinado período de tempo.
          </md-card-content>
        </md-card>
      </md-layout>

      <md-layout md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">

      </md-layout>

      <md-layout md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">

      </md-layout>
    </md-layout>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    name: 'main',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    methods: {
      testFunc() {
        axios.get('http://localhost:8080/tcc-back/webapi/Analysis/test')
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .cardlayout {
    margin-top: 20%;
    margin-left: 50px;
    margin-right: 50px;
  }

</style>
