<template>
  <div class="first-container">
    <img src="@\assets\location.png" class="location-icon">
    <h2>간단한 설문을 하면 적합한 귀농지를 추천해 드려요!</h2>
    <router-link to="/survey">
      <button type="button">시작하기</button>
    </router-link>
    <!-- <div class="main-container">
      
      <div class="content-wrapper">
        <div class="content-container">
          
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

h2 {
  margin: 0;
}

.first-container {
  background-color: #ffffff;
  width: 100%;
  height: 600px;
  display: flex;
  padding: 35px;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
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

.location-icon {
  width: 500px;
  height: auto;
  margin: 0 auto;
  display: block;
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