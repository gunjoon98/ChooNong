<template>
  <div>
    <div>
      <h1>체크리스트 결과</h1>
    </div>
    <div class="container">
      <div class="checkchart">
        <CheckListChartComponent />
      </div>
    </div>

    <div>
      <!-- { "주거": 100, "농지": 100, "교육": 100, "자금": 100, "생활": 100 } -->
      <p>{{ checkStore.categoryWeights }}</p>
    </div>

  </div>
</template>

<script setup>
import { useCheckStore } from '@/stores/checkStore';
import CheckListChartComponent from '@/components/checklist/CheckListChartComponent.vue'

const checkStore = useCheckStore()
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
}

.checkchart {
  height: 400px;
  width: 400px;
}
</style>
