<template>
  <div class="second-container">
    <h2>빅데이터 기반 맞춤형 귀농지 추천 서비스!</h2>
    <div class="explanation-wrapper">
      <p><img src="@/assets/star.png" class="mark-image"> 120만 개 이상의 데이터를 클러스터링하고 추천 알고리즘을 적용하여 최적의 귀농지를 추천해 드립니다.</p>
      <p><img src="@/assets/star.png" class="mark-image"> 토지 가격, 편의 시설, 교육 시설, 자연환경 등 다양한 요소를 종합적으로 고려하여 여러분을 위한 최고의 귀농지를 찾아드립니다.</p>
    </div>
    <div class="clustering-image-wrapper">
        <img src="@/assets/clustering.png" class="clustering-image"/>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.second-container {
  background-color: #fbfbef;
  width: 100%;
  height: 700px;
  padding: 50px 120px;
}

.explanation-wrapper {
  margin: 50px 0;
}

.mark-image {
  width: 20px;
  height: auto;
}

.clustering-image-wrapper {
  background-color: #ffffff;
  border-radius: 15px;
  border: 2px solid #e9e9e9;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.clustering-image {
  max-width: 1200px;
}
</style>
