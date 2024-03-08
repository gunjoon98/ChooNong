<template>
  <the-header-nav />
  <router-view class="main-container" />
  <the-footer class="the-footer" />
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import TheFooter from '@/components/common/TheFooter.vue';
</script>

<style>
#app {
  min-height: 100vh;
  position: relative;
}

body {
  margin: 0;
}

.main-container {
  padding: 50px 15vw;
}

.the-footer {
  width: 100%;
  position: absolute;
  bottom: 0;
}
</style>