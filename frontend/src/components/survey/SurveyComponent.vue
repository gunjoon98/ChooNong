<template>
  <div class="survey-container">
    <div class="progress-bar-container">
      <div class="progress-bar" :style="{ width: progress + '%' }"></div>
    </div>
    <h2 class="survey-title">간단한 설문조사</h2>
    <div v-if="currentStep <= surveyQuestions.length">
      <div v-if="currentStep < surveyQuestions.length">
        <!-- 현재 주 질문 표시 -->
        <div class="question" v-if="!isMainQuestionHidden">
          <h3>{{ currentQuestion.question }}</h3>
          <div>
            <button v-for="(option, index) in currentQuestion.options" :key="index"
              @click="handleAnswer(option, index)" class="option-button">
              {{ option }}
            </button>
          </div>
        </div>
        <!-- 현재 세부 질문들 표시 -->
        <div v-if="currentSubQuestions && currentSubQuestions.length > 0">
          <div v-for="(subQuestion, index) in currentSubQuestions" :key="`sub-${index}`" class="sub-question">
            <h4>{{ subQuestion.question }}</h4>
            <div>
              <button v-for="(option, idx) in subQuestion.options" :key="`option-${idx}`"
                @click="handleSubAnswer(subQuestion.id, idx)" class="option-button">
                {{ option }}
              </button>
            </div>
          </div>
        </div>
        <button @click="nextStep" v-if="canProceed" class="nav-button">다음</button>
      </div>
    </div>
    <div v-if="currentStep === surveyQuestions.length">
      <button @click="showResults" class="nav-button">결과 보기</button>
      <button @click="resetSurvey" class="nav-button">다시 시작하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const surveyQuestions = ref([
  {
    id: "one",
    question: "자녀가 있습니까?",
    options: ["네 있습니다.", "없습니다."],
    followUps: [
      {
        id: "one_sub_1",
        question: "자녀가 다니는 학교는 어디인가요?",
        options: ["유치원", "초등학교", "중학교"],
      },
      {
        id: "one_sub_2",
        question: "자녀의 기상 시간은?",
        options: ["등교 30분 전", "등교 1시간 전", "모름"],
      },
      {
        id: "one_sub_3",
        question: "자녀의 병원 방문 주기를 선택해주세요.",
        options: ["반 년에 1회 미만", "3개월 1회 이상", "월 1회 이상", "월 2회 이상"],
      }
    ]
  },
  { id: "two", question: "고려하는 예산을 선택해주세요. (주거, 30평 토지 100평 기준)", options: ["0번 클러스터의 최소, 최대 값", "1번 클러스터의 최소, 최대 값"] },
  { id: "three_1", question: "귀농을 고려하는데 수질을 어떻게 생각하시나요?", options: ["전혀 중요하지 않다", "약간 중요하지 않다", "보통이다", "중요한 편이다", "매우 중요하다"] },
  { id: "three_2", question: "귀농을 고려하는데 공기 청정도를 어떻게 생각하시나요?", options: ["전혀 중요하지 않다", "약간 중요하지 않다", "보통이다", "중요한 편이다", "매우 중요하다"] },
  { id: "four_1", question: "어떤 귀농 생활을 꿈꾸시나요?", options: ["한적하고 유유자적한 생활", "많은 사람들과 교류하는 생활"] },
  { id: "four_2", question: "대형 마트/시장을 얼마나 자주 방문하시나요?", options: ["월 1회 미만", "월 1회 이상", "월 2회 이상", "월 3회 이상"] },
  { id: "four_3", question: "대중 교통을 얼마나 자주 이용하시나요?", options: ["주 1회 미만", "주 1회 이상", "주 3회 이상", "주 5회 이상"] }
]);

const currentStep = ref(0);
const responses = ref({});
const currentSubQuestions = ref([]);
const isMainQuestionHidden = ref(false);
const canProceed = ref(false); // 다음 버튼 활성화 상태를 관리
const isAnsweringSubQuestions = ref(false); // 세부 문항 응답 중인지 여부를 추적
const currentSubQuestionIndex = ref(0); // 현재 표시되고 있는 세부 문항의 인덱스

const currentQuestion = computed(() => {
  return surveyQuestions.value[currentStep.value] || {};
});

watch(currentQuestion, (newQuestion) => {
  if (newQuestion.followUps && isAnsweringSubQuestions.value) {
    currentSubQuestions.value = [newQuestion.followUps[currentSubQuestionIndex.value]];
    isMainQuestionHidden.value = true;
  } else {
    currentSubQuestions.value = [];
    isMainQuestionHidden.value = false;
  }
  canProceed.value = false; // 새로운 질문이 로드될 때 다음 버튼 비활성화
});

