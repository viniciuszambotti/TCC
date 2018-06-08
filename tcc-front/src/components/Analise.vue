<template>
  <div>

    <head>
      <meta charset="UTF-8">
    </head>
    <md-tabs>
      <md-tab id="selectAnalise" md-label="Configurar visualização">
        <md-button class="md-raised md-primary" @click="attAnalise(nomeAnalise)">Atualizar Análise</md-button>
        <br><br>
        <md-boards class="md-primary" :md-controls="true">
          <md-board v-for="image in encodedImage" >
            <img :src="image"></img>
          </md-board>
        </md-boards>

        <!-- <div v-for="image in encodedImage" >
        <img :src="image"></img>
        <br>  <br>
      </div> -->
    </md-tab>

    <md-snackbar :md-position="'top' + ' ' + 'center'" ref="snackbar" :md-duration="duration">
      <span>{{msg}}</span>
    </md-snackbar>
  </md-tabs>
</div>
</template>

<script>
import axios from 'axios';
import router from 'vue-router';



export default {
  data () {
    return {
      nomeAnalise: '',
      encodedImage: [],
      datacollection: null,
      msg : '',
      duration: Infinity
    }
  },
  methods: {
    init (){
      this.nomeAnalise = this.$route.params.nome;

    },
    open() {
      this.$refs.snackbar.open();
    },
    close() {
      this.$refs.snackbar.close();
    },
    attAnalise: function(name){
      this.msg = "Atualizando Analise..."
      this.open()
      var that = this;
      axios.get('http://localhost:5000/user/' + name)
      .then(function (response) {
        var splt =[];

        for(var i=0; i< response.data.split(',').length; i++){
          splt.push('data:image/jpg;charset=utf-8; base64,' +response.data.split(',')[i]);
        }
        that.encodedImage = splt;
        console.log(  that.encodedImage)

        that.close()
      })
      .catch(function (error) {
        console.log(error);
      });
    }
  },

  beforeMount(){
    this.init()
  }
}
</script>

<style>
.small {
  max-width: 600px;
  margin:  150px auto;
}
</style>
