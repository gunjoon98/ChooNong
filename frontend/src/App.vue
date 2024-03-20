<template>
  <div class="body-container">
    <the-header-nav class="the-header-nav"/>
    <RouterView class="main-container" />
    <!-- <img src="C:\Users\SSAFY\Desktop\S10P22B210\frontend\src\assets\chicken.png" class="bottom-picture"> -->
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
body {
  font-size: 24px; /* 기본 텍스트 크기 */
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
  height: 120px;
  /* position: fixed;
  top: 0px; */
}

.main-container {
  padding: 50px 14vw 100px 14vw;
  /* flex-grow: 1; */
  /* margin-bottom: 50px; */
  min-height: calc(100vh - 170px);
}

/* .bottom-picture {
  width: 80%;
  position: relative;
  top: 7px;
  left: 10vw;
} */

.the-footer {
  width: 100%;
  height: 65px;
  /* position: absolute;
  bottom: 0; */
  /* margin-top: auto; */
}
</style>