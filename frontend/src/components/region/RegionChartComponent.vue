<template>
  <div class="card">
    <canvas id="cropPieChart"></canvas>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import Chart from 'chart.js/auto';
import { useRoute } from 'vue-router';
import { useRegionStore } from '@/stores/regionStore';

const route = useRoute();
const regionStore = useRegionStore();
const regionId = ref(route.params.id); // ref로 감싸서 반응성을 부여
const regionDetail = ref({});


watch(() => route.params.id, async (newId) => {
  regionId.value = newId; // 새로운 ID로 업데이트
  await regionStore.getRegionDetail(regionId.value);
  regionDetail.value = regionStore.regionDetail;
}, { immediate: true });


let myChart = null;

const initChart = (cropList) => {
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
          // Color for 'Others'
          'rgba(201, 203, 207, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
          // Border color for 'Others'
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
        label: function(context) {
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
        size: 0, // 제목 글꼴 크기
      },
      bodyFont: {
        size: 20, // 본문 글꼴 크기
        weight: 'normal'
      }
    },
    legend: {
      labels: {
        font: {
          size: 20, // 범례 글꼴 크기
          weight: 'bold'
        }
      }
    }
  }
}

  });
};

watch(() => regionDetail.value, (newValue) => {
  if (newValue && newValue.crop_list) {
    initChart([...newValue.crop_list]);  // Spread operator to create a shallow copy
  }
}, {
  immediate: true
});
</script>

<style scoped>
.card {
  max-width: 330px;
  margin: auto;
}

canvas {
  height: fit-content;
  width: fit-content;
  margin-bottom: 5px;
}
</style>