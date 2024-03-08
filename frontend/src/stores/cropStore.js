import { ref, computed } from 'vue'
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

	return {
		croplist,
		getCropList
	}
})