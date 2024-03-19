<template>
  <div>
    <h1>체크리스트 페이지</h1>
    <div v-for="(check, cIndex) in checkStore.checkList" :key="cIndex" class="container">
      <div>{{ check.check_title }}</div>

      <div v-for="(option, oIndex) in check.options" :key="oIndex" class="option">
        <RadioButton v-model="checkedAnswers[cIndex]" :value="oIndex" />
        <div>{{ option }}</div>
        
      </div>
    </div>
    <button @click="goToResult">결과보기</button>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useCheckStore } from '@/stores/checkStore';
import { useRouter } from 'vue-router';
import RadioButton from 'primevue/radiobutton';

const checkStore = useCheckStore();
const router = useRouter();
const checkedAnswers = ref(checkStore.checkList.map(() => null));

const calculateWeight = function(answerIndex, optionCount) {
  if (answerIndex === null) return 0;
  return Math.round((answerIndex / (optionCount - 1)) * 100);
}

const categoryWeights = computed(() => {
  const weightSums = {};

  checkStore.checkList.forEach((check, cIndex) => {
    const category = check.category;
    const optionCount = check.options.length;
    const answerIndex = checkedAnswers.value[cIndex];
    const weight = calculateWeight(answerIndex, optionCount);

    if (!weightSums[category]) {
      weightSums[category] = 0;
    }
    weightSums[category] += weight;
  });

  // '생활' 카테고리의 가중치 합을 조정
  if (weightSums['생활'] !== undefined) {
    // 예를 들어, 총합이 200일 경우, 이를 100으로 조정하기 위해 2로 나눕니다.
    // 이 조정 비율은 필요에 따라 조정할 수 있습니다.
    weightSums['생활'] = Math.round(weightSums['생활'] / 2);
  }

  return weightSums;
});


watch(checkedAnswers, (newVal, oldVal) => {
  checkStore.setCategoryWeights(categoryWeights.value);
  console.log(checkStore.categoryWeights)
}, { deep: true });

const goToResult = function () {
  const isAllChecked = checkedAnswers.value.every(answer => answer !== null);

  if (isAllChecked) {
    router.push('/checklist/result');
  } else {
    alert('모든 문항에 답해주세요.');
  }
}

const allChecked = computed(() => {
  return checkedAnswers.value.every(answer => answer !== null);
});

</script>



<style scoped>
.container {
  border: 1px solid black;
}

.option {
  display: flex;
  align-items: center;
  margin: 5px;
}
.option > div {
  margin-right: 10px; /* 텍스트와 라디오 버튼 사이에 약간의 여백을 추가합니다. */
}
</style>