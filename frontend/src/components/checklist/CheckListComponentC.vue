<template>
  
  <!-- 해야할거: 
  1. 2번째 다시 풀어보기시 오답 누른 경우 틀렸습니다 표기 하기
  2. 문제 다 풀고 몇점인지 알려주고 점수에 따른 다양한 텍스트 보이게하기
  3. 맞고, 틀릴때 사진 넣기
  4. 추가 문제 생각하기 
  5. 닫기 어떻게 할지 생각
  -->
          

  <div class="main-div">
    <div class="steppy">
      <h2 class="step-title">문제 {{ currentQuestion + 1 }}/{{ questions.length }}</h2>
      <h3 class="question-text">{{ questions[currentQuestion].question }}</h3>
      <ul class="options-container">
        <li v-for="(answer, index) in questions[currentQuestion].answers" :key="index"
          @click="() => checkAnswer(answer, index)"
          :class="{ 'option': true, 'selected': lastSelectedAnswerIndex === index }">
          {{ answer.text }}
        </li>
      </ul>
    </div>

    <Dialog v-model:visible="showModal" class="custom-dialog" :style="{ width: '50vw' }">
      <template #header>
        <h3>{{ showAnswer ? '정답 확인' : '문제 결과' }}</h3>
      </template>
      <div class="dialog-content">
        <p>{{ modalMessage }}</p>
        <p v-if="showAnswer">{{ answerReason }}</p>
        <p v-if="additionalAnswerReason">{{ additionalAnswerReason }}</p>
      </div>
      <template #footer>
        <div class="buttons-container">
          <button v-if="!showAnswer && !isCorrectAnswer" @click="retryQuestion" class="retry-button">다시 풀어보기</button>
          <button v-if="!showAnswer" @click="revealAnswer" class="show-answer-button">정답 보기</button>
          <button v-if="isCorrectAnswer || showAnswer" @click="nextQuestion" class="next-button">다음 문제 풀기</button>
          <button v-if="showAnswer" @click="closeModal" class="next-button">닫기</button>
        </div>
      </template>
    </Dialog>
  </div>
</template>


<script setup>
import { ref } from 'vue';
import Dialog from 'primevue/dialog';

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
const modalMessage = ref("");
const isCorrectAnswer = ref(false);
const showAnswer = ref(false);
const answerReason = ref("");
const additionalAnswerReason = ref("");
const attemptCounter = ref(0);
let lastSelectedAnswerIndex = -1;

const checkAnswer = function (answer, index) {
  showModal.value = true;
  lastSelectedAnswerIndex = index;
  if (answer.correct) {
    correctAnswerActions(answer);
  } else {
    incorrectAnswerActions(answer);
  }
};

const correctAnswerActions = function (answer) {
  isCorrectAnswer.value = true;
  modalMessage.value = "정답입니다!";
  answerReason.value = answer.reason || "잘했습니다! 다음 문제로 넘어갑시다.";
  additionalAnswerReason.value = "";
  showAnswer.value = true;
  attemptCounter.value = 0;
};

const incorrectAnswerActions = function (answer) {
  if (attemptCounter.value < 1) {
    attemptCounter.value++;
    isCorrectAnswer.value = false;
    modalMessage.value = "틀렸습니다. 한 번 더 시도해보세요.";
    answerReason.value = answer.reason;
    additionalAnswerReason.value = "";
    showAnswer.value = false;
  } else {
    revealAnswer();
  }
};

const revealAnswer = function () {
  const correctAnswer = questions.value[currentQuestion.value].answers.find(a => a.correct);
  isCorrectAnswer.value = false;
  showAnswer.value = true;
  modalMessage.value = "정답은 다음과 같습니다:";
  answerReason.value = `정답: ${correctAnswer.text}`;
  additionalAnswerReason.value = lastSelectedAnswerIndex !== -1 ? questions.value[currentQuestion.value].answers[lastSelectedAnswerIndex].reason : correctAnswer.reason || "";
  showModal.value = true;
  attemptCounter.value = 0;
};

const nextQuestion = function () {
  if (currentQuestion.value < questions.value.length - 1) {
    currentQuestion.value++;
  } else {
    modalMessage.value = "모든 문제를 완료했습니다!";
  }
  resetQuestionState();
};

const resetQuestionState = function () {
  showModal.value = false;
  showAnswer.value = false;
  isCorrectAnswer.value = false;
  answerReason.value = "";
  additionalAnswerReason.value = "";
  attemptCounter.value = 0;
  lastSelectedAnswerIndex = -1;
};

const retryQuestion = function () {
  showModal.value = false;
  showAnswer.value = false;
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

.dialog-content {
  font-size: 1.25em;
  /* Dialog 내용의 폰트 크기를 증가시킴 */
}

.custom-dialog .p-dialog-content {
  max-height: 60vh;
  /* Dialog 내용의 최대 높이 조정 */
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
  margin-bottom: 100px;
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
  padding: 10px 20px;
  background: #f0f0f0;
  border: 1px solid #d0d0d0;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

.option:hover {
  background-color: #ECF6EC;
}

.buttons-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  width: 100%;
  margin-top: 20px;
}

.next-button,
.retry-button,
.show-answer-button {
  padding: 12px 18px;
  /* 버튼의 크기를 증가시킴 */
  border: none;
  border-radius: 5px;
  background: #f0f0f0;
  color: #333;
  cursor: pointer;
  font-size: 1.25em;
  /* 버튼 내부의 폰트 크기를 증가시킴 */
  transition: background-color 0.3s;
}

.next-button:hover,
.retry-button:hover,
.show-answer-button:hover {
  background: #ddd;
}
</style>
