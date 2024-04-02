import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useSurveyStore = defineStore('survey', () => {

  
  const resultList = ref([]);
  const getSurveyResult = async function (structuredResponses) {
    await axios({
      method: "POST",
      url: `https://www.choonong.store/fapi/recommendation`,
      data: structuredResponses,
    }).then((response) => {
      resultList.value = response.data;
    });
  };

  return {
    // steps,
    // selectedAnswers,
    // selectAnswer,
    // isAnswerSelected,
    // isUnanswered,
    // allAnswersSelected,
    // resetAnswers,
    resultList,
    getSurveyResult
  }
});