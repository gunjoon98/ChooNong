<template>
  <div class="third-container">
    <h2>다른 편리한 기능들도 이용해 보세요!</h2>
    <div class="explanation-wrapper">
      <p><img src="@/assets/star.png" class="mark-image"> 지역 정보 검색: 궁금한 지역의 정보를 직접 찾아볼 수 있습니다.</p>
      <p><img src="@/assets/star.png" class="mark-image"> 작물 수익 계산기: 작물과 재배 면적을 입력하면 예상 수익을 계산해 줍니다.</p>
      <p><img src="@/assets/star.png" class="mark-image"> 귀농 지식 퀴즈: 퀴즈를 풀면서 귀농 지식을 배워봅시다.</p>
    </div>
    <div class="content-wrapper">
        <div class="content-container">
          <img src="@\assets\search (2).png" class="search-icon">
          <div>지역 정보 검색</div>
          <router-link to="/region">
            <button type="button">시작하기</button>
          </router-link>
        </div>
        <div class="content-container">
          <img src="@/assets/calculator.png" class="calculator-icon">
          <div>작물 수익 계산기</div>
          <router-link to="/calculator">
            <button type="button">시작하기</button>
          </router-link>
        </div>
        <div class="content-container">
          <img src="@\assets\checklist.png" class="checklist-icon">
          <div>귀농 준비 체크리스트</div>
          <router-link to="/checklist">
            <button type="button">시작하기</button>
          </router-link>
        </div>
      </div>
    <!-- <div class="main-container"> -->
      <!-- <div class="search-container">
        <div>귀농지 검색하기</div>
        <RegionSearchComponentVue class="search-box"/> -->
        <!-- 
          <input type="search">
          <button type="button">검색</button> -->
      <!-- </div> -->
      
      
    <!-- </div> -->
  </div>
</template>

<script setup>
import RegionSearchComponentVue from '../region/RegionSearchComponent.vue';
</script>

<style scoped>
/* body {
  display: flex;
  justify-content: center;
} */

.third-container {
  background-color: #eff9ec;
  width: 100%;
  padding: 60px 100px;
  /* 가로 전체 화면 */
  /* max-width: 1500px; */
  /* 최대 너비 제한 */
  /* display: flex;
  flex-direction: column;
  align-items: center; */
  /* width: 100vw; */
  /* overflow-x: hidden; */
}

.explanation-wrapper {
  margin: 50px 0;
}

.content-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.content-container {
  background-color: #ffffff;
  border: 2px solid #e9e9e9;
  border-radius: 15px;
  /* margin: 10px; */
  padding: 10px;
  width: 350px;
  height: 300px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.mark-image {
  width: 20px;
  height: auto;
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

.search-icon,
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