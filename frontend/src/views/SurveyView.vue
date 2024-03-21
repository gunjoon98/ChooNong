<template>
  <div class="router-container">
    <RouterView/>
    <img src="../assets/chicken.png" class="bottom-picture">
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';

</script>

<style scoped>
.router-container {
  position: relative;
}

.bottom-picture {
  max-width: 120%;
  height: 100px;
  position: absolute;
  bottom: -3px;
}  
</style>