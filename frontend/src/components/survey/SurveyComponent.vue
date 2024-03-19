<template>
  <div class="survey-header">
    <h1 class="survey-title">설문 페이지</h1>
    <button class="reset-button" @click="resetSurvey" v-if="surveyStore.allAnswersSelected">다시하기</button>
  </div>
  <div class="steppy">
    <div class="progress-bar-container">
      <div class="progress-bar" :style="{ width: progressWidth + '%' }"></div>
    </div>
    <div class="steps">
      <div v-for="(step, index) in surveyStore.steps" :key="index" class="step"
        :class="{ unanswered: surveyStore.isUnanswered(index), active: currentStep === index + 1 }"
        @click="setStep(index + 1)">
        <h3>{{ index + 1 }}</h3>
      </div>
    </div>
    <div class="content">
      <div class="step-title">{{ surveyStore.steps[currentStep - 1].title }}</div>
      <br>
      <div class="step-content">{{ surveyStore.steps[currentStep - 1].content }}</div>
      <br>
      <br>
      <div class="options-container">
        <div v-for="(option, oIndex) in surveyStore.steps[currentStep - 1].options" :key="oIndex" class="option"
          :class="{ selected: surveyStore.isAnswerSelected(currentStep - 1, oIndex) }"
          @click="surveyStore.selectAnswer(currentStep - 1, oIndex)">
          {{ option }}
        </div>
      </div>
    </div>
    <div class="buttons-container">
      <button class="prev-button" @click="prevStep" :disabled="currentStep === 1">이전</button>
      <button class="next-button" @click="goToNextStep" v-if="currentStep < surveyStore.steps.length">다음</button>
      <button @click="showResults" v-if="surveyStore.allAnswersSelected">결과보기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useSurveyStore } from '@/stores/surveyStore';

const surveyStore = useSurveyStore();
const router = useRouter();
const currentStep = ref(1);

const progressWidth = computed(() => {
  const answeredCount = surveyStore.selectedAnswers.filter(answer => answer !== null).length;
  return (answeredCount / surveyStore.steps.length) * 100;
});

const resetSurvey = function() {
  currentStep.value = 1;
  surveyStore.resetAnswers();
};

const showResults = function() {
  router.push({ name: 'surveyResult' });
  alert('결과 확인.');

};

const prevStep = function() {
  if (currentStep.value > 1) currentStep.value--;
};

const goToNextStep = function() {
  if (currentStep.value < surveyStore.steps.length) {
    currentStep.value++;
  }
};

const setStep = function(step) {
  currentStep.value = step;
};
</script>

<style scoped>
.survey-container {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding: 20px;
  background: #fff;
  border: solid 3px #ECF6EC;
  border-radius: 1rem;
  margin: 20px auto;
  max-width: 600px;
}

.survey-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 0 20px;
  margin-bottom: 40px;
}

.survey-title {
  margin: 0;
}

.reset-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background: #4BAF47;
  color: white;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
}

.steppy {
  display: flex;
  flex-direction: column;
  align-items: center;
  background: #fff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
  padding: 20px;
}

.steps {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
  width: 100%;
  padding-bottom: 20px;
}

.step {
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 40px;
  height: 40px;
  margin-right: 10px;
  border-radius: 50%;
  background: #e0e0e0;
  color: #333;
  font-weight: bold;
  font-size: 1.2em;
  transition: all 0.3s ease;
}

.step.unanswered {
  opacity: 0.5;
}

.step:hover {
  background: #367c2b;
  color: white;
}

.step.active {
  background: #4BAF47;
  color: white;
  transform: scale(1.1);
}

.content {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
}

.step-title {
  align-self: flex-start;
  font-size: 2em;
  color: #333;
}

.step-content {
  align-self: flex-start;
  font-size: 1.5em;
}

.option {
  padding: 10px;
  margin: 5px;
  background: #f0f0f0;
  border: 1px solid #d0d0d0;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  display: grid;
  place-items: center;
}

.options-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
  width: 100%;
}

.option:hover {
  background-color: #ECF6EC;
}

.option.selected {
  background-color: #4BAF47;
  color: black;
}

button {
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  background: #4BAF47;
  color: white;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
}

.buttons-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-top: 20px;
}

.buttons-container button:hover {
  background: #367c2b;
}

.buttons-container button:disabled {
  background: #cccccc;
  cursor: not-allowed;
}

.progress-bar-container {
  width: 100%;
  background-color: #ECF6EC;
  border-radius: 5px;
  margin: 20px 0;
}

.progress-bar {
  height: 20px;
  background-color: #4BAF47;
  border-radius: 5px;
  transition: width 0.3s ease-in-out;
}
</style>