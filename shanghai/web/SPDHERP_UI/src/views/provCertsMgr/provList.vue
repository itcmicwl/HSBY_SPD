<template>
  <div id="myProv">
    <!--工具条-->
    <el-col :span="24" style="height: 100%">
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
          <el-form :inline="true" :model="filters" ref="provInfoForm">
            <el-form-item label="供应商名称：" prop="provName">
              <el-input v-model="filters.provName" placeholder="供应商名称" ></el-input>
            </el-form-item>
            <el-form-item label="联系人：" prop="provLxr">
              <el-input v-model="filters.provLxr" placeholder="联系人" ></el-input>
            </el-form-item>
            <el-button-group>
              <el-button type="primary" v-on:click="getHosProvInfoVoList(1)" >查询</el-button>
              <el-button type="primary" v-on:click="resetProvInfo('provInfoForm')" >重置</el-button>
              <el-button type="success" @click="provHosInfoEdit(null)" >新增</el-button>
            </el-button-group>
          </el-form>
        </el-col>
        <!--列表-->
        <template>
          <el-table :data="provHosInfos" highlight-current-row v-loading="loading" style="width: 100%;" border fit class="myProv-tableheight">
            <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
            <el-table-column prop="provName" label="供应商名称"  header-align="center"></el-table-column>
            <el-table-column prop="provLxr" label="联系人" header-align="center"></el-table-column>
            <el-table-column prop="provLxrTelphonenum" label="联系电话" align="center"></el-table-column>
            <el-table-column prop="lastUpdateDatetime" label="最后更新时间" align="center" sortable></el-table-column>
            <el-table-column label="操作" width="200" align="center">
              <template slot-scope="scope">
                <el-button-group>
                  <el-button size="mini" @click="provHosInfoEdit(scope.row)" type="info" icon="edit">编辑</el-button>
                  <el-button size="mini" @click="queryProvInfo(scope.row)" type="info" icon="search">详情</el-button>
                  <el-button size="mini" @click="deleteSubmit(scope.row)"  type="warning" icon="warning" >解除关系</el-button>
                </el-button-group>
              </template>
            </el-table-column>
          </el-table>
          <!-- 工具条 -->
          <el-col :span="24" class="toolbar">
            <el-pagination v-if="total>0" small @size-change="handlePSizeChange" style="padding-right: 20px;text-align: right;"
                           @current-change="handleCurrentChange" :current-page="page"
                           layout="total, sizes, prev, pager, next" :page-sizes="[20, 30, 50, 100]" :page-size="pageSize" :total="total">
            </el-pagination>
          </el-col>
        </template>
        <!-- 编辑界面 -->
        <el-dialog :title="modelTitle"   close="onClose"  :visible.sync="editFormVisible" :close-on-click-modal="false" size="tiny">
          <el-form :model="provHosInfoVo" label-width="120px" ref="editForm" style="margin-top: 3%" class="el-form-item-nomsg">
            <el-row>
              <el-col>
              <el-form-item label="供应商名称：" prop="provName">
                <el-select v-model="provId" filterable placeholder="请选择" :clearable="true" :disabled="selhos" style="width: 70%;" @change="" >
                  <el-option
                          v-for="item in hosInfos"
                          :key="item.id"
                          :label="item.cname"
                          :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <el-form-item label="联系人：">
                  <el-input v-model="linkman" style="width: 70%;"  placeholder="请输入联系人"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <el-form-item label="联系电话：">
                  <el-input v-model="tel"  style="width: 70%;" placeholder="请输入联系电话"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <el-form-item label="备注：">
                  <el-input type="textarea" :row="3" style="width: 70%;" placeholder="请输入备注信息" v-model="provHosInfoVo.hosRemark" ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer myHos-button-align">
            <el-button @click.native="editFormVisible = false" >取消</el-button>
            <el-button type="primary" @click.native="editSubmit" :loading="editLoading" >提交</el-button>
          </div>
        </el-dialog>
        <!-- 详情界面 -->
        <el-dialog :title="queryProvTitle" style="min-width: 800px;"  close="onClose"  v-model="queryProvVisible" :close-on-click-modal="false" >
          <el-row :gutter="2">
            <!--列表-->
            <template>
              <el-col :span="24">
                <div style="padding-left: 10px;height: 100%">
                  <div id="myProvQuery">
                    <el-form :model="company" label-width="120px">
                      <el-row>
                        <el-col :span="10">
                          <el-form-item label="公司名称：" style="width: 87.5%;margin-bottom: 0px;" prop="cname">
                            <el-col :span="24">
                              <el-input v-model="company.cname" disabled ></el-input>
                            </el-col>
                          </el-form-item>
                          <el-form-item label="企业编号：" style="width: 87.5%;margin-bottom: 0px;" prop="code">
                            <el-col :span="24">
                              <el-input v-model="company.code" disabled ></el-input>
                            </el-col>
                          </el-form-item>
                          <el-form-item label="公司别名：" style="width: 87.5%;margin-bottom: 0px;" prop="anotherName">
                            <el-col :span="24">
                              <el-input v-model="company.anotherName" disabled ></el-input>
                            </el-col>
                          </el-form-item>
                          <el-form-item label="法人代表：" style="width: 87.5%;margin-bottom: 0px;" prop="legal">
                            <el-col :span="24">
                              <el-input v-model="company.legal" disabled ></el-input>
                            </el-col>
                          </el-form-item>
                          <el-form-item label="地址：" style="width: 87.5%;margin-bottom: 0px;" prop="address">
                            <el-col :span="24">
                              <el-input v-model="company.address" disabled ></el-input>
                            </el-col>
                          </el-form-item>
                          <el-form-item label="电子邮箱：" style="width: 87.5%;margin-bottom: 0px;" prop="email">
                            <el-col :span="24">
                              <el-input v-model="company.email" disabled ></el-input>
                            </el-col>
                          </el-form-item>
                        </el-col>
                        <el-col :span="7">
                          <el-form-item labelWidth="11%" style="margin-bottom: 0px;">
                            <img v-if="logoUrl" :src="logoUrl"
                                 style="width: 100%;max-height: 197px;border: 1px dashed lightgray;border-radius: 6px;">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                          </el-form-item>
                        </el-col>
                      </el-row>
                      <el-col :span="24">
                        <el-col :span="10">
                          <el-form-item label="联系人：" style="width: 87.5%;margin-bottom: 0px;" prop="linkman">
                            <el-input v-model="company.linkman" disabled ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="9">
                          <el-form-item label="联系方式：" labelWidth="100px" style="width: 79%;margin-bottom: 0px;" prop="contactWay">
                            <el-input v-model="company.contactWay" disabled ></el-input>
                          </el-form-item>
                        </el-col>
                      </el-col>
                      <el-col :span="24">
                        <el-col :span="10">
                          <el-form-item label="传真：" style="width: 87.5%;margin-bottom: 0px;" prop="contactFax">
                            <el-input v-model="company.contactFax" disabled ></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="9">
                          <el-form-item label="邮政编号：" labelWidth="100px" style="width: 79%;margin-bottom: 0px;" prop="postCode">
                            <el-input v-model="company.postCode" disabled ></el-input>
                          </el-form-item>
                        </el-col>
                      </el-col>
                      <el-col :span="24" style="margin-top: 6px;">
                        <el-form-item label="备注信息：" prop="remark" style="margin-bottom: 0px;">
                          <el-col :span="16">
                            <el-input type="textarea" style="width: 100.5%;margin-bottom: 0px;" :row="2" disabled v-model="company.remark" ></el-input>
                          </el-col>
                        </el-form-item>
                      </el-col>
                      <el-col :span="24">
                        <el-form-item label="企业三证：" style="margin-bottom: 0px;">
                        </el-form-item>
                      </el-col>
                      <el-col :span="24">
                        <!--<el-form-item label="" style="margin-bottom: 0px;width: 103%;">-->
                          <el-col :span="6" v-for="(d,index) in yyzzImgArray" v-bind:key="d.id" class="certsCard">
                            <div style="margin-top: 3%;">
                              <div class="ImageCard">
                                <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                <i v-else class="el-icon-plus"></i>
                              </div>
                            </div>
                            <div style="">
                              <div class="certListStyle">
                                <span class="certWordsStyle">营业执照</span>
                                <span><el-button type="text" class="button certButtonStyle" @click="checkCompanyImg(d)">查看</el-button></span>
                              </div>
                              <div class="certCodes">
                                <el-input placeholder="登记证号" v-model="yyzzObj.certCode" readonly ></el-input>
                              </div>
                              <div class="block" style="margin-left: 3%;">
                                <span class="demonstration"></span>
                                <el-date-picker
                                        style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                        size = "mini"  readonly
                                        v-model="yyzzObj.expdtBeginDate"
                                        type="date"
                                        placeholder="生效日期">
                                </el-date-picker>
                              </div>
                              <div class="block" style="margin-left: 3%;">
                                      <span class="">
                                          <el-checkbox v-model="yyzzChecked" disabled >
                                              <span style="font-size: 10px;">是否长期有效</span>
                                          </el-checkbox>
                                      </span>
                                <el-date-picker
                                        style="width: 96%;margin-bottom: 5%;"
                                        size = "mini"  readonly
                                        v-model="yyzzObj.expdtBeginDate"
                                        type="date"
                                        placeholder="截止日期">
                                </el-date-picker>
                              </div>
                            </div>
                          </el-col>
                          <el-col :span="6" v-for="(d,index) in jyxkImgArray" v-bind:key="d.id" class="certsCard">
                            <div style="margin-top: 3%;">
                              <div class="ImageCard">
                                <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                <i v-else class="el-icon-plus"></i>
                              </div>
                            </div>
                            <div style="">
                              <div class="certListStyle">
                                <span class="certWordsStyle">经营许可证</span>
                                <span><el-button type="text" class="button certButtonStyle" @click="checkCompanyImg(d)">查看</el-button></span>
                              </div>
                              <div class="certCodes">
                                <el-input placeholder="登记证号" v-model="jyxkObj.certCode" readonly ></el-input>
                              </div>
                              <div class="block" style="margin-left: 3%;">
                                <span class="demonstration"></span>
                                <el-date-picker
                                        style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                        size = "mini"  readonly
                                        v-model="jyxkObj.expdtBeginDate"
                                        type="date"
                                        placeholder="生效日期">
                                </el-date-picker>
                              </div>
                              <div class="block" style="margin-left: 3%;">
                                      <span class="">
                                          <el-checkbox v-model="jyxkChecked" disabled >
                                              <span style="font-size: 10px;">是否长期有效</span>
                                          </el-checkbox>
                                      </span>
                                <el-date-picker
                                        style="width: 96%;margin-bottom: 5%;"
                                        size = "mini"  readonly
                                        v-model="jyxkObj.expdtBeginDate"
                                        type="date"
                                        placeholder="截止日期">
                                </el-date-picker>
                              </div>
                            </div>
                          </el-col>
                          <el-col :span="6" v-for="(d,index) in swdjImgArray" v-bind:key="d.id" class="certsCard">
                            <div style="margin-top: 3%;">
                              <div class="ImageCard">
                                <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                <i v-else class="el-icon-plus"></i>
                              </div>
                            </div>
                            <div style="">
                              <div class="certListStyle">
                                <span class="certWordsStyle">税务登记证</span>
                                <span><el-button type="text" class="button certButtonStyle" @click="checkCompanyImg(d)">查看</el-button></span>
                              </div>
                              <div class="certCodes">
                                <el-input placeholder="登记证号" v-model="swdjObj.certCode" readonly ></el-input>
                              </div>
                              <div class="block" style="margin-left: 3%;">
                                <span class="demonstration"></span>
                                <el-date-picker
                                        style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                        size = "mini"  readonly
                                        v-model="swdjObj.expdtBeginDate"
                                        type="date"
                                        placeholder="生效日期">
                                </el-date-picker>
                              </div>
                              <div class="block" style="margin-left: 3%;">
                              <span class="">
                                  <el-checkbox v-model="swdjChecked" disabled >
                                      <span style="font-size: 10px;">是否长期有效</span>
                                  </el-checkbox>
                              </span>
                                <el-date-picker
                                        style="width: 96%;margin-bottom: 5%;"
                                        size = "mini"  readonly
                                        v-model="swdjObj.expdtBeginDate"
                                        type="date"
                                        placeholder="截止日期">
                                </el-date-picker>
                              </div>
                            </div>
                          </el-col>
                        <!--</el-form-item>-->
                      </el-col>
                    </el-form>
                  </div>
                </div>
              </el-col>
            </template>
          </el-row>
          <div slot="footer" class="dialog-footer" style="text-align: center;">
            <el-button type="primary" @click="queryProvVisible = false" >取消</el-button>
          </div>
        </el-dialog>
        <el-dialog :title="companyImgDailogTitle"  close="onClose"  v-model="companyImgDailog" :close-on-click-modal="false" >
          <img width="100%" :src="dialogcompanyImageUrl" alt="">
        </el-dialog>
      </el-col>
  </div>
