<template>
  <div>
    <RegionSearchComponents/>
  </div>

  <div>
    <RegionNameComponent/>

    <!-- '지역정보' 버튼: 클릭 시-->
    <button @click="showInfo" :class="{ active: activeComponent === 'info' }">지역정보</button>
    <!-- '지역정책' 버튼: 클릭 시-->
    <button @click="showPolicy" :class="{ active: activeComponent === 'policy' }">지역정책</button>

    <!-- 컴포넌트 조건부 렌더링 -->
    <RegionInfoComponent v-if="activeComponent === 'info'"/>
    <RegionPolicyComponent v-if="activeComponent === 'policy'"/>
  </div>
</template>

<script setup>
import RegionInfoComponent from '@/components/region/RegionInfoComponent.vue';
import RegionNameComponent from '@/components/region/RegionNameComponent.vue';
import RegionPolicyComponent from '@/components/region/RegionPolicyComponent.vue';
import RegionSearchComponent from '@/components/region/RegionSearchComponent.vue';

import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useRegionStore } from '@/stores/regionStore';

const route = useRoute();
const regionStore = useRegionStore();

onMounted(async () => {
  await regionStore.getRegion(route.params.regionId);
});

const activeComponent = ref('info');

const showInfo = () => {
  activeComponent.value = 'info';
};

const showPolicy = () => {
  activeComponent.value = 'policy';
};
</script>

<style scoped>
  .active {
    color: white;
    background-color: blue;
  }
</style>
