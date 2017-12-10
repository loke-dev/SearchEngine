<template>
  <div id="search">
    <v-layout column align-center>
      <v-layout row>
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
              <v-flex xs12>
                <v-text-field v-model="input" @keyup.enter.native="search" class="searchInput" name="input-1" label="Query" id="search"></v-text-field>
                <v-btn v-on:click="search" :disabled="disabled" color="primary">Search</v-btn>
              </v-flex>
          </v-layout>
        </v-container>
      </v-layout>
      <ul class="list">
        <li v-for="(res, i) in result" :key="i">
            {{ `Score: ${res.score} - Page: ${res.page}` }}
        </li>
      </ul>
    </v-layout>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data () {
    return {
      result: [],
      input: [],
      disabled: false
    }
  },
  methods: {
    search () {
      this.disabled = true
      axios.get(`http://localhost:1234/?query=` + this.input)
      .then(response => {
        this.result = []
        this.input = ''
        this.disabled = false
        console.log(response)
        response.data.payload.forEach(element => {
          this.result.push(element)
        });
      })
    }
  }
}
</script>

<style lang="stylus" scoped>
.list
  width: 50%
  text-align: left
  list-style: none
  padding-top: 50px
</style>