</template>
<script>
    import { fileServer } from '../../common/js/constance';
    export default {
        data() {
            return {
                yyzzChecked:false,
                jyxkChecked:false,
                swdjChecked:false,
                hosId:'',
                companyImgDailogTitle:'',
                companyImgDailog:false,
                dialogcompanyImageUrl:'',
                yyzzImgArray:[],
                yyzzObj:{},
                jyxkImgArray:[],
                jyxkObj:{},
                swdjImgArray:[],
                swdjObj:{},
                company:{},
                logoUrl:'',
                styleObject: {
                    fontcolor: 'red',
                    fontSize: '13px'
                },
                filters: {
                    provName: '',
                    provLxr: ''
                },
                selhos:false,
                provId: '',
                provName:'',
                total: 0,
                page: 1,
                pageSize:20,
                tel:'',
                linkman:'',
                userId:'',
                loading: false,
                provHosInfos:[],
                hosInfos:[],
                provHosInfoVo: {
                    isThreeInOne: '1',
                    flag: '1',
                    kind: '1'
                },
                modelTitle: "",
                queryProvTitle: "",
                editFormVisible: false,
                queryProvVisible: false,
                editLoading: false
            }
        },
        methods: {
            // 医院获取供应商列表
            getHosProvInfoVoList: function (pIndex) {
                this.page = pIndex;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provName: this.filters.provName,
                        provLxr: this.filters.provLxr
                    },
                    page: this.page,
                    rows: this.pageSize
                };
                this.loading = true;
                this.axios.post('/spdHERPService/provManager/hosProvInfo/hosProvInfoVoList', param).then(res => {
                    this.loading = false;
                    this.provHosInfos = res.data.data.data;
                    this.total = res.data.data.total;
                    // this.page = res.data.data.pageNum;
                }, err => {
                    this.loading = false;
                });
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getHosProvInfoVoList(this.page);
            },
            handlePSizeChange(val) {
                this.pageSize = val;
                this.getHosProvInfoVoList(this.page);
            },
            /**
             * 重置供应商查询
             */
            resetProvInfo:function (formName) {
                this.$refs[formName].resetFields();
                this.getHosProvInfoVoList(1);
            },
            provHosInfoEdit(item) {
                this.getProvInfoVoList();
                if (item) {
                    this.provHosInfoVo = Object.assign({}, item);
                    this.modelTitle = "修改供应商";
                    var tempObj = {
                        cname:item.provName,
                        id:item.provId,
                        linkman:'',
                        tel:''
                    };
                    // 编辑供应商信息时，由于查询出的数组里没有当前供应商信息，需要手动入栈，便回显
                    this.hosInfos.push(tempObj);
                    this.provId = this.provHosInfoVo.provId;
                    this.linkman = this.provHosInfoVo.provLxr;
                    this.tel = this.provHosInfoVo.provLxrTelphonenum;
                    this.selhos=true;
                } else {
                    this.provHosInfoVo = {};
                    this.modelTitle = "添加供应商";
                    this.provId = '';
                    this.linkman = '';
                    this.tel = '';
                    this.selhos=false;
                }
                this.editFormVisible = true;
            },
            editSubmit: function () {
                this.editLoading = true;
                this.provHosInfoVo.hosId = this.hosId;
                this.provHosInfoVo.provId = this.provId;
                this.provHosInfoVo.provLxr = this.linkman;
                this.provHosInfoVo.provLxrTelphonenum = this.tel;
                this.provHosInfoVo.status = '2';
                this.provHosInfoVo.uid = this.userId;
                let serviceUrl = this.provHosInfoVo.id ? "/spdHERPService/provManager/hosProvInfo/update" : "/spdHERPService/provManager/hosProvInfo/insert";
                this.axios.post(serviceUrl, this.provHosInfoVo).then(res => {
                    this.editLoading = false;
                    if (res.data.code == "0") {
                        this.$message({ message: '操作成功！', type: 'success' });
                        this.editFormVisible = false;
                        this.getHosProvInfoVoList(this.page);
                    } else {
                        this.$message.error('操作失败！');
                    }
                }, err => {
                    this.editLoading = false;
                });
            },
            // 查看供应商
            queryProvInfo(prov) {
                this.queryProvTitle = '供应商查看';
                this.queryProvVisible = true;
                this.getProvCompany(prov);
            },
            // 获取供应商信息
            getProvCompany(prov){
                var param = {
                    orderBy: "",
                    queryObject: {
                        provId : prov.provId
                    },
                    page: this.pPageNum,
                    rows: this.pPageSize
                };
                this.axios.post('/spdHERPService/provManager/newProvInfo/getProvCompanyInfo', param).then(res => {
                    this.company = res.data.data;
                    this.logoUrl = this.imgBasePath + res.data.data.logo;
                    this.getProvCompanyCertsInfo(res.data.data);
                }, err =>{
                })
            },
            /**
             * 获取供应商三证信息
             */
            getProvCompanyCertsInfo (company){
                this.company = company;
                if(company.companyCertificateVos.length>0) {
                    // 营业执照对象
                    this.yyzzObj = company.companyCertificateVos.find(item => item.certKind == 1);
                    if(this.yyzzObj){
                        this.yyzzChecked = this.yyzzObj.isValiding == '1' ? true : false;
                        this.yyzzObj.expdtBeginDate = this.yyzzObj.expdtBeginDate == null? '' : this.yyzzObj.expdtBeginDate.substr(0,10);
                        this.yyzzObj.expdtEndDate = this.yyzzObj.expdtEndDate == null? '' : this.yyzzObj.expdtEndDate.substr(0,10);
                        // 营业执照图片数组
                        this.yyzzImgArray = this.yyzzObj.basCompanyCertificateImages;
                    }
                    // 经营许可证对象
                    this.jyxkObj = company.companyCertificateVos.find(item => item.certKind == 2);
                    if(this.jyxkObj){
                        this.jyxkChecked = this.jyxkObj.isValiding == '1' ? true : false;
                        this.jyxkObj.expdtBeginDate = this.jyxkObj.expdtBeginDate == null? '' : this.jyxkObj.expdtBeginDate.substr(0,10);
                        this.jyxkObj.expdtEndDate = this.jyxkObj.expdtEndDate == null? '' : this.jyxkObj.expdtEndDate.substr(0,10);
                        // 经营许可证图片数组
                        this.jyxkImgArray = this.jyxkObj.basCompanyCertificateImages;
                    }
                    // 税务登记证对象
                    this.swdjObj = company.companyCertificateVos.find(item => item.certKind == 3);
                    if(this.swdjObj){
                        this.swdjChecked = this.swdjObj.isValiding == '1' ? true : false;
                        this.swdjObj.expdtBeginDate = this.swdjObj.expdtBeginDate == null? '' : this.swdjObj.expdtBeginDate.substr(0,10);
                        this.swdjObj.expdtEndDate = this.swdjObj.expdtEndDate == null? '' : this.swdjObj.expdtEndDate.substr(0,10);
                        // 税务登记证图片数组
                        this.swdjImgArray = this.swdjObj.basCompanyCertificateImages;
                    }
                }
                this.logoUrl = this.imgBasePath + company.logo;
            },
            /**
             * 查看企业三证
             */
            checkCompanyImg (d){
                this.companyImgDailogTitle = '企业三证 查看';
                this.companyImgDailog = true;
                this.dialogcompanyImageUrl = this.imgBasePath + d.filePath;
            },
            deleteSubmit: function (provHosInfoVo) {
                this.$confirm('确认解除关系吗?', '提示', {}).then(() => {
                    this.loading = true;
                    provHosInfoVo.status = '0';// 已解除
                    this.axios.post("/spdHERPService/provManager/hosProvInfo/setStatus", provHosInfoVo).then(res => {
                        this.loading = false;
                        if (res.data.code == "0") {
                            this.loading = false;
                            this.$message({message: '操作成功！', type: 'success'});
                            this.getHosProvInfoVoList(this.page);
                        } else {
                            this.$message.error(res.data.msg);
                        }
                    }, err => {
                        this.loading = false;
                    });
                }).catch(() => {
                });
            },
            // 获取供应商下拉列表
            getProvInfoVoList: function () {
                var hosProvVo = { hosId:this.hosId };
                this.loading = true;
                this.axios.post(`/spdHERPService/provManager/hosProvInfo/getHosProvVoList`, hosProvVo).then(res => {
                    this.loading = false;
                    this.hosInfos = res.data.data;
                }, err => {
                    this.loading = false;
                });
            },
        },
        mounted() {
            this.hosName = this.user.corpName;
            this.hosId = this.user.corpId;
            this.userId = this.user.userId;
            this.imgBasePath = fileServer.imgBasePath;
            this.getHosProvInfoVoList(1);
        }
    };

