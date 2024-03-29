<template>
  <div class="header-container">
    <div class="header-wrapper">
      <div class="logo-wrapper">
        <router-link to="/" @click="clickMenu(0)">
          <img src="@/assets/logo.png" class="logo-image">
        </router-link>
      </div>
      <!-- <img src="@/assets/cloud.png" /> -->
      <div class="menu-container">
        <router-link to="/survey" @click="clickMenu(1)" class="router-link"
          :class="{ 'clicked-menu': clickedMenu === 1 }">
          <p>귀농지 설문</p><img src="@/assets/cloud.png" :class="{ 'cloud-image': clickedMenu === 1 }"
            v-if="clickedMenu === 1">
        </router-link>
        <router-link to="/region" @click="clickMenu(2)" class="router-link"
          :class="{ 'clicked-menu': clickedMenu === 2 }">
          <p>지역 정보</p><img src=@\assets\search.png class="search-icon" />
          <img src="@/assets/cloud.png" :class="{ 'cloud-image': clickedMenu === 2 }" v-if="clickedMenu === 2">
        </router-link>
        <router-link to="/calculator" @click="clickMenu(3)" class="router-link"
          :class="{ 'clicked-menu': clickedMenu === 3 }">
          <p>작물 수익 계산기</p><img src="@/assets/cloud.png" :class="{ 'cloud-image': clickedMenu === 3 }"
            v-if="clickedMenu === 3">
        </router-link>
        <router-link to="/checklist" @click="clickMenu(4)" class="router-link"
          :class="{ 'clicked-menu': clickedMenu === 4 }">
          <p>체크리스트</p><img src="@/assets/cloud.png" :class="{ 'cloud-image': clickedMenu === 4 }"
            v-if="clickedMenu === 4">
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import { useRoute } from "vue-router";
const clickedMenu = ref(0);
const route = useRoute();

watch(route, (currentRoute) => {
  // 현재 라우트에 따라 clickedMenu 값을 업데이트
  if (currentRoute.path.startsWith('/survey')) {
    clickedMenu.value = 1;
  } else if (currentRoute.path.startsWith('/region')) {
    clickedMenu.value = 2;
  } else if (currentRoute.path.startsWith('/calculator')) {
    clickedMenu.value = 3;
  } else if (currentRoute.path.startsWith('/checklist')) {
    clickedMenu.value = 4;
  } else {
    clickedMenu.value = 0; // 기본값 또는 해당되지 않는 경우
  }
}, { immediate: true }); // 컴포넌트 마운트 시 즉시 실행

const clickMenu = (menuNum) => {
  clickedMenu.value = menuNum;
}

</script>

<style scoped>
p {
  margin: 0;
  height: 80px;
  line-height: 80px;
}

.header-container {
  padding: 0 2vw;
  background-image: linear-gradient(to bottom, #ADE6F9, #ffffff);
  /* display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative; */
}

.header-wrapper {
  width: 1250px;
  height: 100px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
}

.logo-image {
  width: 150px;
  height: 150px;
  position: absolute;
  /* left: 2vw; */
  top: 10px;
}

.menu-container {
  width: 830px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.search-icon {
  width: 30px;
  height: auto;
  margin: 5px;
  z-index: 999;
}

.router-link {
  position: relative;
  z-index: 999;
  display: flex;
  align-items: center;
}

.router-link:hover {
  font-weight: bold;
}

.router-link p {
  z-index: 999;
}

.clicked-menu {
  font-weight: bold;
  line-height: 130px;
}

.cloud-image {
  width: 180px;
  height: auto;
  position: absolute;
  left: 50%;
  /* 부모 요소의 정중앙을 기준으로 좌측으로 이동 */
  top: 50%;
  /* 부모 요소의 정중앙을 기준으로 상단으로 이동 */
  transform: translate(-50%, -60%);
  /* 이미지를 가운데 정렬하기 위해 translate 사용 */
  z-index: 1;
}

a {
  color: #000000;
  /* 텍스트 색상 */
  text-decoration: none;
  /* 밑줄 제거 */
}
</style>
