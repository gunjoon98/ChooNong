<template>
  <div class="card">
    <canvas id="cropPieChart"></canvas>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import Chart from 'chart.js/auto';
import { useCropStore } from '@/stores/cropStore';

const cropStore = useCropStore();
const chartRef = ref(null);

onMounted(() => {
    const ctx = document.getElementById('cropPieChart').getContext('2d');
    chartRef.value = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: cropStore.fullCropInfo.map(info => info.crop_name),
            datasets: [{
                data: cropStore.fullCropInfo.map(info => info.area_ratio),
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
                borderWidth: 1
            }]
        },
        options: {
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            let label = '';
                            
                            if (context.parsed !== undefined) {
                                label += context.parsed + '%';
                            }
                            return label;
                        }
                    }
                }
            }
        },
    });
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
