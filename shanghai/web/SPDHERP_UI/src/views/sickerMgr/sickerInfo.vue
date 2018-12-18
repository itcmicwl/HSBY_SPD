<template>
  <div id="mySicker" style="height:100%;">
    <!--工具条-->
    <el-col :span="24" style="height: 99%">
        <div>
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
          <el-form :inline="true" :model="filters" ref="sickerInfoForm">
            <el-form-item label="姓名：" prop="patientName">
              <el-input v-model="filters.patientName" placeholder="姓名" ></el-input>
            </el-form-item>
            <el-form-item label="身份证号：" prop="patientCard">
              <el-input v-model="filters.patientCard" :clearable="true" label="身份证号" ></el-input>
            </el-form-item>

            <el-form-item label="住院号：" prop="patientInHosId">
              <el-input v-model="filters.patientInHosId" placeholder="住院号" ></el-input>
            </el-form-item>
            <el-button-group>
              <el-button type="primary" v-on:click="getSickerInfoList(1)" >查询</el-button>
              <el-button type="primary" v-on:click="resetSickerInfo('sickerInfoForm')"  >重置</el-button>
            </el-button-group>
          </el-form>
        </el-col>
        </div>
        <!--列表-->
        <template>
          <el-table :data="sickerInfos" id="sickerTable"  highlight-current-row v-loading="loading"  border fit class="mySicker-tableheight">
            <el-table-column type="index" header-align="center" label="序号"></el-table-column>
            <el-table-column label="住院号" header-align="center"  show-overflow-tooltip prop="patientInHosId" sortable></el-table-column>
            <el-table-column label="姓名" header-align="center" width="100" show-overflow-tooltip prop="patientName"></el-table-column>
            <el-table-column label="性别" header-align="center" width="50" show-overflow-tooltip prop="patientSex"></el-table-column>
            <el-table-column label="出生日期" header-align="center" width="100" show-overflow-tooltip prop="patientBirthday">
                <template slot-scope="scope">
                    {{formatDate(scope.row.patientBirthday)}}
                </template>
            </el-table-column>
            <el-table-column label="身份证号" header-align="center" width="180" show-overflow-tooltip prop="patientCard"></el-table-column>
            <el-table-column label="通讯地址" header-align="center" width = "300" show-overflow-tooltip prop="address"></el-table-column>
            <el-table-column label="上次就诊日期" width="150" header-align="center" show-overflow-tooltip prop="lastLookDoctorDate"></el-table-column>

            <el-table-column label="操作" align="center" width="70"  >
              <template slot-scope="scope">
				<el-button size="mini" @click="querySickerInfo(scope.row)" type="info" icon="search">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 工具条 -->
          <el-col :span="24" class="toolbar">
            <el-pagination   @size-change="handleSizeChange" 
            style="float:right;" small @current-change="handleCurrentChange" 
            :current-page="page" layout="total, sizes, prev, pager, next" 
            :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" :total="total">
            </el-pagination>
          </el-col>
        </template>

        <!-- 病人详情 开始 -->
        <el-dialog title="病人详情" close="onClose" :visible.sync="querySickerInfoVisible" :close-on-click-modal="false" width="65%">
            <el-form :model="sicker"  label-width="95px"  class="el-form-item-nomsg " labelPosition='right'>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="姓名："  prop="patientName" >
                            <el-input v-model="sicker.patientName" disabled  ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="姓名拼音"  prop="patientShortPinyin">
                            <el-input v-model="sicker.patientShortPinyin" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="病人标识号：" prop="stocId">
                            <el-input v-model="sicker.patientId" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="医院ID：" prop="hosId">
                            <el-input v-model="sicker.hosId" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="住院号：" prop="patientInHosId">
                            <el-input v-model="sicker.patientInHosId" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="性别：" prop="patientSex">
                            <el-input v-model="sicker.patientSex" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="出生日期：" prop="patientBirthday">  
                            <el-input v-model="sicker.patientBirthday" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="出生地：" prop="patientBornAddress">
                            <el-input v-model="sicker.patientBornAddress" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="国籍：" prop="patientCountry">
                            <el-input v-model="sicker.patientCountry" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="民族：" prop="patientNation">
                            <el-input v-model="sicker.packetCode" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="身份证号：" prop="patientCard">
                            <el-input v-model="sicker.patientCard" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="身份：" prop="patientType">
                            <el-input v-model="sicker.patientType" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="费别：" prop="costKind">
                            <el-input v-model="sicker.costKind" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="合同单位：" prop="contractUnit">
                            <el-input v-model="sicker.contractUnit" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="通讯地址：" prop="address">
                            <el-input v-model="sicker.address" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="邮编："  prop="postCode">
                            <el-input v-model="sicker.postCode" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="家庭电话："  prop="familyPhoneNum">
                            <el-input v-model="sicker.familyPhoneNum" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="单位电话："  prop="unitPhoneNum">
                            <el-input v-model="sicker.unitPhoneNum" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="联系人："  prop="linkName">
                            <el-input v-model="sicker.linkName" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="联系人关系："  prop="linkRelation">
                            <el-input v-model="sicker.linkRelation" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="联系人地址："  prop="linkAddress">
                            <el-input v-model="sicker.linkAddress" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="联系人邮编："  prop="linkPostCode">
                            <el-input v-model="sicker.linkPostCode" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="联系人电话："  prop="linkPhoneNum">
                            <el-input v-model="sicker.linkPhoneNum" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="上次就诊日："  prop="lastLookDoctorDate">
                            <el-input v-model="sicker.lastLookDoctorDate" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="重要人物："  prop="isImportment">
                            <el-input v-model="sicker.isImportment" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="建卡日期："  prop="fillDate">
                            <el-input v-model="sicker.fillDate" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="30">
                    <el-col :span="12">
                        <el-form-item label="操作员："  prop="filler">
                            <el-input v-model="sicker.filler" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="更新时间："  prop="lastUpdateDatetime">
                            <el-input v-model="sicker.lastUpdateDatetime" disabled ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div slot="footer" class="dialog-footer" style="text-align: center;">
                <el-button @click.native="querySickerInfoVisible = false" >取消</el-button>
            </div>
        </el-dialog>
        <!-- 详情界面结束 -->

      </el-col>
  </div>
