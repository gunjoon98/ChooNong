<template>
	<div>
		<h1 class="container-title">작물 수익 계산기</h1>
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
								<input type="search" class="search-box">
								<button type="button" class="search-button">
									<img src="@/assets/search.png" alt="검색" class="search-icon">
								</button>
							</div>
							<div>
								<button type="button" class="sort-button">이름순</button>
								|
								<button type="button" class="sort-button">수익률순</button>
							</div>
						</div>
						<div class="crop-list">
							<ul class="table-header">
								<li>작물 사진</li>
								<li>작물명</li>
								<li>수익률</li>
								<li>추가하기</li>
							</ul>
							<ul v-for="(crop, index) in cropList" :key="index" class="table-body">
								<li><img src="@/assets/cropimage.png" /></li>
								<li>{{ crop.cropName }}</li>
								<li>{{ crop.profitRate }}</li>
								<li>
									<button type="button" class="add-button" @click="addCrop(crop)">추가</button>
								</li>
							</ul>
							<!-- <ul>
								<li><img src="" />@작물 사진@</li>
								<li>작물1</li>
								<li>수익률</li>
								<li>
									<button type="button" class="add-button">추가</button>
								</li>
							</ul> -->
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
									<div>
										<div>
											<InputText v-model.number="crop.cropExtentRatio" />%&nbsp&nbsp
										</div>
										<div>({{ totalExtentP*crop.cropExtentRatio/100 }}평={{ totalExtentM*crop.cropExtentRatio/100 }}㎡)</div>
									</div>
								</div>
								<Slider v-model="crop.cropExtentRatio" :min="0" :max="100" :step="5" />
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<button type="button" class="result-button" @click="showResult(addedCropList)">
		결과보기</button>
	</div>
</template>

<script setup>
import { watch, ref } from "vue";
import { useRouter } from "vue-router";
import { useCalculatorStore } from "@/stores/calculatorStore";
import Slider from 'primevue/slider';
import InputText from 'primevue/inputtext';

const router = useRouter();

const totalExtentM = ref();
const totalExtentP = ref();

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

const cropList = [
	{
		cropId: 1,
		cropName: "가지",
		administrationCost: 10000,
		sales: 40000,
		profitRate: 0.75,
	},
	{
		cropId: 2,
		cropName: "미나리",
		administrationCost: 12000,
		sales: 36000,
		profitRate: 0.67,
	},
	{
		cropId: 3,
		cropName: "사과",
		administrationCost: 5000,
		sales: 30000,
		profitRate: 0.83,
	},
	{
		cropId: 4,
		cropName: "오이",
		administrationCost: 10000,
		sales: 40000,
		profitRate: 0.75,
	},
	{
		cropId: 5,
		cropName: "콩",
		administrationCost: 12000,
		sales: 36000,
		profitRate: 0.67,
	},
	{
		cropId: 6,
		cropName: "토마토",
		administrationCost: 5000,
		sales: 30000,
		profitRate: 0.83,
	}
]

const addedCropList = ref([]);
const addCrop = function (crop) {
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

const showResult = async function (addedCropList) {
	const calculatorStore = useCalculatorStore();
	await calculatorStore.setTotalExtent(totalExtentP);
	await calculatorStore.setAddedCropList(addedCropList);
	router.push({ name: 'calculatorResult' })
}

// onMounted

</script>

<style scoped>
.container-title {
	text-align: center;
}

.calculator-container {
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.extent-input-wrapper > * {
	display: inline-block;
}

.extent-input-box {
	margin-left: 21px;
	margin-right: 5px;
	width: 150px;
	height: 30px;
}

.change-unit-button {
	border: none;
	background-color: transparent;
	width: 40px;
	height: 40px;
	margin-left: 15px;
	cursor: pointer;
	position: relative;
	top: 5px;
}

.change-unit-button img {
	width: 25px;
	height: 25px;
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
	margin-right: 10px;
	width: 150px;
	height: 30px;
}

.search-button {
	background: none;
	border: none;
	cursor: pointer;
	padding: 0;
}

.search-icon {
	width: 25px;
	/* 이미지의 크기 설정 */
	height: 25px;
	vertical-align: middle;
	/* 아이콘을 버튼의 중앙에 정렬 */
}

.sort-button {
	border: none;
	background-color: transparent;
}

.crop-list-container,
.added-crop-list-container {
	border: 2px solid #e9e9e9;
	border-radius: 15px;
	padding: 20px;
	width: 450px;
	height: 500px;
	overflow-y: auto;
	overflow-x: hidden;
}

.crop-list-container::-webkit-scrollbar,
.added-crop-list-container::-webkit-scrollbar {
	display: none;
	/* Chrome */
}

.crop-list-container {
	-ms-overflow-style: none;
	/* IE, Edge */
	scrollbar-width: none;
	/* Firefox */
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

.table-header li,
.table-body li {
	width: 25%;
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
	max-width: 70px;
	/* 이미지 크기 제한 */
}

.add-button {
	border: none;
	border-radius: 15px;
	background-color: #C6EB74;
	width: 45px;
	height: 30px;
}

.arrow-wrapper {
	position: relative;
	top: 200px;
}

.added-crop-list-container {}

.added-crop-list-container ul {
	list-style-type: none;
	padding: 0;
}

.added-crop-list-container ul li {
	border: 1px solid #C6EB74;
	border-radius: 15px;
	padding: 20px;
	width: 410px;
	position: relative;
}

.one-added-crop {
	height: 120px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.one-added-crop div{
	margin-bottom: 3px;
}

.delete-button {
	border: none;
	background-color: transparent;
	position: absolute;
	top: 5px;
	right: 5px;
}

.property-name {
	font-weight: bold;
	color: #333;
	margin-right: 5px; 
}

.added-crop-list-container ul li div {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.p-slider-horizontal {
	width: 80%;
	/* Set the desired width */
	position: relative;
	left: 10%;
}

.p-inputtext {
	width: 50px;
	height: 30px;
}

.result-button {
	display: block;
	margin: 20px auto;
	width: 100px;
	height: 40px;
	background-color: #C6EB74;
	border: none;
	border-radius: 15px;
	cursor: pointer;
	/* 커서 포인터로 변경 */
	transition: background-color 0.3s ease;
	/* 배경색 변화에 대한 전환 */
	color: #000000;
	text-decoration: none;
	text-align: center;
	line-height: 40px;
}
</style>