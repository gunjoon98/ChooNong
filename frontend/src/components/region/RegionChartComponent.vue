<template>
  <div>
    <h2 class="chart-title">지역에서 가장 많이 재배되는 작물 목록입니다.</h2>
    <div class="card">
      <canvas id="cropPieChart" width="500px" height="500px"></canvas>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import Chart from 'chart.js/auto';
import { useRoute } from 'vue-router';
import { useRegionStore } from '@/stores/regionStore';

const route = useRoute();
const regionStore = useRegionStore();
const regionId = ref(route.params.id); 
const regionDetail = ref({});

// 라우터의 params.id 변경시 업데이트
watch(() => route.params.id, async (newId) => {
  regionId.value = newId;
  await regionStore.getRegionDetail(regionId.value);
  regionDetail.value = regionStore.regionDetail;
}, { immediate: true });

// 지역 정보의 작물정보 업데이트
watch(() => regionDetail.value, (newValue) => {
  if (newValue && newValue.crop_list) {
    initChart([...newValue.crop_list]);
  }
}, {
  immediate: true
});

let myChart = null;

// 차트 만들기
const initChart = function(cropList) {
  const totalRate = cropList.reduce((acc, crop) => acc + crop.area_rate, 0);
  const otherRate = 100 - totalRate;

  const ctx = document.getElementById('cropPieChart').getContext('2d');
  if (myChart) {
    myChart.destroy();
  }
  myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: cropList.map(info => info.crop_name).concat(otherRate > 0 ? ['기타'] : []),
      datasets: [{
        data: cropList.map(info => info.area_rate).concat(otherRate > 0 ? [otherRate] : []),
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(201, 203, 207, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
          'rgba(201, 203, 207, 1)'
        ],
        borderWidth: 1
      }]
    },
    options: {
      plugins: {
        tooltip: {
          // 툴팁에 대한 콜백 함수 설정
          callbacks: {
            label: function (context) {
              let label = context.label || '';
              if (label) {
                label += ': ';
              }
              if (context.parsed !== undefined) {
                label += `${Math.ceil(context.parsed * 10) / 10}%`;
              }
              return label;
            }
          },
          // 툴팁의 글꼴 설정
          titleFont: {
            size: 0,
          },
          bodyFont: {
            size: 20,
            weight: 'normal'
          }
        },
        legend: {
          labels: {
            font: {
              size: 20,
              weight: 'bold'
            },
            padding: 20
          }
        },

      },
      layout: {
        padding: 20
      }
    }

  });
};


</script>

<style scoped>
.chart-title {
  text-align: center;
}

.card {
  max-width: 600px;
  margin: auto;
}

canvas {
  height: fit-content;
  width: fit-content;
  margin-bottom: 5px;
}
</style>