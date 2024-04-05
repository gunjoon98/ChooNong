import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useRegionStore } from "./regionStore";

export const useSurveyStore = defineStore("survey", () => {
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
    })
      .then(async (response) => {
        const resultRegionIds = response.data.map((item) => item.region_id);
        await regionStore.getRegionsDetailList(resultRegionIds);
        resultList.value = regionStore.regionsDetailList;
      })
      .catch((error) => {
        console.error("요청 실패:", error);
        throw error;
      });
  };

  return {
    resultList,
    getSurveyResult,
  };
});
