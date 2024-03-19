<template>
    <div class="card flex justify-content-center">
        <Chart type="radar" :data="chartData" :options="chartOptions" class="w-full md:w-30rem" />
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useCheckStore } from '@/stores/checkStore';
import Chart from 'primevue/chart';

const checkStore = useCheckStore();

onMounted(() => {
    chartData.value = setChartData();
    chartOptions.value = setChartOptions();
    category.value = Object.keys(checkStore.categoryWeights);
    weight.value = Object.values(checkStore.categoryWeights);
});

const chartData = ref();
const chartOptions = ref();
const category = ref()
const weight = ref()

const setChartData = function () {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const allColor = documentStyle.getPropertyValue('--green-400')

    return {
        labels: category,
        datasets: [
            {
                label: '',
                // borderColor: documentStyle.getPropertyValue('--bluegray-400'),
                borderColor: allColor,
                pointBackgroundColor: documentStyle.getPropertyValue('--bluegray-400'),
                pointBorderColor: documentStyle.getPropertyValue('--bluegray-400'),
                pointHoverBackgroundColor: textColor,
                pointHoverBorderColor: documentStyle.getPropertyValue('--bluegray-400'),
                data: weight,
                backgroundColor: 'rgba(76, 175, 80, 0.4)' // r,g,b, 투명도
            },
        ]
    };
};

const setChartOptions = function () {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--blue-900');
    const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');

    return {
        plugins: {
            legend: {
                // labels: {
                //     color: textColor
                // },

                display: false, // 범례를 숨깁니다.

            }
        },
        scales: {
            r: {
                grid: {
                    color: textColorSecondary
                },
                suggestedMin: 0,
                suggestedMax: 100,
                ticks: {
                    // 여기서 stepSize를 20으로 설정합니다.
                    stepSize: 20,
                    backdropColor: 'transparent', // 배경색을 투명하게 설정하여 더 깔끔한 차트를 만듭니다.
                    color: textColor // 눈금의 텍스트 색상을 설정합니다.
                }
            }
        }
    };
}
</script>

<style scoped></style>