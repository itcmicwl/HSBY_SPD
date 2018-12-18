<!--科室月采购金额情况柱状图-->
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
//import echarts from 'echarts';
import IEcharts from 'vue-echarts-v3';
export default {
        data() {
            return {
                echartLoading:false,
                optionBar : {
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                         data:['月采购金额']
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
                            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'月采购金额',
                            type:'bar',
                            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
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