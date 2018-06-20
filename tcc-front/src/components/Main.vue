<template>
  <div>
    <md-layout v-for="dado in nAnalises" style="margin-top: 3%" md-gutter>

      <md-layout  v-for="analise in analises"  style="margin:1%" md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">
        <md-card md-with-hover>
          <md-card-header>
            <div class="md-title">Nome da Analise: {{analise.nomeAnalise}}</div>
          </md-card-header>

          <md-card-content>
            Script que será executado: {{analise.nomeScript}}
          </md-card-content>

          <md-card-actions>
            <md-button@click="changePage(analise.nomeScript)">Acessar</md-button>
          </md-card-actions>
        </md-card>
      </md-layout>
      <img  v-for="image in img.encodedImage" :src="image">
      <!-- <md-layout md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">

    </md-layout> -->

    <!-- <md-layout md-flex-xsmall="100" md-flex-small="50" md-flex-medium="33">

  </md-layout> -->
</md-layout>
</div>
</template>

<script>
import axios from 'axios';
import router from 'vue-router';
export default {
  name: 'main',
  data() {
    return {
      img : {  encodedImage: []},
      msg: 'Welcome to Your Vue.js App',
      analises: [],
      nAnalises: [1]
    }
  },
  methods: {
    init() {
      var req  = {nomeAnalise:  this.$session.get('analises'), fk_empresa: this.$session.get('id_empresa')}
      var url;
      var that = this;
      console.log( this.$session.get('tipo'))
      if( this.$session.get('tipo') === '1'){
        url = 'http://localhost:8080/tcc-back/webapi/analise/getId'
      }else{
        url = 'http://localhost:8080/tcc-back/webapi/analise/getEmpresa'
      }
      axios.post(url,{
        req
      })
      .then(function (response) {
        that.analises = response.data
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    changePage: function(name){
      this.$router.push({ path: '/Analise/' + name})
    }
  },
  beforeMount(){
    this.init()
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
