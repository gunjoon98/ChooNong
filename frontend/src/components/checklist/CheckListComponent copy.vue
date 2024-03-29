<template>
  <div>
    <!-- 현재 문항 표시 -->
    <div v-if="currentQuestion < questions.length">
      <h2>{{ questions[currentQuestion].question }}</h2>
      <ul>
        <!-- 선택지 표시 -->
        <li v-for="(answer, index) in questions[currentQuestion].answers" :key="index" @click="checkAnswer(answer)">
          {{ answer.text }}
        </li>
      </ul>
    </div>

    <!-- PrimeVue 모달 창 -->
    <Dialog v-model:visible="showModal">
      <template #header>
        <h3>문제 결과</h3>
      </template>
      <p>{{ modalMessage }}</p>
      <p v-if="!isCorrectAnswer">{{ reason }}</p>
      <template #footer>
        <button v-if="currentQuestion < questions.length - 1" @click="nextQuestion">다음 문제 풀기</button>
        <button v-else @click="closeModal">완료</button>
      </template>
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Dialog from 'primevue/dialog';

const questions = ref([
  {
    question: "1 + 1은?",
    answers: [
      { text: "2", correct: true },
      { text: "3", correct: false, reason: "1에 1을 더하면 2가 됩니다." }
    ]
  },
  {
    question: "태양계에서 지구는 몇 번째 행성인가요?",
    answers: [
      { text: "3번째", correct: true },
      { text: "4번째", correct: false, reason: "태양에서부터 수성, 금성을 지나면 지구가 있습니다." }
    ]
  }
  // 더 많은 문항 추가 가능
]);

const currentQuestion = ref(0);
const showModal = ref(false);
const modalMessage = ref("");
const reason = ref("");
const isCorrectAnswer = ref(true);

const checkAnswer = (answer) => {
  showModal.value = true;
  isCorrectAnswer.value = answer.correct;
  modalMessage.value = answer.correct ? "정답입니다!" : "틀렸습니다!";
  reason.value = answer.reason || "";
};

const nextQuestion = () => {
  if (currentQuestion.value < questions.value.length - 1) {
    currentQuestion.value++;
  } else {
    modalMessage.value = "모든 문제를 완료했습니다!";
  }
  showModal.value = false;
};

const closeModal = () => {
  showModal.value = false;
};
</script>

<style>
/* 필요에 따라 스타일 추가 */
</style>
