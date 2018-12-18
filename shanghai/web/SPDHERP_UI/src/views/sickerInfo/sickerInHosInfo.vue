<!--在院病人信息总览-->
<template>
  <div  style="height: 100%;" id="sickerInHos" @keyup.enter="getParitentList(1)">
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 99%;">
      <!--工具条-->
      <div style="padding-bottom: 1px;">
        <el-form :inline="true" :model="filters" label-width="100px" ref="patientsName">
          <el-form-item label="入院日期范围：">
            <el-date-picker v-model="filters.sDate" unlink-panels type="daterange" align="right"
                            unlink-panels range-separator="至" start-placeholder="开始日期"  @change="selectDateRangChange"
                            end-placeholder="结束日期" :picker-options="pickerOptions2"  :clearable="false" :editable='false'>
            </el-date-picker>
          </el-form-item>

          <el-form-item label="入住科室：">
            <el-select v-model="deptCode" filterable placeholder="请选择" @change="deptChange">
              <el-option
                v-for="item in userDepts"
                :key="item.deptCode"
                :label="item.deptName"
                :value="item.deptCode">
                <span style="float: left;font-size: 12px">{{ item.deptName }}</span>
                <span style="float: right; color: #8492a6; font-size: 6px">{{item.attrName}}</span>
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="病人姓名：" prop="patientName">
            <el-input v-model="filters.patientName" :clearable="true" placeholder="输入病人姓名" ></el-input>
          </el-form-item>
          <el-button-group>
            <el-button type="primary" v-on:click="getParitentList(1)" >查询</el-button>
            <el-button type="primary" v-on:click="resetDeptsName('patientsName')" >重置</el-button>
          </el-button-group>
        </el-form>
      </div>
      <!--在院病人基本信息-->
      <template>
        <el-table ref="paritentsTable" :data="patients"  row-key='id'
                  highlight-current-row v-loading="loading"  border fit
                  class="deptgoods-tableheight"  :row-class-name="tableRowClassName" style="margin-top: 5px">
          <el-table-column type="index" align="center"  width="40" label="序号"></el-table-column>
          <el-table-column prop="patientName" align="center" min-width="70" label="姓名" ></el-table-column>
          <el-table-column prop="patientSex" align="center" label="性别" width="40"></el-table-column>
          <el-table-column prop="inDeptDate" align="center" label="入科室日期" width="140"></el-table-column>
          <el-table-column prop="oprationDate" align="center" label="手术日期" width="140"></el-table-column>
          <el-table-column prop="doctor" header-align="center" align="center" label="经治医生" width="70"></el-table-column>
          <el-table-column prop="sickroomCode" header-align="center" align="center" label="病房代码" width="60"></el-table-column>
          <el-table-column prop="bedNum" header-align="center" align="center" label="床号" width="40"></el-table-column>
          <el-table-column prop="illnessStatus" header-align="center" align="center" label="病情状态" width="120"></el-table-column>
          <el-table-column prop="lookforLevel" header-align="center" align="center" label="护理等级" width="70"></el-table-column>
          <el-table-column prop="address" header-align="center" align="center" label="通讯地址" width="350"></el-table-column>
        </el-table>
        <!--分业工具栏-->
        <el-col :span="24" class="toolbar pageBar">
          <el-pagination
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
            :current-page="page"
            :page-sizes="[100, 400, 800, 1000]" :page-size="pageSize" :total="total"
            layout="total, sizes, prev, pager, next" style="float:right;" small>
          </el-pagination>
        </el-col>
      </template>
    </el-col>
  </div>

