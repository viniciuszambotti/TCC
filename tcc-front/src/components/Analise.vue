<template>
  <md-tabs>
    <md-tab id="selectAnalise" md-label="Configurar visualização">

    </md-tab>
    <md-tab id="tabular" md-label="Tabelas">
    </md-tab>

  </md-tab>
  <md-tab id="graficos" md-label="Gráficos">
    <div class="small">
      <line-chart :chart-data="datacollection"></line-chart>
      <bar-chart :chart-data="datacollection"></bar-chart>
      <doughnut :chart-data="datacollection"></doughnut >
        <pie :chart-data="datacollection"></pie>
        <bubble :chart-data="datacollection2"></bubble>

        <button @click="fillData()">Randomize</button>
      </div>
    </md-tab>
  </md-tabs>
</template>

<script>
import LineChart from './charts/LineChart.js'
import BarChart from './charts/Bar.js'
import Doughnut from './charts/Doughnut.js'
import Pie from './charts/Pie.js'
import Bubble from './charts/Bubble.js'



export default {
  components: {
    LineChart,
    BarChart,
    Doughnut,
    Pie,
    Bubble
  },
  data () {
    return {
      datacollection: null
    }
  },
  mounted () {
    this.fillData()
  },
  methods: {
    fillData () {
      this.datacollection = {
        labels: ['teste1', 'teste2'],
        datasets: [
          {
            label: 'OI',
            backgroundColor: '#f87979',
            data: [this.getRandomInt(), this.getRandomInt()]
          }, {
            label: 'OI',
            backgroundColor: '#f87979',
            data: [this.getRandomInt(), this.getRandomInt()]
          }
        ]
      },
      this.datacollection2 = {
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#f87979',
            data: [
              {
                x: 20,
                y: 25,
                r: 5
              },
              {
                x: 40,
                y: 10,
                r: 10
              },
              {
                x: 30,
                y: 22,
                r: 30
              }
            ]
          }]
        }
      },
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      }
    }
  }
  </script>

  <style>
  .small {
    max-width: 600px;
    margin:  150px auto;
  }
  </style>
