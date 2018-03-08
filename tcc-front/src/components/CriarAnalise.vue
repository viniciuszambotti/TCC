<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%">
        <h2>Cadastrar nova análise</h2>
        <md-input-container>
          <label>Nome da análise</label>
          <md-input v-model="data.nome" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>Nome do script</label>
          <md-input v-model="data.script" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>Tabela de saída</label>
          <md-input v-model="data.table" maxlength="30"></md-input>
        </md-input-container>
        <md-input-container>
          <label>Upload script (.py ou .r)</label>
          <md-file v-model="single"></md-file>
        </md-input-container>
        <md-button class="md-raised md-primary">Criar</md-button>
      </div>
    </md-tab>
    <md-tab id="editar" md-label="Editar">
      <div style="margin:3%">
        <h2>Editar analises</h2>
        <!-- object value -->
        <model-select :options="options"
        v-model="item"
        placeholder="select item">
      </model-select>
      <md-input-container>
        <label>Nome da análise</label>
        <md-input v-model="data.nome" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <label>Nome do script</label>
        <md-input v-model="data.script" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <label>Tabela de saída</label>
        <md-input v-model="data.table" maxlength="30"></md-input>
      </md-input-container>
      <md-input-container>
        <label>Tabela de saída</label>
        <md-input v-model="data.table" maxlength="30"></md-input>
      </md-input-container>
      <md-button class="md-raised md-primary">Editar</md-button>

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
      data:{nome:'', script: '', table: '', dataCriacao: new Date(), isAtualizada: 1},
      editarAnalise:{nome:'A', script: 'aaa', table: '111', dataCriacao: new Date(), isAtualizada: 1},
      options: [
        { value: '1', text: 'aa' + ' - ' + '1' },
        { value: '2', text: 'ab' + ' - ' + '2' },
        { value: '3', text: 'bc' + ' - ' + '3' },
        { value: '4', text: 'cd' + ' - ' + '4' },
        { value: '5', text: 'de' + ' - ' + '5' }
      ],
      item: {
        value: '',
        text: ''
      }
    }
  },
  methods: {
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
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
