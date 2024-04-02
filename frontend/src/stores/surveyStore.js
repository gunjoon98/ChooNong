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
    }).then(async (response) => {
      const resultRegionIds= response.data.map(item => item.region_id);
      console.log(resultRegionIds);
      await regionStore.getRegionsDetailList(resultRegionIds);
      resultList.value = regionStore.regionsDetailList;
      console.log(resultList.value);
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
