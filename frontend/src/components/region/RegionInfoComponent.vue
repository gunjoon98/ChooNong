<template>
  <div>
    <h3>info</h3>
    <div v-for="regionDetail in regionStore.dummyRegion">
    <a href="">지역 URL 링크</a>

    <p>세대수 : {{ regionDetail.household }}</p>
    <p>귀농인 : {{ regionDetail.returners }}</p>
    <p>행정면적 : {{ regionDetail.area }}</p>
    </div>

    <div>
      <h3>지역 작물</h3>

      <div>
      <region-chart-component/>
      </div>
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
  

</style>