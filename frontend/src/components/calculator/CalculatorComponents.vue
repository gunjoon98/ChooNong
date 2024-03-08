<template>
	<div>
		<h1 class="container-title">작물 수익 계산기</h1>
		<div class="calculator-container">
			<div>
				<h2>1. 총 재배 면적</h2>
				<input type="number"> 평 또는 m^2
			</div>
			<div>
				<h2>2. 전체 작물 목록</h2>
				<div class="crop-container">
					<div class="crop-list-container">
						<div class="crop-list-header">
							<div>
								<input type="search">
								<button type="button">검색</button>
							</div>
							<div>
								<button type="button">이름순</button>
								|
								<button type="button">수익률순</button>
							</div>
						</div>
						<div class="crop-list">
							<ul>
								<li>작물 사진</li>
								<li>작물명</li>
								<li>수익률</li>
								<li>추가하기</li>
							</ul>
							<ul>
								<li><img src="" />@작물 사진@</li>
								<li>작물1</li>
								<li>수익률</li>
								<li class="add-crop">
									<button type="button" class="add-button">+</button>
								</li>
							</ul>
						</div>
					</div>
					<div>
						<h1>-></h1>
					</div>
					<div class="added-crop-list-container">
						<ul>
							<li>
								<button type="button" class="delete-button">x</button>
								<div>
									<div>작물<br>토마토</div>
									<div>수익률<br>100,000원</div>
								</div>
								<div>
									<div>재배 면적 비율</div>
									<Slider v-model="cropExtentValue" :min="0" :max="100" :step="1" />
									<div>
									<InputText v-model.number="cropExtentValue" />%
									<div>(100m^2)</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<router-link to="/calculator/result">
			<button type="button" class="result-button">결과보기</button>
		</router-link>
	</div>
</template>

<script setup>
import { ref } from "vue";
// import { useCropStore } from "@/stores/cropStore";
import Slider from 'primevue/slider';
import InputText from 'primevue/inputtext';

const cropExtentValue = ref();

</script>

<style scoped>
.container-title {
	text-align: center;
}

.calculator-container {
	border: 1px solid #4BAF47;
	padding: 20px;
}

.crop-container {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.crop-list-header {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.crop-list-container,
.added-crop-list-container {
	border: 1px solid #C6EB74;
	padding: 10px;
}

.crop-list {
	display: table;
	table-layout: fixed;
}

.crop-list ul {
	list-style-type: none;
	display: table-row;
}

.crop-list ul li {
	display: table-cell;
}

.crop-list ul li img {
	max-width: 100px;
	/* 이미지 크기 제한 */
}

.add-crop {
	text-align: center;
}

.added-crop-list-container ul {
	list-style-type: none;
	padding: 0;
}

.added-crop-list-container ul li {
	border: 1px solid #C6EB74;
	padding-top: 20px;
	width: 450px;
	position: relative;
}

.delete-button {
	position: absolute;
	top: 5px;
	right: 5px;
}

.added-crop-list-container ul li div {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.p-slider-horizontal {
    width: 200px; /* Set the desired width */
}

.p-inputtext {
	width: 50px;
}

.result-button {
	display: block;
	margin: 20px auto;
}
</style>