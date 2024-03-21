<template>
  <div>
    <div class="main-container">
      <div class="search-container">
        <div>귀농지 검색하기</div>
        <RegionSearchComponentVue class="search-box"/>
        <!-- 
          <input type="search">
          <button type="button">검색</button> -->
      </div>
      <div class="content-wrapper">
        <div class="content-container">
          <img src="">@사진/아이콘@
          <div>귀농지 추천 설문 설명</div>
          <router-link to="/survey">
            <button type="button">시작하기</button>
          </router-link>
        </div>
        <div class="content-container">
          <img src="">@사진/아이콘@
          <div>작물 수익 계산기 설명</div>
          <router-link to="/calculator">
            <button type="button">시작하기</button>
          </router-link>
        </div>
        <div class="content-container">
          <div>귀농 준비 체크리스트</div>
          <router-link to="/checklist">
            <button type="button">시작하기</button>
          </router-link>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script setup>
import RegionSearchComponentVue from '../region/RegionSearchComponent.vue';
</script>

<style scoped>
body {
  display: flex;
  justify-content: center;
}

.main-container {
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  padding: 10px;
  /* width: 100vw; */
  /* 가로 전체 화면 */
  /* max-width: 1500px; */
  /* 최대 너비 제한 */
}

.content-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.content-container {
  border: 2px solid #e9e9e9;
  border-radius: 15px;
  margin: 10px;
  padding: 10px;
  height: 300px;
  flex: 1;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.search-container {
  border: 2px solid #e9e9e9;
  border-radius: 15px;
  margin: 10px;
  padding: 10px;
  /* flex: 1; */
  /* text-align: center; */
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}

.search-box {
  /* height: 300px; */
  padding: 0px;
  border: none;
  margin: 0;
}

button {
  display: block;
  margin: 0 auto;
  width: 110px;
  height: 50px;
  background-color: #C6EB74;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  /* 커서 포인터로 변경 */
  /* transition: background-color 0.3s ease; */
  /* 배경색 변화에 대한 전환 */
  /* color: #000000; */
  /* text-decoration: none; */
  text-align: center;
  line-height: 40px;
}

a {
  text-decoration: none;
  color: inherit;
}
</style>