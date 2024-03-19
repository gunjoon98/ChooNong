<template>
  <div>
    <h1 class="container-title">작물 수익 계산 결과</h1>
    <div class="calculator-result-container">
      <div class="earnings-container">
        <img src="">@돈다발@
        <div>
          연간 예상 매출액<br>
          {{ yearlySales }}원
        </div>
        -
        <div>
          연간 예상 경영비<br>
          {{ yearlyAdministrationCost }}원
        </div>
        =
        <div>
          연간 예상 순수익<br>
          {{ yearlyEarnings }}원<br>
          (월 {{ monthlyEarnings }}원)
        </div>
      </div>
      <div class="total-extent-container">
        <img src="">@면적아이콘@
        <div>
          총 재배 면적<br>
          {{ totalExtent }}평<br>
          ({{ totalExtent * 3.3 }} m^2)
        </div>
      </div>
      <div class="crop-list-container">
        <img src="">@새싹아이콘@
        <div>
          재배 작물 목록
          <div>
            <ul>
              <li v-for="(crop, index) in addedCropList" :key="index">
                {{ crop.cropName }}
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="extent-graph-container">
        작물별 재배 면적 그래프
        <div>
          <canvas id="myChart1" width="400" height="400"></canvas>
          @파이 그래프@<br>
          면적(평), 비율(%) 표기
        </div>
      </div>
      <div class="earnings-graph-container">
        작물별 순수익 그래프
        <div>
          <canvas id="myChart2" width="400" height="400"></canvas>
          @파이 그래프@<br>
          연 순수익(원), 비율(%) 표기
        </div>
      </div>
      <div class="earnings-graph-container">
        작물별 순수익 그래프
        <div>
          <canvas id="myChart3" width="400" height="400"></canvas>
          @파이 그래프@<br>
          연 순수익(원), 비율(%) 표기
        </div>
      </div>
    </div>
    <router-link to="/calculator">
      <button type="button" class="retry-button">다시하기</button>
    </router-link>
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { useCalculatorStore } from '@/stores/calculatorStore';
import Chart from 'chart.js/auto';

const calculatorStore = useCalculatorStore();
const addedCropList = calculatorStore.addedCropList;
const totalExtent = calculatorStore.totalExtent;
const cropNameList = addedCropList.map(item => item.cropName);
const cropYearlySalesList = ref([]);
const cropYearlyAdministrationCostList = ref([]);
const cropYearlyEarningsList = ref([]);
const yearlyAdministrationCost = ref(0);
const yearlySales = ref(0);
const yearlyEarnings = ref(0);
const monthlyEarnings = ref(0);

const calculateYearlyAdministrationCost = function () {
  // let cost = 0;
  for (let crop of addedCropList) {
    const cropYearlyAdministrationCost = (totalExtent.value * crop.cropExtentRatio / 100 * crop.administrationCost);
    yearlyAdministrationCost.value += cropYearlyAdministrationCost;
    cropYearlyAdministrationCostList.value.push(cropYearlyAdministrationCost)
    // cropCalculateResultList.value.push({...crop, cropYearlyAdministrationCost})
  }
  // return yearlyAdministrationCost;
}

const calculateYearlySales = function () {
  // let sales = 0;
  console.log(addedCropList)
  for (let crop of addedCropList) {
    const cropYearlySales = (totalExtent.value * crop.cropExtentRatio / 100 * crop.sales);
    yearlySales.value += cropYearlySales
    cropYearlySalesList.value.push(cropYearlySales)
    // console.log(cropYearlySalesList.value)
    // cropCalculateResultList.value.push({...crop, cropYearlySales})
    // console.log("작물판매금액" + cropCalculateResultList.value[0].cropYearlySales)
  }
  // return yearlySales;
}

const calculateYearlyEarnings = function () {
  yearlyEarnings.value = yearlySales.value - yearlyAdministrationCost.value;
  // return yearlyEarnings
}

const calculateMonthlyEarnings = function () {
  monthlyEarnings.value = yearlyEarnings.value / 12;
}

