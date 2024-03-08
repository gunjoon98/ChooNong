<template>
  
  <div class="steppy">
    
    <div class="progress-bar-container">
      <div class="progress-bar" :style="{ width: progressWidth + '%' }"></div>
    </div>

    <div class="steps">
      <!-- 설문의 인덱스만 가로로 표시 -->
      <div v-for="(step, index) in steps" :key="index" class="" @click="setStep(index+1)">
        
        {{ index + 1 }}
      
      </div>
    </div>
    <div class="content">
      <!-- 현재 단계에 해당하는 설문 제목, 내용, 옵션만 표시 -->
      <div>
        <div class="step-title">{{ steps[currentStep-1].title }}</div>
        <div class="step-content">{{ steps[currentStep-1].content }}</div>
        <div v-for="(option, oIndex) in steps[currentStep-1].options" :key="oIndex" class="option" :class="{selected: selectedAnswers[currentStep - 1] === oIndex}" @click="selectAnswer(currentStep - 1, oIndex)">
          {{ option }}
        </div>
      </div>
    </div>
    <!-- 이전 버튼 -->
    <button @click="prevStep" :disabled="currentStep === 1">이전</button>    
    <!-- 다음 버튼: 마지막 설문이 아니거나, 마지막 설문이지만 모든 답변이 선택되지 않았을 경우 -->
    <button @click="goToNextStep" v-if="currentStep < steps.length || !allAnswersSelected">다음</button>
    <!-- 결과보기 버튼: 마지막 설문이고, 모든 답변이 선택되었을 경우 -->
    <button @click="showResults" v-if="canShowResults">결과보기</button>
    <!-- 다시 설문하기 버튼 -->
    <button @click="resetSurvey" v-if="canShowResults">다시하기</button>    
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const steps = ref([
  { title: '질문 1', content: '질문 문항1', options: ['답변 1', '답변 2', '답변 3', '답변 4'] },
  { title: '질문 2', content: '질문 문항2', options: ['답변 1', '답변 2', '답변 3', '답변 4'] },
  { title: '질문 3', content: '질문 문항3', options: ['답변 1', '답변 2', '답변 3', '답변 4'] },
  { title: '질문 4', content: '질문 문항4', options: ['답변 1', '답변 2', '답변 3', '답변 4'] },
  // 나머지 질문들...
]);

const currentStep = ref(1);
const selectedAnswers = ref(Array(steps.value.length).fill(null));

const selectAnswer = (questionIndex, answerIndex) => {
  selectedAnswers.value[questionIndex] = selectedAnswers.value[questionIndex] === answerIndex ? null : answerIndex;
  console.log(selectedAnswers.value)
};

const goToNextStep = () => {
  if (selectedAnswers.value[currentStep.value - 1] === null) {
    alert('답변을 선택해 주세요.');
  } else if (currentStep.value < steps.value.length) {
    currentStep.value++;
  }
};

const prevStep = () => {
  if (currentStep.value > 1) currentStep.value--;
};

const setStep = (step) => {
  currentStep.value = step;
};

const allAnswersSelected = computed(() => {
  return selectedAnswers.value.every(answer => answer !== null);
});

const canShowResults = computed(() => {
  return currentStep.value === steps.value.length && allAnswersSelected.value;
});

// 결과를 보여주는 함수를 추가할 수 있습니다.
const showResults = () => {
  // 결과를 처리하고 보여주는 로직을 여기에 추가합니다.
  alert('결과를 보여줍니다.');
};

// 설문을 초기 상태로 리셋하는 함수
const resetSurvey = () => {
  currentStep.value = 1;
  selectedAnswers.value.fill(null);
};

// 프로그레스바의 너비를 계산하는 computed 속성
const progressWidth = computed(() => {
  const answeredCount = selectedAnswers.value.filter(answer => answer !== null).length;
  return (answeredCount / steps.value.length) * 100;
});
</script>

<style>
.test {
  margin: 0 10px;
  padding: 5px;
  cursor: pointer;
}

.steppy {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.steps, .content {
  display: flex;
  flex-direction: column;
}

.step, .option {
  padding: 10px;
  cursor: pointer;
}

.step-number {
  font-weight: bold;
}

.step.active, .option.selected {
  color: red;
  background-color: lightblue; /* 선택된 답변의 배경색 */
}

.option {
  margin: 5px;
  background-color: #f0f0f0; /* 기본 배경색 */
  border: 1px solid #d0d0d0;
}

.option:hover {
  background-color: #e0e0e0; /* 마우스 호버 시 배경색 */
}

.progress-bar-container {
  width: 100%;
  background-color: #e0e0e0;
  border-radius: 5px;
  margin: 20px 0;
}

.progress-bar {
  height: 20px;
  background-color: green;
  border-radius: 5px;
  transition: width 0.3s ease-in-out;
}
</style>