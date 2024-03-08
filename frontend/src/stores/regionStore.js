import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useRegionStore = defineStore('region', () => {
	// 내부 값 다 변경
	// 지금은 예시 코드만
	const regionlist = ref([])

	return {
		regionlist,
	}
})