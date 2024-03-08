<template>
  <div>
    <div v-for="regionDetail in regionStore.dummyRegion">
      <h3>지역명: {{ regionDetail.region_name }}</h3>
      <img src="" alt="test">
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useRegionStore } from '@/stores/regionStore';

const regionStore = useRegionStore();
const regionName = ref('')
const regionURL = ref('')


</script>

<style scoped>
  
</style>