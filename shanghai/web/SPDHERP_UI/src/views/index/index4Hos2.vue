<!--首页的  demo 页面-->
<template>
<div id="homeIndx">
    <el-row :gutter="5">
        <el-col :span="12"> 
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames1">
                            <el-collapse-item title="月采购金额统计" name='1'>
                                <div style="height: 260px;">
                                    <monthPurchaseAmount></monthPurchaseAmount>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames2" >
                            <el-collapse-item title="近六个月库存金额统计" name='2'>
                                <div style="height: 260px;">
                                   <monthStockAmount></monthStockAmount>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames3" >
                            <el-collapse-item title="其他事项" name='3'>
                                <div style="height: 260px;">
                                    
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames4" >
                            <el-collapse-item title="紧急事项" name='4'>
                                <div style="height: 260px;">
                                    与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
        </el-col>

        <el-col :span="12">
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames5">
                            <el-collapse-item title="科室月环比同比统计" name='5'>
                                <div style="height: 260px;">
                                    <monthPurchaseAmountRatio></monthPurchaseAmountRatio>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames6" >
                            <el-collapse-item title="近六个月出库入库金额统计" name='6'>
                                <div style="height: 260px;">
                                    <monthOutAndInStockAmount></monthOutAndInStockAmount>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames7" >
                            <el-collapse-item title="监控管理" name='7'>
                                <div style="height: 260px;">
                                    与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
            <el-row :gutter="5">
                <el-col :span="24">
                        <el-collapse v-model="activeNames8" >
                            <el-collapse-item title="预警管理" name='8'>
                                <div style="height: 260px;">
                                    与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
   </div> 
</template>

<script>
    import monthPurchaseAmount from './chartMgr/monthPurchaseAmount';
    import monthPurchaseAmountRatio from './chartMgr/monthPurchaseAmountRatio';
    import monthStockAmount from './chartMgr/monthStockAmount';
    import monthOutAndInStockAmount from './chartMgr/monthOutAndInStockAmount';
    
    export default {
            data () {   
                return {
                    activeNames1: ['1'],
                    activeNames2: ['2'],
                    activeNames3: ['3'],
                    activeNames4: ['4'],
                    activeNames5: ['5'],
                    activeNames6: ['6'],
                    activeNames7: ['7'],
                    activeNames8: ['8']
                }
            },
            components: {
                index4Hos1,
                monthPurchaseAmount,
                monthPurchaseAmountRatio,
                monthStockAmount,
                monthOutAndInStockAmount
            },
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 5px;
        &:last-child {
        margin-bottom: 0;
    }
  }
  .el-col {
        border-radius: 4px;
}
</style>

<style>
    #homeIndx .el-collapse-item__header {
        height: 25px;
        line-height: 25px;
        padding-left: 15px;
        color: #48576a;
        cursor: pointer;
        font-size: 13px;
        background:url(../../assets/images/portal_tab_single.gif?V=3_50_2_29);
    }
</style>