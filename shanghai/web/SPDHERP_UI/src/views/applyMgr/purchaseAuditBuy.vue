<template>
    <div id="purchase-audit-buy" style="height: 100%;">
            <el-col :span="24" style="height: 100%;">
                <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                    <el-form :inline="true" label-width="80px">
                        <el-form-item label="请购单号">
                            <el-input v-model="filters.billId" placeholder="请购单号" ></el-input>
                        </el-form-item>
                        <el-form-item label="请购日期：">
                            <el-date-picker v-model="filters.sDate" type="daterange" align="right" placeholder="选择日期范围" :picker-options="pickerOptions2" >
                            </el-date-picker>
                        </el-form-item>
                        <el-button-group>
                            <el-button type="primary" icon="search" v-on:click="getDeptBuyList(1)" >查询</el-button>
                            <el-button type="primary" icon="setting" v-on:click="reset" >重置</el-button>
                        	<el-button type="primary" icon="plus" v-on:click="batchSubmitBill" >提交</el-button>
                        </el-button-group>
                    </el-form>
                </el-col>
                <!--列表-->
                <template>
                        <el-table :row-class-name="tableRowClassName" :data="deptBuyList" highlight-current-row v-loading="loading" style="width: 100%;" fit border class="RO-Main-tableheight" ref="multipleSelection4Main" @selection-change="handleSelectionChange4Main">
                              <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                              <el-table-column prop="billId" label="请购单号" sortable></el-table-column>
                              <el-table-column prop="state" label="状态" sortable :formatter="statusFormat"></el-table-column>
                              <el-table-column prop="buyDeptName" label="请购科室" sortable> </el-table-column>
                              <el-table-column prop="sourceWarehouseName" label="目标科室" sortable> </el-table-column>
                              <el-table-column prop="totalJe" label="总金额" sortable> </el-table-column>
                              <el-table-column prop="fillterName" label="制单人" sortable></el-table-column>
                              <el-table-column prop="fillDate" label="制单时间">
                                    <template slot-scope="scope">
                                        {{scope.row.fillDate | dateFormat('YYYY-MM-DD')}}
                                    </template>
                              </el-table-column>
                              <el-table-column label="操作" width="150">
                                    <template slot-scope="scope">
                                        <el-button   @click="viewBill(scope.row.billId)" type="text">查看</el-button>
                                        <!--<el-button v-if="scope.row.state == 10||scope.row.state =='11'"  @click="editBill(scope.row.billId)" type="text">编辑</el-button>-->
                                        <el-button v-if="scope.row.state == 10||scope.row.state =='11'"  @click="auditBill(scope.row)" type="text">审核</el-button>
                                        <el-button  v-if="scope.row.status == 10" @click="delBill(scope.row)" type="text">删除</el-button>
                                    </template>
                              </el-table-column>
                        </el-table>
                            <!--工具条-->
                            <el-col :span="24" class="toolbar">
                                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
                                </el-pagination>
                            </el-col>
                </template>
                <!--请购单 修改 审核  -->
                <el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" width="70%" center v-loading="vLoading">
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
                                  <el-table :data="buyBill.subVoList" style="width: 100%;" fit border height="300">
                                       <el-table-column label="操作" width="50">
                                            <template slot-scope="scope">
                                                <el-button  @click="delRow(scope.row)" type="text">删除</el-button>
                                            </template>
                                        </el-table-column>
                                        <el-table-column prop="rowNumber" label="序号" width="60">
                                        </el-table-column>
                                        <el-table-column prop="qty" label="请购数量"  width="100">
                                            <template slot-scope="scope">
                                                    <el-input-number placeholder="请输入数量" v-model="scope.row.qty" size="mini" :min="1"  :step="scope.row.reqNum?scope.row.reqNum:1" class="col-input-num" :disabled="scope.row.isPacket==1" @change="qtyChange($event,scope.row)">
                                                    </el-input-number>
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="请购包数"  width="100">
                                            <template slot-scope="scope">
                                                <el-input-number placeholder="请输入数量"   v-model="scope.row.needPacketQty" size="mini" :min="0"  :step="scope.row.reqNum?scope.row.reqNum:1" class="col-input-num" :disabled="scope.row.isPacket==0||scope.row.isPacket==null" @change="qtyPacketChange($event,scope.row)">
                                                </el-input-number>
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
                                        <!-- <el-table-column prop="price" label="单价" width="120">
                                        </el-table-column> -->


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
                                         <el-table-column prop="storeQty" label="库存数量">
                                        </el-table-column>
                                        <el-table-column prop="isPacket"   min-idth="10" label="是否定数包" header-align="center"  :formatter="dsFormatter">
                                        </el-table-column>
                                  </el-table>
                            </template>
                              <!-- <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;">
                                    <div style="margin-left:100px;text-align: center;">
                                        <el-button :plain="true" type="success"  @click="submitBill()">提交</el-button>

                                        <el-button :plain="true" type="danger"  @click="cancel">取消</el-button>
                                    </div>
                              </el-col> -->
                              <span slot="footer" class="dialog-footer">
                                        <el-button :plain="true" type="success"  @click="submitBill()">提交</el-button>
                                        <!--<el-button :plain="true" type="warning"  @click="saveBill()">保存</el-button>-->
                                        <el-button :plain="true" type="danger"  @click="cancel">取消</el-button>
                             </span>
                </el-dialog>
                   <!--手术包请购单 修改 审核  -->
                <el-dialog :title="modelTitle" close="onClose" :visible.sync="editForm4surVisible" :close-on-click-modal="false"   style=" height: 100%;" width="96%"  top="1vh" center>
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
                               <!--手术包请购单 明细列表-->
                            <template>
                                    <el-row  height="auto"    style="height:100%;padding-top:0px">     
                                        <el-col :span="6" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: calc(100% - 30px);">
                                            <el-table highlight-current-row class="purAudit-tableheight"
                                                    :data="buyBill.surDef4applyBillVoList" style="width: 100%"
                                                      ref="multipleTable4Sur"
                                                      row-key='surId'  
                                                      @row-click="fetchSurGoods"
                                                      v-loading="loading" border height="outer"
                                                      :default-sort="{prop: 'surId', order: 'descending'}">
                                                         <el-table-column label="操作" width="50" column-key="op">
                                                              <template slot-scope="scope">
                                                                  <el-button  @click="delRow4sur(scope.row)" type="text">删除</el-button>
                                                              </template>
                                                          </el-table-column>  
                                                        <el-table-column label="请购包数"  width="100">
                                                                        <template slot-scope="scope">
                                                                            <el-input-number placeholder="请输入数量"   v-model="scope.row.qty" size="mini" :min="1" class="col-input-num"  :step="1"   @change="qtySurPacketChange($event,scope.row)">
                                                                            </el-input-number>
                                                                        </template>
                                                        </el-table-column>
                                                        <el-table-column prop="surDef.cname"   min-idth="85" label="手术包名称" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                         
                                                        
                                            </el-table>
                                        </el-col>
                                        <el-col :span="18" class="toolbar" style="padding-bottom: 0px; padding-left:5px;  height: calc(100% - 30px);">
                                            <el-table highlight-current-row class="purAudit-tableheight"
                                                    :data="ssGoodsList" style="width: 100%"
                                                      row-key='goodsId' 
                                                      v-loading="loading" border height="outer"
                                                      :default-sort="{prop: 'goodsId', order: 'descending'}">
                                                      
                                                        <el-table-column prop="goodsId"   min-idth="85" label="商品ID" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        <el-table-column prop="goodsGg"   min-idth="85" label="商品规格" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        <el-table-column prop="goodsName"   min-idth="85" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        <el-table-column prop="surGoodsQty"   min-idth="85" label="每包数量" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        <el-table-column prop="unit"   min-idth="85" label="单位" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        <el-table-column prop="mfrsName"   min-idth="85" label="厂家" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        <el-table-column prop="made"   min-idth="85" label="生产国" width="150" header-align="center" show-overflow-tooltip >
                                                        </el-table-column>
                                                        
                                            </el-table>
                                
                                        </el-col>
                                    </el-row>
                            </template>
                            <span slot="footer" class="dialog-footer">
                                          <el-button :plain="true" type="success"  @click="submitBill()">提交</el-button>
                                        <el-button :plain="true" type="danger"  @click="cancel4sur">取消</el-button>
                            </span>
                             
                </el-dialog>   
            </el-col>
      <view-buy ref="viewBuy" v-on:datacomplete="dataComplete()"></view-buy>
    </div>
