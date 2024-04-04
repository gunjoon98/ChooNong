<template>
  <div class="first-container">
    <img src="@\assets\location.png" class="location-icon">
    <h2>간단한 설문을 하면 적합한 귀농지를 추천해 드려요!</h2>
    <router-link to="/survey">
      <button type="button">시작하기</button>
    </router-link>
  </div>
</template>

<script setup>
</script>

<style scoped>
.first-container {
  background-color: #ffffff;
  width: 100%;
  height: 600px;
  display: flex;
  padding: 35px;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
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
  text-align: center;
  line-height: 40px;
}

.location-icon {
  width: 500px;
  height: auto;
  margin: 0 auto;
  display: block;
}

a {
  text-decoration: none;
  color: inherit;
}
</style>
