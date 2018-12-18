<!--最近六个月出入库金额情况统计-->
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
        name:"monthOutAndInStockAmount",
        data() {
            return {
                echartLoading:false,
                optionBar : {
                    /*title: {
                        text: '',
                        subtext: '纯属虚构'
                    },*/
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['出库金额（元）','入库金额（元）']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            
                            dataView : {show: false, readOnly: false},
                            magicType : {show: false, type: ['line', 'bar']},
                            restore : {show: false},
                            saveAsImage : {show: false}
                        }
                    },
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: ['一月','二月','三月','四月','五月','六月']
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                           // formatter: '{value} 元'
                        }
                    },
                    series: [
                        {
                            name:'出库金额（元）',
                            type:'line',
                            data:[111.2, 115.2, 159, 135, 172, 132, 109],
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                        {
                            name:'入库金额（元）',
                            type:'line',
                            data:[124, 135, 122, 151, 130, 201, 108],
                            markPoint: {
                                data: [
                                    {name: '月最低', value: -2, xAxis: 1, yAxis: -1.5}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'},
                                    [{
                                        symbol: 'none',
                                        x: '90%',
                                        yAxis: 'max'
                                    }, {
                                        symbol: 'circle',
                                        label: {
                                            normal: {
                                                position: 'start',
                                                formatter: '最大值'
                                            }
                                        },
                                        type: 'max',
                                        name: '最高点'
                                    }]
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