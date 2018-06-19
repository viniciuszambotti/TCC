<template>
<div style="margin:13%">
  <h1>Login</h1 >
  <md-input-container>
    <label>Email</label>
    <md-input v-model="data.email"></md-input>
  </md-input-container>
  <md-input-container>
    <label>Senha</label>
    <md-input  type="password" v-model="data.senha" ></md-input>
  </md-input-container>

  <md-button @click="criar" class="md-raised md-primary">Login</md-button>
  <br>
  <md-button @click="changePage('/CriarEmpresa/1')" class="md-raised md-primary">Cadastrar novo usuário</md-button>
</div>
</template>

<script>
import axios from 'axios';
import router from 'vue-router';
export default {
  name: 'login',
  data() {
    return {
      data:{email:'', senha: ''}
    }
  },
  methods: {
    init: function () {
    },
    criar : function(){
      var that = this
      var req = this.data
      axios.post('http://localhost:8080/tcc-back/webapi/usuario/verificar',{
        req
      })
      .then(function (response) {
        that.$session.start()
        that.$session.set('email',response.data.email);
        that.$session.set('fk_departamento',response.data.fk_departamento);
        that.$session.set('usuario',response.data.usuario);
        var re2 = {id:response.data.fk_departamento}
        axios.post('http://localhost:8080/tcc-back/webapi/departamento/getById',{
          re2
        })
        .then(function (response) {
          that.$session.set('analises',response.data.analises);

          if(response.data.analises !== undefined)
          that.$router.push("/Main")
        })
        .catch(function (error) {
          console.log(error);
        });

      })
      .catch(function (error) {
        console.log(error);
      });




      this.$session.set('enable',true)
    },
    changePage: function(name){
      this.$router.push(name)
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
