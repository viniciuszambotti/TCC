<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%" >
        <h2>Cadastrar nova empresa</h2>
        <md-input-container>
          <label>Nome da empresa</label>
          <md-input v-model="data.nome" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>CNPJ da empresa</label>
          <md-input v-model="data.cnpj" maxlength="30"></md-input>
        </md-input-container>
        <md-button @click="criar" class="md-raised md-primary">Criar</md-button>
      </div>
    </md-tab>
    <md-tab id="editar" md-label="Editar">
      <div style="margin:3%" >

        <h2>Editar empresa</h2>
        <model-select :options="options"
        v-model="item"
        placeholder="select item">
      </model-select>
        <md-input-container>
          <label>Nome da empresa</label>
          <md-input v-model="item.text" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>CNPJ da empresa</label>
          <md-input v-model="item.cnpj" maxlength="30"></md-input>
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
  name: 'criarEmpresa',
  components: {
    ModelSelect
  },
  data() {
    return {
      data:{nome:'', cnpj:''},
      options:[],
      item: {
        value: '',
        text: ''
      }
    }
  },
  methods: {
    init: function () {
      var self = this
      axios.post('http://localhost:8080/tcc-back/webapi/empresa/get',{
      })
      .then(function (response) {
        var resp = JSON.stringify(response.data).replace(new RegExp("id", 'g'), 'value').replace(new RegExp("nome", 'g'), 'text')
        self.options = JSON.parse(resp)

      })
      .catch(function (error) {
        console.log(error);
      });

    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data
      axios.post('http://localhost:8080/tcc-back/webapi/empresa/criar',{
        req
      })
      .then(function (response) {
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
      nome: this.item.text,
      cnpj: this.item.cnpj
    }
    axios.post('http://localhost:8080/tcc-back/webapi/empresa/editar',{
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
    nome: this.item.text,
    cnpj: this.item.cnpj
  }
  axios.post('http://localhost:8080/tcc-back/webapi/empresa/deletar',{
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
