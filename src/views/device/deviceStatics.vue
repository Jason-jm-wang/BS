<template>
  <div>
      <el-row :gutter="10">
          <el-col :span="12">
              <el-card>
                  <div style="width: 100%; height: 400px" id="line"></div>
              </el-card>
          </el-col>
          <el-col :span="12">
              <el-card>

              </el-card>
          </el-col>
      </el-row>
  </div>
</template>

<script>

import * as echarts from 'echarts'

const option1 = {
    title: {
        text: "设备信息折线图"
    },
    tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'value'
    },
    series: [

    ]
}

const option2 = {
    title: {
        text: "用户设备统计图",
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left'
    },
    series: [
        {
            name: '',
            type: 'pie',
            radius: '50%',
            data: [

            ],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0,0,0,0)'
                }
            }
        }
    ]
}

export default {
    name: "deviceStatics",
    data() {
        return {}
    },
    mounted() {
        // 折线图
        let lineDom = document.getElementById('line');
        let lineChart = echarts.init(lineDom);
        lineChart.setOption(option1);

        // 饼图
        let pieDom = document.getElementById('pie');
        let pieChart = echarts.init(pieDom);
        lineChart.setOption(option2);

    }
}
</script>

<style scoped>

</style>