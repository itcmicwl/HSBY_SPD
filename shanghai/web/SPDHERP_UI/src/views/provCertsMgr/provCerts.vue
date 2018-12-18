<template>
    <div id="certslist" style="height:100%;">

        <!-- 供应商列表 -->
        <el-col :span="4" class="toolbar" style="padding-bottom: 0px; padding-left:5px; height: 97%;" >
            <div style="padding-bottom: 3px;">
                <el-form :inline="true" :model="filters" ref="provInfoForm">
                    <el-form-item prop="provName">
                        <el-input v-model="filters.provName" placeholder="请输入供应商名称" ></el-input>
                    </el-form-item>
                    <el-button-group>
                        <el-button type="primary" v-on:click="getHosProvInfoVoList(1)" >查询</el-button>
                        <el-button type="primary" v-on:click="resetProvName('provInfoForm')" >重置</el-button>
                    </el-button-group>
                </el-form>
            </div>
            <el-table :data="provHosInfos"  ref="provListTable" @current-change="_queryProvGoodsList" :showHeader="false" style="width: 100%;"
                      highlight-current-row  border fit class="provList-tableheight">
                <el-table-column prop="provName" label="供应商名称"  header-align="center"></el-table-column>
            </el-table>
            <!--工具条-->
            <el-col :span="24" class="toolbar">
                <el-pagination @size-change="pHandleSizeChange" @current-change="pHandleCurrentChange"
                               :current-page="pPage" :page-sizes="[20, 50, 100, 200]"
                               :page-size="pPageSize" layout="total, sizes, prev, pager, next"
                               :total="pTotal" style="float:right;" small>
                </el-pagination>
            </el-col>
        </el-col>
        <el-col :span="20" style="padding-left: 5px;" class="provList-tableheight">
          <el-tabs v-model="activeName" type="card" @tab-click="handleClick" style="height: 99%;">
              <el-tab-pane label="过期证件" name="first" style="height:100%">
                  <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                      <el-form :inline="true" :model="outDateCertQuery" ref="outDateCertQuery">
                          <el-form-item label="证件类型：" prop="certKind">
                              <el-select v-model="outDateCertQuery.certKind"   style="width: 73%" clearable placeholder="请选择">
                                  <el-option
                                          v-for="item in outDateCertKind"
                                          :key="item.value"
                                          :label="item.label"
                                          :value="item.value">
                                  </el-option>
                              </el-select>
                          </el-form-item>
                          <el-form-item label="过期时间：" prop="outDate" style="margin-left: -3%;">
                              <el-select v-model="outDateCertQuery.outDate"  style="width: 73%" clearable placeholder="请选择">
                                  <el-option
                                          v-for="item in outDateDict"
                                          :key="item.value"
                                          :label="item.label"
                                          :value="item.value">
                                  </el-option>
                              </el-select>
                          </el-form-item>
                          <el-form-item label="相关名称：" prop="aboutName" style="margin-left: -3%;">
                              <el-input placeholder="相关名称"  v-model="outDateCertQuery.aboutName"></el-input>
                          </el-form-item>
                          <el-form-item>
                              <el-button type="primary" v-on:click="queryProvOutdateCerts(1)" >查询</el-button>
                              <el-button type="primary" @click="resetForm('outDateCertQuery')" >重置</el-button>
                          </el-form-item>
                      </el-form>
                  </el-col>
                  <el-col style="width:100%;height:100%;">
                      <el-table :data="provOutdateCerts" highlight-current-row  border fit class="tb-height" >
                        <el-table-column type="index" label="序号" align="center" width="40"></el-table-column>
                        <el-table-column prop="certKind" header-align="center" width="150" label="证件类型"></el-table-column>
                        <el-table-column prop="aboutName" header-align="center" min-width="230" label="企业/证件名称"></el-table-column>
                        <el-table-column prop="aboutCode" header-align="center" width="230"  label="证件编码"></el-table-column>
                        <el-table-column prop="expdtBeginDate" width="100" align="center" label="开始时间" :formatter="formatterBeginDate" sortable></el-table-column>
                        <el-table-column prop="expdtEndDate" width="100" align="center" label="截止时间" :formatter="formatterEndDate" sortable></el-table-column>
                        <el-table-column prop="isValiding" width="60" align="center" label="长期有效" :formatter="formateIsValiding"></el-table-column>
                        <el-table-column prop="outDate" width="100" align="center" label="过期天数" :formatter="formatterOutdate" sortable></el-table-column>
                        <el-table-column label="操作" width="100" align="center">
                            <template slot-scope="scope">
                                <el-button size="mini" @click="queryOutdateCertsImg(scope.row)" type="primary" >查看证件</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination  small @size-change="oHandleSizeChange"
                                    @current-change="oHandleCurrentChange" class="el-pagination-right"
                                    :current-page="oPage" layout="total, sizes, prev, pager, next"
                                    :page-sizes="[10, 15, 50, 100]" :page-size="oPageSize" :total="oTotal">
                    </el-pagination>
                  </el-col>
                  
                  <el-dialog :title="outdateCertDailogTitle"  close="onClose" :visible.sync="outdateCertDailog" :close-on-click-modal="false" >
                      <div>
                          <template v-if="outdateCertsImg.length<1">
                              <img src="../../assets/images/noImg.jpg"  class="image">
                          </template>
                          <template v-else>
                              <div class="outdateCert">
                                  <el-col :span="6" v-for="(d,index) in outdateCertsImg" v-bind:key="d.id" style="margin-bottom: 5%;">
                                      <el-card :body-style="{ padding: '10px'}" >
                                          <img :src="imgBasePath+d.filePath" style="width: 100%;display: block;">
                                          <div style="padding: 14px;background-color: rgb(238, 241, 246);">
                                              <el-row>
                                                  <span style="font-weight: bold;">{{currentOutdateCert.certKind}}</span>
                                                  <el-button type="text" class="button" @click="getOutdateCertsImg(d)">查看</el-button>
                                              </el-row>
                                          </div>
                                      </el-card>
                                  </el-col>
                              </div>
                          </template>
                      </div>
                  </el-dialog>
                  <el-dialog :title="outdateCertImgDailogTitle"  close="onClose"  :visible.sync="outdateImgCertDailog" :close-on-click-modal="false" >
                      <img width="100%" :src="dialogOutdateCertImageUrl" alt="">
                  </el-dialog>
                  <!--</div>-->
              </el-tab-pane>
              <el-tab-pane label="企业三证" name="second">
                  <div style="padding-left: 10px;height: 100%;overflow-y: scroll;">
                      <div id="certsListCompanyCerts">
                          <el-form :model="company" label-width="120px"  class="companyOutput">
                              <el-row>
                                  <el-col :span="7" style="min-width: 405px;">
                                      <el-form-item label="公司名称：" style="width: 87.5%;" prop="cname">
                                          <el-col :span="24">
                                              <el-input v-model="company.cname" disabled ></el-input>
                                          </el-col>
                                      </el-form-item>
                                      <el-form-item label="企业编号：" style="width: 87.5%;" prop="code">
                                          <el-col :span="24">
                                              <el-input v-model="company.code" disabled ></el-input>
                                          </el-col>
                                      </el-form-item>
                                      <el-form-item label="公司别名：" style="width: 87.5%;" prop="anotherName">
                                          <el-col :span="24">
                                              <el-input v-model="company.anotherName" disabled ></el-input>
                                          </el-col>
                                      </el-form-item>
                                      <el-form-item label="法人代表：" style="width: 87.5%;" prop="legal">
                                          <el-col :span="24">
                                              <el-input v-model="company.legal" disabled ></el-input>
                                          </el-col>
                                      </el-form-item>
                                      <el-form-item label="地址：" style="width: 87.5%;" prop="address">
                                          <el-col :span="24">
                                              <el-input v-model="company.address" disabled ></el-input>
                                          </el-col>
                                      </el-form-item>
                                      <el-form-item label="电子邮箱：" style="width: 87.5%;" prop="email">
                                          <el-col :span="24">
                                              <el-input v-model="company.email" disabled ></el-input>
                                          </el-col>
                                      </el-form-item>
                                  </el-col>
                                  <el-col :span="7" style="margin-left: 2%;">
                                      <img v-if="logoUrl" :src="logoUrl" style="width: 70%;border: 1px dashed lightgray;border-radius: 6px;">
                                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                  </el-col>
                              </el-row>
                              <el-col :span="24">
                                  <el-col :span="7">
                                      <el-form-item label="联系人：" style="width: 87.5%;" prop="linkman">
                                          <el-input v-model="company.linkman" disabled ></el-input>
                                      </el-form-item>
                                  </el-col>
                                  <el-col :span="7">
                                      <el-form-item label="联系方式：" labelWidth="100px" style="width: 76%;" prop="contactWay">
                                          <el-input v-model="company.contactWay" disabled ></el-input>
                                      </el-form-item>
                                  </el-col>
                              </el-col>

                              <el-col :span="24">
                                  <el-col :span="7">
                                      <el-form-item label="传真：" style="width: 87.5%;" prop="contactFax">
                                          <el-input v-model="company.contactFax" disabled ></el-input>
                                      </el-form-item>
                                  </el-col>
                                  <el-col :span="7">
                                      <el-form-item label="邮政编号：" labelWidth="100px" style="width: 76%;" prop="postCode">
                                          <el-input v-model="company.postCode" disabled ></el-input>
                                      </el-form-item>
                                  </el-col>
                              </el-col>
                              <el-col :span="24">
                                  <el-form-item label="备注信息：" prop="remark">
                                      <el-col :span="16">
                                          <el-input type="textarea" style="width: 96.5%;" :row="2" disabled v-model="company.remark" ></el-input>
                                      </el-col>
                                  </el-form-item>
                              </el-col>
                              <el-col :span="24">
                                <el-form-item label="是否三证合一：" >                       
                                        <el-switch
                                        v-model="company.isThreeInOne"
                                        disabled
                                        active-color="#13ce66"
                                        inactive-color="#ff4949"
                                        :disabled="editDisable"
                                        active-text="是"
                                        inactive-text="否"
                                        active-value="1"
                                        inactive-value="0">
                                        </el-switch>
                                    </el-form-item>
                                </el-col>
                              <el-col :span="24">
                                  <el-form-item label="企业三证：">
                                  </el-form-item>
                              </el-col>

                              <el-col :span="24">
                                  <el-form-item label="" labelWidth="120px" style="width: 76%;min-width: 614px;">
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
                                                          v-model="yyzzObj.expdtEndDate"
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
                                                  <span class="certWordsStyle">职业许可证</span>
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
                                                          v-model="jyxkObj.expdtEndDate"
                                                          type="date"
                                                          placeholder="截止日期">
                                                  </el-date-picker>
                                              </div>
                                          </div>
                                      </el-col>
                                      <el-col :span="6" v-for="(d,index) in swdjImgArray" v-bind:key="d.id" class="certsCard" v-if="company.isThreeInOne==0">
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
                                                            v-model="swdjObj.expdtEndDate"
                                                            type="date"
                                                            placeholder="截止日期">
                                                    </el-date-picker>
                                                </div>
                                            </div>
                                        </el-col>
                                        <!-- 组织机构代码证 -->
                                        <el-col :span="6" v-for="(d,index) in zzjgImgArray" v-bind:key="d.id" class="certsCard" v-if="company.isThreeInOne==0">
                                            <div style="margin-top: 3%;">
                                                <div class="ImageCard">
                                                    <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                                    <i v-else class="el-icon-plus"></i>
                                                </div>
                                            </div>
                                            <div style="">
                                                <div class="certListStyle">
                                                    <span class="certWordsStyle">组织机构代码证</span>
                                                    <span><el-button type="text" class="button certButtonStyle" @click="checkCompanyImg(d)">查看</el-button></span>
                                                </div>
                                                <div class="certCodes">
                                                    <el-input placeholder="登记证号" v-model="zzjgObj.certCode" readonly ></el-input>
                                                </div>
                                                <div class="block" style="margin-left: 3%;">
                                                    <span class="demonstration"></span>
                                                    <el-date-picker
                                                            style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                                            size = "mini"  readonly
                                                            v-model="zzjgObj.expdtBeginDate"
                                                            type="date"
                                                            placeholder="生效日期">
                                                    </el-date-picker>
                                                </div>
                                                <div class="block" style="margin-left: 3%;">
                                                    <span class="">
                                                        <el-checkbox v-model="zzjgChecked" disabled >
                                                            <span style="font-size: 10px;">是否长期有效</span>
                                                        </el-checkbox>
                                                    </span>
                                                    <el-date-picker
                                                            style="width: 96%;margin-bottom: 5%;"
                                                            size = "mini"  readonly
                                                            v-model="zzjgObj.expdtEndDate"
                                                            type="date"
                                                            placeholder="截止日期">
                                                    </el-date-picker>
                                                </div>
                                            </div>
                                        </el-col>
                                        <!-- 医疗器械经营许可证 -->
                                        <el-col :span="6" v-for="(d,index) in ylqxjyxkImgArray" v-bind:key="d.id" class="certsCard">
                                            <div style="margin-top: 3%;">
                                                <div class="ImageCard">
                                                    <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                                    <i v-else class="el-icon-plus"></i>
                                                </div>
                                            </div>
                                            <div style="">
                                                <div class="certListStyle">
                                                    <span class="certWordsStyle">医疗器械经营许可证</span>
                                                    <span><el-button type="text" class="button certButtonStyle" @click="checkCompanyImg(d)">查看</el-button></span>
                                                </div>
                                                <div class="certCodes">
                                                    <el-input placeholder="登记证号" v-model="ylqxjyxkObj.certCode" readonly ></el-input>
                                                </div>
                                                <div class="block" style="margin-left: 3%;">
                                                    <span class="demonstration"></span>
                                                    <el-date-picker
                                                            style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                                            size = "mini"  readonly
                                                            v-model="ylqxjyxkObj.expdtBeginDate"
                                                            type="date"
                                                            placeholder="生效日期">
                                                    </el-date-picker>
                                                </div>
                                                <div class="block" style="margin-left: 3%;">
                                                    <span class="">
                                                        <el-checkbox v-model="ylqxjyxkChecked" disabled >
                                                            <span style="font-size: 10px;">是否长期有效</span>
                                                        </el-checkbox>
                                                    </span>
                                                    <el-date-picker
                                                            style="width: 96%;margin-bottom: 5%;"
                                                            size = "mini"  readonly
                                                            v-model="ylqxjyxkObj.expdtEndDate"
                                                            type="date"
                                                            placeholder="截止日期">
                                                    </el-date-picker>
                                                </div>
                                            </div>
                                        </el-col>
                                        <!-- 医疗器械经营备案证 -->
                                        <el-col :span="6" v-for="(d,index) in ylqxjybaImgArray" v-bind:key="d.id" class="certsCard">
                                            <div style="margin-top: 3%;">
                                                <div class="ImageCard">
                                                    <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                                    <i v-else class="el-icon-plus"></i>
                                                </div>
                                            </div>
                                            <div style="">
                                                <div class="certListStyle">
                                                    <span class="certWordsStyle">医疗器械经营备案证</span>
                                                    <span><el-button type="text" class="button certButtonStyle" @click="checkCompanyImg(d)">查看</el-button></span>
                                                </div>
                                                <div class="certCodes">
                                                    <el-input placeholder="登记证号" v-model="ylqxjybaObj.certCode" readonly ></el-input>
                                                </div>
                                                <div class="block" style="margin-left: 3%;">
                                                    <span class="demonstration"></span>
                                                    <el-date-picker
                                                            style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                                            size = "mini"  readonly
                                                            v-model="ylqxjybaObj.expdtBeginDate"
                                                            type="date"
                                                            placeholder="生效日期">
                                                    </el-date-picker>
                                                </div>
                                                <div class="block" style="margin-left: 3%;">
                                                    <span class="">
                                                        <el-checkbox v-model="ylqxjybaChecked" disabled >
                                                            <span style="font-size: 10px;">是否长期有效</span>
                                                        </el-checkbox>
                                                    </span>
                                                    <el-date-picker
                                                            style="width: 96%;margin-bottom: 5%;"
                                                            size = "mini"  readonly
                                                            v-model="ylqxjybaObj.expdtEndDate"
                                                            type="date"
                                                            placeholder="截止日期">
                                                    </el-date-picker>
                                                </div>
                                            </div>
                                        </el-col>
                                  </el-form-item>
                              </el-col>
                          </el-form>
                          <el-dialog :title="companyImgDailogTitle"  close="onClose"  :visible.sync="companyImgDailog" :close-on-click-modal="false" >
                              <img width="100%" :src="dialogcompanyImageUrl" alt="">
                          </el-dialog>
                      </div>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="医疗器械注册证" name="third">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                          <el-form :inline="true" :model="registCertQuery" ref="registCertQuery">
                              <el-form-item label="注册证品名称：" prop="productName">
                                  <el-input placeholder="注册证品名称"  v-model="registCertQuery.productName"></el-input>
                              </el-form-item>
                              <el-form-item label="注册证号：" prop="certificateCode">
                                  <el-input placeholder="注册证号"  v-model="registCertQuery.certificateCode"></el-input>
                              </el-form-item>
                              <el-form-item>
                                  <el-button type="primary" v-on:click="getPushedRegCertsList(1)" >查询</el-button>
                                  <el-button type="primary" @click="resetFormR('registCertQuery')" >重置</el-button>
                              </el-form-item>
                          </el-form>
                      </el-col>
                      <el-col style="width:100%;height:100%;">
                        <el-table :data="registCerts" highlight-current-row style="width: 100%;" border fit class="tb-height">
                          <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
                          <el-table-column prop="productName" width="200" header-align="center" label="注册证品名称"></el-table-column>
                          <el-table-column prop="certificateCode" width="300" header-align="center" label="注册证号"></el-table-column>
                          <el-table-column prop="regKind" width="100" align="center" label="注册证类别" :formatter="formatregistCertKind"></el-table-column>
                          <el-table-column prop="cname" width="250" header-align="center" label="生产厂家" ></el-table-column>
                          <el-table-column prop="expdtBeginDate" width="150" align="center" label="开始时间" :formatter="formatterBeginDate" sortable></el-table-column>
                          <el-table-column prop="expdtEndDate" width="150" align="center" label="截止时间" :formatter="formatterEndDate" sortable></el-table-column>
                          <el-table-column label="操作" align="center">
                              <template slot-scope="scope">
                                  <el-button size="mini" @click="checkRegistCerts(scope.row)" type="primary" >查看证件</el-button>
                              </template>
                          </el-table-column>
                      </el-table>
                      <el-pagination  small @size-change="rHandleSizeChange"
                                      @current-change="rHandleCurrentChange" class="el-pagination-right"
                                      :current-page="rPage" layout="total, sizes, prev, pager, next"
                                      :page-sizes="[10, 15, 50, 100]" :page-size="rPageSize" :total="rTotal">
                      </el-pagination>
                      </el-col>
                      
                      <el-dialog :title="registCertDailogTitle"  close="onClose"  :visible.sync="registCertDailog" :close-on-click-modal="false" >
                          <template v-if="registCertImgArray.length<1">
                              <img src="../../assets/images/noImg.jpg" style="width: 100%">
                          </template>
                          <template v-else>
                              <div style="margin-bottom: 10%;margin-left: 8%;margin-top: 2%;height: 100%;">
                                  <el-row>
                                      <el-col :span="6" v-for="(d,index) in registCertImgArray" v-bind:key="d.id" style="margin-bottom: 1%;margin-right: 1%;">
                                          <el-card :body-style="{ padding: '10px' }">
                                              <img :src='imgBasePath + d.filePath' style="width: 100%;height: 130px;">
                                              <div style="padding: 14px;background-color: rgb(238, 241, 246);">
                                                  <el-row>
                                                      <span style="font-weight: bold;">注册证</span>
                                                      <el-button type="text" class="button" @click="checkRegistCert(d)">查看</el-button>
                                                  </el-row>
                                              </div>
                                          </el-card>
                                      </el-col>
                                  </el-row>
                              </div>
                          </template>
                      </el-dialog>
                      <el-dialog :title="registCertImgDailogTitle"  close="onClose"  :visible.sync="registCertImgDailog" :close-on-click-modal="false" >
                          <img width="100%" :src="registCertImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="厂家三证" name="fourth">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                          <el-form :inline="true" :model="mfrsCertQuery" ref="mfrsCertQuery">
                              <el-form-item label="厂家名称：" prop="mfrsName">
                                  <el-input placeholder="厂家名称"  v-model="mfrsCertQuery.mfrsName"></el-input>
                              </el-form-item>
                              <el-form-item label="厂家类型：" prop="mfrsKind">
                                  <el-select v-model="mfrsCertQuery.mfrsKind"   style="width: 73%" clearable placeholder="请选择">
                                      <el-option
                                              v-for="item in mfrsKindDict"
                                              :key="item.value"
                                              :label="item.label"
                                              :value="item.value">
                                      </el-option>
                                  </el-select>
                              </el-form-item>
                              <el-form-item>
                                  <el-button type="primary" v-on:click="getBasMfrsList(1)" >查询</el-button>
                                  <el-button type="primary" @click="resetFormM('mfrsCertQuery')" >重置</el-button>
                              </el-form-item>
                          </el-form>
                      </el-col>
                      <el-col style="width:100%;height:100%;">
                        <el-table :data="mfrsData" highlight-current-row border fit class="tb-height">
                          <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
                          <el-table-column prop="mfrsName" header-align="center"  label="厂家名称"></el-table-column>
                          <el-table-column prop="mfrsKind"  align="center" label="厂家类型" :formatter="formatmfrsKind"></el-table-column>
                          <el-table-column prop="productCode" header-align="center" label="生产许可证"></el-table-column>
                          <el-table-column label="操作"  align="center">
                              <template slot-scope="scope">
                                  <el-button size="mini" @click="checkMfrs(scope.row)" type="primary" >查看证件</el-button>
                              </template>
                          </el-table-column>
                      </el-table>
                      <el-pagination  small @size-change="mHandleSizeChange"
                                      @current-change="mHandleCurrentChange" class="el-pagination-right"
                                      :current-page="rPage" layout="total, sizes, prev, pager, next"
                                      :page-sizes="[10, 15, 50, 100]" :page-size="mPageSize" :total="mTotal">
                      </el-pagination>
                      </el-col>
                      
                      <el-dialog :title="mfrsDailogTitle"  close="onClose"  :visible.sync="mfrsDailog" :close-on-click-modal="false" >
                          <div style="margin-left: 5%;height: 100%;margin-bottom: 44%;margin-top: 3%;">
                              <el-col :span="6" v-for="(d,index) in myyzzImgArray" v-bind:key="d.id" class="certsCard">
                                  <div style="margin-top: 3%;">
                                      <div class="ImageCard">
                                          <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                          <i v-else class="el-icon-plus"></i>
                                      </div>
                                  </div>
                                  <div>
                                      <div class="certListStyle">
                                          <span class="certWordsStyle">营业执照</span>
                                          <span><el-button type="text" class="button certButtonStyle" @click="checkMfrsImg(d)">查看</el-button></span>
                                      </div>
                                      <div class="certCodes">
                                          <el-input placeholder="注册号" v-model="myyzzObj.certCode" readonly ></el-input>
                                      </div>
                                      <div class="block" style="margin-left: 3%;">
                                          <span class="demonstration"></span>
                                          <el-date-picker
                                                  style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                                  size = "mini"  readonly
                                                  v-model="myyzzObj.expdtBeginDate"
                                                  type="date"
                                                  placeholder="生效日期">
                                          </el-date-picker>
                                      </div>
                                      <div class="block" style="margin-left: 3%;">
                              <span class="">
                                  <el-checkbox v-model="myyzzChecked" disabled >
                                      <span style="font-size: 10px;">是否长期有效</span>
                                  </el-checkbox>
                              </span>
                                          <el-date-picker
                                                  style="width: 96%;margin-bottom: 5%;"
                                                  size = "mini"  readonly
                                                  v-model="myyzzObj.expdtEndDate"
                                                  type="date"
                                                  placeholder="截止日期">
                                          </el-date-picker>
                                      </div>
                                  </div>
                              </el-col>
                              <el-col :span="6" v-for="(d,index) in mjyxkImgArray" v-bind:key="d.id" class="certsCard">
                                  <div style="margin-top: 3%;">
                                      <div class="ImageCard">
                                          <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                          <i v-else class="el-icon-plus"></i>
                                      </div>
                                  </div>
                                  <div>
                                      <div class="certListStyle">
                                          <span class="certWordsStyle">经营许可证</span>
                                          <span><el-button type="text" class="button certButtonStyle" @click="checkMfrsImg(d)">查看</el-button></span>
                                      </div>
                                      <div class="certCodes">
                                          <el-input placeholder="登记证号" v-model="mjyxkObj.certCode" readonly ></el-input>
                                      </div>
                                      <div class="block" style="margin-left: 3%;">
                                          <span class="demonstration"></span>
                                          <el-date-picker
                                                  style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                                  size = "mini"  readonly
                                                  v-model="mjyxkObj.expdtBeginDate"
                                                  type="date"
                                                  placeholder="生效日期">
                                          </el-date-picker>
                                      </div>
                                      <div class="block" style="margin-left: 3%;">
                              <span class="">
                                  <el-checkbox v-model="mjyxkChecked" disabled >
                                      <span style="font-size: 10px;">是否长期有效</span>
                                  </el-checkbox>
                              </span>
                                          <el-date-picker
                                                  style="width: 96%;margin-bottom: 5%;"
                                                  size = "mini"  readonly
                                                  v-model="mjyxkObj.expdtEndDate"
                                                  type="date"
                                                  placeholder="截止日期">
                                          </el-date-picker>
                                      </div>
                                  </div>
                              </el-col>
                              <el-col :span="6" v-for="(d,index) in mswdjImgArray" v-bind:key="d.id" class="certsCard">
                                  <div style="margin-top: 3%;">
                                      <div class="ImageCard">
                                          <img v-if="d.filePath" :src="imgBasePath+d.filePath" class="certList">
                                          <i v-else class="el-icon-plus" style="background-image: image(../../assets/images/noImg.jpg)"></i>
                                      </div>
                                  </div>
                                  <div>
                                      <div class="certListStyle">
                                          <span class="certWordsStyle">税务登记证</span>
                                          <span><el-button type="text" class="button certButtonStyle" @click="checkMfrsImg(d)">查看</el-button></span>
                                      </div>
                                      <div class="certCodes">
                                          <el-input placeholder="登记证号" v-model="mswdjObj.certCode" readonly ></el-input>
                                      </div>
                                      <div class="block" style="margin-left: 3%;">
                                          <span class="demonstration"></span>
                                          <el-date-picker
                                                  style="width: 96%;margin-bottom: 2px; margin-top: 1%;"
                                                  size = "mini"  readonly
                                                  v-model="mswdjObj.expdtBeginDate"
                                                  type="date"
                                                  placeholder="生效日期">
                                          </el-date-picker>
                                      </div>
                                      <div class="block" style="margin-left: 3%;">
                              <span class="">
                                  <el-checkbox v-model="mswdjChecked" disabled >
                                      <span style="font-size: 10px;">是否长期有效</span>
                                  </el-checkbox>
                              </span>
                                          <el-date-picker
                                                  style="width: 96%;margin-bottom: 5%;"
                                                  size = "mini"  readonly
                                                  v-model="mswdjObj.expdtEndDate"
                                                  type="date"
                                                  placeholder="截止日期">
                                          </el-date-picker>
                                      </div>
                                  </div>
                              </el-col>
                          </div>
                      </el-dialog>
                      <el-dialog :title="mfrsImgDailogTitle"  close="onClose"  :visible.sync="mfrsImgDailog" :close-on-click-modal="false" >
                          <img width="100%" :src="dialogMfrsImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="经销授权书" name="fifth">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                          <el-form :inline="true" :model="jxsqsQuery" ref="jxsqsQuery">
                              <el-form-item label="上级单位名称：" prop="mfrsName">
                                  <el-input placeholder="上级单位名称"  v-model="jxsqsQuery.mfrsName"></el-input>
                              </el-form-item>
                              <el-form-item>
                                  <el-button type="primary" v-on:click="jxsqsList(1)" >查询</el-button>
                                  <el-button type="primary" @click="resetFormJ('jxsqsQuery')" >重置</el-button>
                              </el-form-item>
                          </el-form>
                      </el-col>
                      <el-col style="width:100%;height:100%;">
                          <el-table :data="jxsqsData" highlight-current-row style="width: 100%;" border fit class="tb-height">
                            <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
                            <el-table-column prop="mfrsName" width="300" header-align="center" label="上级单位名称"></el-table-column>
                            <el-table-column prop="saleArea" width="300" align="center" label="授权区域"></el-table-column>
                            <el-table-column prop="expdtBeginDate" width="100" align="center" label="起始日期" :formatter="formatterBeginDate" sortable></el-table-column>
                            <el-table-column prop="expdtEndDate" width="100" align="center" label="截止日期" :formatter="formatterEndDate" sortable></el-table-column>
                            <el-table-column label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-button size="mini" @click="checkSqs(scope.row)" type="primary" >查看授权书</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination  small @size-change="jHandleSizeChange"
                                        @current-change="jHandleCurrentChange" class="el-pagination-right"
                                        :current-page="jPage" layout="total, sizes, prev, pager, next"
                                        :page-sizes="[10, 15, 50, 100]" :page-size="jPageSize" :total="jTotal">
                        </el-pagination>
                      </el-col>
                      
                      <el-dialog :title="sqsDailogTitle"  close="onClose"  :visible.sync="sqsDailog" :close-on-click-modal="false" >
                          <img width="100%" :src="dialogSqsImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="法人委托书" name="sixth">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                          <el-form :inline="true" :model="wtsQuery" ref="wtsQuery">
                              <el-form-item label="委托人：" prop="ename">
                                  <el-input placeholder="委托人"  v-model="wtsQuery.ename"></el-input>
                              </el-form-item>
                              <el-form-item>
                                  <el-button type="primary" v-on:click="wtsList(1)" >查询</el-button>
                                  <el-button type="primary" @click="resetFormJ('wtsQuery')" >重置</el-button>
                              </el-form-item>
                          </el-form>
                      </el-col>
                      <el-col style="width:100%;height:100%;">
                          <el-table :data="wtsData" highlight-current-row style="width: 100%;" border fit class="tb-height">
                          <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
                          <el-table-column prop="ename" align="center" label="委托人"></el-table-column>
                          <el-table-column prop="wtrMoveNum" align="center" label="委托人电话"></el-table-column>
                          <el-table-column prop="expdtBeginDate" align="center" label="起始日期" :formatter="formatterBeginDate" sortable></el-table-column>
                          <el-table-column prop="expdtEndDate" align="center" label="截止日期" :formatter="formatterEndDate" sortable></el-table-column>
                          <el-table-column label="操作" align="center">
                              <template slot-scope="scope">
                                  <el-button-group>
                                      <el-button size="mini" @click="checkIdcard(scope.row)"  type="info" >查看身份证</el-button>
                                      <el-button size="mini" @click="checkWts(scope.row)" type="primary" >查看委托书</el-button>
                                  </el-button-group>
                              </template>
                          </el-table-column>
                      </el-table>
                      <el-pagination  small @size-change="wHandleSizeChange"
                                      @current-change="wHandleCurrentChange" class="el-pagination-right"
                                      :current-page="wPage" layout="total, sizes, prev, pager, next"
                                      :page-sizes="[10, 15, 50, 100]" :page-size="wPageSize" :total="wTotal">
                      </el-pagination>
                      </el-col>
                      
                      <el-dialog :title="wtsDailogTitle"  close="onClose"  :visible.sync="wtsDailog" :close-on-click-modal="false" >
                          <img width="100%" :src="dialogWtsImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="承诺书" name="seventh">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="4" v-for="(d,index) in cnsData" v-bind:key="d.id" style="min-width: 230px;">
                          <el-card :body-style="{ padding: '10px' }">
                              <img :src="cnsImage[index]" class="image">
                              <div style="padding: 14px;background-color: rgb(238, 241, 246);">
                                  <el-row>
                                      <span style="font-weight: bold;">承诺书</span>
                                      <el-button type="text" class="button" @click="checkCns(d)">查看</el-button>
                                  </el-row>
                                  <div class="bottom clearfix">
                                      <el-row>
                                          <span style="font-weight: lighter;">起始时间：<time class = "time">{{ d.expdtBeginDate }}</time></span>
                                      </el-row>
                                      <el-row>
                                          <span style="font-weight: lighter;">截止时间：<time class = "time">{{ d.expdtEndDate }}</time></span>
                                      </el-row>
                                  </div>
                              </div>
                          </el-card>
                      </el-col>
                      <el-dialog :title="cnsDailogTitle"  close="onClose"  :visible.sync="cnsDailog" :close-on-click-modal="false" >
                          <img class="image" :src="dialogCnsImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="销售代表" name="eighth">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
                          <el-form :inline="true" :model="xsdbQuery" ref="xsdbQuery">
                              <el-form-item label="销售代表：" prop="salesName">
                                  <el-input placeholder="销售代表"  v-model="xsdbQuery.salesName"></el-input>
                              </el-form-item>
                              <el-form-item>
                                  <el-button type="primary" v-on:click="salesRepList(1)" >查询</el-button>
                                  <el-button type="primary" @click="resetFormX('xsdbQuery')" >重置</el-button>
                              </el-form-item>
                          </el-form>
                      </el-col>
                      <el-col style="width:100%;height:100%;">
                          <el-table :data="xsdbData" highlight-current-row style="width: 100%;" border fit class="tb-height">
                            <el-table-column type="index" label="序号" align="center" width="62"></el-table-column>
                            <el-table-column prop="salesName" align="center" label="销售代表"></el-table-column>
                            <el-table-column prop="phoneNum" align="center" label="销售代表电话"></el-table-column>
                            <el-table-column prop="expdtBeginDate" align="center" label="起始日期" :formatter="formatterBeginDate" sortable></el-table-column>
                            <el-table-column prop="expdtEndDate" align="center" label="截止日期" :formatter="formatterEndDate" sortable></el-table-column>
                            <el-table-column label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-button-group>
                                        <el-button size="mini" @click="checkXsdbcard(scope.row)"  type="info" >查看证件</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination  small @size-change="xHandleSizeChange"
                                        @current-change="xHandleCurrentChange" class="el-pagination-right"
                                        :current-page="xPage" layout="total, sizes, prev, pager, next"
                                        :page-sizes="[10, 15, 50, 100]" :page-size="xPageSize" :total="xTotal">
                        </el-pagination>
                      </el-col>
                      
                      <el-dialog :title="xsdbDailogTitle"  close="onClose"  :visible.sync="xsdbDailog" :close-on-click-modal="false" >
                          <img width="100%" :src="dialogXsdbImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
              <el-tab-pane label="廉政协议书" name="ninth">
                  <div style="padding-left: 10px;height: 100%">
                      <el-col :span="4" v-for="(d,index) in lzxysData" v-bind:key="d.id" style="min-width: 230px;">
                          <el-card :body-style="{ padding: '10px' }">
                              <img :src="lzxysImage[index]" class="image">
                              <div style="padding: 14px;background-color: rgb(238, 241, 246);">
                                  <el-row>
                                      <span style="font-weight: bold;">廉政协议书</span>
                                      <el-button type="text" class="button" @click="checkLzxys(d)">查看</el-button>
                                  </el-row>
                                  <div class="bottom clearfix">
                                      <el-row>
                                          <span style="font-weight: lighter;">起始时间：<time class = "time">{{ d.expdtBeginDate }}</time></span>
                                      </el-row>
                                      <el-row>
                                          <span style="font-weight: lighter;">截止时间：<time class = "time">{{ d.expdtEndDate }}</time></span>
                                      </el-row>
                                  </div>
                              </div>
                          </el-card>
                      </el-col>
                      <el-dialog :title="lzxysDailogTitle"  close="onClose"  :visible.sync="lzxysDailog" :close-on-click-modal="false" >
                          <img class="image" :src="dialogLzxysImageUrl" alt="">
                      </el-dialog>
                  </div>
              </el-tab-pane>
          </el-tabs>
        </el-col>
        <!---------------------------------------------------------------------------------------------------------------------------------------------->

    </div>
