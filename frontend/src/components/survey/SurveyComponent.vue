<template>
  <div class="survey-container">
    <div class="progress-bar-container">
      <div class="progress-bar" :style="{ width: progress + '%' }"></div>
    </div>
    <h2 class="survey-title">간단한 설문조사</h2>
    <div v-if="currentStep < surveyQuestions.length">
      <div class="question">
        <h3>{{ currentQuestion.question }}</h3>
        <div>
          <button
            v-for="(option, index) in currentQuestion.options"
            :key="index"
            @click="handleAnswer(index)"
            :class="{
              'option-button': true,
              selected: responses[currentQuestion.id] === index,
            }"
          >
            {{ option }}
          </button>
        </div>
      </div>
      <button @click="nextStep" v-if="canProceed" class="nav-button">
        다음
      </button>
    </div>
    <div v-if="currentStep === surveyQuestions.length">
      <button @click="showResults" class="nav-button">결과 보기</button>
      <button @click="resetSurvey" class="nav-button">다시 시작하기</button>
    </div>
  </div>
</template>

<script setup>
// import router from '@/router';
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useSurveyStore } from "@/stores/surveyStore";

const router = useRouter();
const surveyStore = useSurveyStore();

const surveyQuestions = ref([
  {
    id: "one",
    question: "자녀가 있습니까?",
    options: ["네 있습니다.", "없습니다."],
  },
  {
    id: "one_sub_1",
    question: "자녀가 다니는 학교는 어디인가요?",
    options: ["유치원", "초등학교", "중학교"],
    hidden: true,
  },
  {
    id: "one_sub_2",
    question: "자녀의 기상 시간은?",
    options: ["등교 30분 전", "등교 1시간 전", "모름"],
    hidden: true,
  },
  {
    id: "one_sub_3",
    question: "자녀의 병원 방문 주기를 선택해주세요.",
    options: [
      "반 년에 1회 미만",
      "3개월 1회 이상",
      "월 1회 이상",
      "월 2회 이상",
    ],
    hidden: true,
  },
  {
    id: "two",
    question: "고려하는 예산을 선택해주세요. (주거 30평, 농지 100평 기준)",
    options: [
      "농지 1700만원 이하, 주거 1억 이하",
      "농지 1700만원 이상, 주거 1억 이상",
    ],
  },
  {
    id: "three_1",
    question: "귀농을 고려하는데 수질을 어떻게 생각하시나요?",
    options: [
      "전혀 중요하지 않다",
      "약간 중요하지 않다",
      "보통이다",
      "중요한 편이다",
      "매우 중요하다",
    ],
  },
  {
    id: "three_2",
    question: "귀농을 고려하는데 공기 청정도를 어떻게 생각하시나요?",
    options: [
      "전혀 중요하지 않다",
      "약간 중요하지 않다",
      "보통이다",
      "중요한 편이다",
      "매우 중요하다",
    ],
  },
  {
    id: "four_1",
    question: "어떤 귀농 생활을 꿈꾸시나요?",
    options: ["한적하고 유유자적한 생활", "많은 사람들과 교류하는 생활"],
  },
  {
    id: "four_2",
    question: "대형 마트/시장을 얼마나 자주 방문하시나요?",
    options: ["월 1회 미만", "월 1회 이상", "월 2회 이상", "월 3회 이상"],
  },
  {
    id: "four_3",
    question: "대중 교통을 얼마나 자주 이용하시나요?",
    options: ["주 1회 미만", "주 1회 이상", "주 3회 이상", "주 5회 이상"],
  },
]);

const currentStep = ref(0);
const canProceed = ref(false);

const currentQuestion = computed(
  () => surveyQuestions.value[currentStep.value]
);
const responses = ref([]);
const structuredResponses = ref({});

onMounted(function () {
  resetSurvey();
});

const progress = computed(() => {
  let progressPercentage =
    (currentStep.value / surveyQuestions.value.length) * 100;
  return Math.min(progressPercentage, 100);
});

