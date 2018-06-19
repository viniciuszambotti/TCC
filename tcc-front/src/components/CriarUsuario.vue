<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%">
        <h2>Cadastrar novo usuário</h2>
        <md-input-container>
          <label>Nome</label>
          <md-input v-model="data.usuario" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container v-show="this.$route.path !== '/CriarUsuario/1'">
          <model-select  :options="options"
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
  <md-tab :md-disabled="this.$route.path === '/CriarUsuario/1'" id="editar" md-label="Editar">
    <div style="margin:3%">
      <h2>Editar dados pessoais</h2>
      <model-select :options="options2"
      v-model="item2"
      placeholder="Selecione o usuario">
    </model-select>
      <md-input-container>
        <label>Nome</label>
        <md-input v-model="item2.text" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <model-select :options="options"
        v-model="item"
        placeholder="Departamento">
      </model-select>
    </md-input-container>
    <md-input-container>
      <label>CPF</label>
      <md-input v-model="item2.cpf" maxlength="30"></md-input>
    </md-input-container>
    <md-input-container>
      <label>Email</label>
      <md-input v-model="item2.email" maxlength="30"></md-input>
    </md-input-container>
    <md-input-container>
      <label>Senha</label>
      <md-input  type="password" v-model="item2.senha" maxlength="30"></md-input>
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
  name: 'criarUsuario',
  components: {
    ModelSelect
  },
  data() {
    return {
      data:{usuario:'', cpf: '', email: '', fk_departamento: this.$session.get('id_departamento'), senha:""},
      options: [
      ],
      options2: [
      ],
      item: {
        value: '',
        text: ''
      },
      item2: {
        value: '',
        text: ''
      }
    }
  },
  methods: {
    init: function () {
      var self = this
      axios.post('http://localhost:8080/tcc-back/webapi/usuario/get',{
      })
      .then(function (response) {
        // console.log(JSON.stringify(response.data))
        var resp = JSON.stringify(response.data).replace(new RegExp("id\"", 'g'), 'value\"').replace(new RegExp("usuario", 'g'), 'text')
        self.options2 = JSON.parse(resp)
        // console.log(self.options)

      })
      .catch(function (error) {
        console.log(error);
      });
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/get',{
      })
      .then(function (response) {
        // console.log(JSON.stringify(response.data))
        var resp = JSON.stringify(response.data).replace(new RegExp("id\"", 'g'), 'value\"').replace(new RegExp("nome", 'g'), 'text')
        self.options = JSON.parse(resp)

      })
      .catch(function (error) {
        console.log(error);
      });

    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data;
        if(this.$route.path !== '/CriarUsuario/1')
       req.fk_departamento = this.item.value

      var that = this;

      console.log(req)

      axios.post('http://localhost:8080/tcc-back/webapi/usuario/criar',{
        req
      })
      .then(function (response) {
        console.log(response);
        if(that.$route.path === '/CriarUsuario/1')
          that.$router.push('/');
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    editar : function(){
      console.log('fazendo req')
      var req = {
        id: this.item2.value,
        usuario: this.item2.text,
        cpf: this.item2.cpf,
        email: this.item2.email,
        senha: this.item2.senha,
        fk_departamento: this.item.value
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
        id: this.item2.value,
        usuario: this.item2.text
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
