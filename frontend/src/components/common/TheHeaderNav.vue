<template>
  <div class="header-container">
    <div class="logo-wrapper">
      <router-link to="/">
        <!-- <img src=""> -->
        <h1>로고</h1>
      </router-link>
    </div>
    <div class="menu-container">
      <div><router-link to="/survey">귀농지 추천 설문</router-link></div>
      <div>귀농지 검색</div>
      <div><router-link to="/calculator">작물 수익 계산기</router-link></div>
      <div><router-link to="/checklist">귀농 준비 체크리스트</router-link></div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.header-container {
  background-image: linear-gradient(to bottom, #ADE6F9, #ffffff);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.menu-container {
  display: flex;
  flex-direction: row;
}
</style>