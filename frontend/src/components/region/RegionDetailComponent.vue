<template>
  <div class="container">
    <!-- <h1>지역 정보</h1> -->
    <div class="region-container">
      <region-search-component class="search-component" />
      <div class="content-container">
        <div class="region-details">
          <div>
            <h2>{{ regionDetail.province }}</h2>
            <h1>{{ regionDetail.region_name }}</h1>
            <img :src="regionDetail.image_url" alt="test" class="region-img" />
          </div>
        </div>

        <div class="info-policy-container">
          <div class="buttons-container">
            <button
              class="button-choose"
              @click="showInfo"
              :class="{ active: activeComponent === 'info' }"
            >
              지역정보
            </button>

            <button
              class="button-choose"
              @click="showChart"
              :class="{ active: activeComponent === 'chart' }"
            >
              지역작물
            </button>

            <button
              class="button-choose"
              @click="showPolicy"
              :class="{ active: activeComponent === 'policy' }"
            >
              지역정책
            </button>
            <button
              class="button-choose"
              @click="showMap"
              :class="{ active: activeComponent === 'map' }"
            >
              지역지도
            </button>
          </div>
          <div class="info-container">
            <region-info-component
              :regionDetail="regionDetail"
              v-if="activeComponent === 'info'"
            />
            <region-chart-component v-if="activeComponent === 'chart'" />
            <region-policy-component
              :regionDetail="regionDetail"
              v-if="activeComponent === 'policy'"
            />
            <region-map-component
              :regionDetail="regionDetail"
              v-if="activeComponent === 'map'"
              class="map-component"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import RegionInfoComponent from "@/components/region/RegionInfoComponent.vue";
import RegionPolicyComponent from "@/components/region/RegionPolicyComponent.vue";
import RegionSearchComponent from "@/components/region/RegionSearchComponent.vue";
import RegionMapComponent from "@/components/region/RegionMapComponent.vue";
import RegionChartComponent from "@/components/region/RegionChartComponent.vue";
import { ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useRegionStore } from "@/stores/regionStore";

const route = useRoute();
const regionStore = useRegionStore();
const regionDetail = ref({});

const activeComponent = ref("info");

const fetchRegionDetail = async (id) => {
  await regionStore.getRegionDetail(id);
  regionDetail.value = regionStore.regionDetail;
};

// 컴포넌트가 마운트될 때 첫 번째 지역 정보를 가져옵니다.
onMounted(() => {
  window.scrollTo(0, 0);
  fetchRegionDetail(route.params.id);
});

// route.params.id가 변경될 때마다 지역 정보를 가져옵니다.
watch(
  () => route.params.id,
  (newId) => {
    fetchRegionDetail(newId);
  }
);

const showInfo = () => {
  activeComponent.value = "info";
};

const showPolicy = () => {
  activeComponent.value = "policy";
};

const showMap = () => {
  activeComponent.value = "map";
};

const showChart = () => {
  activeComponent.value = "chart";
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

.container h1,
h2 {
  text-align: center;
}

.search-component {
  margin-bottom: 20px;
}

.content-container {
  display: flex;
  flex-direction: row;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  /* border: 3px solid #ECF6EC; */
  border-radius: 1rem;
  margin-bottom: 20px;
  padding: 50px;
}

.region-details {
  display: grid;
  grid-template-columns: 300px auto;
  grid-gap: 20px;
  /* margin-left: 50px; */
  /* border: solid 2px #e9e9e9; */
  border-radius: 15px;
  display: flex;
  justify-content: center;
  /* padding: 20px; */
}

.info-policy-container {
  flex: 2;
  display: flex;
  flex-direction: column;
  padding: 20px 0 0 40px;
}

.buttons-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 10px;
  margin-bottom: 30px;
}

.info-container {
  border: solid 5px #ecf6ec;
  border-radius: 15px;
  padding: 5px;
}

.region-img {
  width: 300px;
  height: 390px;
  object-fit: cover;
  max-width: 100%;
  border-radius: 15px;
}

.region-details h1 {
  /* text-decoration: underline;
	text-decoration-color: rgba(233, 255, 185, 0.5);
	text-decoration-thickness: 40px;
	text-underline-offset: -30px; */
}

.button-choose {
  border: none;
  /* border-radius: 1rem; */
  /* width: 120px;
  height: 50px; */
  background-color: white;
  color: black;
  outline: none;
  font-size: 2rem;
  padding: 0px;
  margin: 0 5px;
  cursor: pointer;
}

.buttons-container button:hover {
  /* background-color: #C6EB74; */
  border-bottom: solid 5px #d2e6d2;
}

.active {
  color: black;
  /* background-color: #C6EB74; */
  border-bottom: solid 5px #d2e6d2;
}

.map-component {
  margin-right: 20px;
}
</style>
