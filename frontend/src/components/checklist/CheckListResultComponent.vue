<template>
   <div>
      <h1>체크리스트 결과</h1>
    </div>
  <div class="container">
   
    <div class="checkchart">
      <CheckListChartComponent />
    </div>
  </div>
</template>

<script setup>
import CheckListChartComponent from '@/components/checklist/CheckListChartComponent.vue'
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  /* align-items: center; */
  /* 화면의 전체 높이 */
  /* height: 100vh;  */
  
}

.checkchart {
  height: 400px;
  width: 400px;
  /* 추가할 필요가 있는 스타일이 있다면 여기에 추가하세요 */
}
</style>
