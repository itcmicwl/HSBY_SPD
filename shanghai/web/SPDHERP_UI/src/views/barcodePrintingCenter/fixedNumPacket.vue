<template>
    <div id="fixed-num-packet" style="height: 100%">
        <el-col :span="24" style="height: 49%">
                    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                        <el-form :inline="true" label-width="100px">
                            <el-form-item label="出库单号：">
                                <el-input v-model="filters.billId" placeholder="请输入出库单号" ></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" icon="search" v-on:click="getOutBillList(1)" >查询</el-button>
                            </el-form-item>
                        </el-form>
                    </el-col>
                    <!--列表-->
                    <template>
                        <el-table :data="outBillList" highlight-current-row v-loading="loading" style="width: 100%;height:100%" fit  @row-click="handleRowClick">
                            <el-table-column prop="id" label="出库单号"></el-table-column>
                            <el-table-column prop="outDeptName" label="出库科室"></el-table-column>
                            <el-table-column prop="inDeptName" label="入库科室"></el-table-column>
                            <el-table-column prop="goodsCount" label="品种数量"></el-table-column>
                            <el-table-column prop="goodsSum" label="商品总数量"></el-table-column>
                            <el-table-column prop="amount" label="金额"></el-table-column>
                            <el-table-column prop="accountDate" label="出库时间" align="center"></el-table-column>
                            <el-table-column label="操作" width="100" align="center">
                                <template slot-scope="scope">
                                    <el-button size="mini" style="width: 80px;height: 25px"  @click="printBarcode(scope.row)">打包</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </template>
                </el-col>
                <el-col :span="24" style="margin-top: 5px;height: 49%">
                    <template>
                        <el-table :data="currentOutStockBill.lstOutsubVo" highlight-current-row ref="billTable" style="height: 100%" border row-key="id">
                            <!-- <el-table-column type="index" label="序号" width="35" align="center"></el-table-column> -->
                            <el-table-column label="名称" prop="goodsName" min-width="100" show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column label="规格型号" prop="goodsGg" width="120" show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column label="批号" prop="batchCode" width="120" show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column label="请领数量" width="60" prop="reqQty" align="center">
                            </el-table-column>
                            <el-table-column label="已发数量" width="60" prop="sentQty" align="center">
                            </el-table-column>
                            <el-table-column label="出库数量" width="60" prop="outQty" align="center">
                            </el-table-column>
                            <el-table-column label="包装数量" width="60" prop="packetCode" align="center">
                            </el-table-column>
                            <el-table-column label="单价" width="60" prop="price" align="center">
                            </el-table-column>
                            <el-table-column label="单位" width="60" prop="unit" align="center">
                            </el-table-column>
                            <el-table-column label="金额" width="60" align="center">
                                <template slot-scope="scope">
                                    {{scope.row.outQty * scope.row.price}}
                                </template>
                            </el-table-column>
                            <el-table-column label="产地" prop="made" align="center">
                            </el-table-column>
                            <el-table-column label="供应商" prop="provName" align="center">
                            </el-table-column>
                            <el-table-column label="生产商" prop="mfrsName" align="center">
                            </el-table-column>
                        </el-table>
                    </template>
                </el-col>
        
        <el-dialog title="产品定数包打包确认" close="onClose" :visible.sync="confirmFixedPackageVisible" style="min-height: 300px;" :close-on-click-modal="false" >
            <template>
                <el-table :data="preFixedPackageList" tooltip-effect="dark" row-key='id' style="width: 100%">
                    <el-table-column label="产品名称" prop="goodsName" min-width="100" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column label="规格型号" prop="goodsGg" width="120" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column label="单位" width="60" prop="unit" align="center">
                    </el-table-column>
                    <el-table-column label="数量" width="60" prop="qty" align="center">
                    </el-table-column>
                    <el-table-column label="包装数量" width="60" prop="packetCode" align="center">
                    </el-table-column>
                    <el-table-column label="包数" width="60" prop="countPacket" align="center">
                    </el-table-column>
                    <!--
                    <el-table-column label="整包数量" width="60" prop="packetQty" align="center">
                    </el-table-column>
                    -->
                </el-table>
                <div style="text-align: center;margin-top: 20px;margin-bottom: 15px;">
                    <el-button @click="submitFixedPackage" type="success" >确定</el-button>
                    <el-button @click.native="confirmFixedPackageVisible = false" >返回</el-button>
                </div>
            </template>
        </el-dialog>
        <el-dialog title="产品定数包条码打印" close="onClose" :visible.sync="printVisible" :close-on-click-modal="false">
            <div>
                <el-col :span="24" name="pvtest" id="printView">
                    <template v-for="(o,index) in packegeLists">
                        <div class="printBarcode"  :key="o.packageId">
                            <div class="barDesc">
                                &nbsp;&nbsp;名称：{{o.goodsName}}<br/>
                                &nbsp;&nbsp;规格：{{o.goodsGg}}<br/>
                                &nbsp;&nbsp;单位：{{o.unit}}&nbsp;&nbsp;
                                数量：{{o.qty}}&nbsp;&nbsp;
                                整包数量：{{o.packetQty}}
                            </div>
                            <div style="height:120px;">
                                <svg :id="gernerateId(index)"></svg>
                            </div>
                        </div>
                    </template> 
                </el-col>
            </div>
            <div style="text-align: center;margin-top: 20px;margin-bottom: 15px;">
                <el-button @click="print" type="success" >确定</el-button>
                <el-button @click.native="printVisible = false" >返回</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    import moment from 'moment';
    import $ from 'jquery';
    import '../../common/third/printThis/printThis';
    import '../../common/third/qrcode/jquery.qrcode.min';
    import JsBarcode from 'jsbarcode';
    export default {
        data() {
            return {
                packegeLists:[],
                hosId:'',
                showTab:'pack',
                filters: {billId: ''},
                filter: {billId: ''},
                total: 0,
                page: 1,
                pageSize: 30,
                loading: false,
                outBillList: [],
                currentOutStockBill: {lstOutsubVo:[]},

                confirmFixedPackageVisible:false,
                preFixedPackageList:[],
                mapParams:{},

                packageInfos:[],
                pTotal: 0,
                pPage: 1,
                pPageSize: 20,

                printVisible:false,
                currentPacket:{}
            }
        },
        methods: {
            // 通过索引拼接条码ID
            gernerateId(index){
                return "barcode_" +index;
            },
            print() {
                $("#printView").printThis({
                    debug: false,
                    importCSS: true,
                    imporSttyle: true,
                    printContainer: true,
                    loadCSS: './static/style/print.css',
                    pageTitle: "定数包",
                    removeInline: false,
                    printDelay: 333,
                    header: null,
                    formValues: true
                });
            },
            showBill(currentPacket) {
                this.printVisible = true;
                this.$nextTick(()=>{
                    var index = 0;
                    this.preFixedPackageList.forEach(item => {
                        var barcode_id = '#' + this.gernerateId(index);
                        index++ ;
                        JsBarcode(barcode_id, item.packageId, {height: 50, displayValue: true});
                    });
                });
            },
            // 查询定数包列表
            getPackageInfos: function (pIndex) {
                this.pPage = pIndex;
                var param = {
                    orderBy: "",
                    page: this.page,
                    rows: this.pPageSize,
                    queryObject: {
                        billId:this.filter.billId,
                        hosId:this.hosId
                    }
                };
                this.loading = true;
                this.axios.post('/spdHERPService/barcodePrintingCenter/hosPackageInfo/listByPage', param).then(res => {
                    this.loading = false;
                    this.packageInfos = res.data.data.data;
                    this.pTotal = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            handleSizeChange(val) {
                this.pPageSize = val;
                this.getPackageInfos(this.pPage);
            },
            handleCurrentChange(val) {
                this.pPage = val;
                this.getPackageInfos(this.pPage);
            },
            resetPackageInfoForm:function (formName) {
                this.$refs[formName].resetFields();
                this.getPackageInfos(1);
            },


            // 查询请购出库单列表
            getOutBillList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    page: this.page,
                    rows: this.pageSize,
                    queryObject: {
                        billId:this.filters.billId,
                        outStockKind:40,// 出库类型：请购出库
                        isPacket:'1',// 定数包管理
                        //inDeptId: this.user.orgId,
                        status:30, //30已记帐
                        outOrgId:this.user.corpId
                    }
                };
                this.loading = true;
                this.axios.post('/spdHERPService/stockMgr/out4In/getBillList4Instock', param).then(res => {
                    this.loading = false;
                    this.outBillList = res.data.data.data;
                    this.total = res.data.data.total;
                }, err => {
                    this.loading = false;
                });
            },
            // 打包
            printBarcode:function (row) {
                this.axios.get('/spdHERPService/stockMgr/out4In/getBillDetail4In2', {
                    params: {
                        id: row.id,
                        isPacket:'1'
                    }
                }).then(res => {
                    if (res.data.code === 0) {
                        var lstOutsubVo = res.data.data;
                        // 数据打包：返回一个以产品ID为key的map集合
                        var map = this.printBarcodeSubmit(row,lstOutsubVo.lstOutsubVo);
                        // 数据打包确认对话框
                        this.showConfirmFixedPackage(map);
                    } else {
                        res.data.msg;
                    }
                }).catch(err => {
                })
            },
            // 数据打包
            printBarcodeSubmit:function (row,lstOutsubVo) {
                var map = {};// 最终分类打包产品map
                if(lstOutsubVo.length>0){
                    lstOutsubVo.forEach(item => {
                        var sameGoodsArray = map[item.goodsId];
                        if(sameGoodsArray && sameGoodsArray.length>0){
                            map[item.goodsId].push(item);
                        }else {
                            sameGoodsArray = [];
                            sameGoodsArray.push(item);
                            map[item.goodsId] = sameGoodsArray;
                        }
                    });
                }
                return map;
            },
            // 数据打包确认对话框
            showConfirmFixedPackage:function (map) {
                this.preFixedPackageList = [];
                for(var i in map) {
                    var countPackage = 0;// 包数
                    var qty =0;//同一商品 （可能不同批号）总数量
                    for(var j=0;j<map[i].length;j++){
                        countPackage += map[i][j].outQty;
                        qty=countPackage;
                    }
                    var goodsPackageObj = {};
                    goodsPackageObj['goodsName'] = map[i][0].goodsName;
                    goodsPackageObj['goodsGg'] = map[i][0].goodsGg;
                    goodsPackageObj['unit'] = map[i][0].unit;
                    //goodsPackageObj['qty'] = map[i][0].lstOutBatch[0].qty;
                    goodsPackageObj['qty'] =qty;
                    goodsPackageObj['packetCode'] = map[i][0].packetCode;
                    goodsPackageObj['countPacket'] = countPackage / map[i][0].packetCode;
                    goodsPackageObj['packetQty'] = (map[i][0].lstOutBatch[0].qty) * (map[i][0].packetCode);
                    this.preFixedPackageList.push(goodsPackageObj);
                }
                this.mapParams = map;
                this.confirmFixedPackageVisible = true;
            },
            submitFixedPackage:function () {
                let o = this;
                o.axios.post('/spdHERPService/barcodePrintingCenter/hosPackageInfo/addHosPackageInfos', o.mapParams).then(res => {
                    if (res.data.code == "0") {
                        o.printVisible = true;
                        o.packegeLists = res.data.data;
                        o.$message({ message: '操作成功！', type: 'success' });
                        var index = 0;
                        o.packegeLists.forEach(item => {
                            var barcode_id = '#' + 'barcode_' + index;
                            index++ ;
                            this.$nextTick(function () {
                                JsBarcode(barcode_id, item.packageId, {height: 50, displayValue: true});
                            })
                            
                        });
                        o.confirmFixedPackageVisible =false;
                        o.getOutBillList(1);
                    } else{
                        this.$message.error( res.data.msg);
                    }
                }, err => {
                });
            },

            // 查询请购出库单详情列表
            handleRowClick (row, event) {
                this.getBillDetail(row, event).then(res => {
                    this.currentOutStockBill = res;
                }).catch(err => {
                    this.handleError(err);
                })
            },
            getBillDetail (row, event) {
                return new Promise((resolve, reject) => {
                    this.axios.get('/spdHERPService/stockMgr/out4In/getBillDetail4In2', {
                        params: {
                            id: row.id,
                            isPacket:'1'
                        }
                    }).then(res => {
                        if (res.data.code === 0) {
                            resolve(res.data.data);
                        } else {
                            reject(res.data.msg);
                        }
                    }).catch(err => {
                        reject(err);
                    })
                })
            },
        },
        mounted() {
            this.hosId = this.user.corpId;
            this.getOutBillList(1);
        }
    }
</script>
<style>
    #fixed-num-packet .el-table {
        font-size: 12px;
    }
    #fixed-num-packet .el-tabs,.el-tabs .el-tabs__content{
        height:94%;
    }
    #fixed-num-packet .el-tabs__item{
        height:30px;
        line-height: 30px;
        font-size:12px;
    }
    #fixed-num-packet .printBarcode{
        font-size: xx-small;
        color: black;
        margin-bottom: 45px;
    }
    #fixed-num-packet .barDesc{
        /* margin-left: 9px; */
        margin-bottom: -5px;
    }
</style>

