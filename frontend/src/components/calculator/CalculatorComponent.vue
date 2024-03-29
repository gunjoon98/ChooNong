<template>
	<div>
		<!-- <h1 class="container-title">작물 수익 계산기</h1> -->
		<h2 class="container-title">작물과 재배 면적을 입력하면 예상 수익을 계산해 줍니다.</h2>
		<div class="calculator-container">
			<div>
				<h2>1. 총 재배 면적</h2>
				<div class="extent-input-wrapper">
					<div v-if="unit == 'p'">
						<input type="number" class="extent-input-box" v-model="totalExtentP"> 평
						= {{ totalExtentM }} ㎡
					</div>
					<div v-if="unit == 'm'">
						<input type="number" class="extent-input-box" v-model="totalExtentM"> ㎡
						= {{ totalExtentP }} 평
					</div>
					<button type="button" @click="changeUnit()" class="change-unit-button"><img
							src="@/assets/change.png"></button>
				</div>
			</div>
			<div>
				<h2>2. 전체 작물 목록</h2>
				<div class="crop-container">
					<div class="crop-list-container">
						<div class="crop-list-header">
							<div>
								<input type="search" class="search-box" v-model="searchQuery" @input="searchCrop($event)"
									placeholder="작물 이름">
								<button type="button" class="search-button">
									<img src="@/assets/search.png" alt="검색" class="search-icon">
								</button>
							</div>
							<div>
								<button type="button" class="sort-button" @click="sortByCropName()">이름순</button>
								|
								<button type="button" class="sort-button" @click="sortByProfitRate()">수익률순</button>
							</div>
						</div>
						<div class="crop-list">
							<ul class="table-header">
								<li class="crop-image-column">작물 사진</li>
								<li class="table-column">작물명</li>
								<li class="profit-rate-column">수익률(%)</li>
								<li class="table-column">추가하기</li>
							</ul>
							<ul v-if="searchQuery === ''" v-for="(crop, index) in sortedCropList" :key="crop" class="table-body">
								<li class="crop-image-column"><img :src="crop.imageUrl"/></li>
								<li class="table-column">{{ crop.cropName }}</li>
								<li class="profit-rate-column">{{ crop.profitRate }}</li>
								<li class="table-column">
									<button type="button" class="add-button" @click="addCrop(crop)">추가</button>
								</li>
							</ul>
							<ul v-else v-for="(filteredCrop, index) in filteredCropList" :key="filteredCrop" class="table-body">
								<li class="crop-image-column"><img :src="filteredCrop.imageUrl"/></li>
								<li class="table-column">{{ filteredCrop.cropName }}</li>
								<li class="profit-rate-column">{{ filteredCrop.profitRate }}</li>
								<li class="table-column">
									<button type="button" class="add-button" @click="addCrop(crop)">추가</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="arrow-wrapper">
						<h1>-></h1>
					</div>
					<div class="added-crop-list-container">
						<ul v-for="(crop, index) in addedCropList" :key="index">
							<li class="one-added-crop">
								<button type="button" class="delete-button" @click="deleteCrop(index)">X</button>
								<div>
									<div class="property-name">작물</div>
									<div>{{ crop.cropName }}</div>
								</div>
								<div>
									<div class="property-name">수익률</div>
									<div>{{ crop.profitRate }}</div>
								</div>
								<div>
									<div class="property-name">재배 면적 비율</div>
									<div class="crop-extent">({{ totalExtentP * crop.cropExtentRatio / 100 }}평={{ totalExtentM *
						crop.cropExtentRatio / 100 }}㎡)
									</div>
								</div>
								<div class="crop-extent-wrapper">
									<div class="slider-wrapper">
										<Slider v-model="crop.cropExtentRatio" :min="0" :max="100" :step="5" />
									</div>
									<InputText v-model.number="crop.cropExtentRatio" />%
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<button type="button" class="result-button" @click="showResult()">결과보기</button>
	</div>
</template>

<script setup>
import { watch, ref, computed, onMounted, watchEffect } from "vue";
import { useRouter } from "vue-router";
import { useCalculatorStore } from "@/stores/calculatorStore";
import Slider from 'primevue/slider';
import InputText from 'primevue/inputtext';

const calculatorStore = useCalculatorStore();
const router = useRouter();

const cropList = ref([])
const sortOption = ref("cropName");
const sortedCropList = ref([]);
const searchQuery = ref("");
const filteredCropList = ref([]);

