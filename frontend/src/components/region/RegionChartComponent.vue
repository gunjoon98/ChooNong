<template>
    <div class="card">
        <canvas id="cropPieChart"></canvas>
    </div>
</template>

<script setup>
import { onMounted, ref, watch, defineProps } from 'vue';
import Chart from 'chart.js/auto';

const props = defineProps({
  regionDetail: Object
});
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
          callbacks: {
            label: function(context) {
              let label = context.label || '';
              if (label) {
                label += ': ';
              }
              if (context.parsed !== undefined) {
                label += context.parsed + '%';
              }
              return label;
            }
          }
        },
        legend: {
          labels: {
            font: {
              size: 14
            }
          }
        }
      }
    }
  });
};

watch(() => props.regionDetail, (newValue) => {
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
