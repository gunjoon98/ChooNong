import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

export const useSurveyStore = defineStore('survey', () => {

  const structuredResponsesToJson = (structuredResponses) => {
    return JSON.stringify(structuredResponses);
  };
  
  const resultList = ref([]);

  const getSurveyResult = function (structuredResponses) {
    return axios({
      method: "POST",
      url: "https://www.choonong.store/fapi/recommendation",
      data: structuredResponsesToJson(structuredResponses),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) => {
      resultList.value = response.data.map(item => item.region_id);
      console.log("응답 결과", resultList.value);
      return response.data;
    }).catch((error) => {
      console.error("요청 실패:", error);
      throw error; // 이 부분은 필요에 따라 변경할 수 있습니다.
    });
  };

  return {
    resultList,
    getSurveyResult
  }
});