const searchCrop = function (event) {
	searchQuery.value = event.target.value;
	// watchEffect(() => {
	// 	filteredCropList.value = sortedCropList.value.filter(item => item.cropName.includes(searchQuery.value))
	// });
	watch([sortedCropList, searchQuery], ([sortedCropListValue, searchQueryValue]) => {
  filteredCropList.value = sortedCropListValue.filter(item => item.cropName.includes(searchQueryValue));
});
	// filteredCropList.value = computed(() => {return sortedCropList.value.filter(item => item.cropName.includes(searchQuery.value))})
// 	filteredCropList.value = computed(() => {
//   return sortedCropList.value.filter(item => item.cropName.includes(searchQuery.value));
// });
}

const sortByCropName = function () {
	sortOption.value = "cropName";
	console.log(sortOption.value)
}

const sortByProfitRate = function () {
	sortOption.value = "profitRate";
	console.log(sortOption.value)
}

const sortCropList = function () {
	switch (sortOption.value) {
		case "cropName":
			sortedCropList.value = cropList.value.sort((a, b) => a.cropName.localeCompare(b.cropName));
			break;
		case "profitRate":
			sortedCropList.value = cropList.value.sort((a, b) => b.profitRate - a.profitRate);
			break;
	}
}

const totalExtentM = ref();
const totalExtentP = ref();

// watchEffect(() => {
// 	totalExtentM.value = Math.round(totalExtentP.value * 3.3);
// 	totalExtentP.value = Math.round(totalExtentM.value / 3.3)
// })

watch(totalExtentM, function (newValue) {
	totalExtentP.value = Math.round(newValue / 3.3);
})

watch(totalExtentP, function (newValue) {
	totalExtentM.value = Math.round(newValue * 3.3);
})

const unit = ref("p");

const changeUnit = function () {
	unit.value = unit.value === 'p' ? 'm' : 'p';
}



const addedCropList = ref([]);
const addCrop = function (crop) {
	if (totalExtentM.value == null || totalExtentM.value <= 0) {
		window.alert("총 재배 면적을 입력해주세요.")
		return;
	}

	const isAlreadyAdded = addedCropList.value.some(addedCrop => addedCrop.cropId === crop.cropId);

	if (!isAlreadyAdded) {
		addedCropList.value.push({ ...crop, cropExtentRatio: 0 });
		console.log(addedCropList)
	} else {
		window.alert("이미 추가된 작물입니다.")
	}
}

const deleteCrop = function (index) {
	addedCropList.value.splice(index, 1);
};

const showResult = async function () {
	if (addedCropList.value.length === 0) {
		window.alert("작물을 하나 이상 선택해 주세요.")
		return;
	}

	let sumOfCropExtentRatio = 0;
	for (let crop of addedCropList.value) {
		if (crop.cropExtentRatio <= 0) {
			window.alert("재배 면적이 0% 이하인 작물이 존재합니다.\n작물의 재배면적을 다시 설정해 주세요.");
			return;
		}
		sumOfCropExtentRatio += crop.cropExtentRatio
	}

	// console.log(sumOfCropExtentRatio);

	if (sumOfCropExtentRatio !== 100) {
		window.alert("작물 재배 면적의 합계가 100이 되도록 설정해주세요.")
		return;
	}

	await calculatorStore.setTotalExtent(totalExtentP);
	await calculatorStore.setAddedCropList(addedCropList.value);
	router.push({ name: 'calculatorResult' })
}

onMounted (async () => {
	await calculatorStore.getCropList();
	cropList.value = calculatorStore.cropList;
	console.log(cropList.value);
	watch(sortOption, sortCropList, { immediate: true })
})

</script>

<style scoped>
.container-title {
	text-align: center;
}

