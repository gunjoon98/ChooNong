<template>
  <div class="router-container">
    <router-view />
    <img src="C:\Users\SSAFY\Desktop\S10P22B210\frontend\src\assets\chicken.png" class="bottom-picture">
  </div>
</template>

<script setup>
import CalculatorComponent from '@/components/calculator/CalculatorComponent.vue';

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