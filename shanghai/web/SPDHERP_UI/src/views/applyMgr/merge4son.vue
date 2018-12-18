<template>
    <el-row :gutter="5" v-loading="ploading" element-loading-text="正生成采购单请稍后..." style="height: 100%;">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form label-width="80px" label-position="right">
                <el-col :span="6">
                    <el-form-item label="库房：">
                        <Stock4Slt v-model="filters.stockId" :hosId="this.user.corpId" :deptId='user.orgId'></Stock4Slt>
                    </el-form-item>
                </el-col>
                <el-col :span="10">
                    <el-form-item label="请领日期：">
                        <el-date-picker v-model="filters.sDate" type="daterange" align="right" placeholder="选择日期范围" :picker-options="pickerOptions2" >
                        </el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="物资名称：">
                        <el-input v-model="filters.goodsName"  placeholder="物资名称"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" icon="search" @click="search" >查询</el-button>
                </el-col>
            </el-form>
        </el-col>
    
        <el-col :span="24" style="height:calc(100% - 36px);">            
            <el-table :data="applyDetailList" highlight-current-row border v-loading="sLoading" style="width: 100%;" fit  class="table_sub_content" tooltip-effect="light" @selection-change="sltRows" @current-change="curRChange" row-key="goodsId">
                <el-table-column type="selection" width="30">
                </el-table-column>                
                <el-table-column prop="applyDeptName"  label="请领科室" sortable width="90">
                   
                </el-table-column>
                <el-table-column prop="storeQty" label="库房数量" sortable width="85">
                </el-table-column>
                <el-table-column prop="qty" label="请领数量" sortable width="85">
                </el-table-column>
                <el-table-column prop="goodsName" label="物资名称" sortable width="150" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="goodsGg" label="规格" width="60" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="unit" label="单位" sortable width="60">
                </el-table-column>
                <!-- <el-table-column prop="price" label="单价" sortable width="60">
                </el-table-column> -->
                <!-- <el-table-column prop="je" label="金额" width="60">
                </el-table-column> -->
                <el-table-column prop="packeage" label="包装规格" sortable width="85">
                </el-table-column>
                <el-table-column prop="mfrsName" label="生产厂家" show-overflow-tooltip>
                </el-table-column>
            </el-table>
            <el-table :data="applyMainList" highlight-current-row v-loading="mLoading" style="width: 100%;" fit  class="table_sub_content" tooltip-effect="light" @selection-change="mainListChange" row-key="subId">
                <el-table-column type="selection" width="30" reserve-selection>
                </el-table-column>
                <el-table-column label="操作" sortable>
                    <template slot-scope="scope">
                        <el-button  @click="rejectDetail(scope.row,30)" type="text">不采购</el-button>
                        <el-button  @click="rejectDetail(scope.row,20)" type="text">驳回</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="billId" label="请购单号" sortable>
                </el-table-column>
                <el-table-column prop="buyDeptName" label="科室名称" sortable>
                </el-table-column>
                <el-table-column prop="fillDate" label="请购时间" sortable>
                    <template slot-scope="scope">{{scope.row.fillDate | dateFormat}}</template>
                </el-table-column>
                <el-table-column prop="qty" label="请购数量">
                </el-table-column>
                <!-- <el-table-column prop="je" label="金额">
                </el-table-column> -->
            </el-table>
            <el-col :span="24" class="toolbar" style="margin-top:10px; padding-bottom: 0px;text-align: center;">
                <el-button type="primary"  @click="mergerDeptBy">合并请购单</el-button>
            </el-col>          
        </el-col>        
    </el-row>
</template>