// // 데이터 변경을 감지하고 배열을 업데이트하는 watchEffect 함수 사용
// watchEffect(() => {
//   // cropNameList.value = cropCalculateResultList.value.map(item => item.cropName);
//   // cropYearlySalesList.value = cropCalculateResultList.value.map(item => item.cropYearlySales);
//   // cropYearlyAdministrationCostList.value = cropCalculateResultList.value.map(item => item.cropYearlyAdministrationCost);
//   // cropYearlyEarningsList.value = cropYearlySalesList.value.map((sales, index) => sales - cropYearlyAdministrationCostList.value[index]);
// });
// const yearlyEarnings = calculateYearlyEarnings();
// const monthlyEarnings = calculateMonthlyEarnings();

onMounted(() => {
  console.log(yearlyEarnings)
  calculateYearlyAdministrationCost();
  calculateYearlySales();
  calculateYearlyEarnings();
  calculateMonthlyEarnings();
  console.log(yearlyEarnings)

  cropYearlyEarningsList.value = cropYearlySalesList.value.map((sales, index) => sales - cropYearlyAdministrationCostList.value[index]);
  console.log(cropYearlyEarningsList.value)

  const cropExtentList = addedCropList.map(item => item.cropExtentRatio);

  const chartData1 = ref({
  labels: cropNameList,
  datasets: [
    {
      label: '선택 작물별 재배 면적 비율',
      data: cropExtentList,
      backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)'
        ],
        borderWidth: 1,
    },
  ]
});
  
  const ctx1 = document.getElementById('myChart1').getContext('2d');
  const chartInstance1 = new Chart(ctx1, {
    type: 'pie',
    data: chartData1.value,
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

  const chartData2 = ref({
  labels: cropNameList,
  datasets: [
    {
      label: '선택 작물별 순수익 비율',
      data: cropYearlyEarningsList,
      backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)'
        ],
        borderWidth: 1,
    },
  ]
});
  
  const ctx2 = document.getElementById('myChart2').getContext('2d');
  const chartInstance2 = new Chart(ctx2, {
    type: 'pie',
    data: chartData2.value,
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

  // const cropNameList = cropCalculateResultList.value.map(item => item.cropName);
  // console.log(cropNameList)

  // // cropCalculateResultList 배열에서 cropYearlySales 값만 추출하여 새로운 배열 생성
  // const cropYearlySalesList = cropCalculateResultList.value.map(item => item.cropYearlySales);

  // // cropCalculateResultList 배열에서 cropYearlyAdministrationCost 값만 추출하여 새로운 배열 생성
  // const cropYearlyAdministrationCostList = cropCalculateResultList.value.map(item => item.cropYearlyAdministrationCost);

  // cropYearlySalesArray와 cropYearlyAdministrationCostArray를 사용하여 cropYearlyEarnings 배열 생성
  

const chartData3 = ref({
  labels: cropNameList,
  datasets: [
    {
      label: '연간 예상 매출액',
      backgroundColor: 'rgba(255, 99, 132, 0.2)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1,
      data: cropYearlySalesList,
    },
    {
      label: '연간 예상 경영비',
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1,
      data: cropYearlyAdministrationCostList,
    },
    {
      label: '연간 에상 수익률',
      backgroundColor: 'rgba(255, 218, 128, 0.2)',
      borderColor: 'rgba(255, 218, 128, 1)',
      borderWidth: 1,
      data: cropYearlyEarningsList,
    }
  ]
});
  
  const ctx3 = document.getElementById('myChart3').getContext('2d');
  const chartInstance3 = new Chart(ctx3, {
    type: 'bar',
    data: chartData3.value,
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
});

</script>

<style scoped>
.container-title {
  text-align: center;
}

.calculator-result-container {
  border: 1px solid #C6EB74;
  padding: 20px;
}

.earnings-container {
  border: 1px solid #fcf287;
  display: flex;
  flex-direction: row;
}

.total-extent-container,
.crop-list-container,
.extent-graph-container,
.earnings-graph-container {
  border: 1px solid #C6EB74;
}

.retry-button {
  display: block;
  margin: 20px auto;
}
</style>
