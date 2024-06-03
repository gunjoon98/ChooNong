<template>
  <!-- 필요에 따라 추가 카테고리 옵션 
      MT1 대형마트
CS2 편의점
PS3 어린이집, 유치원
SC4 학교
AC5 학원
PK6 주차장
OL7 주유소, 충전소
SW8 지하철역
BK9 은행
CT1 문화시설
AG2 중개업소
PO3 공공기관
AT4 관광명소
AD5 숙박
FD6 음식점
CE7 카페
HP8 병원
PM9 약국
      -->
  <h2 class="map-title">편의 시설의 위치를 확인해보세요.</h2>
  <div class="map-container">
    <div class="map" id="map"></div>
    <div class="category-buttons">
      <div class="main-category-buttons">
        <button v-for="category in categories" :key="category.value"
          :class="{ 'selected-category': selectedCategory === category.value }" @click="selectCategory(category.value)">
          <div v-if="category.name === '교육시설'">
            {{ category.name }}
            <!-- <span class="tooltiptext">초,중,고,대학교</span> -->
          </div>
          <div v-else-if="category.name === '보육시설'">
            {{ category.name }}
            <!-- <span class="tooltiptext">어린이집, 유치원</span> -->
          </div>
          <div v-else>
            {{ category.name }}
          </div>
        </button>
      </div>

      <div class="sub-category-buttons">
        <div v-if="selectedCategory === 'HP8' && showSubCategory === true" class="sub-catecory-list">
          <button @click="selectCategory(selectedCategory)">전체</button>
          <button @click="selectedSubCategory = '소아과'">소아과</button>
          <button @click="selectedSubCategory = '내과'">내과</button>
          <button @click="selectedSubCategory = '동물병원'">동물병원</button>
        </div>
        <div v-if="selectedCategory === 'SC4' && showSubCategory === true" class="sub-catecory-list">
          <button @click="selectCategory(selectedCategory)">전체</button>
          <button @click="selectedSubCategory = '초등학교'">초등학교</button>
          <button @click="selectedSubCategory = '중학교'">중학교</button>
          <button @click="selectedSubCategory = '고등학교'">고등학교</button>
        </div>
        <div v-if="selectedCategory === 'PS3' && showSubCategory === true" class="sub-catecory-list">
          <button @click="selectCategory(selectedCategory)">전체</button>
          <button @click="selectedSubCategory = '어린이집'">어린이집</button>
          <button @click="selectedSubCategory = '유치원'">유치원</button>
        </div>
        <!-- <select v-model="selectedSubCategory" v-if="selectedCategory === 'HP8'">
          <option value="소아과">소아과</option>
          <option value="내과">내과</option>
          <option value="동물병원">동물병원</option>
        </select> -->
        <!-- <select v-model="selectedSubCategory" v-if="selectedCategory === 'SC4'">
          <option value="초등학교">초등학교</option>
          <option value="중학교">중학교</option>
          <option value="고등학교">고등학교</option>
        </select>
        <select v-model="selectedSubCategory" v-if="selectedCategory === 'PS3'">
          <option value="어린이집">어린이집</option>
          <option value="유치원">유치원</option>
        </select> -->
      </div>

    </div>

  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRegionStore } from '@/stores/regionStore';

import hospitalMarkerUrl from '@/assets/hospital.png';
import schoolMarkerUrl from '@/assets/school.png';
import martMarkerUrl from '@/assets/market.png';
import kindergartenMarkerUrl from '@/assets/kindergarten.png';

const regionStore = useRegionStore()
const props = defineProps({
  regionDetail: Object
});

let map = null;
let infowindow = null;
let ps = null;
const selectedCategory = ref('');
const markers = ref([]);
const categories = [
  { name: '병원', value: 'HP8', imageUrl: hospitalMarkerUrl },
  { name: '교육시설', value: 'SC4', imageUrl: schoolMarkerUrl },
  { name: '대형마트', value: 'MT1', imageUrl: martMarkerUrl },
  { name: '보육시설', value: 'PS3', imageUrl: kindergartenMarkerUrl },
];

const selectedSubCategory = ref('');
const showSubCategory = ref();

// 지도 로드
const loadKakaoMapsAPI = function () {
  if (!window.kakao || !window.kakao.maps) {
    const script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=;
    script.addEventListener('load', function () {
      kakao.maps.load(initMap);
    });
    document.head.appendChild(script);
  } else {
    kakao.maps.load(initMap);
  }
}

onMounted(function () {
  loadKakaoMapsAPI();

});

watch(() => props.regionDetail.region_name, (newVal) => {
  if (map && newVal) {
    searchRegion(newVal + '청');
  }
}, { immediate: true });

watch(selectedSubCategory, (newCategory) => {
  if (newCategory) {
    searchNearbyHospitals(newCategory);
  }
});

const searchNearbyHospitals = (category) => {
  clearMarkers(); // 기존 마커 제거
  const keyword = `${props.regionDetail.region_name} ${category}`;
  const center = map.getCenter();
  ps.keywordSearch(keyword, (data, status, pagination) => {
    if (status === kakao.maps.services.Status.OK) {
      data.forEach(place => {
        displayMarker(place);
      });
    }
  }, {
    location: new kakao.maps.LatLng(center.getLat(), center.getLng()),
    radius: 10000 // 10km 내 검색
  });
};

