<template>
  <div class="info-chart-container">
    <div class="region-info">
      <h3>info</h3>
      <div class="region-info-detail">
        <p>세대수 : {{ regionDetail.house_hold }}</p>
        <p>귀농인 : {{ regionDetail.returners }}</p>
        <p>행정면적 : {{ regionDetail.area }}</p>
      </div>
    </div>

    <div class="region-chart">
      <h3>지역 작물</h3>
      <region-chart-component :regionDetail="regionDetail" />
    </div>
  </div>
</template>

<script setup>
import RegionChartComponent from '@/components/region/RegionChartComponent.vue';
import { defineProps } from 'vue'


const props = defineProps({
  regionDetail: Object
});
</script>

<style scoped>
.info-chart-container {
  display: flex;
  /* border: 1px solid #4BAF47;
  border-radius: 1rem; */
  margin-right: 10px;
  margin-bottom: 10px;
}

.region-info, .region-chart {
  flex: 1; 
  margin-bottom: 20px;
}

.region-info h3, .region-chart h3 {
  margin: 10px;
}

.region-info {
  margin-right: 20px; 
  margin: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  background: white;
}

.region-chart {
  margin-right: 20px; 
  margin: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  background: white;
}

.region-info-detail {
  margin-left: 10px;
}

</style>
