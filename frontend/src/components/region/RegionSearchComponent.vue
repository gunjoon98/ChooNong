<template>
  <div class="search-container">
    <input 
      class="search-input"
      :value="searchQuery"
      @input="handleInput"
      placeholder="검색..."
      @focus="onFocus"
      @blur="onBlur"
    />
    <img src="@/assets/search.png" @click="searchRegion" alt="검색" class="search-icon" />
    <ul v-if="filteredData.length && dropdownVisible" class="dropdown">
      <li 
        v-for="(item, index) in filteredData" 
        :key="item" 
        @click="logSelection(item)" 
        class="list"
        :class="{'list-divider': index < filteredData.length - 1}"
      >
        {{ item }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const data = ['가평군', '강릉시', '홍성군', '홍천군', '화성시', '화순군', '화천군', '횡성군'];

const searchQuery = ref('');
const dropdownVisible = ref(false);
const inputFocused = ref(false);

const filteredData = computed(() => {
  return searchQuery.value ? data.filter(item => item.includes(searchQuery.value)) : [];
});

const handleInput = (event) => {
  searchQuery.value = event.target.value;
  dropdownVisible.value = true;
};

const logSelection = (item) => {
  console.log(item);
  searchQuery.value = item;
  dropdownVisible.value = false;
};

const searchRegion = () => {
  console.log("검색어:", searchQuery.value);
};

const onFocus = () => {
  inputFocused.value = true;
};

const onBlur = () => {
  inputFocused.value = false;
};
</script>

<style scoped>
.search-icon {
  margin: 5px;
  position: absolute;
  right: 5px;
  height: 40px;
  cursor: pointer;
}

.dropdown {
  position: absolute;
  z-index: 1000;
  background-color: white;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-height: 300px;
  overflow-y: auto;
  border-radius: 1rem;
  border: 1px solid #4BAF47;
  padding-left: 0;
  margin-top: 10px;
}

.search-input {
  font-size: 2em;
  font-weight: bold;
  height: 50px;
  border: 1px solid #4BAF47;
  border-radius: 1rem;
  padding-right: 60px;
  width: 100%;
  box-sizing: border-box;
  text-indent: 10px;
  outline: none; 
  border-color: var(--input-border-color, #4BAF47); 
}

.search-input:focus {
  border-color: var(--input-border-color-focused, #4BAF47); 
}

.list {
  padding: 10px;
  cursor: pointer;
  font-size: 1.5em;
  font-weight: bold;
  border-bottom: 1px solid #e0e0e0; 
}

.list:hover {
  background-color: #ECF6EC;
}

.list:last-child {
  border-bottom: none; 
}

.search-container {
  position: relative;
  width: 390px;
  margin: 0px;
}
</style>
