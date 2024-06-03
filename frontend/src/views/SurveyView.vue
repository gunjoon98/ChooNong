<template>
  <div class="router-container">
    <RouterView class="router-view"/>
    <img src="@\assets\tractor.png" class="bottom-picture">
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';

</script>

<style scoped>
.router-container {
  position: relative;
}

.router-view {
  margin-bottom: 100px;
}

.bottom-picture {
  max-width: 120%;
  height: 100px;
  position: absolute;
  bottom: -1px;
  left: 50%;
  transform: translateX(-50%);
}  
</style>