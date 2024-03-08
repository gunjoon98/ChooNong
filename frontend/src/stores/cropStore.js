import { ref, computed, reactive } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useCropStore = defineStore('crop', () => {
	// 내부 값 다 변경
	// 지금은 예시 코드만
	const croplist = ref([])

	const getCropList = async function () {
		await axios({
			method : "GET",
			url: '/url/test',
			params: {
				id: id
			}
		}) .then((response) => {
			 croplist.value = response.data
		})
		
	}

	const cropInfo = reactive([
    { crop_name: "작물명1", area_ratio: 10.1 },
    { crop_name: "작물명2", area_ratio: 12.5 },
    { crop_name: "작물명3", area_ratio: 9.3 },
    { crop_name: "작물명4", area_ratio: 7.4 },
    { crop_name: "작물명5", area_ratio: 4.4 },
]);

// 나머지 비율을 계산
const remainingRatio = computed(() => {
	const totalUsedRatio = cropInfo.reduce((sum, crop) => sum + crop.area_ratio, 0);
	return 100 - totalUsedRatio;
});

// "기타" 항목 계산
const fullCropInfo = computed(() => {
	const other = { crop_name: "기타", area_ratio: remainingRatio.value };
	return [...cropInfo, other];
});

	return {
		croplist,
		cropInfo,
		fullCropInfo,
		getCropList
	}
})