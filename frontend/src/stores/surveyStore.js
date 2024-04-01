import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useSurveyStore = defineStore('survey', () => {
  const steps = ref([
    { title: '질문 1', content: '질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1 질문 문항1', options: ['답변 1답변 1답변 1답변 1답변 1답변 1답변 1답변 1', '답변 2', '답변 3', '답변 4'] },
    { title: '질문 2', content: '질문 문항2', options: ['답변 1', '답변 2', '답변 3', '답변 4', '답변 5', '답변 6'] },
    { title: '질문 3', content: '질문 문항3', options: ['답변 1', '답변 2', '답변 3'] },
    { title: '질문 4', content: '질문 문항4', options: ['답변 1', '답변 2', '답변 3', '답변 4'] },
  ]);
  const selectedAnswers = ref(steps.value.map(() => null));

  const selectAnswer = function(questionIndex, answerIndex) {
    selectedAnswers.value[questionIndex] = selectedAnswers.value[questionIndex] === answerIndex ? null : answerIndex;
    console.log(selectedAnswers.value)
  };
  
  const resetAnswers = function() {
    selectedAnswers.value.fill(null);
  };

  const isAnswerSelected = function(questionIndex, answerIndex) {
    return selectedAnswers.value[questionIndex] === answerIndex;
  };

  const isUnanswered = function(index) {
    return selectedAnswers.value[index] === null;
  };

  const allAnswersSelected = computed(() => {
    return selectedAnswers.value.every(answer => answer !== null);
  });

  const resultList = ref([]);
  const getSurveyResult = async function () {
    await axios({
      method: "GET",
      url: `https://choonong.store/api/region/survey?edu=0&ground=0&resident=0&env=0`,
    }).then((response) => {
      resultList.value = response.data;
    });
  };

  return {
    steps,
    selectedAnswers,
    selectAnswer,
    isAnswerSelected,
    isUnanswered,
    allAnswersSelected,
    resetAnswers,
    resultList,
    getSurveyResult
  }
});