.calculator-container {
	/* border-radius: 15px; */
	padding: 45px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.extent-input-wrapper {
	margin-bottom: 50px;
	display: flex;
	align-items: center;
}

.extent-input-wrapper>* {
	display: inline-block;
}

.extent-input-box {
	padding-left: 15px;
	margin-left: 21px;
	margin-right: 5px;
	width: 200px;
	height: 45px;
	border-radius: 15px;
	font-size: 1.3rem;
}

.change-unit-button {
	border: none;
	background-color: transparent;
	width: 40px;
	height: 40px;
	margin: 0 0 5px 10px;
	line-height: 45px;
	cursor: pointer;
}

.change-unit-button img {
	width: 40px;
	height: 40px;
}

.crop-container {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.crop-list-header {
	margin-bottom: 10px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.search-box {
	font-size: 1.3rem;
	padding-left: 15px;
	margin-right: 10px;
	width: 200px;
	height: 45px;
	border-radius: 15px;
}

.search-box::placeholder {
	font-size: 1.3rem;
	margin-top: 5px;
}

.search-button {
	background: none;
	border: none;
	cursor: pointer;
	padding: 0;
}

.search-icon {
	width: 40px;
	/* 이미지의 크기 설정 */
	height: 40px;
	vertical-align: middle;
	/* 아이콘을 버튼의 중앙에 정렬 */
}

.sort-button {
	border: none;
	background-color: transparent;
	cursor: pointer;
	line-height: 45px;
}

.crop-list-container,
.added-crop-list-container {
	border: 2px solid #e9e9e9;
	border-radius: 15px;
	width: 520px;
	height: 500px;
	overflow-y: auto;
	overflow-x: hidden;
	scrollbar-width: thin;
  scrollbar-color: #dddddd transparent; /* 스크롤바 색상 설정 */
}
/* 스크롤바 화살표 숨기기 */
.crop-list-container::-webkit-scrollbar,
.added-crop-list-container::-webkit-scrollbar {
  width: 8px; /* 스크롤바 너비 */
}

.crop-list-container::-webkit-scrollbar-track,
.added-crop-list-container::-webkit-scrollbar-track {
  background: transparent; /* 스크롤바 트랙 배경색 */
}

.crop-list-container::-webkit-scrollbar-thumb,
.added-crop-list-container::-webkit-scrollbar-thumb {
  background-color: #dbdbdb; /* 스크롤바 색상 */
  border-radius: 20px; /* 스크롤바 모서리 반경 */
  border: 2px solid transparent; /* 스크롤바 경계선 */
}

.crop-list-container::-webkit-scrollbar-button,
.crop-list-container::-webkit-scrollbar-button {
  display: none; /* 위 아래 화살표 숨김 */
}

.crop-list-container {
	padding: 20px 15px;
}

.crop-list {
	display: table;
	table-layout: fixed;
	width: 100%;
}

.table-header {
	background-color: #ECF6EC;
	/* 배경색 지정 */
	color: #333;
	/* 텍스트 색상 지정 */
	font-weight: bold;
	/* 글꼴 굵기 설정 */
	padding: 8px;
	/* 셀 안쪽 여백 지정 */
	/* border-bottom: 1px solid #ddd; */
	/* 아래쪽 테두리 추가 */
}

.crop-list ul {
	list-style-type: none;
	display: table-row;
}

.crop-image-column {
	width: 36%;
	padding: 5px;
	text-align: center;
	/* border-bottom: 1px solid #cacaca; */
	display: table-cell;
	vertical-align: middle;
}

.profit-rate-column {
	width: 18%;
	/* 각 셀의 너비를 25%로 설정 */
	padding: 5px;
	text-align: center;
	/* border-bottom: 1px solid #cacaca; */
	display: table-cell;
	vertical-align: middle;
}

.table-column {
	width: 23%;
	/* 각 셀의 너비를 25%로 설정 */
	padding: 5px;
	text-align: center;
	/* border-bottom: 1px solid #cacaca; */
	display: table-cell;
	vertical-align: middle;
}

.table-body li {
	border-bottom: 1px solid #cacaca;
}

.crop-list ul li img {
	max-width: 160px;
	/* 이미지 크기 제한 */
}

.add-button {
	border: none;
	border-radius: 15px;
	background-color: #C6EB74;
	width: 60px;
	height: 40px;
	cursor: pointer;
}

.arrow-wrapper {
	height: 50px;
	position: relative;
	top: 200px;
}

.added-crop-list-container ul {
	list-style-type: none;
	padding: 0;
}

.one-added-crop {
	border: 1px solid #C6EB74;
	border-radius: 15px;
	padding: 5px 30px 0px 30px;
	margin: 0 auto;
	width: 488px;
	height: 210px;
	margin: 0 15px;
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.one-added-crop div {
	margin-top: 3px;
}

.delete-button {
	border: none;
	background-color: transparent;
	position: absolute;
	top: 7px;
	right: 7px;
}

.property-name {
	font-weight: bold;
	color: #333;
	margin-right: 5px;
	text-decoration: underline;
	text-decoration-color: rgba(198, 235, 116, 0.5);
	;
	text-decoration-thickness: 5px;
	text-underline-offset: -5px;
}

.added-crop-list-container ul li div {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.slider-wrapper {
	padding: 13px 0 15px 0;
}

.p-slider-horizontal {
	width: 250px;
	/* Set the desired width */
	margin-right: 60px;
	position: relative;
	left: 10%;
}

.crop-extent-wrapper {
	display: flex;
	flex-direction: row;
	/* justify-content: right; */
}

.crop-extent {
	margin-left: 10px;
}

.p-inputtext {
	width: 65px;
	height: 40px;
	font-size: 1.3rem;
}

.result-button {
	display: block;
	margin: 20px auto;
	width: 110px;
	height: 50px;
	background-color: #C6EB74;
	border: none;
	border-radius: 15px;
	cursor: pointer;
	/* 커서 포인터로 변경 */
	/* transition: background-color 0.3s ease; */
	/* 배경색 변화에 대한 전환 */
	/* color: #000000; */
	/* text-decoration: none; */
	text-align: center;
	line-height: 40px;
}
</style>
