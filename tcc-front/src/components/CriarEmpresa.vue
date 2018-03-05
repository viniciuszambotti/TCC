<template>
  <div>
    <md-stepper md-alternate-labels>
      <md-step md-label="Criar empresa">
        <h2>Cadastrar nova empresa</h2>
        <md-input-container>
          <label>Nome da empresa</label>
          <md-input v-model="data.nomeEmpresa" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>CNPJ da empresa</label>
          <md-input v-model="data.cnpjEmpresa" maxlength="30"></md-input>
        </md-input-container>
      </md-step>
      <md-step md-label="Criar departamento">
        <h2>Adicionar departamento(s)</h2>
        <md-chips v-model="data.nomeDepartamento" md-input-placeholder="Adicionar novo departamento"></md-chips>
      </md-step>
      <md-step md-label="Selecionar análise">
        <h2>Mapear análises</h2>
        <div  class="phone-viewport">
          <md-list>
            <md-list-item v-for="value in data.nomeDepartamento">
              <span>{{value}}</span>
              <md-list-expand>
                <md-list>
                  <md-list-item v-for="analise in listAnalises"  class="md-inset">
                    <md-switch @change="addAnalise(value, analise)" id="my-test8" name="check">{{analise}}</md-switch>
                  </md-list-item>
                </md-list>
              </md-list-expand>
            </md-list-item>
          </md-list>
        </div>
      </md-step>
    </md-stepper>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'criarEmpresa',
  data() {
    return {
      data:{nomeEmpresa:'', cnpjEmpresa:'', nomeDepartamento: []},
      listAnalises:["Analise 1", "Analise 2", "Analise 3", "Analise 4"],
      analisesPorDp:[{nomeDepartamento:'',analises:[]}]
    }
  },
  methods: {
    addAnalise: function(dep, analises){
      if(!this.departamentoExiste(dep))
      this.analisesPorDp.push({nomeDepartamento:dep, analises:[]})
      this.gerenciaAnalises(dep, analises)

      console.log(  this.analisesPorDp)
    },
    departamentoExiste: function (departamento){
      var existe = false;
      for(var i =0; i< this.analisesPorDp.length; i++){
        if(departamento === this.analisesPorDp[i].nomeDepartamento){
          return true
        }
        else {
          existe=false
        }
      }
      return existe
    },
    gerenciaAnalises: function (dep, ana){
      var depatamento
      var analise
      var existeAnalise= false
      for (var i =0; i< this.analisesPorDp.length; i++){
        depatamento = this.analisesPorDp[i]

        if (depatamento.nomeDepartamento === dep){

          console.log(depatamento.analises)

          if(depatamento.analises.length === 0){
            depatamento.analises.push(ana)
            return
          }

          for (var j = 0; j< depatamento.analises.length; j++){
            analise =  depatamento.analises[j]
            if (analise === ana){
              depatamento.analises.splice(j, 1)
              return
            }else{
              existeAnalise= false
            }
          }

          if (!existeAnalise){
            depatamento.analises.push(ana)
            return
          }
        }

      }
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
