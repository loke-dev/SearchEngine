<template>
  <div id="app">
    <v-app>
      <v-content>
        <v-container class="container" fluid>
          <search></search>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
import search from './Search.vue'

export default {
  name: 'app',
  components: {
    search
  }
}
</script>

<style lang="stylus">
.application
  background-color: white!important
</style>
