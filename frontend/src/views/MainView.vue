<template>
    <div class="router-container">
      <RouterView class="router-view"/>
      <img src="@\assets\carrot.png" class="bottom-picture">
    </div>
</template>

<script setup>

</script>

<style scoped>
.router-container {
  position: relative;
}

.router-view {
  margin-bottom: 100px;
}

.bottom-picture {
  max-width: 120%;
  height: 80px;
  position: absolute;
  bottom: 0px;
  left: 50%;
  transform: translateX(-50%);
}
</style>