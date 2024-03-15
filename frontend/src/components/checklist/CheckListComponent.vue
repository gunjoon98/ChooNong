<template>
  <div>
    <h1>체크리스트 페이지</h1>
    <div v-for="(check, cIndex) in checkStore.checkList" :key="cIndex" class="container">
      <div>{{ check.check_title }}</div>

      <div v-for="(option, oIndex) in check.options" :key="oIndex" class="option">
        <div>{{ option }}</div>
        <!-- 각 체크박스를 checkedAnswers에 바인딩합니다 -->
        <Checkbox v-model="checkedAnswers[cIndex][oIndex]" :binary="true" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useCheckStore } from '@/stores/checkStore';
import Checkbox from 'primevue/checkbox';

const checkStore = useCheckStore();

// 각 체크박스의 상태를 저장할 구조를 초기화합니다.
const checkedAnswers = ref(checkStore.checkList.map(check => 
  check.options.map(() => false)
));

</script>

<style scoped>
  .container {
    border: 1px solid black;
  }
  .option {
    margin: 5px;
  }
</style>