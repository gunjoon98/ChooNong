import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";
import { useRegionStore } from './regionStore';

export const useSurveyStore = defineStore('survey', () => {

  const regionStore = useRegionStore();

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
      const resultRegionIds= response.data.map(item => item.region_id);
      console.log("응답 결과", resultRegionIds);
      resultList.value = regionStore.getRegionsDetailList(resultRegionIds);
      // return response.data;
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
