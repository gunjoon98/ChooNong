<template>
  <div class="main-div">
    <div class="steppy">
      <h2 class="step-title">문제 {{ currentQuestion + 1 }} / {{ questions.length }}</h2>
      <h3 class="question-text">{{ questions[currentQuestion].question }}</h3>
      <ul class="options-container">
        <li v-for="(answer, index) in questions[currentQuestion].answers" :key="index"
          @click="() => checkAnswer(answer, index)"
          :class="{ 'option': true, 'selected': lastSelectedAnswerIndex === index }">
          {{ answer.text }}
        </li>
      </ul>
    </div>

    <Dialog v-model:visible="showModal" class="custom-dialog" :style="{ width: '50vw' }" :closable="false">
      <template #header>
        <div class="dialog-header">
          <img v-if="resultImage" :src="resultImage" alt="Result Image" class="header-image"/>
          <div class="header-text">{{ dialogTitle }}</div>
        </div>
      </template>
      <div class="dialog-content">
        <p class="dialog-message" v-html="modalMessage"></p>
        <p class="dialog-message" v-if="showAnswer">{{ answerReason }}</p>
        <p class="dialog-message" v-if="additionalAnswerReason">{{ additionalAnswerReason }}</p>
      </div>
      <template #footer>
        <div class="buttons-container">
          <button v-if="showRetryQuestionButton" @click="retryQuestion" class="retry-button">다시 풀어보기</button>
          <button v-if="showRevealAnswerButton" @click="revealAnswer" class="show-answer-button">정답 보기</button>
          <button v-if="showNextQuestionButton" @click="nextQuestion" class="next-button">다음 문제 풀기</button>
          <button v-if="showRetryQuizButton" @click="retryQuiz" class="retry-button">다시하기</button>
          <button v-if="showMainButton" @click="goToMain" class="goto-button">메인으로</button>
        </div>
      </template>
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Dialog from 'primevue/dialog';
import { useRouter } from 'vue-router';
import correctImage from '@/assets/correct.jpg';
import incorrectImage from '@/assets/wrong.png';

