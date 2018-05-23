<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%">
        <h2>Cadastrar novo departamento</h2>
        <multi-select :options="optionsAna"
        :selected-options="data.analises"
        placeholder="Selecione as analises"
        @select="onSelect">
      </multi-select>
      <md-input-container>
        <label>Nome do departamento</label>
        <md-input v-model="data.nome" maxlength="30"></md-input>
      </md-input-container>


      <md-button @click="criar" class="md-raised md-primary">Criar</md-button>
    </div>
  </md-tab>
  <md-tab id="editar" md-label="Editar">
    <div style="margin:3%">
      <h2>Editar departamentos</h2>
      <!-- object value -->
      <model-select :options="options"
      v-model="item"
      placeholder="Selecione o departamento">
    </model-select>
    <md-input-container>
      <label>Nome do departamento</label>
      <md-input v-model="item.text" maxlength="30"></md-input>
    </md-input-container>

    <md-button @click="editar" class="md-raised md-primary">Editar</md-button>
    <md-button @click="deletar" class="md-raised md-primary">Deletar</md-button>

  </div>
</md-tab>
</md-tabs>
</template>

<script>
import axios from 'axios';
import { ModelSelect, MultiSelect  } from 'vue-search-select'
export default {
  name: 'criarAnalise',
  components: {
    ModelSelect,
    MultiSelect
  },
  data() {
    return {
      single:{},
      data:{nome:'', fk_empresa: '', analises: []},
      options: [
      ],
      item: {
        value: '',
        text: ''
      },
      optionsAna: [
      ],
      itemsAna: []
    }
  },
  methods: {
    init: function () {
      var self = this
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/get',{
      })
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        var resp = JSON.stringify(response.data).replace(new RegExp("id\"", 'g'), 'value\"').replace(new RegExp("nome", 'g'), 'text')
        self.options = JSON.parse(resp)
        console.log(self.options)

      })
      .catch(function (error) {
        console.log(error);
      });

      //get analalises
      axios.post('http://localhost:8080/tcc-back/webapi/analise/get',{
      })
      .then(function (response) {
        console.log(JSON.stringify(response.data))
        var resp = JSON.stringify(response.data).replace(new RegExp("id\"", 'g'), 'value\"').replace(new RegExp("nomeAnalise", 'g'), 'text')
        self.optionsAna = JSON.parse(resp)

      })
      .catch(function (error) {
        console.log(error);
      });

    },
    onSelect (items) {
      this.data.analises = items
    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data;
      var ids= ''
      req.analises.map(function(value, key) {
        ids = ids + value.value +',';
      });
      req.analises = ids.substring(0, ids.length -1);

      // req.analises = req.analises.toString();
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/criar',{
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
        nome: this.item.text
      }
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/editar',{
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
        nome: this.item.text
      }
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/deletar',{
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