// 지도 초기
const initMap = function () {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(36.354946759143, 127.29980994578),
    level: 8,
  };

  map = new kakao.maps.Map(container, options);
  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  ps = new kakao.maps.services.Places();

  if (props.regionDetail.region_name) {
    searchRegion(props.regionDetail.region_name + '청');
  }
  // 지도 클릭시 인포윈도우 닫기
  kakao.maps.event.addListener(map, 'click', function () {
    infowindow.close();
  });

}

// 지역 검색
const searchRegion = (keyword) => {
  ps.keywordSearch(keyword, (data, status) => {
    if (status === kakao.maps.services.Status.OK && data.length > 0) {
      const firstPlace = data[0];
      const center = new kakao.maps.LatLng(firstPlace.y, firstPlace.x);
      map.setCenter(center);
    } else {
      console.error("검색 결과가 없습니다.");
    }
  });
};

//카테고리 선택
const selectCategory = (value) => {
  if (value === selectedCategory.value) {
    showSubCategory.value = showSubCategory.value === false ? true : false;
  } else {
    showSubCategory.value = true;
  }
  currentPage = 1
  selectedCategory.value = value;
  searchPlacesByCategory(value);
};

// 카테고리 선택 후 검색
const searchPlacesByCategory = (category) => {
  clearMarkers();
  const center = map.getCenter();
  ps.categorySearch(category, placesSearchCB, {
    location: new kakao.maps.LatLng(center.getLat(), center.getLng()),
    // 주변 15km만 검색
    radius: 10000,
    useMapBounds: true
  });
};

// 카테고리 값에 따라 해당하는 이미지 URL 찾기
const findCategoryImageUrl = (categoryValue) => {
  const category = categories.find(c => c.value === categoryValue);
  return category ? category.imageUrl : null; // 카테고리에 맞는 이미지 URL 반환, 없으면 null 반환
};

// 검색 결과를 마커 생성 지도 표기
let currentPage = 1; // 현재 페이지 번호를 추적하는 변수

const placesSearchCB = function (data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {

    data.forEach(place => {
      displayMarker(place);
    });
    if (pagination.hasNextPage) { // 다음 페이지가 있으면
      currentPage++;
      pagination.gotoPage(currentPage); // 다음 페이지로 이동하여 추가 결과 요청
    }
  }
}

// 주어진 장소에 마커 생성 후 이벤트 리스너 생성
const displayMarker = function (place) {

  const category = categories.find(c => c.value === selectedCategory.value);
  const imageUrl = category ? category.imageUrl : null;
  let markerImage = null;

  if (imageUrl) {
    const imageSrc = imageUrl; // 마커 이미지 주소
    const imageSize = new kakao.maps.Size(51, 51); // 마커 이미지의 크기
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
  }

  const marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(place.y, place.x),
    image: markerImage // 마커 이미지 설정
  });

  kakao.maps.event.addListener(marker, 'click', function () {
    infowindow.setContent('<div class="infowindow-content" style="padding:5px;font-size:17px;">' + place.place_name + '</div>');
    infowindow.open(map, marker);
  });

  markers.value.push(marker);
  return marker;
};

// 모든 마커 제거
const clearMarkers = function () {
  markers.value.forEach(function (marker) {
    marker.setMap(null);
  });
  markers.value = [];

}
</script>

<style scoped>
.map-container {
  position: relative;
  width: 100%;
  height: 500px;
  margin-right: 20px;
}

.map-title {
  text-align: center;
}

.map {
  width: 100%;
  height: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.category-buttons {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 2;
  display: flex;
  flex-direction: row;
}

.main-category-buttons {
  display: flex;
  flex-direction: column;
}

.category-buttons button {
  /* border: none; */
  border: solid 1px #ECF6EC;
  /* border-bottom: solid 1px #ECF6EC; */
  border-radius: 5px;
  /* box-shadow: 0 2px 4px rgba(0,0,0,0.05); */
  width: 75px;
  height: 40px;
  background-color: white;
  color: black;
  outline: none;
  font-weight: bold;
  font-size: 0.7em;
  padding: 0px;
  margin-top: 0px;
  cursor: pointer;
}

.category-buttons .selected-category {
  border-right: none;
  border: solid 1px #ECF6EC;
  /* border-right: solid 1px #ECF6EC; */
  border-radius: 5px;
  /* box-shadow: 0 2px 4px rgba(0,0,0,0.05); */
  width: 75px;
  height: 40px;
  background-color: #C6EB74;
  color: black;
  outline: none;
  font-weight: bold;
  font-size: 0.7em;
  padding: 0px;
  margin-top: 0px;
  cursor: pointer;
}

.sub-category-buttons {
  margin-left: 5px;
  display: flex;
  flex-direction: column;
  justify-content: right;
}

.sub-catecory-list {
  /* height: 100%; */
  border: solid 2px #C6EB74;
  background-color: #ffffff;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
}

infowindow {
  width: 300px;
  height: 200px;
  /* max-width: 300px;
  max-height: 10000px; */
}

.tooltip {
  position: relative;
  display: inline-block;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 100px;
  background-color: white;
  color: black;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;

  /* 위치 조정 */
  position: absolute;
  z-index: 2;
  top: 100%;
  left: 50%;
  margin-left: -60px;
  margin-top: 10px;
  opacity: 0;
  transition: opacity 0.3s;

}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}
</style>
