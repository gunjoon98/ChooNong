import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

export const useCheckStore = defineStore('check', () => {
	// 내부 값 다 변경
	// 지금은 예시 코드만

	const checkList = ref([
		{
			category: '주거',
			check_title: "귀농 시 필요한 주거계획은 어느정도 진행이 되셨습니까?",
			options: ["계획없음", "부동산 정보 탐색", "주거 마련 계획 수집", "매매 계약 또는 건축", "이주 완료", "이주 3년 초과"]
		},
		{
			category: '농지',
			check_title: "귀농 경제활동을 위한 농지 확보는 어느 정도 진행이 되셨습니까?",
			options: ["계획없음", "부동산 정보 탐색", "농지 마련 계획 수립 / 정책지원금 신청 준비", "매매(임차) 계약체결 / 정책지원금 신청 완료", "농지확보", "경작 3년 초과"]
		},
		// {
		// 	check_title: "귀촌 경제활동을 위한 일자리 확보는 어느 정도 진행이 되셨습니까?",
		// 	options: ["계획없음", "일자리 탐색", "일자리 마련 계획 / 일자리 지원 서류 준비", "일자리 지원", "취업", "취업 3년 초과"]
		// },
		{
			category: '교육',
			check_title: "귀농 필요 교육은 어느 단계까지 수강하셨습니까?",
			options: ["계획없음", "귀농교육 정보 탐색 / 상담", "영농기술교육", "영농심화교육 / 체험", "판로개척교육 / 멘토링", "홍보교육 / 판로확대교육"]
		},
		// {
		// 	check_title: "귀촌 필요 교육은 어느 단계까지 수강하셨습니까?",
		// 	options: ["계획없음", "귀촌교육 정보 탐색 / 상담", "관심지역 문화 교육", "관심지역 답사"]
		// },
		{
			category: '자금',
			check_title: "귀농을 위한 자금확보 현황은 어떻게 되십니까?",
			options: ["계획없음", "자금 관련 정보 탐색", "자금 확보 계획 수립", "지원금 / 융자 신청 / 확보", "자금 집행", "자금관리 모니터링"]
		},
		{
			category: '생활',
			check_title: "귀농을 위한 생활 인프라(관공서, 보건복지 시설 등)와 생활여건(거리, 위치 등)에 대해 검토하셨습니까?",
			options: ["계획없음", "생활정보 탐색", "관심지역 생활여건 검토", "관심지역 답사", "이주", "마을공동체 활동"]
		},
		{
			category: '생활',
			check_title: "귀농과 관련해 가족간 협의를 어느정도 진행하셨습니까?",
			options: ["계획없음", "가족 협의 중", "가족 동의"]
		}
	]
	)

	const categoryWeights= ref({})

	const setCategoryWeights = function(newWeights) {
		categoryWeights.value = newWeights;
	}

	const getCropList = async function () {
		await axios({
			method: "GET",
			url: '/url/test',
			params: {
				id: id
			}
		}).then((response) => {
			croplist.value = response.data
		})

	}

	return {
		checkList,
		categoryWeights,
		setCategoryWeights,
		getCropList
	}
})