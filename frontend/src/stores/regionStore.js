import { computed, ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router';
import axios from 'axios'

export const useRegionStore = defineStore('region', () => {
	// 내부 값 다 변경
	// 지금은 예시 코드만
	const regionInfoList = ref([])
	const regionDetail = ref()
	const regionsDetailList = ref([])

	const getRegionInfoList = async function () {
		await axios({
			method: 'GET',
			url: `https://choonong.store/api/region`,
		})
			.then((response) => {
				regionInfoList.value = response.data
			})
			.catch(err => console.log("지역 조회 오류"))
	}

	const getRegionDetail = async function (id) {
		await axios({
			method: 'GET',
			url: `https://choonong.store/api/region/${id}`,
		})
			.then((response) => {
				regionDetail.value = response.data
				console.log(response.data)
			})
			.catch(err => console.log("지역 조회 오류"))
	}

	const getRegionsDetailList = async function(ids) {
		regionsDetailList.value = []; // 리스트를 먼저 비웁니다.
		try {
			for (const id of ids) {
				const response = await axios.get(`https://choonong.store/api/region/${id}`);
				if (response.data) {
					console.log(response.data)
					regionsDetailList.value.push(response.data);
				}
			}
		} catch (error) {
			console.error("지역 세부 정보를 가져오는 중 오류 발생", error);
			// 오류 처리, regionsDetailList를 기본 값으로 설정하거나 빈 배열로 유지할 수 있습니다.
		}
		return regionsDetailList; // 이것은 모든 가져온 지역 세부 정보를 포함하는 반응형 참조를 반환합니다.
	};

	// 21년 기준  출처: {http://www.youngnong.co.kr/news/articleView.html?idxno=39137}
	// const famousRegion = ['의성군', '고흥군', '상주시', '영천군', '양평군', '무안군', '김천시']//
	// 22년도 
	//시도별(시군별) · 성별 귀농가구원
	const famousRegion = ['상주군', '남양주시','김천시', '의성군', '고흥군', '영천시', '아산시', '양평군','제주시']

	return {
		regionInfoList,
		regionDetail,
		regionsDetailList,
		getRegionInfoList,
		getRegionDetail,
		getRegionsDetailList,
		famousRegion,
	}
})