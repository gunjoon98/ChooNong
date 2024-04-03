<template>
  <div class="main-component">
    <router-view></router-view>
    <img src="@\assets\scarecrow.png" class="bottom-picture" />
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
.main-component {
  position: relative;
}

.bottom-picture {
  margin-top: 100px;
  max-width: 120%;
  height: 80px;
  position: absolute;
  bottom: 0px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
