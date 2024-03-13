<template>
  <div>
    <div class="main-container">
      <div class="content-container">
        <img src="">@사진/아이콘@
        <div>귀농지 추천 설문 설명</div>
        <router-link to="/survey">
          <button type="button">시작하기</button>
        </router-link>
      </div>
      <div class="content-container">
        <img src="">@사진/아이콘@
        <div>작물 수익 계산기 설명</div>
        <router-link to="/calculator">
          <button type="button">시작하기</button>
        </router-link>
      </div>
      <div class="content-wrapper">
        <div class="content-container">
          <div>귀농지 검색하기</div>
          <input type="search">
          <button type="button">검색</button>
        </div>
        <div class="content-container">
          <div>귀농 준비 체크리스트</div>
          <router-link to="/checklist">
            <button type="button">시작하기</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
body {
  display: flex;
  justify-content: center;
}

.main-container {
  border: 1px solid #C6EB74;
  padding: 10px;
  height: 50vh;
  width: 100vw;
  /* 가로 전체 화면 */
  max-width: 1050px;
  /* 최대 너비 제한 */
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.content-wrapper {
  display: flex;
  flex-direction: column;
}

.content-container {
  border: 1px solid #C6EB74;
  margin: 10px;
  padding: 10px;
  flex: 1;
}
</style>