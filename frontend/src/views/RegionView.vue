<template>
  <div class="container">
    <h1>지역 정보</h1>
    <region-search-component class="search-component" />
    <div class="content-container">
      <div class="region-details">
        <div v-for="regionDetail in regionStore.dummyRegion" :key="regionDetail.region_id">
          <h3>지역명: {{ regionDetail.region_name }}</h3>
          <a :href="regionDetail.homepage_url">지역링크</a>
          <img src="C:\Users\SSAFY\Desktop\S10P22B210\frontend\src\components\region\ssafy.jpg" alt="test"
            class="region-img">
        </div>
      </div>
      <div class="info-policy-container">
        <div class="buttons-container">
          <button class="button-choose" @click="showInfo" :class="{ active: activeComponent === 'info' }">지역정보</button>
          <button class="button-choose" @click="showPolicy"
            :class="{ active: activeComponent === 'policy' }">지역정책</button>
        </div>
        <region-info-component v-if="activeComponent === 'info'" />
        <region-policy-component v-if="activeComponent === 'policy'" />
      </div>
    </div>
  </div>
</template>

<script setup>
import RegionInfoComponent from '@/components/region/RegionInfoComponent.vue';
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

const showInfo = function () {
  activeComponent.value = 'info';
};

const showPolicy = function () {
  activeComponent.value = 'policy';
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}

.container h1 {
  text-align: center;
}

.search-component {
  margin-bottom: 20px;
}

.content-container {
  display: flex;
  flex-direction: row;
  border: 1px solid #4BAF47;
  border-radius: 1rem;
}

.region-details {
  display: grid;
  grid-template-columns: 300px auto;
  grid-gap: 20px;
  margin-left: 50px;
}

.info-policy-container {
  flex: 2;
  display: flex;
  flex-direction: column;
}

.buttons-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.region-img {
  width: 300px;
  height: auto;
  max-width: 100%;
}

.active {
  color: black;
  background-color: #ECF6EC;
}

.region-details h3 {
  margin-bottom: 0px;
}

.button-choose {
  border: 1px solid #4BAF47;
  border-radius: 1rem;
  width: 100px;
  height: 40px;
  background-color: white;
  color: black;
  outline: none;
  font-weight: bold;
  padding: 0px;
  margin-top: 0px;
}

.buttons-container button:hover {
  background-color: #ECF6EC;
}

.active {
  color: black;
  background-color: #ECF6EC;
}
</style>
