<template>
  <div class="fourth-container">
    <h2>그러면, 설문을 시작해 볼까요?</h2>
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

.fourth-container {
  background-color: #d8f7d0;
  width: 100%;
  height: 500px;
  display: flex;
  padding: 35px;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
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
  /* flex: 1; */
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
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