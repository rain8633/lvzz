<template>
  <div class="Statistics">
  <el-card class="box-card">
  <div>
    <h2>景区收藏统计</h2>
  </div>
  <div id="myChart" :style="{width: '700px', height: '700px'}"></div>
  </el-card>
  </div>
</template>

<script type="text/javascript">
export default {
  name:"Statistics",
  data() {
    return {

    }
  },
  components: {

  },
  mounted(){
  this.$http.post('/shouCang/tongji').then(res=>{

        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        myChart.setOption({
            backgroundColor: 'white',

    title: {
        text: '景区收藏信息统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series: [
        {
            name: '用户数量统计',
            type: 'pie',
            radius: '50%',
            data: res.data.data,
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
        });
     })
  },
}
</script>

<style scoped>
</style>
