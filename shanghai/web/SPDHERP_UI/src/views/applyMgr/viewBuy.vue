<template>
    <el-dialog :visible.sync="viewBuyVisible" width="80%" top="5vh">
    <el-row id="view-buy" :gutter="10" style="height:95%">
            <el-col :span="24" style="height:95%">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;height: 200px;">
                    <el-form label-width="120px">
                        <el-col :span="8">
                            <el-form-item label="请购科室：">
                                <span>{{ buyBill.buyDeptName }}</span>
                            </el-form-item>
                        </el-col>
                       
                        <el-col :span="8">
                            <el-form-item label="目标科室：">
                                <span>{{ buyBill.sourceWarehouseName }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="请购笔数：">
                                <span>{{ buyBill.sumRow }}</span>
                            </el-form-item>
                        </el-col>
                       
                       <!--
                        <el-col :span="8">
                            <el-form-item label="采购笔数：">
                                <span>{{ buyBill.whSumrow }}</span>
                            </el-form-item>
                        </el-col>
                        -->
                         <!-- <el-col :span="8">
                            <el-form-item label="总金额：">
                                <span>{{ buyBill.totalJe }}</span>
                            </el-form-item>
                        </el-col> -->
                       <el-col :span="8">
                            <el-form-item label="制单时间:">
                                <span>{{ buyBill.fillDate | dateFormat('YYYY-MM-DD')}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="制单人：">
                                <span>{{ buyBill.fillterName }}</span>
                            </el-form-item>
                        </el-col>
                            <el-col :span="8">
                            <el-form-item label="科室审核时间:">
                                <span v-if="buyBill.deptDealTime">{{ buyBill.deptDealTime | dateFormat('YYYY-MM-DD')}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="科室审核人：">
                                <span>{{ buyBill.deptDealManName }}</span>
                            </el-form-item>
                        </el-col>
                        <!--
                        <el-col :span="8">
                            <el-form-item label="供应商：">
                                <span>{{ buyBill.provName }}</span>
                            </el-form-item>
                        </el-col>
                       -->
                       <el-col :span="8">
                            <el-form-item label="收货人：">
                                <span>{{ buyBill.recLinkman }}</span>
                            </el-form-item>
                        </el-col>
                         <el-col :span="8">
                            <el-form-item label="收货人电话：">
                                <span>{{ buyBill.recLinkmanPhone }}</span>
                            </el-form-item>
                        </el-col>
                          <el-col :span="16">
                            <el-form-item label="收货地址：">
                                <span>{{ buyBill.recAddress }}</span>
                            </el-form-item>
                        </el-col>
                         
                       
                        <el-col :span="24">
                            <el-steps :space="120" :active="test" finish-status="success" style="justify-content:center;">
                                <el-step title="已保存(护士)"></el-step>
                                <el-step v-if="test > 1" title="已审核(护士长)"></el-step>
                                <el-step v-else title="未审核(护士长)"></el-step>
                                <el-step v-if="test > 2" title="已审核(设备科)"></el-step>
                                <el-step v-else title="未审核(设备科)"></el-step>
                            <!--    <el-step title="未处理"></el-step>-->
                                <el-step title="处理中"></el-step>
                                <el-step title="已处理"></el-step>
                            </el-steps>
                        </el-col>
                    </el-form>
                </el-col>
                <!--列表-->
                    <el-table :data="buyBill.subVoList" height="outter" style="width: 100%; " fit border  class ='viewBuy-tableheight'>
                        <el-table-column prop="rowNumber" label="序号" width="60">
                        </el-table-column>
                          <el-table-column prop="subState" label="状态" width="60">
                                <template slot-scope="scope">
                                      {{scope.row.subState | fStatus}}
                                </template>
                        </el-table-column>
    
                        <el-table-column prop="goodsName" label="名称" width="180" show-overflow-tooltip>
                        </el-table-column>
                        <el-table-column prop="code" label="编号">
                        </el-table-column>
                         <el-table-column prop="goodsGg" label="规格">
                        </el-table-column>
                         <el-table-column prop="brand" label="品牌">
                         </el-table-column>
                        <!--<el-table-column prop="price" label="单价" width="120">
                        </el-table-column>-->
                       
                        <el-table-column prop="unit" align='center' label="计量单位">
                        </el-table-column>
                        <el-table-column prop="applyQty" label="请购数量">
                        </el-table-column>
                        <el-table-column prop="qty" label="审核数量">
                        </el-table-column>
                        <!--<el-table-column prop="je" label="请购金额">
                        </el-table-column>-->
                        <el-table-column label="中包数">
                            <template slot-scope="scope">
                                {{midUnit(scope.row)}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="fieldCode2" label="件包">
                        </el-table-column>
                        <el-table-column prop="provName" label="供应商">
                        </el-table-column>
    
                     
                     
                    </el-table>
                <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                    <div style="margin-right:50px;" align="right">
                        <el-button  type="primary" v-if='buyBill.state==10 && userRole.cname=="护士长"'  @click="auditBill(queryBillId)">审核</el-button>
                        <el-button  type="primary"   @click="goBack">返回</el-button>
                        <el-button  type="primary"   @click="print">打印</el-button>
                    </div>
                </el-col>

                <!--请购单 修改 审核  -->
                <el-dialog title="请购单审核" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" width="70%" center append-to-body>
                            <template>
                                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                                    <el-form label-width="120px">
                                            
                                       
                                          <el-col :span="12">
                                            <el-form-item label="目标科室:">
                                                    <el-input type="text"  v-model="buyBill.sourceWarehouseName" :disabled="true" ></el-input>
                                            </el-form-item>
                                        </el-col>
                                         <el-col :span="12">
                                            <el-form-item label="请购科室:">
                                                  <!--  <el-input type="text"  v-model="buyBill.buyDeptName" :disabled=true ></el-input> -->
                                                  <span>{{ buyBill.buyDeptName }}</span>
                                            </el-form-item>
                                        </el-col>
                                     
                                            <el-col :span="6">
                                                    <el-form-item label="ID:" v-show="false">
                                                        <span>{{ buyBill.recAddressId }}</span>
                                                    </el-form-item>
                                            </el-col>
                                               <el-col :span="5">
                                                    <el-form-item label="收货人:">
                                                        {{buyBill.recLinkman}}
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :span="5">
                                                    <el-form-item label="电话:">
                                                        {{buyBill.recLinkmanPhone}}
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :span="12">
                                                    <el-form-item label="收货地址:">
                                                        {{buyBill.recAddress}}
                                                    </el-form-item>
                                                </el-col>
                                            
                                                <el-col :span="2">
                                                    <el-form-item label-width="0px">
                                                    <deliveryAddress btn_text="修改" v-on:addressChange="setAddress" :hosId="this.user.corpId" :deptId="this.user.orgId"></deliveryAddress>
                                                    </el-form-item>
                                                </el-col>
                                                
                                    </el-form>
                                </el-col>
                            </template>

                               <!--请购明细列表-->
                            <template>
                                  <el-table :data="buyBill.subVoList" style="width: 100%;" fit border>
                                       <el-table-column label="操作" width="50">
                                            <template slot-scope="scope">
                                                <el-button  @click="delRow(scope.row)" type="text">删除</el-button>
                                            </template>
                                        </el-table-column>
                                        <el-table-column prop="rowNumber" label="序号" width="60">
                                        </el-table-column>
                                        <el-table-column prop="goodsName" label="名称" width="180" show-overflow-tooltip>
                                        </el-table-column>
                                        <el-table-column prop="code" label="编号">
                                        </el-table-column>
                                        <el-table-column prop="goodsGg" label="规格">
                                        </el-table-column>
                                         <el-table-column prop="brand" label="品牌">
                                         </el-table-column>
                                        <!-- <el-table-column prop="price" label="单价" width="120">
                                        </el-table-column> -->
                                        <el-table-column prop="applyQty" label="请购数量"  width="100"> 
                                            <template slot-scope="scope">
                                                    <el-input-number placeholder="请输入数量" v-model="scope.row.applyQty" size="mini" :min="1"  :step="scope.row.reqNum?scope.row.reqNum:1" class="col-input-num" :disabled="scope.row.isPacket==1" @change="qtyChange($event,scope.row)">
                                                    </el-input-number>
                                            </template>  
                                        </el-table-column>
                                        <el-table-column label="请购包数"  width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量"   v-model="scope.row.needPacketQty" size="mini" :min="0"  :step="scope.row.reqNum?scope.row.reqNum:1" class="col-input-num" :disabled="scope.row.isPacket==0||scope.row.isPacket==null" @change="qtyPacketChange($event,scope.row)">
                                                </el-input-number>
                                            </template>
                                        </el-table-column>
                                        
                                        <!-- <el-table-column prop="je" label="金额">
                                        </el-table-column> -->
                                        <el-table-column label="资金来源">
									        <template slot-scope="scope">
									          <el-select v-model="scope.row.fundType" placeholder="请选择资金来源" >
												<el-option v-for="item in fundTypes" :key="item.val" :label="item.ename" :value="item.val">
												</el-option>
									          </el-select>
									        </template>
                                        </el-table-column>
                                      <el-table-column prop="fieldCode2" label="件包">
                                      </el-table-column>
                                        <el-table-column prop="provName" label="供应商">
                                        </el-table-column>    
                                        <el-table-column prop="isPacket"   min-idth="10" label="是否定数包" header-align="center"  :formatter="dsFormatter">
                                        </el-table-column>
                                         <el-table-column prop="storeQty" label="库存数量">   
                                        </el-table-column>                            
                                    </el-table>
                                
                            </template>
                              <!-- <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                                    <div style="margin-left:100px;text-align: center;">
                                        <el-button :plain="true" type="success"  @click="submitBill()">提交</el-button>
                                        <el-button :plain="true" type="warning"  @click="saveBill()">保存</el-button>
                                        <el-button :plain="true" type="danger"  @click="cancel">取消</el-button>
                                    </div>
                              </el-col> -->
                            <span slot="footer" class="dialog-footer">
                              <el-button :plain="true" type="success"  @click="submitBill()">提交</el-button>
                                        <!--<el-button :plain="true" type="warning"  @click="saveBill()">保存</el-button>-->
                                        <el-button :plain="true" type="danger"  @click="cancel">取消</el-button>
                            </span>
                </el-dialog>   
            </el-col>
            <viewBuyPrint  ref="applyBuyPrintD" :pBillId = "buyBill.billId" :pBuyBill="buyBill"></viewBuyPrint>
    </el-row>
    </el-dialog>
</template>
<script>
import moment from 'moment';
import { DEPTBUY_MAIN_STATUS } from './common/constance';
import { DEPTBUY_SUB_STATUS } from './common/constance';
import { DEPTBUY_MAIN_DEPTAUDIT_STATUS } from './common/constance';
import deliveryAddress from "../../components/deliveryAddress.vue";
import viewBuyPrint from './viewBuyPrint';

export default {
    data() {
        return {
            buyBill: {
                id: '', billId: '', fillDate: '', returnCompanyId: '', returnCompanyName: '',
                returnOrgId: '', returnOrgCode: '', returnOrgName: '',
                outOrgId: '', outOrgName: '', outOrgCode: '',
                provId: '', provName: '',
                fillter: '', memo: '', isStartInvoice: '', sumRow: '', status: '',
                subVoList: []
            },
          
            test: 0,
            queryBillId :'',//请购单Id
            userRole:{},//当前用户角色
            editFormVisible:false,

            viewBuyVisible: false
        }
    },
   components: { deliveryAddress,viewBuyPrint },
   filters: {
        fStatus: function (value) {
            if (value) {
                return DEPTBUY_SUB_STATUS[value].text;
            } else {
                return '';
            }
        }
    },

    methods: {
        midUnit (row) {
            let getMidUnit = function (midUnitArray, prevUnit, prevNum, counter) {
                if (midUnitArray.length > 0 && prevNum !== 0){
                    const myRe = new RegExp('^(\\d+).*/(.*)', 'g')
                    const unitRe = myRe.exec(midUnitArray[0])
                    if (unitRe) {
                        const unitNum = unitRe[1]
                        const unitStr = unitRe[2]
                        const midNum = Math.floor( prevNum / unitNum )
                        if (midNum !== 0){
                            const restNum = prevNum % unitNum
                            if ( restNum !== 0 ) {
                                return getMidUnit(midUnitArray.slice(1), unitStr, midNum, ++counter) + restNum + prevUnit
                            } else {
                                return getMidUnit(midUnitArray.slice(1), unitStr, midNum, ++counter)
                            }
                        } else if (counter !== 1) {
                            return prevNum + prevUnit
                        } else {
                            return ''
                        }
                    }
                } else if (midUnitArray.length === 0 && counter !== 1) {
                    return prevNum + prevUnit
                }
            }
            if(row.fieldCode2){
                return getMidUnit(row.fieldCode2.split('，'), row.unit, row.applyQty, 1)
            }
        },
        goBack() {
            // this.$router.go(-1);
            this.viewBuyVisible = false
        },
        /**打印 */
        print(){
            this.$refs.applyBuyPrintD.print(this.buyBill.state);
        },
        getBillDetails(billId) {
            let params ={billId:billId};
            return new Promise((resolve, reject) => {
                this.axios.post('/spdHERPService/applyMgr/deptBuyMain/getBillDetails/' , params).then(res => {
                    if (res.data.code == "0") {
                        this.buyBill = res.data.data;
                        this.test = DEPTBUY_MAIN_STATUS[this.buyBill.state].index;
                        this.buyBill.subVoList.forEach(g => {
                            g.needPacketQty = g.isPacket == 1 ? g.applyQty / g.packetCode : 0;
                        });
                        resolve(this.buyBill)
                    }
                }, err => {
                    reject(err)
                })
            })
        },
        /*******************   护士长审核相关逻辑 start*************************************** */
        auditBill(billId) {
            this.modelTitle = "审核请购单";
            this.editFormVisible = true;
            this.getBillDetails(billId);
        },
        setAddress(d) {
            if(d && d.isDefaulte=='1'){
            this.buyBill.recAddressId = d.id;
            this.buyBill.recLinkman = d.lxr;
            this.buyBill.recLinkmanPhone = d.lxrPhone;
            this.buyBill.recAddress = (""+d.province + d.city + d.area + d.address).replace(/null/g,"");
      }
        },
        delRow(pd) {
            var index = this.buyBill.subVoList.findIndex(item => {
                return item.rowNumber == pd.rowNumber;
            });
            this.buyBill.subVoList.splice(index, 1);
            this.buyBill.subVoList.forEach((item, i) => (item.rowNumber = i + 1));
        },
        qtyChange(val, row) {
            row.qty = val;
            row.je = (row.price * row.qty).toFixed(2);
        },
        qtyPacketChange(val,row){
            row.needPacketQty = val;
            row.applyQty  =  (row.needPacketQty * row.packetCode).toFixed(2);
        },
        dsFormatter (row, column, cellValue) {
            if(cellValue==1)
            return "是"
            else return "否"
        },
        //字典资金来源
        getFundTypes () {
            this.loading = true;
            this.axios.post('/platformService/sys/dict/getDictValueByDictEname',{dictName:'ZJLY'}).then(res => {
                this.loading = false;
                if (res.data.code == "0") {
                    this.fundTypes = res.data.data;
                }
                else{
                    this.$message.error( res.data.msg);
                }
            }, err => {
                this.loading = false;
            });
        },
        submitBill: function() {
            this.loading = true;
            //   this.buyBill.state = status;
            this.buyBill.subVoList.forEach(item => delete item.needPacketQty);

            this.axios.post("/spdHERPService/applyMgr/departmentAudit/submitBill",this.buyBill).then(
                res => {
                    this.loading = false;
                    if (res.data.code == "0") {
                    //   var billId = res.data.data.billId;
                    this.$msgbox({
                        title: "提示",
                        message: "操作成功",
                        type: "success"
                        //  callback: action => {
                        //      this.$router.push({ path: 'purchase', query: { billId: billId } });
                        //  }
                    });
                    this.editFormVisible = false;
                    this.$router.push({ path: 'listBuy'});
                    //this.getDeptBuyList(1);
                    } else {
                    this.$msgbox({ title: "提示", message: "操作失败", type: "error" });
                    }
                },
                err => {
                    this.loading = false;
                    this.$msgbox({
                    title: "提示",
                    message: "操作失败",
                    type: "error"
                    });
                }
            );
        },
        cancel() {
             this.editFormVisible = false;
        },
        /** 根据UserID查看用户权限*/
        loadUserRoles(userId){
            let o = this;
            this.axios.post('/platformService/permission/roleConfig/listUserAllRoles/'+ userId ).then(res=>{
                if (res.data.code == 0) {   //成功
                    var roles = res.data.data;
                    if(roles.length>0){
                        o.userRole = roles[0];
                    }
                }else{
                    o.$message.error(res.data.msg);
                }
            },err=>{
                o.$message.error("数据加载出错");
            });
        },
        /*******************   护士长审核相关逻辑 end*************************************** */
        open (billId) {
            // const loading = this.$loading({
            //     lock: true,
            //     text: 'Loading',
            //     spinner: 'el-icon-loading',
            //     background: 'rgba(0, 0, 0, 0.7)'
            // });
            this.getFundTypes();//获取资金来源
            // var billId = this.$route.query.billId;
        //  alert(billId);
            if (billId) {
                this.queryBillId = billId;
                this.getBillDetails(billId).then(res => {
                    this.viewBuyVisible = true
                    // loading.close()
                    this.$emit("datacomplete");
                })
            }
        }
    },
    mounted() {
        this.loadUserRoles(this.user.userId);
    //     this.getFundTypes();//获取资金来源
    //     // var billId = this.$route.query.billId;
    //     let billId = this.billId
    //   //  alert(billId);
    //     if (billId) {
    //          this.queryBillId = billId;
    //         this.getBillDetails(billId);

    //     }
    }
};

</script>

<style>
#view-buy .el-table {
    font-size: 12px;
}
/* #view-buy .viewBuy-tableheight {        
    height:  calc(100% - 215px);
} */
#view-buy .el-table__body-wrapper {
    height: 100%;
}
</style>