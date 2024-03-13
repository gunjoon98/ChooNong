<template>
  <div>
    <input type="text" v-model="searchWord">
    <!-- 검색 이미지 버튼으로 변경, 클릭 시 find 함수 실행 -->
    <img src="@/assets/search.png" @click="searchRegion" alt="검색" class="search-icon" />
  </div>
</template>

<script setup>
import { ref } from 'vue'

const searchWord = ref('')

// 검색을 수행하는 함수
const searchRegion = function () {
  console.log("검색어:", searchWord.value);
  // 검색 로직
}
</script>

<style scoped>
.search-icon {
	width: 25px;
	/* 이미지의 크기 설정 */
	height: 25px;
	vertical-align: middle;
	/* 아이콘을 버튼의 중앙에 정렬 */
}
</style>
