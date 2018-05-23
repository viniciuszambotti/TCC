<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%">
        <h2>Cadastrar novo usuário</h2>
        <md-input-container>
          <label>Nome</label>
          <md-input v-model="data.usuario" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <model-select :options="options"
          v-model="item"
          placeholder="Departamento">
        </model-select>
      </md-input-container>
      <md-input-container>
        <label>CPF</label>
        <md-input v-model="data.cpf" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <label>Email</label>
        <md-input v-model="data.email" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <label>Senha</label>
        <md-input  type="password" v-model="data.senha" maxlength="30"></md-input>
      </md-input-container>

      <md-button @click="criar" class="md-raised md-primary">Criar</md-button>
    </div>
  </md-tab>
  <md-tab id="editar" md-label="Editar">
    <div style="margin:3%">
      <h2>Editar dados pessoais</h2>
      <md-input-container>
        <label>Nome</label>
        <md-input v-model="data.usuario" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <model-select :options="options"
        v-model="item"
        placeholder="Departamento">
      </model-select>
    </md-input-container>
    <md-input-container>
      <label>CPF</label>
      <md-input v-model="data.cpf" maxlength="30"></md-input>
    </md-input-container>
    <md-input-container>
      <label>Email</label>
      <md-input v-model="data.email" maxlength="30"></md-input>
    </md-input-container>
    <md-input-container>
      <label>Senha</label>
      <md-input  type="password" v-model="data.senha" maxlength="30"></md-input>
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
  name: 'criarUsuario',
  components: {
    ModelSelect
  },
  data() {
    return {
      data:{usuario:'', cpf: '', email: '', fk_departamento: "", senha:""},
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

    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data;
      req.fk_departamento = this.item.value

      console.log(req)

      axios.post('http://localhost:8080/tcc-back/webapi/usuario/criar',{
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
      axios.post('http://localhost:8080/tcc-back/webapi/usuario/editar',{
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
      axios.post('http://localhost:8080/tcc-back/webapi/usuario/deletar',{
        req
      })
      .then(function (response) {
        self.init();
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    onSelect (item) {
      this.item = item
    },
    reset () {
      this.item = {}
    },
    selectOption () {
      // select option from parent component
      this.item = this.options[0]
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
