import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useSurveyStore = defineStore('survey', () => {

  
  const resultList = ref([]);
  const getSurveyResult = async function (structuredResponses) {
    try {
      const response = await axios.post("https://www.choonong.store/fapi/recommendation", structuredResponsesToJson(structuredResponses));
      resultList.value = response.data;
    } catch (error) {
      console.error("Error fetching survey result:", error);
    }
  };

  const structuredResponsesToJson = (structuredResponses) => {
    // Convert structuredResponses to JSON format
    return JSON.stringify(structuredResponses);
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