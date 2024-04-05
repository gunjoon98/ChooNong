import { ref, reactive } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useCalculatorStore = defineStore("calculator", () => {
  const totalExtent = ref();
  const addedCropList = ref([]);
  const cropList = ref([]);

  const setTotalExtent = async function (extent) {
    totalExtent.value = extent;
  };

  const setAddedCropList = async function (cropList) {
    addedCropList.value = cropList;
  };

  const getCropList = async function () {
    await axios({
      method: "GET",
      url: `https://choonong.store/api/cropcalculation`,
    }).then((response) => {
      cropList.value = [];
      for (let a of response.data) {
        const crop = {
          cropId: a.crop_id,
          cropName: a.crop_name,
          administrationFee: a.administration_fee,
          imageUrl: a.image_url,
          sales: a.sales,
          profitRate: a.profit_rate,
        };
        cropList.value.push(crop);
      }
    });
  };

  return {
    totalExtent,
    setTotalExtent,
    addedCropList,
    setAddedCropList,
    cropList,
    getCropList,
  };
});
