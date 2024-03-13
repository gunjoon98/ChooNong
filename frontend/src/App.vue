<template>
  <div class="body-container">
    <the-header-nav class="the-header-nav"/>
    <router-view class="main-container" />
    <!-- <img src="C:\Users\SSAFY\Desktop\S10P22B210\frontend\src\assets\chicken.png" class="bottom-picture"> -->
    <the-footer class="the-footer" />
  </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import TheFooter from '@/components/common/TheFooter.vue';
</script>

<style>
/* #app {
  min-height: 100vh;
  position: relative;
} */

html, body {
  height: 100%;
  margin: 0;
}

/* .body-container {
  height: 100%;
	display: flex;
	flex-direction: column;
} */

.the-header-nav {
  height: 120px;
  position: fixed;
  top: 0px;
}

.main-container {
  padding: 50px 15vw 100px 15vw;
  /* flex-grow: 1; */
  /* margin-bottom: 50px; */
  /* min-height: calc(100vh - 170px); 연산자 주위에 공백 추가 */
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