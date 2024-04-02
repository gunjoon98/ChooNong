import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useSurveyStore = defineStore('survey', () => {

  
  const resultList = ref([]);
  const getSurveyResult = async function (structuredResponses) {
    console.log("structuredResponses" + structuredResponses);
    await axios({
      method: "POST",
      url: "https://www.choonong.store/fapi/recommendation",
      data: structuredResponsesToJson(structuredResponses),
      contentType: "application/json",
    }).then((response) => {
      resultList.value = response.data;
      console.log("응답 결과" + resultList.value);
    });
  };

  const structuredResponsesToJson = (structuredResponses) => {
    console.log(JSON.stringify(structuredResponses));
    return JSON.stringify(structuredResponses);
  };

  return {
    resultList,
    getSurveyResult
  }
});