const questions = ref([
  {
    "question": "귀농인이 되기 위해 농촌으로 이주하기 전에 얼마 동안 농촌 외의 지역에 주민등록이 되어 있어야 하나요?",
    "answers": [
      { "text": "1년 이상", "correct": true },
      { "text": "6개월", "correct": false, "reason": "귀농인은 농촌 외의 지역에 1년 이상 주민등록이 되어 있어야 합니다." },
      { "text": "2년 이상", "correct": false, "reason": "귀농인은 농촌 외의 지역에 1년 이상 주민등록이 되어 있어야 합니다." },
      { "text": "3개월", "correct": false, "reason": "귀농인은 농촌 외의 지역에 1년 이상 주민등록이 되어 있어야 합니다." }
    ]
  },
  {
    "question": "농업인이 되기 위한 조건 중 올바르지 않은 것은?",
    "answers": [
      { "text": "1천제곱미터 미만의 농지를 경영하거나 경작하는 사람", "correct": true, "reason": "농업인은 1천제곱미터 이상의 농지를 경영하거나 경작해야 합니다." },
      { "text": "농업경영을 통한 농산물의 연간 판매액이 120만원 이상인 사람", "correct": false, "reason": "이 조건은 농업인이 되기 위한 정확한 조건입니다." },
      { "text": "1년 중 90일 이상 농업에 종사하는 사람", "correct": false, "reason": "이 조건은 농업인이 되기 위한 정확한 조건입니다." },
      { "text": "농업회사법인에 1년 이상 계속하여 고용되지 않은 사람", "correct": false, "reason": "규제에 따라 1년 이상 계속하여 고용된 사람도 농업인에 포함됩니다." }
    ]
  },
  {
    "question": "귀농인과 귀촌인을 구별하는 기준은 무엇인가요?",
    "answers": [
      { "text": "농업경영체에 등록했는지 여부", "correct": true },
      { "text": "이주한 지역의 크기", "correct": false, "reason": "이주한 지역의 크기가 아니라 농업경영체 등록 여부로 귀농인과 귀촌인을 구별합니다." },
      { "text": "이주하기 전의 주민등록 기간", "correct": false, "reason": "주민등록 기간이 아니라 농업경영체 등록 여부로 귀농인과 귀촌인을 구별합니다." },
      { "text": "농업에 종사하는 기간", "correct": false, "reason": "농업에 종사하는 기간이 아니라 농업경영체 등록 여부로 귀농인과 귀촌인을 구별합니다." }
    ]
  },
  {
    "question": "성공적인 귀농을 위해 가장 먼저 해야 할 일은 무엇인가요?",
    "answers": [
      { "text": "귀농정보 수집", "correct": true },
      { "text": "재배 작물 선정", "correct": false, "reason": "재배 작물을 선정하기 전에 농업 관련 정보를 충분히 수집하는 것이 필요합니다." },
      { "text": "영농기술 습득", "correct": false, "reason": "영농기술을 습득하기 전에 농업 관련 정보를 충분히 수집하는 것이 필요합니다." },
      { "text": "주택 및 농지 구매", "correct": false, "reason": "주택이나 농지를 구매하기 전에 충분한 정보 수집과 계획 수립이 필요합니다." }
    ]
  },
  {
    "question": "귀농 계획 시 반드시 고려해야 하는 사항은 무엇인가요?",
    "answers": [
      { "text": "가족과의 충분한 의논", "correct": true },
      { "text": "인터넷으로 정보 검색하기", "correct": false, "reason": "인터넷으로 정보를 검색하는 것도 중요하지만, 가족과의 충분한 논의 후 동의를 얻는 것이 더 중요합니다." },
      { "text": "즉시 농지 구매하기", "correct": false, "reason": "농지 구매 전에 여러 요소를 고려하여 계획을 세우고 가족과의 의논이 필요합니다." },
      { "text": "농기계 구입", "correct": false, "reason": "농기계 구입 전에 가족과의 충분한 논의와 영농 계획 수립이 선행되어야 합니다." }
    ]
  },
  {
    "question": "귀농 시 영농계획을 수립할 때 어떤 점을 고려하는 것이 좋습니까?",
    "answers": [
      { "text": "가격변동이 적고, 영농기술과 자본이 적게 드는 작목 중심으로", "correct": true },
      { "text": "가장 높은 수익을 약속하는 작목 중심으로", "correct": false, "reason": "초보 귀농인은 가격변동이 적고, 영농기술과 자본이 적게 드는 작목 중심으로 계획을 수립하는 것이 좋습니다." },
      { "text": "최신 유행하는 작목 중심으로", "correct": false, "reason": "유행하는 작목보다는 자신의 여건에 맞고, 가격변동이 적은 작목을 선택하는 것이 좋습니다." },
      { "text": "가장 작업이 쉬운 작목 중심으로", "correct": false, "reason": "작업의 용이성도 중요하지만, 가격변동과 영농기술, 자본 투입이 적은 작목을 우선 고려하는 것이 좋습니다." }
    ]
  },
  {
    "question": "귀농을 준비하는 과정에서 정착지를 선정할 때 중요하게 고려해야 할 요소는 무엇인가요?",
    "answers": [
      { "text": "자녀교육 등 생활 여건과 선정된 작목에 적합한 입지 조건", "correct": true },
      { "text": "가장 경치가 좋은 지역", "correct": false, "reason": "경치도 중요할 수 있지만, 생활 여건과 작목에 적합한 입지 조건이 더 중요합니다." },
      { "text": "가장 땅값이 저렴한 지역", "correct": false, "reason": "땅값도 중요하지만, 자녀교육 등 생활 여건과 작목에 적합한 입지 조건을 우선 고려해야 합니다." },
      { "text": "교통이 가장 편리한 지역", "correct": false, "reason": "교통 편의성도 중요하지만, 생활 여건과 작목에 적합한 입지 조건을 더 우선시 해야 합니다." }
    ]
  }

]);

