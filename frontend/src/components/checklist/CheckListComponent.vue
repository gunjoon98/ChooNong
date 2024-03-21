<template>
  <div>
    <!-- <h1>체크리스트 페이지</h1> -->
    <div v-if="currentQuestionIndex < checkStore.checkList.length" class="container">
      <div>{{ currentCheck.check_title }}</div>
      <div v-for="(option, oIndex) in currentCheck.options" :key="oIndex" class="option">
        <button @click="selectOption(oIndex)" :class="{ 'selected': checkedAnswers[currentQuestionIndex] === oIndex, 'option-button': true }">
          {{ option }}
        </button>
      </div>
    </div>
    <div class="button-group">
      <button v-if="currentQuestionIndex > 0" @click="prevQuestion" class="prev-button">이전</button>
      <button v-if="currentQuestionIndex < checkStore.checkList.length - 1" @click="nextQuestion" class="next-button">다음</button>
      <button v-if="allChecked && currentQuestionIndex === checkStore.checkList.length - 1" @click="goToResult" class="result-button">결과보기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useCheckStore } from '@/stores/checkStore';
import { useRouter } from 'vue-router';

const checkStore = useCheckStore();
const router = useRouter();
const checkedAnswers = ref(checkStore.checkList.map(() => null));
const currentQuestionIndex = ref(0);

const currentCheck = computed(() => {
  return checkStore.checkList[currentQuestionIndex.value];
});

const nextQuestion = () => {
  if (currentQuestionIndex.value < checkStore.checkList.length - 1) {
    currentQuestionIndex.value++;
  }
};

const prevQuestion = () => {
  if (currentQuestionIndex.value > 0) {
    currentQuestionIndex.value--;
  }
};

const selectOption = (oIndex) => {
  // 이미 선택된 답변을 다시 클릭하면 선택 취소
  if (checkedAnswers.value[currentQuestionIndex.value] === oIndex) {
    checkedAnswers.value[currentQuestionIndex.value] = null; // 선택 취소
  } else {
    checkedAnswers.value[currentQuestionIndex.value] = oIndex; // 인덱스 값으로 저장
  }
  // 배열 전체를 다시 할당하여 반응성을 유지
  checkedAnswers.value = [...checkedAnswers.value];
};

const goToResult = function () {
  const isAllChecked = checkedAnswers.value.every(answer => answer !== null);
  if (isAllChecked) {
    // 인덱스 값으로 모든 선택된 답변 로그
    console.log('모든 선택된 답변 인덱스:', checkedAnswers.value.join(', '));
    router.push('/checklist/result');
  } else {
    alert('모든 문항에 답해주세요.');
  }
};

const allChecked = computed(() => {
  return checkedAnswers.value.every(answer => answer !== null);
});

watch(checkedAnswers, (newAnswers, oldAnswers) => {
  // 모든 선택된 답변을 출력
  const selectedAnswers = newAnswers
    .map((answer, index) => answer !== null ? checkStore.checkList[index].options[answer] : '미선택')
    .join(', ');
  console.log('현재 선택된 답변:', selectedAnswers);
}, { deep: true });

</script>

<style scoped>
.container {
  border: 1px solid #4BAF47;
  border-radius: 1rem;
}

.option-button, .prev-button, .next-button, .result-button {
  padding: 10px 20px;
  margin: 5px;
  border: 1px solid #4BAF47;
  border-radius: 1rem;
  background-color: white; /* 버튼 기본 배경색 */
  cursor: pointer;
}

.option-button:hover, .prev-button:hover, .next-button:hover, .result-button:hover {
  background-color: #ECF6EC; /* 마우스 오버시 배경색 */
}

.selected {
  background-color: #4BAF47;
  color: white;
  border-color: #3a8e33;
}

.button-group button {
  margin-top: 10px;
}
</style>