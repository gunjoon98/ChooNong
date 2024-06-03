<template>
  <div class="main-component">
    <router-view></router-view>
    <img src="@\assets\scarecrow.png" class="bottom-picture" />
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style scoped>
.main-component {
  position: relative;
}

.bottom-picture {
  margin-top: 100px;
  max-width: 120%;
  height: 80px;
  position: absolute;
  bottom: 0px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
