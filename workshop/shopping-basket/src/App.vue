<template>
  <div id="app">
    <div class="text-center">
      <img alt="Vue logo" src="./assets/logo.png">
      <h1>Welcome to Fruit Shop</h1>
    </div>
    <div class="container">
      <div class="row justify-content-md-center">
        <div class="col-md-3">
          <FruitList/>
        </div>
        <div class="col-md-3">
          <Checkout/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FruitList from './components/FruitList.vue'
import Checkout from './components/Checkout.vue'


export default {
  name: 'App',
  components: {
    FruitList,
    Checkout
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
