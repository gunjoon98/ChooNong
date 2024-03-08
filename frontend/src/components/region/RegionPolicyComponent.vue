<template>
  <div>
    <h3>policy</h3>
    <div v-for="detail in regionStore.dummyPolicy" :key="detail.id">
      <p>{{ detail.business_name }}</p>
      <button @click="toggleDetail(detail.id)">
        {{ detailsVisible[detail.id] ? '닫기' : '상세보기' }}
      </button>
      <p v-if="detailsVisible[detail.id]">지원대상 및 자격조건: {{ detail.target_eligibility }}</p>
      <p v-if="detailsVisible[detail.id]">사업내용: {{ detail.business_content }}</p>
      <p v-if="detailsVisible[detail.id]">지원조건: {{ detail.conditions }}</p>
      <p v-if="detailsVisible[detail.id]">증빙서류: {{ detail.evidence }}</p>
      <p v-if="detailsVisible[detail.id]">접수 및 문의처: {{ detail.reception }}</p>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRegionStore } from '@/stores/regionStore';

const regionStore = useRegionStore()

const detailsVisible = reactive({});

// // 상세보기 버튼 누르면 2개 이상도 볼 수 있게
// const toggleDetail = (id) => {
//   if (detailsVisible[id] === undefined) {
//     detailsVisible[id] = true;
//   } else {
//     detailsVisible[id] = !detailsVisible[id];
//   }
// }

// 상세보기 하나만 가능하게
const toggleDetail = function(id) {
  const isVisible = detailsVisible[id];

  // 모든 detailsVisible의 항목을 false로 설정하여 상세 정보를 닫습니다.
  Object.keys(detailsVisible).forEach(key => {
    detailsVisible[key] = false;
  });

  // 현재 선택한 항목이 이미 열려있었다면, 닫히도록 합니다.
  // 그렇지 않으면 선택한 항목의 상세 정보를 엽니다.
  if (!isVisible) {
    detailsVisible[id] = true;
  }
}


</script>

<style scoped>
  
</style>
