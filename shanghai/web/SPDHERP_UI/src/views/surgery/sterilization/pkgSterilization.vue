<template>
	<div id="sterilization" style="height: 100%;">	
		<div v-if="editFormVisible==false" style="height: 100%;">
			<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
				<el-form :inline="true" label-width="80px">
					<el-form-item label="手术包码">
						<el-input v-model="filters.surCode" placeholder="手术包码" ></el-input>
					</el-form-item>
					<el-form-item label="手术包名">
						<el-input v-model="filters.surName" placeholder="手术包名" ></el-input>
					</el-form-item>
					<el-form-item label="消毒状态" >
						<el-select v-model="filters.status" placeholder="请选择" style="width:100px;" @change="getSterailizationPkgs(1)">
							<el-option label="未消毒" value="0" />
							<el-option label="已消毒" value="1" />
							<el-option label="已过期" value="2" />
						</el-select>
					</el-form-item>

					<el-button-group>
						<el-button type="primary" icon="search" v-on:click="getSterailizationPkgs(1)" >查询</el-button>
						<el-button type="primary" icon="setting" v-on:click="reset" >重置</el-button>
					</el-button-group>
				</el-form>
			</el-col>
              
			<!--列表-->
			<template>
				<el-table key="pkgTable" ref="pkgTable" :data="sterilizationPkgList" highlight-current-row v-loading="loading" style="width: 100%;" fit border class="pkgTableHeight" >
					<el-table-column type="index" label="序号" width="50" ></el-table-column>
					<el-table-column prop="surCode" label="手术包码" ></el-table-column>
					<el-table-column prop="surName" label="手术包名" ></el-table-column>
					<el-table-column prop="deptName" label="请购科室" > </el-table-column>
					<el-table-column prop="sickerName" label="患者姓名" > </el-table-column>
					<el-table-column prop="inHosCode" label="入院号" > </el-table-column>
					<el-table-column prop="surScheduleNo" label="手术编号" > </el-table-column>
		<!-- 			<el-table-column prop="applyBillId" label="请购单号" > </el-table-column>                            
					<el-table-column prop="curStockId" label="当前库房" ></el-table-column> -->
					<el-table-column prop="status" label="状态" :formatter="statusFormat" ></el-table-column>
					<el-table-column label="操作" width="150">
							<template slot-scope="scope">
								<el-button @click="sterilizationBill(scope.row)" type="text">消毒</el-button>
							</template>
					</el-table-column>
				</el-table>
				<!--工具条-->
				<el-col :span="24" class="toolbar">
						<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next" :total="total" style="float:right;" small>
						</el-pagination>
				</el-col>
			</template>
		</div>

		<div v-if="editFormVisible==true" style="height: 100%;">
				<template>
					<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
						<el-form label-width="120px">
							<el-col :span="8">
								<el-form-item label="手术包码:">
									<span>{{ sterilizaListBill.surCode }}</span>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item label="手术包名:">
										<span>{{ sterilizaListBill.surName }}</span>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item label="请购科室:">
									<span>{{ sterilizaListBill.deptName }}</span>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item label="患者姓名:">
									<span>{{ sterilizaListBill.sickerName }}</span>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item label="入院号:">
									<span>{{ sterilizaListBill.inHosCode }}</span>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item label="手术编号:">
									<span>{{ sterilizaListBill.surScheduleNo }}</span>
								</el-form-item>
							</el-col>
							<el-col :span="6">
								<el-form-item label="ID:" v-show="false">
									<span>{{ sterilizaListBill.id }}</span>
								</el-form-item>
							</el-col>												
						</el-form>
					</el-col>
				</template>

			<!--消毒明细列表-->
			<template>
				<el-table key="pkgListTable" ref="pkgListTable" :data="surgeryPkgDetail" style="width: 100%;" fit border tooltip-effect="dark" class="pkgListTableHeight"  @selection-change="handleSelectionChange" :row-class-name="tableRowClassName">
					<el-table-column type="selection" align="center" :reserve-selection="true" width="40"> </el-table-column>
					<el-table-column type="index" label="序号" width="45">
					</el-table-column>
					<el-table-column prop="hosGoods.goodsName" label="商品名称" width="180" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="hosGoods.goodsGg" label="规格" width="150" show-overflow-tooltip>
					</el-table-column>

					<el-table-column prop="qty" label="数量" width="50">
					</el-table-column>
					<el-table-column label="消毒人" width="120">
							<template slot-scope="scope">
								<el-input placeholder="请输入消毒人" v-model="scope.row.firstSterilizer">
								</el-input>
							</template>
					</el-table-column>
					<el-table-column label="消毒时间" width="200">
							<template slot-scope="scope">
								<el-date-picker v-model="scope.row.firstSterilizeDate" type="datetime" placeholder="消毒时间" value-format="yyyy-MM-dd hh:mm:ss" >
								</el-date-picker>
							</template>
					</el-table-column>
					<el-table-column label="消毒效期" width="200">
							<template slot-scope="scope">
								<el-date-picker v-model="scope.row.sterilizeExpdate"  type="datetime" placeholder="消毒效期" value-format="yyyy-MM-dd hh:mm:ss" >
								</el-date-picker>
							</template>
					</el-table-column>

					<el-table-column prop="hosGoods.mfrsName" label="厂家" width="180" show-overflow-tooltip>
					</el-table-column>
						<el-table-column prop="hosGoods.made" label="产地" width="80" show-overflow-tooltip>
						</el-table-column>
					<el-table-column prop="hosGoods.unit" label="单位" width="50">
					</el-table-column>

				</el-table>
				<el-col :span="24" class="toolbar" style="padding-top: 6px">
					<el-pagination @size-change="handleDetailSizeChange" @current-change="handleDetailCurrentChange" :current-page="detailPage" :page-sizes="[20, 30, 50, 100]" :page-size="detailPageSize" layout="total, sizes, prev, pager, next" :total="detailTotal" style="float:right;" small>
					</el-pagination>

					<el-button :plain="true" type="success" style="margin-left: 20px"  @click="submitSterilization()">提交</el-button>
					<el-button :plain="true" type="danger"  @click="cancel">取消</el-button>
				</el-col>
			</template>
	
		</div>
	</div>

