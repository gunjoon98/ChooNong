<template>
  <div class="info-chart-container">
    <div class="region-info">
      <h3>info</h3>
      <div v-for="regionDetail in regionStore.dummyRegion" :key="regionDetail.region_id" class="region-info-detail">
        <a :href="regionDetail.homepage_url">지역 URL 링크</a>

        <p>세대수 : {{ regionDetail.household }}</p>
        <p>귀농인 : {{ regionDetail.returners }}</p>
        <p>행정면적 : {{ regionDetail.area }}</p>
      </div>
    </div>

    <div class="region-chart">
      <h3>지역 작물</h3>
      <region-chart-component/>
    </div>
  </div>
</template>

<script setup>
import RegionChartComponent from '@/components/region/RegionChartComponent.vue';

import { reactive, ref } from 'vue'
import { useCropStore } from '@/stores/cropStore';
import { useRegionStore } from '@/stores/regionStore';

const regionStore = useRegionStore();
const cropStore = useCropStore()
const regionName = ref('')
const regionURL = ref('')

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
  background: #ECF6EC;
}

.region-chart {
  margin-right: 20px; 
  margin: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  background: #ECF6EC;
}

.region-info-detail {
  margin-left: 10px;
}

</style>