</template>
<script>
import { DEPTBUY_MAIN_STATUS } from "./common/constance";
import moment from 'moment';
import util from "../../common/js/util.js";
import { mapGetters } from "vuex";
import deliveryAddress from "../../components/deliveryAddress.vue";
import viewBuy from './viewBuy'
export default {
  data() {
    return {
      filters: {
        billId: "",
        sDate: []
      },
      fundTypes:[],
      multipleSelection4Main:[],
      pickerOptions2: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      billId: "",
      total: 0,
      page: 1,
      pageSize: 20,
      deptBuyList: [],
      loading: false,
      vLoading:false,
      editFormVisible: false,
      editForm4surVisible:false,
      modelTitle: null,
      buyBill: {
        id: "",
        billId: "",
        fillDate: "",
        returnCompanyId: "",
        returnCompanyName: "",
        returnOrgId: "",
        returnOrgCode: "",
        returnOrgName: "",
        outOrgId: "",
        outOrgName: "",
        outOrgCode: "",
        provId: "",
        provName: "",
        fillter: "",
        memo: "",
        isStartInvoice: "",
        sumRow: "",
        status: "",
        subVoList: [],
        recAddressId: "",
        recLinkman: "",
        recLinkmanPhone: "",
        recAddress: ""
      },
      ssGoodsList:[]
    };
  },
  components: { deliveryAddress, viewBuy },
  methods: {
    handleSelectionChange4Main (val) {
      this.multipleSelection4Main = val;
    },
    //字典资金来源
    getFundTypes: function () {
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
    //获取列表
    getDeptBuyList: function(pIndex) {
      this.page = pIndex;
      var param = {
        orderBy: "",
        page: this.page,
        rows: this.pageSize,
        queryObject: {
          billId: this.filters.billId,
        //  buyKind: 10,
          hosId: this.user.corpId,
          buyDeptId: this.user.orgId,
          sDate: this.filters.sDate&&this.filters.sDate[0]!=null?moment(this.filters.sDate[0]).format("YYYY-MM-DD"):null,
          eDate: this.filters.sDate&&this.filters.sDate[1]!=null?moment(moment(this.filters.sDate[1]).add(1,'days').format('YYYY-MM-DD')).subtract(1,'seconds').format('YYYY-MM-DD HH:mm:ss'):null,
 		      sourceWarehouseId:this.user.orgId
        }
      };
      this.loading = true;
      this.axios.post("/spdHERPService/applyMgr/purchaseAudit/listVoByPage", param).then(
        res => {
          this.loading = false;
          this.deptBuyList = res.data.data.data;
          this.total = res.data.data.total;
          this.deptBuyList.forEach(item=>{
            if(item.point=='1'){
              this.$message({showClose:true,message:'单品超过2000元，将显示红色字体',type:'success'});
              return false;
            }
          });
        },
        err => {
          this.loading = false;
        }
      );
    },
    tableRowClassName(row, index) {
      var point = row.row.point;
      if (point == 1) {
        //张志华添加,控制行数据样式
        return "point";
      }
      return "";
    },
    reset: function() {
      this.filters.billId = "";
      this.getDeptBuyList(1);
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getDeptBuyList(this.page);
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getDeptBuyList(this.page);
    },
    statusFormat(row) {
      return DEPTBUY_MAIN_STATUS[row.state].text;
    },
    viewBill(billId) {
      // this.$router.push({ path: "viewBuy", query: { billId: billId } });
      this.loading = true
      this.$refs['viewBuy'].open(billId)
    },
    editBill(billId) {
      this.modelTitle = "修改请购单";
      this.editFormVisible = true;
      this.getBillDetails(billId);
    },
    auditBill(row) {

      if(row.purMode===40){
           this.modelTitle = "审核手术包请购单";
            this.editForm4surVisible = true;
            this.getAuditBillDetails4sur(row.billId);
      }
      else{
          this.modelTitle = "审核请购单";
          this.editFormVisible = true;
          this.getAuditBillDetails(row.billId);
      }
    },
    
    delRow(pd) {
      var index = this.buyBill.subVoList.findIndex(item => {
        return item.rowNumber == pd.rowNumber;
      });
      this.buyBill.subVoList.splice(index, 1);
      this.buyBill.subVoList.forEach((item, i) => (item.rowNumber = i + 1));
    },
      delRow4sur(pd) {
             
          this.ssGoodsList=[]; 
              var index = this.buyBill.surDef4applyBillVoList.findIndex(item=>{
                item.surDef.id = pd.surDef.id
              })
              this.buyBill.surDef4applyBillVoList.splice(index, 1);
              this.buyBill.subVoList =this.buyBill.subVoList.filter(s=>{return s.surId !=pd.surDef.id})
            
    },
    getBillDetails(billId) {
      let params = { billId: billId };
      this.axios
        .post("/spdHERPService/applyMgr/deptBuyMain/getBillDetails/", params)
        .then(
          res => {
            if (res.data.code == "0") {
              this.buyBill = res.data.data;
              this.buyBill.subVoList.forEach(g => {
                g.needPacketQty = g.isPacket == 1 ? g.qty / g.packetCode : 0;
              });
            }
          },
          err => {}
        );
    },
    getAuditBillDetails(billId) {
      let params = { billId: billId };
      this.axios
        .post("/spdHERPService/applyMgr/deptBuyMain/getBillDetails/", params)
        .then(
          res => {
            if (res.data.code == "0") {
              this.buyBill = res.data.data;
              this.buyBill.subVoList.forEach(g => {
                g.needPacketQty = g.isPacket == 1 ? g.qty / g.packetCode : 0;
                g.qty = g.qty>0 ? g.qty : g.applyQty;
              });
            }
          },
          err => {}
        );
    },
    getAuditBillDetails4sur(billId) {
      let params = { billId: billId };
      this.axios
        .post("/spdHERPService/applyMgr/deptBuyMain/getBillDetails4sur/", params)
        .then(
          res => {
            if (res.data.code == "0") {
              this.buyBill = res.data.data;
            
            }
          },
          err => {}
        );
    },
     fetchSurGoods(row,e,column){
       if(column.columnKey==="op")
       return
       else{
            this.ssGoodsList = row.surDefSubBillList
            this.ssGoodsList.forEach(o=>{
                    o.surGoodsQty =row.surDefGoodsList.find(m=>{return m.goodsId==o.goodsId}).qty
                  })  
       }
      
    },
    cancel() {
      this.editFormVisible = false;
    },
    cancel4sur(){
      this.editForm4surVisible = false;
    },
    saveBill: function() {
      this.loading = true;
      this.buyBill.subVoList.forEach(item => delete item.needPacketQty);
      this.axios
        .post(
          "/spdHERPService/applyMgr/purchaseAudit/update/" + this.user.userId,
          this.buyBill
        )
        .then(
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
              this.getDeptBuyList(1);
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
    submitBill: function(status) {
      this.vLoading = true;
      delete this.buyBill.surDef4applyBillVoList

      this.buyBill.subVoList.forEach(item =>{
           delete item.needPacketQty
           delete item.surGoodsQty
      });
        if( this.buyBill.subVoList.length==0){
                 this.$msgbox({title: "提示", message: "明细行不能为空！", type: "error"});
                 this.vLoading = false;
                 return
            }
      this.buyBill.auditor = this.user.userId; //设备科审核人

      this.axios.post("/spdHERPService/applyMgr/purchaseAudit/submitBill",this.buyBill)
        .then(
          res => {
            this.vLoading = false;
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
              this.editForm4surVisible = false;
              this.getDeptBuyList(1);
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
    batchSubmitBill: function() {
      if (this.multipleSelection4Main.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      this.loading = true;
      this.multipleSelection4Main.forEach(item => {
      	item.auditor = this.user.userId; //设备科审核人
      });
      this.axios
        .post(
          "/spdHERPService/applyMgr/purchaseAudit/batchSubmitBill",
          this.multipleSelection4Main
        )
        .then(
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
              this.getDeptBuyList(1);
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
    qtyChange(val, row) {
      row.qty = val;
      row.je = (row.price * row.qty).toFixed(2);
    },
    qtyPacketChange(val, row) {
      row.needPacketQty = val;
      row.qty = (row.needPacketQty * row.packetCode).toFixed(2);
    },
    qtySurPacketChange(val,row){
      row.surDefSubBillList.forEach(sub=>{
           sub.qty=        row.surDefGoodsList.find(dg=>dg.goodsId===sub.goodsId).qty*val
      })
    // this.buyBill.subVoList.forEach(sub=>{
    //    sub.qty= row.surDefSubBillList.find(s=>s.surId===sub.surId&&s.goodsId===sub.goodsId).qty
    // })
     row.surDefSubBillList.forEach(sub=>{
          let  o =this.buyBill.subVoList.find(v=>v.goodsId===sub.goodsId&&v.surId===sub.surId)
          o.qty=sub.qty
        
     })
    },
    dsFormatter(row, column, cellValue) {
      if (cellValue == 1) return "是";
      else return "否";
    },
    setAddress(d) {
      if(d && d.isDefaulte=='1'){
        this.buyBill.recAddressId = d.id;
        this.buyBill.recLinkman = d.lxr;
        this.buyBill.recLinkmanPhone = d.lxrPhone;
        this.buyBill.recAddress = (""+d.province + d.city + d.area + d.address).replace(/null/g,"");
      }
    },
    dataComplete(){
      this.loading = false
    },
  },

  mounted() {
    var end = new Date();
    var start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
    this.filters.sDate = [start, end];
    var billId = this.$route.query.billId;
    if (billId) {
      this.filters.billId = billId;
    }
    this.getDeptBuyList(1);
    this.getFundTypes();//获取资金来源
  }
};
</script>

<style>
#purchase-audit-buy .point {
  color: #ff0000;
}
#purchase-audit-buy .RO-Main-tableheight {
  height: calc(100% - 90px);
  font-size: 12px;
}
.purAudit-tableheight{
        height: calc(100% - 100px);
}

#purchase-audit-buy .el-dialog__body{
       height:  calc(100% - 150px);
       
       padding-bottom: 0px;

}
#purchase-audit-buy .el-dialog--center{
       height:  calc(100% - 0px);
       margin-top: 0px;
}
#purchase-audit-buy .el-dialog__wrapper{
       height:  calc(90% - 0px);
}
</style>
