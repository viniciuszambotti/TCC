<template>
  <div>
    <md-layout md-gutter>

      <md-layout md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">
        <md-card class="md-primary cardlayout">
          <md-card-media>
            <img src="../assets/time-to-market.png">
          </md-card-media>

          <md-card-header>
            <div class="md-title">Time to market</div>
            <div class="md-subhead">Análise de marketing</div>
          </md-card-header>

          <md-card-actions>
            <!-- <md-button>Atualizar informação</md-button> -->
            <md-button>Acessar</md-button>
          </md-card-actions>

          <md-card-content>
            O objetivo principal do TTM é criar parâmetros que garantam a entrega dos produtos no mercado no tempo certo
          </md-card-content>
        </md-card>
      </md-layout>

      <md-layout md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">
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
        <md-card class="md-primary cardlayout">
          <md-card-media>
            <img src="../assets/time-to-market.png">
          </md-card-media>

          <md-card-header>
            <div class="md-title">Market</div>
            <div class="md-subhead">Subtitle here</div>
          </md-card-header>

          <md-card-actions>
            <md-button>Action</md-button>
            <md-button>Action</md-button>
          </md-card-actions>

          <md-card-content>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio itaque ea, nostrum odio. Dolores, sed accusantium quasi non,
            voluptas eius illo quas, saepe voluptate pariatur in deleniti minus sint. Excepturi.
          </md-card-content>
        </md-card>
      </md-layout>
    </md-layout>
  </div>
</template>

<script>
  export default {
    name: 'hello',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
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
