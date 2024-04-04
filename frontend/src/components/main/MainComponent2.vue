<template>
  <div class="second-container">
    <h2>빅데이터 기반 맞춤형 귀농지 추천 서비스!</h2>
    <div class="explanation-wrapper">
      <p><img src="@/assets/star.png" class="mark-image"> 120만 개 이상의 데이터 셋을 클러스터링하고 추천 알고리즘을 적용하여 최적의 귀농지를 추천해 드립니다.</p>
      <p><img src="@/assets/star.png" class="mark-image"> 토지 가격, 편의 시설, 교육 시설, 자연환경 등 다양한 요소를 종합적으로 고려하여 여러분을 위한 최고의 귀농지를 찾아드립니다.</p>
    </div>
    <div class="clustering-image-wrapper">
        <img src="@/assets/clustering.png" class="clustering-image"/>
    </div>
    <!-- <div class="main-container">
      
      <div class="content-wrapper">
        <div class="content-container">
          <img src="@\assets\location.png" class="location-icon">
          <div>귀농지 추천 설문 설명</div>
          <router-link to="/survey">
            <button type="button">시작하기</button>
          </router-link>
        </div>
        
      </div>
      
    </div> -->
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

.second-container {
  background-color: #fbfbef;
  width: 100%;
  height: 700px;
  /* display: flex; */
  padding: 50px 120px;
  /* flex-direction: column;
  justify-content: space-around; */
  /* align-items: center; */
}

.explanation-wrapper {
  margin: 50px 0;
}

.mark-image {
  width: 20px;
  height: auto;
}

.content-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.clustering-image-wrapper {
  background-color: #ffffff;
  border-radius: 15px;
  border: 2px solid #e9e9e9;
  /* width: 1200px; */
  padding: 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.clustering-image {
  max-width: 1200px;
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

.location-icon {
  width: 250px;
  height: 150px;
  margin: 0 auto;
}

.calculator-icon,
.checklist-icon {
  width: 150px;
  height: 150px;
  margin: 0 auto;
}

a {
  text-decoration: none;
  color: inherit;
}
</style>