</template>
<script>
import { SURGERY_PKG_STATUS } from "../common/constance";
import moment from 'moment';
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      filters: {
				surCode: '',
				surName: '',
				status: '0',
				sickerName: '',
				surScheduleNo: '',
        sDate: []
      },

      surCode: "",
      total: 0,
      page: 1,
      pageSize: 20,
      sterilizationPkgList: [],
      loading: false,
      editFormVisible: false,
      modelTitle: null,

      detailTotal: 0,
      detailPage: 1,
      detailPageSize: 20,

			surgeryPkgDetail:[],
			multipleSelection: [],
      sterilizaListBill: {
				surgeryPkgListVos:[]
			}
    };
  },
  components: { },
  methods: {
		tableRowClassName({row, rowIndex}) {
			if(!row.firstSterilizer){
				return "";
			}
			const expdtEndDate = moment(row.sterilizeExpdate);
			const now = moment();
			if (expdtEndDate.isBefore(now)) {
				return "warning-red";
			} else if (expdtEndDate.isBefore(now.add(1, "day"))) {
				return "warning-row";
			}
			return "";
		},
    handleSelectionChange (val) {
      this.multipleSelection = val;
    }, 
    //获取需消毒手术包列表
    getSterailizationPkgs: function(pIndex) {
			this.page = pIndex;
			
			// 未消毒
			if(this.filters.status == '0'){
				this.getSterPkg();
			} else if(this.filters.status == '1' || this.filters.status == '2'){
				// 消毒后未过期和已过期的手术包
				this.getSterPkgExpire();
			}
		},
		// 未消毒的手术包
		getSterPkg:function(){
			if(this.filters.status != '0'){
				return
			}

			let statusArr = []
			if(this.filters.status == '0'){
				// 未消毒
				statusArr = [40, 30]
			}

      let param = {
        orderBy: "",
        page: this.page,
        rows: this.pageSize,
        queryObject: {
          surCode: this.filters.surCode,
					surName: this.filters.surName,
          hosId: this.user.corpId,
          statusArr: statusArr
        }
      };
      this.loading = true;
      this.axios.post("/spdHERPService/surgery/sterilization/getFirstSterilizationPkgByPage", param).then(
        res => {
          this.loading = false;
					this.sterilizationPkgList = res.data.data.data;
          this.total = res.data.data.total;
        },
        err => {
          this.loading = false;
        }
      );
		},
		// 已全部消毒未过期和已过期的手术包
		getSterPkgExpire:function(){
			if(this.filters.status != '1' && this.filters.status != '2'){
				return
			}
      let param = {
        orderBy: "",
        page: this.page,
        rows: this.pageSize,
        queryObject: {
          surCode: this.filters.surCode,
					surName: this.filters.surName,
					hosId: this.user.corpId,
					selType: this.filters.status,
          statusArr: [32]
        }
      };
      this.loading = true;
      this.axios.post("/spdHERPService/surgery/sterilization/listFirstExpireByPage", param).then(
        res => {
          this.loading = false;
					this.sterilizationPkgList = res.data.data.data;
          this.total = res.data.data.total;
        },
        err => {
          this.loading = false;
        }
      );
		},
    reset: function() {
      this.filters.surCode = "";
			this.filters.surName = "";
			this.filters.status = "0";
      this.getSterailizationPkgs(1);
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getSterailizationPkgs(this.page);
    },
    handleSizeChange(val) {
      this.detailPageSize = val;
      this.getSterailizationPkgs(this.page);
    },
		handleDetailCurrentChange(val) {
      this.page = val;
      this.getSurPkgListDetail(this.sterilizaListBill.surCode, this.detailPage);
    },
    handleDetailSizeChange(val) {
      this.detailPageSize = val;
      this.getSurPkgListDetail(this.sterilizaListBill.surCode, this.detailPage);
    },
    statusFormat(row) {
			if(SURGERY_PKG_STATUS[row.status]){
				return SURGERY_PKG_STATUS[row.status].text;
			} else {
				return row.status
			}
    },

    sterilizationBill(row) {
			this.editFormVisible = true;
			this.sterilizaListBill = row;
      this.getSurPkgListDetail(row.surCode);
    },

    getSurPkgListDetail(surCode, pIndex) {
			this.detailPage = pIndex;
      let param = {
        orderBy: "",
        page: this.detailPage,
        rows: this.detailPageSize,
        queryObject: {
          surCode: surCode
        }
      };
      this.axios
        .post("/spdHERPService/surgery/sterilization/getFirstSterilizationListByPage", param)
        .then(
          res => {
            if (res.data.code == "0") {
							this.surgeryPkgDetail = res.data.data.data;
							this.detailTotal = res.data.data.total;
							this.surgeryPkgDetail.forEach(item=>{
								if(!item.firstSterilizer){
									item.sterilizeExpdate = null
									item.firstSterilizeDate = null
								}
							})
            }
          },
          err => {}
        );
    },
    cancel() {
			this.editFormVisible = false;
			this.getSterailizationPkgs(1);
    },
  
    submitSterilization: function() {
			if (this.multipleSelection.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
			}
			
			let checkFlag = true;
			this.multipleSelection.some(item=>{
				if(!item.firstSterilizer || !item.firstSterilizeDate || !item.sterilizeExpdate){
					checkFlag = false;
				}
			})
			if(!checkFlag){
					this.$message.error('消毒人、消毒时间和消毒效期均不能为空')
					return
			}

			const h = this.$createElement;
      let arr = new Array();
			this.multipleSelection.forEach((item, index)=>{
				const expdtEndDate = moment(item.sterilizeExpdate);
				const sterilizaDate = moment(item.firstSterilizeDate);
				if (expdtEndDate.isSameOrBefore(sterilizaDate)) {
					arr.push(h("span", null,  `明细第${index + 1}行："${item.hosGoods.goodsName}"的消毒效期必须晚于消毒时间`));
				}

			})

			if(arr && arr.length > 0){
				this.$msgbox({title: "错误", type: "error", message: h("p", null, arr)});
				return
			}



			this.loading = true;

			this.sterilizaListBill.surgeryPkgListVos = this.multipleSelection

      this.axios
        .post("/spdHERPService/surgery/sterilization/updateFirstSterilization", this.sterilizaListBill)
        .then(
          res => {
            this.loading = false;
            if (res.data.code == "0") {
              this.$msgbox({
                title: "提示",
                message: "操作成功",
                type: "success"
              });
              this.editFormVisible = false;
              this.getSterailizationPkgs(1);
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
   
  },

  mounted() {
    var end = new Date();
    var start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
    this.filters.sDate = [start, end];
    this.getSterailizationPkgs(1);
  }
};
</script>

<style>
	#sterilization .pkgTableHeight {
		height: calc(100% - 70px);
		font-size: 12px;
	}
	#sterilization .pkgListTableHeight {
		height: calc(100% - 105px);
		font-size: 12px;
	}

	#sterilization .el-date-editor--datetime {
		width: 180px;
	}

  #sterilization .el-table .warning-red {
    color: #F56C6C;
  }

  #sterilization .el-table .warning-row {
    color: #E6A23C;
  }

</style>