const handleAnswer = (option, optionIndex) => {
  responses.value[currentQuestion.value.id] = { optionIndex };
  if (currentQuestion.value.followUps && optionIndex === 0) {
    // "네, 있습니다"를 선택한 경우, 세부 문항을 준비하지만 바로 넘어가지 않음
    isAnsweringSubQuestions.value = true;
    // isMainQuestionHidden 상태 변경 부분 삭제 또는 수정
    // isMainQuestionHidden.value = false; // 주 질문과 선택지를 계속 보여줌
    canProceed.value = true; // "다음" 버튼 활성화, 세부 문항으로 넘어가기 전
  } else {
    // "아니오"를 선택한 경우 또는 세부 문항이 없는 경우
    isAnsweringSubQuestions.value = false;
    canProceed.value = true; // 다음 버튼 활성화
  }
};

const handleSubAnswer = (subQuestionId, optionIndex) => {
  if (!responses.value[currentQuestion.value.id + "_sub"]) {
    responses.value[currentQuestion.value.id + "_sub"] = [];
  }
  // 선택된 옵션 인덱스를 배열에 추가
  responses.value[currentQuestion.value.id + "_sub"][currentSubQuestionIndex.value] = optionIndex + 1; // 인덱스는 0부터 시작하므로 1을 더함

  if (currentSubQuestionIndex.value < currentQuestion.value.followUps.length - 1) {
    currentSubQuestionIndex.value++;
    canProceed.value = true;
  } else {
    canProceed.value = true;
    isAnsweringSubQuestions.value = false;
  }
};

const nextStep = () => {
  if (canProceed.value) {
    if (isAnsweringSubQuestions.value) {
      if (currentSubQuestionIndex.value < currentQuestion.value.followUps.length - 1) {
        currentSubQuestionIndex.value++;
        currentSubQuestions.value = [currentQuestion.value.followUps[currentSubQuestionIndex.value]];
        isMainQuestionHidden.value = true; // 세부 문항 표시 시 주 질문 숨김
      } else {
        isMainQuestionHidden.value = false; // 세부 문항이 더 이상 없을 때 주 질문 표시
        isAnsweringSubQuestions.value = false;
        currentStep.value++;
      }
    } else {
      isMainQuestionHidden.value = false;
      currentStep.value++;
    }
    canProceed.value = false;
  }
};

const resetSurvey = () => {
  currentStep.value = 0;
  responses.value = {};
  currentSubQuestions.value = [];
  isMainQuestionHidden.value = false;
  isAnsweringSubQuestions.value = false;
  currentSubQuestionIndex.value = 0;
  canProceed.value = false;
};

const showResults = () => {
  console.log(responses.value);
};

const progress = computed(() => {
  let progressPercentage = (currentStep.value / surveyQuestions.value.length) * 100;
  return Math.min(progressPercentage, 100);
});
</script>


<style scoped>
.main-div {
  margin: 0px
}

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

.survey-explain {
  padding: 0 20px;
  margin-bottom: 20px;
  text-align: center;
}

.survey-title {
  margin: 0;
}

.reset-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background: #C6EB74;
  color: black;
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
  padding: 40px;
  padding-top: 20px;
  margin-bottom: 40px;
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
  background: #C6EB74;
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
  background-color: #C6EB74;
  color: black;
}

.buttons-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin: 10px;
  margin-top: 50px;
}

.buttons-container button {
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  background: #C6EB74;
  color: black;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
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
  background-color: #C6EB74;
  border-radius: 5px;
  transition: width 0.3s ease-in-out;
}

.progress-info {
  text-align: right;
  font-size: 1.2em;
  margin: 10px 0;
}

.button-placeholder {
  flex: 1;
}

.results-reset-container {
  display: flex;
  justify-content: center;
  /* 중앙 정렬 */
  width: 100%;
  /* 너비 100%로 설정하여 버튼 너비를 확장 */
  gap: 20px;
  /* 버튼 사이 간격 */
}

.results-button {
  width: 300px;
  /* 버튼 너비 조정 */
  height: auto;
  padding: 10px 0;
  /* 상하 패딩 조정 */
  font-size: 1.2em;
  /* 글꼴 크기는 기본값 유지 */
}

.reset-button {
  position: absolute;
  width: 200px;
  /* 버튼 너비 조정 */
  padding: 10px 0;
  /* 상하 패딩 조정 */
  font-size: 1em;
  /* 글꼴 크기는 기본값 유지 */
  right: 50px;
}
</style>