const currentQuestion = ref(0);
const showModal = ref(false);
const dialogTitle = ref("");
const modalMessage = ref("");
const isCorrectAnswer = ref(false);
const showAnswer = ref(false);
const answerReason = ref("");
const additionalAnswerReason = ref("");
const correctAnswersCount = ref(0);
let lastSelectedAnswerIndex = ref(-1); // 이 부분을 ref로 감싸주었습니다.
let attemptCounter = ref(0);

// 버튼 표시를 위한 상태 변수
const showRetryQuestionButton = ref(false);
const showRevealAnswerButton = ref(false);
const showNextQuestionButton = ref(false);
const showRetryQuizButton = ref(false);
const showMainButton = ref(false);

const resultImage = ref('');

const router = useRouter();


const checkAnswer = (answer, index) => {
  showModal.value = true;
  lastSelectedAnswerIndex.value = index;

  if (answer.correct) {
    correctAnswersCount.value++;
    correctAnswerActions();
    resultImage.value = correctImage; // 정답일 때 이미지 설정
  } else {
    if (attemptCounter.value < 1) {
      attemptCounter.value++;
      incorrectAnswerActions();
    } else {
      revealAnswer(true);
    }
    resultImage.value = incorrectImage; // 오답일 때 이미지 설정
  }
};

const correctAnswerActions = () => {
  isCorrectAnswer.value = true;
  showAnswer.value = true;
  dialogTitle.value = "정답입니다!";
  modalMessage.value = "잘 했어요! 다음 문제로 넘어갑시다.";

  // 문제가 끝났는지 체크하여 다음 단계 결정
  if (currentQuestion.value === questions.value.length - 1) {
    modalMessage.value = `축하합니다! 모든 문제를 완료했습니다. <br>총 ${correctAnswersCount.value}개 문제를 맞췄습니다.`;
    showRetryQuizButton.value = true;
    showMainButton.value = true;
    showNextQuestionButton.value = false; // 마지막 문제에서는 다음 문제 풀기 버튼을 보이지 않게 합니다.

    showAnswer.value = false; // 마지막 문제에서는 reason을 숨깁니다.
    answerReason.value = ""; // reason을 비웁니다.
    additionalAnswerReason.value = ""; 
  } else {
    showNextQuestionButton.value = true; // 마지막 문제가 아니라면 다음 문제로 넘어갈 수 있게 버튼을 활성화합니다.
  }

  showRetryQuestionButton.value = false;
  showRevealAnswerButton.value = false;
};


const incorrectAnswerActions = () => {
  isCorrectAnswer.value = false;
  showAnswer.value = false;
  dialogTitle.value = "오답입니다.";
  modalMessage.value = "다시 시도해 보세요.";
  showRetryQuestionButton.value = true;
  showRevealAnswerButton.value = true; // 항상 "정답 보기" 버튼을 활성화합니다.
};