</script>
<style>
  /************************************** 三证查看CSS样式 *****************************/
  #myProv.ImageCard{
    border:none;
    background-color: #fbfdff;
    border-radius: 6px;
    box-sizing: border-box;
    width: 93%;
    cursor: pointer;
    line-height: 146px;
    vertical-align: top;
    margin-left: 3%;
  }
  #myProv.certList {   width: 100%;;display: block;height: 130px;   }
  #myProvQuery .certsCard{
    margin-right: 6px;;
    background-color: rgb(238, 241, 246);
    border: 1px solid darkgrey;
    border: 1px solid #d1dbe5;
    border-radius: 4px;
    overflow: hidden;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);
  }
  #myProv.certWordsStyle{
    font-size: 12px;
    /*line-height: 27px;*/
    font-weight: bold;
    margin-left: 4%;
  }
  #myProv.certListStyle{
    margin-left: 3%;
    width: 93%;
    background-color: white;
    height: 30px;
    margin-bottom: 2%;
  }
  #myProv.certButtonStyle{
    /*line-height: 27px;*/
    margin-right: 3%;
  }
  #myProv.certCodes{
    margin-left: 3%;
    margin-right: 4%;
  }
  /*************************************** 三证查看CSS样式 ******************************/

  #myProv.myProv-tableheight {
    height: calc(100% - 85px);
  }
  #myProv.myHos-button-align{
    text-align: center;
  }
  #myProvQuery .el-col-16 {
    min-width: 630px;
  }
  #myProvQuery .el-col-10 {
    min-width: 424px;
  }
  #myProvQuery .el-col-9 {
    min-width: 416px;
  }
  #myProvQuery .el-col-7 {
    min-width: 324px;
  }
  #myProvQuery .el-col-6 {
    min-width: 206px;
  }
  #myProvQuery .el-form-item__content {
    line-height: 35px;
  }

  #myProv{
    height: 100%;
  }
  #myProv .el-dialog--small {
    min-width: 900px;
    width: 50%;
  }

</style>
