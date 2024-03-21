<template>
  <div>
    <div>
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
          <div>지역 검색</div>
          <RegionSearchComponentVue/>
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
import RegionSearchComponentVue from '../region/RegionSearchComponent.vue';
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  overflow-x: hidden;
}

.content-container {
  width: 1200px;
  height: 900px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  margin-bottom: 2rem;
  background-color: #f0f0f0;
  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
  border-radius: 10px;
}
</style>
