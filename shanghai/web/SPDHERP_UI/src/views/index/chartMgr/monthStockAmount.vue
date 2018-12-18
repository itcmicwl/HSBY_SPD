<!--最近六个月库房库存金额情况柱状图-->
<template>
    <el-row>
        <el-col :span="24">
            <el-card :body-style="{ padding: '0px' }" class="bigChart">
                <IEcharts :option="optionBar" :loading="echartLoading" style="width:100%;height:250px;"></IEcharts>
            </el-card>
        </el-col>
      </el-row>  
</template>
<script>
import IEcharts from 'vue-echarts-v3';
export default {
        name:"monthStockAmount",
        data() {
            return {
                echartLoading:false,
                optionBar : {
                    color: ['#FF6347'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                         data:['库存金额（元）']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: false, readOnly: false},
                            magicType : {show: false, type: ['line', 'bar']},
                            restore : {show: false},
                            saveAsImage : {show: false}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ['1月','2月','3月','4月','5月','6月']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'库存金额（元）',
                            type:'bar',
                            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                            markLine : {
                                data : [
                                    {type : 'average', name : '平均值'}
                                ]
                            }
                        }
                    ]
                }       
            }
        },
        components: {
            IEcharts
        },
        methods: {           
            setChartsOpt(){
                var _this= this;
            }
        },

        mounted: function () {
            this.setChartsOpt();
        }
    }
</script>

<style scoped>
.vue-echarts{
    width: 100%;
    height: 150px;
  }
  .bigChart{
      margin-top:10px;
  }
  .bigChart .vue-echarts{ 
    width: 100%;
    height: 250px;
  }
.card_header {
    padding: 8px 20px;
    text-align: center;
} 
</style>