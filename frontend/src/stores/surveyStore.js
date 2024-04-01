import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useSurveyStore = defineStore('survey', () => {

  
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