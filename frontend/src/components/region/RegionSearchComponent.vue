<template>
  <div class="search-container" @keydown="onKeyDown">
    <input 
      class="search-input"
      :value="searchQuery"
      @input="handleInput"
      @keyup.enter="searchRegionDebounced"
      placeholder="검색..."
      @focus="onFocus"
      @blur="onBlur"
    />
    <img src="@/assets/search.png" @click="searchRegionDebounced" alt="검색" class="search-icon" />
    <ul v-if="dropdownVisible" class="dropdown">
      <template v-if="filteredData.length">
        <li 
          v-for="(item, index) in filteredData" 
          :key="index" 
          @click="logSelection(item)" 
          class="list"
          :class="{'list-divider': index < filteredData.length - 1, 'highlighted': selectedIndex === index}"
          :ref="`dropdownItem${index}`"
        >
          {{ item }}
        </li>
      </template>
      <li v-else class="list no-result">
        해당하는 지역은 없습니다.
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted, nextTick } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const data = [
    "가평군", "강릉시", "강진군", "강화군", "거제시", "거창군", "경산시", "경주시", "계룡시", "고령군", "고성군", "고창군", "고흥군", "곡성군",
    "공주시", "광양시", "광주시", "괴산군", "구례군", "구미시", "군산시", "군위군", "금산군", "기장군", "김제시", "김천시", "김포시",
    "김해시", "나주시", "남양주시", "남원시", "남해군", "논산시", "단양군", "달성군", "담양군", "당진시", "무안군", "무주군", "문경시",
    "밀양시", "보령시", "보성군", "보은군", "봉화군", "부안군", "부여군", "사천시", "산청군", "삼척시", "상주시", "서귀포시", "서산시",
    "서천군", "성주군", "세종시", "순창군", "순천시", "신안군", "아산시", "안동시", "안성시", "양구군", "양산시", "양양군", "양주시",
    "양평군", "여수시", "여주시", "연천군", "영광군", "영덕군", "영동군", "영암군", "영양군", "영월군", "영주시", "영천시", "예산군",
    "예천군", "옥천군", "옹진군", "완도군", "완주군", "용인시", "울릉군", "울주군", "울진군", "원주시", "음성군", "의령군", "의성군",
    "이천시", "익산시", "인제군", "임실군", "장성군", "장수군", "장흥군", "정선군", "정읍시", "제주시", "제천시", "증평군", "진도군",
    "진안군", "진주시", "진천군", "창녕군", "창원시", "천안시", "철원군", "청도군", "청송군", "청양군", "청주시", "춘천시", "충주시",
    "칠곡군", "태안군", "통영시", "파주시", "평창군", "평택시", "포천시", "포항시", "하동군", "함안군", "함양군", "함평군", "합천군",
    "해남군", "홍성군", "홍천군", "화성시", "화순군", "화천군", "횡성군"
]

const searchQuery = ref('');
const dropdownVisible = ref(false);
const inputFocused = ref(false);
const selectedIndex = ref(-1);

const filteredData = computed(() => {
  return searchQuery.value ? data.filter(item => item.includes(searchQuery.value)) : [];
});

const handleInput = (event) => {
  searchQuery.value = event.target.value;
  dropdownVisible.value = searchQuery.value.length > 0;
};

const logSelection = (item) => {
  searchQuery.value = item;
  dropdownVisible.value = false;
};

const searchRegion = () => {
  const index = data.findIndex(item => item === searchQuery.value);
  if (index === -1) {
    alert("정확히 일치하는 지역이 없어 검색을 수행할 수 없습니다.");
    return;
  }
  router.push({ name: 'regionDetail', params: { id: index + 1 } });
};

// 디바운싱
let debounceTimeout = null;
const searchRegionDebounced = () => {
  clearTimeout(debounceTimeout);
  debounceTimeout = setTimeout(() => {
    searchRegion(); // searchRegion 함수를 직접 호출하지 않고, 디바운싱을 통해 호출
  }, 300); // 사용자가 입력을 멈춘 후 300ms 후에 실행
};

const onFocus = () => {
  inputFocused.value = true;
};

const onBlur = () => {
  inputFocused.value = false;
  // 검색어가 비었을 경우 드롭다운 숨기기
  if (!searchQuery.value) {
    dropdownVisible.value = false;
  }
};

const onKeyDown = async (event) => {
  if (event.key === "ArrowDown") {
    event.preventDefault();
    selectedIndex.value = Math.min(selectedIndex.value + 1, filteredData.value.length - 1);
    await scrollToSelected();
  } else if (event.key === "ArrowUp") {
    event.preventDefault();
    selectedIndex.value = Math.max(selectedIndex.value - 1, 0);
    await scrollToSelected();
  } else if (event.key === "Enter" && selectedIndex.value !== -1) {
    logSelection(filteredData.value[selectedIndex.value]);
  }
};

const scrollToSelected = () => {
  return new Promise(resolve => {
    // DOM 업데이트를 기다립니다.
    nextTick(() => {
      const selectedItem = document.querySelector(`.highlighted`);
      if (selectedItem) {
        // 선택된 항목이 드롭다운 영역 안에서 보이도록 스크롤을 조정합니다.
        const dropdown = document.querySelector(".dropdown");
        dropdown.scrollTop = selectedItem.offsetTop - dropdown.offsetTop;
      }
      resolve();
    });
  });
};

watch(dropdownVisible, () => {
  selectedIndex.value = -1;
});

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
  top: 100%
}

.search-input {
  font-size: 1.5em;
  font-weight: bold;
  height: 50px;
  /* border: 1px solid #4BAF47;
  border-radius: 1rem; */
  padding-right: 60px;
  width: 100%;
  box-sizing: border-box;
  text-indent: 10px;
  outline: none; 
  /* border-color: var(--input-border-color, #4BAF47);  */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 3px solid #ECF6EC;
  border-radius: 1rem;
}

.search-input:focus {
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: px solid #4BAF47;
  border-radius: 1rem;
}

.list {
  padding: 10px;
  cursor: pointer;
  font-size: 1em;
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

.highlighted {
  background-color: #f0f0f0;
}
</style>
