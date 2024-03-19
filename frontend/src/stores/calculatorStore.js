import { ref, reactive } from "vue";
import { defineStore } from "pinia";

export const useCalculatorStore = defineStore('calculator', () => {

  const totalExtent = ref();
  const addedCropList = ref([]);

  const setTotalExtent = async function(extent) {
    totalExtent.value = extent;
    // console.log(totalExtent)
    // console.log(totalExtent.value)
  }

  const setAddedCropList = async function(cropList) {
    addedCropList.value = cropList;
    // console.log(addedCropList)
    // console.log(addedCropList.value)
  }

  return {
    totalExtent,
    setTotalExtent,
    addedCropList,
    setAddedCropList,
  }
});