<template>
  <md-tabs>
    <md-snackbar :md-position="'top' + ' ' + 'center'" ref="snackbar" :md-duration="duration">
      <span>{{msg}}</span>
    </md-snackbar>

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
  <md-tab  :md-disabled="this.$route.path === '/CriarDepartamento/1'" id="editar" md-label="Editar">
    <div style="margin:3%">
      <h2>Editar departamentos</h2>
      <!-- object value -->
      <model-select :options="options"
      v-model="item"
      placeholder="Selecione o departamento">
    </model-select>
    <br>
    <multi-select :options="optionsAna"
    :selected-options="data.analises"
    placeholder="Selecione as analises"
    @select="onSelect">
  </multi-select>
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
import router from 'vue-router';
import { ModelSelect, MultiSelect  } from 'vue-search-select'
export default {
  name: 'criarAnalise',
  components: {
    ModelSelect,
    MultiSelect
  },
  data() {
    return {
      msg : '',
      duration: 3000,
      single:{},
      data:{nome:'', fk_empresa: this.$session.get('id_empresa'), analises: []},
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
      var req = {fk_empresa: this.$session.get('id_empresa')}
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/getEmpresa',{
        req
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
      req = {fk_empresa: this.$session.get('id_empresa')}
      //get analalises
      axios.post('http://localhost:8080/tcc-back/webapi/analise/getEmpresa',{
        req
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
    open() {
      this.$refs.snackbar.open();
    },
    close() {
      this.$refs.snackbar.close();
    },
    criar : function(){
      console.log('fazendo req')
      var req = this.data;
      var that = this;
      var ids= ''
      req.analises.map(function(value, key) {
        ids = ids + value.value +',';
      });
      req.analises = ids.substring(0, ids.length -1);

      axios.post('http://localhost:8080/tcc-back/webapi/departamento/criar',{
        req
      })
      .then(function (response) {
        that.msg = "Departamento criado"
        that.open()
        console.log(response);
        if(response.data.id !== 0 ){
          if(that.$route.path === '/CriarDepartamento/1'){
            that.$session.set('id_departamento', response.data.id);
            that.$router.push("/CriarUsuario/1")
          }
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    editar : function(){
      console.log('fazendo req')
      var ids= ''
      var that = this;
      var req = {
        id: this.item.value,
        nome: this.item.text,
        analises: this.data.analises,
        fk_empresa: this.$session.get('id_empresa')
      }

      req.analises.map(function(value, key) {
        ids = ids + value.value +',';
      });
      req.analises = ids.substring(0, ids.length -1);

      axios.post('http://localhost:8080/tcc-back/webapi/departamento/editar',{
        req
      })
      .then(function (response) {
        that.msg = "Departamento editado"
        that.open()

        that.$session.set('analises',req.analises);
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
        nome: this.item.text
      }
      axios.post('http://localhost:8080/tcc-back/webapi/departamento/deletar',{
        req
      })
      .then(function (response) {
        that.msg = "Departamento deletado"
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