const revealAnswer = (forced = false) => {
  const correctAnswer = questions.value[currentQuestion.value].answers.find(a => a.correct);
  isCorrectAnswer.value = false;
  showAnswer.value = true;
  answerReason.value = correctAnswer.reason || "";

  if (lastSelectedAnswerIndex.value >= 0 && !questions.value[currentQuestion.value].answers[lastSelectedAnswerIndex.value].correct) {
    additionalAnswerReason.value = questions.value[currentQuestion.value].answers[lastSelectedAnswerIndex.value].reason || "";
  } else {
    additionalAnswerReason.value = "";
  }

  // 마지막 문제에서만 정답 확인 후 메시지에 현재까지 맞힌 정답 개수 표시
  if (currentQuestion.value === questions.value.length - 1) {
    modalMessage.value = `정답은 '${correctAnswer.text}'입니다. 총 ${correctAnswersCount.value}개의 문제를 맞췄습니다.`;
  } else {
    modalMessage.value = `정답은 '${correctAnswer.text}'입니다.`;
  }

  resetButtons();

  if (currentQuestion.value === questions.value.length - 1) {
    showRetryQuizButton.value = true; // 다시하기 버튼 활성화
    showMainButton.value = true; // 메인으로 버튼 활성화
    showNextQuestionButton.value = false; // 마지막 문제이므로 다음 문제 버튼 비활성화
    // 마지막 문제에서 정답 또는 오답 확인 후 더 이상 reason을 표시하지 않음
    showAnswer.value = false;
    answerReason.value = "";
    additionalAnswerReason.value = "";
  } else {
    showNextQuestionButton.value = true; // 다음 문제로 넘어가기 버튼 활성화
  }

  // 오답을 선택한 후 정답을 확인하는 경우에는 다시 풀어보기 버튼과 정답 보기 버튼을 숨김
  showRevealAnswerButton.value = false;
  showRetryQuestionButton.value = false;
};

const nextQuestion = () => {
  if (currentQuestion.value < questions.value.length - 1) {
    currentQuestion.value++; // 현재 문제 번호 증가
    resetQuestionState(); // 다이얼로그 숨기기 및 관련 상태 초기화
    attemptCounter.value = 0; // 시도 횟수 초기화
    lastSelectedAnswerIndex.value = -1; // 마지막 선택한 답변 인덱스 초기화
  } else {
    // 마지막 문제를 완료한 후 처리할 로직 (예: 퀴즈 종료 메시지 표시 등)
    console.log("퀴즈 완료");
  }
};


const resetQuestionState = () => {
  additionalAnswerReason.value = "";
  showModal.value = false;
  showAnswer.value = false;
  resetButtons();
};

const resetButtons = () => {
  showRetryQuestionButton.value = false;
  showRevealAnswerButton.value = false;
  showNextQuestionButton.value = false;
  showRetryQuizButton.value = false;
  showMainButton.value = false;
};

const retryQuestion = () => {
  showModal.value = false;
  showAnswer.value = false;
};

const retryQuiz = () => {
  currentQuestion.value = 0;
  correctAnswersCount.value = 0;
  resetQuestionState();
};

const goToMain = () => {
  router.push({ path: '/' });
};
</script>

<style scoped>
.main-div {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background: #fff;
  border: solid 3px #ECF6EC;
  border-radius: 1rem;
  max-width: 1000px;
  margin: 20px auto;
}

.dialog-header {
  display: flex;
  align-items: center;
}

.header-text {
  font-size: 1.5em;
  font-weight: bold
}

.header-image {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}

.steppy {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-bottom: 100px;
}

.step-title {
  font-size: 1.5em;
  color: #333;
  margin-bottom: 70px;
}

.question-text {
  margin: 0 auto;
  text-align: center;
}

.dialog-message {
  font-size: 1.2em;
}

.options-container {
  list-style-type: none;
  padding: 0;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  margin: 0 auto;
  max-width: 600px;
}

.option {
  margin: 10px 5px;
  padding: 10px 20px;
  border: solid 2px #C6EB74;
  border-radius: 15px;
  background-color: #F9F9F9;
  color: #333;
  cursor: pointer;
  text-align: center;
}

.option:hover {
  background-color: #C6EB74;
  color: black;
}

.custom-dialog .dialog-content {
  display: flex;
  flex-direction: column;
}

.response-container {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.result-image {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}

.buttons-container {
  display: flex;
  justify-content: center;
  gap: 10px;
  width: 100%;
}

.retry-button, .show-answer-button, .next-button, .goto-button {
  margin: 10px 5px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #C6EB74;
  color: black;
  cursor: pointer;
}

.retry-button:hover, .show-answer-button:hover, .next-button:hover, .goto-button:hover {
  background-color: #AFCB59;
}
</style>
