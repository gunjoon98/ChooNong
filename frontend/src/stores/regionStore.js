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
			url: `${import.meta.env.VITE_API_URL}/region`,
		})
			.then((response) => {
				regionInfoList.value = response.data
			})
			.catch(err => console.log("지역 조회 오류"))
	}

	const getRegionDetail = async function (id) {
		await axios({
			method: 'GET',
			url: `${import.meta.env.VITE_API_URL}/region/${id}`,
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
				const response = await axios.get(`${import.meta.env.VITE_API_URL}/region/${id}`);
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

	const dummyRegionList = reactive([
		{
			region_id: 1,
			region_name: "대전",
			household: 1234,
			province: "충청남도",
			returners: 1234,
			area: 5678,
			homepage_url: "http://example.com",
			img_url: "http://example.com/image.png",
			suitability: 0.3 // 적합도 예시 값
		},
		{
			region_id: 2,
			region_name: "부산",
			household: 2345,
			province: "충청남도",
			returners: 2345,
			area: 6789,
			homepage_url: "http://example.com",
			img_url: "http://example.com/image.png",
			suitability: 0.7 // 적합도 예시 값
		},
		{
			region_id: 3,
			region_name: "광주",
			household: 3456,
			province: "충청남도",
			returners: 3456,
			area: 7890,
			homepage_url: "http://example.com",
			img_url: "http://example.com/image.png",
			suitability: 0.5 // 적합도 예시 값
		},
		{
			region_id: 4,
			region_name: "서울",
			household: 4567,
			province: "충청남도",
			returners: 4567,
			area: 8901,
			homepage_url: "http://example.com",
			img_url: "http://example.com/image.png",
			suitability: 0.85 // 적합도 예시 값
		},
		{
			region_id: 5,
			region_name: "인천",
			household: 5678,
			province: "충청남도",
			returners: 5678,
			area: 9012,
			homepage_url: "http://example.com",
			img_url: "http://example.com/image.png",
			suitability: 1 // 적합도 예시 값
		}
	]);

	// 21년 기준  출처: {http://www.youngnong.co.kr/news/articleView.html?idxno=39137}
	// const famousRegion = ['의성군', '고흥군', '상주시', '영천군', '양평군', '무안군', '김천시']//

	// 22년도 
	//시도별(시군별) · 성별 귀농가구원
	const famousRegion = ['상주군', '남양주시','김천시', '의성군', '고흥군', '영천시', '아산시', '양평군','제주시']

	return {
		regionInfoList,
		regionDetail,
		regionsDetailList,
		dummyRegionList,
		getRegionInfoList,
		getRegionDetail,
		getRegionsDetailList,
		famousRegion,
	}
})