<script>
//请领采购
import Vue from 'vue';
//import colAndProvs from '../../components/colAndProvs';
import Stock4Slt from '../../components/Stock4Slt';
import moment from 'moment';
var Child =  {
  template: '<div><p v-for="item in errMsg" v-html="item"></p><p v-if="vGoOn">是否忽略以上物资，继续生成！</p></div>',
  props:["errMsg","vGoOn"]
};
export default {
    data() {
        return {
            filters: {
                sDate: [],
                goodsName: '',
                stockId: ''
            },
            sLoading: false,
            mLoading: false,
            ploading: false,
            pickerOptions2: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            },
            sltGoods: [],
            sltMainList:[],
            applyDetailList: [],
            applyMainList: [],
            provList:[]
        };
    },
    components: { Stock4Slt},
    methods: {        
        whQtyChange(row,val) {
            row.whQty = val;
            row.je = (row.whQty * row.price) || 0;
        },
        search() {
            var _this = this;
            this.applyMainList = [];
            this.applyDetailList = [];
        
            if (!this.filters.stockId) {
                return;
            }
            var param = {
                goodsName: this.filters.goodsName,
                stockId: this.filters.stockId,
                sDate: this.filters.sDate[0],
                eDate: this.filters.sDate[1],
                hosId:this.user.corpId,
                subState:11,
                buyKind:10,
			    sourceWarehouseId:this.user.orgId
            };
            this.sLoading = true;
            this.axios.post('/spdHERPService/applyMgr/deptBuySub/getDetailsVo4Merger', param).then(res => {
                _this.sLoading = false;
                var applyDetailList = res.data.data;
                if(applyDetailList.length > 0){
                    applyDetailList.forEach(item=>{
                        item.whQty = Number(item.qty) - Number(item.storeQty); 
                    });
                     _this.applyDetailList = applyDetailList;
                    _this.getMainList(_this.applyDetailList[0].goodsId);
                } 
            }, err => {
                _this.sLoading = false;
            });
        },
        curRChange(row) {
            if (row) {
                this.getMainList(row.goodsId);
            }
        },
        mainListChange(rows){
            this.sltMainList=rows;
        },
        sltRows(rows) {
            this.sltGoods = rows;
        },
        getMainList(goodsId) {
            var _this = this;
            this.mLoading = true;
            this.MainListApi(goodsId).then(res => {
                _this.mLoading = false;
                _this.applyMainList = res.data.data;
            }, err => {
                _this.mLoading = false;
            });
        },
        MainListApi(goodsId){
            var param = {
                goodsId: goodsId,
                hosId: this.user.corpId,
                sDate: this.filters.sDate[0],
                eDate: this.filters.sDate[1],
                subState:10,
                buyKind:10
            };            
           return this.axios.post('/spdHERPService/applyMgr/deptBuyMain/getMainBillVo4merger', param);
        },
        notPur(goodsId) {   //不采购 批量操作
            var _this = this;
            this.MainListApi(goodsId).then(res=>{
                var lst = res.data.data;
                if(lst){
                    var params =[];
                    lst.foreach(item=>{
                        var param={
                            billId:item.billId,
                            id:item.subId,
                            subState:30,
                            warehouseDealMan:_this.user.userId
                        };
                        params.push(params);
                    });
                    _this.changeState(goodsId,params);
                }
            },err=>{});
        },
        rejectDetail(row,status) {
             var param={
                billId:row.billId,
                rowNumber:row.rowNumber,
                subState:status || 30,
                warehouseDealMan:this.user.userId
            };
            this.changeState(row.goodsId,[param]);
        },
        changeState(goodsId,param){
            var _this = this;
             _this.axios.post('/spdHERPService/applyMgr/deptBuySub/dealBill4Pur',param).then(res=>{
                if(res.data.code == 0){
                    _this.$message({ message: '操作成功！',type: 'success' });
                    _this.search();                  
                }else{
                    _this.$message({ message: '操作失败！',type: 'error' });
                }
            },err=>{ _this.$message({ message: '操作失败！',type: 'error' });});
        },
        mergerDeptBy() {//合并请购单
            var _this = this;
            this.ploading = true;
            var errMsg = [];   
            const h = _this.$createElement;
            var methods = [];
            if( this.sltGoods.length == 0){
                this.$message.error('请选择要采购的物资');
                 _this.ploading = false;
                return;
            }
            if(this.sltMainList.length == 0){
                this.$message.error('请选择要采购的请购单');
                _this.ploading = false;
                return;
            }
            var mainDateList = [];
            this.sltGoods.forEach(item=>{
                var mainList = this.sltMainList.filter(mItem=>{
                   return mItem.goodsId == item.goodsId;
                });
                if(mainList.length>0){
                    mainDateList.push(mainList);
                }else{
                    errMsg.push(`物资：<font color='red'>${item.goodsName}</font>下没有选择单据。`);                    
                }
            });
            if(errMsg.length>0){
                _this.ploading = false;                
                _this.$msgbox({
                    title:"错误",type:"error", 
                    message:h(Child,{props:{errMsg:errMsg}})
                });
                return false;
            }
            _this.creatDeptByBill(mainDateList);
             //_this.createByNoSlt();
            
        },
        createByNoSlt(){
            var _this = this;
            for(var j = 0;j < this.sltGoods.length;j++){
                methods.push(_this.MainListApi(this.sltGoods[j].goodsId)); 
            }            
            _this.axios.all(methods).then(_this.axios.spread(function () {
                if(arguments.length>0){
                    var mainList = Array.prototype.slice.apply(arguments).map(item=> {return item.data.data;});
                    _this.creatDeptByBill(mainList);
                }
            }));
        },
        creatDeptByBill(mainListData){
            var errMsg =[];
            var _this = this;
            const h = _this.$createElement;
            var purOrder = {
                provId: this.filters.provId,
                status: '0',
                purKind: 1,
                purchaseList: []
            };
            var sltGoods = _this.sltGoods;
            var sltMainList = _this.sltMainList;
            //循环勾选的商品信息
            for(var j = 0;j<sltGoods.length;j++){
                var goodsInfo = sltGoods[j];
                if(goodsInfo.code){
                    var subIds = [];
                    var qty = 0;
                    //循环主单计算需求数量
                    for(var k=0;k<sltMainList.length;k++){
                        var main = sltMainList[k];
                        if(goodsInfo.goodsId==main.goodsId){
                            qty += main.qty;
                            subIds.push(main.subId);
                        }
                    }
                    var purDetail = {
                    	goodsId:goodsInfo.goodsId,
                    	hosId:goodsInfo.hosId,
                        code: goodsInfo.code,
                        goodsName: goodsInfo.goodsName,
                        goodsGg: goodsInfo.goodsGg,
                        made: goodsInfo.made,
                        unit: goodsInfo.unit,
                        packetCode: goodsInfo.packetCode,
                        price: goodsInfo.price,
                        qty: goodsInfo.storeQty,
                        isPacket: goodsInfo.isPacket,
                        needQty: qty,
                        needPacketQty:goodsInfo.needPacketQty,
                        generalName:goodsInfo.generalName,
                        goodsDesc:goodsInfo.goodsDesc,
                        buyKind:goodsInfo.buyKind,
                        subIds:subIds
                    };
                    purOrder.purchaseList.push(purDetail); 
                }
            }
            _this.ploading = false;            
            if(errMsg.length>0){   
                _this.$msgbox({title:"警告",type:"warning",showCancelButton:true,message:h(Child,{props:{errMsg:errMsg,vGoOn:true}}),
                callback:action=>{
                    if(action=="confirm"){                        
                        _this.setPurBill(purOrder);
                    }
                }});
                return false;
            }else{
                _this.setPurBill(purOrder);
            }
        },
        setPurBill(purOrder){
            var _this = this;
            if(purOrder.purchaseList.length > 0){
                var sessionId = _this.user.corpId + _this.user.orgId + moment(new Date()).format("YYYYMMDDHHmmss");
                LocalStorage.setItem(sessionId, JSON.stringify(purOrder));
                _this.$router.push({path:"merge4add", query : {applyInfo: sessionId}});
            }
        }
    },
    mounted() {
        var end = new Date();
        var start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
        this.filters.sDate = [start, end];
   		this.search();
    }
}
</script>

<style>
.send-type {
    float: right;
}
.table_sub_content{
    font-size: 12px;
    height: calc(48% - 25px);
    overflow-y: auto;
}
.prov_list{
    list-style-type:none;
    padding:0px;
    margin: 0px;
}
.prov_list li{
    height: 25px;
    line-height: 25px;
    padding:0px 0px 0px 10px;
    cursor: pointer;
    border-bottom: 1px solid #ccc;
}
.prov_info{
    height:100%;
    width:100%;
    border: 1px solid #ccc;
    overflow: auto;
}
.prov_list li:hover{   
    background-color: #ccc;   
}
</style>