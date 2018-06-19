<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%">
        <h2>Cadastrar nova análise</h2>
        <md-input-container>
          <label>Nome da análise</label>
          <md-input v-model="data.nomeAnalise" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>Nome do script</label>
          <md-input v-model="data.nomeScript" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>Upload script (.py ou .r)</label>
          <md-file v-model="file"></md-file>
        </md-input-container>
        <md-button @click="criar" class="md-raised md-primary">Criar</md-button>
      </div>
    </md-tab>
    <md-tab :md-disabled="this.$route.path === '/CriarAnalise/1'" id="editar" md-label="Editar">
      <div style="margin:3%">
        <h2>Editar analises</h2>
        <!-- object value -->
        <model-select :options="options"
        v-model="item"
        placeholder="Selecione a analise">
      </model-select>
      <md-input-container>
        <label>Nome da análise</label>
        <md-input v-model="item.text" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <label>Nome do script</label>
        <md-input v-model="item.nomeScript" maxlength="30"></md-input>
      </md-input-container>
      <md-button @click="editar" class="md-raised md-primary">Editar</md-button>
        <md-button @click="deletar" class="md-raised md-primary">Deletar</md-button>
    </div>
  </md-tab>
</md-tabs>
</template>

<script>
import axios from 'axios';
import { ModelSelect } from 'vue-search-select'
export default {
  name: 'criarAnalise',
  components: {
    ModelSelect
  },
  data() {
    return {
      single:{},
      file:'',
      data:{nomeAnalise:'', nomeScript: '', fk_empresa: this.$session.get('id_empresa')},
      editarAnalise:{},
      options: [
      ],
      item: {
        value: '',
        text: ''
      }
    }
  },
  methods: {
    init: function () {
      var self = this
      axios.post('http://localhost:8080/tcc-back/webapi/analise/get',{
      })
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        var resp = JSON.stringify(response.data).replace(new RegExp("id\"", 'g'), 'value\"').replace(new RegExp("nomeAnalise", 'g'), 'text')
        self.options = JSON.parse(resp)
        console.log(self.options)

      })
      .catch(function (error) {
        console.log(error);
      });

    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data
      var that = this;
      axios.post('http://localhost:8080/tcc-back/webapi/analise/criar',{
        req
      })
      .then(function (response) {
        if(that.$route.path === '/CriarAnalise/1')
        that.$router.push("/CriarDepartamento/1")
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  editar : function(){
    console.log('fazendo req')
    var req = {
      id: this.item.value,
      nomeAnalise: this.item.text,
      nomeScript: this.item.nomeScript
    }
    axios.post('http://localhost:8080/tcc-back/webapi/analise/editar',{
      req
    })
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
},
deletar : function(){
  var self = this
  console.log('fazendo req')
  var req = {
    id: this.item.value,
    nomeAnalise: this.item.text,
    nomeScript: this.item.nomeScript
  }
  axios.post('http://localhost:8080/tcc-back/webapi/analise/deletar',{
    req
  })
  .then(function (response) {
    self.init();
    console.log(response);
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
