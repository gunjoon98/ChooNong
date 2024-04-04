<template>
  <div class="fourth-container">
    <h2>그러면, 설문을 시작해 볼까요?</h2>
    <router-link to="/survey">
      <button type="button">시작하기</button>
    </router-link>
  </div>
</template>

<script setup>
</script>

<style scoped>
.fourth-container {
  background-color: #d8f7d0;
  width: 100%;
  height: 500px;
  display: flex;
  padding: 35px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.fourth-container > * {
  margin: 20px;
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

a {
  text-decoration: none;
  color: inherit;
}
</style>
