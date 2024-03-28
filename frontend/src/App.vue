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
  color: #333333;
  /* 커서 변경 */
  /* cursor : url('./assets/search.png') 54 54 , auto; */
  
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
}

.the-header-nav {
  width: 100%;
  min-width: 1340px;
  height: 150px;
  /* position: fixed;
  top: 0px; */
}

.main-container {
  width: 1340px;
  padding: 50px 70px 100px 70px;
  margin: 0 auto;
  /* flex-grow: 1; */
  /* margin-bottom: 50px; */
  min-height: calc(100vh - 200px);
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