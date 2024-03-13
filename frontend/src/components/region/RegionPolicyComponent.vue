<template>
  <div class="policy-container">
    <div v-for="detail in regionStore.dummyPolicy" :key="detail.id" class="policy-item">
      <div class="policy-header">
        <div class="policy-title">
          <p>{{ detail.business_name }}</p>
        </div>
        <div class="policy-button">
          <button @click="toggleDetail(detail.id)">
            {{ detailsVisible[detail.id] ? '닫기' : '상세보기' }}
          </button>
        </div>
      </div>
      <transition name="fade">
        <table v-if="detailsVisible[detail.id]" class="detail-content">
          <tbody>
            <tr>
              <th>지원대상 및 자격조건</th>
              <td>{{ detail.target_eligibility }}</td>
            </tr>
            <tr>
              <th>사업내용</th>
              <td>{{ detail.business_content }}</td>
            </tr>
            <tr>
              <th>지원조건</th>
              <td>{{ detail.conditions }}</td>
            </tr>
            <tr>
              <th>증빙서류</th>
              <td>{{ detail.evidence }}</td>
            </tr>
            <tr>
              <th>접수 및 문의처</th>
              <td>{{ detail.reception }}</td>
            </tr>
          </tbody>
        </table>
      </transition>

    </div>
  </div>
</template>



<script setup>
import { reactive } from 'vue'
import { useRegionStore } from '@/stores/regionStore';

const regionStore = useRegionStore()

const detailsVisible = reactive({});


// 상세보기 하나만 가능하게
const toggleDetail = function (id) {
  const isVisible = detailsVisible[id];

  Object.keys(detailsVisible).forEach(key => {
    detailsVisible[key] = false;
  });

  if (!isVisible) {
    detailsVisible[id] = true;
  }
}

</script>

<style scoped>
.policy-container {
  max-height: 500px;
  overflow-y: auto;
}

.policy-item {
  margin-bottom: 10px;
}

.policy-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #4BAF47;
  border-radius: 1rem;
  margin-right: 10px;
  margin-bottom: 5px;
}

.policy-title p {
  margin-left: 10px;
}

.detail-content {
  box-sizing: border-box;
  border: 1px solid #4BAF47;
  border-radius: 1rem;
  margin-right: 20px;
  margin-bottom: 5px;
  width: 100%;
  overflow: hidden;
  padding: 5px;
}

.detail-content th {
  width: 21%;
  background-color: #f2f2f2;
  padding: 8px;
  border: 1px solid #ddd;
  text-align: left;
}

.detail-content td {
  width: 79%;
  padding: 8px;
  border: 1px solid #ddd;
  text-align: left;
}


.policy-button button {
  border: 1px solid #4BAF47;
  border-radius: 1rem;
  width: 90px;
  height: 30px;
  background-color: white;
  color: black;
  outline: none;
  margin: 10px;
  font-weight: bold;
  font-size: 13px;
  padding: 0px;
}

.policy-button button:hover {
  background-color: #ECF6EC;
  /* 마우스를 올리거나 포커스되었을 때의 배경색 */
}


/* 트렌지션 관련 css */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
