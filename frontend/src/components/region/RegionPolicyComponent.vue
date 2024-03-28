<template>
  <div class="policy-container">
    <!-- 정책 리스트가 있을 때 -->
    <div v-if="regionDetail.policy_list && regionDetail.policy_list.length > 0">
      <div v-for="detail in regionDetail.policy_list" :key="detail.policy_id" class="policy-item">
        <div class="policy-header">
          <div class="policy-title">
            <p>{{ detail.business_name }}</p>
          </div>
          <div class="policy-button">
            <button @click="toggleDetail(detail.policy_id)">
              {{ detailsVisible[detail.policy_id] ? '닫기' : '상세보기' }}
            </button>
          </div>
        </div>
        <transition name="fade">
          <table v-if="detailsVisible[detail.policy_id]" class="detail-content">
            <tbody>
              <tr>
                <th>지원대상 및 자격조건</th>
                <td>{{ detail.target_eligibility ? detail.target_eligibility : '-' }}</td>
              </tr>
              <tr>
                <th>사업내용</th>
                <td>{{ detail.business_content ? detail.business_content : '-' }}</td>
              </tr>
              <tr>
                <th>지원조건</th>
                <td>{{ detail.conditions ? detail.conditions : '-' }}</td>
              </tr>
              <tr>
                <th>증빙서류</th>
                <td>{{ detail.evidence ? detail.evidence : '-' }}</td>
              </tr>
              <tr>
                <th>접수 및 문의처</th>
                <td>{{ detail.reception ? detail.reception : '-' }}</td>
              </tr>
            </tbody>
          </table>
        </transition>
      </div>
    </div>
    <!-- 정책 리스트가 비어 있을 때 -->
    <div v-else class="no-policy">
      <p>이 지역에 대한 정책 정보가 없습니다.</p>
    </div>
  </div>
</template>


<script setup>
import { reactive } from 'vue'
import { useRegionStore } from '@/stores/regionStore';


const regionStore = useRegionStore()
const props = defineProps({
  regionDetail: Object
});

const detailsVisible = reactive({});


// 상세보기 하나만 가능하게
const toggleDetail = (policy_id) => {
  if (detailsVisible[policy_id] === undefined) {
    // 최초 클릭 시 해당 항목에 대한 키를 생성하고 true로 설정
    detailsVisible[policy_id] = true;
  } else {
    // 이미 존재하는 항목은 토글
    detailsVisible[policy_id] = !detailsVisible[policy_id];
  }
};
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
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  margin-right: 10px;
  margin-bottom: 5px;
}

.policy-title p {
  margin-left: 10px;
  font-weight: bold;
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