</template>
<script>
    export default {
        data() {
            return {
  
                filters: {
                    patientName:'',
                    //patientId: '',
                    patientInHosId: '',
                    patientCard: ''
                },

                sicker:{},
                sickerInfos:[],

                total: 0,
                page: 1,
                pageSize:20,
                loading: false,
                
                querySickerInfoVisible: false
            }
        },
        methods: {
            formatDate (item) {
                return new Date(item).toLocaleDateString()
            },
            // 获取病人主索引信息列表
            getSickerInfoList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId: this.user.corpId,
                        patientName: this.filters.patientName.trim(),
                        //patientId: this.filters.patientId.trim(),
                        patientInHosId: this.filters.patientInHosId.trim(),
                        patientCard: this.filters.patientCard.trim()

                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;

                this.axios.post('/spdHERPService/sickerInfo/sickerInfo/listByPage', param).then(res => {
                    this.loading = false;
                    this.sickerInfos = res.data.data.data;
                    this.total = res.data.data.total;

                }, err => {
                    this.loading = false;
                });
                
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getSickerInfoList(this.page);
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.getSickerInfoList(this.page);
            },
            /**
             * 重置病人查询
             */
            resetSickerInfo:function (formName) {
                this.$refs[formName].resetFields();
                this.getSickerInfoList(1);
            },
            // 查看病人详情
            querySickerInfo(sicker) {
                this.querySickerInfoVisible = true;
                let obj = Object.assign({}, sicker);
                this.sicker = obj;
            }
        },

        mounted() {
            this.getSickerInfoList(1);

 
        }
    };

</script>

<style>
  #mySicker .mySicker-tableheight {
    height: calc(100% - 85px);
  }
</style>