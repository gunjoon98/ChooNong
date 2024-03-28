<template>
  <div class="container">
    <!-- <h1>지역 정보</h1> -->
    <div class="region-container">
      <region-search-component class="search-component" />
      <div class="content-container">
        <div class="region-details">
          <div>
            <h2>{{regionDetail.province}} </h2>
            <h1>{{ regionDetail.region_name }}</h1>
            <img :src="regionDetail.image_url" alt="test" class="region-img">
          </div>
        </div>
        <div class="info-policy-container">
          <div class="buttons-container">
            <button class="button-choose" @click="showInfo"
              :class="{ active: activeComponent === 'info' }">지역정보</button>
            <button class="button-choose" @click="showPolicy"
              :class="{ active: activeComponent === 'policy' }">지역정책</button>
            <button class="button-choose" @click="showMap" :class="{ active: activeComponent === 'map' }">지역지도</button>
          </div>
          <region-info-component :regionDetail="regionDetail" v-if="activeComponent === 'info'" />
          <region-policy-component :regionDetail="regionDetail" v-if="activeComponent === 'policy'" />
          <region-map-component :regionDetail="regionDetail" v-if="activeComponent === 'map'" />
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import RegionInfoComponent from '@/components/region/RegionInfoComponent.vue';
import RegionPolicyComponent from '@/components/region/RegionPolicyComponent.vue';
import RegionSearchComponent from '@/components/region/RegionSearchComponent.vue';
import RegionMapComponent from '@/components/region/RegionMapComponent.vue'
import { ref, watch, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useRegionStore } from '@/stores/regionStore';

const route = useRoute();
const regionStore = useRegionStore();
const regionDetail = ref({});

const activeComponent = ref('info');

const fetchRegionDetail = async (id) => {
  await regionStore.getRegionDetail(id);
  regionDetail.value = regionStore.regionDetail;
};

// 컴포넌트가 마운트될 때 첫 번째 지역 정보를 가져옵니다.
onMounted(() => { 
  fetchRegionDetail(route.params.id);
});

// route.params.id가 변경될 때마다 지역 정보를 가져옵니다.
watch(() => route.params.id, (newId) => {
  fetchRegionDetail(newId);
});

const showInfo = () => {
  activeComponent.value = 'info';
};

const showPolicy = () => {
  activeComponent.value = 'policy';
};

const showMap = () => {
  activeComponent.value = 'map';
};
</script>

<style scoped>
.region-container {
  margin-bottom: 100px;
}

.container {
  display: flex;
  flex-direction: column;
  position: relative;
}

.container h1 {
  margin-top: 0px;
}

.container h2 {
  margin-bottom: 0px;
  margin-top: 20px;
}

.container h1, h2{
  text-align: center;
}

.search-component {
  margin-bottom: 20px;
}

.content-container {
  display: flex;
  flex-direction: row;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  margin-bottom: 20px;
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
