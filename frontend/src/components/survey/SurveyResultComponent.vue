<template>
  <div>
    <h1>설문결과</h1>
    <h3>{{ surveyStore.selectedAnswers }}</h3>
  </div>
</template>

<script setup>
import { useSurveyStore } from '@/stores/surveyStore';
const surveyStore = useSurveyStore()
</script>

<style scoped>
  
</style>