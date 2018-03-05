<template>
  <md-tabs>
    <md-tab id="criar" md-label="Criar">
      <div style="margin:3%">
        <h2>Cadastrar novo usuário</h2>
        <md-input-container>
          <label>Nome</label>
          <md-input v-model="data.nome" maxlength="30"></md-input>
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
          <model-select :options="options"
          v-model="item"
          placeholder="Empresa">
        </model-select>
      </md-input-container>
      <md-input-container>
        <model-select :options="options"
        v-model="item"
        placeholder="Departamento">
      </model-select>
    </md-input-container>
    <md-button class="md-raised md-primary">Criar</md-button>
  </div>
</md-tab>
<md-tab id="editar" md-label="Editar">
  <div style="margin:3%">
    <h2>Editar dados pessoais</h2>
    <md-input-container>
      <label>Nome</label>
      <md-input v-model="data.nome" maxlength="30"></md-input>
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
      <model-select :options="options"
      v-model="item"
      placeholder="Empresa">
    </model-select>
  </md-input-container>
  <md-input-container>
    <model-select :options="options"
    v-model="item"
    placeholder="Departamento">
  </model-select>
</md-input-container>
<md-button class="md-raised md-primary">Criar</md-button>
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
      data:{nome:'', cpf: '', email: '', departamento: "", empresa: ""},
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
