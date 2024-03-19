<template>
  <div class="header-container">
    <div class="logo-wrapper">
      <router-link to="/" @click="clickMenu(0)">
        <img src="@/assets/logo.png" class="logo-image">
      </router-link>
    </div>
    <!-- <img src="@/assets/cloud.png" /> -->
    <div class="menu-container">
      <div class="one-menu" :class="{ 'clicked-menu' : clickedMenu === 1 }"><router-link to="/survey" @click="clickMenu(1)">귀농지 추천 설문</router-link></div>
      <div class="one-menu">귀농지 검색</div>
      <div class="one-menu" :class="{ 'clicked-menu' : clickedMenu === 3 }"><router-link to="/calculator" @click="clickMenu(3)">작물 수익 계산기</router-link></div>
      <div class="one-menu" :class="{ 'clicked-menu' : clickedMenu === 4 }"><router-link to="/checklist" @click="clickMenu(4)">귀농 준비 체크리스트</router-link></div>
      <div class="one-menu" :class="{ 'clicked-menu' : clickedMenu === 5 }"><router-link to="/region" @click="clickMenu(5)">지역 정보</router-link></div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const clickedMenu = ref(0);
const clickMenu = function (menuNum) {
  clickedMenu.value = menuNum;
}

</script>

<style scoped>
.header-container {
  padding: 0 2vw;
  background-image: linear-gradient(to bottom, #ADE6F9, #ffffff);
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
}

.logo-image {
  width: 120px;
  height: 120px;
  position: absolute;
  left: 2vw;
  top: 20px;
}

.menu-container {
  width: 830px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.one-menu {
  width: 200px;
  height: 90px;
  text-align: center;
  line-height: 130px;
  position: relative;
}

/* 호버 상태에서 구름 이미지 크기 일치 */
.one-menu:hover {
  background-image: url("@/assets/cloud.png");
  background-size: cover;
  
}

.clicked-menu {
  background-image: url("@/assets/cloud.png");
  background-size: cover;
  font-weight: bold;
  line-height: 130px;
}

.one-menu a:hover {
  font-weight: bold;
}

.one-menu a {
  color: #000000; /* 텍스트 색상 */
  text-decoration: none; /* 밑줄 제거 */
}
</style>