</template>
<script>
  import '../../common/third/tableExport/tableExport';
  import '../../common/third/tableExport/jquery.base64';
  export default {
    data() {
      return {
        hosId:'',
        deptCode:'',
        hosName:'',
        deptName:'',
        userDepts:[],
        deptIds:[],

        //默认选中当前月
        filters: {
          patientName: '' ,
          orgName:'',
          sDate:[],
        },
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

        total: 0,
        page: 1,
        pageSize: 20,
        loading: false,
        patients: [],
        good: {mfrsId:''},

        tableModel:[],
        // 树
        expandIds: [],
        orgInfo: [],
        orgInfoProps: {
          children: 'children',
          label: 'label',
          id: 'id',
          pid: 'pid'
        },
        // 快速查询
        orgName:''
      }
    },
    methods: {
      /**获取当前用户对应可见科室 */
      getUserDepts:function(){
        var param = {
          hospCode:this.hosId,
        };
        this.axios.post('/spdHERPService/sickerMgr/sickerInHospitalInfo/getHisDepts', param).then(res => {
          if (res.data.code == "0") {
            this.userDepts = res.data.data;
          }else{
            this.$message.error( res.data.msg);
          }
        }, err => {
        });
      },
      deptChange :function(deptCode){
        this.deptCode = deptCode;
        this.getParitentList(1);
      },
      // 点击即查询
      handleNodeClick:function(item){
        this.deptCode = item.deptCode;
        this.deptName = item.label;
        this.$refs.paritentsTable.clearSelection();
        this.getParitentList(1);
      },
      /**月份选择事件 */
      selectDateRangChange:function(){
        this.$refs.paritentsTable.clearSelection();
        this.getParitentList(1);
      },
      /**表格行样式渲染 */
      tableRowClassName (row, index) {
        return (row.row.monthQty > row.row.mQtyUpper) ? 'red-row' : ''
      },
      //获取列表数据
      getParitentList: function (pIndex) {
        this.page = pIndex;
        var param = {
          orderBy: "",
          queryObject: {
            patientName: this.filters.patientName,
            hosId:this.hosId,
            deptCode:this.deptCode,
            sDate: this.filters.sDate?this.filters.sDate[0]:null,
            eDate: this.filters.sDate?this.filters.sDate[1]:null
          },
          page: this.page,
          rows: this.pageSize
        };
        this.loading = true;
        this.axios.post('/spdHERPService/sickerMgr/sickerInHospitalInfo/listByPage', param).then(res => {
          this.loading = false;
          if (res.data.code == "0") {
            this.patients = res.data.data.data;
            this.total = res.data.data.total;
          }else{
            this.$message.error( res.data.msg);
          }
        }, err => {
          this.loading = false;
        });
      },
      resetDeptsName:function (patientsName) {
        this.$refs[patientsName].resetFields();
        //this.$refs.paritentsTable.clearSelection();
        this.deptCode = '';
        this.getParitentList(1);
      },

      /**分业处理事件 */
      handleCurrentChange : function(val) {
        this.page = val;
        this.getParitentList(this.page);
      },
      handleSizeChange:function(val) {
        this.pageSize = val;
        this.getParitentList(this.page);
      }
    },
    mounted() {
      this.hosId = this.user.corpId;
      /**查询时间默认为 上月25号至本月25号 */
      var nowDate = new Date();
      var thisMonth = nowDate.getMonth() + 1;
      var thisDay = nowDate.getDate();
      var start = null;
      if(thisMonth==1){
        start = new Date((nowDate.getFullYear()-1)+'/'+12+'/25 00:00:00');
      }else{
        start = new Date(nowDate.getFullYear()+'/'+nowDate.getMonth()+'/25 00:00:00');
      }
      var end = new Date();
      end.setTime(end.getTime() + 3600 * 1000 * 24 * (25-thisDay));
      this.filters.sDate = [start, end];
      this.getUserDepts();
      this.getParitentList(1);//selectDateRangChange事件里触发查询
    }
  };

</script>
<style>
  #sickerInHos .el-checkbox__label{
    font-size: 12px;
    vertical-align: middle;
  }
  #sickerInHos .el-checkbox-group{
    vertical-align: middle;
  }
  #sickerInHos .deptgoods-tableheight {
    width: 100%;
    height:  calc(100% - 90px);
  }
  #sickerInHos .deptGoods{
    border-radius: 6px;
    padding-bottom: 10px;
    padding-top: 12px;
    padding-right: 19px;
    border: 1px solid #d1dbe5;
    padding-left: 0px;
    margin-left: 15px;
    margin-right: 15px;
  }
  #sickerInHos .deptGoods .el-row{
    height: 36px;
  }

  #sickerInHos .deptMonthPurchaseForm{
    padding-bottom: 10px;
    /* padding-top: 20px;
     padding-right: 19px;*/
    border: 1px solid #d1dbe5;
    padding-left: 0px;
    margin-top:57px;
  }
  #sickerInHos .pageBar{
    border-bottom: 1px solid #d1dbe5;
    border-left: 1px solid #d1dbe5;
    border-right: 1px solid #d1dbe5;
  }

  #sickerInHos .red-row {
    color: #ff2020;
  }
</style>
