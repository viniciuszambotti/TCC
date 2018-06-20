<template>
  <md-tabs>
    <md-snackbar :md-position="'top' + ' ' + 'center'" ref="snackbar" :md-duration="duration">
      <span>{{msg}}</span>
    </md-snackbar>
    <md-tab  :md-disabled="this.$route.path !== '/CriarEmpresa/1'" id="criar" md-label="Criar">
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
    <md-tab :md-disabled="this.$route.path === '/CriarEmpresa/1'" id="editar" md-label="Editar">
      <div style="margin:3%" >

        <h2>Editar empresa</h2>
        <model-select :options="options"
        v-model="item"
        placeholder="Selecione a empresa">
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
import router from 'vue-router';
import { ModelSelect } from 'vue-search-select'
export default {
  name: 'criarEmpresa',
  components: {
    ModelSelect
  },
  data() {
    return {
      ed: false,
      data:{nome:'', cnpj:''},
      options:[],
      msg : '',
      duration: 3000,
      item: {
        value: '',
        text: ''
      }
    }
  },
  methods: {
    init: function () {
      var self = this
      var req = {id: this.$session.get('id_empresa')}
      axios.post('http://localhost:8080/tcc-back/webapi/empresa/get',{
        req
      })
      .then(function (response) {
        var resp = JSON.stringify(response.data).replace(new RegExp("id", 'g'), 'value').replace(new RegExp("nome", 'g'), 'text')
        self.options = JSON.parse(resp)

      })
      .catch(function (error) {
        console.log(error);
      });

    },
    open() {
      this.$refs.snackbar.open();
    },
    close() {
      this.$refs.snackbar.close();
    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data
      var that = this
      axios.post('http://localhost:8080/tcc-back/webapi/empresa/criar',{
        req
      })
      .then(function (response) {
        that.msg = "Empresa criada"
        that.open()
        that.$session.start()
        that.$session.set('id_empresa', response.data.id);
        that.$router.push("/CriarAnalise/1")

      })
      .catch(function (error) {
        console.log(error);
      });
    },
    editar : function(){
      var that = this
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
        that.msg = "Empresa editada"
        that.open()
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    deletar : function(){
      var self = this
      var that = this
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
        that.msg = "Empresa deletada"
        that.open()
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