const handleAnswer = function (optionIndex) {
  let questionId = currentQuestion.value.id;
  responses.value[questionId] = optionIndex;
  if (questionId === "one") {
    structuredResponses.value[questionId] = optionIndex + 1;
    // "one_sub" 질문이 세 개 있으므로, 초기화 시 [null, null, null] 사용
    structuredResponses.value["one_sub"] = [null, null, null];
  } else if (questionId.startsWith("one_sub")) {
    let index = parseInt(questionId.split("_")[2]) - 1;
    structuredResponses.value["one_sub"][index] = optionIndex + 1;
  } else {
    let parts = questionId.split("_");
    let category = parts[0];
    let subId = parts[1] ? parseInt(parts[1]) - 1 : 0;

    if (category === "two") {
      structuredResponses.value[category] = optionIndex + 1;
    } else {
      if (!structuredResponses.value[category]) {
        // "three"와 "four" 카테고리의 경우 미리 정의된 배열 크기 할당
        structuredResponses.value[category] =
          category === "three" ? [null, null] : [null, null, null];
      }
      structuredResponses.value[category][subId] = optionIndex + 1;
    }
  }
  canProceed.value = true;
};

const nextStep = function () {
  if (canProceed.value) {
    // 첫 번째 문항에서 "네 있습니다." 선택 시, 순차적으로 다음 문항으로
    if (currentStep.value === 0 && responses.value["one"] === 0) {
      // 2, 3, 4번 문항을 정상적으로 거치도록 설정
      currentStep.value++;
    } else if (currentStep.value === 0 && responses.value["one"] === 1) {
      // 첫 번째 문항에서 "없습니다" 선택 시, 바로 5번 문항으로
      currentStep.value = 4; // 실제 배열 인덱스로는 5번 문항으로 이동
    } else {
      // 기타 모든 경우에는 단순히 다음 문항으로 이동
      currentStep.value++;
    }
    canProceed.value = false; // '다음' 버튼 초기화
  }
};

const showResults = async function () {
  // 결과 보여주기 (실제 구현 필요)
  console.log("structuredResponses", structuredResponses.value);
  console.log("responses", responses.value);
  console.log("결과 보기");
  await surveyStore.getSurveyResult(structuredResponses.value);
  router.push({ name: "surveyResult" });
};

const resetSurvey = function () {
  currentStep.value = 0;
  canProceed.value = false;
  responses.value = []; // responses 초기화
  structuredResponses.value = {}; // structuredResponses 초기화
  // 서브 질문 숨기기 초기화
  surveyQuestions.value.forEach((question, index) => {
    if (question.hidden) {
      question.hidden = true;
    }
  });
};
</script>

<style scoped>
.main-div {
  margin: 0px;
}

.survey-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 600px;
  margin: 40px auto;
}

.progress-bar-container {
  width: 100%;
  background-color: #e9ecef;
  border-radius: 20px;
  margin: 20px 0;
}

.progress-bar {
  height: 20px;
  background-color: #c6eb74;
  border-radius: 20px;
  transition: width 0.4s ease;
}

.survey-title {
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

.question h3 {
  color: #333;
  font-size: 1.5rem;
  text-align: center;
}

.option-button {
  margin: 10px 5px;
  padding: 10px 20px;
  border: solid 2px #c6eb74;
  border-radius: 15px;
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  cursor: pointer;
  transition: background-color 0.3s;
}

.option-button:hover {
  background-color: #c6eb74;
}

.nav-button {
  display: block;
  margin: 20px auto;
  padding: 10px 20px;
  /* margin-top: 20px; */
  background-color: #c6eb74;
  color: rgb(0, 0, 0);
  border: none;
  border-radius: 15px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.nav-button:hover {
  /* border: solid 2px #C6EB74;
  border-radius: 15px;
  background-color: #ffffff; */
  /* box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.5); */
  box-shadow: 7px 7px 5px 0px #0002, 4px 4px 5px 0px #0001;
}

.selected {
  background-color: #c6eb74;
  /* 선택된 버튼의 배경색 */
  color: rgb(0, 0, 0);
}

.result-button {
  display: block;
  margin: 20px auto;
  width: 110px;
  height: 50px;
  background-color: #c6eb74;
  border: none;
  border-radius: 15px;
  cursor: pointer;
  /* 커서 포인터로 변경 */
  /* transition: background-color 0.3s ease; */
  /* 배경색 변화에 대한 전환 */
  /* color: #000000; */
  /* text-decoration: none; */
  text-align: center;
  line-height: 40px;
}
</style>
