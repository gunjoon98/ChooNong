<template>
  <div>
    <h1 class="container-title">작물 수익 계산 결과</h1>
    <div class="calculator-result-container">
      <div class="earnings-container">
        <img src="">@돈다발@
        <div>
          연간 예상 매출액<br>
          10,311,843원
        </div>
        -
        <div>
          연간 예상 경영비<br>
          5,789,051원
        </div>
        =
        <div>
          연간 예상 순수익<br>
          4,522,792원<br>
          (월 476,899원)
        </div>
      </div>
      <div class="total-extent-container">
        <img src="">@면적아이콘@
        <div>
          총 재배 면적<br>
          1000평<br>
          (3,300 m^2)
        </div>
      </div>
      <div class="crop-list-container">
        <img src="">@새싹아이콘@
        <div>
          재배 작물 목록
          <div>
            고랭지무<br>
            고랭지감자<br>
            고구마
          </div>
        </div>
      </div>
      <div class="extent-graph-container">
        작물별 재배 면적 그래프
        <div>
          <canvas id="myChart" width="400" height="400"></canvas>
          @파이 그래프@<br>
          면적(평), 비율(%) 표기
        </div>
      </div>
      <div class="earnings-graph-container">
        작물별 순수익 그래프
        <div>
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
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';

onMounted(() => {
  const ctx = document.getElementById('myChart');
  const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
      datasets: [{
        label: '# of Votes',
        data: [12, 19, 3, 5, 2, 3],
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
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
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
  border: 1px solid #C6EB74;
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