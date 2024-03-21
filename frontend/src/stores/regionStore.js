import { computed, ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router';
import axios from 'axios'

export const useRegionStore = defineStore('region', () => {
	// 내부 값 다 변경
	// 지금은 예시 코드만
	const regionInfoList = ref([])
	const policyList = ref([])

	const getRegionInfoList = function (regionId) {
		axios({
			method : 'GET',
			url : '/api1/region',
			params: {
				regionId: regionId
			}
		}) 
		.then((response) => {
			regionInfoList.value = response.data
		}) 
		.catch(err => console.log("지역 조회 오류"))
	}

	const getPolicyList = function () {
		axios({
			method : 'GET',
			url : '/api1/policy',
			params: {
				regionId: regionId
			}
		}) 
		.then((response) => {
			policyList.value = response.data
		}) 
		.catch(err => console.log("정책 조회 오류"))
	}

	const dummyRegion = reactive([
		{
			// 고유 식별자로, 일반적으로 데이터베이스에서 자동으로 증가합니다.
			region_id: 1, 
		
			// 지역명입니다. 해당 지역의 이름을 나타냅니다.
			region_name: "대전", 
		
			// 세대 수입니다. 해당 지역의 총 가구 수를 나타냅니다.
			household: 1234, 
		
			// 도(省)명입니다. 해당 지역이 속한 도의 이름을 나타냅니다.
			province: "가상 도명", 
		
			// 귀농인 수입니다. 해당 지역으로 돌아온 사람들의 수를 나타냅니다.
			// 이 값은 문자열이나 숫자가 될 수 있으며, 여기서는 예시로 문자열을 사용했습니다.
			returners: 1234, 
		
			// 면적입니다. 해당 지역의 총 면적을 제곱미터 단위 등으로 나타냅니다.
			area: 5678, 
		
			// 홈페이지 URL입니다. 해당 지역의 공식 홈페이지 주소입니다.
			homepage_url: "http://example.com", 
		
			// 이미지 URL입니다. 해당 지역을 나타내는 이미지의 인터넷 주소입니다.
			img_url: "http://example.com/image.png" 
		},
	])

	const dummyPolicy = reactive([
		{ id: 1, business_name: '세부사업명1', target_eligibility: '지원대상1', business_content: '사업내용1', conditions : '지원조건1', evidence: '증빙서류1', reception: '문의처1' },
		{ id: 2, business_name: '세부사업명2', target_eligibility: '지원대상2', business_content: '사업내용2', conditions : '지원조건2', evidence: '증빙서류2', reception: '문의처2' },
		{ id: 3, business_name: '세부사업명3', target_eligibility: '지원대상3', business_content: '사업내용3', conditions : '지원조건3', evidence: '증빙서류3', reception: '문의처3' },
		{ id: 4, business_name: '세부사업명4', target_eligibility: '지원대상4', business_content: '사업내용4', conditions : '지원조건4', evidence: '증빙서류4', reception: '문의처4' },
		{ id: 5, business_name: '세부사업명5', target_eligibility: '지원대상5', business_content: '사업내용5', conditions : '지원조건5', evidence: '증빙서류5', reception: '문의처5' },

	])

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

	return {
		regionInfoList,
		policyList,
		dummyRegion,
		dummyPolicy,
		dummyRegionList,
		getRegionInfoList,
		getPolicyList,
	}
})