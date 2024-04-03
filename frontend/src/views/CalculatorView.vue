<template>
  <div class="router-container">
    <RouterView class="router-view" />
    <img src="../assets/chicken.png" class="bottom-picture" />
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import { onMounted } from "vue";

onMounted(() => {
  // 페이지를 맨 위로 스크롤하는 로직
  window.scrollTo(0, 0);
});
</script>

<style scoped>
.router-container {
  position: relative;
}

.router-view {
  margin-bottom: 100px;
}

.bottom-picture {
  max-width: 120%;
  height: 100px;
  position: absolute;
  bottom: -2px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
