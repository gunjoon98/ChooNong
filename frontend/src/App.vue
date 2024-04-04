<template>
  <div class="body-container">
    <the-header-nav class="the-header-nav"/>
    <RouterView class="main-container" />
    <the-footer class="the-footer" />
  </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import TheFooter from '@/components/common/TheFooter.vue';
import { RouterView } from 'vue-router';
</script>

<style>
body {
  font-size: 24px;
  color: #323437;
  font-family: 'TTLaundryGothicB';
}

button {
  font-family: 'TTLaundryGothicB';
}

@font-face {
    font-family: 'TTLaundryGothicB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

html, body {
  margin: 0;
}

button {
  font-size: 1.3rem;
}

</style>

<style scoped>
button {
  font-size: 1.3rem;
}

html, body {
  height: 100%;
}

.body-container {
  height: 100%;
	display: flex;
	flex-direction: column;
  position: relative;
}

.the-header-nav {
  width: 100%;
  min-width: 1050px;
  height: 110px;
  position: fixed;
  top: 0px;
  z-index: 999;
}

.main-container {
  width: 1519.2px;
  padding: 50px 25px 100px 25px;
  margin: 150px auto 0 auto;
  min-height: calc(100vh - 190px);
}

.the-footer {
  width: 100%;
  min-width: 1340px;
  height: 65px;
}
</style>