</template>
<script>
    import { fileServer } from '../../common/js/constance';
    export default {
        data() {
            return {
                currentTabName:'',// 当前Tabs页签name
                imgBasePath:'',
                hosId:'',
                yyzzBeginDate:'',
                jyxkBeginDate:'',
                swdjBeginDate:'',
                zzjgBeginDate:'',
                ylqxjyxkBeginDate:'',
                ylqxjybaBeginDate:'',
                yyzzEndDate:'',
                jyxkEndDate:'',
                swdjEndDate:'',
                zzjgEndDate:'',
                ylqxjyxkEndDate:'',
                ylqxjybaEndDate:'',
                yyzzCertCode:'',
                jyxkCertCode:'',
                swdjCertCode:'',
                zzjgCertCode:'',
                ylqxjyxkCertCode:'',
                ylqxjybaCertCode:'',
                yyzzChecked:false,
                jyxkChecked:false,
                swdjChecked:false,
                zzjgChecked:false,
                ylqxjyxkChecked:false,
                ylqxjybaChecked:false,
                newjyxk:'',
                newyyzz:'',
                newswdj:'',
                newzzjg:'',
                newylqxjyxk:'',
                newylqxjyba:'',
                editDisable:false,

                errors: {
                    codeErr: '',
                    cnameErr:''
                },
                logoUrl:'',
                mfrsKindDict: [{
                    value: '1',
                    label: '境内'
                }, {
                    value: '2',
                    label: '境外'
                }],
                registCertKindDict: [{
                    value: '1',
                    label: '注册证'
                }, {
                    value: '2',
                    label: '备案证'
                },{
                    value: '3',
                    label: '其他'
                }],
                cnsImage:[],
               yyzzImgArray:[],
                jyxkImgArray:[],
                swdjImgArray:[],
                zzjgImgArray:[],
                ylqxjyxkImgArray:[],
                ylqxjybaImgArray:[],
                yyzzObj:'',
                jyxkObj:'',
                swdjObj:'',
                zzjgObj:'',
                ylqxjyxkObj:'',
                ylqxjybaObj:'',
                myyzzImgArray:[],
                mjyxkImgArray:[],
                mswdjImgArray:[],
                mzzjgImgArray:[],
                mylqxjyxkImgArray:[],
                mylqxjybaImgArray:[],
                myyzzObj:'',
                mjyxkObj:'',
                mswdjObj:'',
                mzzjgObj:'',
                mylqxjyxkObj:'',
                mylqxjybaObj:'',
                mswdjChecked:false,
                mjyxkChecked:false,
                myyzzChecked:false,
                mzzjgChecked:false,
                mylqxjyxkChecked:false,
                mylqxjybaChecked:false,

                dialogWtsImageUrl:'',
                dialogCnsImageUrl:'',
                dialogSqsImageUrl:'',
                dialogcompanyImageUrl:'',
                dialogRegistCertImageUrl:'',
                dialogMfrsImageUrl:'',
                certsVosArray:[],
                certsVosObj:{},
                mfrsImgArray:[],
                company:{},
                cnsDailog:false,
                wtsDailog:false,
                sqsDailog:false,
                mfrsDailog:false,
                mfrsImgDailog:false,

                companyImgDailog:false,
                cnsDailogTitle:'',
                wtsDailogTitle:'',
                sqsDailogTitle:'',
                mfrsDailogTitle:'',

                companyImgDailogTitle:'',
                mfrsImgDailogTitle:'',
                activeName:'first',
                inputProvSearch: "",
                currentRow: {},
                regCertsData: [],
                modelTitle: "",
                regCertLoading: false,
                baseURL: '/spdHDIService/myInfo',



                /***************************供应商列表********************************/
                currentProv:{},
                provHosInfos:[],
                pTotal: 0,
                pPage: 1,
                pPageSize: 20,
                filters: {
                    provName:''
                },

                /***************************过期证件**********************************/
                provOutdateCerts:[],
                oTotal: 0,
                oPage: 1,
                oPageSize: 15,
                outDateCertQuery:{
                    isValiding:'',
                    certKind:'',
                    outDate:'-30',
                    aboutName:''
                },
                currentOutdateCert:{},// 当前过期证件对象，点击'查看图片'时用到
                outdateCertsImg:[],// 过期证件图片path数组
                outDateDict: [{
                    value: '0',
                    label: '已过期'
                },{
                    value: '-7',
                    label: '一周内过期'
                },{
                    value: '-30',
                    label: '一月内过期'
                },{
                    value: '-90',
                    label: '三个月内过期'
                },{
                    value: '-180',
                    label: '半年内过期'
                },{
                    value: '1',
                    label: '长期有效'
                }],
                outDateCertKind: [{
                    value: '供应商营业执照',
                    label: '供应商营业执照'
                }, {
                    value: '供应商经营许可证',
                    label: '供应商经营许可证'
                }, {
                    value: '供应商税务登记证',
                    label: '供应商税务登记证'
                }, {
                    value: '生产厂家营业执照',
                    label: '生产厂家营业执照'
                }, {
                    value: '生产厂家经营许可证',
                    label: '生产厂家经营许可证'
                }, {
                    value: '生产厂家税务登记证',
                    label: '生产厂家税务登记证'
                }, {
                    value: '医疗器械注册证',
                    label: '医疗器械注册证'
                }, {
                    value: '经销授权书',
                    label: '经销授权书'
                }, {
                    value: '承诺书',
                    label: '承诺书'
                }, {
                    value: '法人委托书',
                    label: '法人委托书'
                }, {
                    value: '销售代表',
                    label: '销售代表'
                }, {
                    value: '廉政协议书',
                    label: '廉政协议书'
                }],
                outdateCertDailogTitle:'',
                outdateCertDailog:false,
                dialogOutdateCertImageUrl:'',
                outdateCertImgDailogTitle:'',
                outdateImgCertDailog:false,

                /********************************器械注册证**********************************/
                registCerts:[],
                registCertImgArray:[],
                registCertDailogTitle:'',
                registCertDailog:false,
                registCertImageUrl:'',
                registCertImgDailogTitle:'',
                registCertImgDailog:false,
                registCertQuery:{
                    productName:'',
                    certificateCode:''
                },
                rTotal: 0,
                rPage: 1,
                rPageSize: 10,
                regCertsQuery:{
                    isValiding:'',
                    certKind:'',
                    outDate:'',
                    aboutName:''
                },
                currentRegCert:{},// 当前过期证件对象，点击'查看图片'时用到
                RegCertsImg:[],// 过期证件图片path数组
                RegCertDailogTitle:'',
                RegCertDailog:false,
                dialogRegCertImageUrl:'',
                RegCertImgDailogTitle:'',
                RegCertImgDailog:false,

                /********************************厂家三证***************************************/
                mTotal: 0,
                mPage: 1,
                mPageSize: 10,
                mfrsData:[],
                mfrsCertQuery:{
                    mfrsName:'',
                    mfrsKind:''
                },

                /********************************经销授权书***************************************/
                jxsqsData:[],
                jTotal: 0,
                jPage: 1,
                jPageSize: 10,
                jxsqsQuery:{
                    mfrsName:''
                },

                /********************************法人委托书***************************************/
                wtsData:[],
                wTotal: 0,
                wPage: 1,
                wPageSize: 10,
                wtsQuery:{
                    ename:''
                },

                /********************************承诺书***************************************/
                cnsData:[],

                /**************************销售代表*************************/
                xsdbData:[],
                xsdbQuery:{salesName:''},
                xTotal: 0,
                xPage: 1,
                xPageSize: 10,
                xsdbDailog:false,
                xsdbDailogTitle:'',
                dialogXsdbImageUrl:'',

                /**************************廉政协议书*************************/
                lzxysImage:[],
                lzxysData:[],
                lzxysDailogTitle:'',
                lzxysDailog:false,
                dialogLzxysImageUrl:'',

            }
        },
        methods: {
            /************************************左侧供应商列表<begin>*****************************************/
            /**
             * 重置供应商查询
             */
            resetProvName:function (formName) {
                this.$refs[formName].resetFields();
                this.getHosProvInfoVoList(1);
            },
            // 医院获取供应商列表
            getHosProvInfoVoList: function (page) {
                this.pPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provName: this.filters.provName
                    },
                    page: this.pPage,
                    rows: this.pPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provManager/hosProvInfo/hosProvInfoVoList', param).then(res => {
                    o.provHosInfos = res.data.data.data;
                    o.pTotal = res.data.data.total;
                    if(o.provHosInfos.length>0){
                        o.$nextTick(function () {
                            o.setCurrent(o.provHosInfos[0]);
                        });
                    }
                }, err => {});
            },
            setCurrent(row) {
                this.currentProv = row;
                this.$refs.provListTable.setCurrentRow(row);
            },
            pHandleSizeChange(val) {
                this.pPageSize = val;
                this.getHosProvInfoVoList(this.pPage);
            },
            pHandleCurrentChange(val) {
                this.pPage = val;
                this.getHosProvInfoVoList(this.pPage);
            },
            // 点击供应商事件
            _queryProvGoodsList: function (row) {

                this.currentProv = row;
//                this.queryProvOutdateCerts(1);

                var tempTabObj = {name:this.currentTabName};
                this.handleClick(tempTabObj);



            },
            /************************************左侧供应商列表<end>******************************************/
            /************************************右侧过期证件<begin>******************************************/
            // 查询过期证件
            queryProvOutdateCerts:function (page) {
                this.oPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        certKind:this.outDateCertQuery.certKind,
                        outDate:this.outDateCertQuery.outDate,
                        isValiding:this.outDateCertQuery.isValiding,
                        aboutName:this.outDateCertQuery.aboutName

                    },
                    page: this.oPage,
                    rows: this.oPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/queryProvOutdateCerts', param).then(res => {
                    o.provOutdateCerts = res.data.data.data;
                    o.oTotal = res.data.data.total;
                }, err => {});
            },
            /**
             * 重置查询条件
             */
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.queryProvOutdateCerts(1);
            },
            oHandleSizeChange(val) {
                this.oPageSize = val;
                this.queryProvOutdateCerts(this.oPage);
            },
            oHandleCurrentChange(val) {
                this.oPage = val;
                this.queryProvOutdateCerts(this.oPage);
            },

            // 查看过期证件
            queryOutdateCertsImg(d){
                d.hosId = this.hosId;
                this.currentOutdateCert = d;
                this.axios.post('/spdHERPService/provMgr/provCerts/queryOutdateCertsImg', d).then(res => {
                    this.outdateCertsImg = res.data.data || [];
                    this.queryOutdateCertsDailog();
                }, err =>{})
            },
            // 查看过期证件Dailog
            queryOutdateCertsDailog (){
                this.outdateCertDailogTitle = '过期证件 查看' + '【'+ this.currentOutdateCert.aboutName +'】';
                this.outdateCertDailog = true;
            },
            // 查看过期证件ImgDailog
            getOutdateCertsImg (d){
                this.dialogOutdateCertImageUrl = this.imgBasePath + d.filePath;
                this.outdateCertImgDailogTitle = '过期证件图片 查看' + '【'+ this.currentOutdateCert.aboutName +'】';
                this.outdateImgCertDailog = true;
            },
            formateIsValiding:function (row) {
                return row.isValiding == '1'?'是':'否';
            },
            /*************************************右侧过期证件<end>*******************************************/

            /*************************************右侧企业三证<begin>*******************************************/
            /**
             * 获取供应商信息
             */
            _getProvCompanyInfo (){
                let o = this;
                o.axios.post('/spdHERPService/myInfo/companyInfo/getCompanyInfo', o.currentProv.provId).then(res => {
                    if (res.data.code == "0") {
                        o.company = res.data.data;
                        o.getProvCompanyInfo(o.company);
                    } else {
                        o.$message.error(res.data.msg);
                    }
                }, err => {});
            },
            getProvCompanyInfo:function (company) {
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
                    // 组织机构代码证对象
                    this.zzjgObj = company.companyCertificateVos.find(item => item.certKind == 4);
                    if(this.zzjgObj){
                        this.zzjgChecked = this.zzjgObj.isValiding == '1' ? true : false;
                        this.zzjgObj.expdtBeginDate = this.zzjgObj.expdtBeginDate == null? '' : this.zzjgObj.expdtBeginDate.substr(0,10);
                        this.zzjgObj.expdtEndDate = this.zzjgObj.expdtEndDate == null? '' : this.zzjgObj.expdtEndDate.substr(0,10);
                        // 组织机构代码证图片数组
                        this.zzjgImgArray = this.zzjgObj.basCompanyCertificateImages;
                    }
                     // 医疗器械经营许可证对象
                    this.ylqxjyxkObj = company.companyCertificateVos.find(item => item.certKind == 5);
                    if(this.ylqxjyxkObj){
                        this.ylqxjyxkChecked = this.ylqxjyxkObj.isValiding == '1' ? true : false;
                        this.ylqxjyxkObj.expdtBeginDate = this.ylqxjyxkObj.expdtBeginDate == null? '' : this.ylqxjyxkObj.expdtBeginDate.substr(0,10);
                        this.ylqxjyxkObj.expdtEndDate = this.ylqxjyxkObj.expdtEndDate == null? '' : this.ylqxjyxkObj.expdtEndDate.substr(0,10);
                        // 医疗器械经营许可证图片数组
                        this.ylqxjyxkImgArray = this.ylqxjyxkObj.basCompanyCertificateImages;
                    }
                    // 医疗器械经营备案证对象
                    this.ylqxjybaObj = company.companyCertificateVos.find(item => item.certKind == 6);
                    if(this.ylqxjybaObj){
                        this.ylqxjybaChecked = this.ylqxjybaObj.isValiding == '1' ? true : false;
                        this.ylqxjybaObj.expdtBeginDate = this.ylqxjybaObj.expdtBeginDate == null? '' : this.ylqxjybaObj.expdtBeginDate.substr(0,10);
                        this.ylqxjybaObj.expdtEndDate = this.ylqxjybaObj.expdtEndDate == null? '' : this.ylqxjybaObj.expdtEndDate.substr(0,10);
                        // 医疗器械经营备案证图片数组
                        this.ylqxjybaImgArray = this.ylqxjybaObj.basCompanyCertificateImages;
                    }
                }
                this.logoUrl = this.imgBasePath + company.logo;
            },
            /**************************************************右侧企业三证<end>********************************************************/

            /**************************************************右侧医疗器械注册证<begin>*************************************************/
            // 查询医疗器械注册证
            getPushedRegCertsList:function (page) {
                this.rPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        productName:this.registCertQuery.productName,
                        certificateCode:this.registCertQuery.certificateCode
                    },
                    page: this.rPage,
                    rows: this.rPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/getPushedRegCertsList', param).then(res => {
                    o.registCerts = res.data.data.data;
                    o.rTotal = res.data.data.total;
                }, err => {});
            },

            /**
             * 重置查询条件
             */
            resetFormR(formName) {
                this.$refs[formName].resetFields();
                this.getPushedRegCertsList(1);
            },
            rHandleSizeChange(val) {
                this.rPageSize = val;
                this.getPushedRegCertsList(this.rPage);
            },
            rHandleCurrentChange(val) {
                this.rPage = val;
                this.getPushedRegCertsList(this.rPage);
            },
            /**
             * 查看注册证小图片列表
             */
            checkRegistCerts (d){
                this.registCertImgArray = [];
                if (d.registrationImages.length>0){
                    this.registCertImgArray = d.registrationImages;
                }
                this.registCertDailogTitle = '注册证 查看' + '【'+ this.currentProv.provName +'】';
                this.registCertDailog = true;
            },
            /**
             * 查看注册证大图片
             */
            checkRegistCert (d){
                this.registCertImageUrl = this.imgBasePath + d.filePath;
                this.registCertImgDailogTitle = '注册证图片 查看' + '【'+ this.currentProv.provName +'】';
                this.registCertImgDailog = true;
            },
            /**************************************************右侧医疗器械注册证<end>*************************************************/

            /**************************************************厂家三证列表<begin>*************************************************/
            // 查询厂家三证
            getBasMfrsList:function (page) {
                this.mPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        mfrsName:this.mfrsCertQuery.mfrsName,
                        mfrsKind:this.mfrsCertQuery.mfrsKind
                    },
                    page: this.mPage,
                    rows: this.mPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/getBasMfrsList', param).then(res => {
                    o.mfrsData = res.data.data.data;
                    o.mTotal = res.data.data.total;
                }, err => {});
            },

            /**
             * 重置查询条件
             */
            resetFormM(formName) {
                this.$refs[formName].resetFields();
                this.getBasMfrsList(1);
            },
            mHandleSizeChange(val) {
                this.mPageSize = val;
                this.getBasMfrsList(this.mPage);
            },
            mHandleCurrentChange(val) {
                this.mPage = val;
                this.getBasMfrsList(this.mPage);
            },

            /**
             * 查看厂家三证Dialog
             */
            checkMfrs (d){
                this.mfrsImgArray = d;
                this.getMfrsCertsInfo(d);
                this.mfrsDailogTitle = '厂家三证 查看' + '【'+ this.currentProv.provName +'】';
                this.mfrsDailog = true;
            },
            /**
             * 获取厂家三证图片对象信息
             */
            getMfrsCertsInfo (mfrs){
                if(mfrs.basMfrsCertificateVos.length>0) {
                    // 营业执照对象
                    this.myyzzChecked = this.myyzzObj.isValiding == '1' ? true : false;
                    this.myyzzObj = mfrs.basMfrsCertificateVos.find(item => item.certKind == 1);
                    // 营业执照图片数组
                    if(this.myyzzObj){
                        this.myyzzImgArray = this.myyzzObj.provMfrsCertificateImages;
                    }
                    // 经营许可证对象
                    this.mjyxkChecked = this.mjyxkObj.isValiding == '1' ? true : false;
                    this.mjyxkObj = mfrs.basMfrsCertificateVos.find(item => item.certKind == 2);
                    if(this.mjyxkObj){
                        // 经营许可证图片数组
                        this.mjyxkImgArray = this.mjyxkObj.provMfrsCertificateImages;
                    }
                    // 税务登记证对象
                    this.mswdjChecked = this.mswdjObj.isValiding == '1' ? true : false;
                    this.mswdjObj = mfrs.basMfrsCertificateVos.find(item => item.certKind == 3);
                    if(this.mswdjObj){
                        // 税务登记证图片数组
                        this.mswdjImgArray = this.mswdjObj.provMfrsCertificateImages;
                    }
                }
            },
            /**
             * 查看厂家三证图片
             */
            checkMfrsImg (d){
                this.mfrsImgDailogTitle = '企业三证图片 查看' + '【'+ this.currentProv.provName +'】';
                this.mfrsImgDailog = true;
                this.dialogMfrsImageUrl = this.imgBasePath + d.filePath;
            },

            /**
             * 格式化厂家类型
             */
            formatmfrsKind (row){
                return this.mfrsKindDict.find(item => item.value == row.mfrsKind).label;
            },

            /**************************************************厂家三证列表<end>**************************************************/

            /**************************************************经销授权书列表<begin>**********************************************/
            // 查询经销授权书
            jxsqsList:function (page) {
                this.jPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        mfrsName:this.jxsqsQuery.mfrsName
                    },
                    page: this.jPage,
                    rows: this.jPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/jxsqsList', param).then(res => {
                    o.jxsqsData = res.data.data.data;
                    o.jTotal = res.data.data.total;
                }, err => {});
            },

            /**
             * 重置查询条件
             */
            resetFormJ(formName) {
                this.$refs[formName].resetFields();
                this.jxsqsList(1);
            },
            jHandleSizeChange(val) {
                this.jPageSize = val;
                this.jxsqsList(this.jPage);
            },
            jHandleCurrentChange(val) {
                this.jPage = val;
                this.jxsqsList(this.jPage);
            },
            /**************************************************经销授权书列表<end>*************************************************/

            /**************************************************法人委托书列表<begin>**********************************************/
            // 查询法人委托书
            wtsList:function (page) {
                this.wPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        ename:this.wtsQuery.ename
                    },
                    page: this.wPage,
                    rows: this.wPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/wtsList', param).then(res => {
                    o.wtsData = res.data.data.data;
                    o.wTotal = res.data.data.total;
                }, err => {});
            },
            /**
             * 重置查询条件
             */
            resetFormW(formName) {
                this.$refs[formName].resetFields();
                this.wtsList(1);
            },
            wHandleSizeChange(val) {
                this.wPageSize = val;
                this.wtsList(this.wPage);
            },
            wHandleCurrentChange(val) {
                this.wPage = val;
                this.wtsList(this.wPage);
            },
            /**************************************************法人委托书列表<end>*************************************************/

            /**************************************************承诺书列表<begin>**********************************************/
            // 查询承诺书
            cnsList:function (page) {
                var param = {
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId
                    },
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/cnsList', param).then(res => {
                    o.cnsData = res.data.data.data;
                    for(var i=0;i<o.cnsData.length;i++){
                        if(o.cnsData[i].expdtBeginDate){
                            o.cnsData[i].expdtBeginDate = o.cnsData[i].expdtBeginDate.substr(0,10);
                        }
                        if(o.cnsData[i].expdtEndDate){
                            o.cnsData[i].expdtEndDate = o.cnsData[i].expdtEndDate.substr(0,10);
                        }
                        o.cnsImage.push(o.imgBasePath + o.cnsData[i].filePathWts);
                    }
                }, err => {});
            },
            /**
             * 查看承诺书
             */
            checkCns (d){
                this.cnsDailogTitle = '承诺书 查看' + '【'+ this.currentProv.provName +'】';
                this.cnsDailog = true;
                this.dialogCnsImageUrl = this.imgBasePath + d.filePathWts;
            },
            /**************************************************承诺书列表<end>*************************************************/

            /**************************************************销售代表列表<begin>**********************************************/
            // 查询销售代表
            salesRepList:function (page) {
                this.xPage = page;
                var param = {
                    orderBy: "",
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId,
                        salesName:this.xsdbQuery.salesName
                    },
                    page: this.xPage,
                    rows: this.xPageSize
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/salesRepList', param).then(res => {
                    o.xsdbData = res.data.data.data;
                    o.xTotal = res.data.data.total;
                }, err => {});
            },
            /**
             * 重置查询条件
             */
            resetFormX(formName) {
                this.$refs[formName].resetFields();
                this.salesRepList(1);
            },
            xHandleSizeChange(val) {
                this.xPageSize = val;
                this.salesRepList(this.xPage);
            },
            xHandleCurrentChange(val) {
                this.xPage = val;
                this.salesRepList(this.xPage);
            },
            /**
             * 查看销售代表证件
             */
            checkXsdbcard (d){
                this.xsdbDailogTitle = '销售代表证件 查看' + '【'+ this.currentProv.provName +'】';
                this.xsdbDailog = true;
                this.dialogXsdbImageUrl = this.imgBasePath + d.filePath;
            },
            /**************************************************销售代表列表<end>*************************************************/

            /*************************************************廉政协议书列表<begin>**********************************************/
            // 查询廉政协议书
            listIncorruptAgrmt:function () {
                var param = {
                    queryObject: {
                        hosId:this.hosId,
                        provId:this.currentProv.provId
                    },
                };
                let o = this;
                o.axios.post('/spdHERPService/provMgr/provCerts/listIncorruptAgrmt', param).then(res => {
                    o.lzxysData = res.data.data.data;
                    for(var i=0;i<o.lzxysData.length;i++){
                        if(o.lzxysData[i].expdtBeginDate){
                            o.lzxysData[i].expdtBeginDate = o.lzxysData[i].expdtBeginDate.substr(0,10);
                        }
                        if(o.lzxysData[i].expdtEndDate){
                            o.lzxysData[i].expdtEndDate = o.lzxysData[i].expdtEndDate.substr(0,10);
                        }
                        o.lzxysImage.push(o.imgBasePath + o.lzxysData[i].filePath);
                    }
                }, err => {});
            },
            /**
             * 廉政协议书
             */
            checkLzxys (d){
                this.lzxysDailogTitle = '廉政协议书 查看' + '【'+ this.currentProv.provName +'】';
                this.lzxysDailog = true;
                this.dialogLzxysImageUrl = this.imgBasePath + d.filePath;
            },
            /**************************************************廉政协议书列表<end>*************************************************/

            /**
             * 格式化生效日期
             */
            formatterBeginDate (row,colum) {
                var result = '';
                if(row.expdtBeginDate != null && row.expdtBeginDate != ''){
                    result = row.expdtBeginDate.substr(0,10);
                }
                return result;
            },
            /**
             * 格式化截止日期
             */
            formatterEndDate (row,colum) {
                var result = '';
                if(row.expdtEndDate != null && row.expdtEndDate != ''){
                    result = row.expdtEndDate.substr(0,10);
                }
                return result;
            },
            /**
             * 格式化过期天数
             */
            formatterOutdate (row,colum) {
                var result = '';
                if(row.outDate == null){
                    return '';
                }
                if(row.outDate.indexOf('-') > -1){// 如果过期天数包含负数，则说明还没过期
                    result = row.outDate.substr(1) + '天后过期';
                }else {
                    result = '已过期' + row.outDate + '天';
                }
                return result;
            },

            /**
             * 选中左侧某行供应商onclick事件：查询'我的证件'集合对象
             */
            selectProvRow (row, event, column) {
                if (this.currentRow.provId === row.provId && this.currentRow.hosId === row.hosId) {
                    return;
                }
                this.currentRow = row;
                this.getPushedCertsMap(row);
            },
            /**
             * 查询过期证件(查询按钮事件查询)
             */
            _getPushedCertsMap(){
                this.getPushedCertsMap(this.currentRow);
            },
            handleCSizeChange (val) {
                this.pPageSize = val;
                this.getPushedCertsMap(this.currentRow);
            },
            handleCCurrentChange (val) {
                this.pPageNum = val;
                this.getPushedCertsMap(this.currentRow);
            },
            /**
             * 获取'我的证件'集合对象
             */
            getPushedCertsMap(row){
                this.currentRow = row;
                var param = {
                    orderBy: "",
                    queryObject: {
                        certKind : this.outDateCertQuery.certKind,
                        outDate : this.outDateCertQuery.outDate,
                        isValiding : this.outDateCertQuery.outDate == '1'?'1':'',
                        aboutName : this.outDateCertQuery.aboutName,
                        provId : row.provId,
                        hosId : row.hosId
                    },
                    page: this.pPageNum,
                    rows: this.pPageSize
                };
                this.axios.post(this.baseURL + '/provRegistInfoPush/getPushedCertsMap', param).then(res => {
                    this.pushedGoodsData = res.data.data.data;
                    this.wtsData = res.data.data.frwt;
                    this.cnsData = res.data.data.cns;
                    for(var i=0;i<this.cnsData.length;i++){
                        if(this.cnsData[i].expdtBeginDate){
                            this.cnsData[i].expdtBeginDate = this.cnsData[i].expdtBeginDate.substr(0,10);
                        }
                        if(this.cnsData[i].expdtEndDate){
                            this.cnsData[i].expdtEndDate = this.cnsData[i].expdtEndDate.substr(0,10);
                        }
                        this.cnsImage.push(this.imgBasePath + this.cnsData[i].filePathWts);
                    }
                    this.jxsqsData = res.data.data.jxsqs;
                    this.mfrsData = res.data.data.mfrs;
                    this.registCerts = res.data.data.registCerts;
                    this.certsVosObj = res.data.data.certsVos;
                    this.pPageNum = this.certsVosObj.pageNum;
                    this.pPageSize = this.certsVosObj.pageSize;
                    this.pTotal = this.certsVosObj.total;
                    this.certsVosArray = res.data.data.certsVos.data;
                    this.getProvCompanyInfo(res.data.data.provInfo);
                    this.rTotal = res.data.data.total;
                    // this.rPageNum = res.data.data.pageNum;
                }, err =>{
                })
            },

            /**
             * 查看委托人身份证
             */
            checkIdcard (d){
                this.wtsDailogTitle = '委托人身份证 查看' + '【'+ this.currentProv.provName +'】';
                this.wtsDailog = true;
                this.dialogWtsImageUrl = this.imgBasePath + d.filePathWtr;
            },
            /**
             * 查看委托书
             */
            checkWts (d){
                this.wtsDailogTitle = '委托书 查看' + '【'+ this.currentProv.provName +'】';
                this.wtsDailog = true;
                this.dialogWtsImageUrl = this.imgBasePath + d.filePathWts;
            },

            /**
             * 查看授权书
             */
            checkSqs (d){
                this.sqsDailogTitle = '授权书 查看' + '【'+ this.currentProv.provName +'】';
                this.sqsDailog = true;
                this.dialogSqsImageUrl = this.imgBasePath + d.filePath;
            },

            /**
             * 格式化注册证类型
             */
            formatregistCertKind (row){
                return this.registCertKindDict.find(item => item.value == row.regKind).label;
            },
            /**
             * 查看企业三证
             */
            checkCompanyImg (d){
                this.companyImgDailogTitle = '企业三证 查看' + '【'+ this.currentProv.provName +'】';
                this.companyImgDailog = true;
                this.dialogcompanyImageUrl = this.imgBasePath + d.filePath;
            },
            handleClick(tab) {
                this.currentTabName = tab.name;
                if(tab.name == ''){
                    tab.name = 'first';
                }
                if(tab.name === 'first'){// 过期证件 页签
                    this.queryProvOutdateCerts(1);
                }else if(tab.name === 'second'){// 企业三证 页签
                    this._getProvCompanyInfo();
                }else if(tab.name === 'third'){// 医疗器械注册证 页签
                    this.getPushedRegCertsList(1);
                }else if(tab.name === 'fourth'){// 厂家三证 页签
                    this.getBasMfrsList(1);
                }else if(tab.name === 'fifth'){// 经销授权书 页签
                    this.jxsqsList(1);
                }else if(tab.name === 'sixth'){// 法人委托书 页签
                    this.wtsList(1);
                }else if(tab.name === 'seventh'){// 承诺书 页签
                    this.cnsList(1);
                }else if(tab.name === 'eighth'){// 销售代表 页签
                    this.salesRepList(1);
                }else {// 廉政协议书 页签
                    this.listIncorruptAgrmt();
                }
            },
        },
        mounted() {
            this.hosId = this.user.corpId;
            this.imgBasePath = fileServer.imgBasePath;
            this.getHosProvInfoVoList(1);
        }
    };

