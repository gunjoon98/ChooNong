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
/* 전역 스타일 */
/* './assets/so.png' */
body {
  font-size: 24px; /* 기본 텍스트 크기 */
  color: #323437;
  font-family: 'TTLaundryGothicB';
  /* 커서 변경 */
  /* cursor : url('./assets/search.png') 54 54 , auto; */
  
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
/* app {
  min-height: 100%;
  position: relative;
  background-color: brown;
} */
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
  /* min-width: 1340px; */
  height: 110px;
  position: fixed;
  top: 0px;
  z-index: 999;
}

.main-container {
  width: 1519.2px;
  padding: 50px 25px 100px 25px;
  margin: 150px auto 0 auto;
  /* flex-grow: 1; */
  /* margin-bottom: 50px; */
  min-height: calc(100vh - 190px);
}

/* .bottom-picture {
  width: 80%;
  position: relative;
  top: 7px;
  left: 10vw;
} */

.the-footer {
  width: 100%;
  min-width: 1340px;
  height: 65px;
  /* position: absolute;
  bottom: 0; */
  /* margin-top: auto; */
}
</style>