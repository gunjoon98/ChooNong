<template>
  <div class="typewriter">
    {{ displayText }}
    <span class="cursor">|</span>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const texts = [
  'Vue에서 타자 치듯이 글자가 나오는 텍스트 슬라이더 구현하기!',
  '이 코드는 여러 문장을 무한히 순환합니다.',
  '각 문장은 나타났다가 사라지는 효과를 가집니다.',
];
const displayText = ref('');
let currentIndex = 0;
let currentTextIndex = 0;
let isDeleting = false;

onMounted(() => {
  typingEffect();
});

const typingEffect = function() {
    const currentText = texts[currentTextIndex];
    if (!isDeleting) {
      displayText.value = currentText.substring(0, currentIndex++);
      if (currentIndex > currentText.length) {
        // 현재 문장이 끝나면 삭제를 시작하기 위해 대기
        setTimeout(() => {
          isDeleting = true;
        }, 200); // 0.1초 후에 삭제 시작
      }
    } else {
      displayText.value = currentText.substring(0, currentIndex--);
      if (currentIndex <= 0) {
        // 문장을 모두 삭제하면 다음 문장으로 넘어감
        isDeleting = false;
        currentTextIndex = (currentTextIndex + 1) % texts.length; // 루프를 위해 순환
      }
    }
    setTimeout(typingEffect, isDeleting ? 50 : 100); // 삭제하는 동안 타이핑 속도를 빠르게
  };
</script>

<style scoped>
.typewriter {
  font-family: 'Courier New', Courier, monospace;
  white-space: nowrap;
  overflow: hidden;
}

.cursor {
  animation: blink 0.75s step-start infinite;
}

@keyframes blink {
  50% {
    opacity: 0;
  }
}
</style>