</script>
<style>
  #certslist .time {font-size: 12px;  color: #999;}
  #certslist .bottom {margin-top: 13px;  line-height: 14px;}
  #certslist .button {padding: 0;  float: right;}
  #certslist .image {width: 100%;display: block;}
  #certslist .clearfix:before,
  #certslist .clearfix:after {display: table;  content: "";}
  #certslist .clearfix:after {clear: both}
  #certslist .companyOutput .el-form-item {width: 75%;margin-bottom: 6px;}
  #certslist .companyOutput el-input {border: 0px}
  #certslist .companyOutput {padding-bottom: 100px;}
  #certslist .outdateCert .el-dialog{
    height: 75%;
    border-radius: 5px;
  }
  #certslist .fullheight {
    height: 100%;
  }
   #certslist .cerListtableheight{
     height: 100%;
     width: 100%;
   }

   /************************************** 三证查看CSS样式 *****************************/
    #certslist .ImageCard{
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
  #certslist .certList {   width: 100%;;display: block;height: 130px;   }
  #certslist .certsCard{
    min-width: 195px;
    margin-right: 0.3%;
    background-color: rgb(238, 241, 246);
    border: 1px solid #d1dbe5;
    border-radius: 4px;
    overflow: hidden;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04);
  }
  #certslist .certWordsStyle{
    font-size: 12px;
    line-height: 27px;
    font-weight: bold;
    margin-left: 4%;
  }
  #certslist .certListStyle{
    margin-left: 3%;
    width: 93%;
    background-color: white;
    height: 26px;
    margin-bottom: 2%;
  }
  #certslist .certButtonStyle{
    line-height: 27px;
    margin-right: 3%;
  }
  #certslist .certCodes{
    margin-left: 3%;
    margin-right: 4%;
  }
  #certslist .outdateClass{
    color: red;
  }
  #certslist .el-pagination-right {
    float: right;
    margin-top: 5px;
  }
  #certsListCompanyCerts .el-col-7{
      min-width: 405px;
  }
  #certsListCompanyCerts .el-col-16{
      min-width: 614px;
  }

  /*************************************** 三证查看CSS样式 ******************************/

  #certslist .provList-tableheight{
      height:  calc(100% - 75px);
  }

  /* .el-table__body-wrapper {
      overflow-x: hidden;
      height: calc(100% - 25px);
  }
  .el-col-24 {
      width:auto;
  } */
  #certslist .tb-height {
      height: calc(100% - 70px);
  }
  /* .el-tabs__content {
      height: 100%;
  }
  .el-table {
      overflow: auto;
  } */
</style>