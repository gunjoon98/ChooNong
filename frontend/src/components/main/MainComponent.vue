<template>
  <div>
    <div class="main-container">
      <div class="content-container">
        <MainComponent1 />
      </div>
      <div class="content-container">
        <MainComponent2 />
      </div>
      <div class="content-container">
        <MainComponent3 />
      </div>
      <div class="content-container">
        <MainComponent4 />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import MainComponent1 from '../main/MainComponent1.vue';
import MainComponent2 from '../main/MainComponent2.vue';
import MainComponent3 from '../main/MainComponent3.vue';
import MainComponent4 from '../main/MainComponent4.vue';

onMounted(() => {
  window.scrollTo(0, 0);
})
</script>

<style scoped>
.main-container {
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

.content-container {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
}
</style>